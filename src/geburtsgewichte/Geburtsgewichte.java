package geburtsgewichte;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.NumberFormatter;

/**
 * Diese Klasse ist die Hauptklasse des Geburtsgewichte-Projektes, welche sowohl die Grafik als auch interne Berechnungen umfasst.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Geburtsgewichte {
	
	private JFrame frame1 = new JFrame("Geburtsgewichte");
	private JLabel labelTopic = new JLabel();
	private NumberFormat format = NumberFormat.getInstance(); 
    private NumberFormatter formatter = new NumberFormatter(format); 
	private JFormattedTextField textfeldAnzahl = new JFormattedTextField(formatter);
	private JButton buttonStart = new JButton();
	private JList<String> geburtsliste = new JList<String>();
	private DefaultListModel<String> geburtslisteModel = new DefaultListModel<String>();
	private JScrollPane geburtslisteScrollPane = new JScrollPane(geburtsliste);
	
	public Geburtsgewichte() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(270,220));
		frame1.setMinimumSize(new Dimension(270,220));
		frame1.setMaximumSize(new Dimension(400,400));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		labelTopic.setText("Anzahl der Geburten:");
		textfeldAnzahl.setText("10000");
		buttonStart.setText("Einordnen");
		buttonStart.setMargin(new Insets(2, 2, 2, 2));
	    buttonStart.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) { 
	    		einordnen();
	    	}
	    });
	    geburtsliste.setModel(geburtslisteModel);
		
		JPanel oben = new JPanel();
		oben.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        c.gridx = 0;
        oben.add(labelTopic,c);
        c.gridx = 1;
        oben.add(textfeldAnzahl,c);
        
        frame1.add(oben,new GridBagFelder(0,0,1,1,1,0.1,true));
        frame1.add(buttonStart,new GridBagFelder(0,1,1,1,1,0.1,false));
        frame1.add(geburtslisteScrollPane,new GridBagFelder(0,2,1,1,1,0.8,true));
	    
		format.setGroupingUsed(false);
	    formatter.setAllowsInvalid(false);
	    frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
	
	/**
	 * Diese Methode liest die Anzahl der Geburten ein, wuerfelt die Gewichte aus und ordnet sie in Kategorien ein.<br>
	 * Ausserdem berechnet sie das durchschnittliche Gewicht.
	 */
	private void einordnen() {
		int anzahl = Integer.valueOf(textfeldAnzahl.getText());
	    int anzahl2000 = 0;
	    int anzahl2500 = 0;
	    int anzahl3000 = 0;
	    int anzahl3500 = 0;
	    int anzahl4000 = 0;
	    int anzahl4500 = 0;
	    
	    Random wuerfel = new Random();
	    ArrayList<Integer> gewichte = new ArrayList<Integer>();
	    for(int i=0;i<anzahl;i++) {
	    	gewichte.add(wuerfel.nextInt(3001)+2000);
	    }
	    int gesamt = 0;
	    for(int gewicht:gewichte) {
	    	gesamt += gewicht;
	    	if(gewicht>=2000 && gewicht<2500) {
	    		anzahl2000++;
	    	} else if(gewicht>=2500 && gewicht<3000) {
	    		anzahl2500++;
	    	} else if(gewicht>=3000 && gewicht<3500) {
	    		anzahl3000++;
	    	} else if(gewicht>=3500 && gewicht<4000) {
	    		anzahl3500++;
	    	} else if(gewicht>=4000 && gewicht<4500) {
	    		anzahl4000++;
	    	} else if(gewicht>=4500 && gewicht<5000) {
	    		anzahl4500++;
	    	}
	    }
	    gesamt /= gewichte.size();
		 
		geburtslisteModel.removeAllElements();
	    geburtslisteModel.addElement("Anzahl 2000-2499g: "+anzahl2000);
	    geburtslisteModel.addElement("Anzahl 2500-2999g: "+anzahl2500);
	    geburtslisteModel.addElement("Anzahl 3000-3499g: "+anzahl3000);
	    geburtslisteModel.addElement("Anzahl 3500-3999g: "+anzahl3500);
	    geburtslisteModel.addElement("Anzahl 4000-4499g: "+anzahl4000);
	    geburtslisteModel.addElement("Anzahl 4500-5000g: "+anzahl4500);
	    geburtslisteModel.addElement("Durchschnitt: "+gesamt+"g");
	}

	public static void main(String[] args) {
		new Geburtsgewichte();
	}
}