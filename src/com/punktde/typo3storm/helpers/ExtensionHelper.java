package com.punktde.typo3storm.helpers;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.punktde.typo3storm.Typo3StormSettings;
import com.punktde.typo3storm.models.ExtensionInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements some helper methods for working with TYPO3 extensions
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class ExtensionHelper {

    public static String TYPO3_CONF_DIRECTORY = "typo3conf";



    public static String TYPO3_EXT_DIRECTORY = "ext";



    /**
     * Returns a list of extension information for the given project.
     *
     * @param project Project to get contained extensions from
     * @return List of extensions for given project
     */
    public static List<ExtensionInfo> getExtensionInfos(Project project) {
        List<ExtensionInfo> extensionInfos = new ArrayList<ExtensionInfo>();
        PsiDirectory extensionsDirectory = getExtensionDirectory(project);
        for (PsiDirectory extensionDirectory : extensionsDirectory.getSubdirectories()) {
            // TODO think about whether we have to check anything else here, before declaring a directory inside typo3conf/ext to be an extension (existence of ext_emconf.php ...)
            if (extensionDirectory.getName() != "..") {
                extensionInfos.add(new ExtensionInfo(project, extensionDirectory));
            }
        }
        return extensionInfos;
    }



    /**
     * Returns the extension directory for the given project
     *
     * TODO The strategy to determine base directory for extensions should be twofold:
     * TODO 1. Get base path from settings (project settings)
     * TODO 2. Use convention that PHPStorm project must be created in document root of TYPO3 instance
     *
     * @param project Project to get extension directory from
     * @return Extension directory for given project
     */
    public static PsiDirectory getExtensionDirectory(Project project) {
        Typo3StormSettings settings = Typo3StormSettings.getInstance(project);
        VirtualFile projectRootDirectory = LocalFileSystem.getInstance().findFileByIoFile(new File(settings.getPathToTypo3()));
        PsiDirectory projectDirectory = PsiManager.getInstance(project).findDirectory(projectRootDirectory);
        if (projectDirectory.findSubdirectory(TYPO3_CONF_DIRECTORY) == null) {
            throw new RuntimeException(TYPO3_CONF_DIRECTORY + " does not exist in current project path.");
        }
        if (projectDirectory.findSubdirectory(TYPO3_CONF_DIRECTORY).findSubdirectory(TYPO3_EXT_DIRECTORY) == null) {
            throw new RuntimeException(TYPO3_EXT_DIRECTORY + " does not exist in " + TYPO3_CONF_DIRECTORY + " within current project.");
        }
        PsiDirectory extensionDirectory = projectDirectory.findSubdirectory(TYPO3_CONF_DIRECTORY).findSubdirectory(TYPO3_EXT_DIRECTORY);
        return extensionDirectory;
    }

}
