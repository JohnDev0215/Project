package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.m;
import g.a.a.p2.p;
import g.a.a.w2.u;
import g.a.a.x2.g;
import g.a.b.j0.a0;
import g.a.b.j0.b;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.c.e.e;
import g.a.d.b.d;
import g.a.d.b.h;
import g.a.f.a;
import g.a.f.c;
import g.a.f.i;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Enumeration;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class ECUtil {
    private static h calculateQ(BigInteger bigInteger, e eVar) {
        return eVar.b().y(bigInteger).A();
    }

    public static int[] convertMidTerms(int[] iArr) {
        int[] iArr2 = new int[3];
        if (iArr.length == 1) {
            iArr2[0] = iArr[0];
        } else {
            if (iArr.length != 3) {
                throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
            }
            if (iArr[0] < iArr[1] && iArr[0] < iArr[2]) {
                iArr2[0] = iArr[0];
                if (iArr[1] < iArr[2]) {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[1];
                }
            } else if (iArr[1] < iArr[2]) {
                iArr2[0] = iArr[1];
                if (iArr[0] < iArr[2]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[2];
                } else {
                    iArr2[1] = iArr[2];
                    iArr2[2] = iArr[0];
                }
            } else {
                iArr2[0] = iArr[2];
                if (iArr[0] < iArr[1]) {
                    iArr2[1] = iArr[0];
                    iArr2[2] = iArr[1];
                } else {
                    iArr2[1] = iArr[1];
                    iArr2[2] = iArr[0];
                }
            }
        }
        return iArr2;
    }

    public static String generateKeyFingerprint(h hVar, e eVar) {
        d dVarA = eVar.a();
        return dVarA != null ? new c(a.o(hVar.l(false), dVarA.o().e(), dVarA.p().e(), eVar.b().l(false))).toString() : new c(hVar.l(false)).toString();
    }

    public static b generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) privateKey;
            e parameters = eCPrivateKey.getParameters();
            if (parameters == null) {
                parameters = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            }
            return new b0(eCPrivateKey.getD(), new x(parameters.a(), parameters.b(), parameters.d(), parameters.c(), parameters.e()));
        }
        if (privateKey instanceof java.security.interfaces.ECPrivateKey) {
            java.security.interfaces.ECPrivateKey eCPrivateKey2 = (java.security.interfaces.ECPrivateKey) privateKey;
            e eVarConvertSpec = EC5Util.convertSpec(eCPrivateKey2.getParams(), false);
            return new b0(eCPrivateKey2.getS(), new x(eVarConvertSpec.a(), eVarConvertSpec.b(), eVarConvertSpec.d(), eVarConvertSpec.c(), eVarConvertSpec.e()));
        }
        try {
            byte[] encoded = privateKey.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("no encoding for EC private key");
            }
            PrivateKey privateKey2 = BouncyCastleProvider.getPrivateKey(p.g(encoded));
            if (privateKey2 instanceof java.security.interfaces.ECPrivateKey) {
                return generatePrivateKeyParameter(privateKey2);
            }
            throw new InvalidKeyException("can't identify EC private key.");
        } catch (Exception e2) {
            throw new InvalidKeyException("cannot identify EC private key: " + e2.toString());
        }
    }

    public static b generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            e parameters = eCPublicKey.getParameters();
            return new c0(eCPublicKey.getQ(), new x(parameters.a(), parameters.b(), parameters.d(), parameters.c(), parameters.e()));
        }
        if (publicKey instanceof java.security.interfaces.ECPublicKey) {
            java.security.interfaces.ECPublicKey eCPublicKey2 = (java.security.interfaces.ECPublicKey) publicKey;
            e eVarConvertSpec = EC5Util.convertSpec(eCPublicKey2.getParams(), false);
            return new c0(EC5Util.convertPoint(eCPublicKey2.getParams(), eCPublicKey2.getW(), false), new x(eVarConvertSpec.a(), eVarConvertSpec.b(), eVarConvertSpec.d(), eVarConvertSpec.c(), eVarConvertSpec.e()));
        }
        try {
            byte[] encoded = publicKey.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("no encoding for EC public key");
            }
            PublicKey publicKey2 = BouncyCastleProvider.getPublicKey(u.i(encoded));
            if (publicKey2 instanceof java.security.interfaces.ECPublicKey) {
                return generatePublicKeyParameter(publicKey2);
            }
            throw new InvalidKeyException("cannot identify EC public key.");
        } catch (Exception e2) {
            throw new InvalidKeyException("cannot identify EC public key: " + e2.toString());
        }
    }

    public static String getCurveName(m mVar) {
        return g.a.a.x2.b.e(mVar);
    }

    public static x getDomainParameters(ProviderConfiguration providerConfiguration, g.a.a.x2.e eVar) {
        x xVar;
        if (eVar.j()) {
            m mVarS = m.s(eVar.h());
            g namedCurveByOid = getNamedCurveByOid(mVarS);
            if (namedCurveByOid == null) {
                namedCurveByOid = (g) providerConfiguration.getAdditionalECParameters().get(mVarS);
            }
            return new a0(mVarS, namedCurveByOid.g(), namedCurveByOid.h(), namedCurveByOid.k(), namedCurveByOid.i(), namedCurveByOid.l());
        }
        if (eVar.i()) {
            e ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
            xVar = new x(ecImplicitlyCa.a(), ecImplicitlyCa.b(), ecImplicitlyCa.d(), ecImplicitlyCa.c(), ecImplicitlyCa.e());
        } else {
            g gVarJ = g.j(eVar.h());
            xVar = new x(gVarJ.g(), gVarJ.h(), gVarJ.k(), gVarJ.i(), gVarJ.l());
        }
        return xVar;
    }

    public static x getDomainParameters(ProviderConfiguration providerConfiguration, e eVar) {
        if (eVar instanceof g.a.c.e.c) {
            g.a.c.e.c cVar = (g.a.c.e.c) eVar;
            return new a0(getNamedCurveOid(cVar.f()), cVar.a(), cVar.b(), cVar.d(), cVar.c(), cVar.e());
        }
        if (eVar != null) {
            return new x(eVar.a(), eVar.b(), eVar.d(), eVar.c(), eVar.e());
        }
        e ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
        return new x(ecImplicitlyCa.a(), ecImplicitlyCa.b(), ecImplicitlyCa.d(), ecImplicitlyCa.c(), ecImplicitlyCa.e());
    }

    public static g getNamedCurveByName(String str) {
        g gVarH = g.a.b.b0.a.h(str);
        return gVarH == null ? g.a.a.x2.b.c(str) : gVarH;
    }

    public static g getNamedCurveByOid(m mVar) {
        g gVarI = g.a.b.b0.a.i(mVar);
        return gVarI == null ? g.a.a.x2.b.d(mVar) : gVarI;
    }

    public static m getNamedCurveOid(e eVar) {
        Enumeration enumerationF = g.a.a.x2.b.f();
        while (enumerationF.hasMoreElements()) {
            String str = (String) enumerationF.nextElement();
            g gVarC = g.a.a.x2.b.c(str);
            if (gVarC.k().equals(eVar.d()) && gVarC.i().equals(eVar.c()) && gVarC.g().m(eVar.a()) && gVarC.h().e(eVar.b())) {
                return g.a.a.x2.b.g(str);
            }
        }
        return null;
    }

    public static m getNamedCurveOid(String str) {
        int iIndexOf = str.indexOf(32);
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 1);
        }
        try {
            if (str.charAt(0) >= '0' && str.charAt(0) <= '2') {
                return new m(str);
            }
        } catch (IllegalArgumentException unused) {
        }
        return g.a.a.x2.b.g(str);
    }

    public static int getOrderBitLength(ProviderConfiguration providerConfiguration, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger != null) {
            return bigInteger.bitLength();
        }
        e ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
        return ecImplicitlyCa == null ? bigInteger2.bitLength() : ecImplicitlyCa.d().bitLength();
    }

    public static String privateKeyToString(String str, BigInteger bigInteger, e eVar) {
        StringBuffer stringBuffer = new StringBuffer();
        String strD = i.d();
        h hVarCalculateQ = calculateQ(bigInteger, eVar);
        stringBuffer.append(str);
        stringBuffer.append(" Private Key [");
        stringBuffer.append(generateKeyFingerprint(hVarCalculateQ, eVar));
        stringBuffer.append("]");
        stringBuffer.append(strD);
        stringBuffer.append("            X: ");
        stringBuffer.append(hVarCalculateQ.f().t().toString(16));
        stringBuffer.append(strD);
        stringBuffer.append("            Y: ");
        stringBuffer.append(hVarCalculateQ.g().t().toString(16));
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }

    public static String publicKeyToString(String str, h hVar, e eVar) {
        StringBuffer stringBuffer = new StringBuffer();
        String strD = i.d();
        stringBuffer.append(str);
        stringBuffer.append(" Public Key [");
        stringBuffer.append(generateKeyFingerprint(hVar, eVar));
        stringBuffer.append("]");
        stringBuffer.append(strD);
        stringBuffer.append("            X: ");
        stringBuffer.append(hVar.f().t().toString(16));
        stringBuffer.append(strD);
        stringBuffer.append("            Y: ");
        stringBuffer.append(hVar.g().t().toString(16));
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }
}
