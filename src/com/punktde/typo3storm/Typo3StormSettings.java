package com.punktde.typo3storm;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: mimi
 * Date: 16.06.13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
@State(
        name = "Typo3StormSettings",    // must be equal to the class name I think
        storages = {
                @Storage(id = "default", file = "$PROJECT_FILE$"),
                @Storage(id = "dir", file = "$PROJECT_CONFIG_DIR$/typo3storm.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public class Typo3StormSettings implements PersistentStateComponent<Typo3StormSettings> {

    public boolean enabled = true;
    public String pathToTypo3;



    public static Typo3StormSettings getInstance(Project project) {
        Typo3StormSettings settings = ServiceManager.getService(project, Typo3StormSettings.class);
        if(settings == null){
            IdeHelper.logError("Cannot find Magicento Settings");
        }
        settings.project = project;

        settings.autoSetPathToMage();

        return settings;
    }



    @Nullable
    @Override
    public Typo3StormSettings getState() {
        return this;
    }



    @Override
    public void loadState(Typo3StormSettings typo3StormSettings) {
        try{
            XmlSerializerUtil.copyBean(typo3StormSettings, this);
        }
        catch(Exception e){
            // IdeHelper.logError(e.getMessage());
            // IdeHelper.showDialog(null,"Cannot read the saved settings for Magicento. Please try saving them again from File > Settings > Magicento", "Error in Magicento Settings");
        }
        // autoSetPathToMage();
    }



    public boolean isEnabled() {
        return enabled;
    }



    public String getPathToTypo3() {
        return getPathToTypo3();
    }



    public void setPathToTypo3(String pathToTypo3) {
        this.pathToTypo3 = pathToTypo3;
    }

}

