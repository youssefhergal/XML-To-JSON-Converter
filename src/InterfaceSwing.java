import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;

public class InterfaceSwing extends JFrame {
    private JPanel panelHeader;

    private JTextArea textAreaLeft;
    private JButton centerButton;
    private JButton centerButton1;
    private JButton uploadButton;
    private JTextArea textAreaRight;
    private XmlJsonConverter xmlJsonConverter;

    public InterfaceSwing() {
        setTitle("Xml To Json Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialisation de la classe XmlJsonConverter
        xmlJsonConverter = new XmlJsonConverter();

        // Création des composants
        panelHeader = new JPanel();
        textAreaLeft = new JTextArea();
        centerButton = new JButton("XML TO JSON");
        centerButton1 = new JButton("XML TO YAML");
        uploadButton = new JButton("UPLOAD  XML");
        textAreaRight = new JTextArea();

        // Ajustement de la taille des composants
        textAreaLeft.setPreferredSize(new Dimension(300, 300));
        centerButton.setPreferredSize(new Dimension(120, 40));
        centerButton1.setPreferredSize(new Dimension(120, 40));
        uploadButton.setPreferredSize(new Dimension(120, 40));
        textAreaRight.setPreferredSize(new Dimension(300, 300));

        panelHeader.setBackground(new Color(0x458796));
        panelHeader.setPreferredSize(new Dimension(getWidth(), 30));

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        panelLeft.add(new JScrollPane(textAreaLeft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        panelCenter.add(centerButton);
        panelCenter.add(centerButton1);
        panelCenter.add(uploadButton);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        panelRight.add(new JScrollPane(textAreaRight, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        setLayout(new BorderLayout());

        add(panelHeader, BorderLayout.NORTH);
        add(panelLeft, BorderLayout.WEST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelRight, BorderLayout.EAST);

        centerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConvertButtonClicked();
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUploadButtonClicked();
            }
        });

        setVisible(true);
    }

    private void onConvertButtonClicked() {
        // Récupération du texte XML du premier JTextArea
        String xmlInput = textAreaLeft.getText();

        // Appel de la méthode de conversion de la classe XmlJsonConverter
        try {
            String jsonOutput = xmlJsonConverter.convertXmlToJson(xmlInput);
            textAreaRight.setText(jsonOutput);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la conversion XML vers JSON.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onUploadButtonClicked() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers XML", "xml"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String xmlContent = new String(Files.readAllBytes(selectedFile.toPath()));
                textAreaLeft.setText(xmlContent);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier XML.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



}
