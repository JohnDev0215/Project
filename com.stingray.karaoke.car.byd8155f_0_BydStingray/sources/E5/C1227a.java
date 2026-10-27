package e5;

import P8.n;
import java.io.OutputStream;

/* JADX INFO: renamed from: e5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1227a extends OutputStream {
    public final String toString() {
        return "ByteStreams.nullOutputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i7) {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        bArr.getClass();
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i10) {
        bArr.getClass();
        n.s(i7, i10 + i7, bArr.length);
    }
}
