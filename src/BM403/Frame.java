package BM403;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    JLabel label1, label2, label3, label4, label5, label6;
    static JTextField tf_dataRate, tf_text;
    JComboBox comboBox1;
    JButton button;
    static JLabel[] pictures = new JLabel[160];
    static String pathPlus1, pathZero, pathMinus1, pathLine, pathDashedLine, pathM0, pathM1;
    static final int x = 50;
    static final int y = 50;
    static final int w = 135;
    static final int h = 30;
    static int dataRate = 3;

    public Frame(String name, int width, int height){
        super(name);
        setSize(width, height);
        pathPlus1 = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\+1.png";
        pathZero = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\0.png";
        pathMinus1 = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\-1.png";
        pathLine = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\line.png";
        pathDashedLine = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\dashed line.png";
        pathM0 = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\M0.png";
        pathM1 = "C:\\Users\\KAAN KÜÇÜK\\Desktop\\M1.png";
        label1 = newLabel("Data Rate:", x, y);
        tf_dataRate = newTextField(x+150, y);
        label2 = newLabel("Line Coding Yöntemi:", x, y+50);
        comboBox1 = newComboBox( x+150, y+50);
        label3 = newLabel("Text:", x, y+100);
        tf_text = newTextField(x+150, y+100);
        button =  newButton(this, "Çevir", x+100, y+150);
        label4 = newResultLabel("Text:", x, y+200);
        label5 = newResultLabel("Bit Dizisi:", x, y+240);
        label6 = newResultLabel("", x, y+330);
        add(button);
        add(tf_text);
        add(tf_dataRate);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(comboBox1);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton newButton(JFrame f, String content, int x, int y){
        JButton b = new JButton(content);
        b.setBounds(x, y, Frame.w, Frame.h);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = tf_text.getText();
                label4.setText("Text: " + s);
                s = Ascii.StringToAscii(s);
                label5.setText("Bit Dizisi: " + s);
                if (comboBox1.getSelectedIndex() == 0){
                    label6.setText("Unipolar NRZ: " + s);
                    Unipolar.draw(f, s);
                } else if (comboBox1.getSelectedIndex() == 1){
                    label6.setText("NRZ Level: " + NRZLevel.covertToNRZLevel(s));
                    NRZLevel.draw(f, s);
                } else if (comboBox1.getSelectedIndex() == 2){
                    label6.setText("NRZ Invert: " + NRZInvert.covertToNRZInvert(s));
                    NRZLevel.draw(f, s);
                } else if (comboBox1.getSelectedIndex() == 3){
                    label6.setText("Manchester: " + s);
                    Manchester.draw(f, s);
                } else if (comboBox1.getSelectedIndex() == 4){
                    label6.setText("Differential Manchester: " + DifferentialManchester.covertToDifferentialManchester(s));
                    DifferentialManchester.draw(f, s);
                } else if (comboBox1.getSelectedIndex() == 5){
                    label6.setText("AMI: " + s);
                    AMI.draw(f, s);
                }
            }
        });
        return b;
    }

    public static JLabel newPicture(String path, int x) {
        JLabel p = new JLabel();
        p.setIcon(new javax.swing.ImageIcon(path));
        p.setSize(20, 41);
        p.setLocation(x, y+280);
        return p;
    }

    public static JLabel newLine(String path, int x) {
        JLabel p = new JLabel();
        p.setIcon(new javax.swing.ImageIcon(path));
        p.setSize(2, 41);
        p.setLocation(x, y+280);
        return p;
    }

    public static JLabel newLabel(String content, int x, int y){
        JLabel l = new JLabel(content);
        l.setBounds(x, y, w, h);
        return l;
    }

    public static JLabel newResultLabel(String content, int x, int y){
        JLabel l = new JLabel(content);
        l.setBounds(x, y, 1000, h);
        return l;
    }

    public static JTextField newTextField(int x, int y){
        JTextField tf = new JTextField("");
        tf.setBounds(x, y, w, h);
        return tf;
    }

    public static JComboBox newComboBox (int x, int y){
        String[] methods = {"Unipolar NRZ", "NRZ Level", "NRZ Invert", "Manchester", "Differential Manchester", "AMI"};
        JComboBox cb = new JComboBox(methods);
        cb.setBounds(x, y, w, h);
        return cb;
    }

}
