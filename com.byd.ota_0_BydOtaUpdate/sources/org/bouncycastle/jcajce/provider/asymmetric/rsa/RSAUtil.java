package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import g.a.a.m;
import g.a.a.p2.n;
import g.a.a.w2.a0;
import g.a.b.j0.e1;
import g.a.b.j0.f1;
import g.a.f.a;
import g.a.f.c;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class RSAUtil {
    public static final m[] rsaOids = {n.f3978e, a0.g2, n.k, n.n};

    public static String generateKeyFingerprint(BigInteger bigInteger, BigInteger bigInteger2) {
        return new c(a.m(bigInteger.toByteArray(), bigInteger2.toByteArray())).toString();
    }

    public static e1 generatePrivateKeyParameter(RSAPrivateKey rSAPrivateKey) {
        if (!(rSAPrivateKey instanceof RSAPrivateCrtKey)) {
            return new e1(true, rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent());
        }
        RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) rSAPrivateKey;
        return new f1(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent(), rSAPrivateCrtKey.getPrivateExponent(), rSAPrivateCrtKey.getPrimeP(), rSAPrivateCrtKey.getPrimeQ(), rSAPrivateCrtKey.getPrimeExponentP(), rSAPrivateCrtKey.getPrimeExponentQ(), rSAPrivateCrtKey.getCrtCoefficient());
    }

    public static e1 generatePublicKeyParameter(RSAPublicKey rSAPublicKey) {
        return new e1(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
    }

    public static boolean isRsaOid(m mVar) {
        int i2 = 0;
        while (true) {
            m[] mVarArr = rsaOids;
            if (i2 == mVarArr.length) {
                return false;
            }
            if (mVar.equals(mVarArr[i2])) {
                return true;
            }
            i2++;
        }
    }
}
