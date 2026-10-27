package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import g.a.b.b;
import g.a.b.e0.m;
import g.a.b.e0.n;
import g.a.b.j;
import g.a.b.j0.d0;
import g.a.b.j0.f0;
import g.a.b.j0.g0;
import g.a.b.j0.h0;
import g.a.c.e.i;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public int certainty;
    public m engine;
    public boolean initialised;
    public d0 param;
    public SecureRandom random;
    public int strength;

    public KeyPairGeneratorSpi() {
        super("ElGamal");
        this.engine = new m();
        this.strength = 1024;
        this.certainty = 20;
        this.random = j.b();
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        d0 d0Var;
        if (!this.initialised) {
            DHParameterSpec dHDefaultParameters = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.strength);
            if (dHDefaultParameters != null) {
                d0Var = new d0(this.random, new f0(dHDefaultParameters.getP(), dHDefaultParameters.getG(), dHDefaultParameters.getL()));
            } else {
                n nVar = new n();
                nVar.b(this.strength, this.certainty, this.random);
                d0Var = new d0(this.random, nVar.a());
            }
            this.param = d0Var;
            this.engine.b(this.param);
            this.initialised = true;
        }
        b bVarA = this.engine.a();
        return new KeyPair(new BCElGamalPublicKey((h0) bVarA.b()), new BCElGamalPrivateKey((g0) bVarA.a()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        d0 d0Var;
        boolean z = algorithmParameterSpec instanceof i;
        if (!z && !(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec or an ElGamalParameterSpec");
        }
        if (z) {
            i iVar = (i) algorithmParameterSpec;
            d0Var = new d0(secureRandom, new f0(iVar.b(), iVar.a()));
        } else {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
            d0Var = new d0(secureRandom, new f0(dHParameterSpec.getP(), dHParameterSpec.getG(), dHParameterSpec.getL()));
        }
        this.param = d0Var;
        this.engine.b(this.param);
        this.initialised = true;
    }
}
