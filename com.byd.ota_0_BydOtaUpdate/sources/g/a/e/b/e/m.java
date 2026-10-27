package g.a.e.b.e;

import java.io.IOException;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;

/* JADX INFO: loaded from: classes.dex */
public final class m extends g.a.b.j0.b {
    public final byte[] E3;
    public final byte[] F3;
    public final BDSStateMap G3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f5660d;
    public final long s;
    public final byte[] x;
    public final byte[] y;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final l f5661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f5662b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte[] f5663c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte[] f5664d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public byte[] f5665e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public byte[] f5666f = null;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public BDSStateMap f5667g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public byte[] f5668h = null;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public q f5669i = null;

        public b(l lVar) {
            this.f5661a = lVar;
        }

        public m j() {
            return new m(this);
        }

        public b k(BDSStateMap bDSStateMap) {
            this.f5667g = bDSStateMap;
            return this;
        }

        public b l(long j) {
            this.f5662b = j;
            return this;
        }

        public b m(byte[] bArr) {
            this.f5665e = t.c(bArr);
            return this;
        }

        public b n(byte[] bArr) {
            this.f5666f = t.c(bArr);
            return this;
        }

        public b o(byte[] bArr) {
            this.f5664d = t.c(bArr);
            return this;
        }

        public b p(byte[] bArr) {
            this.f5663c = t.c(bArr);
            return this;
        }
    }

    public m(b bVar) {
        super(true);
        l lVar = bVar.f5661a;
        this.f5660d = lVar;
        Objects.requireNonNull(lVar, "params == null");
        int iB = lVar.b();
        byte[] bArr = bVar.f5668h;
        if (bArr != null) {
            Objects.requireNonNull(bVar.f5669i, "xmss == null");
            int iC = lVar.c();
            int i2 = (iC + 7) / 8;
            long jA = t.a(bArr, 0, i2);
            this.s = jA;
            if (!t.l(iC, jA)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            int i3 = i2 + 0;
            this.x = t.g(bArr, i3, iB);
            int i4 = i3 + iB;
            this.y = t.g(bArr, i4, iB);
            int i5 = i4 + iB;
            this.E3 = t.g(bArr, i5, iB);
            int i6 = i5 + iB;
            this.F3 = t.g(bArr, i6, iB);
            int i7 = i6 + iB;
            try {
                BDSStateMap bDSStateMap = (BDSStateMap) t.f(t.g(bArr, i7, bArr.length - i7), BDSStateMap.class);
                bDSStateMap.setXMSS(bVar.f5669i);
                this.G3 = bDSStateMap;
                return;
            } catch (IOException e2) {
                throw new IllegalArgumentException(e2.getMessage(), e2);
            } catch (ClassNotFoundException e3) {
                throw new IllegalArgumentException(e3.getMessage(), e3);
            }
        }
        this.s = bVar.f5662b;
        byte[] bArr2 = bVar.f5663c;
        if (bArr2 == null) {
            this.x = new byte[iB];
        } else {
            if (bArr2.length != iB) {
                throw new IllegalArgumentException("size of secretKeySeed needs to be equal size of digest");
            }
            this.x = bArr2;
        }
        byte[] bArr3 = bVar.f5664d;
        if (bArr3 == null) {
            this.y = new byte[iB];
        } else {
            if (bArr3.length != iB) {
                throw new IllegalArgumentException("size of secretKeyPRF needs to be equal size of digest");
            }
            this.y = bArr3;
        }
        byte[] bArr4 = bVar.f5665e;
        if (bArr4 == null) {
            this.E3 = new byte[iB];
        } else {
            if (bArr4.length != iB) {
                throw new IllegalArgumentException("size of publicSeed needs to be equal size of digest");
            }
            this.E3 = bArr4;
        }
        byte[] bArr5 = bVar.f5666f;
        if (bArr5 == null) {
            this.F3 = new byte[iB];
        } else {
            if (bArr5.length != iB) {
                throw new IllegalArgumentException("size of root needs to be equal size of digest");
            }
            this.F3 = bArr5;
        }
        BDSStateMap bDSStateMap2 = bVar.f5667g;
        if (bDSStateMap2 == null) {
            if (!t.l(lVar.c(), bVar.f5662b) || bArr4 == null || bArr2 == null) {
                this.G3 = new BDSStateMap();
                return;
            }
            bDSStateMap2 = new BDSStateMap(lVar, bVar.f5662b, bArr4, bArr2);
        }
        this.G3 = bDSStateMap2;
    }

    public l b() {
        return this.f5660d;
    }

    public byte[] c() {
        int iB = this.f5660d.b();
        int iC = (this.f5660d.c() + 7) / 8;
        byte[] bArr = new byte[iC + iB + iB + iB + iB];
        t.e(bArr, t.p(this.s, iC), 0);
        int i2 = iC + 0;
        t.e(bArr, this.x, i2);
        int i3 = i2 + iB;
        t.e(bArr, this.y, i3);
        int i4 = i3 + iB;
        t.e(bArr, this.E3, i4);
        t.e(bArr, this.F3, i4 + iB);
        try {
            return g.a.f.a.m(bArr, t.o(this.G3));
        } catch (IOException e2) {
            throw new IllegalStateException("error serializing bds state: " + e2.getMessage(), e2);
        }
    }
}
