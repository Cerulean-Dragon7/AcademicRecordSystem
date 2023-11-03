import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class StudentSystem extends JFrame
{
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int TRANSFER = 3;
    private static final int EXIT = 4;

    private BorderLayout borderLayout; // declare layout managers
    private JPanel numberPad; // key pad for input
    //create array for saved 15 number and character
    private JButton[] numberPad_Button = new JButton[15];
    private JPanel leftSide; // left hand side buttons
    private JButton[] leftSide_Button = new JButton[4];
    private JPanel rightSide; // right hand side buttons
    private JButton[] rightSide_Button = new JButton[4];
    private JPanel mon;  //the monitor of the StudentSystem
    private JLabel AccountNumber_label;
    private JTextField AccountNumber_field;
    private JLabel AccountPassword_label;
    private JPasswordField AccountPassword_field;
    public StudentSystem()
    {
        super("StudentSystem"); // the name of the window

        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start

        // set frame layout for the whole window
        setLayout(borderLayout = new BorderLayout());

        numberPad = new JPanel();
        //set the grid layout for the keypad
        numberPad.setLayout( new GridLayout(4,4,2,2) );
        numberPad.setPreferredSize(new Dimension(800, 250));
        numberPad.setMaximumSize(numberPad.getPreferredSize());
        numberPad.setMinimumSize(numberPad.getPreferredSize());

        // use for loop to print the key pad
        for(int i = 0; i < numberPad_Button.length; i++)
        {
            numberPad_Button[i] = new JButton(Integer.toString(i));
        }
        //print the number of the keypad
        for(int i = 1; i < 4; i++)
        {
            numberPad.add(numberPad_Button[i]);
        }
        //add the word ' Clear' to the keypad of the JFrame
        numberPad_Button[13].setText("Clear");
        numberPad.add( numberPad_Button[13] );

        //print the number of the keypad
        for(int i = 4; i < 7; i++)
        {
            numberPad.add(numberPad_Button[i]);
        }
        // set the text for the button
        numberPad_Button[12].setText("Cancel");
        //add the word ' Cancel' to the keypad of the JFrame
        numberPad.add( numberPad_Button[12] );
        for(int i = 7; i < 10; i++)
        {
            numberPad.add(numberPad_Button[i]);
        }
        //add the word ' Enter' to the keypad of the JFrame
        numberPad_Button[14].setText("Enter");
        numberPad.add( numberPad_Button[14] );

        // set the text for the button
        numberPad_Button[0].setText("0");
        numberPad_Button[10].setText(".");
        numberPad_Button[11].setText("00");
        //add number '0' to the keypad of the JFrame
        numberPad.add(numberPad_Button[0]);
        //add character '.' to the keypad of the JFrame
        numberPad.add(numberPad_Button[10]);
        //add character '00' to the keypad of the JFrame
        numberPad.add(numberPad_Button[11]);

        for (int  i = 0; i <= 11; i++)
        {
            numberPad_Button[i].setBackground(Color.LIGHT_GRAY);
        }
        numberPad_Button[12].setBackground(Color.RED);
        numberPad_Button[13].setBackground(Color.YELLOW);
        numberPad_Button[14].setBackground(Color.GREEN);

        leftSide = new JPanel();
        // set layout for left hand side button
        leftSide.setLayout( new GridLayout(4,1,5,5) ); // 4 by 1; no gaps
        rightSide = new JPanel();
        rightSide.setLayout( new GridLayout(4,1,5,5) );
        // use for loop to print the array of the left and right hand side button
        for(int i = 0; i < leftSide_Button.length && i < rightSide_Button.length; i++)
        {
            leftSide_Button[i] = new JButton();
            // add left hand side button to the JFrame
            leftSide.add(leftSide_Button[i]);
            leftSide_Button[i].setPreferredSize(new Dimension(195, 115));
            rightSide_Button[i] = new JButton();
            // add right hand side button to the JFrame
            rightSide.add(rightSide_Button[i]);
            rightSide_Button[i].setPreferredSize(new Dimension(195, 115));
            leftSide_Button[i].setBackground(Color.LIGHT_GRAY);
            rightSide_Button[i].setBackground(Color.LIGHT_GRAY);
        }

        leftSide_Button[0].setText("");
        leftSide_Button[1].setText("");
        leftSide_Button[2].setText("");
        leftSide_Button[3].setText("Personal Information");
        rightSide_Button[0].setText("");
        rightSide_Button[1].setText("");
        rightSide_Button[2].setText("Check Score");
        rightSide_Button[3].setText("Update");

        mon = new JPanel();
        mon.setLayout( null ); // Monitor do not have the layout managers
        AccountNumber_label = new JLabel("Account:");
        AccountNumber_label.setFont(new Font("Serif", Font.PLAIN, 20));
        // using Absolute positioning method to set the position of the GUI component
        AccountNumber_label.setBounds(60,170,200,20); //x,y,w,h
        AccountNumber_field = new JTextField();
        AccountNumber_field.setFont(new Font("Serif", Font.PLAIN, 25));
        AccountNumber_field.setBounds(180,170,150,40);
        AccountPassword_label = new JLabel("Password: ");
        AccountPassword_label.setFont(new Font("Serif", Font.PLAIN, 20));
        AccountPassword_label.setBounds(60,220,200,20);
        AccountPassword_field = new JPasswordField(10);
        AccountPassword_field.setFont(new Font("Serif", Font.PLAIN, 25));
        AccountPassword_field.setBounds(180,220,150,40);
        // add the component to the JFrame
        mon.add(AccountNumber_label);
        mon.add(AccountNumber_field);
        mon.add(AccountPassword_label);
        mon.add(AccountPassword_field);

        // add the component with the border layout
        add(leftSide, borderLayout.WEST);
        add(rightSide, borderLayout.EAST);
        add(numberPad, borderLayout.SOUTH);
        add(mon, borderLayout.CENTER);
        // register event handler for the keypad number and character
    } // end no-argument StudentSystem constructor


} // end class StudentSystem

