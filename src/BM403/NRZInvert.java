package BM403;

import javax.swing.*;

public class NRZInvert {

    public static void draw (JFrame f, String s){
        s = covertToNRZInvert(s);
        s = NRZLevel.covertToNRZLevel(s);
        NRZLevel.draw(f, s);
    }

    public static String covertToNRZInvert(String s){
        String str = "";
        if (s.charAt(0) == '0'){
            str += "0";
        } else {
            str += "1";
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

    public static String convertFromNRZInvert(String s){
        String str = "";
        if (s.charAt(0) == '0'){
            str += "0";
        } else {
            str += "1";
        }
        for (int i = 0; i < s.length()-1; i++){
            if(s.charAt(i+1) == s.charAt(i)){
                str += "0";
            } else {
                str += "1";
            }
        }
        return str;
    }
}
