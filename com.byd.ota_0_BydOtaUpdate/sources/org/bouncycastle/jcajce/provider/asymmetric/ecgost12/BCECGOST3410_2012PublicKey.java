package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import g.a.a.d2.b;
import g.a.a.d2.f;
import g.a.a.m;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.q2.a;
import g.a.a.w2.u;
import g.a.a.y0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.c.e.c;
import g.a.c.e.e;
import g.a.c.e.g;
import g.a.d.b.d;
import g.a.d.b.h;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCECGOST3410_2012PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    public static final long serialVersionUID = 7026240464295649314L;
    private String algorithm;
    private transient c0 ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private transient f gostParams;
    private boolean withCompression;

    public BCECGOST3410_2012PublicKey(u uVar) {
        this.algorithm = "ECGOST3410-2012";
        populateFromPubKeyInfo(uVar);
    }

    public BCECGOST3410_2012PublicKey(g gVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "ECGOST3410-2012";
        if (gVar.a() == null) {
            this.ecPublicKey = new c0(providerConfiguration.getEcImplicitlyCa().a().g(gVar.b().f().t(), gVar.b().g().t()), EC5Util.getDomainParameters(providerConfiguration, null));
            this.ecSpec = null;
        } else {
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(gVar.a().a(), gVar.a().e());
            this.ecPublicKey = new c0(gVar.b(), ECUtil.getDomainParameters(providerConfiguration, gVar.a()));
            this.ecSpec = EC5Util.convertSpec(ellipticCurveConvertCurve, gVar.a());
        }
    }

    public BCECGOST3410_2012PublicKey(String str, c0 c0Var) {
        this.algorithm = "ECGOST3410-2012";
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = null;
    }

    public BCECGOST3410_2012PublicKey(String str, c0 c0Var, e eVar) {
        this.algorithm = "ECGOST3410-2012";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = eVar == null ? createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB) : EC5Util.convertSpec(EC5Util.convertCurve(eVar.a(), eVar.e()), eVar);
    }

    public BCECGOST3410_2012PublicKey(String str, c0 c0Var, ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410-2012";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCECGOST3410_2012PublicKey(ECPublicKey eCPublicKey) {
        this.algorithm = "ECGOST3410-2012";
        this.algorithm = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKey.getW(), false), EC5Util.getDomainParameters(null, eCPublicKey.getParams()));
    }

    public BCECGOST3410_2012PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.algorithm = "ECGOST3410-2012";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(null, eCPublicKeySpec.getParams()));
    }

    public BCECGOST3410_2012PublicKey(BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey) {
        this.algorithm = "ECGOST3410-2012";
        this.ecPublicKey = bCECGOST3410_2012PublicKey.ecPublicKey;
        this.ecSpec = bCECGOST3410_2012PublicKey.ecSpec;
        this.withCompression = bCECGOST3410_2012PublicKey.withCompression;
        this.gostParams = bCECGOST3410_2012PublicKey.gostParams;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, x xVar) {
        return new ECParameterSpec(ellipticCurve, EC5Util.convertPoint(xVar.b()), xVar.e(), xVar.c().intValue());
    }

    private void extractBytes(byte[] bArr, int i2, int i3, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(byteArray, 0, bArr2, i2 - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i4 = 0; i4 != i2; i4++) {
            bArr[i3 + i4] = byteArray[(byteArray.length - 1) - i4];
        }
    }

    private void populateFromPubKeyInfo(u uVar) {
        m mVarG = uVar.g().g();
        o0 o0VarJ = uVar.j();
        this.algorithm = "ECGOST3410-2012";
        try {
            byte[] bArrP = ((n) q.j(o0VarJ.p())).p();
            int i2 = mVarG.equals(a.f4009h) ? 64 : 32;
            int i3 = i2 * 2;
            byte[] bArr = new byte[i3 + 1];
            bArr[0] = 4;
            for (int i4 = 1; i4 <= i2; i4++) {
                bArr[i4] = bArrP[i2 - i4];
                bArr[i4 + i2] = bArrP[i3 - i4];
            }
            f fVarI = f.i(uVar.g().j());
            this.gostParams = fVarI;
            c cVarA = g.a.c.a.a(b.c(fVarI.j()));
            d dVarA = cVarA.a();
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(dVarA, cVarA.e());
            this.ecPublicKey = new c0(dVarA.k(bArr), ECUtil.getDomainParameters((ProviderConfiguration) null, cVarA));
            this.ecSpec = new g.a.c.e.d(b.c(this.gostParams.j()), ellipticCurveConvertCurve, EC5Util.convertPoint(cVarA.b()), cVarA.d(), cVarA.c());
        } catch (IOException unused) {
            throw new IllegalArgumentException("error recovering public key");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        populateFromPubKeyInfo(u.i(q.j((byte[]) objectInputStream.readObject())));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public c0 engineGetKeyParameters() {
        return this.ecPublicKey;
    }

    public e engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec, this.withCompression) : BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410_2012PublicKey)) {
            return false;
        }
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = (BCECGOST3410_2012PublicKey) obj;
        return this.ecPublicKey.c().e(bCECGOST3410_2012PublicKey.ecPublicKey.c()) && engineGetSpec().equals(bCECGOST3410_2012PublicKey.engineGetSpec());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        int i2;
        m mVar;
        g.a.a.e eVar;
        f fVar;
        BigInteger bigIntegerT = this.ecPublicKey.c().f().t();
        BigInteger bigIntegerT2 = this.ecPublicKey.c().g().t();
        boolean z = bigIntegerT.bitLength() > 256;
        f gostParams = getGostParams();
        g.a.a.e eVar2 = gostParams;
        if (gostParams == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof g.a.c.e.d) {
                m mVarE = b.e(((g.a.c.e.d) eCParameterSpec).c());
                if (z) {
                    eVar = fVar;
                    fVar = new f(mVarE, a.f4005d);
                } else {
                    eVar = fVar;
                    fVar = new f(mVarE, a.f4004c);
                }
            } else {
                d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                eVar = new g.a.a.x2.e(new g.a.a.x2.g(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            }
            eVar2 = eVar;
        }
        int i3 = 64;
        if (z) {
            mVar = a.f4009h;
            i3 = 128;
            i2 = 64;
        } else {
            i2 = 32;
            mVar = a.f4008g;
        }
        byte[] bArr = new byte[i3];
        int i4 = i3 / 2;
        extractBytes(bArr, i4, 0, bigIntegerT);
        extractBytes(bArr, i4, i2, bigIntegerT2);
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new u(new g.a.a.w2.a(mVar, eVar2), new y0(bArr)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public f getGostParams() {
        if (this.gostParams == null && (this.ecSpec instanceof g.a.c.e.d)) {
            this.gostParams = this.ecPublicKey.c().f().t().bitLength() > 256 ? new f(b.e(((g.a.c.e.d) this.ecSpec).c()), a.f4005d) : new f(b.e(((g.a.c.e.d) this.ecSpec).c()), a.f4004c);
        }
        return this.gostParams;
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey, g.a.c.c.a
    public e getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec, this.withCompression);
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey
    public h getQ() {
        return this.ecSpec == null ? this.ecPublicKey.c().k() : this.ecPublicKey.c();
    }

    @Override // java.security.interfaces.ECPublicKey
    public ECPoint getW() {
        return EC5Util.convertPoint(this.ecPublicKey.c());
    }

    public int hashCode() {
        return this.ecPublicKey.c().hashCode() ^ engineGetSpec().hashCode();
    }

    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return ECUtil.publicKeyToString(this.algorithm, this.ecPublicKey.c(), engineGetSpec());
    }
}
