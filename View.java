import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

/* The View class give the user the options to interact with the functions of the controller class
through a GUI, it takes the user's input through text fields, chooses which function through buttons, and outputs to lists and labels.
*/
class View implements ActionListener {
    //lets the functions and variables of other classes be used
    static Controller C1 = new Controller();
    Model M1 = new Model();
    //initializes variables needed for actionListener
    static JTextField ta1;
    static JTextField ta2;
    static JTextField ta3;
    static JTextField ta4;
    static JTextField ta5;
    static JTextField ta6;
    static JTextField tr1;
    static JTextField tr2;
    static JLabel w1;
    static JList<Object> l1;
    static DefaultListModel<Object> listModel;

    public static void main(String[] args) {
        //creates base frame
        JFrame f1 = new JFrame("MDS");
        f1.setSize(900, 900);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creates panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        JPanel p10 = new JPanel();
        //creates panel for list
        JScrollPane sc1 = new JScrollPane();
        //adds panel to frame
        f1.add(p1);
        //sets panel layout
        p1.setLayout(new GridLayout(2, 1));
        //adds panel on to other panel
        p1.add(p2);
        p1.add(p3);
        p2.setLayout(new GridLayout(1, 2));
        p2.add(p4);
        p2.add(p5);
        p3.setLayout(new GridLayout(1, 5));
        p3.add(p6);
        p3.add(p7);
        p3.add(p8);
        p3.add(p9);
        p3.add(p10);

        //makes list
        listModel = new DefaultListModel<>();
        l1 = new JList<>(listModel);
        //creates label to display bmi
        w1 = new JLabel("");
        p2.add(w1);
        //adds list
        p2.add(sc1);
        //sets list format
        sc1.setViewportView(l1);

        //creates buttons
        JButton b1 = new JButton("Add Manual");
        //makes sure button is big enough to prevent labels from sharing the same line
        b1.setPreferredSize(new Dimension(160, 27));
        JButton b2 = new JButton("Add File");
        JButton b3 = new JButton("Remove Person");
        b3.setPreferredSize(new Dimension(160, 27));
        JButton b4 = new JButton("Edit Person");
        JButton b5 = new JButton("Get BMI");

        //adds button
        p6.add(b1);
        //sets up text fields to take user input
        ta1 = new JTextField(16);
        ta2 = new JTextField(16);
        ta3 = new JTextField(16);
        ta4 = new JTextField(16);
        ta5 = new JTextField(16);
        ta6 = new JTextField(16);
        //makes labels to describe text fields
        JLabel va = new JLabel("Name");
        JLabel vb = new JLabel("Blood Type");
        JLabel vc = new JLabel("Age");
        JLabel vd = new JLabel("Blood Pressure");
        JLabel ve = new JLabel("Height");
        JLabel vf = new JLabel("Weight");

        //adds labels and text fields
        p6.add(va);
        p6.add(ta1);
        p6.add(vb);
        p6.add(ta2);
        p6.add(vc);
        p6.add(ta3);
        p6.add(vd);
        p6.add(ta4);
        p6.add(ve);
        p6.add(ta5);
        p6.add(vf);
        p6.add(ta6);

        p7.add(b2);

        p8.add(b3);
        //fields for finding person
        tr1 = new JTextField(16);
        tr2 = new JTextField(16);
        //labels text fields
        JLabel v2 = new JLabel("Name");
        JLabel v3 = new JLabel("Blood Type");
        //adds labels and text fields
        p8.add(v2);
        p8.add(tr1);
        p8.add(v3);
        p8.add(tr2);

        p9.add(b4);
        p10.add(b5);

        //adds actionListener
        View aL = new View();

        //adds actionListener to buttons
        b1.addActionListener(aL);
        b2.addActionListener(aL);
        b3.addActionListener(aL);
        b4.addActionListener(aL);
        b5.addActionListener(aL);

        //sets GUI
        f1.setVisible(true);

        try {
            //Allows access to database
            Connection con = DriverManager.getConnection(C1.url);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(C1.query);
            //iterates through database
            while (resultSet.next()) {
                //Sets all columns
                String ln = resultSet.getString(1);
                String lt = resultSet.getString(2);
                int la = resultSet.getInt(3);
                float lp = resultSet.getFloat(4);
                float lh = resultSet.getFloat(5);
                float lw = resultSet.getFloat(6);

                //Puts items in list
                List<Object> Hol = List.of(ln, lt, la, lp, lh, lw);
                //Adds database item to list
                listModel.addElement(Hol);
            }
        }
        catch (SQLException s) {}
    }

    //defines actionListener
    public void actionPerformed(ActionEvent e) {

        //gets button name
        String act = e.getActionCommand();
        //checks button name
        if (act.equals("Add Manual")) {
            try {
                //calls addManual function with values taken from text fields
                C1.addManual(ta1.getText(), ta2.getText(), Integer.parseInt(ta3.getText()), Float.parseFloat(ta4.getText()), Float.parseFloat(ta5.getText()), Float.parseFloat(ta6.getText()));
                //empties list
                listModel.clear();
                try {
                    //Allows access to database
                    Connection con = DriverManager.getConnection(C1.url);
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(C1.query);
                    //iterates through database
                    while (resultSet.next()) {
                        //Sets all columns
                        String ln = resultSet.getString(1);
                        String lt = resultSet.getString(2);
                        int la = resultSet.getInt(3);
                        float lp = resultSet.getFloat(4);
                        float lh = resultSet.getFloat(5);
                        float lw = resultSet.getFloat(6);

                        //Puts items in list
                        List<Object> Hol = List.of(ln, lt, la, lp, lh, lw);
                        //Adds database item to list
                        listModel.addElement(Hol);
                    }
                }
                catch (SQLException s) {}
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            //empties text fields
            ta1.setText("");
            ta2.setText("");
            ta3.setText("");
            ta4.setText("");
            ta5.setText("");
            ta6.setText("");
        } else if (act.equals("Add File")) {
            //sets up JFileChooser
            JFileChooser jc = new JFileChooser();

            jc.showOpenDialog(null);

            //calls addFile with file taken from JFileChooser
            C1.addFile(jc.getSelectedFile());

            //clears list
            listModel.clear();

            try {
                //Allows access to database
                Connection con = DriverManager.getConnection(C1.url);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(C1.query);
                //iterates through database
                while (resultSet.next()) {
                    //Sets all columns
                    String ln = resultSet.getString(1);
                    String lt = resultSet.getString(2);
                    int la = resultSet.getInt(3);
                    float lp = resultSet.getFloat(4);
                    float lh = resultSet.getFloat(5);
                    float lw = resultSet.getFloat(6);

                    //Puts items in list
                    List<Object> Hol = List.of(ln, lt, la, lp, lh, lw);
                    //Adds database item to list
                    listModel.addElement(Hol);
                }
            }
            catch (SQLException s) {}
        } else if (act.equals("Remove Person")) {
            //calls removePerson with location taken from text fields
            C1.removePerson(tr1.getText(), tr2.getText());
            //clears list
            listModel.clear();
            try {
                //Allows access to database
                Connection con = DriverManager.getConnection(C1.url);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(C1.query);
                //iterates through database
                while (resultSet.next()) {
                    //Sets all columns
                    String ln = resultSet.getString(1);
                    String lt = resultSet.getString(2);
                    int la = resultSet.getInt(3);
                    float lp = resultSet.getFloat(4);
                    float lh = resultSet.getFloat(5);
                    float lw = resultSet.getFloat(6);

                    //Puts items in list
                    List<Object> Hol = List.of(ln, lt, la, lp, lh, lw);
                    //Adds database item to list
                    listModel.addElement(Hol);
                }
            }
            catch (SQLException s) {}
            //clears location text fields
            tr1.setText("");
            tr2.setText("");
        } else if (act.equals("Get BMI")) {
            //calls getBMI with text field location
            C1.getBMI(tr1.getText(), tr2.getText());

            //sets label with result
            w1.setText(String.valueOf(M1.bmi));

            //clears text fields
            tr1.setText("");
            tr2.setText("");
        } else if (act.equals("Edit Person")) {
            try {
                //calls editPerson with location and values from text fields
                C1.editPerson(tr1.getText(), tr2.getText(), ta1.getText(), ta2.getText(), ta3.getText(), ta4.getText(), ta5.getText(), ta6.getText());
                //empties list
                listModel.clear();
                try {
                    //Allows access to database
                    Connection con = DriverManager.getConnection(C1.url);
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(C1.query);
                    //iterates through database
                    while (resultSet.next()) {
                        //Sets all columns
                        String ln = resultSet.getString(1);
                        String lt = resultSet.getString(2);
                        int la = resultSet.getInt(3);
                        float lp = resultSet.getFloat(4);
                        float lh = resultSet.getFloat(5);
                        float lw = resultSet.getFloat(6);

                        //Puts items in list
                        List<Object> Hol = List.of(ln, lt, la, lp, lh, lw);
                        //Adds database item to list
                        listModel.addElement(Hol);
                    }
                }
                catch (SQLException s) {}
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            //clears all text fields
            tr1.setText("");
            tr2.setText("");
            ta1.setText("");
            ta2.setText("");
            ta3.setText("");
            ta4.setText("");
            ta5.setText("");
            ta6.setText("");
        }
    }
}