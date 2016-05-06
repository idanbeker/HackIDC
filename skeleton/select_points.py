import numpy as np
import cv2
import copy
import argparse

IMAGE_SCALE = 2
STAGES = ["SHOULDERS", "HIP", "Knee", "Feet"]
NUM_POINTS = len(STAGES)

SELECTION = []
CURRENT_FRAME = None
stoped = False

OUT=""

def handle_click(event, x, y, flags, param):
    global SELECTION
    global cap
    global stoped
    global OUT
    if (event == cv2.EVENT_LBUTTONDOWN):
        SELECTION.append((x,y))
        OUT += "%d,%d," % (x,y)
        if len(SELECTION) == NUM_POINTS:
           # finished
           print SELECTION
           SELECTION = []
           stoped = False

                      

def show_image_with_label(label):
   font = cv2.FONT_HERSHEY_SIMPLEX
   img = copy.copy(CURRENT_FRAME)
   cv2.putText(img, "Select %s" % (STAGES[len(SELECTION)],) ,(50,50), font, 2,(255,255,255))
   if len(SELECTION) > 1:
       for i in xrange(len(SELECTION) - 1):
           cv2.circle(img, (SELECTION[i][0], SELECTION[i][1]), 10, (0,255,0))
           cv2.circle(img, (SELECTION[i+1][0], SELECTION[i+1][1]), 10, (0,255,0))
           cv2.line(img, (SELECTION[i][0], SELECTION[i][1]), (SELECTION[i+1][0], SELECTION[i+1][1]), (255,0,0)) 
   cv2.imshow('image', img)

def parse_args():
    parser = argparse.ArgumentParser(description='Squat points')
    parser.add_argument('--input', dest='input', required=True, help='Input Video File')
    parser.add_argument('--out', dest='output', required=True, help='Output File')
    args = parser.parse_args()
    return args 

def main():
    args = parse_args()

    global CURRENT_FRAME
    global cap
    global stoped
    global OUT
    cap = cv2.VideoCapture(args.input)
    cv2.namedWindow('image')
    cv2.setMouseCallback('image', handle_click)  
    while True:
        if not stoped:
	    ret, img = cap.read()
            if ret is False:
                break
            img = cv2.resize(img, (img.shape[1] / IMAGE_SCALE, img.shape[0] / IMAGE_SCALE))
            CURRENT_FRAME = img
            stoped = True
        k = cv2.waitKey(10) & 0xff
        if k == 27 or k ==ord('q'):
            break
        elif k == ord('n') or k == ord('N'):
            stoped = False
        
        if stoped == True:
            if CURRENT_FRAME is not None : show_image_with_label("select %s" % (STAGES[len(SELECTION)],))
    cv2.destroyAllWindows()
    cap.release()

    with open(args.output, "w") as f:
        f.write(OUT)

if __name__ == "__main__":
    main()
