

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
		return shoulderToAssAngle - kneeToAssAngle;
	}

	public double getKneeBendAngle() {
		double assToKneeAngle = Math.atan2(m_ass.getY() - m_knee.getY(), m_ass.getX() - m_knee.getX());
		double feetToKneeAngle = Math.atan2(m_feet.getY() - m_knee.getY(), m_feet.getX() - m_knee.getX());
		return assToKneeAngle - feetToKneeAngle;
	}

	public String toString() {
		return "======================\n" + "Head Value= " + m_head + "\nShoulder Value= " + m_shoulder
				+ "\nAss Value= " + m_ass + "\nFeet Value= " + m_feet + "\nBack Angle= " + this.getBackAngle()
				+ "\nKneeBend Angle = " + this.getKneeBendAngle() + "======================\n";
	}

	public static void main() {

		/*
		 * a good position: shoulder : 517,826 ass: 126 ,1061 foot: 593, 1001 a
		 * bad position: shoulder: 458,594 ass: 186,906 foot: 587, 1100
		 */
		Point[] goodBodyParam = new Point[5];
		goodBodyParam[0] = new Point(504, 652);
		goodBodyParam[1] = new Point(517, 826);
		goodBodyParam[2] = new Point(126, 1061);
		goodBodyParam[3] = new Point(593, 1001);
		goodBodyParam[4] = new Point(349, 1393);
		SquatBodyFrame goodBodyFrame = new SquatBodyFrame(goodBodyParam[0], goodBodyParam[1], goodBodyParam[2],
				goodBodyParam[3], goodBodyParam[4]);
		System.out.println(goodBodyFrame);

	}

}
