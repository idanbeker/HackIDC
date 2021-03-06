import org.junit.Test;

import java.util.ArrayList;

public class SquatTest {
    
	
	
	
	@Test
    public void testFindFirstFrame() throws Exception {
        // Point m_head, Point m_shoulder, Point m_ass, Point m_knee, Point m_feet
        SquatFrame sf1 = new SquatFrame(new Point(553, 1060), new Point(554f, 1061f), new Point(555, 1062), new Point(0, 0));
        SquatFrame sf2 = new SquatFrame(new Point(553, 1060), new Point(554f, 1061f), new Point(555, 1062), new Point(0, 0));
        SquatFrame sf3 = new SquatFrame(new Point(554, 1060), new Point(554f, 1061f), new Point(555, 1061), new Point(0, 0));
        SquatFrame sf4 = new SquatFrame(new Point(560, 1060), new Point(554f, 1061f), new Point(555, 1061), new Point(0, 0));
        SquatFrame sf5 = new SquatFrame(new Point(560, 1060), new Point(554f, 1061f), new Point(555, 1061), new Point(0, 0));
        SquatFrame sf6 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf7 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf8 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf9 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf10 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf11 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf12 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf13 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf14 = new SquatFrame(new Point(0f, 0f), new Point(300f, 300f), new Point(200f, 200), new Point(300, 200));
        SquatFrame sf15 = new SquatFrame(new Point(0f, 0f), new Point(300f, 300f), new Point(200f, 200), new Point(300, 100));
        SquatFrame sf16 = new SquatFrame(new Point(0f, 0f), new Point(300f, 300f), new Point(200f, 200), new Point(300, 200));
        SquatFrame sf17 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf18 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf19 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf20 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf21 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf22 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf23 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf24 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf25 = new SquatFrame(new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f), new Point(0f, 0f));
        SquatFrame sf26 = new SquatFrame(new Point(560f, 1060f), new Point(554f, 1061f), new Point(555f, 1061f), new Point(0f, 0f));
        SquatFrame sf27 = new SquatFrame(new Point(560f, 1060f), new Point(554f, 1061f), new Point(555f, 1061f), new Point(0f, 0f));
        SquatFrame sf28 = new SquatFrame(new Point(554f, 1060f), new Point(554f, 1061f), new Point(555f, 1061f), new Point(0f, 0f));
        SquatFrame sf29 = new SquatFrame(new Point(553f, 1060f), new Point(554f, 1061f), new Point(555f, 1062f), new Point(0f, 0f));
        SquatFrame sf30 = new SquatFrame(new Point(553f, 1060f), new Point(554f, 1061f), new Point(555f, 1062f), new Point(0f, 0f));
        ArrayList<SquatFrame> frames = new ArrayList<SquatFrame>();
        frames.add(sf1);
        frames.add(sf2);
        frames.add(sf3);
        frames.add(sf4);
        frames.add(sf5);
        frames.add(sf6);
        frames.add(sf7);
        frames.add(sf8);
        frames.add(sf9);
        frames.add(sf10);
        frames.add(sf11);
        frames.add(sf12);
        frames.add(sf13);
        frames.add(sf14);
        frames.add(sf15);
        frames.add(sf16);
        frames.add(sf17);
        frames.add(sf18);
        frames.add(sf19);
        frames.add(sf20);
        frames.add(sf21);
        frames.add(sf22);
        frames.add(sf23);
        frames.add(sf24);
        frames.add(sf25);
        frames.add(sf26);
        frames.add(sf27);
        frames.add(sf28);
        frames.add(sf29);
        frames.add(sf30);
        Squat squat = new Squat(frames);

       // System.out.println(squat.getM_firstSquatFrame());
       // System.out.println(squat.getM_middleSquatFrame());
       // System.out.println(squat.getM_lastSquatFrame());
    }
}

