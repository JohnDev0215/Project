package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.m;
import g.a.a.x2.b;
import g.a.a.x2.g;
import g.a.b.b0.a;
import g.a.b.j0.x;
import g.a.c.e.c;
import g.a.d.b.d;
import g.a.d.b.h;
import g.a.d.c.e;
import g.a.d.c.f;
import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class EC5Util {
    private static Map customCurves = new HashMap();

    static {
        Enumeration enumerationJ = a.j();
        while (enumerationJ.hasMoreElements()) {
            String str = (String) enumerationJ.nextElement();
            g gVarC = b.c(str);
            if (gVarC != null) {
                customCurves.put(gVarC.g(), a.h(str).g());
            }
        }
        g gVarH = a.h("Curve25519");
        customCurves.put(new d.f(gVarH.g().t().c(), gVarH.g().o().t(), gVarH.g().p().t()), gVarH.g());
    }

    public static d convertCurve(EllipticCurve ellipticCurve) {
        ECField field = ellipticCurve.getField();
        BigInteger a2 = ellipticCurve.getA();
        BigInteger b2 = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            d.f fVar = new d.f(((ECFieldFp) field).getP(), a2, b2);
            return customCurves.containsKey(fVar) ? (d) customCurves.get(fVar) : fVar;
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m = eCFieldF2m.getM();
        int[] iArrConvertMidTerms = ECUtil.convertMidTerms(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new d.e(m, iArrConvertMidTerms[0], iArrConvertMidTerms[1], iArrConvertMidTerms[2], a2, b2);
    }

    public static EllipticCurve convertCurve(d dVar, byte[] bArr) {
        return new EllipticCurve(convertField(dVar.t()), dVar.o().t(), dVar.p().t(), null);
    }

    public static ECField convertField(g.a.d.c.a aVar) {
        if (g.a.d.b.b.n(aVar)) {
            return new ECFieldFp(aVar.c());
        }
        e eVarA = ((f) aVar).a();
        int[] iArrA = eVarA.a();
        return new ECFieldF2m(eVarA.b(), g.a.f.a.I(g.a.f.a.t(iArrA, 1, iArrA.length - 1)));
    }

    public static h convertPoint(d dVar, ECPoint eCPoint, boolean z) {
        return dVar.g(eCPoint.getAffineX(), eCPoint.getAffineY());
    }

    public static h convertPoint(ECParameterSpec eCParameterSpec, ECPoint eCPoint, boolean z) {
        return convertPoint(convertCurve(eCParameterSpec.getCurve()), eCPoint, z);
    }

    public static ECPoint convertPoint(h hVar) {
        h hVarA = hVar.A();
        return new ECPoint(hVarA.f().t(), hVarA.g().t());
    }

    public static g.a.c.e.e convertSpec(ECParameterSpec eCParameterSpec, boolean z) {
        d dVarConvertCurve = convertCurve(eCParameterSpec.getCurve());
        return new g.a.c.e.e(dVarConvertCurve, convertPoint(dVarConvertCurve, eCParameterSpec.getGenerator(), z), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed());
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, g.a.c.e.e eVar) {
        return eVar instanceof c ? new g.a.c.e.d(((c) eVar).f(), ellipticCurve, convertPoint(eVar.b()), eVar.d(), eVar.c()) : new ECParameterSpec(ellipticCurve, convertPoint(eVar.b()), eVar.d(), eVar.c().intValue());
    }

    public static ECParameterSpec convertToSpec(g.a.a.x2.e eVar, d dVar) {
        if (!eVar.j()) {
            if (eVar.i()) {
                return null;
            }
            g gVarJ = g.j(eVar.h());
            EllipticCurve ellipticCurveConvertCurve = convertCurve(dVar, gVarJ.l());
            return gVarJ.i() != null ? new ECParameterSpec(ellipticCurveConvertCurve, convertPoint(gVarJ.h()), gVarJ.k(), gVarJ.i().intValue()) : new ECParameterSpec(ellipticCurveConvertCurve, convertPoint(gVarJ.h()), gVarJ.k(), 1);
        }
        m mVar = (m) eVar.h();
        g namedCurveByOid = ECUtil.getNamedCurveByOid(mVar);
        if (namedCurveByOid == null) {
            Map additionalECParameters = BouncyCastleProvider.CONFIGURATION.getAdditionalECParameters();
            if (!additionalECParameters.isEmpty()) {
                namedCurveByOid = (g) additionalECParameters.get(mVar);
            }
        }
        return new g.a.c.e.d(ECUtil.getCurveName(mVar), convertCurve(dVar, namedCurveByOid.l()), convertPoint(namedCurveByOid.h()), namedCurveByOid.k(), namedCurveByOid.i());
    }

    public static ECParameterSpec convertToSpec(g gVar) {
        return new ECParameterSpec(convertCurve(gVar.g(), null), convertPoint(gVar.h()), gVar.k(), gVar.i().intValue());
    }

    public static d getCurve(ProviderConfiguration providerConfiguration, g.a.a.x2.e eVar) {
        Set acceptableNamedCurves = providerConfiguration.getAcceptableNamedCurves();
        if (!eVar.j()) {
            if (eVar.i()) {
                return providerConfiguration.getEcImplicitlyCa().a();
            }
            if (acceptableNamedCurves.isEmpty()) {
                return g.j(eVar.h()).g();
            }
            throw new IllegalStateException("encoded parameters not acceptable");
        }
        m mVarS = m.s(eVar.h());
        if (!acceptableNamedCurves.isEmpty() && !acceptableNamedCurves.contains(mVarS)) {
            throw new IllegalStateException("named curve not acceptable");
        }
        g namedCurveByOid = ECUtil.getNamedCurveByOid(mVarS);
        if (namedCurveByOid == null) {
            namedCurveByOid = (g) providerConfiguration.getAdditionalECParameters().get(mVarS);
        }
        return namedCurveByOid.g();
    }

    public static x getDomainParameters(ProviderConfiguration providerConfiguration, ECParameterSpec eCParameterSpec) {
        if (eCParameterSpec != null) {
            return ECUtil.getDomainParameters(providerConfiguration, convertSpec(eCParameterSpec, false));
        }
        g.a.c.e.e ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
        return new x(ecImplicitlyCa.a(), ecImplicitlyCa.b(), ecImplicitlyCa.d(), ecImplicitlyCa.c(), ecImplicitlyCa.e());
    }
}
