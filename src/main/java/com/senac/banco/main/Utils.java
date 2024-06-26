package com.senac.banco.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Utils {
    public static NumberFormat formattingValues = new DecimalFormat ("R$ #, ##0.00");

    public static String doubleToString(Double value){
        return formattingValues.format(value);
    }
    
    public static String doubleToRealCurrency(double amount) {
    	DecimalFormat df = new DecimalFormat("#,##0.00");
    	return "R$ " + df.format(amount);
    }
    
    public static Font loadFont(String fileName, float fontSize) {
    	Font font = null;
    	try {
//    		InputStream is = new BufferedInputStream(new FileInputStream(fileName));
    		InputStream is = App.class.getResourceAsStream(fileName);
    		
    		font = Font.createFont(Font.TRUETYPE_FONT, is);
    		font = font.deriveFont(Font.PLAIN, fontSize);
    	} catch(IOException | FontFormatException e) {
    		e.printStackTrace();
    		return null;
    	}
    	return font;
    }
    
    /** Gera um número aleatorio entre o valor <i>min</i> e <i>max</i> **/
    public static int randomNumber(int min, int max) {
    	return new Random(System.currentTimeMillis()).nextInt(max - min + 1) + min;
    }
}
