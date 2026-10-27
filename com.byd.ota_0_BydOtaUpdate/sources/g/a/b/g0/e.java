package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class e implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g.a.b.h0.l f4769a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4770b = 128;

    public e(g.a.b.h0.l lVar) {
        this.f4769a = lVar;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        try {
            return this.f4769a.doFinal(bArr, i2);
        } catch (InvalidCipherTextException e2) {
            throw new IllegalStateException(e2.toString());
        }
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4769a.getUnderlyingCipher().getAlgorithmName() + "-GMAC";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4770b / 8;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("GMAC requires ParametersWithIV");
        }
        w0 w0Var = (w0) iVar;
        byte[] bArrA = w0Var.a();
        this.f4769a.init(true, new g.a.b.j0.a((s0) w0Var.b(), this.f4770b, bArrA));
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4769a.m();
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        this.f4769a.j(b2);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        this.f4769a.a(bArr, i2, i3);
    }
}
