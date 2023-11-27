import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;

public class InterfaceSwing extends JFrame {
    private JPanel panelHeader;
    private JButton xmlToButton;
    private JButton yamlToButton;  // Correction : Renommé de YamlToButton à yamlToButton
    private JButton jsonToButton;  // Correction : Renommé de xmlToButton à jsonToButton
    private RSyntaxTextArea textAreaLeft;
    private JButton centerButton;
    private JButton centerButton1;
    private JButton uploadButton;
    private RSyntaxTextArea textAreaRight;
    private XmlJsonConverter xmlJsonConverter;

    public InterfaceSwing() {
        setTitle("Xml To Json Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);

        // Initialize the XmlJsonConverter class
        xmlJsonConverter = new XmlJsonConverter();

        // Create components
        panelHeader = new JPanel();
        xmlToButton = new JButton("XML TO ");
        yamlToButton = new JButton("YAML TO ");
        jsonToButton = new JButton("JSON TO ");
        textAreaLeft = new RSyntaxTextArea();
        centerButton = new JButton("XML TO JSON");
        centerButton1 = new JButton("XML TO YAML");
        uploadButton = new JButton("UPLOAD  XML");
        textAreaRight = new RSyntaxTextArea();

        // Set syntax highlighting
        textAreaLeft.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        textAreaRight.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);

        // Set component sizes
        textAreaLeft.setPreferredSize(new Dimension(400, 300));
        centerButton.setPreferredSize(new Dimension(120, 40));
        centerButton1.setPreferredSize(new Dimension(120, 40));
        uploadButton.setPreferredSize(new Dimension(120, 40));
        textAreaRight.setPreferredSize(new Dimension(400, 300));

        panelHeader.setBackground(new Color(0x90B8C1));
        panelHeader.setPreferredSize(new Dimension(getWidth(), 40));

        RTextScrollPane scrollPaneLeft = new RTextScrollPane(textAreaLeft);
        RTextScrollPane scrollPaneRight = new RTextScrollPane(textAreaRight);

        // Set layout
        setLayout(new BorderLayout());
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        panelLeft.add(scrollPaneLeft, BorderLayout.CENTER);

        JPanel panelCenter = new JPanel();
        panelCenter.setSize(120, 600);
        panelCenter.setLayout(new GridLayout(6, 1, 40, 40));
        panelCenter.add(centerButton);
        panelCenter.add(centerButton1);
        panelCenter.add(uploadButton);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        panelRight.add(scrollPaneRight, BorderLayout.CENTER);

        // Add buttons to the header panel
        panelHeader.add(xmlToButton);
        panelHeader.add(yamlToButton);
        panelHeader.add(jsonToButton);

        add(panelHeader, BorderLayout.NORTH);
        add(panelLeft, BorderLayout.WEST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelRight, BorderLayout.EAST);

        xmlToButton.addActionListener(e -> onXmlToButtonClicked());
        yamlToButton.addActionListener(e -> onYamlToButtonClicked());
        jsonToButton.addActionListener(e -> onJsonToButtonClicked());
        centerButton.addActionListener(e -> onConvertButtonClicked());
        uploadButton.addActionListener(e -> onUploadButtonClicked());

        setVisible(true);
    }

    private void onYamlToButtonClicked() {
        // TODO: Gérer la conversion YAML
    }

    private void onXmlToButtonClicked() {
        // TODO: Gérer la conversion XML
    }

    private void onJsonToButtonClicked() {
        // TODO: Gérer la conversion JSON
    }

    private void onConvertButtonClicked() {
        String xmlInput = textAreaLeft.getText();
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

    public static void main(String[] args) {
        new InterfaceSwing();
    }
}
