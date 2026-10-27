package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class m implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f4818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4820e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4821f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f4822g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f4823h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4824i;
    public int j;
    public int k;

    public m() {
        this.f4824i = 0L;
        this.j = 0;
        this.k = 0;
        this.f4816a = 2;
        this.f4817b = 4;
    }

    public m(int i2, int i3) {
        this.f4824i = 0L;
        this.j = 0;
        this.k = 0;
        this.f4816a = i2;
        this.f4817b = i3;
    }

    public static long d(long j, int i2) {
        return (j >>> (-i2)) | (j << i2);
    }

    public void a(int i2) {
        long jD = this.f4820e;
        long jD2 = this.f4821f;
        long jD3 = this.f4822g;
        long jD4 = this.f4823h;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = jD + jD2;
            long j2 = jD3 + jD4;
            long jD5 = d(jD2, 13) ^ j;
            long jD6 = d(jD4, 16) ^ j2;
            long j3 = j2 + jD5;
            jD = d(j, 32) + jD6;
            jD2 = d(jD5, 17) ^ j3;
            jD4 = d(jD6, 21) ^ jD;
            jD3 = d(j3, 32);
        }
        this.f4820e = jD;
        this.f4821f = jD2;
        this.f4822g = jD3;
        this.f4823h = jD4;
    }

    public long b() throws IllegalStateException, DataLengthException {
        long j = this.f4824i;
        int i2 = this.j;
        long j2 = j >>> ((7 - i2) << 3);
        this.f4824i = j2;
        long j3 = j2 >>> 8;
        this.f4824i = j3;
        this.f4824i = j3 | ((((long) ((this.k << 3) + i2)) & 255) << 56);
        c();
        this.f4822g ^= 255;
        a(this.f4817b);
        long j4 = ((this.f4820e ^ this.f4821f) ^ this.f4822g) ^ this.f4823h;
        reset();
        return j4;
    }

    public void c() {
        this.k++;
        this.f4823h ^= this.f4824i;
        a(this.f4816a);
        this.f4820e ^= this.f4824i;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        g.a.f.f.t(b(), bArr, i2);
        return 8;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "SipHash-" + this.f4816a + "-" + this.f4817b;
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return 8;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof s0)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] bArrA = ((s0) iVar).a();
        if (bArrA.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f4818c = g.a.f.f.n(bArrA, 0);
        this.f4819d = g.a.f.f.n(bArrA, 8);
        reset();
    }

    @Override // g.a.b.t
    public void reset() {
        long j = this.f4818c;
        this.f4820e = 8317987319222330741L ^ j;
        long j2 = this.f4819d;
        this.f4821f = 7237128888997146477L ^ j2;
        this.f4822g = j ^ 7816392313619706465L;
        this.f4823h = 8387220255154660723L ^ j2;
        this.f4824i = 0L;
        this.j = 0;
        this.k = 0;
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        long j = this.f4824i >>> 8;
        this.f4824i = j;
        this.f4824i = j | ((((long) b2) & 255) << 56);
        int i2 = this.j + 1;
        this.j = i2;
        if (i2 == 8) {
            c();
            this.j = 0;
        }
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        int i4 = i3 & (-8);
        int i5 = this.j;
        int i6 = 0;
        if (i5 == 0) {
            while (i6 < i4) {
                this.f4824i = g.a.f.f.n(bArr, i2 + i6);
                c();
                i6 += 8;
            }
            while (i6 < i3) {
                long j = this.f4824i >>> 8;
                this.f4824i = j;
                this.f4824i = j | ((((long) bArr[i2 + i6]) & 255) << 56);
                i6++;
            }
            this.j = i3 - i4;
            return;
        }
        int i7 = i5 << 3;
        int i8 = 0;
        while (i8 < i4) {
            long jN = g.a.f.f.n(bArr, i2 + i8);
            this.f4824i = (this.f4824i >>> (-i7)) | (jN << i7);
            c();
            this.f4824i = jN;
            i8 += 8;
        }
        while (i8 < i3) {
            long j2 = this.f4824i >>> 8;
            this.f4824i = j2;
            this.f4824i = j2 | ((((long) bArr[i2 + i8]) & 255) << 56);
            int i9 = this.j + 1;
            this.j = i9;
            if (i9 == 8) {
                c();
                this.j = 0;
            }
            i8++;
        }
    }
}
