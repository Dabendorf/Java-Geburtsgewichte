package geburtsgewichte;

import java.awt.GridBagConstraints;

/**
 * Diese Klasse steuert die Generierung von GridBagLayouts auf Grundlage der wichtigsten Eigenschaften.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class GridBagFelder extends GridBagConstraints {
	
	public GridBagFelder(int x, int y, int width, int height, double weightX, double weightY, boolean horiAndVerti) {
		if(horiAndVerti) {
			this.fill = GridBagConstraints.BOTH;
		} else {
			this.fill = GridBagConstraints.VERTICAL;
		}
	    this.gridx = x;
	    this.gridy = y;
	    this.gridwidth = width;
	    this.gridheight = height;
	    this.weightx = weightX;
	    this.weighty = weightY;
	}
}