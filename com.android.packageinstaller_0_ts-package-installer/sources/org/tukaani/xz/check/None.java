package org.tukaani.xz.check;

/* JADX INFO: loaded from: classes2.dex */
public class None extends Check {
    public None() {
        this.size = 0;
        this.name = "None";
    }

    @Override // org.tukaani.xz.check.Check
    public void update(byte[] buf, int off, int len) {
    }

    @Override // org.tukaani.xz.check.Check
    public byte[] finish() {
        byte[] empty = new byte[0];
        return empty;
    }
}
