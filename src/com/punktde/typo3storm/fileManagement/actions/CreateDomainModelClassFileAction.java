package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.models.CreateFileInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: mimi
 * Date: 12.06.13
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public class CreateDomainModelClassFileAction implements CreateFileAction {

    @Override
    public PsiFile createFile(CreateFileInfo createFileInfo) {
        final Properties properties = new Properties();
        properties.put("CLASSNAME", "Tx_Test_Domain_Model_" + createFileInfo.fileName.replace(".php", ""));
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
