/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 */
public class FormatUtils {
    
    public static String formataDinheiroExibicao(BigDecimal valor){
        NumberFormat f = NumberFormat.getCurrencyInstance();
        f.setMaximumFractionDigits(2);
        f.setMinimumFractionDigits(0);
        return f.format(valor);
    }
    
    public static String ajustaFormato(String valor){
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        valor = valor.replace(NumberFormat.getCurrencyInstance().getCurrency().getSymbol(), "");
        valor = valor.replace("- ", "-");
        return valor.trim();
    }
    
    public static String formataDecimalExibicao(BigDecimal valor){
        NumberFormat f = NumberFormat.getNumberInstance();
        f.setMaximumFractionDigits(2);
        f.setMinimumFractionDigits(0);
        return f.format(valor);
    }

}
