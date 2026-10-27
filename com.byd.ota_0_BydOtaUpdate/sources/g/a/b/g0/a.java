package g.a.b.g0;

import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class a implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.e f4746d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.i0.a f4747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4748f;

    public a(g.a.b.e eVar) {
        this(eVar, (eVar.b() * 8) / 2, null);
    }

    public a(g.a.b.e eVar, int i2) {
        this(eVar, i2, null);
    }

    public a(g.a.b.e eVar, int i2, g.a.b.i0.a aVar) {
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.f4746d = new g.a.b.h0.b(eVar);
        this.f4747e = aVar;
        this.f4748f = i2 / 8;
        this.f4743a = new byte[eVar.b()];
        this.f4744b = new byte[eVar.b()];
        this.f4745c = 0;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        int iB = this.f4746d.b();
        if (this.f4747e == null) {
            while (true) {
                int i3 = this.f4745c;
                if (i3 >= iB) {
                    break;
                }
                this.f4744b[i3] = 0;
                this.f4745c = i3 + 1;
            }
        } else {
            if (this.f4745c == iB) {
                this.f4746d.a(this.f4744b, 0, this.f4743a, 0);
                this.f4745c = 0;
            }
            this.f4747e.b(this.f4744b, this.f4745c);
        }
        this.f4746d.a(this.f4744b, 0, this.f4743a, 0);
        System.arraycopy(this.f4743a, 0, bArr, i2, this.f4748f);
        reset();
        return this.f4748f;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4746d.getAlgorithmName();
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4748f;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        reset();
        this.f4746d.init(true, iVar);
    }

    @Override // g.a.b.t
    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4744b;
            if (i2 >= bArr.length) {
                this.f4745c = 0;
                this.f4746d.reset();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        int i2 = this.f4745c;
        byte[] bArr = this.f4744b;
        if (i2 == bArr.length) {
            this.f4746d.a(bArr, 0, this.f4743a, 0);
            this.f4745c = 0;
        }
        byte[] bArr2 = this.f4744b;
        int i3 = this.f4745c;
        this.f4745c = i3 + 1;
        bArr2[i3] = b2;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = this.f4746d.b();
        int i4 = this.f4745c;
        int i5 = iB - i4;
        if (i3 > i5) {
            System.arraycopy(bArr, i2, this.f4744b, i4, i5);
            this.f4746d.a(this.f4744b, 0, this.f4743a, 0);
            this.f4745c = 0;
            i3 -= i5;
            i2 += i5;
            while (i3 > iB) {
                this.f4746d.a(bArr, i2, this.f4743a, 0);
                i3 -= iB;
                i2 += iB;
            }
        }
        System.arraycopy(bArr, i2, this.f4744b, this.f4745c, i3);
        this.f4745c += i3;
    }
}
