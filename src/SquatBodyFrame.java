
import java.awt.Point;

public class SquatBodyFrame {
	Point m_head;
	Point m_shoulder;
	Point m_ass;
	Point m_knee;
	Point m_feet;

	public SquatBodyFrame(Point m_head, Point m_shoulder, Point m_ass, Point m_knee, Point m_feet) {
		super();
		this.m_head = m_head;
		this.m_shoulder = m_shoulder;
		this.m_ass = m_ass;
		this.m_knee = m_knee;
		this.m_feet = m_feet;
	}

	public double getBackAngle() {
		double shoulderToAssAngle = Math.atan2(m_shoulder.getY() - m_ass.getY(), m_shoulder.getX() - m_ass.getX());
		double kneeToAssAngle = Math.atan2(m_knee.getY() - m_ass.getY(), m_knee.getX() - m_ass.getX());
		return Math.toDegrees(shoulderToAssAngle - kneeToAssAngle);

	}

	public double getKneeBendAngle() {
		double assToKneeAngle = Math.atan2(m_ass.getY() - m_knee.getY(), m_ass.getX() - m_knee.getX());
		double feetToKneeAngle = Math.atan2(m_feet.getY() - m_knee.getY(), m_feet.getX() - m_knee.getX());
		return Math.toDegrees(assToKneeAngle - feetToKneeAngle);
	}

	public String toString() {
		return "======================\n" + "Head Value= " + m_head + "\nShoulder Value= " + m_shoulder
				+ "\nAss Value= " + m_ass + "\nFeet Value= " + m_feet + "\nBack Angle= " + this.getBackAngle()
				+ "\nKneeBend Angle = " + this.getKneeBendAngle() + "\n======================\n";
	}


	public static void main(String [] args) {


		/*
		 * a good position: shoulder : 517,826 ass: 126 ,1061 foot: 593, 1001 a
		 * bad position: shoulder: 458,594 ass: 186,906 foot: 587, 1100
		 */
		Point[] goodBodyParam = new Point[5];
		goodBodyParam[0] = new Point(504, 652);//Head
		goodBodyParam[1] = new Point(517, 826);//Shoulder
		goodBodyParam[2] = new Point(126, 1061);//Ass
		goodBodyParam[3] = new Point(593, 1001);//Knee
		goodBodyParam[4] = new Point(349, 1393);//Feet
		SquatBodyFrame goodBodyFrame = new SquatBodyFrame(goodBodyParam[0], goodBodyParam[1], goodBodyParam[2],
				goodBodyParam[3], goodBodyParam[4]);
		System.out.println(goodBodyFrame);

		Point[] testAngleParam = new Point[5];
		testAngleParam[0] = new Point(0, 0);//Head
		testAngleParam[1] = new Point(1, 0);//Shoulder
		testAngleParam[2] = new Point(0, 0);//Ass
		testAngleParam[3] = new Point(0, 1);//Knee
		testAngleParam[4] = new Point(1, 0);//Feet

		SquatBodyFrame testAngleFrame = new SquatBodyFrame(testAngleParam[0], testAngleParam[1], testAngleParam[2],
				testAngleParam[3], testAngleParam[4]);
		System.out.println(testAngleFrame);
		
		
		
		Point zeroZero=new Point(0,0);
		Point oneZero=new Point(1,0);
		Point threeZero=new Point(2,0);
		Point fourZero=new Point(4,0);
		Point fiveZero=new Point (5,0);
		Squat [] squatsArray= new Squat [30];
		for (int i=0;i<10;i++){//the getBackAngle stays above 170
			
		}
		for (int i=10;i<21;i++){//the getBackAngle is under 170
			
		
		}
		for (int i=21;i<squatsArray.length;i++){
			
		}

	}

}
