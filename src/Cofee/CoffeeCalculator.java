import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CoffeeCalculator
{
   public static void main(String [] args)
   {
      NewFrame coffee = new NewFrame("Delicious Coffee Calculator");
      coffee.pack();
      coffee.setLocation(200,300);
      coffee.
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      coffee.setVisible(true); 
   }
}

class NewFrame extends JFrame
{
   public final double SUMATRAN = 5.00;
   public final double FRENCH_ROAST = 6.00;
   public final double ARACABIA = 7.00;
   public final double CAPPUCCINO = 8.00;
   public final double CARAMEL_MACCHIATO = 9.00;
  
   public final double GREAT_VALUE = 1.00;
   public final double FOLGERS = 1.50;
   public final double TIM_HORTONS = 2.50;
   public final double STARBUCKS = 3.00;
   public final double PEETS = 5.00;
  
   public final double CREAM = 0.35;
   public final double SUGAR = 0.15;
   public final double CHOCOLATE = 0.30;
   public final double PEPPERMINT = 0.40;
   public final double SYRUP = 0.25;
  
   public final double ZERO_COST = 0.00;
  
   JPanel titlePanel = new JPanel();
   JPanel detailPanel = new JPanel();
   JPanel calcPanel = new JPanel();
  
   JPanel coffeePanel = new JPanel();
   JPanel coffeeBrandPanel = new JPanel();
   JPanel extraPanel = new JPanel();
  
   JRadioButton Sumatran = new JRadioButton("Sumatran", true);
   JRadioButton frenchRoast = new JRadioButton("French Roast", false);
   JRadioButton Aracabia = new JRadioButton("Aracabia", false);
   JRadioButton Cappuccino = new JRadioButton("Cappuccino", false);
   JRadioButton caramelMacchiato = new JRadioButton("Caramel Macchiato", false);
  
   JRadioButton greatValue = new JRadioButton("Great Value", true);
   JRadioButton Folgers = new JRadioButton("Folgers", false);
   JRadioButton timHortons = new JRadioButton("Tim Hortons", false);
   JRadioButton Starbucks = new JRadioButton("Starbucks", false);
   JRadioButton Peets = new JRadioButton("Peet's", false);
  
   JCheckBox Cream = new JCheckBox("Cream");
   JCheckBox Sugar = new JCheckBox("Sugar");
   JCheckBox Chocolate = new JCheckBox("Chocolate");
   JCheckBox Peppermint = new JCheckBox("Peppermint");
   JCheckBox Syrup = new JCheckBox("Syrup");
  
   JButton messageButton = new JButton("Message");
   JButton priceButton = new JButton("Price");
   JButton exitButton = new JButton("Exit");
  
   JLabel menuTitle = new JLabel("Please order a delicious coffee drink below.");
  
   //Need to make a button group so that you cannot click on multiple coffee flavors
   ButtonGroup coffeeGroup = new ButtonGroup();
   ButtonGroup brandGroup = new ButtonGroup();
  
   public NewFrame(String s)
   {
      super(s);
     
      //Display coffee message to user
      titlePanel.add(menuTitle);
     
      //Set the grid layouts for coffee flavors and extras.
      coffeePanel.setLayout(new GridLayout(5,1));
      coffeeBrandPanel.setLayout(new GridLayout(5,1));
      extraPanel.setLayout(new GridLayout(5,1));
     
      //Reference this inside the grid layout of the detail panel
      detailPanel.setLayout(new GridLayout(1,3));
     
      //Add information to the coffee panel
      coffeePanel.add(Sumatran);
      coffeePanel.add(frenchRoast);
      coffeePanel.add(Aracabia);
      coffeePanel.add(Cappuccino);
      coffeePanel.add(caramelMacchiato);
      coffeePanel.setBorder(BorderFactory.createTitledBorder("Coffee Flavors"));
     
      //Add information to the cofee brand panel
      coffeeBrandPanel.add(greatValue);
      coffeeBrandPanel.add(Folgers);
      coffeeBrandPanel.add(timHortons);
      coffeeBrandPanel.add(Starbucks);
      coffeeBrandPanel.add(Peets);
      coffeeBrandPanel.setBorder(BorderFactory.createTitledBorder("Coffee Brand"));
     
      //Also add information to the extras panel
      extraPanel.add(Cream);
      extraPanel.add(Sugar);
      extraPanel.add(Chocolate);
      extraPanel.add(Peppermint);
      extraPanel.add(Syrup);
      extraPanel.setBorder(BorderFactory.createTitledBorder("Coffee Enhancements"));
     
      //Add these to the detail panel
      detailPanel.add(coffeePanel);
      detailPanel.add(coffeeBrandPanel);
      detailPanel.add(extraPanel);
     
      //Setup the bottom panel for calculations
      calcPanel.add(messageButton);
      calcPanel.add(priceButton);
      calcPanel.add(exitButton); 
     
      //Add these to the frame window
      add(titlePanel, BorderLayout.NORTH);
      add(detailPanel, BorderLayout.CENTER);
      add(calcPanel, BorderLayout.SOUTH);
     
      //Add the button groups so that you cannot order multiple coffee flavors
      coffeeGroup.add(Sumatran);
      coffeeGroup.add(frenchRoast);
      coffeeGroup.add(Aracabia);
      coffeeGroup.add(Cappuccino);
      coffeeGroup.add(caramelMacchiato);
     
      //Similarly, do the same for coffee brands.
      brandGroup.add(greatValue);
      brandGroup.add(Folgers);
      brandGroup.add(timHortons);
      brandGroup.add(Starbucks);
      brandGroup.add(Peets);
     
      Sumatran.setBackground(Color.WHITE);
      frenchRoast.setBackground(Color.WHITE);
      Aracabia.setBackground(Color.WHITE);
      Cappuccino.setBackground(Color.WHITE);
      caramelMacchiato.setBackground(Color.WHITE);
      greatValue.setBackground(Color.WHITE);
      Folgers.setBackground(Color.WHITE);
      timHortons.setBackground(Color.WHITE);
      Starbucks.setBackground(Color.WHITE);
      Peets.setBackground(Color.WHITE);
      Cream.setBackground(Color.WHITE);
      Sugar.setBackground(Color.WHITE);
      Chocolate.setBackground(Color.WHITE);
      Peppermint.setBackground(Color.WHITE);
      Syrup.setBackground(Color.WHITE);
      titlePanel.setBackground(Color.LIGHT_GRAY);
      coffeePanel.setBackground(Color.LIGHT_GRAY);
      coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
      extraPanel.setBackground(Color.LIGHT_GRAY);
      calcPanel.setBackground(Color.LIGHT_GRAY);
     
      messageButton.addActionListener(new MessageHandler());
      priceButton.addActionListener(new CalculationHandler());
      exitButton.addActionListener(new ExitHandler());
     
      Cream.addItemListener(new CheckBoxListener());
      Sugar.addItemListener(new CheckBoxListener());
      Chocolate.addItemListener(new CheckBoxListener());
      Peppermint.addItemListener(new CheckBoxListener());
      Syrup.addItemListener(new CheckBoxListener());
   }
  
   class MessageHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String result = "";
        
         if(Sumatran.isSelected())
            result = "Sumatran coffee is yummy.";
         if(frenchRoast.isSelected())
            result = "French roast is good in the morning.";
         if(Aracabia.isSelected())
            result = "Aracabian coffee is alright.";
         if(Cappuccino.isSelected())
            result = "Cappuccino is good in the morning or evening.";
         if(caramelMacchiato.isSelected())
            result = "Caramel macchiato is holy water.";
         
         JOptionPane.showMessageDialog(null, result);
      }
   }
  
   class CalculationHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         double totalCost = ZERO_COST; //Used as an accumulator
        
         if(Sumatran.isSelected())
            totalCost += SUMATRAN;
         if(frenchRoast.isSelected())
            totalCost += FRENCH_ROAST;
         if(Aracabia.isSelected())
            totalCost += ARACABIA;
         if(Cappuccino.isSelected())
            totalCost += CAPPUCCINO;
         if(caramelMacchiato.isSelected())
            totalCost += CARAMEL_MACCHIATO;
         if(greatValue.isSelected())
            totalCost += GREAT_VALUE;
         if(Folgers.isSelected())
            totalCost += FOLGERS;
         if(timHortons.isSelected())
            totalCost += TIM_HORTONS;
         if(Starbucks.isSelected())
            totalCost += STARBUCKS;
         if(Peets.isSelected())
            totalCost += PEETS;
         if(Cream.isSelected())
            totalCost += CREAM;
         if(Sugar.isSelected())
            totalCost += SUGAR;
         if(Chocolate.isSelected())
            totalCost += CHOCOLATE;
         if(Peppermint.isSelected())
            totalCost += PEPPERMINT;
         if(Syrup.isSelected())
            totalCost += SYRUP;
           
         String result = String.format("The total cost of your coffee purchase is $%.02f.", totalCost);
         JOptionPane.showMessageDialog(null, result);
      }
   }
   class ExitHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }  
   }
  
   private class CheckBoxListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         //Find the check box that was clicked
         if(e.getSource() == Cream)
         {
            if(Cream.isSelected())
            {
               titlePanel.setBackground(new Color(230,230,230));
               coffeePanel.setBackground(new Color(230,230,230));
               coffeeBrandPanel.setBackground(new Color(230,230,230));
               extraPanel.setBackground(new Color(230,230,230));;
               calcPanel.setBackground(new Color(230,230,230));
            }
           
            else
            {
               titlePanel.setBackground(Color.LIGHT_GRAY);
               coffeePanel.setBackground(Color.LIGHT_GRAY);
               coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
               extraPanel.setBackground(Color.LIGHT_GRAY);
               calcPanel.setBackground(Color.LIGHT_GRAY);
            }
         }
        
         if(e.getSource() == Sugar)
         {
            if(Sugar.isSelected())
            {
               titlePanel.setBackground(new Color(204,204,255));
               coffeePanel.setBackground(new Color(204,204,255));
               coffeeBrandPanel.setBackground(new Color(204,204,255));
               extraPanel.setBackground(new Color(204,204,255));;
               calcPanel.setBackground(new Color(204,204,255));
            }
           
            else
            {
               titlePanel.setBackground(Color.LIGHT_GRAY);
               coffeePanel.setBackground(Color.LIGHT_GRAY);
               coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
               extraPanel.setBackground(Color.LIGHT_GRAY);
               calcPanel.setBackground(Color.LIGHT_GRAY);
            }
         }
     
         if(e.getSource() == Chocolate)
         {
            if(Chocolate.isSelected())
            { 
               titlePanel.setBackground(new Color(200,105,0));
               coffeePanel.setBackground(new Color(200,105,0));
               coffeeBrandPanel.setBackground(new Color(200,105,0));
               extraPanel.setBackground(new Color(200,105,0));;
               calcPanel.setBackground(new Color(200,105,0));
            }
           
            else
            {
               titlePanel.setBackground(Color.LIGHT_GRAY);
               coffeePanel.setBackground(Color.LIGHT_GRAY);
               coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
               extraPanel.setBackground(Color.LIGHT_GRAY);
               calcPanel.setBackground(Color.LIGHT_GRAY);
            }
         }
        
         if(e.getSource() == Peppermint)
         {
            if(Peppermint.isSelected())
            { 
               titlePanel.setBackground(Color.GREEN);
               coffeePanel.setBackground(Color.GREEN);
               coffeeBrandPanel.setBackground(Color.GREEN);
               extraPanel.setBackground(Color.GREEN);
               calcPanel.setBackground(Color.GREEN);
            }
           
            else
            {
               titlePanel.setBackground(Color.LIGHT_GRAY);
               coffeePanel.setBackground(Color.LIGHT_GRAY);
               coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
               extraPanel.setBackground(Color.LIGHT_GRAY);
               calcPanel.setBackground(Color.LIGHT_GRAY);
            }
         }
        
         if(e.getSource() == Syrup)
         {
            if(Syrup.isSelected())
            { 
               titlePanel.setBackground(new Color(70,70,70));
               coffeePanel.setBackground(new Color(70,70,70));
               coffeeBrandPanel.setBackground(new Color(70,70,70));
               extraPanel.setBackground(new Color(70,70,70));
               calcPanel.setBackground(new Color(70,70,70));
            }
           
            else
            {
               titlePanel.setBackground(Color.LIGHT_GRAY);
               coffeePanel.setBackground(Color.LIGHT_GRAY);
               coffeeBrandPanel.setBackground(Color.LIGHT_GRAY);
               extraPanel.setBackground(Color.LIGHT_GRAY);
               calcPanel.setBackground(Color.LIGHT_GRAY);
            }
         }
      }
   }
}
