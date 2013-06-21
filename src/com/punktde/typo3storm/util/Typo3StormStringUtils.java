package com.punktde.typo3storm.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang.WordUtils;

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
     * Returns a path for a given file name
     *
     * E.g. "Tx_PtExtbase_Domain_Model_FeUser.php" --> "Domain/Model/FeUser.php"
     *
     * @param fileName Name of the file to be converted to a path
     * @return Converted path for given file name
     */
    public static String fileNameToPath(String fileName) {
        String pathForFileName = "";
        String[] fileNameParts = fileName.split("_");
        // String pathForFileName = camelCaseToDashedString(fileNameParts[1]) + "/";
        for (int i = 2; i < fileNameParts.length; i++) {
            pathForFileName += fileNameParts[i] + "/";
        }
        pathForFileName = pathForFileName.substring(0, pathForFileName.length() - 1);
        return pathForFileName;
    }


    /**
     * Converts UpperCamelCase into upper_camel_case
     *
     * @param camelCaseString String to be transformed to lower underscore
     * @return String transformed into lower underscore
     */
    public static String camelCaseToDashedString(String camelCaseString) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camelCaseString);
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



    /**
     * Replaces last occurrence of toReplace in given string with replacement
     *
     * @param string String in which to replace last occurrence of toReplace
     * @param toReplace Substring to be searched in string
     * @param replacement String to replace substring with
     * @return String with replaced toReplace
     */
    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }



    /**
     * Removes "Test" from the end of the given string (only if given string contains "Test" at the end of the string
     *
     * @param string String to search substring at the end
     * @param substring Substring to be replaced at the end of string
     * @return String with ending replaced
     */
    public static String removeSubstringFromEndOfString(String string, String substring) {
        return string.endsWith(substring) ? string.substring(0,string.length() - substring.length()) : string;
    }


    /**
     * Combines elements of given array with given glue string.
     * @param inputArray Array to be imploded
     * @param glueString Glue to be used for implosion
     * @return Imploded string
     */
    public static String implode(String[] inputArray, String glueString) {
        String output = "";
        if (inputArray.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(inputArray[0]);
            for (int i=1; i<inputArray.length; i++) {
                sb.append(glueString);
                sb.append(inputArray[i]);
            }
            output = sb.toString();
        }
        return output;
    }


}
