package org.bouncycastle.jcajce.provider.asymmetric.dh;

import g.a.b.b;
import g.a.b.e0.d;
import g.a.b.e0.g;
import g.a.b.j;
import g.a.b.j0.e;
import g.a.b.j0.i;
import g.a.b.j0.k;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public d engine;
    public boolean initialised;
    public e param;
    public SecureRandom random;
    public int strength;
    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();

    public KeyPairGeneratorSpi() {
        super("DH");
        this.engine = new d();
        this.strength = 2048;
        this.random = j.b();
        this.initialised = false;
    }

    private e convertParams(SecureRandom secureRandom, DHParameterSpec dHParameterSpec) {
        return dHParameterSpec instanceof DHDomainParameterSpec ? new e(secureRandom, ((DHDomainParameterSpec) dHParameterSpec).getDomainParameters()) : new e(secureRandom, new i(dHParameterSpec.getP(), dHParameterSpec.getG(), null, dHParameterSpec.getL()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        e eVarConvertParams;
        if (!this.initialised) {
            Integer numB = g.a.f.d.b(this.strength);
            if (params.containsKey(numB)) {
                eVarConvertParams = (e) params.get(numB);
            } else {
                DHParameterSpec dHDefaultParameters = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.strength);
                if (dHDefaultParameters != null) {
                    eVarConvertParams = convertParams(this.random, dHDefaultParameters);
                } else {
                    synchronized (lock) {
                        if (params.containsKey(numB)) {
                            this.param = (e) params.get(numB);
                        } else {
                            g gVar = new g();
                            int i2 = this.strength;
                            gVar.b(i2, PrimeCertaintyCalculator.getDefaultCertainty(i2), this.random);
                            e eVar = new e(this.random, gVar.a());
                            this.param = eVar;
                            params.put(numB, eVar);
                        }
                    }
                    this.engine.b(this.param);
                    this.initialised = true;
                }
            }
            this.param = eVarConvertParams;
            this.engine.b(this.param);
            this.initialised = true;
        }
        b bVarA = this.engine.a();
        return new KeyPair(new BCDHPublicKey((k) bVarA.b()), new BCDHPrivateKey((g.a.b.j0.j) bVarA.a()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec");
        }
        try {
            e eVarConvertParams = convertParams(secureRandom, (DHParameterSpec) algorithmParameterSpec);
            this.param = eVarConvertParams;
            this.engine.b(eVarConvertParams);
            this.initialised = true;
        } catch (IllegalArgumentException e2) {
            throw new InvalidAlgorithmParameterException(e2.getMessage(), e2);
        }
    }
}
