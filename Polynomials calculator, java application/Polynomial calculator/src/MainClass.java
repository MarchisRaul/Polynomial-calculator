import MVCmodel.Monom;
import MVCmodel.Polinom;
import MVCmodel.Util;
import MVCmodel.Util2;
import MVCview.PolinoameView;

import javax.swing.JOptionPane;

import MVCcontroller.PolinoameController;

public class MainClass {

	public static void main(String[] args) {
		PolinoameView myView = new PolinoameView();
		Polinom myModel = new Polinom();
		PolinoameController myController = new PolinoameController(myModel, myView);
		JOptionPane.showMessageDialog(null, "Example of polynomial: 3x^2+5x-4x^3+1", "Informative message",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
