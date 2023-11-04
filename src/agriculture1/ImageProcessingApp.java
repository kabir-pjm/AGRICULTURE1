package agriculture1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessingApp extends JFrame {
    private JLabel originalImageLabel;
    private JLabel soilTypeLabel;

    public  ImageProcessingApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        originalImageLabel = new JLabel();
        soilTypeLabel = new JLabel();
        soilTypeLabel.setVerticalAlignment(SwingConstants.TOP); // Align the text to the top

        JButton openButton = new JButton("Open JPG Image");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        });

        add(openButton);
        add(originalImageLabel);
        add(soilTypeLabel);
        openButton.setBounds(40, 60, 60, 40);
        originalImageLabel.setBounds(100, 100, 600, 400); // Adjusted height to give more space
        soilTypeLabel.setBounds(100, 500, 600, 100); // Adjusted position and height

        setVisible(true);
    }

    private void openImage() {
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
                int red = pixelColor.getRed();
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();

                // Determine the soil type using RGB values
                String soilType = detectSoilType(red, green, blue);

                soilTypeLabel.setText("Soil Type: " + soilType+red+":"+green+":"+blue);

                originalImageLabel.setIcon(new ImageIcon(image));
                revalidate();
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Function to detect soil type based on RGB values
    private String detectSoilType(int red, int green, int blue) {
        // Define the RGB limits for each soil type
        String[] soilTypes = {
                "Sandy Soil",
                "Clayey Soil",
                "Loamy Soil",
                "Silt Soil",
                "Peat Soil",
                "Chalky Soil",
                "Volcanic Soil",
                "Laterite Soil",
                "Limestone",
                "Chernozem Soil",
                "Red Soil",
                "Black Soil",
                "Alluvial Soil"
        };

        int[][] rgbLimits = {
                {150, 255, 100, 215, 50, 112},
                {100, 139, 50, 69, 0, 19},
                {139, 165, 105, 132, 20, 82},
                {150, 192, 150, 192, 150, 192},
                {0, 38, 50, 83, 0, 53},
                {200, 255, 200, 255, 200, 240},
                {0, 139, 0, 69, 0, 19},
                {139, 165, 0, 42, 0, 42},
                {200, 255, 180, 205, 140, 200},
                {50, 100, 70, 120, 0, 20},
                {150,255,0,50,0,50},
                {0,50,0,50,0,50},
                {100,200,80,160,0,100}


        };

        for (int i = 0; i < soilTypes.length; i++) {
            if (red >= rgbLimits[i][0] && red <= rgbLimits[i][1] &&
                    green >= rgbLimits[i][2] && green <= rgbLimits[i][3] &&
                    blue >= rgbLimits[i][4] && blue <= rgbLimits[i][5]) {
                return soilTypes[i];
            }
        }
        return "Unknown";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageProcessingApp());
    }
}