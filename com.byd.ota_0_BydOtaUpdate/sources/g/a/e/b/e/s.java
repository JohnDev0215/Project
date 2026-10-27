package g.a.e.b.e;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class s extends g.a.b.j0.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f5689d;
    public final byte[] s;
    public final byte[] x;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q f5690a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte[] f5691b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f5692c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte[] f5693d = null;

        public b(q qVar) {
            this.f5690a = qVar;
        }

        public s e() {
            return new s(this);
        }

        public b f(byte[] bArr) {
            this.f5692c = t.c(bArr);
            return this;
        }

        public b g(byte[] bArr) {
            this.f5691b = t.c(bArr);
            return this;
        }
    }

    public s(b bVar) {
        super(false);
        q qVar = bVar.f5690a;
        this.f5689d = qVar;
        Objects.requireNonNull(qVar, "params == null");
        int iC = qVar.c();
        byte[] bArr = bVar.f5693d;
        if (bArr != null) {
            if (bArr.length != iC + iC) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            this.s = t.g(bArr, 0, iC);
            this.x = t.g(bArr, iC + 0, iC);
            return;
        }
        byte[] bArr2 = bVar.f5691b;
        if (bArr2 == null) {
            this.s = new byte[iC];
        } else {
            if (bArr2.length != iC) {
                throw new IllegalArgumentException("length of root must be equal to length of digest");
            }
            this.s = bArr2;
        }
        byte[] bArr3 = bVar.f5692c;
        if (bArr3 == null) {
            this.x = new byte[iC];
        } else {
            if (bArr3.length != iC) {
                throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
            }
            this.x = bArr3;
        }
    }

    public q b() {
        return this.f5689d;
    }

    public byte[] c() {
        return t.c(this.x);
    }

    public byte[] d() {
        return t.c(this.s);
    }

    public byte[] e() {
        int iC = this.f5689d.c();
        byte[] bArr = new byte[iC + iC];
        t.e(bArr, this.s, 0);
        t.e(bArr, this.x, iC + 0);
        return bArr;
    }
}
