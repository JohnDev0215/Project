package g.a.e.b.e;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[][] f5647a;

    public j(i iVar, byte[][] bArr) {
        Objects.requireNonNull(iVar, "params == null");
        Objects.requireNonNull(bArr, "publicKey == null");
        if (t.k(bArr)) {
            throw new NullPointerException("publicKey byte array == null");
        }
        if (bArr.length != iVar.c()) {
            throw new IllegalArgumentException("wrong publicKey size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != iVar.b()) {
                throw new IllegalArgumentException("wrong publicKey format");
            }
        }
        this.f5647a = t.d(bArr);
    }

    public byte[][] a() {
        return t.d(this.f5647a);
    }
}
