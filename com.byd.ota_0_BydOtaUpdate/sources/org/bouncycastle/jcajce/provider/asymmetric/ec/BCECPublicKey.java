package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.a.n;
import g.a.a.q;
import g.a.a.w2.a;
import g.a.a.w2.u;
import g.a.a.x2.i;
import g.a.a.x2.l;
import g.a.a.x2.m;
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
public class BCECPublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    public static final long serialVersionUID = 2422789860422731812L;
    private String algorithm;
    private transient ProviderConfiguration configuration;
    private transient c0 ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private boolean withCompression;

    public BCECPublicKey(String str, u uVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        this.algorithm = str;
        this.configuration = providerConfiguration;
        populateFromPubKeyInfo(uVar);
    }

    public BCECPublicKey(String str, c0 c0Var, e eVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecSpec = eVar == null ? createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB) : EC5Util.convertSpec(EC5Util.convertCurve(eVar.a(), eVar.e()), eVar);
        this.ecPublicKey = c0Var;
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String str, c0 c0Var, ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        x xVarB = c0Var.b();
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(xVarB.a(), xVarB.f()), xVarB);
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String str, c0 c0Var, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        this.algorithm = str;
        this.ecPublicKey = c0Var;
        this.ecSpec = null;
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String str, g gVar, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        this.algorithm = str;
        if (gVar.a() != null) {
            EllipticCurve ellipticCurveConvertCurve = EC5Util.convertCurve(gVar.a().a(), gVar.a().e());
            this.ecPublicKey = new c0(gVar.b(), ECUtil.getDomainParameters(providerConfiguration, gVar.a()));
            this.ecSpec = EC5Util.convertSpec(ellipticCurveConvertCurve, gVar.a());
        } else {
            this.ecPublicKey = new c0(providerConfiguration.getEcImplicitlyCa().a().g(gVar.b().f().t(), gVar.b().g().t()), EC5Util.getDomainParameters(providerConfiguration, null));
            this.ecSpec = null;
        }
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String str, ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        this.algorithm = str;
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(providerConfiguration, eCPublicKeySpec.getParams()));
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String str, BCECPublicKey bCECPublicKey) {
        this.algorithm = "EC";
        this.algorithm = str;
        this.ecPublicKey = bCECPublicKey.ecPublicKey;
        this.ecSpec = bCECPublicKey.ecSpec;
        this.withCompression = bCECPublicKey.withCompression;
        this.configuration = bCECPublicKey.configuration;
    }

    public BCECPublicKey(ECPublicKey eCPublicKey, ProviderConfiguration providerConfiguration) {
        this.algorithm = "EC";
        this.algorithm = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new c0(EC5Util.convertPoint(params, eCPublicKey.getW(), false), EC5Util.getDomainParameters(providerConfiguration, eCPublicKey.getParams()));
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, x xVar) {
        return new ECParameterSpec(ellipticCurve, EC5Util.convertPoint(xVar.b()), xVar.e(), xVar.c().intValue());
    }

    private void populateFromPubKeyInfo(u uVar) {
        g.a.a.x2.e eVarG = g.a.a.x2.e.g(uVar.g().j());
        d curve = EC5Util.getCurve(this.configuration, eVarG);
        this.ecSpec = EC5Util.convertToSpec(eVarG, curve);
        byte[] bArrP = uVar.j().p();
        n y0Var = new y0(bArrP);
        if (bArrP[0] == 4 && bArrP[1] == bArrP.length - 2 && ((bArrP[2] == 2 || bArrP[2] == 3) && new l().a(curve) >= bArrP.length - 3)) {
            try {
                y0Var = (n) q.j(bArrP);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
        this.ecPublicKey = new c0(new i(curve, y0Var).g(), ECUtil.getDomainParameters(this.configuration, eVarG));
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        byte[] bArr = (byte[]) objectInputStream.readObject();
        this.configuration = BouncyCastleProvider.CONFIGURATION;
        populateFromPubKeyInfo(u.i(q.j(bArr)));
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
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec, this.withCompression) : this.configuration.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECPublicKey)) {
            return false;
        }
        BCECPublicKey bCECPublicKey = (BCECPublicKey) obj;
        return this.ecPublicKey.c().e(bCECPublicKey.ecPublicKey.c()) && engineGetSpec().equals(bCECPublicKey.engineGetSpec());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new u(new a(m.z2, ECUtils.getDomainParametersFromName(this.ecSpec, this.withCompression)), n.n(new i(this.ecPublicKey.c(), this.withCompression).c()).p()));
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
        return ECUtil.publicKeyToString("EC", this.ecPublicKey.c(), engineGetSpec());
    }
}
