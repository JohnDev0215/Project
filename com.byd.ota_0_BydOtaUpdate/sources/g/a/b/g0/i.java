package g.a.b.g0;

import g.a.b.h0.q;
import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class i implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f4796a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4797b;

    public i(q qVar, int i2) {
        this.f4796a = qVar;
        this.f4797b = i2;
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        try {
            return this.f4796a.doFinal(bArr, i2);
        } catch (InvalidCipherTextException e2) {
            throw new IllegalStateException(e2.toString());
        }
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return this.f4796a.getUnderlyingCipher().getAlgorithmName() + "-KGMAC";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4797b / 8;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("KGMAC requires ParametersWithIV");
        }
        w0 w0Var = (w0) iVar;
        byte[] bArrA = w0Var.a();
        this.f4796a.init(true, new g.a.b.j0.a((s0) w0Var.b(), this.f4797b, bArrA));
    }

    @Override // g.a.b.t
    public void reset() {
        this.f4796a.g();
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        this.f4796a.f(b2);
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        this.f4796a.a(bArr, i2, i3);
    }
}
