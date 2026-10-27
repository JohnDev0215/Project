package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class l implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g.a.b.e f4807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f4808b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4809c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4810d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4811e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4812f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4813g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4814h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4815i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public final byte[] p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;

    public l() {
        this.f4808b = new byte[1];
        this.p = new byte[16];
        this.q = 0;
        this.f4807a = null;
    }

    public l(g.a.b.e eVar) {
        this.f4808b = new byte[1];
        this.p = new byte[16];
        this.q = 0;
        if (eVar.b() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.f4807a = eVar;
    }

    public static final long a(int i2, int i3) {
        return (((long) i2) & 4294967295L) * ((long) i3);
    }

    public final void b() {
        int i2 = this.q;
        if (i2 < 16) {
            this.p[i2] = 1;
            for (int i3 = i2 + 1; i3 < 16; i3++) {
                this.p[i3] = 0;
            }
        }
        long j = ((long) g.a.f.f.j(this.p, 0)) & 4294967295L;
        long j2 = ((long) g.a.f.f.j(this.p, 4)) & 4294967295L;
        long j3 = ((long) g.a.f.f.j(this.p, 8)) & 4294967295L;
        long j4 = 4294967295L & ((long) g.a.f.f.j(this.p, 12));
        int i4 = (int) (((long) this.r) + (j & 67108863));
        this.r = i4;
        this.s = (int) (((long) this.s) + ((((j2 << 32) | j) >>> 26) & 67108863));
        this.t = (int) (((long) this.t) + (((j2 | (j3 << 32)) >>> 20) & 67108863));
        this.u = (int) (((long) this.u) + ((((j4 << 32) | j3) >>> 14) & 67108863));
        int i5 = (int) (((long) this.v) + (j4 >>> 8));
        this.v = i5;
        if (this.q == 16) {
            this.v = i5 + 16777216;
        }
        long jA = a(i4, this.f4809c) + a(this.s, this.k) + a(this.t, this.j) + a(this.u, this.f4815i) + a(this.v, this.f4814h);
        long jA2 = a(this.r, this.f4810d) + a(this.s, this.f4809c) + a(this.t, this.k) + a(this.u, this.j) + a(this.v, this.f4815i);
        long jA3 = a(this.r, this.f4811e) + a(this.s, this.f4810d) + a(this.t, this.f4809c) + a(this.u, this.k) + a(this.v, this.j);
        long jA4 = a(this.r, this.f4812f) + a(this.s, this.f4811e) + a(this.t, this.f4810d) + a(this.u, this.f4809c) + a(this.v, this.k);
        long jA5 = a(this.r, this.f4813g) + a(this.s, this.f4812f) + a(this.t, this.f4811e) + a(this.u, this.f4810d) + a(this.v, this.f4809c);
        int i6 = ((int) jA) & 67108863;
        this.r = i6;
        long j5 = jA2 + (jA >>> 26);
        int i7 = ((int) j5) & 67108863;
        this.s = i7;
        long j6 = jA3 + (j5 >>> 26);
        this.t = ((int) j6) & 67108863;
        long j7 = jA4 + (j6 >>> 26);
        this.u = ((int) j7) & 67108863;
        long j8 = jA5 + (j7 >>> 26);
        this.v = ((int) j8) & 67108863;
        int i8 = i6 + (((int) (j8 >>> 26)) * 5);
        this.r = i8;
        this.s = i7 + (i8 >>> 26);
        this.r = i8 & 67108863;
    }

    public final void c(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i2 = 16;
        if (this.f4807a != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int iJ = g.a.f.f.j(bArr, 0);
        int iJ2 = g.a.f.f.j(bArr, 4);
        int iJ3 = g.a.f.f.j(bArr, 8);
        int iJ4 = g.a.f.f.j(bArr, 12);
        this.f4809c = 67108863 & iJ;
        int i3 = ((iJ >>> 26) | (iJ2 << 6)) & 67108611;
        this.f4810d = i3;
        int i4 = ((iJ2 >>> 20) | (iJ3 << 12)) & 67092735;
        this.f4811e = i4;
        int i5 = ((iJ3 >>> 14) | (iJ4 << 18)) & 66076671;
        this.f4812f = i5;
        int i6 = (iJ4 >>> 8) & 1048575;
        this.f4813g = i6;
        this.f4814h = i3 * 5;
        this.f4815i = i4 * 5;
        this.j = i5 * 5;
        this.k = i6 * 5;
        g.a.b.e eVar = this.f4807a;
        if (eVar != null) {
            byte[] bArr3 = new byte[16];
            eVar.init(true, new s0(bArr, 16, 16));
            this.f4807a.a(bArr2, 0, bArr3, 0);
            i2 = 0;
            bArr = bArr3;
        }
        this.l = g.a.f.f.j(bArr, i2 + 0);
        this.m = g.a.f.f.j(bArr, i2 + 4);
        this.n = g.a.f.f.j(bArr, i2 + 8);
        this.o = g.a.f.f.j(bArr, i2 + 12);
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        if (i2 + 16 > bArr.length) {
            throw new OutputLengthException("Output buffer is too short.");
        }
        if (this.q > 0) {
            b();
        }
        int i3 = this.s;
        int i4 = this.r;
        int i5 = i3 + (i4 >>> 26);
        this.s = i5;
        int i6 = i4 & 67108863;
        this.r = i6;
        int i7 = this.t + (i5 >>> 26);
        this.t = i7;
        int i8 = i5 & 67108863;
        this.s = i8;
        int i9 = this.u + (i7 >>> 26);
        this.u = i9;
        int i10 = i7 & 67108863;
        this.t = i10;
        int i11 = this.v + (i9 >>> 26);
        this.v = i11;
        int i12 = i9 & 67108863;
        this.u = i12;
        int i13 = i6 + ((i11 >>> 26) * 5);
        this.r = i13;
        int i14 = i11 & 67108863;
        this.v = i14;
        int i15 = i8 + (i13 >>> 26);
        this.s = i15;
        int i16 = i13 & 67108863;
        this.r = i16;
        int i17 = i16 + 5;
        int i18 = (i17 >>> 26) + i15;
        int i19 = (i18 >>> 26) + i10;
        int i20 = (i19 >>> 26) + i12;
        int i21 = 67108863 & i20;
        int i22 = ((i20 >>> 26) + i14) - 67108864;
        int i23 = (i22 >>> 31) - 1;
        int i24 = ~i23;
        int i25 = (i16 & i24) | (i17 & 67108863 & i23);
        this.r = i25;
        int i26 = (i15 & i24) | (i18 & 67108863 & i23);
        this.s = i26;
        int i27 = (i10 & i24) | (i19 & 67108863 & i23);
        this.t = i27;
        int i28 = (i21 & i23) | (i12 & i24);
        this.u = i28;
        int i29 = (i14 & i24) | (i22 & i23);
        this.v = i29;
        long j = (((long) (i25 | (i26 << 26))) & 4294967295L) + (((long) this.l) & 4294967295L);
        long j2 = (((long) ((i26 >>> 6) | (i27 << 20))) & 4294967295L) + (((long) this.m) & 4294967295L);
        long j3 = (((long) ((i27 >>> 12) | (i28 << 14))) & 4294967295L) + (((long) this.n) & 4294967295L);
        long j4 = (((long) ((i28 >>> 18) | (i29 << 8))) & 4294967295L) + (4294967295L & ((long) this.o));
        g.a.f.f.g((int) j, bArr, i2);
        long j5 = j2 + (j >>> 32);
        g.a.f.f.g((int) j5, bArr, i2 + 4);
        long j6 = j3 + (j5 >>> 32);
        g.a.f.f.g((int) j6, bArr, i2 + 8);
        g.a.f.f.g((int) (j4 + (j6 >>> 32)), bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        if (this.f4807a == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.f4807a.getAlgorithmName();
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return 16;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        byte[] bArrA;
        if (this.f4807a == null) {
            bArrA = null;
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            w0 w0Var = (w0) iVar;
            bArrA = w0Var.a();
            iVar = w0Var.b();
        }
        if (!(iVar instanceof s0)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        c(((s0) iVar).a(), bArrA);
        reset();
    }

    @Override // g.a.b.t
    public void reset() {
        this.q = 0;
        this.v = 0;
        this.u = 0;
        this.t = 0;
        this.s = 0;
        this.r = 0;
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        byte[] bArr = this.f4808b;
        bArr[0] = b2;
        update(bArr, 0, 1);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = 0;
        while (i3 > i4) {
            if (this.q == 16) {
                b();
                this.q = 0;
            }
            int iMin = Math.min(i3 - i4, 16 - this.q);
            System.arraycopy(bArr, i4 + i2, this.p, this.q, iMin);
            i4 += iMin;
            this.q += iMin;
        }
    }
}
