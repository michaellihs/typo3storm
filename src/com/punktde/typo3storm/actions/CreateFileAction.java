package com.punktde.typo3storm.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.punktde.typo3storm.models.CreateFileInfo;
import com.punktde.typo3storm.ui.dialogs.CreateFileDialog;

/**
 * Class implements an action to create new files.
 *
 * @author Michael Knoll mimi@kaktusteam.de
 */
public class CreateFileAction extends AnAction {

    protected Project project;



    protected DataContext dataContext;



    protected AnActionEvent event;



    public void actionPerformed(AnActionEvent e) {

        final Project project = getProject();
        final CreateFileDialog dialog = new CreateFileDialog(project);

        dialog.show();

        if( dialog.isOK()) {
            this.handleCreateFileAction(dialog.getCreateFileInfo());
        }


    }



    private void handleCreateFileAction(CreateFileInfo createFileInfo) {

    }



    public Project getProject() {
        if(this.project == null){
            if(getDataContext() != null){
                //_project = (Project) getDataContext().getData(DataConstants.PROJECT); // DataKeys.PROJECT.getData(getDataContext());
                this.project = PlatformDataKeys.PROJECT.getData(getDataContext());
            }
        }
        return this.project;
    }



    public DataContext getDataContext() {
        if(dataContext == null){
            if(getEvent() != null){
                dataContext = getEvent().getDataContext();
            }
        }
        return dataContext;
    }



    public AnActionEvent getEvent() {
        return this.event;
    }

}
