package org.apache.tools.ant.types;

/* JADX INFO: loaded from: classes.dex */
public class FileSet extends AbstractFileSet {
    public FileSet() {
    }

    protected FileSet(FileSet fileset) {
        super(fileset);
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public Object clone() {
        if (isReference()) {
            return ((FileSet) getRef(getProject())).clone();
        }
        return super.clone();
    }
}
