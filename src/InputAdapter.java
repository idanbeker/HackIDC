import java.util.ArrayList;

public class InputAdapter {
    public Point shoulder;
    public Point ass;
    public Point knee;
    public Point feet;

    public InputAdapter(ArrayList<Point> points) {
        /*
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.getY(), o2.getY());
            }
        });
        */

        shoulder = points.get(0);
        ass = points.get(1);
        knee = points.get(2);
        /*if (points.get(1).getX() > points.get(2).getX()) {
            ass = points.get(1);
            knee = points.get(2);
        } else {
            ass = points.get(2);
            knee = points.get(1);
        }
        */
        feet = points.get(3);
    }
}
