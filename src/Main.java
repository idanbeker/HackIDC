import java.io.FileNotFoundException;
import java.util.ArrayList;

import weka.core.Instances;

public class Main {
	private static final int NUM_OF_TRAINING_INSTANCES = 7;
	private static final int NUM_OF_DATA_INSTANCES = 3;
	private static final boolean isGoodSquat[] = {true ,true,false,false,true,false,false,false,true};

	public static void main(String[] args) throws Exception {

		ArrayList<Squat> trainingSquats = getTrainingSquats("input/input",NUM_OF_TRAINING_INSTANCES);
		ArrayList<Squat> dataSquats = getTrainingSquats("input/data" , NUM_OF_DATA_INSTANCES);
		TrainingDataService tds = new TrainingDataService(dataSquats);
		Instances data = tds.getTrainingData();
		SquatPredictor sp = new SquatPredictor(trainingSquats);
		//System.out.println(trainingData);
		//System.out.println(data);
		
		for(int i = 0; i < data.numInstances();i++)
		{
			System.out.println(data.instance(i));
			System.out.println("Real value = " + data.instance(i).classValue());
			System.out.println("Predicted value = " + sp.predict(data.instance(i)));
		}



		
	}

	private static ArrayList<Squat> getTrainingSquats(String i_fileName, int i_numOfInstances) throws FileNotFoundException {
		ArrayList<Squat> trainingSquats = new ArrayList<Squat>();
		for (int j = 1; j <= i_numOfInstances; j++) {
			InputParser ip = new InputParser(i_fileName + j + ".txt");
			ArrayList<SquatFrame> squatFrames = new ArrayList<SquatFrame>();
			ArrayList<Point> points = ip.points;
			for (int i = 0; i < points.size(); i += 4) {
				SquatFrame sf = new SquatFrame(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
				squatFrames.add(sf);
			}
			Squat squatToAdd = new Squat(squatFrames);
			squatToAdd.setGoodSquat(isGoodSquat[j]);
			trainingSquats.add(squatToAdd);
		}
		return trainingSquats;
	}
}
