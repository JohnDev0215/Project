package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.a.k;
import g.a.a.m;
import g.a.a.w0;
import g.a.a.x2.e;
import g.a.a.x2.g;
import g.a.b.j0.b;
import g.a.c.e.d;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;

/* JADX INFO: loaded from: classes.dex */
public class ECUtils {
    public static b generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        return publicKey instanceof BCECPublicKey ? ((BCECPublicKey) publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
    }

    public static g getDomainParametersFromGenSpec(ECGenParameterSpec eCGenParameterSpec) {
        return getDomainParametersFromName(eCGenParameterSpec.getName());
    }

    public static e getDomainParametersFromName(ECParameterSpec eCParameterSpec, boolean z) {
        if (!(eCParameterSpec instanceof d)) {
            if (eCParameterSpec == null) {
                return new e((k) w0.f4105c);
            }
            g.a.d.b.d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            return new e(new g(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, eCParameterSpec.getGenerator(), z), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed()));
        }
        d dVar = (d) eCParameterSpec;
        m namedCurveOid = ECUtil.getNamedCurveOid(dVar.c());
        if (namedCurveOid == null) {
            namedCurveOid = new m(dVar.c());
        }
        return new e(namedCurveOid);
    }

    public static g getDomainParametersFromName(String str) {
        try {
            if (str.charAt(0) >= '0' && str.charAt(0) <= '2') {
                return ECUtil.getNamedCurveByOid(new m(str));
            }
            if (str.indexOf(32) > 0) {
                str = str.substring(str.indexOf(32) + 1);
            }
            return ECUtil.getNamedCurveByName(str);
        } catch (IllegalArgumentException unused) {
            return ECUtil.getNamedCurveByName(str);
        }
    }
}
