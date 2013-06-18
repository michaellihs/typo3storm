package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;
import com.punktde.typo3storm.fileManagement.Typo3StormTemplateFactory;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.util.Typo3StormStringUtils;

import java.util.Properties;

/**
 * Class implements an action that creates an extbase controller class file.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class CreateControllerClassFileAction implements CreateFileAction {

    protected final static String EXTENDED_CLASS = "Tx_PtExtbase_Controller_AbstractActionController";



    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        properties.put("CLASSNAME", Typo3StormStringUtils.pathToExtbaseClassName(createFileInfo.extensionInfo.name, "Controller", createFileInfo.fileName));
        properties.put("EXTENDS", EXTENDED_CLASS);
        String path = createFileInfo.extensionInfo.getPath() + "/Classes/Controller";
        PsiFile controllerClassFile = Typo3StormTemplateFactory.createFileFromTemplate(path, properties, createFileInfo.fileName, Typo3StormFileTemplate.PhpClass, createFileInfo.project);
        if (controllerClassFile == null) {
            throw new RuntimeException("Could not create new file with path " + path + "/" + createFileInfo.fileName);
        }
        return controllerClassFile;
    }

}
