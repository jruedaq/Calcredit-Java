package logic;

import javax.swing.JOptionPane;

public class Logic {
    
    private static int y, a, b, c, x;
    
    public static void main(String[] args) {
        
        try {
            y = Integer.parseInt(JOptionPane.showInputDialog("Imgrese valor de Y"));
            a = Integer.parseInt(JOptionPane.showInputDialog("Imgrese valor de A"));
            b = Integer.parseInt(JOptionPane.showInputDialog("Imgrese valor de B"));
            c = Integer.parseInt(JOptionPane.showInputDialog("Imgrese valor de C"));
        
            if (y<a && (a<b && b<c)) {
                x = 0;
            } else if (a<=y && y<b) {
                x = 1;
            } else if (b<=y && y<c) {
                x = 2;
            } else if (c<=y) {
                x = 3;
            } else {
                x = 4;
            }
            JOptionPane.showMessageDialog(null, "X vale " + x);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Se ingresaron datos no validos");
        }
    }
    
}
