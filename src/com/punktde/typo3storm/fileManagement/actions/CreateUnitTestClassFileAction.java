package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.PathBuilder;
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
        final PathBuilder pathBuilder = new PathBuilder(createFileInfo);

        properties.put("AUTHOR", getAuthor());
        properties.put("PACKAGE", "Tests");
        properties.put("SUB_PACKAGE", "Unit\\" + getSubpackageByClassName(pathBuilder.getClassName()));
        properties.put("CLASSNAME", this.getUnitTestClassName(pathBuilder.getClassName()));
        properties.put("EXTENDS", EXTENDED_CLASS);
        properties.put("TESTED_CLASS", Typo3StormStringUtils.removeSubstringFromEndOfString(pathBuilder.getClassName(), "Test"));

        PsiFile unitTestClassFile = Typo3StormTemplateFactory.createFileFromTemplate(
                pathBuilder.getClassFilePath(),
                properties,
                createFileInfo.fileName,
                Typo3StormFileTemplate.UnitTest,
                createFileInfo.project
        );

        if (unitTestClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + pathBuilder.getClassFilePath() + "/" + createFileInfo.fileName);
        }

        return unitTestClassFile;
    }


}
