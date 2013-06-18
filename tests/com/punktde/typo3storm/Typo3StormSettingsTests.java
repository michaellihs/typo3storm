package com.punktde.typo3storm;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.FakeVirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.VirtualFileImpl;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.xmlb.XmlSerializer;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.PicoContainer;
import org.picocontainer.PicoVerificationException;
import org.picocontainer.PicoVisitor;
import org.picocontainer.alternatives.CachingPicoContainer;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Class implements a testcase for the typo3storm settings class.
 *
 * @author Michael Knoll (mimi@kaktusteam.de)
 */
public class Typo3StormSettingsTests {

    @Test
    public void settingsCanBeSerialized() {
        Typo3StormSettings settings = new Typo3StormSettings();
        settings.setPathToTypo3("/tmp");
        XmlSerializer.serialize(settings);
    }

}
