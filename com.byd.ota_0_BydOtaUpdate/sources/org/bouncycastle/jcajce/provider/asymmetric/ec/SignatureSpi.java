package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.a.c1;
import g.a.a.f;
import g.a.a.j;
import g.a.a.q;
import g.a.a.r;
import g.a.b.a0.n;
import g.a.b.a0.p;
import g.a.b.j0.b;
import g.a.b.j0.x0;
import g.a.b.k;
import g.a.b.m0.d;
import g.a.b.m0.g;
import g.a.b.m0.i;
import g.a.f.a;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSABase;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;

/* JADX INFO: loaded from: classes.dex */
public class SignatureSpi extends DSABase {

    public static class PlainDSAEncoder implements DSAEncoder {
        private PlainDSAEncoder() {
        }

        private byte[] makeUnsigned(BigInteger bigInteger) {
            byte[] byteArray = bigInteger.toByteArray();
            if (byteArray[0] != 0) {
                return byteArray;
            }
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder
        public BigInteger[] decode(byte[] bArr) throws IOException {
            int length = bArr.length / 2;
            byte[] bArr2 = new byte[length];
            int length2 = bArr.length / 2;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            System.arraycopy(bArr, length, bArr3, 0, length2);
            return new BigInteger[]{new BigInteger(1, bArr2), new BigInteger(1, bArr3)};
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder
        public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
            byte[] bArrMakeUnsigned = makeUnsigned(bigInteger);
            byte[] bArrMakeUnsigned2 = makeUnsigned(bigInteger2);
            byte[] bArr = new byte[(bArrMakeUnsigned.length > bArrMakeUnsigned2.length ? bArrMakeUnsigned.length : bArrMakeUnsigned2.length) * 2];
            System.arraycopy(bArrMakeUnsigned, 0, bArr, (bArr.length / 2) - bArrMakeUnsigned.length, bArrMakeUnsigned.length);
            System.arraycopy(bArrMakeUnsigned2, 0, bArr, bArr.length - bArrMakeUnsigned2.length, bArrMakeUnsigned2.length);
            return bArr;
        }
    }

    public static class StdDSAEncoder implements DSAEncoder {
        private StdDSAEncoder() {
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder
        public BigInteger[] decode(byte[] bArr) throws IOException {
            r rVar = (r) q.j(bArr);
            if (rVar.size() != 2) {
                throw new IOException("malformed signature");
            }
            if (a.a(bArr, rVar.f("DER"))) {
                return new BigInteger[]{j.n(rVar.q(0)).q(), j.n(rVar.q(1)).q()};
            }
            throw new IOException("malformed signature");
        }

        @Override // org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder
        public byte[] encode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
            f fVar = new f();
            fVar.a(new j(bigInteger));
            fVar.a(new j(bigInteger2));
            return new c1(fVar).f("DER");
        }
    }

    public static class ecCVCDSA extends SignatureSpi {
        public ecCVCDSA() {
            super(g.a.b.n0.a.b(), new d(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA224 extends SignatureSpi {
        public ecCVCDSA224() {
            super(g.a.b.n0.a.c(), new d(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA256 extends SignatureSpi {
        public ecCVCDSA256() {
            super(g.a.b.n0.a.d(), new d(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA384 extends SignatureSpi {
        public ecCVCDSA384() {
            super(g.a.b.n0.a.e(), new d(), new PlainDSAEncoder());
        }
    }

    public static class ecCVCDSA512 extends SignatureSpi {
        public ecCVCDSA512() {
            super(g.a.b.n0.a.j(), new d(), new PlainDSAEncoder());
        }
    }

    public static class ecDSA extends SignatureSpi {
        public ecDSA() {
            super(g.a.b.n0.a.b(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSA224 extends SignatureSpi {
        public ecDSA224() {
            super(g.a.b.n0.a.c(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSA256 extends SignatureSpi {
        public ecDSA256() {
            super(g.a.b.n0.a.d(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSA384 extends SignatureSpi {
        public ecDSA384() {
            super(g.a.b.n0.a.e(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSA512 extends SignatureSpi {
        public ecDSA512() {
            super(g.a.b.n0.a.j(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSARipeMD160 extends SignatureSpi {
        public ecDSARipeMD160() {
            super(new p(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_224 extends SignatureSpi {
        public ecDSASha3_224() {
            super(g.a.b.n0.a.f(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_256 extends SignatureSpi {
        public ecDSASha3_256() {
            super(g.a.b.n0.a.g(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_384 extends SignatureSpi {
        public ecDSASha3_384() {
            super(g.a.b.n0.a.h(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSASha3_512 extends SignatureSpi {
        public ecDSASha3_512() {
            super(g.a.b.n0.a.i(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDSAnone extends SignatureSpi {
        public ecDSAnone() {
            super(new n(), new d(), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA extends SignatureSpi {
        public ecDetDSA() {
            super(g.a.b.n0.a.b(), new d(new i(g.a.b.n0.a.b())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA224 extends SignatureSpi {
        public ecDetDSA224() {
            super(g.a.b.n0.a.c(), new d(new i(g.a.b.n0.a.c())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA256 extends SignatureSpi {
        public ecDetDSA256() {
            super(g.a.b.n0.a.d(), new d(new i(g.a.b.n0.a.d())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA384 extends SignatureSpi {
        public ecDetDSA384() {
            super(g.a.b.n0.a.e(), new d(new i(g.a.b.n0.a.e())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSA512 extends SignatureSpi {
        public ecDetDSA512() {
            super(g.a.b.n0.a.j(), new d(new i(g.a.b.n0.a.j())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_224 extends SignatureSpi {
        public ecDetDSASha3_224() {
            super(g.a.b.n0.a.f(), new d(new i(g.a.b.n0.a.f())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_256 extends SignatureSpi {
        public ecDetDSASha3_256() {
            super(g.a.b.n0.a.g(), new d(new i(g.a.b.n0.a.g())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_384 extends SignatureSpi {
        public ecDetDSASha3_384() {
            super(g.a.b.n0.a.h(), new d(new i(g.a.b.n0.a.h())), new StdDSAEncoder());
        }
    }

    public static class ecDetDSASha3_512 extends SignatureSpi {
        public ecDetDSASha3_512() {
            super(g.a.b.n0.a.i(), new d(new i(g.a.b.n0.a.i())), new StdDSAEncoder());
        }
    }

    public static class ecNR extends SignatureSpi {
        public ecNR() {
            super(g.a.b.n0.a.b(), new g(), new StdDSAEncoder());
        }
    }

    public static class ecNR224 extends SignatureSpi {
        public ecNR224() {
            super(g.a.b.n0.a.c(), new g(), new StdDSAEncoder());
        }
    }

    public static class ecNR256 extends SignatureSpi {
        public ecNR256() {
            super(g.a.b.n0.a.d(), new g(), new StdDSAEncoder());
        }
    }

    public static class ecNR384 extends SignatureSpi {
        public ecNR384() {
            super(g.a.b.n0.a.e(), new g(), new StdDSAEncoder());
        }
    }

    public static class ecNR512 extends SignatureSpi {
        public ecNR512() {
            super(g.a.b.n0.a.j(), new g(), new StdDSAEncoder());
        }
    }

    public static class ecPlainDSARP160 extends SignatureSpi {
        public ecPlainDSARP160() {
            super(new p(), new d(), new PlainDSAEncoder());
        }
    }

    public SignatureSpi(g.a.b.n nVar, k kVar, DSAEncoder dSAEncoder) {
        super(nVar, kVar, dSAEncoder);
    }

    @Override // java.security.SignatureSpi
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        b bVarGeneratePrivateKeyParameter = ECUtil.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
        SecureRandom secureRandom = ((java.security.SignatureSpi) this).appRandom;
        if (secureRandom != null) {
            this.signer.init(true, new x0(bVarGeneratePrivateKeyParameter, secureRandom));
        } else {
            this.signer.init(true, bVarGeneratePrivateKeyParameter);
        }
    }

    @Override // java.security.SignatureSpi
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        b bVarGeneratePublicKeyParameter = ECUtils.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, bVarGeneratePublicKeyParameter);
    }
}
