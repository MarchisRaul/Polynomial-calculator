package MVCmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Util2 {

	public static int checkDifferentFromNull(Polinom secondP) {
		int nrOfPolynomials = 0;

		Iterator<Monom> myIt = secondP.getPolynomial().iterator();
		while (myIt.hasNext()) {
			Monom temp = myIt.next();
			if (temp.getCoeficient() != 0)
				nrOfPolynomials++;
		}
		if (nrOfPolynomials != 0)
			return 1;
		return 0;
	}

	public static void copyPolynomials(Polinom source, Polinom destination) {
		Iterator<Monom> myIt = source.getPolynomial().iterator();
		while (myIt.hasNext()) {
			Monom temp = myIt.next();
			destination.getPolynomial().add(temp);
		}
		Util.sortDescendingOrderByPower(destination);
	}

	public static int checkDegree(Polinom polynomial) {
		int degree = 0;
		Iterator<Monom> myIt = polynomial.getPolynomial().iterator();
		while (myIt.hasNext()) {
			Monom temp = myIt.next();
			if (temp.getPutere() > degree && temp.getCoeficient() != 0)
				degree = temp.getPutere();
		}

		return degree;
	}

	public static Monom getLead(Polinom polynomial) {
		Monom monomTemp;
		Monom resultReturned = new Monom(0.0f, 0);
		int power = -1;
		Iterator<Monom> myIt = polynomial.getPolynomial().iterator();
		while (myIt.hasNext()) {
			monomTemp = myIt.next();
			if (monomTemp.getPutere() > power && monomTemp.getCoeficient() != 0) {
				resultReturned = monomTemp;
				power = monomTemp.getPutere();
			}
		}

		return resultReturned;
	}

	public static void doDivisionAlgorithm(Polinom firstP, Polinom secondP, Polinom[] result) {
		Monom t;
		if (checkDifferentFromNull(secondP) == 1) {
			Polinom Q = new Polinom();
			Polinom R = new Polinom();
			copyPolynomials(firstP, R);

			while (checkDifferentFromNull(R) == 1 && checkDegree(R) >= checkDegree(secondP)) {
				Monom biggestPowerR = getLead(R);
				Monom biggestPowerSP = getLead(secondP);
				float coef = biggestPowerR.getCoeficient() / biggestPowerSP.getCoeficient();
				int power = biggestPowerR.getPutere() - biggestPowerSP.getPutere();
				t = new Monom(coef, power);
				Polinom polinomForHelp = new Polinom();
				polinomForHelp.getPolynomial().add(t);

				Util.addMonomToExistingPolynomial(t, Q);
				Util.substractFromExistingPolynomial(Util.multiplyingTwoPolynomials(polinomForHelp, secondP), R);
			}
			result[0] = Q;
			result[1] = R;
		}
	}
}
