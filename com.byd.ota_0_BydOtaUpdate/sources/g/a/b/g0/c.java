package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.t;

/* JADX INFO: loaded from: classes.dex */
public class c implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4755a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4756b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4757c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4758d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4759e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g.a.b.e f4760f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4761g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4762h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4763i;

    public c(g.a.b.e eVar) {
        this(eVar, eVar.b() * 8);
    }

    public c(g.a.b.e eVar, int i2) {
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (i2 > eVar.b() * 8) {
            throw new IllegalArgumentException("MAC size must be less or equal to " + (eVar.b() * 8));
        }
        this.f4760f = new g.a.b.h0.b(eVar);
        this.f4761g = i2 / 8;
        this.f4755a = b(eVar.b());
        this.f4757c = new byte[eVar.b()];
        this.f4758d = new byte[eVar.b()];
        this.f4756b = new byte[eVar.b()];
        this.f4759e = 0;
    }

    public static byte[] b(int i2) {
        int i3 = i2 * 8;
        int i4 = 135;
        switch (i3) {
            case 64:
            case 320:
                i4 = 27;
                break;
            case 128:
            case 192:
                break;
            case 160:
                i4 = 45;
                break;
            case 224:
                i4 = 777;
                break;
            case 256:
                i4 = 1061;
                break;
            case 384:
                i4 = 4109;
                break;
            case 448:
                i4 = 2129;
                break;
            case 512:
                i4 = 293;
                break;
            case 768:
                i4 = 655377;
                break;
            case 1024:
                i4 = 524355;
                break;
            case 2048:
                i4 = 548865;
                break;
            default:
                throw new IllegalArgumentException("Unknown block size for CMAC: " + i3);
        }
        return g.a.f.f.f(i4);
    }

    public static int c(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            int i3 = bArr[length] & 255;
            bArr2[length] = (byte) (i2 | (i3 << 1));
            i2 = (i3 >>> 7) & 1;
        }
    }

    public final byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i2 = (-c(bArr, bArr2)) & 255;
        int length = bArr.length - 3;
        byte b2 = bArr2[length];
        byte[] bArr3 = this.f4755a;
        bArr2[length] = (byte) (b2 ^ (bArr3[1] & i2));
        int length2 = bArr.length - 2;
        bArr2[length2] = (byte) ((bArr3[2] & i2) ^ bArr2[length2]);
        int length3 = bArr.length - 1;
        bArr2[length3] = (byte) ((i2 & bArr3[3]) ^ bArr2[length3]);
        return bArr2;
    }

    public void d(g.a.b.i iVar) {
        if (iVar != null && !(iVar instanceof s0)) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
        }
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        byte[] bArr2;
        if (this.f4759e == this.f4760f.b()) {
            bArr2 = this.f4762h;
        } else {
            new g.a.b.i0.c().b(this.f4758d, this.f4759e);
            bArr2 = this.f4763i;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f4757c;
            if (i3 >= bArr3.length) {
                this.f4760f.a(this.f4758d, 0, bArr3, 0);
                System.arraycopy(this.f4757c, 0, bArr, i2, this.f4761g);
                reset();
                return this.f4761g;
            }
            byte[] bArr4 = this.f4758d;
            bArr4[i3] = (byte) (bArr4[i3] ^ bArr2[i3]);
            i3++;
        }
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4760f.getAlgorithmName();
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4761g;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        d(iVar);
        this.f4760f.init(true, iVar);
        byte[] bArr = this.f4756b;
        byte[] bArr2 = new byte[bArr.length];
        this.f4760f.a(bArr, 0, bArr2, 0);
        byte[] bArrA = a(bArr2);
        this.f4762h = bArrA;
        this.f4763i = a(bArrA);
        reset();
    }

    @Override // g.a.b.t
    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4758d;
            if (i2 >= bArr.length) {
                this.f4759e = 0;
                this.f4760f.reset();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        int i2 = this.f4759e;
        byte[] bArr = this.f4758d;
        if (i2 == bArr.length) {
            this.f4760f.a(bArr, 0, this.f4757c, 0);
            this.f4759e = 0;
        }
        byte[] bArr2 = this.f4758d;
        int i3 = this.f4759e;
        this.f4759e = i3 + 1;
        bArr2[i3] = b2;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int iB = this.f4760f.b();
        int i4 = this.f4759e;
        int i5 = iB - i4;
        if (i3 > i5) {
            System.arraycopy(bArr, i2, this.f4758d, i4, i5);
            this.f4760f.a(this.f4758d, 0, this.f4757c, 0);
            this.f4759e = 0;
            i3 -= i5;
            i2 += i5;
            while (i3 > iB) {
                this.f4760f.a(bArr, i2, this.f4757c, 0);
                i3 -= iB;
                i2 += iB;
            }
        }
        System.arraycopy(bArr, i2, this.f4758d, this.f4759e, i3);
        this.f4759e += i3;
    }
}
