package com.punktde.typo3storm.models;

import com.punktde.typo3storm.enums.FileType;

/**
 * Class implements a Data Transfer Object for the information
 * gathered by the create file dialog.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateFileInfo {

    final public FileType fileType;
    final public String fileName;
    final public Boolean unitTest;
    final public Boolean functionalTest;


    public CreateFileInfo(FileType fileType, String fileName, boolean unitTest, boolean functionalTest) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.unitTest = unitTest;
        this.functionalTest = functionalTest;
    }

}
