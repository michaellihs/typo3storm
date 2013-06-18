package com.punktde.typo3storm;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Transient;
import com.punktde.typo3storm.helpers.IdeHelper;
import org.jetbrains.annotations.Nullable;



/**
 * Class implements a container for the settings of Typo3Storm plugin.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
@State(
        name = "Typo3StormSettings",    // must be equal to the class name I think
        storages = {
                @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
                @Storage(id = "dir", file = "/tmp/test/typo3storm.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public class Typo3StormSettings implements PersistentStateComponent<Typo3StormSettings> {

    // Those fields should be serialized
    public boolean enabled = true;
    public String pathToTypo3;



    @Transient // Do not serialize this field!
    protected Project project;



    public static Typo3StormSettings getInstance(Project project) {
        Typo3StormSettings settings = ServiceManager.getService(project, Typo3StormSettings.class);

        if(settings == null){
            IdeHelper.logError("Cannot find Typo3Storm Settings");
        }

        settings.project = project;

        return settings;
    }



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
            IdeHelper.logError(e.getMessage());
            IdeHelper.showDialog(null,"Cannot read the saved settings for Typo3Storm. Please try saving them again from File > Settings > Typo3Storm", "Error in Typo3Storm Settings");
        }
    }



    public boolean isEnabled() {
        return enabled;
    }



    public String getPathToTypo3() {
        return pathToTypo3;
    }



    public void setPathToTypo3(String pathToTypo3) {
        this.pathToTypo3 = pathToTypo3;
    }

}

