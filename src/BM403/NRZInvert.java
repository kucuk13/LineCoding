package BM403;

import javax.swing.*;

public class NRZInvert {

    public static String covertToNRZInvert(String s){
        String str = "";
        if (s.charAt(0) == '0'){
            str += "1";
        } else {
            str += "0";
        }
        for (int i = 0; i < s.length()-1; i++){
            if(s.charAt(i+1) == '1'){
                if (str.charAt(i) == '0'){
                    str += "1";
                } else {
                    str += "0";
                }
            } else if (s.charAt(i+1) == '0') {
                char ch = str.charAt(i);
                String c = Character.toString(ch);
                str += c;
            }
        }
        return str;
    }

    public static void draw (JFrame f, String s){
        s = NRZInvert.covertToNRZInvert(s);
        NRZLevel.draw(f, s);
    }
}
