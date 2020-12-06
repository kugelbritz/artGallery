

import com.gembox.spreadsheet.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


public class MainScreen extends JPanel implements ActionListener {
    protected static final Color fill_color = new Color(0, 0, 0, 85);

    private JButton btn1;
    private JButton btn2;
    private JLabel sLbl;
    private JTextField sField;
    private JTextArea aData;
    public JFrame startFrame;
    public JLabel background;
    public JOptionPane popup;
    public JLabel pieceDisplayed;
    private MyPanel pieceForm;

    public void MainScreen()
    {
        //JFrame details for MainScreen
        startFrame=new JFrame("CSCI Art Gallery Project");
        startFrame.setLayout(null);
        glassPanel.setOpaque(false);
        startFrame.setGlassPane(glassPanel);
        btn1=new JButton(" Admin?");
        btn2=new JButton("▷");
        sLbl = new JLabel ("search:");
        sField = new JTextField (25);
        aData = new JTextArea(5,30);
        pieceDisplayed = new JLabel();
        background = new JLabel(new ImageIcon("C:\\Users\\Brittany Francois\\IdeaProjects\\ArtGallery.java\\src\\resources\\images\\GalleryBack.jpg"));
        setOpaque(false);
        background.add(btn1);
        btn1.addActionListener(this);
        background.add(btn2);
        btn2.addActionListener(this);
        background.add(sField);
        background.add(sLbl);
        background.add(pieceDisplayed);
        aData.setBackground(Color.lightGray);
        Font font = new Font ("Times New Roman", Font.ITALIC, 12);
        aData.setFont(font);
        JScrollPane scrollPane = new JScrollPane(aData);
        this.add(scrollPane, BorderLayout.EAST);
        background.add(aData);


        startFrame.setContentPane(background);
        btn1.setBounds (35, 5, 105, 30);
        btn2.setBounds (910, 5, 55, 25);
        sField.setBounds (680, 5, 230, 26);
        sLbl.setBounds(625,5,50,25);
        aData.setBounds(382,460,250,60);
        pieceDisplayed.setBounds(315, 55, 480,400);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(1032,630);
        startFrame.setVisible(true);
    }

    public void paint(Graphics g)
{        Image a=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brittany Francois\\IdeaProjects\\ArtGallery.java\\src\\resources\\images\\GalleryBack.jpg");
        g.drawImage(a,0,0,getSize().width,getSize().height,this);
        super.paint(g);
    }
    final JPanel glassPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
            //Dimmer for Aesthetics
            super.paintComponent(g);
            g.setColor(fill_color);
            g.fillRect(0, 0, getWidth(), getHeight());
        };
    };
    @Override
    public void actionPerformed(ActionEvent at) {
        if (at.getSource() == btn1) {
            //Popup to Check Passkey
            glassPanel.setVisible(true);
            String keyentered = popup.showInputDialog(startFrame, "Enter 5-Digit Passkey:", "Passkey?", JOptionPane.OK_CANCEL_OPTION);
            String key = keyentered;
            if (key == null) {
                glassPanel.setVisible(false);
                popup.setVisible(false);
            }
            if (key.equals("00252")) {
                startFrame.setVisible(false);
                if(pieceForm == null){
                    pieceForm = new MyPanel(this);
                } else{
                    pieceForm.pieceFrame.setVisible(true);
                }
            }
            if (key != "00252") {
                glassPanel.setVisible(false);
                popup.setVisible(false);

            }
        }

        if (at.getSource() == btn2) {
            //implement Search of Database
            SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
            aData.setText("");

            String keyword = sField.getText();

            try {
                ExcelFile workbook = null;
                try {
                    workbook = ExcelFile.load("ArtData.xlsx");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                var worksheet = workbook.getWorksheet("Sheet1");
                RowColumn place = worksheet.getCells().findText(keyword, false, false);
                if (place == null) {
                    JOptionPane.showMessageDialog(startFrame, "Can't find text.", "You Got An Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    int keywordRow = place.getRow();
                    int keywordColumn = place.getColumn();
                    ExcelCell kt = worksheet.getCell(keywordRow, 0);
                    String keywordTitle = (String) kt.getValue();
                    ExcelCell ka = worksheet.getCell(keywordRow, 1);
                    String keywordArtist = (String) ka.getValue();
                    ExcelCell ki = worksheet.getCell(keywordRow, 4);
                    String keywordImageLoci = (String) ki.getValue();
                    ExcelCell ks = worksheet.getCell(keywordRow, 5);
                    String keywordSnippet = (String) ks.getValue();
                    aData.setText("          \t" + keywordTitle + "    \n" +
                            "             \t" + keywordArtist + "    \n" +
                            "   " + keywordSnippet + "    \n");
                    BufferedImage Img = ImageIO.read(new File(keywordImageLoci));
                    Images imgs = new Images();
                    Image piece = imgs.DisplayHandler(Img);
                    ImageIcon Icon = new ImageIcon(piece);

                    pieceDisplayed.setIcon(Icon);

                   /* Iterator<ExcelCell> it = datasheet.getColumn(keywordColumn).getCells().getReadIterator();
                    while (it.hasNext()) {
                        ExcelCell cell = iterator.next();
                        if (cell.getValue() instanceof String) {
                            ExcelCell nkt = worksheet.getCell(keywordRow, 0);
                            String nkeywordTitle = (String) nkt.getValue();
                            ExcelCell nka = worksheet.getCell(keywordRow, 1);
                            String nkeywordArtist = (String) nka.getValue();
                            ExcelCell nki = worksheet.getCell(keywordRow, 4);
                            String nkeywordImageLoci = (String) nki.getValue();
                            ExcelCell nks = worksheet.getCell(keywordRow, 5);
                            String nkeywordSnippet = (String) nks.getValue();
                            aData.setText("          \t" + nkeywordTitle + "    \n" +
                                    "             \t" + nkeywordArtist + "    \n" +
                                    "   " + nkeywordSnippet + "    \n");
                            BufferedImage nImg = ImageIO.read(new File(nkeywordImageLoci));
                            pieceDisplayed.setIcon(Images.DisplayHandler(nImg));

                            TODO//last five pieces - string builder array , linked list?

                            TODO//next button build it into the GUI
                        }


                    }*/
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[]args)
    {
        MainScreen mS = new MainScreen();

        mS.MainScreen();


    }
}