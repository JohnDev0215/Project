package g.a.e.b.e;

import g.a.e.b.e.f;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f5633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5634b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5635c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f5636d;

    public g(i iVar) {
        Objects.requireNonNull(iVar, "params == null");
        this.f5633a = iVar;
        int iB = iVar.b();
        this.f5634b = new d(iVar.a(), iB);
        this.f5635c = new byte[iB];
        this.f5636d = new byte[iB];
    }

    public final byte[] a(byte[] bArr, int i2, int i3, f fVar) {
        int iB = this.f5633a.b();
        Objects.requireNonNull(bArr, "startHash == null");
        if (bArr.length != iB) {
            throw new IllegalArgumentException("startHash needs to be " + iB + "bytes");
        }
        Objects.requireNonNull(fVar, "otsHashAddress == null");
        Objects.requireNonNull(fVar.d(), "otsHashAddress byte array == null");
        int i4 = i2 + i3;
        if (i4 > this.f5633a.d() - 1) {
            throw new IllegalArgumentException("max chain length must not be greater than w");
        }
        if (i3 == 0) {
            return bArr;
        }
        byte[] bArrA = a(bArr, i2, i3 - 1, fVar);
        f fVar2 = (f) new f.b().g(fVar.b()).h(fVar.c()).p(fVar.g()).n(fVar.e()).o(i4 - 1).f(0).l();
        byte[] bArrC = this.f5634b.c(this.f5636d, fVar2.d());
        byte[] bArrC2 = this.f5634b.c(this.f5636d, ((f) new f.b().g(fVar2.b()).h(fVar2.c()).p(fVar2.g()).n(fVar2.e()).o(fVar2.f()).f(1).l()).d());
        byte[] bArr2 = new byte[iB];
        for (int i5 = 0; i5 < iB; i5++) {
            bArr2[i5] = (byte) (bArrA[i5] ^ bArrC2[i5]);
        }
        return this.f5634b.a(bArrC, bArr2);
    }

    public final byte[] b(int i2) {
        if (i2 < 0 || i2 >= this.f5633a.c()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.f5634b.c(this.f5635c, t.p(i2, 32));
    }

    public d c() {
        return this.f5634b;
    }

    public i d() {
        return this.f5633a;
    }

    public j e(f fVar) {
        Objects.requireNonNull(fVar, "otsHashAddress == null");
        byte[][] bArr = new byte[this.f5633a.c()][];
        for (int i2 = 0; i2 < this.f5633a.c(); i2++) {
            fVar = (f) new f.b().g(fVar.b()).h(fVar.c()).p(fVar.g()).n(i2).o(fVar.f()).f(fVar.a()).l();
            bArr[i2] = a(b(i2), 0, this.f5633a.d() - 1, fVar);
        }
        return new j(this.f5633a, bArr);
    }

    public byte[] f() {
        return t.c(this.f5636d);
    }

    public byte[] g(byte[] bArr, f fVar) {
        return this.f5634b.c(bArr, ((f) new f.b().g(fVar.b()).h(fVar.c()).p(fVar.g()).l()).d());
    }

    public void h(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "secretKeySeed == null");
        if (bArr.length != this.f5633a.b()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        Objects.requireNonNull(bArr2, "publicSeed == null");
        if (bArr2.length != this.f5633a.b()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.f5635c = bArr;
        this.f5636d = bArr2;
    }
}
