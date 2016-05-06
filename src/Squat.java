import java.util.ArrayList;

public class Squat {
	public static final float INITIAL__FRAME_BACK_ANGLE = 170;
	public static final float LAST__FRAME_BACK_ANGLE = 170;
	public static final int FRAMES_IN_SECOND = 24;
	public ArrayList<SquatFrame> squat = new ArrayList<SquatFrame>();
	public SquatFrame first = null;
	public SquatFrame last = null;
	public SquatFrame middle = null;
	private float totalBendTime;
	private float totalStrechTime;

	
	public float getTotalBendTime() {
		return totalBendTime;
	}


	public float getTotalStrechTime() {
		return totalStrechTime;
	}



	public Squat(ArrayList<SquatFrame> squat) {
		this.squat = squat;
		findFirstFrame();
		deleteToFisrt();
		findLastFrame();// NOTICE: these funcs should run after findFirstFrame()
						// deletes all the initial irrelevant frames
		findMiddleFrame();
		totalBendTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(middle);
		totalStrechTime = (1 / FRAMES_IN_SECOND) * this.squat.indexOf(last);
	}

	public ArrayList<Squat> getSquatInfo()
	{
		
		//TODO Ilan: make this a 30-Squat long array list of instances
		//Starting from begining of strech->end of strech
		return null;
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
	
	public void deleteToFisrt(){
		int indexOfFirst=squat.indexOf(first);
		for (int i=0;i<indexOfFirst;i++){
			squat.remove(0);
		}
	}

	public void findLastFrame() {
		for (SquatFrame frame : squat) {
			if (frame.getBackAngle() > INITIAL__FRAME_BACK_ANGLE) {
				this.last = frame;
				break;
			}
		}

	}

	public void findMiddleFrame() {
		// assume the angle is decreasing until the middle frame.
		// find the first frame from which the angle doesnt decrease
		for (SquatFrame frame : squat) {
			if (squat.get(squat.indexOf(frame) + 1) == null)
				break;
			else if (frame.getKneeBendAngle() > squat.get(
					squat.indexOf(frame) + 1).getKneeBendAngle()) {
				middle = frame;
				break;

			}
		}
	}

}
