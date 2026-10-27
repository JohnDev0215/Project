package org.chromium.mojo.system;

/* JADX INFO: loaded from: classes3.dex */
public class MojoException extends RuntimeException {
    private final int mCode;

    public MojoException(int i) {
        this.mCode = i;
    }

    public MojoException(Throwable th) {
        super(th);
        this.mCode = 2;
    }

    public int getMojoResult() {
        return this.mCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "MojoResult(" + this.mCode + "): " + MojoResult.describe(this.mCode);
    }
}
