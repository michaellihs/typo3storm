package com.punktde.typo3storm.fileManagement.actions;

import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Arrays;

/**
 * Abstract base class for create class file action classes.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public abstract class AbstractCreateFileAction implements CreateFileAction {

    private static String AUTHOR = "Michael Knoll <mimi@kaktusteam.de>";



    protected String getAuthor() {
        // TODO replace with author from settings
        return AUTHOR;
    }



    protected String getSubpackageByClassName(String className) {
        String[] fileNameParts = className.split("_");
        return Typo3StormStringUtils.implode(Arrays.copyOfRange(fileNameParts, 2, fileNameParts.length), "\\");
    }



    protected String getUnitTestClassName(String className) {
        return replaceTestString(className, "_Tests_Unit_");
    }



    protected String getFunctionalTestClassName(String className) {
        return replaceTestString(className, "_Tests_Functional_");
    }



    protected String replaceTestString(String className, String testString) {
        String[] fileNameParts = className.split("_");
        String unitTestClassName = fileNameParts[0] + "_" + fileNameParts[1];
        unitTestClassName += testString;
        unitTestClassName += Typo3StormStringUtils.implode(Arrays.copyOfRange(fileNameParts, 2, fileNameParts.length), "_");
        return unitTestClassName;
    }

}
