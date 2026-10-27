package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.w2.u;
import g.a.b.j0.r;
import g.a.b.j0.s;
import g.a.f.a;
import g.a.f.c;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class DSAUtil {
    public static final m[] dsaOids = {g.a.a.x2.m.j3, b.j, g.a.a.x2.m.k3};

    public static String generateKeyFingerprint(BigInteger bigInteger, DSAParams dSAParams) {
        return new c(a.o(bigInteger.toByteArray(), dSAParams.getP().toByteArray(), dSAParams.getQ().toByteArray(), dSAParams.getG().toByteArray())).toString();
    }

    public static g.a.b.j0.b generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof DSAPrivateKey)) {
            throw new InvalidKeyException("can't identify DSA private key.");
        }
        DSAPrivateKey dSAPrivateKey = (DSAPrivateKey) privateKey;
        return new s(dSAPrivateKey.getX(), new r(dSAPrivateKey.getParams().getP(), dSAPrivateKey.getParams().getQ(), dSAPrivateKey.getParams().getG()));
    }

    public static g.a.b.j0.b generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof BCDSAPublicKey) {
            return ((BCDSAPublicKey) publicKey).engineGetKeyParameters();
        }
        if (publicKey instanceof DSAPublicKey) {
            return new BCDSAPublicKey((DSAPublicKey) publicKey).engineGetKeyParameters();
        }
        try {
            return new BCDSAPublicKey(u.i(publicKey.getEncoded())).engineGetKeyParameters();
        } catch (Exception unused) {
            throw new InvalidKeyException("can't identify DSA public key: " + publicKey.getClass().getName());
        }
    }

    public static boolean isDsaOid(m mVar) {
        int i2 = 0;
        while (true) {
            m[] mVarArr = dsaOids;
            if (i2 == mVarArr.length) {
                return false;
            }
            if (mVar.equals(mVarArr[i2])) {
                return true;
            }
            i2++;
        }
    }

    public static r toDSAParameters(DSAParams dSAParams) {
        if (dSAParams != null) {
            return new r(dSAParams.getP(), dSAParams.getQ(), dSAParams.getG());
        }
        return null;
    }
}
