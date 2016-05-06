import java.awt.Point;

public class Main {
    public static void main(String[] args) {


        Point[] goodBodyParam = new Point[5];
        goodBodyParam[0] = new Point(504, 652);//Head
        goodBodyParam[1] = new Point(517, 826);//Shoulder
        goodBodyParam[2] = new Point(126, 1061);//Ass
        goodBodyParam[3] = new Point(593, 1001);//Knee
        goodBodyParam[4] = new Point(349, 1393);//Feet
        SquatFrame goodBodyFrame = new SquatFrame(goodBodyParam[0], goodBodyParam[1], goodBodyParam[2],
                goodBodyParam[3], goodBodyParam[4]);
        System.out.println(goodBodyFrame);

        Point[] testAngleParam = new Point[5];
        testAngleParam[0] = new Point(0, 0);//Head
        testAngleParam[1] = new Point(1, 0);//Shoulder
        testAngleParam[2] = new Point(0, 0);//Ass
        testAngleParam[3] = new Point(0, 1);//Knee
        testAngleParam[4] = new Point(1, 0);//Feet

        SquatFrame testAngleFrame = new SquatFrame(testAngleParam[0], testAngleParam[1], testAngleParam[2],
                testAngleParam[3], testAngleParam[4]);
        System.out.println(testAngleFrame);


        Point zeroZero = new Point(0, 0);
        Point oneZero = new Point(0, 1);
        Point threeZero = new Point(0, 2);
        Squat[] squatsArray = new Squat[30];
        for (int i = 0; i < 10; i++) {//the getBackAngle stays above 170

        }
        for (int i = 10; i < 21; i++) {//the getBackAngle is under 170


        }
        for (int i = 21; i < squatsArray.length; i++) {

        }

    }
}
