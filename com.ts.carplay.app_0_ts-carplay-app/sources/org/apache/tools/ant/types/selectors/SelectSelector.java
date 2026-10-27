package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.util.Enumeration;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class SelectSelector extends BaseSelectorContainer {
    private String ifProperty;
    private String unlessProperty;

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (hasSelectors()) {
            buf.append("{select");
            if (this.ifProperty != null) {
                buf.append(" if: ");
                buf.append(this.ifProperty);
            }
            if (this.unlessProperty != null) {
                buf.append(" unless: ");
                buf.append(this.unlessProperty);
            }
            buf.append(" ");
            buf.append(super.toString());
            buf.append("}");
        }
        return buf.toString();
    }

    private SelectSelector getRef() {
        Object o = getCheckedRef(getClass(), "SelectSelector");
        return (SelectSelector) o;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public boolean hasSelectors() {
        if (isReference()) {
            return getRef().hasSelectors();
        }
        return super.hasSelectors();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public int selectorCount() {
        if (isReference()) {
            return getRef().selectorCount();
        }
        return super.selectorCount();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public FileSelector[] getSelectors(Project p) {
        if (isReference()) {
            return getRef().getSelectors(p);
        }
        return super.getSelectors(p);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public Enumeration selectorElements() {
        if (isReference()) {
            return getRef().selectorElements();
        }
        return super.selectorElements();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.SelectorContainer
    public void appendSelector(FileSelector selector) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        super.appendSelector(selector);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        int cnt = selectorCount();
        if (cnt < 0 || cnt > 1) {
            setError("Only one selector is allowed within the <selector> tag");
        }
    }

    public boolean passesConditions() {
        if (this.ifProperty == null || getProject().getProperty(this.ifProperty) != null) {
            return this.unlessProperty == null || getProject().getProperty(this.unlessProperty) == null;
        }
        return false;
    }

    public void setIf(String ifProperty) {
        this.ifProperty = ifProperty;
    }

    public void setUnless(String unlessProperty) {
        this.unlessProperty = unlessProperty;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelectorContainer, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        if (!passesConditions()) {
            return false;
        }
        Enumeration e = selectorElements();
        if (!e.hasMoreElements()) {
            return true;
        }
        FileSelector f = (FileSelector) e.nextElement();
        return f.isSelected(basedir, filename, file);
    }
}
