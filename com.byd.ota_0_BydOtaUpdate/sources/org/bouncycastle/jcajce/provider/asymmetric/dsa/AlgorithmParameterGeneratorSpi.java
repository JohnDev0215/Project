package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import g.a.b.a0.u;
import g.a.b.e0.j;
import g.a.b.j0.q;
import g.a.b.j0.r;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAlgorithmParameterGeneratorSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

/* JADX INFO: loaded from: classes.dex */
public class AlgorithmParameterGeneratorSpi extends BaseAlgorithmParameterGeneratorSpi {
    public q params;
    public SecureRandom random;
    public int strength = 2048;

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public AlgorithmParameters engineGenerateParameters() {
        j jVar = this.strength <= 1024 ? new j() : new j(new u());
        if (this.random == null) {
            this.random = g.a.b.j.b();
        }
        int defaultCertainty = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
        int i2 = this.strength;
        if (i2 == 1024) {
            q qVar = new q(1024, 160, defaultCertainty, this.random);
            this.params = qVar;
            jVar.l(qVar);
        } else if (i2 > 1024) {
            q qVar2 = new q(i2, 256, defaultCertainty, this.random);
            this.params = qVar2;
            jVar.l(qVar2);
        } else {
            jVar.k(i2, defaultCertainty, this.random);
        }
        r rVarD = jVar.d();
        try {
            AlgorithmParameters algorithmParametersCreateParametersInstance = createParametersInstance("DSA");
            algorithmParametersCreateParametersInstance.init(new DSAParameterSpec(rVarD.b(), rVarD.c(), rVarD.a()));
            return algorithmParametersCreateParametersInstance;
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        }
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public void engineInit(int i2, SecureRandom secureRandom) {
        if (i2 < 512 || i2 > 3072) {
            throw new InvalidParameterException("strength must be from 512 - 3072");
        }
        if (i2 <= 1024 && i2 % 64 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 64 below 1024 bits.");
        }
        if (i2 > 1024 && i2 % 1024 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 1024 above 1024 bits.");
        }
        this.strength = i2;
        this.random = secureRandom;
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSA parameter generation.");
    }
}
