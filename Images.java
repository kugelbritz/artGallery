import com.gembox.spreadsheet.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;
import javax.imageio.ImageIO;


public class Images extends MainScreen {
    public Images(){

    }

    public Images(String Path) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame(Path);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(Path));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Path);
                String resourcePath = "/src/resources/images";
                java.nio.file.Path targetFolder = Paths.get("resources/images");
                try {
                    Files.createDirectories(targetFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(img));
                frame.getContentPane().add(label, BorderLayout.CENTER);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }
    public  Image DisplayHandler(BufferedImage img){
        Image piece;
            if (img.getWidth() > img.getHeight()) {
                piece = img.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
             } else if (img.getHeight() > img.getWidth()) {
                piece = img.getScaledInstance(350, 400, Image.SCALE_DEFAULT);
             } else {
                piece = img;
            }
            return  piece;
    }


}