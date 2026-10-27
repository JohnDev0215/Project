package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class h implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4788a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4789b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4790c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.e f4791d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.i0.a f4792e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4793f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s0 f4794g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public s0 f4795h;

    public h(g.a.b.e eVar) {
        this(eVar, eVar.b() * 8, null);
    }

    public h(g.a.b.e eVar, int i2, g.a.b.i0.a aVar) {
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (!(eVar instanceof g.a.b.d0.o)) {
            throw new IllegalArgumentException("cipher must be instance of DESEngine");
        }
        this.f4791d = new g.a.b.h0.b(eVar);
        this.f4792e = aVar;
        this.f4793f = i2 / 8;
        this.f4788a = new byte[eVar.b()];
        this.f4789b = new byte[eVar.b()];
        this.f4790c = 0;
    }

    public h(g.a.b.e eVar, g.a.b.i0.a aVar) {
        this(eVar, eVar.b() * 8, aVar);
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        int iB = this.f4791d.b();
        if (this.f4792e == null) {
            while (true) {
                int i3 = this.f4790c;
                if (i3 >= iB) {
                    break;
                }
                this.f4789b[i3] = 0;
                this.f4790c = i3 + 1;
            }
        } else {
            if (this.f4790c == iB) {
                this.f4791d.a(this.f4789b, 0, this.f4788a, 0);
                this.f4790c = 0;
            }
            this.f4792e.b(this.f4789b, this.f4790c);
        }
        this.f4791d.a(this.f4789b, 0, this.f4788a, 0);
        g.a.b.d0.o oVar = new g.a.b.d0.o();
        oVar.init(false, this.f4794g);
        byte[] bArr2 = this.f4788a;
        oVar.a(bArr2, 0, bArr2, 0);
        oVar.init(true, this.f4795h);
        byte[] bArr3 = this.f4788a;
        oVar.a(bArr3, 0, bArr3, 0);
        System.arraycopy(this.f4788a, 0, bArr, i2, this.f4793f);
        reset();
        return this.f4793f;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "ISO9797Alg3";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4793f;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        s0 s0Var;
        reset();
        boolean z = iVar instanceof s0;
        if (!z && !(iVar instanceof w0)) {
            throw new IllegalArgumentException("params must be an instance of KeyParameter or ParametersWithIV");
        }
        byte[] bArrA = (z ? (s0) iVar : (s0) ((w0) iVar).b()).a();
        if (bArrA.length == 16) {
            s0Var = new s0(bArrA, 0, 8);
            this.f4794g = new s0(bArrA, 8, 8);
            this.f4795h = s0Var;
        } else {
            if (bArrA.length != 24) {
                throw new IllegalArgumentException("Key must be either 112 or 168 bit long");
            }
            s0Var = new s0(bArrA, 0, 8);
            this.f4794g = new s0(bArrA, 8, 8);
            this.f4795h = new s0(bArrA, 16, 8);
        }
        if (iVar instanceof w0) {
            this.f4791d.init(true, new w0(s0Var, ((w0) iVar).a()));
        } else {
            this.f4791d.init(true, s0Var);
        }
    }

    @Override // g.a.b.t
    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4789b;
            if (i2 >= bArr.length) {
                this.f4790c = 0;
                this.f4791d.reset();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        int i2 = this.f4790c;
        byte[] bArr = this.f4789b;
        if (i2 == bArr.length) {
            this.f4791d.a(bArr, 0, this.f4788a, 0);
            this.f4790c = 0;
        }
        byte[] bArr2 = this.f4789b;
        int i3 = this.f4790c;
        this.f4790c = i3 + 1;
        bArr2[i3] = b2;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = this.f4791d.b();
        int i4 = this.f4790c;
        int i5 = iB - i4;
        if (i3 > i5) {
            System.arraycopy(bArr, i2, this.f4789b, i4, i5);
            this.f4791d.a(this.f4789b, 0, this.f4788a, 0);
            this.f4790c = 0;
            i3 -= i5;
            i2 += i5;
            while (i3 > iB) {
                this.f4791d.a(bArr, i2, this.f4788a, 0);
                i3 -= iB;
                i2 += iB;
            }
        }
        System.arraycopy(bArr, i2, this.f4789b, this.f4790c, i3);
        this.f4790c += i3;
    }
}
