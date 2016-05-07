import java.util.ArrayList;

public class Squat {
	public static final double INITIAL__FRAME_BACK_ANGLE = 170;
	public static final double LAST__FRAME_BACK_ANGLE = 180;
	// public static final int FRAMES_IN_SECOND = 24;
	public static final int FRAMES_IN_SQUAT = 30;
	public static final int INDEX_OF_MIDDLE = 13;

	private ArrayList<SquatFrame> m_initialSquat;
	private ArrayList<SquatFrame> m_squatGestures;
	private SquatFrame m_firstSquatFrame;
    private int m_firstSquatIndex;
	private SquatFrame m_lastSquatFrame;
    private int m_lastSquatIndex;
	private SquatFrame m_middleSquatFrame;
    private int m_middleSquatIndex;

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

    public int getFirstSquatIndex() { return m_firstSquatIndex; }
    public int getMiddleSquatIndex() { return m_middleSquatIndex; }
    public int getLastSquatIndex() { return m_lastSquatIndex; }

	public Squat(ArrayList<SquatFrame> i_squatFramesList) {
		this.m_initialSquat = i_squatFramesList;
		m_squatGestures = new ArrayList<SquatFrame>();
		findFirstFrame(); // NOTICE: start working with squat after deleting
							// first elments!!

		//deleteToFirst();
		findLastFrame();// NOTICE: these funcs should run after findFirstFrame()
						// deletes all the initial irrelevant frames
		findMiddleFrame();
        this.m_firstSquatIndex = m_initialSquat.indexOf(m_firstSquatFrame);
        this.m_middleSquatIndex = m_initialSquat.indexOf(m_middleSquatFrame);
        this.m_lastSquatIndex = m_initialSquat.indexOf(m_lastSquatFrame);
		// totalBendTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(middle);
		// totalStrechTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(last);

        int c = 0;
        for (SquatFrame frame : m_initialSquat){
            c++;
            System.out.println(c + ". " + frame.getBackAngle() + " " + frame.getKneeBendAngle());
        }
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
        int sequenceLength = 6;
        for (SquatFrame frame : m_initialSquat) {
            if (m_initialSquat.indexOf(frame) > m_initialSquat.size() - sequenceLength - 1) {
                return;
            }
            boolean foundError = false;
            for (int i = 0; i < sequenceLength; i++) {
                if (foundError) {
                    continue;
                }
                if (m_initialSquat.get(m_initialSquat.indexOf(frame) + i).getBackAngle() > m_initialSquat.get(
                        m_initialSquat.indexOf(frame) + i + 1).getBackAngle()) {
                    foundError = true;
                }
            }
            if (!foundError) {
                m_lastSquatFrame = m_initialSquat.get(m_initialSquat.indexOf(frame) + sequenceLength + 1);
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
        int sequenceLength = 3;
		for (SquatFrame frame : m_initialSquat) {
            if (m_initialSquat.indexOf(frame) > m_initialSquat.size() - sequenceLength - 1) {
                return;
            }
            boolean foundError = false;
            for (int i = 0; i < sequenceLength; i++) {
                if (foundError) {
                    continue;
                }
                if (m_initialSquat.get(m_initialSquat.indexOf(frame) + i).getBackAngle() > m_initialSquat.get(
                        m_initialSquat.indexOf(frame) + i + 1).getBackAngle()) {
                    foundError = true;
                }
            }
            if (!foundError) {
                m_middleSquatFrame = frame;
                break;
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
		for (int i = 0; i < Math.min(FRAMES_IN_SQUAT,m_initialSquat.size()); i++) {
			m_squatGestures.add(m_initialSquat.get(i));
		}
		return m_squatGestures;
	}
}
