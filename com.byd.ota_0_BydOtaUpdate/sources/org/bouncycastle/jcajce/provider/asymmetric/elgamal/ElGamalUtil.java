package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import g.a.b.j0.b;
import g.a.b.j0.f0;
import g.a.b.j0.g0;
import g.a.b.j0.h0;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalUtil {
    public static b generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof ElGamalPrivateKey) {
            ElGamalPrivateKey elGamalPrivateKey = (ElGamalPrivateKey) privateKey;
            return new g0(elGamalPrivateKey.getX(), new f0(elGamalPrivateKey.getParameters().b(), elGamalPrivateKey.getParameters().a()));
        }
        if (!(privateKey instanceof DHPrivateKey)) {
            throw new InvalidKeyException("can't identify private key for El Gamal.");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) privateKey;
        return new g0(dHPrivateKey.getX(), new f0(dHPrivateKey.getParams().getP(), dHPrivateKey.getParams().getG()));
    }

    public static b generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof ElGamalPublicKey) {
            ElGamalPublicKey elGamalPublicKey = (ElGamalPublicKey) publicKey;
            return new h0(elGamalPublicKey.getY(), new f0(elGamalPublicKey.getParameters().b(), elGamalPublicKey.getParameters().a()));
        }
        if (!(publicKey instanceof DHPublicKey)) {
            throw new InvalidKeyException("can't identify public key for El Gamal.");
        }
        DHPublicKey dHPublicKey = (DHPublicKey) publicKey;
        return new h0(dHPublicKey.getY(), new f0(dHPublicKey.getParams().getP(), dHPublicKey.getParams().getG()));
    }
}
