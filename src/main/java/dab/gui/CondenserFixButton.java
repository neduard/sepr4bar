package dab.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CondenserFixButton extends FixButton {

	public CondenserFixButton(int coordX, int coordY, int initialCounter){
		super(coordX, coordY, initialCounter);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				pressed();
				if (getFixed()){
            	    notifyFixObservers();
				}
			}
		});
	}
}
