package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.Typo3StormTemplateFactory;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Properties;

/**
 * Class implements create class file action for functional test classes.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateFunctionalTestClassFileAction extends AbstractCreateFileAction {

    private static String EXTENDED_CLASS = "Tx_PtExtbase_Tests_Functional_AbstractBaseTestcase";



    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        String fileName = createFileInfo.fileName;
        String fileNameWithoutPhp = fileName.contains(".php") ? fileName.substring(0, fileName.indexOf(".php")) : fileName;
        String className = Typo3StormStringUtils.pathToExtbaseClassName(createFileInfo.extensionInfo.name, createFileInfo.path, fileNameWithoutPhp);
        properties.put("AUTHOR", getAuthor());
        properties.put("PACKAGE", "Tests");
        properties.put("SUB_PACKAGE", "Functional\\" + getSubpackageByClassName(className));
        properties.put("CLASSNAME", this.getFunctionalTestClassName(className));
        properties.put("EXTENDS", EXTENDED_CLASS);
        properties.put("TESTED_CLASS", Typo3StormStringUtils.removeSubstringFromEndOfString(className, "Test"));

        String path = createFileInfo.extensionInfo.getPath() + "/Tests/Functional/" + createFileInfo.path;

        PsiFile functionalTestClassFile = Typo3StormTemplateFactory.createFileFromTemplate(path, properties, createFileInfo.fileName, Typo3StormFileTemplate.FunctionalTest, createFileInfo.project);
        if (functionalTestClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + path + "/" + createFileInfo.fileName);
        }

        return functionalTestClassFile;
    }

}
