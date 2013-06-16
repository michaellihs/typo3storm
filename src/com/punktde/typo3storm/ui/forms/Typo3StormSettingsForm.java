package com.punktde.typo3storm.ui.forms;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.punktde.typo3storm.Typo3StormProjectComponent;
import com.punktde.typo3storm.Typo3StormSettings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 */
public class Typo3StormSettingsForm implements Configurable {
    private JPanel typo3stormPanel;
    private JCheckBox enabledCheckBox;
    private JButton browseButton;
    private JTextField pathToTypo3Textfield;

    private Project project;



    public Typo3StormSettingsForm(@NotNull final Project currentProject) {
        project = currentProject;
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseButtonListener(e);
            }
        });
    }



    private void browseButtonListener(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String startPath = "";
        if (pathToTypo3Textfield != null) {
            startPath = pathToTypo3Textfield.getText();
        }
        if(startPath == null || startPath.isEmpty() || ! (new File(startPath)).exists()){
            Typo3StormProjectComponent typo3Storm = Typo3StormProjectComponent.getInstance(project);
            if(typo3Storm != null){
                startPath = typo3Storm.getDefaultPathToTypo3();
            }
        }
        if(startPath != null && ! startPath.isEmpty()){
            chooser.setCurrentDirectory(new File(startPath));
        }
        chooser.showOpenDialog(WindowManager.getInstance().suggestParentWindow(project));
        if (chooser.getSelectedFile() != null) {
            pathToTypo3Textfield.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Typo3Storm";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        Typo3StormSettings typo3StormSettings = Typo3StormSettings

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isModified() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void apply() throws ConfigurationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void disposeUIResources() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
