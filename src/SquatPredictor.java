import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.lazy.KStar;
import weka.classifiers.mi.CitationKNN;
import weka.core.Instance;
import weka.core.Instances;

public class SquatPredictor {
	private static final int NUM_OF_ATTRIBUTES = 34;
	ArrayList<Squat> m_squatList;
	Instances m_trainingData;
	KStar m_classifier;
	
	public SquatPredictor(ArrayList<Squat> i_trainingDataList) throws Exception {
		this.m_squatList = i_trainingDataList;
		TrainingDataService trainingDataService = new TrainingDataService(i_trainingDataList);
		m_trainingData = trainingDataService.getTrainingData();
		System.out.println(m_trainingData);
		m_classifier = new KStar();
		m_classifier.buildClassifier(m_trainingData);
	}
	
	public boolean predict(Squat i_squat) throws Exception{
		Instance instance = squatToInstance(i_squat);
		m_trainingData.add(instance);
		instance.setDataset(m_trainingData);
		System.out.println("Test" + instance);
		return m_classifier.classifyInstance(instance) == 1;
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
		for(int i = 0; i < Math.min(30,squatFrameList.size()); i += 2)
		{
			values[i + 3] = squatFrameList.get(i).getKneeBendAngle();
			values[i + 4] = squatFrameList.get(i).getBackAngle();
		}
		values[NUM_OF_ATTRIBUTES - 1] = i_squatToParse.isGoodSquat()? 1.0: 0.0;
		
		return values;
	}

}
