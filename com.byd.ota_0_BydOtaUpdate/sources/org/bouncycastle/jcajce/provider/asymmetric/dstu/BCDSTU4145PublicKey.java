package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import g.a.a.m;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.r;
import g.a.a.t2.a;
import g.a.a.t2.b;
import g.a.a.t2.c;
import g.a.a.t2.d;
import g.a.a.t2.f;
import g.a.a.w2.u;
import g.a.a.y0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
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
public class BCDSTU4145PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    public static final long serialVersionUID = 7026240464295649314L;
    private String algorithm;
    private transient d dstuParams;
    private transient c0 ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private boolean withCompression;

    public BCDSTU4145PublicKey(u uVar) {
        this.algorithm = "DSTU4145";
        populateFromPubKeyInfo(uVar);
    }

    public BCDSTU4145PublicKey(g gVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "DSTU4145";
        if (gVar.a() == null) {
            this.ecPublicKey = new c0(providerConfiguration.getEcImplicitlyCa().a().g(gVar.b().f().t(), gVar.b().g().t()), EC5Util.getDomainParameters(providerConfiguration, null));
            this.ecSpec = null;
        } else {
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(gVar.a().a(), gVar.a().e());
            this.ecPublicKey = new c0(gVar.b(), ECUtil.getDomainParameters(providerConfiguration, gVar.a()));
            this.ecSpec = EC5Util.convertSpec(ellipticCurveConvertCurve, gVar.a());
        }
    }

    public BCDSTU4145PublicKey(String str, c0 c0Var) {
        this.algorithm = "DSTU4145";
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = null;
    }

    public BCDSTU4145PublicKey(String str, c0 c0Var, e eVar) {
        this.algorithm = "DSTU4145";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecSpec = eVar == null ? createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB) : EC5Util.convertSpec(EC5Util.convertCurve(eVar.a(), eVar.e()), eVar);
        this.ecPublicKey = c0Var;
    }

    public BCDSTU4145PublicKey(String str, c0 c0Var, ECParameterSpec eCParameterSpec) {
        this.algorithm = "DSTU4145";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.algorithm = "DSTU4145";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(null, this.ecSpec));
    }

    public BCDSTU4145PublicKey(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        this.algorithm = "DSTU4145";
        this.ecPublicKey = bCDSTU4145PublicKey.ecPublicKey;
        this.ecSpec = bCDSTU4145PublicKey.ecSpec;
        this.withCompression = bCDSTU4145PublicKey.withCompression;
        this.dstuParams = bCDSTU4145PublicKey.dstuParams;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, x xVar) {
        return new ECParameterSpec(ellipticCurve, EC5Util.convertPoint(xVar.b()), xVar.e(), xVar.c().intValue());
    }

    private void populateFromPubKeyInfo(u uVar) {
        e eVar;
        o0 o0VarJ = uVar.j();
        this.algorithm = "DSTU4145";
        try {
            byte[] bArrP = ((n) q.j(o0VarJ.p())).p();
            m mVarG = uVar.g().g();
            m mVar = f.f4066b;
            if (mVarG.equals(mVar)) {
                reverseBytes(bArrP);
            }
            d dVarJ = d.j((r) uVar.g().j());
            this.dstuParams = dVarJ;
            if (dVarJ.l()) {
                m mVarK = this.dstuParams.k();
                x xVarA = c.a(mVarK);
                eVar = new g.a.c.e.c(mVarK.r(), xVarA.a(), xVarA.b(), xVarA.e(), xVarA.c(), xVarA.f());
            } else {
                b bVarI = this.dstuParams.i();
                byte[] bArrH = bVarI.h();
                if (uVar.g().g().equals(mVar)) {
                    reverseBytes(bArrH);
                }
                a aVarI = bVarI.i();
                d.e eVar2 = new d.e(aVarI.k(), aVarI.h(), aVarI.i(), aVarI.j(), bVarI.g(), new BigInteger(1, bArrH));
                byte[] bArrJ = bVarI.j();
                if (uVar.g().g().equals(mVar)) {
                    reverseBytes(bArrJ);
                }
                eVar = new e(eVar2, g.a.a.t2.e.a(eVar2, bArrJ), bVarI.l());
            }
            g.a.d.b.d dVarA = eVar.a();
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(dVarA, eVar.e());
            this.ecSpec = this.dstuParams.l() ? new g.a.c.e.d(this.dstuParams.k().r(), ellipticCurveConvertCurve, EC5Util.convertPoint(eVar.b()), eVar.d(), eVar.c()) : new ECParameterSpec(ellipticCurveConvertCurve, EC5Util.convertPoint(eVar.b()), eVar.d(), eVar.c().intValue());
            this.ecPublicKey = new c0(g.a.a.t2.e.a(dVarA, bArrP), EC5Util.getDomainParameters(null, this.ecSpec));
        } catch (IOException unused) {
            throw new IllegalArgumentException("error recovering public key");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        populateFromPubKeyInfo(u.i(q.j((byte[]) objectInputStream.readObject())));
    }

    private void reverseBytes(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[(bArr.length - 1) - i2];
            bArr[(bArr.length - 1) - i2] = b2;
        }
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
        if (!(obj instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) obj;
        return this.ecPublicKey.c().e(bCDSTU4145PublicKey.ecPublicKey.c()) && engineGetSpec().equals(bCDSTU4145PublicKey.engineGetSpec());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        g.a.a.e eVar = this.dstuParams;
        if (eVar == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof g.a.c.e.d) {
                eVar = new g.a.a.t2.d(new m(((g.a.c.e.d) this.ecSpec).c()));
            } else {
                g.a.d.b.d dVarConvertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                eVar = new g.a.a.x2.e(new g.a.a.x2.g(dVarConvertCurve, EC5Util.convertPoint(dVarConvertCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            }
        }
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new u(new g.a.a.w2.a(f.f4067c, eVar), new y0(g.a.a.t2.e.b(this.ecPublicKey.c()))));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
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
        h hVarC = this.ecPublicKey.c();
        return this.ecSpec == null ? hVarC.k() : hVarC;
    }

    public byte[] getSbox() {
        g.a.a.t2.d dVar = this.dstuParams;
        return dVar != null ? dVar.g() : g.a.a.t2.d.h();
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
