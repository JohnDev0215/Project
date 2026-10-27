package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.b.j0.b;
import g.a.b.j0.k0;
import g.a.b.j0.l0;
import g.a.b.j0.m0;
import g.a.c.e.n;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.jce.interfaces.GOST3410PrivateKey;
import org.bouncycastle.jce.interfaces.GOST3410PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410Util {
    public static b generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof GOST3410PrivateKey)) {
            throw new InvalidKeyException("can't identify GOST3410 private key.");
        }
        GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) privateKey;
        n nVarA = gOST3410PrivateKey.getParameters().a();
        return new l0(gOST3410PrivateKey.getX(), new k0(nVarA.b(), nVarA.c(), nVarA.a()));
    }

    public static b generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof GOST3410PublicKey) {
            GOST3410PublicKey gOST3410PublicKey = (GOST3410PublicKey) publicKey;
            n nVarA = gOST3410PublicKey.getParameters().a();
            return new m0(gOST3410PublicKey.getY(), new k0(nVarA.b(), nVarA.c(), nVarA.a()));
        }
        throw new InvalidKeyException("can't identify GOST3410 public key: " + publicKey.getClass().getName());
    }
}
