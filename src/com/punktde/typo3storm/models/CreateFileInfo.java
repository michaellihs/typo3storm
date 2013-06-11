package com.punktde.typo3storm.models;

/**
 * Created with IntelliJ IDEA.
 * User: mimi
 * Date: 12.06.13
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class CreateFileInfo {

    private String fileType;
    private String fileName;
    private Boolean unitTest;
    private Boolean functionalTest;



    public String getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public Boolean createUnitTest() {
        return unitTest;
    }

    public Boolean createFunctionalTest() {
        return functionalTest;
    }

}
