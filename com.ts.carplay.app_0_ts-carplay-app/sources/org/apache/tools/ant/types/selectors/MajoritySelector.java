package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class MajoritySelector extends BaseSelectorContainer {
    private boolean allowtie = true;

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (hasSelectors()) {
            buf.append("{majorityselect: ");
            buf.append(super.toString());
            buf.append("}");
        }
        return buf.toString();
    }

    public void setAllowtie(boolean tiebreaker) {
        this.allowtie = tiebreaker;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        int yesvotes = 0;
        int novotes = 0;
        Enumeration e = selectorElements();
        while (e.hasMoreElements()) {
            boolean result = ((FileSelector) e.nextElement()).isSelected(basedir, filename, file);
            if (result) {
                yesvotes++;
            } else {
                novotes++;
            }
        }
        if (yesvotes > novotes) {
            return true;
        }
        if (novotes > yesvotes) {
            return false;
        }
        return this.allowtie;
    }
}
