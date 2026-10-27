package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ClassFileEntry {
    protected static final ClassFileEntry[] NONE = new ClassFileEntry[0];
    private boolean resolved;

    protected abstract void doWrite(DataOutputStream dataOutputStream) throws IOException;

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();

    protected ClassFileEntry[] getNestedClassFileEntries() {
        return NONE;
    }

    protected void resolve(ClassConstantPool classConstantPool) {
        this.resolved = true;
    }

    protected int objectHashCode() {
        return super.hashCode();
    }

    public final void write(DataOutputStream dataOutputStream) throws IOException {
        if (!this.resolved) {
            throw new IllegalStateException("Entry has not been resolved");
        }
        doWrite(dataOutputStream);
    }
}
