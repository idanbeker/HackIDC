import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Squat {

	public static final double INITIAL__FRAME_BACK_ANGLE = 170;
	public static final double LAST__FRAME_BACK_ANGLE = 170;
	// public static final int FRAMES_IN_SECOND = 24;
	public static final int FRAMES_IN_SQUAT = 30;
	public static final int INDEX_OF_MIDDLE = 13;

	public ArrayList<SquatFrame> squat = new ArrayList<SquatFrame>();
	public ArrayList<SquatFrame> shortenSquat = null;
	public SquatFrame first = null;
	public SquatFrame last = null;
	public SquatFrame middle = null;

	private double totalBendTime;
	private double totalStrechTime;
	private double[] stamps;
	public structForSort[] struct;
	public structForSort[] shortenStruct;

	public static boolean sortBy = true;

	// true -sort by delta
	// false- sort by index

	public double getTotalBendTime() {
		return squat.indexOf(middle) - squat.indexOf(first);
	}

	public double getTotalStrechTime() {
		return squat.indexOf(last) - squat.indexOf(middle);
	}

	// TODO: make sure the size is bigger than FRAMES_IN_SQUAT
	public Squat(ArrayList<SquatFrame> squat) {
		this.squat = squat;
		findFirstFrame(); // NOTICE: start working with squat after deleting
							// fisrt elments!!
		stamps = new double[squat.size()];
		shortenStruct = new structForSort[FRAMES_IN_SQUAT];
		struct = new structForSort[squat.size() - 1]; // since we compare 2
														// elements, we have
														// (n-1) deltas
		deleteToFirst();
		findLastFrame();// NOTICE: these funcs should run after findFirstFrame()
						// deletes all the initial irrelevant frames
		findMiddleFrame();
		// totalBendTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(middle);
		// totalStrechTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(last);
	}

	public void initialStructArray() {// check null
		for (int i = 0; i < struct.length ; i++) {
			struct[i] = new structForSort(i + 1, deltaOfFrames(squat.get(i),
					squat.get(i + 1)), squat.get(i + 1));
		}
	}

	public void sortStructByDeltas(structForSort[] str) {
		sortBy = true;
		Arrays.sort(str);
	}

	public void sortStructByIndex(structForSort[] str) {
		sortBy = false;
		Arrays.sort(str);
	}

	public void fillStamps() {
		for (int i = 0; i < squat.size(); i++) {
			stamps[i] = deltaOfFrames(squat.get(i), squat.get(i + 1));
		}
	}

	public double deltaOfFrames(SquatFrame a, SquatFrame b) {
		return (1 * distance(a.m_ass, b.m_ass))
				+ (1 * distance(a.m_feet, b.m_feet))
				+ (1 * distance(a.m_head, b.m_head))
				+ (1 * distance(a.m_knee, b.m_knee))
				+ (1 * distance(a.m_shoulder, b.m_shoulder));

	}

	public double distance(Point a, Point b) {
		return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
	}
	private boolean isGoodSquat;
	
	public boolean isGoodSquat() {
		return isGoodSquat;
	}

	public void setGoodSquat(boolean isGoodSquat) {
		this.isGoodSquat = isGoodSquat;
	}

	

	


	public ArrayList<SquatFrame> getSquatInfo() { // idan
		int indexOfShorten = 0;
		sortStructByDeltas(struct);
		// now take only the FRAMES_IN_SQUAT biggest deltas
		for (int i = struct.length - 1; i > (struct.length - 1)
				- FRAMES_IN_SQUAT; i--) {
			shortenStruct[indexOfShorten] = struct[i];
			indexOfShorten++;
		}

		sortStructByIndex(shortenStruct);
		for (int i = 0; i < shortenStruct.length; i++) {
			shortenSquat.add(shortenStruct[i].frame);
		}

		return shortenSquat;
	}

	public void filterFirstHalf() {

	}

	public void filterSecondHalf() {

	}

	/*
	 * we look for the first instance of frame in which the angel of the back is
	 * less than INITIAL__FRAME_BACK_ANGLE. we remove all the previous frames we
	 * then look for the first instance of the frame in which the angel is
	 * higher than INITIAL__FRAME_BACK_ANGLE - means that the player stood up!
	 */
	public void findFirstFrame() {
		for (SquatFrame frame : squat) {
			if (frame.getBackAngle() < INITIAL__FRAME_BACK_ANGLE) {
				this.first = frame;
				break;
			}
		}
	}


	
	
	public void deleteToFirst(){
		int indexOfFirst=squat.indexOf(first);
		for (int i=0;i<indexOfFirst;i++){

			squat.remove(0);
		}
	}

	public void findLastFrame() {
		for (SquatFrame frame : squat) {
			if (frame.getBackAngle() > LAST__FRAME_BACK_ANGLE) {
				this.last = frame;
				break;
			}
		}
	}

	public void findMiddleFrame() {
		// assume the angle is decreasing until the middle frame.
		// find the first frame from which the angle doesnt decrease
		for (SquatFrame frame : squat) {
			if (squat.indexOf(frame) == squat.size() - 1)
				break;
			else if (frame.getKneeBendAngle() > squat.get(
					squat.indexOf(frame) + 1).getKneeBendAngle()) {
				middle = frame;
				break;
			}
		}
	}
}
