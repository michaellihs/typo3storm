package com.punktde.typo3storm.util;

import org.junit.Test;
import org.junit.Assert;

/**
 * Class implements testcase for file helpers.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class StringUtilsTests {

    @Test
    public void fileNameWithoutSuffixReturnsExpectedFileName() throws Exception {
        String fileNameWithSuffix = "TestFile.php";
        String expectedFileName = "TestFile";
        Assert.assertEquals("Returned file name is not the one expected", expectedFileName, StringUtils.fileNameWithoutSuffix(fileNameWithSuffix));
    }



    @Test
    public void testPathToClassName() throws Exception {
        String extension = "pt_extlist";
        String path = "Classes/Controller/";
        String fileName = "ListController.php";

        String expectedClassName = "Tx_PtExtlist_Controller_ListController";

        Assert.assertEquals("Returned class name is not the one expected", expectedClassName, StringUtils.pathToExtbaseClassName(extension, path, fileName));
    }



    @Test
    public void upperCaseFirstReturnsStringWithUppercaseFirstChar() {
        String input = "test";
        String expectedOutput = "Test";

        Assert.assertEquals("UpperCaseFirst did not return expected output", expectedOutput, StringUtils.upperCaseFirst(input));
    }



    @Test
    public void concatPartsUppercaseReturnsExpectedString() {
        String[] input = new String[] {"first", "second", "third"};
        String expectedOutput = "FirstSecondThird";

        Assert.assertEquals("concatPartsUpperCase did not return expected output", expectedOutput, StringUtils.concatPartsUppercase(input));
    }



    @Test
    public void dashToUpperCaseReturnsExpectedString() {
        String input = "this_is_my_string";
        String expectedOutput = "ThisIsMyString";

        Assert.assertEquals("dashToUpperCase did not return expected output", expectedOutput, StringUtils.dashToUpperCase(input));
    }



    @Test
    public void concatPartsDashedReturnsExpectedString() {
        String[] input = new String[]{"first", "second", "third"};
        String expectedOutput = "first_second_third";

        Assert.assertEquals("concatPartsDashed did not return expected string", expectedOutput, StringUtils.concatPartsDashed(input));
    }



    @Test
    public void pathToDashedUpperCaseReturnsExpectedString() {
        String input = "Domain/Model/List";
        String expectedOutput = "Domain_Model_List";

        Assert.assertEquals("pathToDashedUpperCase did not return expected output", expectedOutput, StringUtils.pathToDashedUpperCase(input));
    }



    @Test
    public void removeLeadingClassesReturnsExpectedString() {
        String input = "Classes/Domain/Model";
        String expectedOutput = "Domain/Model";

        Assert.assertEquals("removeLeadingClasses did not return expected output", expectedOutput, StringUtils.removeLeadingClasses(input));
    }

}
