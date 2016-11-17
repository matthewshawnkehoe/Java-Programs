/*
 	Matthew Kehoe
*/

import javax.swing.*;
import javax.swing.border.Border;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class PostfixCalculatorDoubleColors extends JFrame
{
	private JPanel calculatedResultPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	private JPanel num1Panel;
	private JPanel num2Panel;
	private JPanel num3Panel;
	private JPanel num4Panel;
	private JPanel num5Panel;
	private JPanel num6Panel;
	private JPanel num7Panel;
	private JPanel num8Panel;
	private JPanel num9Panel;
	private JPanel addPanel;
	private JPanel subtractPanel;
	private JPanel multiplyPanel;
	private JPanel dividePanel;
	private JPanel zeroPanel;
	private JPanel spacePanel;
	private JPanel calculatePanel;
	private JPanel clearPanel;
	private JButton zero;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	private JButton add;
	private JButton subtract;
	private JButton multiply;
	private JButton divide;
	private JButton space;
	private JButton calculate;
	private JButton clear;
	private JLabel calculatorLabel;
	private final int WINDOW_WIDTH = 350;
	private final int WINDOW_HEIGHT = 250;
	private static final Random rnd = new Random();
	
	// Main
	public static void main(String[] args)
	{
		new PostfixCalculatorDoubleColors();
	}
	
	// Constructor
	public PostfixCalculatorDoubleColors()
	{
		setTitle("Posfix Calculator: ***PUSH BUTTON TO GENERATE COLORS***");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Build the panel and add it to the frame
		buildPanel();
		
		//Get the string output from the buttons pressed
		getStringOutput();
		
		// Display the window
		setVisible(true);
	}
	
	// The buildPanel method is created to add a label, text field,
	// and a button to the panel
	public void buildPanel()
	{
		// Use the insert containers method
		insertContainers();
		
		// Adjust the north panel to display the label bar
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		calculatorLabel.setBorder(border);
		calculatorLabel.setPreferredSize(new Dimension(150, 25));
		calculatedResultPanel.add(calculatorLabel);
		northPanel.add(calculatedResultPanel);
		add(northPanel, BorderLayout.NORTH);
		
		// Adjust the east panel to display the majority
		// of the buttons
		centerPanel.setLayout(new GridLayout(4,4));
		num1Panel.add(one);
		num2Panel.add(two);
		num3Panel.add(three);
		addPanel.add(add);
		num4Panel.add(four);
		num5Panel.add(five);
		num6Panel.add(six);
		subtractPanel.add(subtract);
		num7Panel.add(seven);
		num8Panel.add(eight);
		num9Panel.add(nine);
		multiplyPanel.add(multiply);
		zeroPanel.add(zero);
		clearPanel.add(clear);
		dividePanel.add(divide);
		centerPanel.add(num1Panel);
		centerPanel.add(num2Panel);
		centerPanel.add(num3Panel);
		centerPanel.add(addPanel);
		centerPanel.add(num4Panel);
		centerPanel.add(num5Panel);
		centerPanel.add(num6Panel);
		centerPanel.add(subtractPanel);
		centerPanel.add(num7Panel);
		centerPanel.add(num8Panel);
		centerPanel.add(num9Panel);
		centerPanel.add(multiplyPanel);
		centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
		centerPanel.add(zeroPanel);
		centerPanel.add(clearPanel);
		centerPanel.add(dividePanel);
		add(centerPanel, BorderLayout.CENTER);
		
		// Put the remaining buttons and labels inside the South panel
		southPanel.setLayout(new GridLayout(1,3));
		calculatePanel.add(calculate);
		spacePanel.add(space);
		southPanel.add(Box.createRigidArea(new Dimension(0,5)));
		southPanel.add(calculatePanel);
		southPanel.add(spacePanel);
		add(southPanel, BorderLayout.SOUTH);
		
		// Add the action listeners to the panel
		zero.addActionListener(new buttonListener());
		one.addActionListener(new buttonListener());
		two.addActionListener(new buttonListener());
		three.addActionListener(new buttonListener());
		four.addActionListener(new buttonListener());
		five.addActionListener(new buttonListener());
		six.addActionListener(new buttonListener());
		seven.addActionListener(new buttonListener());
		eight.addActionListener(new buttonListener());
		nine.addActionListener(new buttonListener());
		add.addActionListener(new buttonListener());
		subtract.addActionListener(new buttonListener());
		multiply.addActionListener(new buttonListener());
		divide.addActionListener(new buttonListener());
		space.addActionListener(new buttonListener());
		clear.addActionListener(new buttonListener());
		
		//Add the action listener for the calculate button
		calculate.addActionListener(new calcButtonListener());
		
		// Make some colors
		
		zero.addActionListener(new ColorListener());
		one.addActionListener(new ColorListener());
		two.addActionListener(new ColorListener());
		three.addActionListener(new ColorListener());
		four.addActionListener(new ColorListener());
		five.addActionListener(new ColorListener());
		six.addActionListener(new ColorListener());
		seven.addActionListener(new ColorListener());
		eight.addActionListener(new ColorListener());
		nine.addActionListener(new ColorListener());
		add.addActionListener(new ColorListener());
		subtract.addActionListener(new ColorListener());
		multiply.addActionListener(new ColorListener());
		divide.addActionListener(new ColorListener());
		space.addActionListener(new ColorListener());
		clear.addActionListener(new ColorListener());
		calculate.addActionListener(new ColorListener());
	}
	
	// Insert the panels, buttons and labels into the frame
	public void insertContainers()
	{
		// Insert a label for the calculator output
		calculatorLabel = new JLabel("");
			
		// Create the panels
		calculatedResultPanel = new JPanel();
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
				
		// Create the specialized panels
				
		num1Panel = new JPanel();
		num2Panel = new JPanel();
		num3Panel = new JPanel();
		num4Panel = new JPanel();
		num5Panel = new JPanel();
		num6Panel = new JPanel();
		num7Panel = new JPanel();
		num8Panel = new JPanel();
		num9Panel = new JPanel();
		addPanel = new JPanel();
		subtractPanel = new JPanel();
		multiplyPanel = new JPanel();
		zeroPanel = new JPanel();
		clearPanel = new JPanel();
		dividePanel = new JPanel();
		calculatePanel = new JPanel();
		spacePanel = new JPanel();
				
		// Create the buttons to be displayed
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		add = new JButton("+");
		subtract = new JButton("-");
		multiply = new JButton("*");
		zero = new JButton("0");
		clear = new JButton("C");
		divide = new JButton("/");
		calculate = new JButton("Calculate");
		space = new JButton("_");
	}
	
	// Use the String's split method to get rid of spaces
	public String[] getStringOutput()
	{
		String str = calculatorLabel.getText();
		String[] string = str.split(" ");
		
		return string;
	}
	
	// Create the evaluatePostFix method to be referenced
	// by the calcButtonListener
	public static double postfixCalc(String[] strArray) 
	{
	    // Create a new double array which will loop through the String[] array
		Stack<Double> numbers = new Stack<Double>();
		// Create a for statement which looks  at each individual element in the array
	    for(int i = 0; i < strArray.length; i++) 
	    {
			// Specify a specific element inside the array as "StringArray at i"
			// This will loop through the entire array starting with the first element 
			String element = strArray[i];
			// if the element is "+" add the last 2 numbers a + b
	        if(element.equals("+")) 
			{
				// .doubleValue() returns the primitive data type of "double."
				// This can be used to convert "6" to 6.
				// Pop the last two values at the top of the stack
				double b = numbers.pop().doubleValue();
	            double a = numbers.pop().doubleValue();
				// Push a + b back into the numbers stack. Convert this value to a new integer.
	            // This calculation is self explanatory.  4 5 + adds 4 + 5 = 9.
	            numbers.push(new Double(a + b));
	        } 
			// if the element is "-" subtract the difference of a - b
			else if(element.equals("-")) 
			{
				// Pop the last two values at the top of the stack
				double b = numbers.pop().doubleValue();
	            double a = numbers.pop().doubleValue();
				// Push a - b back into the numbers stack. Convert this value to a new integer.
	            // Note: 10 - 5 in INFIX notation is the same as 10 5 - in POSTFIX notation.
	            // We want to take the first number b(5) and minus this from the second number a(10).
	            // 10 - 5 = 5.
	            numbers.push(new Double(a - b));
	        } 
			// if the element is "*" multiply a times b
			else if(element.equals("*")) 
			{
				// Pop the last two values at the top of the stack
				double b = numbers.pop().doubleValue();
	            double a = numbers.pop().doubleValue();
				// Push a * b back into the numbers stack. Convert this value to a new integer.
	            // This calculation is self explanatory.  4 5 * multiplies 4 * 5 to get 20.
	            numbers.push(new Double(a * b));
	        } 
			// if the element is "/" divide a by b
			else if(element.equals("/")) 
			{
				// Pop the last two values at the top of the stack
				double b = numbers.pop().doubleValue();
	            double a = numbers.pop().doubleValue();
				// Push a / b back into the numbers stack. Convert this value to a new integer.
	            // Note : 10 / 5 in INFIX notation is the same as 10 5 / in POSTFIX notation
	            // This is why you pop b then a. b = 5, a = 10. a(10) b(5) / is 2.
	            numbers.push(new Double(a / b));
	        } 
			// If the number is not an operator such as +,-,*,/ then we can push the
			// number back to the stack.
			else 
			{ 
	            double num = Double.parseDouble(element);
	            numbers.push(new Double(num));
	        }
	    }
		// We are now outside of the for statement.
		// We now have the correct value from the postfix calculation
		// due to numbers.push(). Lets put this back inside the stack!
	    return numbers.pop().doubleValue();
	}
	
	private class ColorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			one.setBackground(new Color(rnd.nextInt()));
			two.setBackground(new Color(rnd.nextInt()));
			three.setBackground(new Color(rnd.nextInt()));
			four.setBackground(new Color(rnd.nextInt()));
			five.setBackground(new Color(rnd.nextInt()));
			six.setBackground(new Color(rnd.nextInt()));
			seven.setBackground(new Color(rnd.nextInt()));
			eight.setBackground(new Color(rnd.nextInt()));
			nine.setBackground(new Color(rnd.nextInt()));
			add.setBackground(new Color(rnd.nextInt()));
			subtract.setBackground(new Color(rnd.nextInt()));
			multiply.setBackground(new Color(rnd.nextInt()));
			zero.setBackground(new Color(rnd.nextInt()));
			clear.setBackground(new Color(rnd.nextInt()));
			divide.setBackground(new Color(rnd.nextInt()));
			calculate.setBackground(new Color(rnd.nextInt()));
			space.setBackground(new Color(rnd.nextInt()));
			calculatedResultPanel.setBackground(new Color(rnd.nextInt()));
		}
	}
	// Create the calculator button action listener.
	private class calcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] str = getStringOutput();
			try
			{
				double postFixNum = postfixCalc(str);
				if (Double.isInfinite(postFixNum) || Double.isNaN(postFixNum))
			        throw new ArithmeticException("illegal double value: " + postFixNum);
				calculatorLabel.setText("" + postFixNum);
			}
			catch (NumberFormatException excep)
			{
				JOptionPane.showMessageDialog(null, 
						"Enter the correct number format. "
						+ "Characters should be followed by spaces.");
			}
			catch (EmptyStackException ese)
			{
				JOptionPane.showMessageDialog(null, 
						"Enter the correct number format. "
						+ "Characters should be followed by spaces.");
			}
			catch (ArithmeticException ae)
			{
				JOptionPane.showMessageDialog(null, 
						"Don't divide by 0.");
			}
		}
	}
	// Add the button pressed to the JLabel field
	private class buttonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Determine which button was clicked and add this to the JLabel
			if( e.getSource() == zero)
				calculatorLabel.setText(calculatorLabel.getText() + "0");
			
			if( e.getSource() == one)
				calculatorLabel.setText(calculatorLabel.getText() + "1");
			
			if( e.getSource() == two)
				calculatorLabel.setText(calculatorLabel.getText() + "2");
			
			if( e.getSource() == three)
				calculatorLabel.setText(calculatorLabel.getText() + "3");
			
			if( e.getSource() == four)
				calculatorLabel.setText(calculatorLabel.getText() + "4");
			
			if( e.getSource() == five)
				calculatorLabel.setText(calculatorLabel.getText() + "5");
			
			if( e.getSource() == six)
				calculatorLabel.setText(calculatorLabel.getText() + "6");
			
			if( e.getSource() == seven)
				calculatorLabel.setText(calculatorLabel.getText() + "7");
			
			if( e.getSource() == eight)
				calculatorLabel.setText(calculatorLabel.getText() + "8");
			
			if( e.getSource() == nine)
				calculatorLabel.setText(calculatorLabel.getText() + "9");
			
			if( e.getSource() == add)
				calculatorLabel.setText(calculatorLabel.getText() + "+");
			
			if( e.getSource() == subtract)
				calculatorLabel.setText(calculatorLabel.getText() + "-");
			
			if( e.getSource() == multiply)
				calculatorLabel.setText(calculatorLabel.getText() + "*");
			
			if( e.getSource() == divide)
				calculatorLabel.setText(calculatorLabel.getText() + "/");
			
			if( e.getSource() == space)
				calculatorLabel.setText(calculatorLabel.getText() + " ");
			
			if( e.getSource() == clear)
				calculatorLabel.setText("");
		}	
	}
}
