package com.punktde.typo3storm.models;

import com.intellij.openapi.project.Project;
import com.punktde.typo3storm.enums.FileType;

/**
 * Class implements a Data Transfer Object for the information
 * gathered by the create file dialog.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateFileInfo {

    final public ExtensionInfo extensionInfo;
    final public FileType fileType;
    final public String path;
    final public String fileName;
    final public Boolean createUnitTest;
    final public Boolean createFunctionalTest;
    final public Project project;



    public CreateFileInfo(ExtensionInfo extensionInfo, FileType fileType, String path, String fileName, boolean createUnitTest, boolean createFunctionalTest, Project project) {
        this.extensionInfo = extensionInfo;
        this.fileType = fileType;
        this.path = path;
        this.fileName = fileName;
        this.createUnitTest = createUnitTest;
        this.createFunctionalTest = createFunctionalTest;
        this.project = project;
    }




}
