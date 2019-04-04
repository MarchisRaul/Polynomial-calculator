package MVCview;

import MVCmodel.Monom;
import MVCmodel.Polinom;
import MVCmodel.Util;
import MVCmodel.Util2;
import MVCcontroller.PolinoameController;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PolinoameView extends JFrame {

	JLabel labelFirstPolynomial;
	JLabel labelSecondPolynomial;
	JLabel operationSelection;
	JLabel labelResult;

	public JTextField textFirstPolynomial;
	public JTextField textSecondPolynomial;
	public JTextField textResult;

	public JComboBox operations;

	JButton calculateButton;

	JPanel mainPanel;
	JPanel tempPanel1;
	JPanel tempPanel2;
	JPanel tempPanel3;
	JPanel tempPanel4;
	JPanel tempPanel5;
	JPanel tempPanel6;
	JPanel resultPanel;

	public PolinoameView() throws HeadlessException {
		super();
		labelFirstPolynomial = new JLabel("Introduce the first polynomial:", JLabel.CENTER);
		labelSecondPolynomial = new JLabel("Introduce the second polynomial:", JLabel.CENTER);
		operationSelection = new JLabel("Select the operation: ", JLabel.CENTER);
		labelResult = new JLabel("Result is:");

		textFirstPolynomial = new JTextField(20);
		textFirstPolynomial.setText("0");
		textSecondPolynomial = new JTextField(20);
		textSecondPolynomial.setText("0");
		textResult = new JTextField(20);
		textResult.setEditable(false);
		operations = new JComboBox();
		calculateButton = new JButton("Calculate");

		tempPanel1 = new JPanel();
		tempPanel1.setLayout(new FlowLayout());
		tempPanel1.add(labelFirstPolynomial);
		tempPanel2 = new JPanel();
		tempPanel2.setLayout(new FlowLayout());
		tempPanel2.add(textFirstPolynomial);
		tempPanel3 = new JPanel();
		tempPanel3.setLayout(new FlowLayout());
		tempPanel3.add(labelSecondPolynomial);
		tempPanel4 = new JPanel();
		tempPanel4.setLayout(new FlowLayout());
		tempPanel4.add(textSecondPolynomial);
		tempPanel5 = new JPanel();
		tempPanel5.setLayout(new FlowLayout());
		tempPanel5.add(operationSelection);
		tempPanel6 = new JPanel();
		tempPanel6.setLayout(new FlowLayout());
		tempPanel6.add(operations);
		tempPanel6.add(calculateButton);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		resultPanel = new JPanel();
		resultPanel.setLayout(new FlowLayout());

		operations.addItem(new String("+"));
		operations.addItem(new String("-"));
		operations.addItem(new String("*"));
		operations.addItem(new String("/"));
		operations.addItem(new String("Derivation"));
		operations.addItem(new String("Integration"));

		resultPanel.add(labelResult);
		resultPanel.add(textResult);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 2)));
		mainPanel.add(tempPanel1);
		mainPanel.add(tempPanel2);
		mainPanel.add(tempPanel3);
		mainPanel.add(tempPanel4);
		mainPanel.add(tempPanel5);
		mainPanel.add(tempPanel6);
		mainPanel.add(resultPanel);

		this.setTitle("Polynomial operations");
		this.setSize(400, 280);
		this.setLocation(760, 390);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	public void calculationListener(ActionListener action) {
		calculateButton.addActionListener(action);
	}

	public String printResult(String operation, Polinom result) {
		String finalResult = "";
		Iterator<Monom> myIterator = result.getPolynomial().iterator();
		while (myIterator.hasNext()) {
			String tempString = "";
			Monom monom = myIterator.next();
			if (operation.equals("+") || operation.equals("-") || operation.equals("*")
					|| operation.equals("Derivation"))
				tempString = Util.doWorkOnStringIntCoef(monom, finalResult, tempString);
			else
				tempString = Util.doWorkOnStringFloatCoef(monom, finalResult, tempString);
			finalResult = finalResult + tempString;
		}
		if (finalResult.equals(""))
			finalResult = "0";
		this.textResult.setText(finalResult);
		return finalResult;
	}

}
