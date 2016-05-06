import java.util.Comparator;

public class structForSort implements Comparator<structForSort> {
	public int index;
	public double delta;
	public SquatFrame frame;

	// public boolean sortBy=true;
	// true -sort by delta
	// false- sort by index

	public structForSort(int index, double delta, SquatFrame frame) {
		this.index = index;
		this.delta = delta;
		this.frame = frame;
	}

	@Override
	public int compare(structForSort a, structForSort b) {//sorts from big to small
		if (Squat.sortBy)
		// sort by delta
		{
			if (a.delta > b.delta) {
				return -1;
			} else if (a.delta < b.delta) {
				return 1;
			}
		} else {// sort by index
			if (a.index > b.index) {
				return -1;
			} else if (a.index > b.index) {
				return 1;
			}
		}

		return 0;
	}

}
