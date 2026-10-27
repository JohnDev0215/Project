package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.p;
import g.a.b.t;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class g implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Hashtable f4780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.n f4781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4782c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4783d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.f.e f4784e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g.a.f.e f4785f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4786g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4787h;

    static {
        Hashtable hashtable = new Hashtable();
        f4780a = hashtable;
        hashtable.put("GOST3411", g.a.f.d.b(32));
        f4780a.put("MD2", g.a.f.d.b(16));
        f4780a.put("MD4", g.a.f.d.b(64));
        f4780a.put("MD5", g.a.f.d.b(64));
        f4780a.put("RIPEMD128", g.a.f.d.b(64));
        f4780a.put("RIPEMD160", g.a.f.d.b(64));
        f4780a.put("SHA-1", g.a.f.d.b(64));
        f4780a.put("SHA-224", g.a.f.d.b(64));
        f4780a.put("SHA-256", g.a.f.d.b(64));
        f4780a.put("SHA-384", g.a.f.d.b(128));
        f4780a.put("SHA-512", g.a.f.d.b(128));
        f4780a.put("Tiger", g.a.f.d.b(64));
        f4780a.put("Whirlpool", g.a.f.d.b(64));
    }

    public g(g.a.b.n nVar) {
        this(nVar, a(nVar));
    }

    public g(g.a.b.n nVar, int i2) {
        this.f4781b = nVar;
        int digestSize = nVar.getDigestSize();
        this.f4782c = digestSize;
        this.f4783d = i2;
        this.f4786g = new byte[i2];
        this.f4787h = new byte[i2 + digestSize];
    }

    public static int a(g.a.b.n nVar) {
        if (nVar instanceof p) {
            return ((p) nVar).getByteLength();
        }
        Integer num = (Integer) f4780a.get(nVar.getAlgorithmName());
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("unknown digest passed: " + nVar.getAlgorithmName());
    }

    public static void b(byte[] bArr, int i2, byte b2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ b2);
        }
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) {
        this.f4781b.doFinal(this.f4787h, this.f4783d);
        g.a.f.e eVar = this.f4785f;
        if (eVar != null) {
            ((g.a.f.e) this.f4781b).a(eVar);
            g.a.b.n nVar = this.f4781b;
            nVar.update(this.f4787h, this.f4783d, nVar.getDigestSize());
        } else {
            g.a.b.n nVar2 = this.f4781b;
            byte[] bArr2 = this.f4787h;
            nVar2.update(bArr2, 0, bArr2.length);
        }
        int iDoFinal = this.f4781b.doFinal(bArr, i2);
        int i3 = this.f4783d;
        while (true) {
            byte[] bArr3 = this.f4787h;
            if (i3 >= bArr3.length) {
                break;
            }
            bArr3[i3] = 0;
            i3++;
        }
        g.a.f.e eVar2 = this.f4784e;
        if (eVar2 != null) {
            ((g.a.f.e) this.f4781b).a(eVar2);
        } else {
            g.a.b.n nVar3 = this.f4781b;
            byte[] bArr4 = this.f4786g;
            nVar3.update(bArr4, 0, bArr4.length);
        }
        return iDoFinal;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4781b.getAlgorithmName() + "/HMAC";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4782c;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) {
        byte[] bArr;
        this.f4781b.reset();
        byte[] bArrA = ((s0) iVar).a();
        int length = bArrA.length;
        if (length > this.f4783d) {
            this.f4781b.update(bArrA, 0, length);
            this.f4781b.doFinal(this.f4786g, 0);
            length = this.f4782c;
        } else {
            System.arraycopy(bArrA, 0, this.f4786g, 0, length);
        }
        while (true) {
            bArr = this.f4786g;
            if (length >= bArr.length) {
                break;
            }
            bArr[length] = 0;
            length++;
        }
        System.arraycopy(bArr, 0, this.f4787h, 0, this.f4783d);
        b(this.f4786g, this.f4783d, (byte) 54);
        b(this.f4787h, this.f4783d, (byte) 92);
        g.a.b.n nVar = this.f4781b;
        if (nVar instanceof g.a.f.e) {
            g.a.f.e eVarB = ((g.a.f.e) nVar).b();
            this.f4785f = eVarB;
            ((g.a.b.n) eVarB).update(this.f4787h, 0, this.f4783d);
        }
        g.a.b.n nVar2 = this.f4781b;
        byte[] bArr2 = this.f4786g;
        nVar2.update(bArr2, 0, bArr2.length);
        g.a.b.n nVar3 = this.f4781b;
        if (nVar3 instanceof g.a.f.e) {
            this.f4784e = ((g.a.f.e) nVar3).b();
        }
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4781b.reset();
        g.a.b.n nVar = this.f4781b;
        byte[] bArr = this.f4786g;
        nVar.update(bArr, 0, bArr.length);
    }

    @Override // g.a.b.t
    public void update(byte b2) {
        this.f4781b.update(b2);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) {
        this.f4781b.update(bArr, i2, i3);
    }
}
