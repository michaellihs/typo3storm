package com.punktde.typo3storm.fileManagement;

import com.intellij.openapi.project.Project;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.fileManagement.actions.CreateUnitTestClassFileAction;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.models.ExtensionInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Arrays;

/**
 * Class implements a path builder that creates
 */
public class PathBuilder {

    private final ExtensionInfo extensionInfo;
    private final String path;
    private final String fileName;
    private final FileType fileType;



    public PathBuilder(CreateFileInfo createFileInfo) {
        this(createFileInfo.extensionInfo, createFileInfo.path, createFileInfo.fileName, createFileInfo.fileType);
    }



    public PathBuilder(ExtensionInfo extensionInfo, String path, String fileName, FileType fileType) {
        this.extensionInfo = extensionInfo;
        this.path = path;
        this.fileName = fileName;
        this.fileType = fileType;
    }



    public String getClassName() {
        String path = this.fileType.defaultPath + (this.path != "" ? "/" + this.path : "");
        return Typo3StormStringUtils.pathToExtbaseClassName(this.extensionInfo.name, path, this.fileName);
    }



    public String getClassFilePath() {
        String classFilePath = extensionInfo.getPath() + "/" + fileType.defaultPath;
        classFilePath += (this.path != "" ? "/" + this.path : "");
        return classFilePath;
    }



    public CreateFileInfo getUnitTestCreateFileInfo() {
        String fileNameWithoutPhp = fileName.contains(".php") ? getFileNameWithoutFileEnding() : fileName;
        String relativePathInTestsDirectory = fileType.defaultPath.replaceFirst("Classes/", "") + (this.path != "" ? "/" + this.path : "");

        CreateFileInfo unitTestFileInfo = new CreateFileInfo(extensionInfo, FileType.UnitTest, relativePathInTestsDirectory, fileNameWithoutPhp + "Test.php", false, false, extensionInfo.project);
        return unitTestFileInfo;
    }



    public CreateFileInfo getFunctionalTestCreateFileInfo() {
        String fileNameWithoutPhp = fileName.contains(".php") ? getFileNameWithoutFileEnding() : fileName;
        String relativePathInTestsDirectory = fileType.defaultPath.replaceFirst("Classes/", "") + (this.path != "" ? "/" + this.path : "");

        CreateFileInfo functionalTestFileInfo = new CreateFileInfo(extensionInfo, FileType.FunctionalTest, relativePathInTestsDirectory, fileNameWithoutPhp + "Test.php", false, false, extensionInfo.project);
        return functionalTestFileInfo;
    }


    /**
     * Removes file ending from file name
     *
     * @return File name without ending
     */
    public String getFileNameWithoutFileEnding() {
        String[] dotSeparatedFileParts = fileName.split("\\.");
        // TODO put this into Typo3StringUtils
        return Typo3StormStringUtils.implode(Arrays.copyOfRange(dotSeparatedFileParts, 0, dotSeparatedFileParts.length -1), ".");
    }

}
