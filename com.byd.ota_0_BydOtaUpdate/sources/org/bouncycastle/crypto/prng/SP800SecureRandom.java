package org.bouncycastle.crypto.prng;

import g.a.b.l0.b;
import g.a.b.l0.e;
import g.a.b.l0.h.c;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class SP800SecureRandom extends SecureRandom {
    private c drbg;
    private final b drbgProvider;
    private final g.a.b.l0.c entropySource;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;

    public SP800SecureRandom(SecureRandom secureRandom, g.a.b.l0.c cVar, b bVar, boolean z) {
        this.randomSource = secureRandom;
        this.entropySource = cVar;
        this.drbgProvider = bVar;
        this.predictionResistant = z;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i2) {
        return e.a(this.entropySource, i2);
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.a(this.entropySource);
            }
            if (this.drbg.a(bArr, null, this.predictionResistant) < 0) {
                this.drbg.b(null);
                this.drbg.a(bArr, null, this.predictionResistant);
            }
        }
    }

    public void reseed(byte[] bArr) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.a(this.entropySource);
            }
            this.drbg.b(bArr);
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
        synchronized (this) {
            SecureRandom secureRandom = this.randomSource;
            if (secureRandom != null) {
                secureRandom.setSeed(j);
            }
        }
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
            SecureRandom secureRandom = this.randomSource;
            if (secureRandom != null) {
                secureRandom.setSeed(bArr);
            }
        }
    }
}
