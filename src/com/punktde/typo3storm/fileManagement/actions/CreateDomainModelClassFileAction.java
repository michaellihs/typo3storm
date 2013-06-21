package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.PathBuilder;
import com.punktde.typo3storm.fileManagement.Typo3StormTemplateFactory;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Properties;

/**
 * Class implements an action that creates a domain model class file.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateDomainModelClassFileAction implements CreateFileAction {

    protected final static String EXTENDED_CLASS = "Tx_Extbase_DomainObject_AbstractEntity";



    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        final PathBuilder pathBuilder = new PathBuilder(createFileInfo);
        properties.put("CLASSNAME", pathBuilder.getClassName());
        properties.put("EXTENDS", EXTENDED_CLASS);

        PsiFile domainModelClassFile = Typo3StormTemplateFactory.createFileFromTemplate(
                pathBuilder.getClassFilePath(),
                properties,
                createFileInfo.fileName,
                Typo3StormFileTemplate.PhpClass,
                createFileInfo.project
        );

        if (domainModelClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + pathBuilder.getClassFilePath() + "/" + createFileInfo.fileName);
        }

        if (createFileInfo.createUnitTest) {
            new CreateUnitTestClassFileAction().createFile(pathBuilder.getUnitTestCreateFileInfo());
        }

        if (createFileInfo.createFunctionalTest) {
            new CreateFunctionalTestClassFileAction().createFile(pathBuilder.getFunctionalTestCreateFileInfo());
        }

        return domainModelClassFile;
    }

}
