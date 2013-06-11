package com.punktde.typo3storm.enums;

/**
 * Enums for file types to be handled by create file action and dialog.
 *
 * @url http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html for documentation
 */
public enum FileType {

    CONTROLLER("Controller", true, true),
    DOMAIN_MODEL ("Domain Model", true, true),
    REPOSITORY ("Repository", true, true),
    TEMPLATE ("Fluid Template", false, false),
    PARTIAL ("Fluid Partial", false, false);



    private String displayName;
    private boolean canHaveUnitTest;
    private boolean canHaveFunctionalTest;



    FileType(String displayName, boolean canHaveUnitTest, boolean canHaveFunctionalTest) {
        this.displayName = displayName;
        this.canHaveUnitTest = canHaveUnitTest;
        this.canHaveFunctionalTest = canHaveFunctionalTest;
    }



    public String displayName() {
        return this.displayName;
    }



    public boolean canHaveUnitTest() {
        return this.canHaveUnitTest;
    }



    public boolean isCanHaveFunctionalTest() {
        return this.canHaveFunctionalTest;
    }



    public String toString() {
        return this.displayName();
    }

}
