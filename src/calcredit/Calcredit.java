package calcredit;

import javax.swing.JOptionPane;

public class Calcredit {

    private static double ValorCompraTC, Tasa, CuotaManejo, TasaAnual, PagoTotalMailActivity;
    private static String TotalMensualStr, InteresMainActivityStr, ValorCompraSegundoStr, PagoTotalCuotaMAnejoStr;
    private static int Cuotas;
    private static boolean CuotaMBool, TasaABool;
    
    public static void main(String[] args) {
        
        TasaABool  = (0 == JOptionPane.showConfirmDialog(null, "Calculará con la tasa anual?"));
        if (TasaABool) {
            TasaAnual = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa anual"));
        } else {
            Tasa = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa mensual"));
        }
        
        CuotaMBool = (0 == JOptionPane.showConfirmDialog(null, "Calculará con cuota de manejo?"));
        if (CuotaMBool) {
            CuotaManejo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cuota de manejo"));
        } else {
            CuotaManejo = 0;
        }
        
        ValorCompraTC = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Valor de la compra"));
        Cuotas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de cuotas"));
        
        calculoTasa();
        operacion();
    }
    
    private static void errorMessage() {
        JOptionPane.showMessageDialog(null, "Error en la ejecución");
    }
    
    private static void calculoTasa() {

        if (TasaABool) {
            try {
                Tasa = TasaAnual / 12;
            } catch (Exception e){
                errorMessage();
            }
        }
    }
    
    private static void calculoCuotaDeManejo() {
        if (CuotaMBool) {
            PagoTotalCuotaMAnejoStr = String.valueOf(CuotaManejo * Cuotas);
        } else {
            PagoTotalCuotaMAnejoStr = "0";
        }
    }

    private static void operacion() {
        
        try {
            if (Tasa > 99) {
                JOptionPane.showInputDialog("La tasa debe ser inferior a 99");
                return;
            } else {
                double e = Tasa / 100;

                double v1 = 1 + e;
                double v2 = Math.pow(v1, Cuotas);
                double v3 = e * v2;
                double v4 = ValorCompraTC * v3;
                double v5 = v2 - 1;

                double Respuesta = v4 / v5;
                double RespuestaSinCuoMan = v4 / v5;
                if (CuotaMBool) {
                    Respuesta = Respuesta + CuotaManejo;
                }

                if (Respuesta < 1) {
                    JOptionPane.showMessageDialog(null, "La compra es grátis");
                    calculoCuotaDeManejo();
                    InteresMainActivityStr = "0";
                    ValorCompraSegundoStr = "Gratis";

                } else {
                    if (Cuotas == 1) {
                        JOptionPane.showMessageDialog(null, "Total a pagar en el mes " + String.valueOf(ValorCompraTC));
                        JOptionPane.showMessageDialog(null, "Total a pagar del presamo " + String.valueOf(ValorCompraTC));
                        
                        calculoCuotaDeManejo();
                        
                        InteresMainActivityStr = "0";
                        ValorCompraSegundoStr = String.valueOf(ValorCompraTC);
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "Pago total mensual " + String.valueOf(Respuesta));

                        double InteresCalculo = RespuestaSinCuoMan * Cuotas;
                        double InteresesCalculoSinCuoMan = RespuestaSinCuoMan * Cuotas;
                        if (CuotaMBool) {
                            InteresCalculo = InteresCalculo + CuotaManejo * Cuotas;
                        }

                        JOptionPane.showMessageDialog(null, "Pago total " + String.valueOf(InteresCalculo));
                        calculoCuotaDeManejo();
                        JOptionPane.showMessageDialog(null, "Pago total en intereses " + String.valueOf(InteresesCalculoSinCuoMan - ValorCompraTC));
                        ValorCompraSegundoStr = String.valueOf(ValorCompraTC);
                    }
                }
            }
        } catch (Exception e) {
            errorMessage();
            e.printStackTrace();
        }
    }   
}
