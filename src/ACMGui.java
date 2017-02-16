import acm.gui.*;
import acm.program.*;
import java.awt.event.ActionEvent;


import javax.swing.*;
import javax.swing.event.*;

public class ACMGui extends Program implements ChangeListener{

	public void init() {
		field=new JTextField();
		result=new JTextField();
		JSlider slider=new JSlider(0,9,0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true); 
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		JTextField sliderValue=new JTextField();
		setLayout(new TableLayout(8, 4, 10, 10)); //Table layout is with grid and distance between buttons
		add(field, "gridwidth=4");
		add(result, "gridwidth=4");
		add(new JButton("Clear"), "gridwidth=2");
		add(new JButton("+/-"));
		add(new JButton("/"));
		add(new JButton("1"));
		add(new JButton("2"));
		add(new JButton("3"));
		add(new JButton("x"));
		add(new JButton("4"));
		add(new JButton("5"));
		add(new JButton("6"));
		add(new JButton("-"));		
		add(new JButton("7"));
		add(new JButton("8"));
		add(new JButton("9"));
		add(new JButton("+"));
		add(new JButton("0"), "gridwidth=2");
		add(new JButton("."));
		add(new JButton("="));
		addActionListeners();
		add(new JLabel("Precision")); //Label for precision
		add(slider, "gridwidth=4");
		
	}
	public void stateChanged(ChangeEvent e){ //Allows the slider value to be changed 
		JSlider slider = (JSlider)e.getSource();
		decimal = (int)slider.getValue();
	}
	public void actionPerformed(ActionEvent e){
		String x=e.getActionCommand();
		switch(x){ //Following things add elements to the field
		case "Clear":
			field.setText("");
			result.setText("");
			break;
		case "+":
			field.setText(field.getText()+"+");
			break;
		case "-":
			field.setText(field.getText()+"-");
			break;
		case "/":
			field.setText(field.getText()+"/");
			break;
		case "x":
			field.setText(field.getText()+"x");
			break;
		case ".":
			field.setText(field.getText()+".");
			break;
		case "=":
			Evaluate calc=new Evaluate();
			String out=calc.initialize(field.getText());
			result.setText(String.format("%1$." + decimal + "f", Double.parseDouble(out)));
			break;
			//Sets a negative boolean that can be manipulated later 
		case "+/-":
			if(negative){
				negative=false;
			}else{
				negative=true;
			}
		}
		//Accounts for the digits, if the digits are negative it gets added with a negative, if a digit is positive just adds to the string
		for(int i=0;i<10;i++){
			if(x.equals(Integer.toString(i))){
				if(negative){
					field.setText(field.getText()+"-"+ Integer.toString(i));
					negative=false;
				}else{
					field.setText(field.getText()+Integer.toString(i));
				}
			}
		}
	}
	
	
	private JTextField field;
	private JTextField result;
	private int decimal=0;
	private boolean negative=false;

}
