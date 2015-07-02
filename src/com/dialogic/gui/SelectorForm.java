/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.gui;

import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSObjectFactory;
import gov.nist.javax.sip.header.CSeq;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sip.address.Address;
import javax.sip.header.CSeqHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.ToHeader;
import javax.sip.message.Request;
import javax.sip.message.Response;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 *
 * @author ssatyana
 */
public class SelectorForm extends javax.swing.JFrame {
    
    static final Logger logger = Logger.getLogger(SelectorForm.class.getName());
    static FileInputStream xmlFile;

    /**
     * Creates new form CallForm
     */
    public SelectorForm() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
            initComponents();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setResizable(false);
            File f = new File("SelectorConfiguration.xml");
            if (f.exists()) {
                try {
                    readFromXMLFile(new FileInputStream("SelectorConfiguration.xml"));
                } catch (FileNotFoundException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        typeComboBox = new javax.swing.JComboBox();
        ipAddressTextField = new javax.swing.JTextField();
        userTextField = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        techTypeLabel = new javax.swing.JLabel();
        xmsAddressLAbel = new javax.swing.JLabel();
        appIdLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        fileTextField = new javax.swing.JTextField();
        fileButton = new javax.swing.JButton();
        enterButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selector");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fields", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        typeComboBox.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Type", "REST", "MSML" }));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });

        userTextField.setText("app");

        clearButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        clearButton.setText("Clear");
        clearButton.setMaximumSize(new java.awt.Dimension(55, 23));
        clearButton.setMinimumSize(new java.awt.Dimension(55, 23));
        clearButton.setPreferredSize(new java.awt.Dimension(55, 23));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        techTypeLabel.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        techTypeLabel.setForeground(new java.awt.Color(0, 0, 204));
        techTypeLabel.setText("TechType");

        xmsAddressLAbel.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        xmsAddressLAbel.setForeground(new java.awt.Color(0, 0, 204));
        xmsAddressLAbel.setText("XMS Address");

        appIdLabel.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        appIdLabel.setForeground(new java.awt.Color(0, 0, 204));
        appIdLabel.setText("App ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(userTextField)
                        .addComponent(ipAddressTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 191, Short.MAX_VALUE))
                    .addComponent(xmsAddressLAbel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(techTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(techTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xmsAddressLAbel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipAddressTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upload Config File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        fileTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileTextFieldActionPerformed(evt);
            }
        });

        fileButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        fileButton.setText("Browse");
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileTextField)
                    .addComponent(fileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        enterButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(enterButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileTextFieldActionPerformed
    
    private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileButtonActionPerformed
        try {
            JFileChooser chooser = new JFileChooser("");
            FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
            chooser.setFileFilter(xmlfilter);
            chooser.setDialogTitle("Open config file");
            chooser.setFileFilter(xmlfilter);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            int returnVal = chooser.showOpenDialog((java.awt.Component) null);
            File inFile = null;
            if (returnVal == chooser.APPROVE_OPTION) {
                // to populate the text field
                chooser.addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt.getPropertyName())) {
                            JFileChooser chooser = (JFileChooser) evt.getSource();
                            if (chooser.getSelectedFile() != null) {
                                fileTextField.setText(chooser.getSelectedFile().getName());
                            }
                        }
                    }
                });
                inFile = chooser.getSelectedFile();
                System.out.println("Selected File: " + inFile.getAbsolutePath());

                // copy to local file
                BufferedReader in = new BufferedReader(new FileReader(inFile));
                FileWriter fstream = new FileWriter("SelectorConfiguration.xml", true);
                BufferedWriter out = new BufferedWriter(fstream);
                
                String line = in.readLine();
                while (line != null) {
                    out.write(line);
                    out.newLine();
                    line = in.readLine();
                }
                in.close();
                out.close();

                // auto populate the fields
                xmlFile = new FileInputStream(inFile);
                readFromXMLFile(xmlFile);
//                Document doc = new Builder().build(xmlFile);
//                Element root = doc.getRootElement();
//                Elements entries = root.getChildElements();
//                for (int x = 0; x < entries.size(); x++) {
//                    Element element = entries.get(x);
//                    if (element.getLocalName().equals("appid") || element.getLocalName().equals("user")) {
//                        userTextField.setText(element.getValue());
//                    }
//                    if (element.getLocalName().equals("baseurl") || element.getLocalName().equals("xmsAddress")) {
//                        ipAddressTextField.setText(element.getValue());
//                    }
//                    if (element.getLocalName().equals("techtype")) {
//                        typeComboBox.setSelectedItem(element.getValue());
//                    }
//                }
            } else if (returnVal == chooser.CANCEL_OPTION) {
                System.out.println("No file selected");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//GEN-LAST:event_fileButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        
        if (xmlFile != null) {
            File f = new File("SelectorConfiguration.xml");
            if (f.exists()) {
                this.dispose();
                return;
            }
        } else {
            try {
                if (typeComboBox.getSelectedItem() == "Type"
                        || ipAddressTextField.getText().isEmpty() || userTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please Choose a config file or enter data in the fields", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
//                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//
//                org.w3c.dom.Document doc = docBuilder.newDocument();
//
//                org.w3c.dom.Element rootElement = doc.createElement("xmsconfig");
//                doc.appendChild(rootElement);
//
//                org.w3c.dom.Element techType = doc.createElement("techtype");
//                techType.appendChild(doc.createTextNode(typeComboBox.getSelectedItem().toString()));
//                rootElement.appendChild(techType);
//
//                org.w3c.dom.Element baseurl = doc.createElement("baseurl");
//                baseurl.appendChild(doc.createTextNode(ipAddressTextField.getText()));
//                rootElement.appendChild(baseurl);
//
//                org.w3c.dom.Element appid = doc.createElement("appid");
//                appid.appendChild(doc.createTextNode(userTextField.getText()));
//                rootElement.appendChild(appid);
//
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
//                DOMSource source = new DOMSource(doc);
                DOMSource source = getXMLSource();
                
                StreamResult result = new StreamResult(new File("SelectorConfiguration.xml"));
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.transform(source, result);
                this.dispose();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        new XMSObjectFactory().unblock();
    }//GEN-LAST:event_enterButtonActionPerformed
    
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        typeComboBox.setSelectedItem("Type");
        ipAddressTextField.setText("");
        userTextField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed
    
    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        if (typeComboBox.getSelectedItem() == "REST") {
            userTextField.setText("app");
        } else if (typeComboBox.getSelectedItem() == "MSML") {
            userTextField.setText("msml");
        }
    }//GEN-LAST:event_typeComboBoxActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        int actionDialog = SaveAs.showOpenDialog(this);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        File fileName = new File(SaveAs.getSelectedFile() + ".xml");
        //BufferedWriter outFile = null;
        try {
            //outFile = new BufferedWriter(new FileWriter(fileName));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = getXMLSource();
            
            StreamResult result = new StreamResult(fileName);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_saveButtonActionPerformed
    
    private void readFromXMLFile(FileInputStream file) {
        Document doc;
        try {
            doc = new Builder().build(file);
            Element root = doc.getRootElement();
            Elements entries = root.getChildElements();
            for (int x = 0; x < entries.size(); x++) {
                Element element = entries.get(x);
                if (element.getLocalName().equals("appid") || element.getLocalName().equals("user")) {
                    userTextField.setText(element.getValue());
                }
                if (element.getLocalName().equals("baseurl") || element.getLocalName().equals("xmsAddress")) {
                    ipAddressTextField.setText(element.getValue());
                }
                if (element.getLocalName().equals("techtype")) {
                    typeComboBox.setSelectedItem(element.getValue());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SelectorForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private DOMSource getXMLSource() {
        DOMSource source = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            org.w3c.dom.Document doc = docBuilder.newDocument();
            
            org.w3c.dom.Element rootElement = doc.createElement("xmsconfig");
            doc.appendChild(rootElement);
            
            org.w3c.dom.Element techType = doc.createElement("techtype");
            techType.appendChild(doc.createTextNode(typeComboBox.getSelectedItem().toString()));
            rootElement.appendChild(techType);
            
            org.w3c.dom.Element baseurl = doc.createElement("baseurl");
            baseurl.appendChild(doc.createTextNode(ipAddressTextField.getText()));
            rootElement.appendChild(baseurl);
            
            org.w3c.dom.Element appid = doc.createElement("appid");
            appid.appendChild(doc.createTextNode(userTextField.getText()));
            rootElement.appendChild(appid);
            
            source = new DOMSource(doc);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return source;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appIdLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JButton fileButton;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField ipAddressTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel techTypeLabel;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JTextField userTextField;
    private javax.swing.JLabel xmsAddressLAbel;
    // End of variables declaration//GEN-END:variables
}
