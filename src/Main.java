import java.io.FileNotFoundException;
import java.util.ArrayList;

import weka.core.Instances;

public class Main {
	private static final int NUM_OF_TRAINING_INSTANCES = 1;

	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<Squat> trainingSquats = getTrainingSquats();
		TrainingDataService tds = new TrainingDataService(trainingSquats);
		Instances data = tds.getTrainingData();
		System.out.println(data);
		for(Squat s: trainingSquats)
		{
			System.out.println(s);
		}



		
	}

	private static ArrayList<Squat> getTrainingSquats() throws FileNotFoundException {
		ArrayList<Squat> trainingSquats = new ArrayList<Squat>();
		for (int j = 1; j <= NUM_OF_TRAINING_INSTANCES; j++) {
			InputParser ip = new InputParser("input/input" + j + ".txt");
			ArrayList<SquatFrame> squatFrames = new ArrayList<SquatFrame>();
			ArrayList<Point> points = ip.points;
			for (int i = 0; i < points.size(); i += 4) {
				SquatFrame sf = new SquatFrame(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
				squatFrames.add(sf);
			}
			trainingSquats.add(new Squat(squatFrames));
		}
		return trainingSquats;
	}
}
