package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.Typo3StormTemplateFactory;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Arrays;
import java.util.Properties;

/**
 * Class implements a create file action for unit tests.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateUnitTestClassFileAction extends AbstractCreateFileAction {

    private static String EXTENDED_CLASS = "Tx_PtExtbase_Tests_Unit_AbstractBaseTestcase";



    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        String fileName = createFileInfo.fileName;
        String fileNameWithoutPhp = fileName.contains(".php") ? fileName.substring(0, fileName.indexOf(".php")) : fileName;
        String className = Typo3StormStringUtils.pathToExtbaseClassName(createFileInfo.extensionInfo.name, createFileInfo.path, fileNameWithoutPhp);
        properties.put("AUTHOR", getAuthor());
        properties.put("PACKAGE", "Tests");
        properties.put("SUB_PACKAGE", "Unit\\" + getSubpackageByClassName(className));
        properties.put("CLASSNAME", this.getUnitTestClassName(className));
        properties.put("EXTENDS", EXTENDED_CLASS);
        properties.put("TESTED_CLASS", Typo3StormStringUtils.removeSubstringFromEndOfString(className, "Test"));
        String path = createFileInfo.extensionInfo.getPath() + "/Tests/Unit/" + createFileInfo.path;
        PsiFile unitTestClassFile = Typo3StormTemplateFactory.createFileFromTemplate(path, properties, createFileInfo.fileName, Typo3StormFileTemplate.UnitTest, createFileInfo.project);
        if (unitTestClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + path + "/" + createFileInfo.fileName);
        }
        return unitTestClassFile;
    }


}
