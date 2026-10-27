package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
public final class ClassTooLargeException extends IndexOutOfBoundsException {
    private final String className;
    private final int constantPoolCount;

    public ClassTooLargeException(String str, int i) {
        super("Class too large: " + str);
        this.className = str;
        this.constantPoolCount = i;
    }

    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }
}
