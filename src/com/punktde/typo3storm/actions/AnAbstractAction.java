package com.punktde.typo3storm.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.punktde.typo3storm.enums.FileType;
import com.punktde.typo3storm.fileManagement.actions.CreateFileAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mimi
 * Date: 15.06.13
 * Time: 01:50
 * To change this template use File | Settings | File Templates.
 */
public abstract class AnAbstractAction extends AnAction {


    protected Project project;



    protected DataContext dataContext;



    protected AnActionEvent event;



    protected Map<FileType, CreateFileAction> createFileActions = new HashMap<FileType, CreateFileAction>();



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
