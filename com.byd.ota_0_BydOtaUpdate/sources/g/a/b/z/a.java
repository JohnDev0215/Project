package g.a.b.z;

import g.a.b.j0.i;
import g.a.b.j0.j;
import g.a.b.j0.k;
import g.a.b.j0.x0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class a implements g.a.b.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5199a = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f5200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f5201c;

    @Override // g.a.b.d
    public int a() {
        return (this.f5200b.b().f().bitLength() + 7) / 8;
    }

    @Override // g.a.b.d
    public BigInteger b(g.a.b.i iVar) {
        k kVar = (k) iVar;
        if (!kVar.b().equals(this.f5201c)) {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        }
        BigInteger bigIntegerF = this.f5201c.f();
        BigInteger bigIntegerC = kVar.c();
        if (bigIntegerC != null) {
            BigInteger bigInteger = f5199a;
            if (bigIntegerC.compareTo(bigInteger) > 0 && bigIntegerC.compareTo(bigIntegerF.subtract(bigInteger)) < 0) {
                BigInteger bigIntegerModPow = bigIntegerC.modPow(this.f5200b.c(), bigIntegerF);
                if (bigIntegerModPow.equals(bigInteger)) {
                    throw new IllegalStateException("Shared key can't be 1");
                }
                return bigIntegerModPow;
            }
        }
        throw new IllegalArgumentException("Diffie-Hellman public key is weak");
    }

    @Override // g.a.b.d
    public void init(g.a.b.i iVar) {
        if (iVar instanceof x0) {
            iVar = ((x0) iVar).a();
        }
        g.a.b.j0.b bVar = (g.a.b.j0.b) iVar;
        if (!(bVar instanceof j)) {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        }
        j jVar = (j) bVar;
        this.f5200b = jVar;
        this.f5201c = jVar.b();
    }
}
