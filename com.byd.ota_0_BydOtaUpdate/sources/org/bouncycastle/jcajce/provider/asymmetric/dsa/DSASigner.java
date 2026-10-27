package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import g.a.a.c1;
import g.a.a.j;
import g.a.a.p2.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.w2.a0;
import g.a.b.j0.x0;
import g.a.b.k;
import g.a.b.m0.b;
import g.a.b.m0.i;
import g.a.b.n0.a;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class DSASigner extends SignatureSpi implements n, a0 {
    private g.a.b.n digest;
    private SecureRandom random;
    private k signer;

    public static class detDSA extends DSASigner {
        public detDSA() {
            super(a.b(), new b(new i(a.b())));
        }
    }

    public static class detDSA224 extends DSASigner {
        public detDSA224() {
            super(a.c(), new b(new i(a.c())));
        }
    }

    public static class detDSA256 extends DSASigner {
        public detDSA256() {
            super(a.d(), new b(new i(a.d())));
        }
    }

    public static class detDSA384 extends DSASigner {
        public detDSA384() {
            super(a.e(), new b(new i(a.e())));
        }
    }

    public static class detDSA512 extends DSASigner {
        public detDSA512() {
            super(a.j(), new b(new i(a.j())));
        }
    }

    public static class detDSASha3_224 extends DSASigner {
        public detDSASha3_224() {
            super(a.f(), new b(new i(a.f())));
        }
    }

    public static class detDSASha3_256 extends DSASigner {
        public detDSASha3_256() {
            super(a.g(), new b(new i(a.g())));
        }
    }

    public static class detDSASha3_384 extends DSASigner {
        public detDSASha3_384() {
            super(a.h(), new b(new i(a.h())));
        }
    }

    public static class detDSASha3_512 extends DSASigner {
        public detDSASha3_512() {
            super(a.i(), new b(new i(a.i())));
        }
    }

    public static class dsa224 extends DSASigner {
        public dsa224() {
            super(a.c(), new b());
        }
    }

    public static class dsa256 extends DSASigner {
        public dsa256() {
            super(a.d(), new b());
        }
    }

    public static class dsa384 extends DSASigner {
        public dsa384() {
            super(a.e(), new b());
        }
    }

    public static class dsa512 extends DSASigner {
        public dsa512() {
            super(a.j(), new b());
        }
    }

    public static class dsaSha3_224 extends DSASigner {
        public dsaSha3_224() {
            super(a.f(), new b());
        }
    }

    public static class dsaSha3_256 extends DSASigner {
        public dsaSha3_256() {
            super(a.g(), new b());
        }
    }

    public static class dsaSha3_384 extends DSASigner {
        public dsaSha3_384() {
            super(a.h(), new b());
        }
    }

    public static class dsaSha3_512 extends DSASigner {
        public dsaSha3_512() {
            super(a.i(), new b());
        }
    }

    public static class noneDSA extends DSASigner {
        public noneDSA() {
            super(new g.a.b.a0.n(), new b());
        }
    }

    public static class stdDSA extends DSASigner {
        public stdDSA() {
            super(a.b(), new b());
        }
    }

    public DSASigner(g.a.b.n nVar, k kVar) {
        this.digest = nVar;
        this.signer = kVar;
    }

    private BigInteger[] derDecode(byte[] bArr) throws IOException {
        r rVar = (r) q.j(bArr);
        if (rVar.size() != 2) {
            throw new IOException("malformed signature");
        }
        if (g.a.f.a.a(bArr, rVar.f("DER"))) {
            return new BigInteger[]{((j) rVar.q(0)).q(), ((j) rVar.q(1)).q()};
        }
        throw new IOException("malformed signature");
    }

    private byte[] derEncode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
        return new c1(new j[]{new j(bigInteger), new j(bigInteger2)}).f("DER");
    }

    @Override // java.security.SignatureSpi
    public Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        g.a.b.i iVarGeneratePrivateKeyParameter = DSAUtil.generatePrivateKeyParameter(privateKey);
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            iVarGeneratePrivateKeyParameter = new x0(iVarGeneratePrivateKeyParameter, secureRandom);
        }
        this.digest.reset();
        this.signer.init(true, iVarGeneratePrivateKeyParameter);
    }

    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        g.a.b.j0.b bVarGeneratePublicKeyParameter = DSAUtil.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, bVarGeneratePublicKeyParameter);
    }

    @Override // java.security.SignatureSpi
    public void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    public void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    public byte[] engineSign() throws SignatureException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            BigInteger[] bigIntegerArrA = this.signer.a(bArr);
            return derEncode(bigIntegerArrA[0], bigIntegerArrA[1]);
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    @Override // java.security.SignatureSpi
    public void engineUpdate(byte b2) throws SignatureException {
        this.digest.update(b2);
    }

    @Override // java.security.SignatureSpi
    public void engineUpdate(byte[] bArr, int i2, int i3) throws SignatureException {
        this.digest.update(bArr, i2, i3);
    }

    @Override // java.security.SignatureSpi
    public boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        try {
            BigInteger[] bigIntegerArrDerDecode = derDecode(bArr);
            return this.signer.b(bArr2, bigIntegerArrDerDecode[0], bigIntegerArrDerDecode[1]);
        } catch (Exception unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }
}
