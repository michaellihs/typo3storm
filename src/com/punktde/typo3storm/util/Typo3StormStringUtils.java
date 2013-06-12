package com.punktde.typo3storm.util;

/**
 * Class implements some helper methods for string manipulation.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class Typo3StormStringUtils {

    public static final String EXTENSION_CLASS_SUFFIX = "Tx";



    /**
     * Returns the given file name without suffix
     *
     * E.g. fileName.php --> fileName
     *
     * @param fileName File name to remove suffix from
     * @return File name without suffix
     */
    public static String fileNameWithoutSuffix(String fileName) {
        String fileNameWithoutSuffix;
        fileNameWithoutSuffix = fileName.replace(".php", "");
        return fileNameWithoutSuffix;
    }



    /**
     * Returns an Extbase style class name for a given extension, path and fileName
     *
     * E.g. (pt_extlist, "Classes/Controller", "ListController.php") --> "Tx_PtExtlist_Controller_ListController"
     *
     * @param path Path to class (a leading "Classes/" will be removed)
     * @param fileName File name of class (preceding ".php" will be removed)
     * @return Created class name for given properties
     */
    public static String pathToExtbaseClassName(String extensionName, String path, String fileName) {
        String[] pathToBeConcatenatedByDashes = new String[]{
                EXTENSION_CLASS_SUFFIX,
                dashToUpperCase(extensionName),
                pathToDashedUpperCase(removeLeadingClasses(path)),
                fileNameWithoutSuffix(fileName)
        };
        return concatPartsDashed(pathToBeConcatenatedByDashes);
    }



    /**
     * Removes leading "Classes/" from a path
     *
     * E.g. "Classes/Domain/Model" --> "Domain/Model"
     *
     * @param path Path which probably starts with "Classes/"
     * @return Path without "Classes/" at the beginning
     */
    public static String removeLeadingClasses(String path) {
        return path.replaceFirst("Classes/", "");
    }



    /**
     * Transforms a path to a dash-separated upper camel case string:
     *
     * E.g. "Domain/Model/List/" --> "Domain_Model_List"
     *
     * Removes leading and preceding slashes:
     *
     * E.g. "/Domain/Model/List/" --> "Domain_Model_List"
     *
     * @param path Path to be transformed
     * @return Transformed path
     */
    public static String pathToDashedUpperCase(String path) {
        return concatPartsDashed(path.split("/"));
    }



    /**
     * Returns a concatenated string for a given array of string parts. Each string part is uppercased in resulting string.
     *
     * E.g. {"first", "second", "third"} --> "FirstSecondThird"
     *
     * @param parts String parts to be concatenated
     * @return Concatenated string parts using upper camel case
     */
    public static String concatPartsUppercase(String[] parts) {
        String uppercasedParts = "";

        for (String part : parts) {
            uppercasedParts += upperCaseFirst(part);
        }

        return uppercasedParts;
    }



    /**
     * Transforms an array of strings into a concatenated string with dash ('_') as splitter.
     *
     * E.g. {"first", "second", "third"} --> "first_second_third"
     *
     * @param parts String parts to be concatenated
     * @return Concatenated string parts using dash as splitter
     */
    public static String concatPartsDashed(String[] parts) {
        String dashedConcatenatedParts = "";

        for (String part : parts) {
            dashedConcatenatedParts += part + "_";
        }

        return dashedConcatenatedParts.substring(0, dashedConcatenatedParts.length() - 1);
    }



    /**
     * Returns given string with uppercased first letter
     *
     * E.g. "test" --> "Test"
     *
     * @param string String to be transformed
     * @return String with upper case first letter
     */
    public static String upperCaseFirst(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }



    /**
     * Transforms strings with dashes to upper camel case.
     *
     * E.g. "pt_extlist" --> "PtExtlist"
     *
     * @param dashedString
     * @return
     */
    public static String dashToUpperCase(String dashedString) {
        return concatPartsUppercase(dashedString.split("_"));
    }

}
