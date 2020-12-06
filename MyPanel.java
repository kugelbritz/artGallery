import com.gembox.spreadsheet.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.awt.event.*;
import javax.swing.*;


public class MyPanel extends MainScreen implements ActionListener {
        public JFrame pieceFrame;
        private JLabel jcomp1;
        private JTextField titulo;
        private JComboBox aMedium;
        private JLabel jcomp4;
        private JComboBox aArtist;
        private JLabel jcomp6;
        private JLabel jcomp7;
        private JTextField oArtist;
        private JTextField oMedium;
        private JComboBox aEra;
        private JTextArea bio;
        private JLabel jcomp12;
        private JLabel jcomp13;
        private JButton submit;
        private JTextField oPeriod;
        private JButton view;
        private JLabel jcomp17;
        private JButton aImage;
        private int rCount;
        private int HrCount;
        private MainScreen parent;

        public MyPanel(MainScreen daddy) {
            //JFrame details for ArtForm
            parent = daddy;
            //construct preComponents
            String[] aMediumItems = {"", "Oil", "Pastel", "Acrylic"};
            String[] aArtistItems = {"", "Stacey Robinson", "Monet", "John Fredrick Kensett"};
            String[] aEraItems = {"", "Impressionist", "Contemporary", "Classical"};

            //construct components
           pieceFrame=new JFrame("Admin Menu");
           pieceFrame.setLayout(null);

            jcomp1 = new JLabel("Artist:");
            titulo = new JTextField(5);
            aMedium = new JComboBox(aMediumItems);
            jcomp4 = new JLabel("Medium:");
            aArtist = new JComboBox(aArtistItems);
            jcomp6 = new JLabel("Period:");
            jcomp7 = new JLabel("Title:");
            oArtist = new JTextField(5);
            oMedium = new JTextField(5);
            aEra = new JComboBox(aEraItems);
            bio = new JTextArea(5, 5);
            jcomp12 = new JLabel("Description/Location:");
            jcomp13 = new JLabel("Add To Collection");
            submit = new JButton("Submit and Add New");
            oPeriod = new JTextField(5);
            view = new JButton("View Collection");
            jcomp17 = new JLabel("Image (as JPG):");
            aImage = new JButton ("Find Image");

            //adjust size and set layout
            pieceFrame.setPreferredSize(new Dimension(492, 430));

            //add components
            pieceFrame.add(jcomp1);
            pieceFrame.add(titulo);
            pieceFrame.add(aMedium);
            pieceFrame.add(jcomp4);
            pieceFrame.add(aArtist);
            pieceFrame.add(jcomp6);
            pieceFrame.add(jcomp7);
            pieceFrame.add(oArtist);
            pieceFrame.add(oMedium);
            pieceFrame.add(aEra);
            pieceFrame.add(bio);
            pieceFrame.add(jcomp12);
            pieceFrame.add(jcomp13);
            pieceFrame.add(submit);
            submit.addActionListener(this);
            pieceFrame.add(oPeriod);
            pieceFrame.add(view);
            view.addActionListener(this);
            pieceFrame.add(jcomp17);
            pieceFrame.add(aImage);
            aImage.addActionListener(this);

            //set component bounds (only needed by Absolute Positioning)
            jcomp1.setBounds(65, 120, 100, 25);
            titulo.setBounds(165, 75, 220, 25);
            aMedium.setBounds(165, 160, 100, 25);
            jcomp4.setBounds(60, 160, 100, 25);
            aArtist.setBounds(165, 115, 100, 25);
            jcomp6.setBounds(60, 205, 100, 25);
            jcomp7.setBounds(65, 75, 100, 25);
            oArtist.setBounds(285, 115, 100, 25);
            oMedium.setBounds(285, 160, 100, 25);
            aEra.setBounds(165, 205, 100, 25);
            bio.setBounds(160, 265, 260, 75);
            jcomp12.setBounds(20, 260, 170, 40);
            jcomp13.setBounds(165, 0, 140, 35);
            submit.setBounds(50, 360, 175, 25);
            oPeriod.setBounds(285, 205, 100, 25);
            view.setBounds(250, 360, 185, 25);
            jcomp17.setBounds(45, 35, 100, 25);
            aImage.setBounds(165, 35, 220, 25);

            pieceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pieceFrame.pack();
            pieceFrame.setVisible(true);
        }

    public MyPanel() {
        // Empty Constructor
    }

        public void actionPerformed(ActionEvent  e) {
            //Button Managers
            if (e.getSource() == submit) {
                //Submit button to load the ArtData.xlsx
                rCount = HrCount;
                rCount++;
                SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
                String title = titulo.getText();
                //check if drop down selection is valid
                String medium = (String) aMedium.getSelectedItem();
                if (medium == ""){
                    medium = oMedium.getText();
                }
                String artist = (String) aArtist.getSelectedItem();
                if (artist == ""){
                artist = oArtist.getText();
                }
                String era = (String) aEra.getSelectedItem();
                if (era == "") {
                    era = oPeriod.getText();
                }

                String snippet = bio.getText();
                String nTitle = title.replace(' ', '_');

                try {
                    //Load the workbook and write to the cells
                    ExcelFile workbook = ExcelFile.load("ArtData.xlsx");
                    ExcelWorksheet datasheet = workbook.getWorksheet("Sheet1");
                    datasheet.getCell(rCount, 0).setValue(title);
                    datasheet.getCell(rCount, 1).setValue(artist);
                    datasheet.getCell(rCount, 2).setValue(medium);
                    datasheet.getCell(rCount, 3).setValue(era);
                    datasheet.getCell(rCount, 4).setValue("src/resources/images/" + nTitle + ".jpg");
                    datasheet.getCell(rCount, 5).setValue(snippet);
                    workbook.save("ArtData.xlsx");
                } catch (IOException ex) {
                    //If not found
                    ex.printStackTrace();
                    System.out.println("running into the ground");
                }
                //Clear the fields
                titulo.setText("");
                oArtist.setText("");
                oMedium.setText("");
                oPeriod.setText("");
                bio.setText("");
            }
            if (e.getSource() == aImage){
                //Dim Main Screen and use file manager to load in new Images
                glassPanel.setVisible(true);
            SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(fc);
            String iPath = null;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                iPath = fc.getSelectedFile().getAbsolutePath();
            } else {
                System.out.println("Window Cancelled");
                System.exit(1);
            }
            //Rename the image and load into project specific folder (src/resources/images)
            String title = titulo.getText();
            String sTitle = title.replace(' ', '_');
            File img = new File(iPath);
            java.nio.file.Path targetFolder = Paths.get("/src/resources/images");
            if (img.renameTo
                    (new File("/ArtGallery.java/src/resources/images/" + sTitle + ".jpg"))) {
                System.out.println("File renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }
            }
            if (e.getSource() == view) {
                //View the previous JFrame so you can view the art
                int HrCount = rCount;
                pieceFrame.setVisible(false);
                parent.startFrame.setVisible(true);
            }
        }

            public static void main(String[] args) {
            MyPanel aM = new MyPanel();


        }
    }
