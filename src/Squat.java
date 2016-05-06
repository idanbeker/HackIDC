import java.util.ArrayList;
import java.util.Arrays;

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

	
	public Squat(ArrayList<SquatFrame> squat) {
		this.squat = squat;
		shortenSquat=new ArrayList<SquatFrame>();
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
		for (SquatFrame frame : squat) {
			if (frame.getBackAngle() < INITIAL__FRAME_BACK_ANGLE) {
				this.first = frame;
				break;
			}
		}
	}

	public void deleteToFirst() {
		int indexOfFirst = squat.indexOf(first);
		for (int i = 0; i < indexOfFirst; i++) {

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
	
	
	public double getTotalBendTime() {
		return squat.indexOf(middle) - squat.indexOf(first);
	}

	public double getTotalStrechTime() {
		return squat.indexOf(last) - squat.indexOf(middle);
	}
	
	public ArrayList<SquatFrame> getSquatInfo(){
		for (int i=0; i< FRAMES_IN_SQUAT;i++){
			shortenSquat.add(squat.get(i));
		}
		return shortenSquat;
	}
}
