package g.a.b.d0;

import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class v implements g.a.b.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public u f4618a = new u();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.g0.f f4619b = new g.a.b.g0.f();

    @Override // g.a.b.x
    public byte[] a(byte[] bArr, int i2, int i3) {
        this.f4619b.update(bArr, i2, i3);
        byte[] bArr2 = new byte[this.f4619b.getMacSize() + i3];
        this.f4618a.a(bArr, i2, bArr2, 0);
        this.f4618a.a(bArr, i2 + 8, bArr2, 8);
        this.f4618a.a(bArr, i2 + 16, bArr2, 16);
        this.f4618a.a(bArr, i2 + 24, bArr2, 24);
        this.f4619b.doFinal(bArr2, i3);
        return bArr2;
    }

    @Override // g.a.b.x
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        int macSize = i3 - this.f4619b.getMacSize();
        byte[] bArr2 = new byte[macSize];
        this.f4618a.a(bArr, i2, bArr2, 0);
        this.f4618a.a(bArr, i2 + 8, bArr2, 8);
        this.f4618a.a(bArr, i2 + 16, bArr2, 16);
        this.f4618a.a(bArr, i2 + 24, bArr2, 24);
        byte[] bArr3 = new byte[this.f4619b.getMacSize()];
        this.f4619b.update(bArr2, 0, macSize);
        this.f4619b.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[this.f4619b.getMacSize()];
        System.arraycopy(bArr, (i2 + i3) - 4, bArr4, 0, this.f4619b.getMacSize());
        if (g.a.f.a.q(bArr3, bArr4)) {
            return bArr2;
        }
        throw new IllegalStateException("mac mismatch");
    }

    @Override // g.a.b.x
    public String getAlgorithmName() {
        return "GOST28147Wrap";
    }

    @Override // g.a.b.x
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        g.a.b.j0.z0 z0Var = (g.a.b.j0.z0) iVar;
        this.f4618a.init(z, z0Var.a());
        this.f4619b.init(new g.a.b.j0.w0((g.a.b.j0.s0) (z0Var.a() instanceof g.a.b.j0.y0 ? ((g.a.b.j0.y0) z0Var.a()).a() : z0Var.a()), z0Var.b()));
    }
}
