import java.util.ArrayList;

public class Squat {
	private static final double INITIAL__FRAME_BACK_ANGLE = 170;
	private static final double LAST__FRAME_BACK_ANGLE = 170;
	private ArrayList<SquatFrame> squat = new ArrayList<SquatFrame>();
	private SquatFrame first = null;
	private SquatFrame last = null;
	private SquatFrame middle = null;

	public Squat(ArrayList<SquatFrame> squat) {
		this.squat = squat;
		findFirstFrame();
		findLastFrame();
		findMiddleFrame();
		
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
			} else {
				squat.remove(frame);
			}
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
			if (squat.get(squat.indexOf(frame) + 1) != null
					&& (frame.getKneeBendAngle() > squat.get(
							squat.indexOf(frame) + 1).getKneeBendAngle())) {
				middle=frame;

			}
		}
	}

}
