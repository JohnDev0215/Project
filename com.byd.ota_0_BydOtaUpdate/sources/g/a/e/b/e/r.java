package g.a.e.b.e;

import g.a.e.b.e.f;
import java.io.IOException;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.BDS;

/* JADX INFO: loaded from: classes.dex */
public final class r extends g.a.b.j0.b {
    public final byte[] E3;
    public final BDS F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f5679d;
    public final byte[] s;
    public final byte[] x;
    public final byte[] y;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q f5680a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5681b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f5682c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte[] f5683d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public byte[] f5684e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public byte[] f5685f = null;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public BDS f5686g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public byte[] f5687h = null;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public q f5688i = null;

        public b(q qVar) {
            this.f5680a = qVar;
        }

        public r j() {
            return new r(this);
        }

        public b k(BDS bds) {
            this.f5686g = bds;
            return this;
        }

        public b l(int i2) {
            this.f5681b = i2;
            return this;
        }

        public b m(byte[] bArr) {
            this.f5684e = t.c(bArr);
            return this;
        }

        public b n(byte[] bArr) {
            this.f5685f = t.c(bArr);
            return this;
        }

        public b o(byte[] bArr) {
            this.f5683d = t.c(bArr);
            return this;
        }

        public b p(byte[] bArr) {
            this.f5682c = t.c(bArr);
            return this;
        }
    }

    public r(b bVar) {
        super(true);
        q qVar = bVar.f5680a;
        this.f5679d = qVar;
        Objects.requireNonNull(qVar, "params == null");
        int iC = qVar.c();
        byte[] bArr = bVar.f5687h;
        if (bArr != null) {
            Objects.requireNonNull(bVar.f5688i, "xmss == null");
            int iD = qVar.d();
            int iA = g.a.f.f.a(bArr, 0);
            if (!t.l(iD, iA)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            this.s = t.g(bArr, 4, iC);
            int i2 = 4 + iC;
            this.x = t.g(bArr, i2, iC);
            int i3 = i2 + iC;
            this.y = t.g(bArr, i3, iC);
            int i4 = i3 + iC;
            this.E3 = t.g(bArr, i4, iC);
            int i5 = i4 + iC;
            try {
                BDS bds = (BDS) t.f(t.g(bArr, i5, bArr.length - i5), BDS.class);
                bds.setXMSS(bVar.f5688i);
                bds.validate();
                if (bds.getIndex() != iA) {
                    throw new IllegalStateException("serialized BDS has wrong index");
                }
                this.F3 = bds;
                return;
            } catch (IOException e2) {
                throw new IllegalArgumentException(e2.getMessage(), e2);
            } catch (ClassNotFoundException e3) {
                throw new IllegalArgumentException(e3.getMessage(), e3);
            }
        }
        byte[] bArr2 = bVar.f5682c;
        if (bArr2 == null) {
            this.s = new byte[iC];
        } else {
            if (bArr2.length != iC) {
                throw new IllegalArgumentException("size of secretKeySeed needs to be equal size of digest");
            }
            this.s = bArr2;
        }
        byte[] bArr3 = bVar.f5683d;
        if (bArr3 == null) {
            this.x = new byte[iC];
        } else {
            if (bArr3.length != iC) {
                throw new IllegalArgumentException("size of secretKeyPRF needs to be equal size of digest");
            }
            this.x = bArr3;
        }
        byte[] bArr4 = bVar.f5684e;
        if (bArr4 == null) {
            this.y = new byte[iC];
        } else {
            if (bArr4.length != iC) {
                throw new IllegalArgumentException("size of publicSeed needs to be equal size of digest");
            }
            this.y = bArr4;
        }
        byte[] bArr5 = bVar.f5685f;
        if (bArr5 == null) {
            this.E3 = new byte[iC];
        } else {
            if (bArr5.length != iC) {
                throw new IllegalArgumentException("size of root needs to be equal size of digest");
            }
            this.E3 = bArr5;
        }
        BDS bds2 = bVar.f5686g;
        if (bds2 != null) {
            this.F3 = bds2;
        } else {
            this.F3 = (bVar.f5681b >= (1 << qVar.d()) + (-2) || bArr4 == null || bArr2 == null) ? new BDS(qVar, bVar.f5681b) : new BDS(qVar, bArr4, bArr2, (f) new f.b().l(), bVar.f5681b);
        }
    }

    public q b() {
        return this.f5679d;
    }

    public byte[] c() {
        int iC = this.f5679d.c();
        byte[] bArr = new byte[iC + 4 + iC + iC + iC];
        g.a.f.f.d(this.F3.getIndex(), bArr, 0);
        t.e(bArr, this.s, 4);
        int i2 = 4 + iC;
        t.e(bArr, this.x, i2);
        int i3 = i2 + iC;
        t.e(bArr, this.y, i3);
        t.e(bArr, this.E3, i3 + iC);
        try {
            return g.a.f.a.m(bArr, t.o(this.F3));
        } catch (IOException e2) {
            throw new RuntimeException("error serializing bds state: " + e2.getMessage());
        }
    }
}
