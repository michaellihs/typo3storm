package com.punktde.typo3storm.models;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;

import java.io.File;

/**
 * Class implements a container for TYPO3 extension information.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class ExtensionInfo {

    final public PsiDirectory rootDirectory;
    final public Project project;
    final public String name;



    public ExtensionInfo(Project project, String rootDirectory) {
        VirtualFile directory = LocalFileSystem.getInstance().findFileByIoFile(new File(rootDirectory));
        this.project = project;
        this.rootDirectory = PsiManager.getInstance(project).findDirectory(directory);
        this.name = this.rootDirectory.getName();
    }



    public ExtensionInfo(Project project, PsiDirectory rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.project = project;
        this.name = this.rootDirectory.getName();
    }



    public String toString() {
        return this.name;
    }



    public String getPath() {
        return this.rootDirectory.getVirtualFile().getPath();
    }

}
