package com.senac.banco.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
    static NumberFormat formattingValues = new DecimalFormat ("R$ #, ##0.00");

    public static String doubleToString(Double value){
        return formattingValues.format(value);
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
}
