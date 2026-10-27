package g.a.e.b.e;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class n extends g.a.b.j0.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f5670d;
    public final byte[] s;
    public final byte[] x;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l f5671a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte[] f5672b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f5673c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte[] f5674d = null;

        public b(l lVar) {
            this.f5671a = lVar;
        }

        public n e() {
            return new n(this);
        }

        public b f(byte[] bArr) {
            this.f5673c = t.c(bArr);
            return this;
        }

        public b g(byte[] bArr) {
            this.f5672b = t.c(bArr);
            return this;
        }
    }

    public n(b bVar) {
        super(false);
        l lVar = bVar.f5671a;
        this.f5670d = lVar;
        Objects.requireNonNull(lVar, "params == null");
        int iB = lVar.b();
        byte[] bArr = bVar.f5674d;
        if (bArr != null) {
            if (bArr.length != iB + iB) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            this.s = t.g(bArr, 0, iB);
            this.x = t.g(bArr, iB + 0, iB);
            return;
        }
        byte[] bArr2 = bVar.f5672b;
        if (bArr2 == null) {
            this.s = new byte[iB];
        } else {
            if (bArr2.length != iB) {
                throw new IllegalArgumentException("length of root must be equal to length of digest");
            }
            this.s = bArr2;
        }
        byte[] bArr3 = bVar.f5673c;
        if (bArr3 == null) {
            this.x = new byte[iB];
        } else {
            if (bArr3.length != iB) {
                throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
            }
            this.x = bArr3;
        }
    }

    public l b() {
        return this.f5670d;
    }

    public byte[] c() {
        return t.c(this.x);
    }

    public byte[] d() {
        return t.c(this.s);
    }

    public byte[] e() {
        int iB = this.f5670d.b();
        byte[] bArr = new byte[iB + iB];
        t.e(bArr, this.s, 0);
        t.e(bArr, this.x, iB + 0);
        return bArr;
    }
}
