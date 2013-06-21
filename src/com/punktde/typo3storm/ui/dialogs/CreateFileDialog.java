package com.punktde.typo3storm.ui.dialogs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.helpers.ExtensionHelper;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.models.ExtensionInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class implements a GUI dialog to be shown to users for creating a new file.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateFileDialog extends DialogWrapper {

    private JComboBox extensionSelector;
    private JComboBox fileTypeSelector;
    private JTextField fileName;
    private JCheckBox unitTest;
    private JCheckBox functionalTest;
    private JPanel panel;



    protected Project project;



    public CreateFileDialog(Project project) {
        super(project);

        this.project = project;
        fileTypeSelector = new JComboBox();
        fileTypeSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateTestCheckboxStates();
            }
        });

        this.extensionSelector = new JComboBox();

        fileName = new JTextField(10);
        unitTest = new JCheckBox("Unit Test");
        functionalTest = new JCheckBox("Functional Test");
        init();
        setTitle("New File");
    }



    @Override
    protected void init() {
        // Initialize selectable extensions
        for (ExtensionInfo extensionInfo : ExtensionHelper.getExtensionInfos(project)) {
            this.extensionSelector.addItem(extensionInfo);
        }

        // Initialize selectable file types
        for (FileType fileType: FileType.values()) {
            this.fileTypeSelector.addItem(fileType);
        }

        updateTestCheckboxStates();

        super.init();
    }



    private void updateTestCheckboxStates() {
        unitTest.setEnabled(((FileType)this.fileTypeSelector.getSelectedItem()).canHaveUnitTest);
        functionalTest.setEnabled(((FileType)this.fileTypeSelector.getSelectedItem()).canHaveFunctionalTest);
    }



    @Override
    protected JComponent createCenterPanel() {
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        c.gridx = 0; c.gridy = 0;
        panel.add(new Label("Extension"), c);
        c.gridx = 1;
        panel.add(extensionSelector, c);

        c.gridx = 0; c.gridy = 1;
        panel.add(new Label("File Type"), c);
        c.gridx = 1;
        panel.add(fileTypeSelector, c);

        c.gridx = 0; c.gridy = 2;
        panel.add(new Label("File Name"), c);
        c.gridx = 1;
        panel.add(fileName, c);

        c.gridx = 0; c.gridy = 7;
        panel.add(new Label("Include"), c);
        JPanel includePanel = new JPanel();
        includePanel.setLayout(new BoxLayout(includePanel, BoxLayout.Y_AXIS));
        includePanel.add(functionalTest);
        includePanel.add(unitTest);
        c.gridx = 1; c.gridy = 7;
        panel.add(includePanel, c);

        return panel;
    }



    public FileType getFileType() {
        return (FileType)fileTypeSelector.getSelectedItem();
    }



    public String getFileName() {
        String fileName = this.fileName.getText();
        if (fileName.contains("/")) {
            String[] fileNameParts = fileName.split("/");
            fileName = fileNameParts[fileNameParts.length - 1];
        }
        return fileName;
    }



    public boolean createUnitTest() {
        return unitTest.isSelected();
    }



    public boolean createFunctionalTest() {
        return functionalTest.isSelected();
    }



    public ExtensionInfo getExtensionInfo() {
        return (ExtensionInfo)extensionSelector.getSelectedItem();
    }



    public String getDirectory() {
        String path = "";
        if (this.fileName.getText().contains("/")) {
            String[] fileNameParts = this.fileName.getText().split("/");
            for (int i = 0; i < fileNameParts.length -1; i++) {
                path += fileNameParts[i] + "/";
            }
            path = path.substring(0,path.length() - 1);
        }
        return path;
    }



    @Override
    public JComponent getPreferredFocusedComponent() {
        return fileName;
    }



    public CreateFileInfo getCreateFileInfo() {
        CreateFileInfo createFileInfo = new CreateFileInfo(this.getExtensionInfo(), this.getFileType(), this.getDirectory(), this.getFileName(), this.createUnitTest(), this.createFunctionalTest(), this.project);
        return createFileInfo;
    }

}
