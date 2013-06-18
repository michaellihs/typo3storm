package com.punktde.typo3storm.ui.forms;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.punktde.typo3storm.Typo3StormProjectComponent;
import com.punktde.typo3storm.Typo3StormSettings;
import com.punktde.typo3storm.helpers.IdeHelper;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class implements a gui form to be displayed for the configuration of the plugin in the PHPStorm settings dialog.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class Typo3StormSettingsForm implements Configurable {
    private JComponent myComponent;

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
        Typo3StormSettings typo3StormSettings = Typo3StormSettings.getInstance(project);

        if (typo3StormSettings != null) {
            String pathToTypo3 = typo3StormSettings.pathToTypo3;
            if (pathToTypo3 != null) {
                pathToTypo3Textfield.setText(pathToTypo3);
            }
            enabledCheckBox.setSelected(typo3StormSettings.enabled);
        } else {
            pathToTypo3Textfield.setText("");
            enabledCheckBox.setEnabled(false);
        }

        myComponent = typo3stormPanel;

        return myComponent;
    }



    @Override
    public boolean isModified() {
        boolean modified;

        Typo3StormSettings typo3StormSettings = Typo3StormSettings.getInstance(project);
        if (typo3StormSettings.pathToTypo3 == pathToTypo3Textfield.getText()
                && typo3StormSettings.enabled == enabledCheckBox.isEnabled()) {
            modified = false;
        } else {
            modified = true;
        }

        return modified;
    }



    @Override
    public void apply() throws ConfigurationException {
        try {

            DataManager dataManager = DataManager.getInstance();

            if(myComponent != null && dataManager != null && PlatformDataKeys.PROJECT != null){

                if(project != null){

                    String pathToTypo3 = null;
                    try{
                        pathToTypo3 = pathToTypo3Textfield.getText();
                    }
                    catch (Exception e){
                        pathToTypo3 = "";
                        IdeHelper.logError("Error trying to read the path to TYPO3 from the textfield");
                        IdeHelper.logError(e.getMessage());
                    }

                    boolean enabled = enabledCheckBox.isSelected();

                    Typo3StormSettings settings = Typo3StormSettings.getInstance(project);

                    if(settings != null) {
                        if(pathToTypo3 != null) {
                            settings.setPathToTypo3(pathToTypo3);
                        }

                        settings.enabled = enabled;

                        // Implement setting of further settings like this:
                        //settings.setPathToPhp(pathToPhpTextField.getText());

                    }
                    else {
                        IdeHelper.logError("Typo3StormSettings is null");
                    }

                }
                else {
                    IdeHelper.logError("Project is null");
                }
            }
            else {
                IdeHelper.logError("DataManager is null");
            }
        }
        catch(Exception e){
            IdeHelper.logError("Unknown Error trying to save Typo3Storm settings");
            IdeHelper.logError(e.getMessage());
        }
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
