package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.Typo3StormTemplateFactory;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: mimi
 * Date: 14.06.13
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class CreateRepositoryClassFileAction implements CreateFileAction {

    protected final String EXTENDED_CLASS = "Tx_Extbase_Persistence_Repository";

    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        properties.put("CLASSNAME", Typo3StormStringUtils.pathToExtbaseClassName(createFileInfo.extensionInfo.name, "Domain/Repository", createFileInfo.fileName));
        properties.put("EXTENDS", EXTENDED_CLASS);
        String path = createFileInfo.extensionInfo.getPath() + "/Classes/Domain/Repository";
        PsiFile repositoryClassFile = Typo3StormTemplateFactory.createFileFromTemplate(path, properties, createFileInfo.fileName, Typo3StormFileTemplate.PhpClass, createFileInfo.project);
        if (repositoryClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + path + "/" + createFileInfo.fileName);
        }
        return repositoryClassFile;
    }

}
