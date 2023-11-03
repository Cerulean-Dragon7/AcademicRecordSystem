import java.awt.*;
import javax.swing.*;
public class GUI
{
    public static void main(String[] args)
    {
        StudentSystem theStudentSystem = new StudentSystem();
        //specifies the width and height of the window in pixels
        theStudentSystem.setSize(800,700);
        theStudentSystem.setResizable(false); // can't change the size of the window
        //the program terminate when the use press the close button
        theStudentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theStudentSystem.setVisible(true); // Display frame
    }
}

