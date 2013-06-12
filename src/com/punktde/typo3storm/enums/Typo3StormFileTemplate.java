package com.punktde.typo3storm.enums;

/**
 * Enums for available file templates
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public enum Typo3StormFileTemplate {

    /**
     * Define all templates available in this plugin here
     */
    Controller("typo3storm_controller.php", true),
    DomainModel("typo3storm_domainModelPlugin.php", true),
    Repository("typo3storm_repository.php", true),
    PhpClass("typo3storm_phpClass.php", true),
    UnitTest("typo3storm_unitTest.php", true),
    FunctionalTest("typo3storm_functionalTest.php", true);

    String fileName;



    boolean isJ2eeTemplate;


    /**
     * Constructor for enum type.
     *
     * @param fileName Holds file name of the corresponding template (located in resources/FileTemplates</j2ee>)
     * @param isJ2eeTemplate If set to true, template should be handled as a j2ee template
     */
    Typo3StormFileTemplate(String fileName, boolean isJ2eeTemplate) {
        this.fileName = fileName;
        this.isJ2eeTemplate = isJ2eeTemplate;
    }


    /**
     * Returns file name of corresponding template
     * @return File name of corresponding template
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * Returns true if corresponding template file should be handled as j2ee template
     * @return True, if corresponding template file should be handled as j2ee template
     */
    public boolean isJ2eeTemplate() {
        return isJ2eeTemplate;
    }
}