package g.a.e.c.a.a;

import g.a.a.p2.p;
import g.a.a.q;
import g.a.a.w2.u;
import g.a.e.a.d;
import g.a.e.a.e;
import g.a.e.b.a.f;
import g.a.e.b.a.g;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcEliecePrivateKey;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcEliecePublicKey;

/* JADX INFO: loaded from: classes.dex */
public class b extends KeyFactorySpi implements AsymmetricKeyInfoConverter {
    @Override // java.security.KeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof PKCS8EncodedKeySpec)) {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
        try {
            p pVarG = p.g(q.j(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
            try {
                if (!e.m.equals(pVarG.h().g())) {
                    throw new InvalidKeySpecException("Unable to recognise OID in McEliece private key");
                }
                g.a.e.a.c cVarI = g.a.e.a.c.i(pVarG.i());
                return new BCMcEliecePrivateKey(new f(cVarI.k(), cVarI.j(), cVarI.g(), cVarI.h(), cVarI.l(), cVarI.m(), cVarI.n()));
            } catch (IOException unused) {
                throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec.");
            }
        } catch (IOException e2) {
            throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec: " + e2);
        }
    }

    @Override // java.security.KeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof X509EncodedKeySpec)) {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
        try {
            u uVarI = u.i(q.j(((X509EncodedKeySpec) keySpec).getEncoded()));
            try {
                if (!e.m.equals(uVarI.g().g())) {
                    throw new InvalidKeySpecException("Unable to recognise OID in McEliece public key");
                }
                d dVarH = d.h(uVarI.k());
                return new BCMcEliecePublicKey(new g(dVarH.i(), dVarH.j(), dVarH.g()));
            } catch (IOException e2) {
                throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec: " + e2.getMessage());
            }
        } catch (IOException e3) {
            throw new InvalidKeySpecException(e3.toString());
        }
    }

    @Override // java.security.KeyFactorySpi
    public KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        return null;
    }

    @Override // java.security.KeyFactorySpi
    public Key engineTranslateKey(Key key) throws InvalidKeyException {
        return null;
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(p pVar) throws IOException {
        g.a.e.a.c cVarI = g.a.e.a.c.i(pVar.i().c());
        return new BCMcEliecePrivateKey(new f(cVarI.k(), cVarI.j(), cVarI.g(), cVarI.h(), cVarI.l(), cVarI.m(), cVarI.n()));
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(u uVar) throws IOException {
        d dVarH = d.h(uVar.k());
        return new BCMcEliecePublicKey(new g(dVarH.i(), dVarH.j(), dVarH.g()));
    }
}
