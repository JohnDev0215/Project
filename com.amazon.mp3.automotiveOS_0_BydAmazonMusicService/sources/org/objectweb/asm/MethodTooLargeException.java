package org.objectweb.asm;

/* JADX INFO: loaded from: classes6.dex */
public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private final String className;
    private final int codeSize;
    private final String descriptor;
    private final String methodName;

    public MethodTooLargeException(String str, String str2, String str3, int i) {
        super("Method too large: " + str + "." + str2 + " " + str3);
        this.className = str;
        this.methodName = str2;
        this.descriptor = str3;
        this.codeSize = i;
    }

    public int getCodeSize() {
        return this.codeSize;
    }
}
