package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class OrSelector extends BaseSelectorContainer {
    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (hasSelectors()) {
            buf.append("{orselect: ");
            buf.append(super.toString());
            buf.append("}");
        }
        return buf.toString();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        Enumeration e = selectorElements();
        while (e.hasMoreElements()) {
            boolean result = ((FileSelector) e.nextElement()).isSelected(basedir, filename, file);
            if (result) {
                return true;
            }
        }
        return false;
    }
}
