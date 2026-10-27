package org.bouncycastle.jcajce.provider.asymmetric.ecgost;

import g.a.a.d2.b;
import g.a.a.d2.f;
import g.a.a.e;
import g.a.a.m;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.w2.u;
import g.a.a.y0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.c.a;
import g.a.c.e.c;
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
public class BCECGOST3410PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    public static final long serialVersionUID = 7026240464295649314L;
    private String algorithm;
    private transient c0 ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private transient e gostParams;
    private boolean withCompression;

    public BCECGOST3410PublicKey(u uVar) {
        this.algorithm = "ECGOST3410";
        populateFromPubKeyInfo(uVar);
    }

    public BCECGOST3410PublicKey(g gVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "ECGOST3410";
        if (gVar.a() == null) {
            this.ecPublicKey = new c0(providerConfiguration.getEcImplicitlyCa().a().g(gVar.b().f().t(), gVar.b().g().t()), EC5Util.getDomainParameters(providerConfiguration, null));
            this.ecSpec = null;
        } else {
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(gVar.a().a(), gVar.a().e());
            this.ecPublicKey = new c0(gVar.b(), ECUtil.getDomainParameters(providerConfiguration, gVar.a()));
            this.ecSpec = EC5Util.convertSpec(ellipticCurveConvertCurve, gVar.a());
        }
    }

    public BCECGOST3410PublicKey(String str, c0 c0Var) {
        this.algorithm = "ECGOST3410";
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = null;
    }

    public BCECGOST3410PublicKey(String str, c0 c0Var, g.a.c.e.e eVar) {
        this.algorithm = "ECGOST3410";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = eVar == null ? createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB) : EC5Util.convertSpec(EC5Util.convertCurve(eVar.a(), eVar.e()), eVar);
    }

    public BCECGOST3410PublicKey(String str, c0 c0Var, ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCECGOST3410PublicKey(ECPublicKey eCPublicKey) {
        this.algorithm = "ECGOST3410";
        this.algorithm = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKey.getW(), false), EC5Util.getDomainParameters(null, eCPublicKey.getParams()));
    }

    public BCECGOST3410PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.algorithm = "ECGOST3410";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(null, eCPublicKeySpec.getParams()));
    }

    public BCECGOST3410PublicKey(BCECGOST3410PublicKey bCECGOST3410PublicKey) {
        this.algorithm = "ECGOST3410";
        this.ecPublicKey = bCECGOST3410PublicKey.ecPublicKey;
        this.ecSpec = bCECGOST3410PublicKey.ecSpec;
        this.withCompression = bCECGOST3410PublicKey.withCompression;
        this.gostParams = bCECGOST3410PublicKey.gostParams;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, x xVar) {
        return new ECParameterSpec(ellipticCurve, EC5Util.convertPoint(xVar.b()), xVar.e(), xVar.c().intValue());
    }

    private void extractBytes(byte[] bArr, int i2, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(byteArray, 0, bArr2, 32 - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i3 = 0; i3 != 32; i3++) {
            bArr[i2 + i3] = byteArray[(byteArray.length - 1) - i3];
        }
    }

    private void populateFromPubKeyInfo(u uVar) {
        m mVarJ;
        o0 o0VarJ = uVar.j();
        this.algorithm = "ECGOST3410";
        try {
            byte[] bArrP = ((n) q.j(o0VarJ.p())).p();
            byte[] bArr = new byte[65];
            bArr[0] = 4;
            for (int i2 = 1; i2 <= 32; i2++) {
                bArr[i2] = bArrP[32 - i2];
                bArr[i2 + 32] = bArrP[64 - i2];
            }
            boolean z = uVar.g().j() instanceof m;
            e eVarJ = uVar.g().j();
            if (z) {
                mVarJ = m.s(eVarJ);
                this.gostParams = mVarJ;
            } else {
                f fVarI = f.i(eVarJ);
                this.gostParams = fVarI;
                mVarJ = fVarI.j();
            }
            c cVarA = a.a(b.c(mVarJ));
            d dVarA = cVarA.a();
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(dVarA, cVarA.e());
            this.ecPublicKey = new c0(dVarA.k(bArr), ECUtil.getDomainParameters((ProviderConfiguration) null, cVarA));
            this.ecSpec = new g.a.c.e.d(b.c(mVarJ), ellipticCurveConvertCurve, EC5Util.convertPoint(cVarA.b()), cVarA.d(), cVarA.c());
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

    public g.a.c.e.e engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec, this.withCompression) : BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410PublicKey)) {
            return false;
        }
        BCECGOST3410PublicKey bCECGOST3410PublicKey = (BCECGOST3410PublicKey) obj;
        return this.ecPublicKey.c().e(bCECGOST3410PublicKey.ecPublicKey.c()) && engineGetSpec().equals(bCECGOST3410PublicKey.engineGetSpec());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        e eVar;
        e gostParams = getGostParams();
        if (gostParams == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof g.a.c.e.d) {
                eVar = new f(b.e(((g.a.c.e.d) eCParameterSpec).c()), g.a.a.d2.a.p);
            } else {
                d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                eVar = new g.a.a.x2.e(new g.a.a.x2.g(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            }
            gostParams = eVar;
        }
        BigInteger bigIntegerT = this.ecPublicKey.c().f().t();
        BigInteger bigIntegerT2 = this.ecPublicKey.c().g().t();
        byte[] bArr = new byte[64];
        extractBytes(bArr, 0, bigIntegerT);
        extractBytes(bArr, 32, bigIntegerT2);
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new u(new g.a.a.w2.a(g.a.a.d2.a.m, gostParams), new y0(bArr)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public e getGostParams() {
        if (this.gostParams == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof g.a.c.e.d) {
                this.gostParams = new f(b.e(((g.a.c.e.d) eCParameterSpec).c()), g.a.a.d2.a.p);
            }
        }
        return this.gostParams;
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey, g.a.c.c.a
    public g.a.c.e.e getParameters() {
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
