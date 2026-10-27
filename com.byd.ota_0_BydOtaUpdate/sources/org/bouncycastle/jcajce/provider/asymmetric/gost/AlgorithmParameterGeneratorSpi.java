package org.bouncycastle.jcajce.provider.asymmetric.gost;

import g.a.b.e0.q;
import g.a.b.j;
import g.a.b.j0.k0;
import g.a.c.e.l;
import g.a.c.e.n;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAlgorithmParameterGeneratorSpi;

/* JADX INFO: loaded from: classes.dex */
public abstract class AlgorithmParameterGeneratorSpi extends BaseAlgorithmParameterGeneratorSpi {
    public SecureRandom random;
    public int strength = 1024;

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public AlgorithmParameters engineGenerateParameters() {
        q qVar = new q();
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            qVar.b(this.strength, 2, secureRandom);
        } else {
            qVar.b(this.strength, 2, j.b());
        }
        k0 k0VarA = qVar.a();
        try {
            AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("GOST3410");
            algorithmParametersCreateParametersInstance.init(new l(new n(k0VarA.b(), k0VarA.c(), k0VarA.a())));
            return algorithmParametersCreateParametersInstance;
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        }
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public void engineInit(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for GOST3410 parameter generation.");
    }
}
