public class SquatFrame {
	public Point m_shoulder;
	public Point m_ass;
	public Point m_knee;
	public Point m_feet;

	public SquatFrame(Point m_shoulder, Point m_ass,
			Point m_knee, Point m_feet) {
		super();
		this.m_shoulder = m_shoulder;
		this.m_ass = m_ass;
		this.m_knee = m_knee;
		this.m_feet = m_feet;
	}

	public double getBackAngle() {
		double shoulderToAssAngle = Math.atan2(
				m_shoulder.getY() - m_ass.getY(),
				m_shoulder.getX() - m_ass.getX());
		double kneeToAssAngle = Math.atan2(m_knee.getY() - m_ass.getY(),
				m_knee.getX() - m_ass.getX());
        double angle = Math.toDegrees(shoulderToAssAngle - kneeToAssAngle);
        if (angle < 0){
            angle += 360;
        }
		return angle;

	}

	public double getKneeBendAngle() {
		double assToKneeAngle = Math.atan2(m_ass.getY() - m_knee.getY(),
				m_ass.getX() - m_knee.getX());
		double feetToKneeAngle = Math.atan2(m_feet.getY() - m_knee.getY(),
				m_feet.getX() - m_knee.getX());
        double angle = Math.toDegrees(assToKneeAngle - feetToKneeAngle);
        if (angle < 0){
            angle += 360;
        }
		return angle;
	}


    public String toString() {
        return "======================\nShoulder Value= " + m_shoulder
                + "\nAss Value= " + m_ass + "\nKnee Value= " + m_knee + "\nFeet Value= " + m_feet + "\nBack Angle= " + this.getBackAngle()
                + "\nKneeBend Angle = " + this.getKneeBendAngle() + "\n======================\n";
    }

}
