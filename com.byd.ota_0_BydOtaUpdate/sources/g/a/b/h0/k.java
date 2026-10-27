package g.a.b.h0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.j0.x0;
import g.a.b.j0.y0;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class k extends g.a.b.v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f4902b = {105, 0, 114, 34, 100, -55, 4, 35, -115, 58, -37, -106, 70, -23, 42, -60, 24, -2, -84, -108, 0, -19, 7, 18, -64, -122, -36, -62, -17, 76, -87, 43};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f4903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public s0 f4904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4906f;

    public k(g.a.b.e eVar) {
        super(eVar);
        this.f4905e = 0L;
        this.f4903c = new d(eVar, eVar.b() * 8);
    }

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        processBytes(bArr, i2, this.f4903c.b(), bArr2, i3);
        return this.f4903c.b();
    }

    @Override // g.a.b.e
    public int b() {
        return this.f4903c.b();
    }

    @Override // g.a.b.v
    public byte c(byte b2) {
        long j = this.f4905e;
        if (j > 0 && j % 1024 == 0) {
            g.a.b.e eVarD = this.f4903c.d();
            eVarD.init(false, this.f4904d);
            byte[] bArr = new byte[32];
            byte[] bArr2 = f4902b;
            eVarD.a(bArr2, 0, bArr, 0);
            eVarD.a(bArr2, 8, bArr, 8);
            eVarD.a(bArr2, 16, bArr, 16);
            eVarD.a(bArr2, 24, bArr, 24);
            s0 s0Var = new s0(bArr);
            this.f4904d = s0Var;
            eVarD.init(true, s0Var);
            byte[] bArrG = this.f4903c.g();
            eVarD.a(bArrG, 0, bArrG, 0);
            this.f4903c.init(this.f4906f, new w0(this.f4904d, bArrG));
        }
        this.f4905e++;
        return this.f4903c.c(b2);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        String algorithmName = this.f4903c.getAlgorithmName();
        return algorithmName.substring(0, algorithmName.indexOf(47)) + "/G" + algorithmName.substring(algorithmName.indexOf(47) + 1);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        this.f4905e = 0L;
        this.f4903c.init(z, iVar);
        this.f4906f = z;
        if (iVar instanceof w0) {
            iVar = ((w0) iVar).b();
        }
        if (iVar instanceof x0) {
            iVar = ((x0) iVar).a();
        }
        if (iVar instanceof y0) {
            iVar = ((y0) iVar).a();
        }
        this.f4904d = (s0) iVar;
    }

    @Override // g.a.b.e
    public void reset() {
        this.f4905e = 0L;
        this.f4903c.reset();
    }
}
