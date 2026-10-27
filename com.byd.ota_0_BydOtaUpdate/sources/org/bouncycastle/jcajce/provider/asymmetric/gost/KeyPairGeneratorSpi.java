package org.bouncycastle.jcajce.provider.asymmetric.gost;

import g.a.a.d2.a;
import g.a.b.b;
import g.a.b.e0.p;
import g.a.b.j;
import g.a.b.j0.i0;
import g.a.b.j0.k0;
import g.a.b.j0.l0;
import g.a.b.j0.m0;
import g.a.c.e.l;
import g.a.c.e.n;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public p engine;
    public l gost3410Params;
    public boolean initialised;
    public i0 param;
    public SecureRandom random;
    public int strength;

    public KeyPairGeneratorSpi() {
        super("GOST3410");
        this.engine = new p();
        this.strength = 1024;
        this.random = null;
        this.initialised = false;
    }

    private void init(l lVar, SecureRandom secureRandom) {
        n nVarA = lVar.a();
        i0 i0Var = new i0(secureRandom, new k0(nVarA.b(), nVarA.c(), nVarA.a()));
        this.param = i0Var;
        this.engine.b(i0Var);
        this.initialised = true;
        this.gost3410Params = lVar;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            init(new l(a.q.r()), j.b());
        }
        b bVarA = this.engine.a();
        return new KeyPair(new BCGOST3410PublicKey((m0) bVarA.b(), this.gost3410Params), new BCGOST3410PrivateKey((l0) bVarA.a(), this.gost3410Params));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof l)) {
            throw new InvalidAlgorithmParameterException("parameter object not a GOST3410ParameterSpec");
        }
        init((l) algorithmParameterSpec, secureRandom);
    }
}
