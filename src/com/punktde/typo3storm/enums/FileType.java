package com.punktde.typo3storm.enums;

/**
 * Enums for file types to be handled by create file action and dialog.
 *
 * @url http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html for documentation
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public enum FileType {

    Controller("Controller", "Classes/Controllers/", true, true),
    DomainModel("Domain Model", "Classes/Domain/Model/", true, true),
    Repository("Repository", "Classes/Domain/Repository/", true, true),
    UnitTest("Unit Test", "Tests/Unit/", false, false),
    FunctionalTest("Functional Test", "Tests/Functional/", false, false),
    Layout("Fluid Layout", "Resources/Private/Layouts/", false, false),
    Template("Fluid Template", "Resources/Private/Templates/", false, false),
    Partial("Fluid Partial", "Resources/Private/Partials/", false, false);



    final public String displayName;
    final public String defaultPath;
    final public boolean canHaveUnitTest;
    final public boolean canHaveFunctionalTest;



    FileType(String displayName, String defaultPath, boolean canHaveUnitTest, boolean canHaveFunctionalTest) {
        this.displayName = displayName;
        this.defaultPath = defaultPath;
        this.canHaveUnitTest = canHaveUnitTest;
        this.canHaveFunctionalTest = canHaveFunctionalTest;
    }

}
