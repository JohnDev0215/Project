package defpackage;

import defpackage.d83;

/* JADX INFO: compiled from: TextFormatEscaper.java */
/* JADX INFO: loaded from: classes.dex */
public final class e83 implements d83.b {
    public final /* synthetic */ byte[] a;

    public e83(byte[] bArr) {
        this.a = bArr;
    }

    @Override // d83.b
    public final byte a(int i) {
        return this.a[i];
    }

    @Override // d83.b
    public final int size() {
        return this.a.length;
    }
}
