
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;



public class TrainingDataService {
	private static final int NUM_OF_ATTRIBUTES = 34;
	ArrayList<Squat> m_squatList;

	public TrainingDataService(ArrayList<Squat> i_squatList) {
		m_squatList = i_squatList;
	}
	
	public Instances getTrainingData()
	{
		FastVector squatAttributes = getAttributesVector();
		Instances trainingData = new Instances("SquatDataSet",squatAttributes ,100 );
		System.out.println(trainingData);
		
		for(Squat s : m_squatList)
		{
			double[] values = getSquatValues(s);
			trainingData.add(new Instance(1, values));
		}
		return null;
	}

	private double[] getSquatValues(Squat i_squatToParse) {
		double[] values = new double[NUM_OF_ATTRIBUTES];
		values[0] = i_squatToParse.getTotalBendTime() + i_squatToParse.getTotalStrechTime();
		
		return values;
	}

	private FastVector getAttributesVector() {
		FastVector fastVector = new FastVector(NUM_OF_ATTRIBUTES);
		fastVector.addElement(new Attribute("Total Time"));
		fastVector.addElement(new Attribute("Total Bend Time"));
		fastVector.addElement(new Attribute("Total Strech Time"));
		for(int i = 0; i < 15; i++)
		{
			fastVector.addElement(new Attribute("Knee-bend degrees " + i));
			fastVector.addElement(new Attribute("Back-bend degrees " + i));
		}
		FastVector nominalClassValues = new FastVector();
		nominalClassValues.addElement("Good");
		nominalClassValues.addElement("Bad");
		fastVector.addElement(new Attribute("Class", nominalClassValues));
		
	
		return fastVector;

		
	}

}
