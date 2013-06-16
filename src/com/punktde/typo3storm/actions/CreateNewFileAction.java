package com.punktde.typo3storm.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.fileManagement.actions.*;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.ui.dialogs.CreateFileDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implements an action to create new files within TYPO3 project.
 *
 * @author Michael Knoll mimi@kaktusteam.de
 */
public class CreateNewFileAction extends AnAbstractAction {

    public CreateNewFileAction() {
        super();

        // Initialize the file type to create action mapping
        this.createFileActions.put(FileType.DomainModel, new CreateDomainModelClassFileAction());
        this.createFileActions.put(FileType.Controller, new CreateControllerClassFileAction());
        this.createFileActions.put(FileType.Repository, new CreateRepositoryClassFileAction());
        this.createFileActions.put(FileType.UnitTest, new CreateUnitTestClassFileAction());
        this.createFileActions.put(FileType.FunctionalTest, new CreateFunctionalTestClassFileAction());
    }


    public void actionPerformed(AnActionEvent e) {

        this.event = e;

        final Project project = getProject();
        final CreateFileDialog dialog = new CreateFileDialog(project);

        dialog.show();

        if( dialog.isOK()) {
            this.handleCreateFileAction(dialog.getCreateFileInfo());
        }


    }



    private void handleCreateFileAction(CreateFileInfo createFileInfo) {
        com.punktde.typo3storm.fileManagement.actions.CreateFileAction createFileAction = this.createFileActions.get(createFileInfo.fileType);
        PsiFile psiFile;
        if (createFileAction != null) {
            psiFile = createFileAction.createFile(createFileInfo);
        } else {
            throw new RuntimeException("No action could be found to handle creation of " + createFileInfo.fileType);
        }

    }

}
