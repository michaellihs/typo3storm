package com.punktde.typo3storm;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class Typo3StormProjectComponent implements ProjectComponent {

    private Project project;



    public static Typo3StormProjectComponent getInstance(Project project) {
        return project.getComponent(Typo3StormProjectComponent.class);
    }



    public Typo3StormProjectComponent(Project project) {
        this.project = project;
    }



    public String getDefaultPathToTypo3() {
        String projectPath = project.getBaseDir().getPath();
        if(projectPath == null || projectPath.isEmpty()){
            projectPath = project.getPresentableUrl();
        }
        return projectPath;
    }



    @Override
    public void projectOpened() {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void projectClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void initComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public void disposeComponent() {

    }



    @NotNull
    @Override
    public String getComponentName() {
        return "Typo3StormProjectComponent";
    }

}
