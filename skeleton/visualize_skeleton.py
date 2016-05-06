import argparse
from select_points import IMAGE_SCALE
import cv2

def parse_args():
    parser = argparse.ArgumentParser(description='Squat points')
    parser.add_argument('--input', dest='input', required=True, help='Input Video File')
    
    parser.add_argument('--skeleton', dest='skeleton', required=True)
    args = parser.parse_args()
    return args

def load_skeletion(filename):
    with open(filename) as f:
        s = f.read()
    l = eval("["+s+"]")
    return l

def chunks(l, n):
    """Yield successive n-sized chunks from l."""
    for i in range(0, len(l), n):
        yield l[i:i+n]    

def joints_generator(skeleton_lst):
    assert len(skeleton_lst) % 8 == 0
    frame_chunks = chunks(skeleton_lst, 8)
    for c in frame_chunks:
        yield c
    
def add_joints(img, joints_lst):
    points = list(chunks(joints_lst, 2))
    prev_p = None
    for p in points:
        cv2.circle(img, tuple(p), 10, (0,255,0))
        if prev_p is not None:
            cv2.line(img, tuple(prev_p), tuple(p), (0,0,255))
        prev_p = p

def visualize(inf, skeletonf):
    sk = load_skeletion(skeletonf)
    
    cap = cv2.VideoCapture(inf)
    cv2.namedWindow('image')
    for joints_lst in joints_generator(sk):
        # add img
        ret, img = cap.read()
        img = cv2.resize(img, (img.shape[1] / IMAGE_SCALE, img.shape[0] / IMAGE_SCALE))
        if not ret:
            break
        # add joints
        add_joints(img, joints_lst)

        # show img
        cv2.imshow('image', img)
        k = cv2.waitKey(30) & 0xff
        if k == 27 or k ==  ord('q'):
            break
    cv2.destroyAllWindows()
    cap.release()  

def main():
    args = parse_args()
    visualize(args.input, args.skeleton)
    pass

if __name__ == "__main__":
    main()
    
    
