package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import g.a.a.m;
import g.a.a.s2.b;
import g.a.a.w0;
import g.a.a.w2.a;
import g.a.a.w2.j;
import g.a.b.a0.k;
import g.a.b.a0.l;
import g.a.b.a0.o;
import g.a.b.a0.p;
import g.a.b.a0.q;
import g.a.b.c0.c;
import g.a.b.d0.n0;
import g.a.b.j0.e1;
import g.a.b.n;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class DigestSignatureSpi extends SignatureSpi {
    private a algId;
    private g.a.b.a cipher;
    private n digest;

    public static class MD2 extends DigestSignatureSpi {
        public MD2() {
            super(g.a.a.p2.n.N, new k(), new c(new n0()));
        }
    }

    public static class MD4 extends DigestSignatureSpi {
        public MD4() {
            super(g.a.a.p2.n.O, new l(), new c(new n0()));
        }
    }

    public static class MD5 extends DigestSignatureSpi {
        public MD5() {
            super(g.a.a.p2.n.P, g.a.b.n0.a.a(), new c(new n0()));
        }
    }

    public static class RIPEMD128 extends DigestSignatureSpi {
        public RIPEMD128() {
            super(b.f4045c, new o(), new c(new n0()));
        }
    }

    public static class RIPEMD160 extends DigestSignatureSpi {
        public RIPEMD160() {
            super(b.f4044b, new p(), new c(new n0()));
        }
    }

    public static class RIPEMD256 extends DigestSignatureSpi {
        public RIPEMD256() {
            super(b.f4046d, new q(), new c(new n0()));
        }
    }

    public static class SHA1 extends DigestSignatureSpi {
        public SHA1() {
            super(g.a.a.o2.b.f3948i, g.a.b.n0.a.b(), new c(new n0()));
        }
    }

    public static class SHA224 extends DigestSignatureSpi {
        public SHA224() {
            super(g.a.a.l2.b.f3912f, g.a.b.n0.a.c(), new c(new n0()));
        }
    }

    public static class SHA256 extends DigestSignatureSpi {
        public SHA256() {
            super(g.a.a.l2.b.f3909c, g.a.b.n0.a.d(), new c(new n0()));
        }
    }

    public static class SHA384 extends DigestSignatureSpi {
        public SHA384() {
            super(g.a.a.l2.b.f3910d, g.a.b.n0.a.e(), new c(new n0()));
        }
    }

    public static class SHA3_224 extends DigestSignatureSpi {
        public SHA3_224() {
            super(g.a.a.l2.b.f3915i, g.a.b.n0.a.f(), new c(new n0()));
        }
    }

    public static class SHA3_256 extends DigestSignatureSpi {
        public SHA3_256() {
            super(g.a.a.l2.b.j, g.a.b.n0.a.g(), new c(new n0()));
        }
    }

    public static class SHA3_384 extends DigestSignatureSpi {
        public SHA3_384() {
            super(g.a.a.l2.b.k, g.a.b.n0.a.h(), new c(new n0()));
        }
    }

    public static class SHA3_512 extends DigestSignatureSpi {
        public SHA3_512() {
            super(g.a.a.l2.b.l, g.a.b.n0.a.i(), new c(new n0()));
        }
    }

    public static class SHA512 extends DigestSignatureSpi {
        public SHA512() {
            super(g.a.a.l2.b.f3911e, g.a.b.n0.a.j(), new c(new n0()));
        }
    }

    public static class SHA512_224 extends DigestSignatureSpi {
        public SHA512_224() {
            super(g.a.a.l2.b.f3913g, g.a.b.n0.a.k(), new c(new n0()));
        }
    }

    public static class SHA512_256 extends DigestSignatureSpi {
        public SHA512_256() {
            super(g.a.a.l2.b.f3914h, g.a.b.n0.a.l(), new c(new n0()));
        }
    }

    public static class noneRSA extends DigestSignatureSpi {
        public noneRSA() {
            super(new g.a.b.a0.n(), new c(new n0()));
        }
    }

    public DigestSignatureSpi(m mVar, n nVar, g.a.b.a aVar) {
        this.digest = nVar;
        this.cipher = aVar;
        this.algId = new a(mVar, w0.f4105c);
    }

    public DigestSignatureSpi(n nVar, g.a.b.a aVar) {
        this.digest = nVar;
        this.cipher = aVar;
        this.algId = null;
    }

    private byte[] derEncode(byte[] bArr) throws IOException {
        a aVar = this.algId;
        return aVar == null ? bArr : new j(aVar, bArr).f("DER");
    }

    private String getType(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName();
    }

    @Override // java.security.SignatureSpi
    public Object engineGetParameter(String str) {
        return null;
    }

    @Override // java.security.SignatureSpi
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof RSAPrivateKey) {
            e1 e1VarGeneratePrivateKeyParameter = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey) privateKey);
            this.digest.reset();
            this.cipher.init(true, e1VarGeneratePrivateKeyParameter);
        } else {
            throw new InvalidKeyException("Supplied key (" + getType(privateKey) + ") is not a RSAPrivateKey instance");
        }
    }

    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof RSAPublicKey) {
            e1 e1VarGeneratePublicKeyParameter = RSAUtil.generatePublicKeyParameter((RSAPublicKey) publicKey);
            this.digest.reset();
            this.cipher.init(false, e1VarGeneratePublicKeyParameter);
        } else {
            throw new InvalidKeyException("Supplied key (" + getType(publicKey) + ") is not a RSAPublicKey instance");
        }
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
            byte[] bArrDerEncode = derEncode(bArr);
            return this.cipher.b(bArrDerEncode, 0, bArrDerEncode.length);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new SignatureException("key too small for signature type");
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
        byte[] bArrB;
        byte[] bArrDerEncode;
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        try {
            bArrB = this.cipher.b(bArr, 0, bArr.length);
            bArrDerEncode = derEncode(bArr2);
        } catch (Exception unused) {
        }
        if (bArrB.length == bArrDerEncode.length) {
            return g.a.f.a.q(bArrB, bArrDerEncode);
        }
        if (bArrB.length != bArrDerEncode.length - 2) {
            g.a.f.a.q(bArrDerEncode, bArrDerEncode);
            return false;
        }
        bArrDerEncode[1] = (byte) (bArrDerEncode[1] - 2);
        bArrDerEncode[3] = (byte) (bArrDerEncode[3] - 2);
        int i2 = bArrDerEncode[3] + 4;
        int i3 = i2 + 2;
        int i4 = 0;
        for (int i5 = 0; i5 < bArrDerEncode.length - i3; i5++) {
            i4 |= bArrB[i2 + i5] ^ bArrDerEncode[i3 + i5];
        }
        for (int i6 = 0; i6 < i2; i6++) {
            i4 |= bArrB[i6] ^ bArrDerEncode[i6];
        }
        return i4 == 0;
    }
}
