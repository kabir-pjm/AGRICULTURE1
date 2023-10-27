/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agriculture1;

/**
 *
 * @author LENOVO
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessing {
    private JFrame frame;
    private JLabel originalImageLabel;
    private JLabel rgbLabel;



    
    public void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);


                int pixelX = image.getWidth() / 2;
                int pixelY = image.getHeight() / 2;
                Color pixelColor = new Color(image.getRGB(pixelX, pixelY));
                rgbLabel.setText("RGB: " + pixelColor.getRed() + ", " + pixelColor.getGreen() + ", " + pixelColor.getBlue());

                originalImageLabel.setIcon(new ImageIcon(image));
                frame.revalidate();
                frame.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error opening the image.");
            }
        }
    }

    
}