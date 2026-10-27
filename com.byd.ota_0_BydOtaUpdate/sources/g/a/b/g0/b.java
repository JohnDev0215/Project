package g.a.b.g0;

import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class b implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f4752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.i0.a f4753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4754f;

    public b(g.a.b.e eVar) {
        this(eVar, 8, (eVar.b() * 8) / 2, null);
    }

    public b(g.a.b.e eVar, int i2, int i3, g.a.b.i0.a aVar) {
        this.f4753e = null;
        if (i3 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.f4749a = new byte[eVar.b()];
        j jVar = new j(eVar, i2);
        this.f4752d = jVar;
        this.f4753e = aVar;
        this.f4754f = i3 / 8;
        this.f4750b = new byte[jVar.b()];
        this.f4751c = 0;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        int iB = this.f4752d.b();
        g.a.b.i0.a aVar = this.f4753e;
        if (aVar == null) {
            while (true) {
                int i3 = this.f4751c;
                if (i3 >= iB) {
                    break;
                }
                this.f4750b[i3] = 0;
                this.f4751c = i3 + 1;
            }
        } else {
            aVar.b(this.f4750b, this.f4751c);
        }
        this.f4752d.e(this.f4750b, 0, this.f4749a, 0);
        this.f4752d.c(this.f4749a);
        System.arraycopy(this.f4749a, 0, bArr, i2, this.f4754f);
        reset();
        return this.f4754f;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4752d.a();
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4754f;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        reset();
        this.f4752d.d(iVar);
    }

    @Override // g.a.b.t
    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4750b;
            if (i2 >= bArr.length) {
                this.f4751c = 0;
                this.f4752d.f();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        int i2 = this.f4751c;
        byte[] bArr = this.f4750b;
        if (i2 == bArr.length) {
            this.f4752d.e(bArr, 0, this.f4749a, 0);
            this.f4751c = 0;
        }
        byte[] bArr2 = this.f4750b;
        int i3 = this.f4751c;
        this.f4751c = i3 + 1;
        bArr2[i3] = b2;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = this.f4752d.b();
        int i4 = this.f4751c;
        int i5 = iB - i4;
        if (i3 > i5) {
            System.arraycopy(bArr, i2, this.f4750b, i4, i5);
            this.f4752d.e(this.f4750b, 0, this.f4749a, 0);
            this.f4751c = 0;
            i3 -= i5;
            i2 += i5;
            while (i3 > iB) {
                this.f4752d.e(bArr, i2, this.f4749a, 0);
                i3 -= iB;
                i2 += iB;
            }
        }
        System.arraycopy(bArr, i2, this.f4750b, this.f4751c, i3);
        this.f4751c += i3;
    }
}
