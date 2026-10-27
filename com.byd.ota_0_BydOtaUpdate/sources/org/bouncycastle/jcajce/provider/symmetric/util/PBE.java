package org.bouncycastle.jcajce.provider.symmetric.util;

import g.a.b.a0.d;
import g.a.b.a0.d0;
import g.a.b.a0.k;
import g.a.b.a0.p;
import g.a.b.e0.s;
import g.a.b.e0.t;
import g.a.b.e0.v;
import g.a.b.i;
import g.a.b.j0.c;
import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.n0.a;
import g.a.b.u;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public interface PBE {
    public static final int GOST3411 = 6;
    public static final int MD2 = 5;
    public static final int MD5 = 0;
    public static final int OPENSSL = 3;
    public static final int PKCS12 = 2;
    public static final int PKCS5S1 = 0;
    public static final int PKCS5S1_UTF8 = 4;
    public static final int PKCS5S2 = 1;
    public static final int PKCS5S2_UTF8 = 5;
    public static final int RIPEMD160 = 2;
    public static final int SHA1 = 1;
    public static final int SHA224 = 7;
    public static final int SHA256 = 4;
    public static final int SHA384 = 8;
    public static final int SHA3_224 = 10;
    public static final int SHA3_256 = 11;
    public static final int SHA3_384 = 12;
    public static final int SHA3_512 = 13;
    public static final int SHA512 = 9;
    public static final int TIGER = 3;

    public static class Util {
        private static byte[] convertPassword(int i2, PBEKeySpec pBEKeySpec) {
            return i2 == 2 ? u.a(pBEKeySpec.getPassword()) : (i2 == 5 || i2 == 4) ? u.c(pBEKeySpec.getPassword()) : u.b(pBEKeySpec.getPassword());
        }

        private static u makePBEGenerator(int i2, int i3) {
            if (i2 == 0 || i2 == 4) {
                if (i3 == 0) {
                    return new g.a.b.e0.u(a.a());
                }
                if (i3 == 1) {
                    return new g.a.b.e0.u(a.b());
                }
                if (i3 == 5) {
                    return new g.a.b.e0.u(new k());
                }
                throw new IllegalStateException("PKCS5 scheme 1 only supports MD2, MD5 and SHA1.");
            }
            if (i2 != 1 && i2 != 5) {
                if (i2 != 2) {
                    return new s();
                }
                switch (i3) {
                    case 0:
                        return new t(a.a());
                    case 1:
                        return new t(a.b());
                    case 2:
                        return new t(new p());
                    case 3:
                        return new t(new d0());
                    case 4:
                        return new t(a.d());
                    case 5:
                        return new t(new k());
                    case 6:
                        return new t(new d());
                    case 7:
                        return new t(a.c());
                    case 8:
                        return new t(a.e());
                    case 9:
                        return new t(a.j());
                    default:
                        throw new IllegalStateException("unknown digest scheme for PBE encryption.");
                }
            }
            switch (i3) {
                case 0:
                    return new v(a.a());
                case 1:
                    return new v(a.b());
                case 2:
                    return new v(new p());
                case 3:
                    return new v(new d0());
                case 4:
                    return new v(a.d());
                case 5:
                    return new v(new k());
                case 6:
                    return new v(new d());
                case 7:
                    return new v(a.c());
                case 8:
                    return new v(a.e());
                case 9:
                    return new v(a.j());
                case 10:
                    return new v(a.f());
                case 11:
                    return new v(a.g());
                case 12:
                    return new v(a.h());
                case 13:
                    return new v(a.i());
                default:
                    throw new IllegalStateException("unknown digest scheme for PBE PKCS5S2 encryption.");
            }
        }

        public static i makePBEMacParameters(SecretKey secretKey, int i2, int i3, int i4, PBEParameterSpec pBEParameterSpec) {
            u uVarMakePBEGenerator = makePBEGenerator(i2, i3);
            byte[] encoded = secretKey.getEncoded();
            uVarMakePBEGenerator.g(secretKey.getEncoded(), pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            i iVarD = uVarMakePBEGenerator.d(i4);
            for (int i5 = 0; i5 != encoded.length; i5++) {
                encoded[i5] = 0;
            }
            return iVarD;
        }

        public static i makePBEMacParameters(PBEKeySpec pBEKeySpec, int i2, int i3, int i4) {
            u uVarMakePBEGenerator = makePBEGenerator(i2, i3);
            byte[] bArrConvertPassword = convertPassword(i2, pBEKeySpec);
            uVarMakePBEGenerator.g(bArrConvertPassword, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            i iVarD = uVarMakePBEGenerator.d(i4);
            for (int i5 = 0; i5 != bArrConvertPassword.length; i5++) {
                bArrConvertPassword[i5] = 0;
            }
            return iVarD;
        }

        public static i makePBEMacParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            u uVarMakePBEGenerator = makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            uVarMakePBEGenerator.g(bCPBEKey.getEncoded(), pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            return uVarMakePBEGenerator.d(bCPBEKey.getKeySize());
        }

        public static i makePBEParameters(PBEKeySpec pBEKeySpec, int i2, int i3, int i4, int i5) {
            u uVarMakePBEGenerator = makePBEGenerator(i2, i3);
            byte[] bArrConvertPassword = convertPassword(i2, pBEKeySpec);
            uVarMakePBEGenerator.g(bArrConvertPassword, pBEKeySpec.getSalt(), pBEKeySpec.getIterationCount());
            i iVarF = i5 != 0 ? uVarMakePBEGenerator.f(i4, i5) : uVarMakePBEGenerator.e(i4);
            for (int i6 = 0; i6 != bArrConvertPassword.length; i6++) {
                bArrConvertPassword[i6] = 0;
            }
            return iVarF;
        }

        public static i makePBEParameters(BCPBEKey bCPBEKey, AlgorithmParameterSpec algorithmParameterSpec, String str) {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new IllegalArgumentException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            u uVarMakePBEGenerator = makePBEGenerator(bCPBEKey.getType(), bCPBEKey.getDigest());
            byte[] encoded = bCPBEKey.getEncoded();
            if (bCPBEKey.shouldTryWrongPKCS12()) {
                encoded = new byte[2];
            }
            uVarMakePBEGenerator.g(encoded, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            i iVarF = bCPBEKey.getIvSize() != 0 ? uVarMakePBEGenerator.f(bCPBEKey.getKeySize(), bCPBEKey.getIvSize()) : uVarMakePBEGenerator.e(bCPBEKey.getKeySize());
            if (str.startsWith("DES")) {
                if (iVarF instanceof w0) {
                    c.c(((s0) ((w0) iVarF).b()).a());
                } else {
                    c.c(((s0) iVarF).a());
                }
            }
            return iVarF;
        }

        public static i makePBEParameters(byte[] bArr, int i2, int i3, int i4, int i5, AlgorithmParameterSpec algorithmParameterSpec, String str) throws InvalidAlgorithmParameterException {
            if (algorithmParameterSpec == null || !(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidAlgorithmParameterException("Need a PBEParameter spec with a PBE key.");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
            u uVarMakePBEGenerator = makePBEGenerator(i2, i3);
            uVarMakePBEGenerator.g(bArr, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
            i iVarF = i5 != 0 ? uVarMakePBEGenerator.f(i4, i5) : uVarMakePBEGenerator.e(i4);
            if (str.startsWith("DES")) {
                if (iVarF instanceof w0) {
                    c.c(((s0) ((w0) iVarF).b()).a());
                } else {
                    c.c(((s0) iVarF).a());
                }
            }
            return iVarF;
        }
    }
}
