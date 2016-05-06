import java.awt.*;

/**
 * Created by ariebarsky on 06/05/2016.
 */
public class SquatFrame {
    Point m_head;
    Point m_shoulder;
    Point m_ass;
    Point m_knee;
    Point m_feet;

    public SquatFrame(Point m_head, Point m_shoulder, Point m_ass, Point m_knee, Point m_feet) {
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
}
