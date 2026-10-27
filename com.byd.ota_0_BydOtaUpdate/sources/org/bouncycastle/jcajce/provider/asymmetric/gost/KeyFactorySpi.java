package org.bouncycastle.jcajce.provider.asymmetric.gost;

import g.a.a.d2.a;
import g.a.a.p2.p;
import g.a.a.w2.u;
import g.a.c.e.m;
import g.a.c.e.n;
import g.a.c.e.o;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.bouncycastle.jce.interfaces.GOST3410PrivateKey;
import org.bouncycastle.jce.interfaces.GOST3410PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class KeyFactorySpi extends BaseKeyFactorySpi {
    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        return keySpec instanceof m ? new BCGOST3410PrivateKey((m) keySpec) : super.engineGeneratePrivate(keySpec);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        return keySpec instanceof o ? new BCGOST3410PublicKey((o) keySpec) : super.engineGeneratePublic(keySpec);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    public KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (cls.isAssignableFrom(o.class) && (key instanceof GOST3410PublicKey)) {
            GOST3410PublicKey gOST3410PublicKey = (GOST3410PublicKey) key;
            n nVarA = gOST3410PublicKey.getParameters().a();
            return new o(gOST3410PublicKey.getY(), nVarA.b(), nVarA.c(), nVarA.a());
        }
        if (!cls.isAssignableFrom(m.class) || !(key instanceof GOST3410PrivateKey)) {
            return super.engineGetKeySpec(key, cls);
        }
        GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) key;
        n nVarA2 = gOST3410PrivateKey.getParameters().a();
        return new m(gOST3410PrivateKey.getX(), nVarA2.b(), nVarA2.c(), nVarA2.a());
    }

    @Override // java.security.KeyFactorySpi
    public Key engineTranslateKey(Key key) throws InvalidKeyException {
        if (key instanceof GOST3410PublicKey) {
            return new BCGOST3410PublicKey((GOST3410PublicKey) key);
        }
        if (key instanceof GOST3410PrivateKey) {
            return new BCGOST3410PrivateKey((GOST3410PrivateKey) key);
        }
        throw new InvalidKeyException("key type unknown");
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(p pVar) throws IOException {
        g.a.a.m mVarG = pVar.h().g();
        if (mVarG.equals(a.l)) {
            return new BCGOST3410PrivateKey(pVar);
        }
        throw new IOException("algorithm identifier " + mVarG + " in key not recognised");
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(u uVar) throws IOException {
        g.a.a.m mVarG = uVar.g().g();
        if (mVarG.equals(a.l)) {
            return new BCGOST3410PublicKey(uVar);
        }
        throw new IOException("algorithm identifier " + mVarG + " in key not recognised");
    }
}
