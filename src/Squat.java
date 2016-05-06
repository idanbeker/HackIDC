import java.util.ArrayList;
import java.util.Arrays;

public class Squat1 {
	public static final double INITIAL__FRAME_BACK_ANGLE = 170;
	public static final double LAST__FRAME_BACK_ANGLE = 170;
	// public static final int FRAMES_IN_SECOND = 24;
	public static final int FRAMES_IN_SQUAT = 30;
	public static final int INDEX_OF_MIDDLE = 13;

	private ArrayList<SquatFrame> m_initialSquat;
	private ArrayList<SquatFrame> m_squatGestures;
	private SquatFrame m_firstSquatFrame;
	private SquatFrame m_lastSquatFrame;
	private SquatFrame m_middleSquatFrame;



	// private double[] stamps;
	// public structForSort[] struct;
	// public structForSort[] shortenStruct;
	// public static boolean sortBy = true;
	// true -sort by delta
	// false- sort by index

	public SquatFrame getM_firstSquatFrame() {
		return m_firstSquatFrame;
	}

	public void setM_firstSquatFrame(SquatFrame m_firstSquatFrame) {
		this.m_firstSquatFrame = m_firstSquatFrame;
	}


	// TODO: make sure the size is bigger than FRAMES_IN_SQUAT
	public Squat(ArrayList<SquatFrame> squat) {
		this.m_initialSquat = squat;
		m_squatGestures = new ArrayList<SquatFrame>();
		findFirstFrame(); // NOTICE: start working with squat after deleting
							// fisrt elments!()!
		//stamps = new double[squat.size()];
		//shortenStruct = new structForSort[FRAMES_IN_SQUAT];
		//struct = new structForSort[squat.size() - 1]; // since we compare 2
														// elements, we have												// (n-1) deltas
		deleteToFirst();
		findLastFrame();// NOTICE: these funcs should run after findFirstFrame()
						// deletes all the initial irrelevant frames
		findMiddleFrame();
		// totalBendTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(middle);
		// totalStrechTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(last);
	}
	public SquatFrame getM_lastSquatFrame() {
		return m_lastSquatFrame;

	}

	public void setM_lastSquatFrame(SquatFrame m_lastSquatFrame) {
		this.m_lastSquatFrame = m_lastSquatFrame;
	}

	public SquatFrame getM_middleSquatFrame() {
		return m_middleSquatFrame;
	}

	public void setM_middleSquatFrame(SquatFrame m_middleSquatFrame) {
		this.m_middleSquatFrame = m_middleSquatFrame;
	}

	public Squat(ArrayList<SquatFrame> i_squatFramesList) {
		this.m_initialSquat = i_squatFramesList;
		m_squatGestures = new ArrayList<SquatFrame>();
		findFirstFrame(); // NOTICE: start working with squat after deleting
							// fisrt elments!!

		deleteToFirst();
		findLastFrame();// NOTICE: these funcs should run after findFirstFrame()
						// deletes all the initial irrelevant frames
		findMiddleFrame();
		// totalBendTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(middle);
		// totalStrechTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(last);
	}

	private boolean isGoodSquat;

	public boolean isGoodSquat() {
		return isGoodSquat;
	}

	public void setGoodSquat(boolean isGoodSquat) {
		this.isGoodSquat = isGoodSquat;
	}

	public void findFirstFrame() {
		m_firstSquatFrame = m_initialSquat.get(0);
		for (SquatFrame frame : m_initialSquat) {
			if (frame.getBackAngle() < INITIAL__FRAME_BACK_ANGLE) {
				this.m_firstSquatFrame = frame;
				return;
			}
		}
	}

	public void deleteToFirst() {
		int indexOfFirst = m_initialSquat.indexOf(m_firstSquatFrame);
		for (int i = 0; i < indexOfFirst; i++) {
			m_initialSquat.remove(0);
		}
	}

	public void findLastFrame() {
		m_lastSquatFrame = m_initialSquat.get(m_initialSquat.size() - 1);
		for (SquatFrame frame : m_initialSquat) {
			if (frame.getBackAngle() > LAST__FRAME_BACK_ANGLE) {
				this.m_lastSquatFrame = frame;
				break;
			}
		}
	}

	public void findMiddleFrame() {
		// assume the angle is decreasing until the middle frame.
		// find the first frame from which the angle doesnt decrease
		int middleIndex = ((m_initialSquat.indexOf(m_lastSquatFrame) - m_initialSquat
				.indexOf(m_firstSquatFrame)) / 2)
				+ m_initialSquat.indexOf(m_firstSquatFrame);
		m_middleSquatFrame = m_initialSquat.get(middleIndex);
		for (SquatFrame frame : m_initialSquat) {
			if (m_initialSquat.indexOf(frame) == m_initialSquat.size() - 1)
			{
				return;
			}
			else if (frame.getKneeBendAngle() > m_initialSquat.get(
					m_initialSquat.indexOf(frame) + 1).getKneeBendAngle()) {
				m_middleSquatFrame = frame;
				return;
			}
		}
	}

	public double getTotalBendTime() {
		return m_initialSquat.indexOf(m_middleSquatFrame)
				- m_initialSquat.indexOf(m_firstSquatFrame);
	}

	public double getTotalStrechTime() {
		return m_initialSquat.indexOf(m_lastSquatFrame)
				- m_initialSquat.indexOf(m_middleSquatFrame);
	}

	public ArrayList<SquatFrame> getSquatInfo() {
		for (int i = 0; i < FRAMES_IN_SQUAT; i++) {
			m_squatGestures.add(m_initialSquat.get(i));
		}
		return m_squatGestures;
	}
}
