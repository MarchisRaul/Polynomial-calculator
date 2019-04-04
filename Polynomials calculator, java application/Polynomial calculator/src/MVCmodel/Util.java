package MVCmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class Util {
	public static Polinom convertToPolynomial(String string) throws Exception {
		string = string.replaceAll(" ", "");
		String splitString[] = string.split("(?=[+-])");
		Polinom polinomyal = new Polinom();
		Monom monom;
		for (String s : splitString) {
			int coef, notMonom = 0;
			int power = -1;
			notMonom = s.indexOf('x');
			if (s.length() == 1) {
				coef = power = 1;
			}
			if (s.equals("x"))
				s = "+x";
			String[] splitsString = s.split("[x^]+");
			if (splitsString.length == 1 || splitsString[0].equals("+") || splitsString[0].equals("-")) {
				if (notMonom == -1)
					power = 0;
				else
					power = 1;
			}
			if (splitsString.length == 2)
				power = Integer.parseInt(splitsString[1]);
			if (splitsString[0].equals("") || splitsString[0].equals("+"))
				coef = 1;
			else if (splitsString[0].equals("-"))
				coef = -1;
			else
				coef = Integer.parseInt(splitsString[0]);
			polinomyal.addToPolynomial(new Monom(coef, power));
		}
		return polinomyal;
	}

	public static int sortDescendingOrderByPower(Polinom polynomial) {

		Collections.sort(polynomial.getPolynomial(), new Comparator<Monom>() {

			@Override
			public int compare(Monom arg0, Monom arg1) {
				return arg1.getPutere() - arg0.getPutere();
			}

		});

		return 0;

	}

	public static String doWorkOnStringIntCoef(Monom monom, String finalResult, String tempString) {
		if (monom.getCoeficient() != 0) {
			if (monom.getCoeficient() < 0 && monom.getCoeficient() == -1 && monom.getPutere() >= 1)
				tempString = tempString + "-";
			else if (monom.getCoeficient() < 0 && monom.getCoeficient() <= -1 && monom.getPutere() == 0)
				tempString = tempString + (int) monom.getCoeficient();
			else if (monom.getCoeficient() < -1)
				tempString = tempString + (int) monom.getCoeficient();
			else if (finalResult.equals("") && monom.getCoeficient() > 0 && monom.getPutere() == 0)
				tempString = tempString + (int) monom.getCoeficient();
			else if (finalResult.equals("") && monom.getCoeficient() == 1 && monom.getPutere() > 0)
				tempString = tempString;
			else if (finalResult.equals("") && monom.getCoeficient() > 1 && monom.getPutere() > 0)
				tempString = tempString + (int) monom.getCoeficient();
			else if (monom.getCoeficient() > 0 && monom.getPutere() == 0)
				tempString = tempString + "+" + (int) monom.getCoeficient();
			else if (monom.getCoeficient() > 0 && monom.getCoeficient() == 1 && monom.getPutere() > 0)
				tempString = tempString + "+";
			else if (monom.getCoeficient() > 0 && monom.getCoeficient() > 1 && monom.getPutere() > 0)
				tempString = tempString + "+" + (int) monom.getCoeficient();
			if (monom.getPutere() != 0 && monom.getPutere() > 0)
				tempString = tempString + "x";
			if (monom.getPutere() != 0 && monom.getPutere() > 1)
				tempString = tempString + "^" + monom.getPutere();
		}
		return tempString;
	}

	public static String doWorkOnStringFloatCoef(Monom monom, String finalResult, String tempString) {
		if (monom.getCoeficient() != 0) {
			if (monom.getCoeficient() < 0 && monom.getCoeficient() == -1 && monom.getPutere() >= 1)
				tempString = tempString + "-";
			else if (monom.getCoeficient() < 0 && monom.getCoeficient() <= -1 && monom.getPutere() == 0)
				tempString = tempString + String.format("%.02f", monom.getCoeficient());
			else if (monom.getCoeficient() < -1)
				tempString = tempString + String.format("%.02f", monom.getCoeficient());
			else if (finalResult.equals("") && monom.getCoeficient() > 0 && monom.getPutere() == 0)
				tempString = tempString + String.format("%.02f", monom.getCoeficient());
			else if (finalResult.equals("") && monom.getCoeficient() == 1 && monom.getPutere() > 0)
				tempString = tempString;
			else if (finalResult.equals("") && monom.getCoeficient() > 1 && monom.getPutere() > 0)
				tempString = tempString + String.format("%.02f", monom.getCoeficient());
			else if (monom.getCoeficient() > 0 && monom.getPutere() == 0)
				tempString = tempString + "+" + String.format("%.02f", monom.getCoeficient());
			else if (monom.getCoeficient() > 0 && monom.getCoeficient() == 1 && monom.getPutere() > 0)
				tempString = tempString + "+";
			else if (monom.getCoeficient() > 0 && monom.getCoeficient() > 1 && monom.getPutere() > 0)
				tempString = tempString + "+" + String.format("%.02f", monom.getCoeficient());
			else if (monom.getCoeficient() < 0)
				tempString = tempString + String.format("%.02f", monom.getCoeficient());
			else if (monom.getCoeficient() > 0)
				tempString = tempString + "+" + String.format("%.02f", monom.getCoeficient());
			if (monom.getPutere() != 0 && monom.getPutere() > 0)
				tempString = tempString + "x";
			if (monom.getPutere() != 0 && monom.getPutere() > 1)
				tempString = tempString + "^" + monom.getPutere();
		}
		return tempString;
	}

	public static void addToExistingPolynomial(Polinom polynomial1, Polinom resultP) {
		ListIterator<Monom> iteratorFirstP = polynomial1.getPolynomial().listIterator();
		ListIterator<Monom> iteratorResultP;
		Monom monomForResult;
		float coefTemp = 0.0f;

		etic1: while (iteratorFirstP.hasNext()) {
			Monom monomFirstP = iteratorFirstP.next();
			coefTemp = 0;
			iteratorResultP = resultP.getPolynomial().listIterator();
			if (iteratorResultP.hasNext()) {
				while (iteratorResultP.hasNext()) {
					Monom monomResultP = iteratorResultP.next();
					if (monomFirstP.getPutere() == monomResultP.getPutere()) {
						coefTemp = monomResultP.getCoeficient() + monomFirstP.getCoeficient();
						Monom monomTemp = new Monom(coefTemp, monomResultP.getPutere());
						iteratorResultP.set(monomTemp);
						continue etic1;
					}

				}
				iteratorResultP.add(monomFirstP);
			} else
				iteratorResultP.add(monomFirstP);
		}
	}

	public static void substractFromExistingPolynomial(Polinom polynomial1, Polinom resultP) {
		ListIterator<Monom> iteratorFirstP = polynomial1.getPolynomial().listIterator();
		ListIterator<Monom> iteratorResultP;
		Monom monomForResult;
		float coefTemp = 0.0f;

		etic1: while (iteratorFirstP.hasNext()) {
			Monom monomFirstP = iteratorFirstP.next();
			coefTemp = 0.0f;
			iteratorResultP = resultP.getPolynomial().listIterator();
			if (iteratorResultP.hasNext()) {
				while (iteratorResultP.hasNext()) {
					Monom monomResultP = iteratorResultP.next();
					if (monomFirstP.getPutere() == monomResultP.getPutere()) {
						coefTemp = monomResultP.getCoeficient() - monomFirstP.getCoeficient();
						Monom monomTemp = new Monom(coefTemp, monomResultP.getPutere());
						iteratorResultP.set(monomTemp);
						continue etic1;
					}

				}
				iteratorResultP.add(new Monom((-1) * monomFirstP.getCoeficient(), monomFirstP.getPutere()));
			} else {
				iteratorResultP.add(new Monom((-1) * monomFirstP.getCoeficient(), monomFirstP.getPutere()));
			}

		}
	}

	public static void addMonomToExistingPolynomial(Monom monomForResult, Polinom resultP) {
		ListIterator<Monom> iteratorResultP;
		float coefTemp = 0.0f;
		int ok = 0;

		iteratorResultP = resultP.getPolynomial().listIterator();
		if (iteratorResultP.hasNext()) {
			while (iteratorResultP.hasNext()) {
				coefTemp = 0.0f;
				Monom monomResultP = iteratorResultP.next();
				if (monomForResult.getPutere() == monomResultP.getPutere()) {
					coefTemp = monomResultP.getCoeficient() + monomForResult.getCoeficient();
					Monom monomTemp = new Monom(coefTemp, monomResultP.getPutere());
					iteratorResultP.set(monomTemp);
					ok = 1;
					break;
				}
			}
			if (ok == 0)
				iteratorResultP.add(monomForResult);
		} else
			iteratorResultP.add(monomForResult);
	}

	public static Polinom multiplyingTwoPolynomials(Polinom polynomial1, Polinom polynomial2) {
		Polinom result = new Polinom();

		ListIterator<Monom> iteratorFirstP = polynomial1.getPolynomial().listIterator();
		ListIterator<Monom> iteratorSecondP;
		Monom monomForResult;
		int powerTemp = 0;
		float coefTemp = 0.0f;
		while (iteratorFirstP.hasNext()) {
			powerTemp = 0;
			coefTemp = 0.0f;
			Monom monomFirstP = iteratorFirstP.next();
			iteratorSecondP = polynomial2.getPolynomial().listIterator();
			while (iteratorSecondP.hasNext()) {
				Monom monomSecondP = iteratorSecondP.next();
				coefTemp = monomFirstP.getCoeficient() * monomSecondP.getCoeficient();
				powerTemp = monomFirstP.getPutere() + monomSecondP.getPutere();
				monomForResult = new Monom(coefTemp, powerTemp);
				Util.addMonomToExistingPolynomial(monomForResult, result);
			}
		}
		return result;
	}

	public static Polinom calculateDerivatives(Polinom polynomial) {
		Polinom result = new Polinom();

		ListIterator<Monom> iteratorP = polynomial.getPolynomial().listIterator();
		Monom monomForResult;
		int powerTemp = 0;
		float coefTemp = 0.0f;
		etic: while (iteratorP.hasNext()) {
			powerTemp = 0;
			coefTemp = 0.0f;
			Monom monomP = iteratorP.next();
			if (monomP.getPutere() == 0) {
				Util.addMonomToExistingPolynomial(new Monom(0, 0), result);
				continue etic;
			}
			coefTemp = monomP.getCoeficient() * monomP.getPutere();
			powerTemp = monomP.getPutere() - 1;
			monomForResult = new Monom(coefTemp, powerTemp);
			Util.addMonomToExistingPolynomial(monomForResult, result);
		}
		return result;
	}

	public static Polinom calculateIntegrals(Polinom polynomial) {
		Polinom result = new Polinom();

		ListIterator<Monom> iteratorP = polynomial.getPolynomial().listIterator();
		Monom monomForResult;
		int powerTemp = 0;
		float coefTemp = 0.0f;
		while (iteratorP.hasNext()) {
			powerTemp = 0;
			coefTemp = 0.0f;
			Monom monomP = iteratorP.next();
			coefTemp = monomP.getCoeficient() / (monomP.getPutere() + 1);
			powerTemp = monomP.getPutere() + 1;
			monomForResult = new Monom(coefTemp, powerTemp);
			Util.addMonomToExistingPolynomial(monomForResult, result);
		}
		return result;
	}

	public static Polinom[] divideTwoPolynomials(Polinom polynomial1, Polinom polynomial2) {
		Polinom[] result = new Polinom[2];
		for (Polinom s : result)
			s = new Polinom();

		Polinom firstP = new Polinom();
		ListIterator<Monom> iteratorF = polynomial1.getPolynomial().listIterator();
		Monom monomTemp;
		while (iteratorF.hasNext()) {
			monomTemp = iteratorF.next();
			Util.addMonomToExistingPolynomial(monomTemp, firstP);
		}
		Util.sortDescendingOrderByPower(firstP);
		Polinom secondP = new Polinom();
		ListIterator<Monom> iteratorS = polynomial2.getPolynomial().listIterator();
		while (iteratorS.hasNext()) {
			monomTemp = iteratorS.next();
			Util.addMonomToExistingPolynomial(monomTemp, secondP);
		}
		Util.sortDescendingOrderByPower(secondP);

		Util2.doDivisionAlgorithm(firstP, secondP, result);
		return result;
	}

}
