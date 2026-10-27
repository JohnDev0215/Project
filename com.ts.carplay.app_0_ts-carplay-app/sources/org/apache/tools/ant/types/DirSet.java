package org.apache.tools.ant.types;

/* JADX INFO: loaded from: classes.dex */
public class DirSet extends AbstractFileSet {
    public DirSet() {
    }

    protected DirSet(DirSet dirset) {
        super(dirset);
    }

    @Override // org.apache.tools.ant.types.AbstractFileSet
    public Object clone() {
        if (isReference()) {
            return ((DirSet) getRef(getProject())).clone();
        }
        return super.clone();
    }
}
