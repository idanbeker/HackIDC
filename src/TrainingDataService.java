
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;



public class TrainingDataService {
	private static final int NUM_OF_ATTRIBUTES = 34;
	private static final int NUM_OF_SAMPLES = 30;
	ArrayList<Squat> m_squatList;

	public TrainingDataService(ArrayList<Squat> i_squatList) {
		m_squatList = i_squatList;
	}
	
	public Instances getTrainingData()
	{
		FastVector squatAttributes = getAttributesVector();
		Instances trainingData = new Instances("SquatDataSet",squatAttributes ,100 );
		//sSystem.out.println(trainingData);
		
		for(Squat s : m_squatList)
		{
			double[] values = getSquatValues(s);
			trainingData.add(new Instance(1, values));
		}
		trainingData.setClassIndex(trainingData.numAttributes() - 1);
		return trainingData;
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
		values[NUM_OF_ATTRIBUTES - 1] = i_squatToParse.isGoodSquat()? 0.0: 1.0;
		
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
