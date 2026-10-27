package g.a.e.b.e;

import g.a.b.y;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g.a.b.n f5619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5620b;

    public d(g.a.b.n nVar, int i2) {
        Objects.requireNonNull(nVar, "digest == null");
        this.f5619a = nVar;
        this.f5620b = i2;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i2 = this.f5620b;
        if (length != i2) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == i2) {
            return d(0, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong in length");
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i2 = this.f5620b;
        if (length != i2) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == i2 * 2) {
            return d(1, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong in length");
    }

    public byte[] c(byte[] bArr, byte[] bArr2) {
        if (bArr.length != this.f5620b) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == 32) {
            return d(3, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong address length");
    }

    public final byte[] d(int i2, byte[] bArr, byte[] bArr2) {
        byte[] bArrP = t.p(i2, this.f5620b);
        this.f5619a.update(bArrP, 0, bArrP.length);
        this.f5619a.update(bArr, 0, bArr.length);
        this.f5619a.update(bArr2, 0, bArr2.length);
        int i3 = this.f5620b;
        byte[] bArr3 = new byte[i3];
        g.a.b.n nVar = this.f5619a;
        if (nVar instanceof y) {
            ((y) nVar).c(bArr3, 0, i3);
        } else {
            nVar.doFinal(bArr3, 0);
        }
        return bArr3;
    }
}
