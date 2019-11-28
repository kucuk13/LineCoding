package BM403;

import java.util.Arrays;

public class Ascii {

    public static String StringToAscii (String s){
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        String str = binary.toString().replaceAll(" ", "");
        return str;
    }

    public static String AsciiToString (String s){
        int charCode = Integer.parseInt(s, 2);
        String str = new Character((char)charCode).toString();
        return str;
    }

}
