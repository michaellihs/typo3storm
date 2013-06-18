package com.punktde.typo3storm;

import com.magicento.actions.IMagicentoAction;
import com.magicento.actions.MagicentoActionAbstract;
import com.intellij.openapi.actionSystem.*;
//import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.impl.ActionManagerImpl;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

import java.util.ArrayList;
import java.util.List;

/**
 * General typo3storm action for Alt+T (Option+T)
 *
 * @author Enrique Piatti
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class Typo3StormAction extends MagicentoActionAbstract implements IMagicentoAction {

    public void executeAction() {
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        List<AnAction> actions = getTypo3StormContextActions();
        if( actions.size() > 0){
            for (AnAction action : actions) {
                actionGroup.add(action);
            }
            final ListPopup popup =
                    JBPopupFactory.getInstance().createActionGroupPopup(
                            "Typo3Storm Actions",
                            actionGroup,
                            getDataContext(),
                            JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                            false);

            popup.showInBestPositionFor(getDataContext());
        }
    }



    /**
     * Define Typo3Storm actions to be listed when using Alt+T (Option+T)
     * @return
     */
    protected List<AnAction> getTypo3StormContextActions() {
        List<AnAction> actions = new ArrayList<AnAction>();
        // Action ids are registered in plugin.xml in the id attribute of the action tag.
        String[] actionIds = {
                "Typo3Storm.createNewFileAction"
        };
        ActionManager actionManager;
        // TODO get rid of this error
        // getInstance() is missing in the included phpStorm environment. It is still running in PHPStorm
        actionManager = ActionManagerImpl.getInstance();
        for (String actionId : actionIds) {
            AnAction action = actionManager.getAction(actionId);
            // if( ((IMagicentoAction)action).isApplicable(getEvent()) ) {
                actions.add(action);
            //}
        }
        return actions;
    }



    @Override
    public Boolean isApplicable(AnActionEvent anActionEvent) {
        return true;
    }

}
