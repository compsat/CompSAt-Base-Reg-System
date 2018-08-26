//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Arrays;
import java.awt.Font;
import java.io.File;
import java.awt.GraphicsEnvironment;

public class AttendanceSystem extends JFrame {
    Field idNumber;
    Field nickname;
    Field year;
    Field lastName;
    Field course;
    Field email;
    Field firstName;
    Field middleInitial;
    Field mobileNumber;
    Field birthMonth;
    Field birthDay;
    Field birthYear;

    ButtonGroup membershipStatus;
    JRadioButton oldMember;
    JRadioButton newMember;
    ButtonGroup scholarshipStatus;
    JRadioButton isScholar;
    JRadioButton notScholar;
    ArrayList<Field> fields;

    HashMap<String, String[]> oldMembers;
    SchoolSorter sorter;

    public AttendanceSystem() {
        setUpOldMemberDictionary();

        setWindowSize();
        setContentPane(new JLabel(new ImageIcon("background.png")));

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AttendanceSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        fields = new ArrayList<Field>();
        addFields ();
        addRadioButtons ();
        addSubmitButton ();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        repaint();

        EventQueue.invokeLater(new Runnable() {

           @Override
             public void run() {
                 idNumber.grabFocus();
                 idNumber.requestFocus();//or inWindow
             }
        });

        sorter = new SchoolSorter();

        // Disable Maximizing
        setResizable (false);
        setMaximizedBounds(new Rectangle(0, 0));
            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(final WindowEvent e) {
                    if (e.getNewState() == MAXIMIZED_BOTH) {
                        setExtendedState(NORMAL);
                    }
                }
            });

        System.out.println ("Set-up completed!");
    }

    private void setUpOldMemberDictionary() {
        oldMembers = new HashMap<String, String[]>();
        BufferedReader br = null;

		try {
			String currentLine;
			br = new BufferedReader(new FileReader("oldmembers.csv"));

			while ((currentLine = br.readLine()) != null) {
				String[] rawData = currentLine.split(",");
                String[] data = Arrays.copyOfRange(rawData, 1, rawData.length);
                oldMembers.put(rawData[0], data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        System.out.println ("Successfully imported old members!");
    }

    private void addFields () {
        // Text Fields
        // Construct Components
        idNumber = new Field (6);
        nickname = new Field (20);
        year = new Field (1);
        lastName = new Field ("Last Name", 20);
        course = new Field ("BS CS", 10);
        email = new Field (40);
        firstName = new Field ("First Name", 20);
        middleInitial = new Field ("MI", 1);
        mobileNumber = new Field ("eg. 09171234567", 11);
        birthMonth = new Field ("MM", 2);
        birthDay = new Field (" DD", 2);
        birthYear = new Field ("YYYY", 4);

        // Add Text Fields to ArrayList
        fields.add (idNumber);
        fields.add (nickname);
        fields.add (year);
        fields.add (lastName);
        fields.add (course);
        fields.add (email);
        fields.add (firstName);
        fields.add (middleInitial);
        fields.add (mobileNumber);
        fields.add (birthMonth);
        fields.add (birthDay);
        fields.add (birthYear);

        for (Field text_field : fields) {
            add (text_field);
        }

        // Cross-Platform Compatibility
        if (System.getProperty("os.name").contains("Windows")) {
            idNumber.setBounds (147, 170, 90, 35);
            nickname.setBounds (147, 260, 155, 35);
            year.setBounds (374, 260, 40, 35);
            lastName.setBounds (147, 350, 290, 35);
            course.setBounds (448, 260, 100, 35);
            email.setBounds (147, 440, 350, 35);
            firstName.setBounds (471, 350, 290, 35);
            middleInitial.setBounds (779, 350, 45, 35);
            mobileNumber.setBounds (616, 440, 200, 35);
            birthMonth.setBounds (616, 260, 50, 35);
            birthDay.setBounds (693, 260, 50, 35);
            birthYear.setBounds (768, 260, 59, 35);
        } else {
            idNumber.setBounds (157, 180, 90, 35);
            nickname.setBounds (157, 270, 155, 35);
            year.setBounds (384, 270, 40, 35);
            lastName.setBounds (157, 360, 290, 35);
            course.setBounds (458, 270, 100, 35);
            email.setBounds (157, 450, 350, 35);
            firstName.setBounds (481, 360, 290, 35);
            middleInitial.setBounds (789, 360, 45, 35);
            mobileNumber.setBounds (626, 450, 200, 35);
            birthMonth.setBounds (626, 270, 50, 35);
            birthDay.setBounds (703, 270, 50, 35);
            birthYear.setBounds (778, 270, 59, 35);
        }

        // Mouse pointer to ID Number
        idNumber.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (idNumber.getText().length() == 6 && oldMembers.containsKey(idNumber.getText())){
                    String[] data = oldMembers.get(idNumber.getText());
                    String[] birthday = data[7].split("/");
                    System.out.println(Arrays.toString(birthday));
                    System.out.println(Arrays.toString(data));

                    lastName.setText(data[0]);
                    firstName.setText(data[1]);
                    middleInitial.setText(data[2].substring(0,1));
                    nickname.setText(data[3]);
                    year.setText(String.valueOf(Integer.parseInt(data[4]) + 1));
                    course.setText(data[5]);
                    birthMonth.setText(birthday[0]);
                    birthDay.setText(birthday[1]);
                    birthYear.setText(birthday[2]);
                    email.setText (data[9]);
                    mobileNumber.setText("0" + data[8]);
                    oldMember.setSelected (true);
                    notScholar.setSelected (true);
                }
            }
        });
    }

    private void addRadioButtons () {
        // Membership Status
        membershipStatus = new ButtonGroup();
        oldMember = new JRadioButton();
        newMember = new JRadioButton();
        membershipStatus.add(oldMember);
        membershipStatus.add(newMember);
        add(oldMember);
        add(newMember);
        oldMember.setContentAreaFilled(false);
        newMember.setContentAreaFilled(false);
        newMember.setSelected (true);

        // Scholarship Status
        scholarshipStatus = new ButtonGroup();
        isScholar = new JRadioButton();
        notScholar = new JRadioButton();
        scholarshipStatus.add(isScholar);
        scholarshipStatus.add(notScholar);
        add(isScholar);
        add(notScholar);
        isScholar.setContentAreaFilled(false);
        notScholar.setContentAreaFilled(false);
        notScholar.setSelected (true);

        // Cross Platform Compatibility
        if (System.getProperty("os.name").contains("Windows")) {
            /*
            oldMember.setBounds (150, 533, 30, 30);
            newMember.setBounds (265, 533, 30, 30);
            isScholar.setBounds (620, 533, 30, 30);
            notScholar.setBounds (738, 533, 30, 30); */
            oldMember.setBounds (160, 540, 30, 30);
            newMember.setBounds (273, 540, 30, 30);
            isScholar.setBounds (630, 540, 30, 30);
            notScholar.setBounds (743, 540, 30, 30);
        } else {
            oldMember.setBounds (160, 543, 30, 30);
            newMember.setBounds (275, 543, 30, 30);
            isScholar.setBounds (630, 543, 30, 30);
            notScholar.setBounds (748, 543, 30, 30);
        }

    }

    private void addSubmitButton () {
        // Submit Button
        try {
             GraphicsEnvironment ge =
                 GraphicsEnvironment.getLocalGraphicsEnvironment();
                 ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")));
        } catch (Exception e) {
             //Handle exception
        }
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = submitButtonClicked();
                switch (result) {
                    case "Success":
                        cleanData();
                        exportData();
                        JOptionPane.showMessageDialog(new JFrame(), "Thank you for registering!", "Success!", JOptionPane.PLAIN_MESSAGE);
                        idNumber.requestFocus();
                        break;
                    case "Incomplete":
                        JOptionPane.showMessageDialog(new JFrame(), "Please fill up all fields.", "Submission Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(new JFrame(), "Please re-enter the following information:\n" + result, "Submission Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
        add (submitButton);
        submitButton.setBackground(Color.decode("#EFEFEF"));
        submitButton.setForeground(Color.decode("#333333"));
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);
        submitButton.setBounds (703, 602, 120, 56);

        getRootPane().setDefaultButton(submitButton);
    }

    private void setWindowSize () {
        int width = 1000;
        int height = 750;

        // Full Screen Settings
        /*
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        */
        //setSize (new Dimension (1000, 750));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        setVisible(true);
        setLayout (null);
    }

    // 0: Success
    // 1: Incomplete Fields
    // 2: Failed Validation
    private String submitButtonClicked () {
        // Check for Empty Fields
        for (Field text_field : fields) {
            if (text_field.getText().length() == 0)
                return "Incomplete";
        }

        // Validate Data
        String incorrect_fields = "";
        // idNumber
        if (!isNumeric(idNumber.getText()) || idNumber.getText().replaceAll("\\s+","").length() != 6)
            incorrect_fields += "ID Number (eg.131356)\n";
        // year
        if (!isNumeric(year.getText()) || year.getText().replaceAll("\\s+","").length() != 1)
            incorrect_fields += "Year Level (eg. 4)\n";
        // email
        if (!email.getText().contains("@") || !email.getText().contains("."))
            incorrect_fields += "E-mail (eg. name@example.com)\n";
        // mobileNumber
        if (!isNumeric(mobileNumber.getText()) || mobileNumber.getText().replaceAll("\\s+","").length() != 11)
            incorrect_fields += "Mobile Number (eg. 09171234567)\n";
        // birthMonth
        if (!isNumeric(birthMonth.getText()))
            incorrect_fields += "Birth Month (eg. 12)\n";
        // birthDay
        if (!isNumeric(birthDay.getText()))
            incorrect_fields += "Birth Day (eg. 04)\n";
        // birthYear
        if (!isNumeric(birthYear.getText()) || birthYear.getText().replaceAll("\\s+","").length() != 4)
            incorrect_fields += "Birth Year (eg. 1994)\n";

        if (!incorrect_fields.isEmpty())
            return incorrect_fields;

        return "Success";
    }

    public static boolean isNumeric(String str)
    {
      return str.matches("^[0-9]+$");  //match a number with optional '-' and decimal.
    }

    public void exportData () {
        // Export data
        try {
            String isOldMember = oldMember.isSelected() ? "YES" : "NO";
            String scholarStatus = isScholar.isSelected() ? "YES" : "NO";

            String data =
                idNumber.getText() + "," +
                lastName.getText() + "," +
                firstName.getText() + "," +
                middleInitial.getText().toUpperCase() + "," +
                nickname.getText() + "," +
                year.getText() + "," +
                course.getText().toUpperCase() + "," +
                sorter.getSchool(course.getText()) + "," +
                birthMonth.getText() + "/" + birthDay.getText() + "/" + birthYear.getText() + "," +
                mobileNumber.getText() + "," +
                email.getText() + "," +
                isOldMember + "," +
                scholarStatus;

                File file = new File("output.csv");
                boolean fileExistedBefore = true;
          			// if file doesnt exists, then create it
          			if (!file.exists()) {
          				    file.createNewFile();
                      fileExistedBefore = false;
          			}

          			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); // boolean: append mode
          			BufferedWriter bw = new BufferedWriter(fw);
                if (!fileExistedBefore) {
                    bw.append("ID Number,Last Name,First Name,MI,Nickname,Year,Course,School,Birthday,Mobile,Email,Old Member?, Scholar?");
                }
                bw.newLine();
          			bw.append(data);
          			bw.close();

            System.out.println ("Registered user!");
            for (Field field : fields) {
                field.setText("");
            }
            newMember.setSelected (true);

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void cleanData () {
        if (birthMonth.getText().replaceAll("\\s+","").length() < 2)
            birthMonth.setText("0" + birthMonth.getText().replaceAll("\\s+",""));

        if (birthDay.getText().replaceAll("\\s+","").length() < 2)
            birthDay.setText("0" + birthDay.getText().replaceAll("\\s+",""));
    }

    public static void main (String[] args) {
        AttendanceSystem r = new AttendanceSystem();
    }
}
