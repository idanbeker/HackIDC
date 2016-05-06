import java.util.ArrayList;

import weka.classifiers.mi.CitationKNN;
import weka.core.Instance;
import weka.core.Instances;

public class SquatPredictor {
	private static final int NUM_OF_ATTRIBUTES = 34;
	ArrayList<Squat> m_squatList;
	CitationKNN m_classifier;
	
	public SquatPredictor(ArrayList<Squat> i_trainingDataList) throws Exception {
		this.m_squatList = i_trainingDataList;
		TrainingDataService trainingDataService = new TrainingDataService(i_trainingDataList);
		Instances trainingData = trainingDataService.getTrainingData();
		m_classifier = new CitationKNN();
		m_classifier.buildClassifier(trainingData);
	}
	
	public boolean predict(Squat i_squat) throws Exception{
		return m_classifier.classifyInstance(squatToInstance(i_squat)) == 1;
	}
	
	private Instance squatToInstance(Squat i_squat)
	{
		return new Instance(1 , getSquatValues(i_squat));
	}
	private double[] getSquatValues(Squat i_squatToParse) {
		double[] values = new double[NUM_OF_ATTRIBUTES];
		values[0] = i_squatToParse.getTotalBendTime() + i_squatToParse.getTotalStrechTime();
		values[1] = i_squatToParse.getTotalBendTime();
		values[2] = i_squatToParse.getTotalStrechTime();
		
		ArrayList<SquatFrame> squatFrameList = i_squatToParse.getSquatInfo();
		for(int i = 0; i < 30; i += 2)
		{
			values[i] = squatFrameList.get(i).getKneeBendAngle();
			values[i + 1] = squatFrameList.get(i).getBackAngle();
		}
		values[NUM_OF_ATTRIBUTES - 1] = i_squatToParse.isGoodSquat()? 1.0: 0.0;
		
		return values;
	}

}
