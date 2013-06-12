package com.punktde.typo3storm.fileManagement.actions;

import com.intellij.psi.PsiFile;
import com.punktde.typo3storm.models.CreateFileInfo;

/**
 * Interface for actions that create files.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public interface CreateFileAction {

    public PsiFile createFile(CreateFileInfo createFileInfo);

}
