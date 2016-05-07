import argparse
import numpy as np
import cv2
from select_points import IMAGE_SCALE

GAUS_MASK = (25,25)
BG_THRESH = 30
THRESH = 20

def parse_args(): 
    parser = argparse.ArgumentParser(description='Squat points') 
    parser.add_argument('--input', dest='input', required=True, help='Input Video File') 
    parser.add_argument('--background', dest='background', required=True, help='Background file') 
    args = parser.parse_args() 
    return args    

def sekeletonize(img):
    size = np.size(img)
    skel = np.zeros(img.shape,np.uint8)
     
    ret,img = cv2.threshold(img,127,255,0)
    element = cv2.getStructuringElement(cv2.MORPH_CROSS,(3,3))
    done = False
     
    while( not done):
        eroded = cv2.erode(img,element)
        temp = cv2.dilate(eroded,element)
        temp = cv2.subtract(img,temp)
        skel = cv2.bitwise_or(skel,temp)
        img = eroded.copy()
     
        zeros = size - cv2.countNonZero(img)
        if zeros==size:
            done = True
     
    return skel

def mask_skeleton(img, prev, background):
    img = cv2.resize(img, (img.shape[1] / IMAGE_SCALE, img.shape[0]/ IMAGE_SCALE))
    background = cv2.resize(background, (img.shape[1],img.shape[0]))
   
    gray_bg = cv2.cvtColor(background, cv2.COLOR_BGR2GRAY)
    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    gray_bg = cv2.GaussianBlur(gray_bg, GAUS_MASK, 0)
    gray_img = cv2.GaussianBlur(gray_img, GAUS_MASK, 0)
    frameDelta = cv2.absdiff(gray_bg, gray_img)
    thresh = cv2.threshold(frameDelta, BG_THRESH, 255, cv2.THRESH_BINARY)[1] 
    
    # now using the motion
    # uncomment this for motion usage
    """
    if prev is None:
        return (thresh, thresh)  
    frameDelta = cv2.absdiff(prev, thresh)
    motion_thresh = cv2.threshold(frameDelta, THRESH, 255, cv2.THRESH_BINARY)[1]
    
    for _ in xrange(1):
        motion_thresh = cv2.dilate(motion_thresh, None, iterations=20)
        motion_thresh = cv2.erode(motion_thresh,None,iterations =15)
    # remove_big_blobs(motion_thresh)
    
    # contours
    # (contours, _) = cv2.findContours(motion_thresh.copy(), cv2.RETR_EXTERNAL, 	cv2.CHAIN_APPROX_SIMPLE)
    # cv2.drawContours(motion_thresh, contours, -1, (0,255,0), 3)
    return thresh, motion_thresh
    """
    return (thresh, thresh)


def main():
    args = parse_args()
    
    cap = cv2.VideoCapture(args.input)
    background = cv2.imread(args.background)
    prev = None
    while True:
        ret,  img = cap.read()
        if ret is False:
            break
        prev2, result = mask_skeleton(img, prev, background)
        prev = prev2

        cv2.imshow('test', result)
        
        k = cv2.waitKey(10) & 0xff
        if k == 27 or k == ord('q'):
            break
    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
   main()
