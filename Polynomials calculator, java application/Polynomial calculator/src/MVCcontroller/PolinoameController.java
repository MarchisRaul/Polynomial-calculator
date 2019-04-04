package MVCcontroller;

import MVCmodel.Monom;
import MVCmodel.Polinom;
import MVCmodel.Util;
import MVCmodel.Util2;
import MVCview.PolinoameView;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class PolinoameController {
	protected Polinom model;
	public PolinoameView viewP;

	public PolinoameController(Polinom model, PolinoameView view) {
		super();
		this.model = model;
		this.viewP = view;

		viewP.calculationListener(new ButtonListener());
	}

	public Polinom getModel() {
		return model;
	}

	public void setModel(Polinom model) {
		this.model = model;
	}

	public PolinoameView getViewP() {
		return viewP;
	}

	public void setViewP(PolinoameView viewP) {
		this.viewP = viewP;
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String firstP = viewP.textFirstPolynomial.getText();
			String secondP = viewP.textSecondPolynomial.getText();
			String operation = (String) viewP.operations.getSelectedItem();

			Polinom firstPolynomial = new Polinom();
			try {
				firstPolynomial = Util.convertToPolynomial(firstP);
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null,
						"Something went wrong with the first Polynomial !\nPlease introduce valid polynomials !\nExample : 3x^2+x-1",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			Util.sortDescendingOrderByPower(firstPolynomial);

			Polinom secondPolynomial = new Polinom();
			if (operation.equals("Derivation") == false && operation.equals("Integration") == false) {
				if (secondP.equals("For derivation => first text field, please.")
						|| secondP.equals("For integration => first text field, please.")) {
					viewP.textSecondPolynomial.setEditable(true);
					viewP.textSecondPolynomial.setText("0");
					secondP = "0";
				}
				try {
					secondPolynomial = Util.convertToPolynomial(secondP);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null,
							"Something went wrong with the second Polynomial !\nPlease introduce valid polynomials !\nExample : 3x^2+x-1",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				Util.sortDescendingOrderByPower(secondPolynomial);
			}

			Polinom result = new Polinom();
			Polinom[] resultDiv = new Polinom[2];
			if (operation.equals("+"))
				result = result.addition(firstPolynomial, secondPolynomial);
			else if (operation.equals("-"))
				result = result.substraction(firstPolynomial, secondPolynomial);
			else if (operation.equals("*"))
				result = result.multiplication(firstPolynomial, secondPolynomial);
			else if (operation.equals("/")) {
				if (Util2.checkDegree(secondPolynomial) > Util2.checkDegree(firstPolynomial)) {
					JOptionPane.showMessageDialog(null,
							"Make sure that the degree of the first polynomial is greater or equal with the degree of the second polynomial !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				resultDiv = result.division(firstPolynomial, secondPolynomial);
			} else if (operation.equals("Derivation")) {
				viewP.textSecondPolynomial.setText("For derivation => first text field, please.");
				viewP.textSecondPolynomial.setEditable(false);
				result = result.derivation(firstPolynomial);
			} else if (operation.equals("Integration")) {
				viewP.textSecondPolynomial.setText("For integration => first text field, please.");
				viewP.textSecondPolynomial.setEditable(false);
				result = result.integration(firstPolynomial);
			}

			if (operation.equals("/") && resultDiv[0] != null) {
				viewP.printResult(operation, resultDiv[0]);
				if (resultDiv[1] != null) {
					Polinom polinomForRest = new Polinom();
					Iterator<Monom> myIt = resultDiv[1].getPolynomial().iterator();
					etic1: while (myIt.hasNext()) {
						Monom monomTemp = myIt.next();
						if (monomTemp.getCoeficient() == 0.0f)
							continue etic1;
						else
							polinomForRest.getPolynomial().add(monomTemp);
					}
					String tempStringOne = viewP.textResult.getText();
					viewP.printResult(operation, polinomForRest);
					String tempStringTwo = viewP.textResult.getText();
					tempStringOne = tempStringOne + ", Rest: " + tempStringTwo;
					viewP.textResult.setText(tempStringOne);
				}
			} else
				viewP.printResult(operation, result);
		}
	}

}
