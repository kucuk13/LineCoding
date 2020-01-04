package BM403;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class HammingCode extends JFrame {
    JLabel label1, label2, label3;
    JButton button;
    JTextArea textSend;
    static JLabel pictures[] = new JLabel[1400];
    static JLabel labelMessage;
    static JScrollPane scrolltxt;
    static Font header  = new Font(Font.DIALOG, Font.BOLD,  16);
    static Font  textF  = new Font(Font.DIALOG, Font.PLAIN,  12);
    static final String path0 = "0.png";
    static final String path1 = "1.png";
    static final String pathE0 = "e0.png";
    static final String pathE1 = "e1.png";
    static final int x = 30;
    static final int y = 370;

    public HammingCode(String name, int width, int height){
        super(name);
        setSize(width, height);
        button = newButton(this, "Gönder", 250, 200);
        label1 = newLabel(this, "Mesaj: ", 50, 50);
        label2 = newLabel(this, "Düzeltilmiş Mesaj: ", 50, 250);
        labelMessage = newLabel(this, "...", 50, 300);
        label3 = newLabel(this, "Alınan Hatalı Mesaj: ", 50, 350);
        textSend = newTextArea(this, 50, 100);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static JLabel newLabel(JFrame f, String content, int x, int y){
        JLabel l = new JLabel(content);
        l.setFont(header);
        l.setBounds(x, y, 200, 40);
        f.add(l);
        l.setVerticalAlignment(JLabel.TOP);
        return l;
    }

    public static JLabel newImage(JFrame f, String path, int x, int y){
        JLabel l = new JLabel();
        l.setIcon(new javax.swing.ImageIcon(path));
        l.setFont(header);
        l.setBounds(x, y, 52, 45);
        f.add(l);
        l.setVerticalAlignment(JLabel.TOP);
        return l;
    }

    public static JTextArea newTextArea(JFrame f, int x, int y){
        JTextArea ta = new JTextArea();
        ta.setFont(textF);
        ta.setBounds(x, y, 500, 80);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                int max = 100;
                if(ta.getText().length() > max+1) {
                    e.consume();
                    String shortened = ta.getText().substring(0, max);
                    ta.setText(shortened);
                } else if(ta.getText().length() > max) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
        f.add(ta);
        scrolltxt=new JScrollPane(ta);
        scrolltxt.setBounds(x,y,500,80);
        f.add(scrolltxt);
        return ta;
    }

    public JButton newButton(JFrame f, String content, int x, int y){
        JButton b = new JButton(content);
        b.setFont(textF);
        b.setBounds(x, y, 75, 30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textSend.getText();
                String binaryMessage = convertBinaryMessage(message);
                String codeword = convertCodeword(binaryMessage);
                String errorMessage = makeErrorMessage(f, codeword);
                String fixedBitMessage = fixMessageWithHammingCode(errorMessage);
                System.out.println("Mesaj       : " + message);
                System.out.println("Bit         : " + binaryMessage);
                System.out.println("Codeword    : " + codeword);
                System.out.println("Hatalı      : " + errorMessage);
                System.out.println("Düzeltilmiş : " + fixedBitMessage);
                findMessage(fixedBitMessage);
            }
        });
        f.add(b);
        return b;
    }

    public static String convertBinaryMessage(String s){
        String str = Ascii.StringToAscii(s);
        return str;
    }

    public static String convertCodeword(String s){
        String str = "";
        for (int i = 0; i < s.length(); i += 4){
            String dataWord = s.substring(i, i+4);
            int a3 = convertToInteger(dataWord.charAt(0));
            int a2 = convertToInteger(dataWord.charAt(1));
            int a1 = convertToInteger(dataWord.charAt(2));
            int a0 = convertToInteger(dataWord.charAt(3));
            int r2 = a0 ^ a1 ^ a3;
            int r1 = a1 ^ a2 ^ a3;
            int r0 = a0 ^ a1 ^ a2;
            str += dataWord + r2 + "" + r1 + "" + r0;
        }
        return str;
    }

    public static String convertToDataWord(String codeword){
        switch (codeword){
            case "0000000": return "0000";
            case "0001101": return "0001";
            case "0010111": return "0010";
            case "0011010": return "0011";
            case "0100011": return "0100";
            case "0101110": return "0101";
            case "0110100": return "0110";
            case "0111001": return "0111";
            case "1000110": return "1000";
            case "1001011": return "1001";
            case "1010001": return "1010";
            case "1011100": return "1011";
            case "1100101": return "1100";
            case "1101000": return "1101";
            case "1110011": return "1110";
            case "1111111": return "1111";
        }
        return "0000";
    }

    public static String changeBitReverse(String s, int i){
        StringBuilder sb = new StringBuilder(s);
        if(s.charAt(i) == '0'){
            sb.setCharAt(i, '1');
        } else {
            sb.setCharAt(i, '0');
        }
        s = sb.toString();
        return s;
    }

    public static String makeErrorMessage(JFrame jf, String s){
        Random r = new Random();
        int randomIndex;
        int tempX = x;
        int tempY = y;
        int i = 0;
        while (pictures[i] != null && i < 1400){
            jf.remove(pictures[i]);
            i++;
        }
        for (i = 0; i < s.length(); i += 7){       //4
             randomIndex = r.nextInt(6);   //3
             if (i + randomIndex < s.length()){
                s = changeBitReverse(s, i+randomIndex);
            }
             for (int j = i; j < s.length() && (j < (i + 7)); j++){   //4
                 if (j == i+randomIndex){
                     if (s.charAt(j) == '0')
                         pictures[j] = newImage(jf, pathE0, tempX, tempY);
                     else
                         pictures[j] = newImage(jf, pathE1, tempX, tempY);
                 } else {
                     if (s.charAt(j) == '0')
                         pictures[j]= newImage(jf, path0, tempX, tempY);
                     else
                         pictures[j]= newImage(jf, path1, tempX, tempY);
                 }
                 tempX += 15;
             }
             tempX = x;
             tempY += 40;
        }
        jf.setVisible(false);
        jf.setVisible(true);
        return s;
    }

    public static int convertToInteger(char c){
        return Integer.parseInt(Character.toString(c));
    }

    public static String fixMessageWithHammingCode(String string){
        String dataword = "";
        for (int i = 0; i < string.length(); i += 7){
            String s = string.substring(i, i + 7);
            int b3 = convertToInteger(s.charAt(0));
            int b2 = convertToInteger(s.charAt(1));
            int b1 = convertToInteger(s.charAt(2));
            int b0 = convertToInteger(s.charAt(3));
            int q2 = convertToInteger(s.charAt(4));
            int q1 = convertToInteger(s.charAt(5));
            int q0 = convertToInteger(s.charAt(6));
            int s2 = b3 ^ b1 ^ b0 ^ q2;
            int s1 = b3 ^ b2 ^ b1 ^ q1;
            int s0 = b2 ^ b1 ^ b0 ^ q0;
            String s2s1s0 = s2 + "" + s1 + "" + s0;
            String codeword = "";
            switch (s2s1s0){
                case "000": codeword = s; break;
                case "001": codeword = changeBitReverse(s, 6); break;
                case "010": codeword = changeBitReverse(s, 5); break;
                case "011": codeword = changeBitReverse(s, 1); break;
                case "100": codeword = changeBitReverse(s, 4); break;
                case "101": codeword = changeBitReverse(s, 3); break;
                case "110": codeword = changeBitReverse(s, 0); break;
                case "111": codeword = changeBitReverse(s, 2); break;
            }
            dataword += convertToDataWord(codeword);
        }
        return dataword;
    }

    public static void findMessage(String s){
        String str = "";
        for (int i = 0; i < s.length(); i += 8){
            int charCode = Integer.parseInt(s.substring(i, i+8), 2);
            str += Character.toString((char) charCode);
        }
        labelMessage.setText(str);
    }

    public static void main(String args[]){
        HammingCode f = new HammingCode("Hamming Code", 1200, 900);
    }

}
