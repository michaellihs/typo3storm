package com.punktde.typo3storm.extensions;

import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import com.punktde.typo3storm.helpers.IdeHelper;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.php.lang.psi.resolve.types.PhpType;
import com.jetbrains.php.lang.psi.resolve.types.PhpTypeProvider;



/**
 * Class implements a type provider that enables auto-completion for
 *
 * t3lib_div::makeInstance('className');
 *
 * For further information on PSI etc. see http://confluence.jetbrains.com/display/PhpStorm/PHP+Open+API
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class MakeInstanceTypeProvider implements PhpTypeProvider {

    @Nullable
    @Override
    public PhpType getType(PsiElement e) {
        if (DumbService.getInstance(e.getProject()).isDumb()) {
            return null;
        }

        if( ! IdeHelper.isPhpWithAutocompleteFeature()){
            return null;
        }

        // Make sure, given element is a static method reference
        if (e instanceof MethodReference && ((MethodReference)e).isStatic()) {
            MethodReference methodReference = (MethodReference)e;
            // Make sure, given element is a makeInstance method call of t3lib_div class
            if (methodReference.getClassReference().getName().equals("t3lib_div") && methodReference.getName().equals("makeInstance")) {
                // Get parameter list of method
                PsiElement[] parameters = methodReference.getParameters();
                if (parameters.length > 0) {
                    // Get first parameter of parameter list
                    PsiElement parameter = parameters[0];
                    if (parameter instanceof StringLiteralExpression) {
                        // Get content of first parameter
                        String param = ((StringLiteralExpression)parameter).getContents();
                        if (StringUtil.isNotEmpty(param)) {
                            // Return new type from first parameter
                            return new PhpType().add(param);
                        }
                    }
                }
            }
        }

        return null;
    }

}

