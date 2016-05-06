import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hey");
        InputParser ip = new InputParser("input/input.txt");

        ArrayList<SquatFrame> squatFrames = new ArrayList<SquatFrame>();
        ArrayList<Point> points = ip.points;
        for (int i = 0; i < points.size(); i+=4) {
            SquatFrame sf = new SquatFrame(points.get(i), points.get(i+1), points.get(i+2), points.get(i+3));
            squatFrames.add(sf);
        }

        Squat s = new Squat(squatFrames);


        System.out.println(s.getFirstSquatIndex());
        System.out.print(s.getM_firstSquatFrame());

        System.out.println(s.getMiddleSquatIndex());
        System.out.print(s.getM_middleSquatFrame());

        System.out.println(s.getLastSquatIndex());
        System.out.print(s.getM_lastSquatFrame());
    }
}
