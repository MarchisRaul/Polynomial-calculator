import static org.junit.Assert.*;

import org.junit.Test;

import MVCmodel.Monom;
import MVCmodel.Polinom;
import MVCmodel.Util;
import MVCmodel.Util2;
import MVCview.PolinoameView;
import MVCcontroller.PolinoameController;

public class TestClass {

	PolinoameView myView = new PolinoameView();
	Polinom pol1 = new Polinom();
	Polinom pol2 = new Polinom();
	Polinom result = new Polinom();

	@Test
	public void testAddition() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
			pol2 = Util.convertToPolynomial("-1-2-3x^3+x^2+x");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("+", result.addition(pol1, pol2)).equals("3x^3+2x^2+2x-12"));
		result = new Polinom();
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
			pol2 = Util.convertToPolynomial("x-1+3x^2");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(myView.printResult("+", result.addition(pol1, pol2)).equals("0"));
	}

	@Test
	public void testSubstraction() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
			pol2 = Util.convertToPolynomial("-1-2-3x^3+x^2+x");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("-", result.substraction(pol1, pol2)).equals("9x^3-6"));
		result = new Polinom();
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
			pol2 = Util.convertToPolynomial("x-1+3x^2");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(myView.printResult("-", result.substraction(pol1, pol2)).equals("-6x^2-2x+2"));
	}

	@Test
	public void testMultiplication() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
			pol2 = Util.convertToPolynomial("-1-2-3x^3+x^2+x");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("*", result.multiplication(pol1, pol2))
				.equals("-18x^6+3x^5+4x^4+11x^3-11x^2-12x+27"));
		result = new Polinom();
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
			pol2 = Util.convertToPolynomial("x-1+3x^2");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(myView.printResult("*", result.multiplication(pol1, pol2)).equals("-9x^4-6x^3+8x^2+3x-1"));
	}

	@Test
	public void testDivision() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
			pol2 = Util.convertToPolynomial("-1-2-3x^3+x^2+x");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		Polinom[] polDivision = new Polinom[2];
		polDivision = result.division(pol1, pol2);
		assertTrue(myView.printResult("/", polDivision[0]).equals("-2.00"));
		assertTrue(myView.printResult("/", polDivision[1]).equals("3.00x^2+3.00x-15.00"));
		polDivision = new Polinom[2];
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
			pol2 = Util.convertToPolynomial("x-1+3x^2");
			polDivision = result.division(pol1, pol2);
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("/", polDivision[0]).equals("-1.00"));
		assertFalse(myView.printResult("/", polDivision[1]).equals("2.00"));
	}

	@Test
	public void testDerivation() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("Derivation", result.derivation(pol1)).equals("18x^2+2x+1"));
		result = new Polinom();
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(myView.printResult("Derivation", result.derivation(pol1)).equals("-6x-2"));
	}

	@Test
	public void testIntegration() {
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(myView.printResult("Integration", result.integration(pol1)).equals("1.50x^4+0.33x^3+0.50x^2-9.00x"));
		result = new Polinom();
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(myView.printResult("Integration", result.integration(pol1)).equals("-x^3-0.50x^2+1.00x"));
	}

	@Test
	public void testConversion() {
		boolean correctlyConverted = false;
		try {
			pol1 = Util.convertToPolynomial("x-x+x+2x^2-x^2+6x^3-17+8");
			pol2 = Util.convertToPolynomial("-1-2-3x^3+x^2+x");
			correctlyConverted = true;
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertTrue(correctlyConverted);
		correctlyConverted = false;
		try {
			pol1 = Util.convertToPolynomial("-x+2-3x^2");
			pol2 = Util.convertToPolynomial("x-1+3x^2+a");
			correctlyConverted = true;
		} catch (Exception e) {
			System.out.println("Incorrect conversion of strings in method testAddition");
		}
		assertFalse(correctlyConverted);
	}
}
