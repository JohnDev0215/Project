package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import g.a.b.a0.u;
import g.a.b.b;
import g.a.b.e0.i;
import g.a.b.j;
import g.a.b.j0.o;
import g.a.b.j0.q;
import g.a.b.j0.r;
import g.a.b.j0.s;
import g.a.b.j0.t;
import g.a.f.d;
import g.a.f.g;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    public i engine;
    public boolean initialised;
    public o param;
    public SecureRandom random;
    public int strength;
    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();

    public KeyPairGeneratorSpi() {
        super("DSA");
        this.engine = new i();
        this.strength = 2048;
        this.random = j.b();
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        g.a.b.e0.j jVar;
        int i2;
        SecureRandom secureRandom;
        if (!this.initialised) {
            Integer numB = d.b(this.strength);
            if (params.containsKey(numB)) {
                this.param = (o) params.get(numB);
            } else {
                synchronized (lock) {
                    if (params.containsKey(numB)) {
                        this.param = (o) params.get(numB);
                    } else {
                        int defaultCertainty = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
                        int i3 = this.strength;
                        if (i3 == 1024) {
                            jVar = new g.a.b.e0.j();
                            if (g.d("org.bouncycastle.dsa.FIPS186-2for1024bits")) {
                                i2 = this.strength;
                                secureRandom = this.random;
                                jVar.k(i2, defaultCertainty, secureRandom);
                                o oVar = new o(this.random, jVar.d());
                                this.param = oVar;
                                params.put(numB, oVar);
                            } else {
                                jVar.l(new q(1024, 160, defaultCertainty, this.random));
                                o oVar2 = new o(this.random, jVar.d());
                                this.param = oVar2;
                                params.put(numB, oVar2);
                            }
                        } else if (i3 > 1024) {
                            q qVar = new q(i3, 256, defaultCertainty, this.random);
                            jVar = new g.a.b.e0.j(new u());
                            jVar.l(qVar);
                            o oVar22 = new o(this.random, jVar.d());
                            this.param = oVar22;
                            params.put(numB, oVar22);
                        } else {
                            jVar = new g.a.b.e0.j();
                            i2 = this.strength;
                            secureRandom = this.random;
                            jVar.k(i2, defaultCertainty, secureRandom);
                            o oVar222 = new o(this.random, jVar.d());
                            this.param = oVar222;
                            params.put(numB, oVar222);
                        }
                    }
                }
            }
            this.engine.d(this.param);
            this.initialised = true;
        }
        b bVarA = this.engine.a();
        return new KeyPair(new BCDSAPublicKey((t) bVarA.b()), new BCDSAPrivateKey((s) bVarA.a()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        boolean z;
        if (i2 < 512 || i2 > 4096 || ((i2 < 1024 && i2 % 64 != 0) || (i2 >= 1024 && i2 % 1024 != 0))) {
            throw new InvalidParameterException("strength must be from 512 - 4096 and a multiple of 1024 above 1024");
        }
        DSAParameterSpec dSADefaultParameters = BouncyCastleProvider.CONFIGURATION.getDSADefaultParameters(i2);
        if (dSADefaultParameters != null) {
            o oVar = new o(secureRandom, new r(dSADefaultParameters.getP(), dSADefaultParameters.getQ(), dSADefaultParameters.getG()));
            this.param = oVar;
            this.engine.d(oVar);
            z = true;
        } else {
            this.strength = i2;
            this.random = secureRandom;
            z = false;
        }
        this.initialised = z;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof DSAParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
        }
        DSAParameterSpec dSAParameterSpec = (DSAParameterSpec) algorithmParameterSpec;
        o oVar = new o(secureRandom, new r(dSAParameterSpec.getP(), dSAParameterSpec.getQ(), dSAParameterSpec.getG()));
        this.param = oVar;
        this.engine.d(oVar);
        this.initialised = true;
    }
}
