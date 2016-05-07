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
		//System.out.println(m_trainingData);
		m_classifier = new KStar();
		m_classifier.buildClassifier(m_trainingData);
	}
	
	public double predict(Instance i_instance) throws Exception{
		
		//m_trainingData.add(i_instance);
		//i_instance.setDataset(m_trainingData);
		return m_classifier.classifyInstance(i_instance);
	}
	
	

}
