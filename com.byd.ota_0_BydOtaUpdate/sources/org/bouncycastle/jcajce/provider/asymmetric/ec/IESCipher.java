package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.b.d0.c0;
import g.a.b.e;
import g.a.b.e0.l;
import g.a.b.e0.o;
import g.a.b.e0.r;
import g.a.b.f;
import g.a.b.g0.g;
import g.a.b.i;
import g.a.b.j0.b;
import g.a.b.j0.p0;
import g.a.b.j0.w0;
import g.a.b.j0.x;
import g.a.b.j0.y;
import g.a.b.j0.z;
import g.a.b.n0.a;
import g.a.b.q;
import g.a.b.z.c;
import g.a.c.e.p;
import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.IESKey;

/* JADX INFO: loaded from: classes.dex */
public class IESCipher extends CipherSpi {
    private ByteArrayOutputStream buffer;
    private boolean dhaesMode;
    private c0 engine;
    private AlgorithmParameters engineParam;
    private p engineSpec;
    private final JcaJceHelper helper;
    private int ivLength;
    private b key;
    private b otherKeyParameter;
    private SecureRandom random;
    private int state;

    public static class ECIES extends IESCipher {
        public ECIES() {
            super(new c0(new c(), new r(a.b()), new g(a.b())));
        }
    }

    public static class ECIESwithAESCBC extends ECIESwithCipher {
        public ECIESwithAESCBC() {
            super(new g.a.b.h0.b(new g.a.b.d0.a()), 16);
        }
    }

    public static class ECIESwithCipher extends IESCipher {
        public ECIESwithCipher(e eVar, int i2) {
            super(new c0(new c(), new r(a.b()), new g(a.b()), new g.a.b.i0.e(eVar)), i2);
        }
    }

    public static class ECIESwithDESedeCBC extends ECIESwithCipher {
        public ECIESwithDESedeCBC() {
            super(new g.a.b.h0.b(new g.a.b.d0.p()), 8);
        }
    }

    public IESCipher(c0 c0Var) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = c0Var;
        this.ivLength = 0;
    }

    public IESCipher(c0 c0Var, int i2) {
        this.helper = new BCJcaJceHelper();
        this.state = -1;
        this.buffer = new ByteArrayOutputStream();
        this.engineParam = null;
        this.engineSpec = null;
        this.dhaesMode = false;
        this.otherKeyParameter = null;
        this.engine = c0Var;
        this.ivLength = i2;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        byte[] bArrEngineDoFinal = engineDoFinal(bArr, i2, i3);
        System.arraycopy(bArrEngineDoFinal, 0, bArr2, i4, bArrEngineDoFinal.length);
        return bArrEngineDoFinal.length;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i2, int i3) throws BadPaddingException, IllegalBlockSizeException {
        if (i3 != 0) {
            this.buffer.write(bArr, i2, i3);
        }
        byte[] byteArray = this.buffer.toByteArray();
        this.buffer.reset();
        i p0Var = new p0(this.engineSpec.b(), this.engineSpec.c(), this.engineSpec.d(), this.engineSpec.a());
        if (this.engineSpec.e() != null) {
            p0Var = new w0(p0Var, this.engineSpec.e());
        }
        x xVarB = ((z) this.key).b();
        b bVar = this.otherKeyParameter;
        if (bVar != null) {
            try {
                int i4 = this.state;
                if (i4 == 1 || i4 == 3) {
                    this.engine.i(true, bVar, this.key, p0Var);
                } else {
                    this.engine.i(false, this.key, bVar, p0Var);
                }
                return this.engine.j(byteArray, 0, byteArray.length);
            } catch (Exception e2) {
                throw new BadBlockException("unable to process block", e2);
            }
        }
        int i5 = this.state;
        if (i5 != 1 && i5 != 3) {
            if (i5 != 2 && i5 != 4) {
                throw new IllegalStateException("cipher not initialised");
            }
            try {
                this.engine.g(this.key, p0Var, new g.a.b.k0.b(xVarB));
                return this.engine.j(byteArray, 0, byteArray.length);
            } catch (InvalidCipherTextException e3) {
                throw new BadBlockException("unable to process block", e3);
            }
        }
        l lVar = new l();
        lVar.c(new y(xVarB, this.random));
        final boolean zF = this.engineSpec.f();
        try {
            this.engine.h(this.key, p0Var, new o(lVar, new q() { // from class: org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher.1
                @Override // g.a.b.q
                public byte[] getEncoded(b bVar2) {
                    return ((g.a.b.j0.c0) bVar2).c().l(zF);
                }
            }));
            return this.engine.j(byteArray, 0, byteArray.length);
        } catch (Exception e4) {
            throw new BadBlockException("unable to process block", e4);
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        if (this.engine.d() != null) {
            return this.engine.d().b();
        }
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        p pVar = this.engineSpec;
        if (pVar != null) {
            return pVar.e();
        }
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof g.a.c.c.a) {
            return ((g.a.c.c.a) key).getParameters().a().u();
        }
        throw new IllegalArgumentException("not an EC key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i2) {
        int size;
        f fVarD;
        if (this.key == null) {
            throw new IllegalStateException("cipher not initialised");
        }
        int macSize = this.engine.f().getMacSize();
        int iU = this.otherKeyParameter == null ? ((((z) this.key).b().a().u() + 7) / 8) * 2 : 0;
        if (this.engine.d() != null) {
            int i3 = this.state;
            if (i3 == 1 || i3 == 3) {
                fVarD = this.engine.d();
            } else {
                if (i3 != 2 && i3 != 4) {
                    throw new IllegalStateException("cipher not initialised");
                }
                fVarD = this.engine.d();
                i2 = (i2 - macSize) - iU;
            }
            i2 = fVarD.c(i2);
        }
        int i4 = this.state;
        if (i4 == 1 || i4 == 3) {
            size = this.buffer.size() + macSize + 1 + iU;
        } else {
            if (i4 != 2 && i4 != 4) {
                throw new IllegalStateException("cipher not initialised");
            }
            size = (this.buffer.size() - macSize) - iU;
        }
        return size + i2;
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParam == null && this.engineSpec != null) {
            try {
                AlgorithmParameters algorithmParametersCreateAlgorithmParameters = this.helper.createAlgorithmParameters("IES");
                this.engineParam = algorithmParametersCreateAlgorithmParameters;
                algorithmParametersCreateAlgorithmParameters.init(this.engineSpec);
            } catch (Exception e2) {
                throw new RuntimeException(e2.toString());
            }
        }
        return this.engineParam;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            try {
                parameterSpec = algorithmParameters.getParameterSpec(p.class);
            } catch (Exception e2) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e2.toString());
            }
        } else {
            parameterSpec = null;
        }
        this.engineParam = algorithmParameters;
        engineInit(i2, key, parameterSpec, secureRandom);
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i2, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new IllegalArgumentException("cannot handle supplied parameter spec: " + e2.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        p pVarGuessParameterSpec;
        b bVarGeneratePublicKeyParameter;
        PrivateKey privateKey;
        byte[] bArr = null;
        this.otherKeyParameter = null;
        if (algorithmParameterSpec == null) {
            int i3 = this.ivLength;
            if (i3 != 0 && i2 == 1) {
                bArr = new byte[i3];
                secureRandom.nextBytes(bArr);
            }
            pVarGuessParameterSpec = IESUtil.guessParameterSpec(this.engine.d(), bArr);
        } else {
            if (!(algorithmParameterSpec instanceof p)) {
                throw new InvalidAlgorithmParameterException("must be passed IES parameters");
            }
            pVarGuessParameterSpec = (p) algorithmParameterSpec;
        }
        this.engineSpec = pVarGuessParameterSpec;
        byte[] bArrE = this.engineSpec.e();
        int i4 = this.ivLength;
        if (i4 != 0 && (bArrE == null || bArrE.length != i4)) {
            throw new InvalidAlgorithmParameterException("NONCE in IES Parameters needs to be " + this.ivLength + " bytes long");
        }
        if (i2 == 1 || i2 == 3) {
            if (!(key instanceof PublicKey)) {
                if (!(key instanceof IESKey)) {
                    throw new InvalidKeyException("must be passed recipient's public EC key for encryption");
                }
                IESKey iESKey = (IESKey) key;
                this.key = ECUtils.generatePublicKeyParameter(iESKey.getPublic());
                this.otherKeyParameter = ECUtil.generatePrivateKeyParameter(iESKey.getPrivate());
                this.random = secureRandom;
                this.state = i2;
                this.buffer.reset();
            }
            bVarGeneratePublicKeyParameter = ECUtils.generatePublicKeyParameter((PublicKey) key);
        } else {
            if (i2 != 2 && i2 != 4) {
                throw new InvalidKeyException("must be passed EC key");
            }
            if (key instanceof PrivateKey) {
                privateKey = (PrivateKey) key;
            } else {
                if (!(key instanceof IESKey)) {
                    throw new InvalidKeyException("must be passed recipient's private EC key for decryption");
                }
                IESKey iESKey2 = (IESKey) key;
                this.otherKeyParameter = ECUtils.generatePublicKeyParameter(iESKey2.getPublic());
                privateKey = iESKey2.getPrivate();
            }
            bVarGeneratePublicKeyParameter = ECUtil.generatePrivateKeyParameter(privateKey);
        }
        this.key = bVarGeneratePublicKeyParameter;
        this.random = secureRandom;
        this.state = i2;
        this.buffer.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        boolean z;
        String strK = g.a.f.i.k(str);
        if (strK.equals("NONE")) {
            z = false;
        } else {
            if (!strK.equals("DHAES")) {
                throw new IllegalArgumentException("can't support mode " + str);
            }
            z = true;
        }
        this.dhaesMode = z;
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        String strK = g.a.f.i.k(str);
        if (!strK.equals("NOPADDING") && !strK.equals("PKCS5PADDING") && !strK.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        this.buffer.write(bArr, i2, i3);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i2, int i3) {
        this.buffer.write(bArr, i2, i3);
        return null;
    }
}
