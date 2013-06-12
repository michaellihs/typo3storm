package com.punktde.typo3storm.fileManagement;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiManager;
import com.punktde.typo3storm.enums.Typo3StormFileTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Class implements a factory that writes files using templates.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class Typo3StormTemplateFactory {

    /**
     * Writes a file for given parameters
     *
     * @param psiDirectory Directory to which file should be written
     * @param customProperties A map of properties to be used as variables in given file template
     * @param fileName File name of created file
     * @param template Template to be used for created file
     * @return Created file
     */
    public static PsiFile createFileFromTemplate(final PsiDirectory psiDirectory, Properties customProperties, String fileName, Typo3StormFileTemplate template) {
        // TODO refactor me - I have too many lines of code!
        String templateFileName = template.getFileName();
        FileTemplate fileTemplate;
        FileTemplateManager fileTemplateManager = FileTemplateManager.getInstance();

        if (template.isJ2eeTemplate()) {
            fileTemplate = fileTemplateManager.getJ2eeTemplate(templateFileName);
        } else {
            fileTemplate = fileTemplateManager.getInternalTemplate(templateFileName);
        }
        Properties properties = new Properties(fileTemplateManager.getDefaultProperties());
        if (customProperties != null) {
            properties.putAll(customProperties);
        }
        String fileContent;
        try {
            fileContent = fileTemplate.getText(properties);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load template for " + templateFileName, e);
        }
        final PsiFileFactory factory = PsiFileFactory.getInstance(psiDirectory.getProject());
        final PsiFile file = factory.createFileFromText(fileName, fileContent);

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run()
            {
                psiDirectory.add(file);
            }
        });

        return psiDirectory.findFile(fileName);
    }



    /**
     * Writes a file for given parameters
     *
     * @param directoryPath Path to write file to
     * @param customProperties Properties to be used as variables for file template
     * @param fileName File name of created file
     * @param template Template to be used for created file
     * @param project Current project
     * @return Created file
     */
    public static PsiFile createFileFromTemplate(String directoryPath, Properties customProperties, String fileName, Typo3StormFileTemplate template, Project project) {
        VirtualFile directory = LocalFileSystem.getInstance().findFileByIoFile(new File(directoryPath));
        if(directory == null){
            try {
                directory = VfsUtil.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(directory != null)
        {
            final PsiDirectory psiDirectory = PsiManager.getInstance(project).findDirectory(directory);
            if(psiDirectory != null)
            {
                if(psiDirectory.findFile(fileName) == null)
                {
                    return createFileFromTemplate(psiDirectory, customProperties, fileName, template);
                }
                else {
                    String message = "File " + directoryPath + "/" + fileName + " already exists";
                    Messages.showInfoMessage("Cannot create new file", message);
                }
            }
        }
        return null;
    }

}
