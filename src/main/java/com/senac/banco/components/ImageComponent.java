package com.senac.banco.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.senac.banco.components.ImageComponent.ImageDimension;

public class ImageComponent extends JPanel {

	private Image image;

    public ImageComponent(String imagePath) {
        loadImage(imagePath);
        this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
    }

    private void loadImage(String imagePath) {
        try {
            // Carrega a imagem a partir do diretório resources
            File file = new File("resources/" + imagePath);
            image = ImageIO.read(file);
            image = scaleImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
        	int x = (int) (getWidth() / 2 - ((int)382/2) / 2);
        	
            g.drawImage(image, x, 0, this);
        }
    }
    
    public class ImageDimension {
    	public int width;
    	public int height;
    
    	public ImageDimension(int w, int h) {
    		this.width = w;
    		this.height = h;
    	}
    }
    
    public ImageDimension getImageDimension() {
    	return new ImageDimension(image.getWidth(null), image.getHeight(null));
    }
    
    public Image getImage() {
    	return this.image;
    }
    
    public Image scaleImage() {
		int panelWidth = getWidth();
        int panelHeight = getHeight();

        ImageDimension imgDs = getImageDimension();
        
        double panelAspectRatio = (double) panelWidth / panelHeight;
        double imageAspectRatio = (double) imgDs.width / imgDs.height;

        int scaledWidth;
        int scaledHeight;

        if (panelAspectRatio > imageAspectRatio) {
            scaledWidth = panelWidth;
            scaledHeight = (int) (scaledWidth / imageAspectRatio);
        } else {
            scaledHeight = panelHeight;
            scaledWidth = (int) (scaledHeight * imageAspectRatio);
        }

//        return getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        return getImage().getScaledInstance((int)460/2, (int) 421/2, Image.SCALE_SMOOTH);
    }
}
