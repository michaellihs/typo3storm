package com.punktde.typo3storm.ui.dialogs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.models.CreateFileInfo;

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

        fileName = new JTextField(10);
        unitTest = new JCheckBox("Unit Test");
        functionalTest = new JCheckBox("Functional Test");
        init();
        setTitle("New File");
    }



    @Override
    protected void init() {
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
        panel.add(new Label("File Type"), c);
        c.gridx = 1;
        panel.add(fileTypeSelector, c);

        c.gridx = 0; c.gridy = 1;
        panel.add(new Label("File Name"), c);
        c.gridx = 1;
        panel.add(fileName, c);

        c.gridx = 0; c.gridy = 6;
        panel.add(new Label("Include"), c);
        JPanel includePanel = new JPanel();
        includePanel.setLayout(new BoxLayout(includePanel, BoxLayout.Y_AXIS));
        includePanel.add(functionalTest);
        includePanel.add(unitTest);
        c.gridx = 1; c.gridy = 6;
        panel.add(includePanel, c);

        return panel;
    }



    public FileType getFileType() {
        return (FileType)fileTypeSelector.getSelectedItem();
    }



    public String getFileName() {
        return fileName.getText();
    }



    public boolean createUnitTest() {
        return unitTest.isSelected();
    }



    public boolean createFunctionalTest() {
        return functionalTest.isSelected();
    }



    @Override
    public JComponent getPreferredFocusedComponent() {
        return fileName;
    }



    public CreateFileInfo getCreateFileInfo() {
        CreateFileInfo createFileInfo = new CreateFileInfo(this.getFileType(), this.getFileName(), this.createUnitTest(), this.createFunctionalTest(), this.project);
        return createFileInfo;
    }

}
