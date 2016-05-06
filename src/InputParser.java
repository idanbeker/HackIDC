import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    ArrayList<Point> points = new ArrayList<Point>();

    public InputParser(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new File(path));
        scanner.useDelimiter(",");

        while (scanner.hasNextDouble()) {
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            points.add(new Point(a, b));
        }
    }
}