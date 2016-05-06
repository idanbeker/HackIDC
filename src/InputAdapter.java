import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InputAdapter {
    public Point shoulder;
    public Point ass;
    public Point knee;
    public Point feet;

    public InputAdapter(ArrayList<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.y, o2.y);
            }
        });

        // if we have all 4 points, find them
        shoulder = points.get(0);

        if (points.get(1).x > points.get(2).x) {
            ass = points.get(1);
            knee = points.get(2);
        } else {
            ass = points.get(2);
            knee = points.get(1);
        }

        feet = points.get(3);
    }
}
