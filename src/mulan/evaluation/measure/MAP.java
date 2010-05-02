package mulan.evaluation.measure;

import java.util.Collections;

import weka.core.Utils;

/**
 * Implementation of MAP (Mean Average Precision)
 * 
 * @author Eleftherios Spyromitros Xioufis
 *
 */
public class MAP extends MAPMeasureBase {

	public MAP(int numOfLabels) {
		super(numOfLabels);
	}

	public double getValue() {
		double[] AveragePrecision = new double[numOfLabels];
		for (int labelIndex = 0; labelIndex < numOfLabels; labelIndex++) {
			AveragePrecision[labelIndex] = 0;
			Collections.sort(confact[labelIndex], Collections.reverseOrder());
			double retrievedCounter = 0;
			double relevantCounter = 0;

			for (int i = 0; i < confact[labelIndex].size(); i++) {
				retrievedCounter++;
				Boolean actual = confact[labelIndex].get(i).getActual();
				if (actual) {
					relevantCounter++;
					AveragePrecision[labelIndex] += relevantCounter
							/ retrievedCounter;
				}
			}
			AveragePrecision[labelIndex] /= relevantCounter;
		}
		return Utils.mean(AveragePrecision);
	}

	public String getName() {
		return "Mean Average Precision";
	}

	public double getIdealValue() {
		return 1;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
