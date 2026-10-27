package g.a.c.e;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;

/* JADX INFO: loaded from: classes.dex */
public class d extends ECParameterSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5240a;

    public d(String str, g.a.d.b.d dVar, g.a.d.b.h hVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        super(a(dVar, bArr), EC5Util.convertPoint(hVar), bigInteger, bigInteger2.intValue());
        this.f5240a = str;
    }

    public d(String str, EllipticCurve ellipticCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        super(ellipticCurve, eCPoint, bigInteger, bigInteger2.intValue());
        this.f5240a = str;
    }

    public static EllipticCurve a(g.a.d.b.d dVar, byte[] bArr) {
        return new EllipticCurve(b(dVar.t()), dVar.o().t(), dVar.p().t(), bArr);
    }

    public static ECField b(g.a.d.c.a aVar) {
        if (g.a.d.b.b.n(aVar)) {
            return new ECFieldFp(aVar.c());
        }
        g.a.d.c.e eVarA = ((g.a.d.c.f) aVar).a();
        int[] iArrA = eVarA.a();
        return new ECFieldF2m(eVarA.b(), g.a.f.a.I(g.a.f.a.t(iArrA, 1, iArrA.length - 1)));
    }

    public String c() {
        return this.f5240a;
    }
}
