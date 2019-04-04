package MVCmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;

public class Polinom {
	List<Monom> polynomial;

	public Polinom() {
		super();
		polynomial = new LinkedList<Monom>();
	}

	public List<Monom> getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(List<Monom> polynomial) {
		this.polynomial = polynomial;
	}

	public void addToPolynomial(Monom monom) {
		this.getPolynomial().add(monom);
	}

	public Polinom addition(Polinom polynomial1, Polinom polynomial2) {
		Polinom resultP = new Polinom();

		Util.addToExistingPolynomial(polynomial1, resultP);
		Util.addToExistingPolynomial(polynomial2, resultP);
		Util.sortDescendingOrderByPower(resultP);
		return resultP;
	}

	public Polinom substraction(Polinom polynomial1, Polinom polynomial2) {
		Polinom resultP = new Polinom();

		Util.addToExistingPolynomial(polynomial1, resultP);
		Util.substractFromExistingPolynomial(polynomial2, resultP);
		Util.sortDescendingOrderByPower(resultP);
		return resultP;
	}

	public Polinom multiplication(Polinom polynomial1, Polinom polynomial2) {
		Polinom resultP = new Polinom();

		resultP = Util.multiplyingTwoPolynomials(polynomial1, polynomial2);
		Util.sortDescendingOrderByPower(resultP);
		return resultP;
	}

	public Polinom derivation(Polinom polynomial1) {
		Polinom resultP = new Polinom();

		resultP = Util.calculateDerivatives(polynomial1);
		Util.sortDescendingOrderByPower(resultP);
		return resultP;
	}

	public Polinom integration(Polinom polynomial1) {
		Polinom resultP = new Polinom();

		resultP = Util.calculateIntegrals(polynomial1);
		Util.sortDescendingOrderByPower(resultP);
		return resultP;
	}

	public Polinom[] division(Polinom polynomial1, Polinom polynomial2) {
		Polinom[] resultP = new Polinom[2];
		for (Polinom s : resultP)
			s = new Polinom();

		resultP = Util.divideTwoPolynomials(polynomial1, polynomial2);
		if (resultP[0] != null)
			Util.sortDescendingOrderByPower(resultP[0]);
		if (Util2.checkDifferentFromNull(polynomial2) != 1)
			JOptionPane.showMessageDialog(null, "Dividing by 0 caused an error!", "Error", JOptionPane.ERROR_MESSAGE);
		else
			Util.sortDescendingOrderByPower(resultP[1]);
		return resultP;
	}

}
