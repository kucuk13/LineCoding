package BM403;

import javax.swing.*;

public class DifferentialManchester {

    public static void draw(JFrame f, String s){
        s = DifferentialManchester.covertToDifferentialManchester(s);
        Manchester.draw(f, s);
    }

    public static String covertToDifferentialManchester(String s){
        String str = "";
        if (s.charAt(0) == '0'){
            str += "1";
        } else {
            str += "0";
        }
        for (int i = 0; i < s.length()-1; i++){
            if(s.charAt(i+1) == '0'){
                if (str.charAt(i) == '0'){
                    str += "1";
                } else {
                    str += "0";
                }
            } else if (s.charAt(i+1) == '1') {
                char ch = str.charAt(i);
                String c = Character.toString(ch);
                str += c;
            }
        }
        return str;
    }

    public static String convertFromDifferentialManchester(String s){
        String str = "";
        if (s.charAt(0) == '0'){
            str += "1";
        } else {
            str += "0";
        }
        for (int i = 0; i < s.length()-1; i++){
            if(s.charAt(i+1) == s.charAt(i)){
                str += "1";
            } else {
                str += "0";
            }
        }
        return str;
    }

}
