package BM403;

import javax.swing.*;

public class AMI {

    static boolean isOdd;

    public static void draw(JFrame f, String s){
        isOdd = true;
        int temp = Frame.x;
        int k = 0;
        while (Frame.pictures[k] != null && k < 160){
            f.remove(Frame.pictures[k]);
            k++;
        }
        Frame.pictures[0] = Frame.newLine(Frame.pathLine, Frame.x-1);
        f.add(Frame.pictures[0]);
        int j = 1;
        for (int i = 0; i < s.length(); i++){
            if (i % (Integer.parseInt(Frame.tf_dataRate.getText())) == 0 && i != 0){
                Frame.pictures[j] = Frame.newLine(Frame.pathDashedLine, temp);
                temp += 2;
                f.add(Frame.pictures[j]);
                j++;
            }
            if (s.charAt(i) == '1'){
                if (isOdd){
                    Frame.pictures[j] = Frame.newPicture(Frame.pathPlus1, temp);
                    isOdd = false;
                } else {
                    Frame.pictures[j] = Frame.newPicture(Frame.pathMinus1, temp);
                    isOdd = true;
                }
            } else if (s.charAt(i) == '0'){
                Frame.pictures[j] = Frame.newPicture(Frame.pathZero, temp);
            }
            temp += 20;
            f.add(Frame.pictures[j]);
            j++;
        }
        f.setVisible(false);
        f.setVisible(true);
    }

}
