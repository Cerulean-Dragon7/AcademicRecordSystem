import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
    private JLabel userName;
    private JButton profile;
    private JButton logout;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel logoutPanel;
    private JPanel coursePanel;
    public MainPanel(){
        //check the user access level to show difference panel
        initBaseComponent();
        getUserView(2);
    }

    private void initBaseComponent(){   //create the header
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setSize(800,100);   //set the panel size

        //show the username
        userName = new JLabel("User Name");
        userName.setPreferredSize(new Dimension(100,30));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(userName);

        //profile button
        profile = new JButton("Profile");
        profile.setPreferredSize(new Dimension(100,30));

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(profile);

        //header panel show username and button
        headerPanel.add(leftPanel,BorderLayout.WEST);
        headerPanel.add(rightPanel,BorderLayout.EAST);
        headerPanel.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));

        //logout button
        logout = new JButton("logout");
        logout.setPreferredSize(new Dimension(70,20));

        logout.addActionListener(new ActionListener() {
            //go to login screen
            //disconnect to db
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(MainPanel.this);
                guiFrame.changePanel(GUIFrame.LOGINPANEL);
            }
        });

        logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setSize(800,50);
        logoutPanel.setBorder(new MatteBorder(1,0,0,0,Color.BLACK));
        logoutPanel.add(logout);



        this.add(headerPanel,BorderLayout.NORTH);   //add header panel to the bottom panel
        this.add(logoutPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getUserView(int user){
        if(user == 0){
            studentPanel();
        }
        if(user == 1){
            teacherPanel();
        }
        if(user == 2){
            adminPanel();
        }
    }

    private void teacherPanel(){
        //show the course title
        JLabel courseTitle = new JLabel("My course");
        courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(courseTitle,BorderLayout.NORTH);

        //show the student title
        JLabel studentTitle = new JLabel("Student");
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(studentTitle,BorderLayout.NORTH);


        //create panel of the course
        JPanel bottomPanel = new JPanel(new FlowLayout());


        JPanel coursePanel = new JPanel(); //show course as vertical
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));


        //add the student all course panel
        for(int i = 0;i < 6;i++) {
            int finalI = i;
            JLabel course = new JLabel();
            String courseTextFormat = "<html><font size ='4'> Course " + finalI + "</font></html>";
            coursePanel.add(Box.createRigidArea(new Dimension(0,1)));

            course.setText(courseTextFormat);
            course.setMinimumSize(new Dimension(200,300));
            course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            course.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    String courseName = "Course: ";
                    String courseGrade = "this is " + finalI + " label";
                    JOptionPane.showMessageDialog(MainPanel.this, courseGrade, courseName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                }
            });
            coursePanel.add(course);
        }

        JScrollPane courseScrollPanel = new JScrollPane(coursePanel); //let the panel can scroll
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        courseScrollPanel.setPreferredSize(new Dimension(300,570));

        //crate all student panel
        //show to list of student witch in the course
        JPanel studentListPanel = new JPanel(); //show student in course as vertical
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));

        //add the student all course panel
        for(int i = 0;i < 6;i++) {
            int finalI = i;
            JLabel student = new JLabel();
            String studentTextFormat = "<html><font size ='4'> Student " + finalI + "</font></html>";
            studentListPanel.add(Box.createRigidArea(new Dimension(0,1)));

            student.setText(studentTextFormat);
            student.setMinimumSize(new Dimension(200,300));
            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            student.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //teacher can modify student grade in the dialog
                    String studentName = "student: ";
                    String studentCourseGrade = "this is " + finalI + " label";
                    JOptionPane.showMessageDialog(MainPanel.this, studentCourseGrade, studentName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                }
            });
            studentListPanel.add(student);
        }

        JScrollPane studentScrollPanel = new JScrollPane(studentListPanel); //let the panel can scroll
        studentScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        studentScrollPanel.setPreferredSize(new Dimension(300,570));

        leftPanel.add(courseScrollPanel);
        rightPanel.add(studentScrollPanel);

        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        this.add(bottomPanel,BorderLayout.CENTER);
    }

    private void studentPanel(){
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JLabel courseTitle = new JLabel("My Course");
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        courseTitle.setBackground(Color.GREEN);
        courseTitle.setPreferredSize(new Dimension(200,20));

        JPanel coursePanel = new JPanel(); //show course as vertical
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));


            //add the course all course panel
            for(int i = 0;i < 6;i++) {
                int finalI = i;
                JLabel course = new JLabel();
                String courseTextFormat = "<html><font size ='4'> Course " + finalI + "</font></html>";

                course.setText(courseTextFormat);
                course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                coursePanel.add(Box.createRigidArea(new Dimension(0,5)));


                course.addMouseListener(new MouseAdapter() { //click the course to see more detail
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        String courseName = "Course: ";
                        String courseGrade = "this is " + finalI + " label";
                        JOptionPane.showMessageDialog(MainPanel.this, courseGrade, courseName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                    }
                });
                coursePanel.add(course);
            }

        JScrollPane courseScrollPanel = new JScrollPane(coursePanel); //let the panel can scroll
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        courseScrollPanel.setPreferredSize(new Dimension(300,570));

        bottomPanel.add(courseTitle,BorderLayout.NORTH);
        bottomPanel.add(courseScrollPanel,BorderLayout.CENTER);

        this.add(bottomPanel,BorderLayout.CENTER);
    }

    private void adminPanel(){
        //course title
        JLabel courseTitle = new JLabel("My course");
        courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(courseTitle,BorderLayout.NORTH);
        leftPanel.setMinimumSize(new Dimension(300,500));


        //teacher title
        JLabel teacherTitle = new JLabel("Teacher");
        teacherTitle.setHorizontalAlignment(SwingConstants.CENTER);
        teacherTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(teacherTitle,BorderLayout.NORTH);

        //student title
        JLabel studentTitle = new JLabel("Student");
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(studentTitle,BorderLayout.NORTH);

        //panel contain course, teacher, student
        JPanel bottomPanel = new JPanel(new FlowLayout());


        JPanel coursePanel = new JPanel(); //show course as vertical
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));


        //add the student all course panel
        for(int i = 0;i < 6;i++) {
            int finalI = i;
            JLabel course = new JLabel();
            String courseTextFormat = "<html><font size ='4'> Course " + finalI + "</font></html>";
            coursePanel.add(Box.createRigidArea(new Dimension(0,1)));

            course.setText(courseTextFormat);
            course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            course.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    String courseName = "Course: ";
                    String courseGrade = "this is " + finalI + " label";
                    JOptionPane.showMessageDialog(MainPanel.this, courseGrade, courseName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                }
            });
            coursePanel.add(course);
            coursePanel.setMinimumSize(new Dimension(300,500));
        }

        JScrollPane courseScrollPanel = new JScrollPane(coursePanel); //let the panel can scroll
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //crate all student panel
        //show to list of student witch in the course
        JPanel studentListPanel = new JPanel(); //show student in course as vertical
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));

        //add the student all course panel
        for(int i = 0;i < 6;i++) {
            int finalI = i;
            JLabel student = new JLabel();
            String studentTextFormat = "<html><font size ='4'> Student " + finalI + "</font></html>";
            studentListPanel.add(Box.createRigidArea(new Dimension(0,1)));

            student.setText(studentTextFormat);
            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            student.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //teacher can modify student grade in the dialog
                    String studentName = "student: ";
                    String studentCourseGrade = "this is " + finalI + " label";
                    JOptionPane.showMessageDialog(MainPanel.this, studentCourseGrade, studentName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                }
            });
            studentListPanel.add(student);
        }

        JScrollPane studentScrollPanel = new JScrollPane(studentListPanel); //let the panel can scroll
        studentScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel teacherPanel = new JPanel(); //show student in course as vertical
        teacherPanel.setLayout(new BoxLayout(teacherPanel, BoxLayout.Y_AXIS));

        //add the student all course panel
        for(int i = 0;i < 6;i++) {
            int finalI = i;
            JLabel student = new JLabel();
            String studentTextFormat = "<html><font size ='4'> Teacher " + finalI + "</font></html>";
            teacherPanel.add(Box.createRigidArea(new Dimension(0,1)));

            student.setText(studentTextFormat);
            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            student.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //teacher can modify student grade in the dialog
                    String studentName = "Teacher: ";
                    String studentCourseGrade = "teacher";
                    JOptionPane.showMessageDialog(MainPanel.this, studentCourseGrade, studentName, JOptionPane.PLAIN_MESSAGE);//the course score will in this dialog

                }
            });
            teacherPanel.add(student);
        }

        JScrollPane teacherScrollPanel = new JScrollPane(teacherPanel); //let the panel can scroll
        teacherScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.add(courseScrollPanel);
        middlePanel.add(teacherScrollPanel);
        rightPanel.add(studentScrollPanel);

        leftPanel.setPreferredSize(new Dimension(250,620));
        middlePanel.setPreferredSize(new Dimension(250,620));
        rightPanel.setPreferredSize(new Dimension(250,620));

        bottomPanel.add(leftPanel);
        bottomPanel.add(middlePanel);
        bottomPanel.add(rightPanel);


        this.add(bottomPanel,BorderLayout.CENTER);
    }
}
