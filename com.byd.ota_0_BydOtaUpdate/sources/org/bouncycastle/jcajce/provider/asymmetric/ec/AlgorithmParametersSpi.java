package org.bouncycastle.jcajce.provider.asymmetric.ec;

import g.a.a.k;
import g.a.a.m;
import g.a.a.w0;
import g.a.a.x2.b;
import g.a.a.x2.e;
import g.a.a.x2.g;
import g.a.c.e.d;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    private String curveName;
    private ECParameterSpec ecParameterSpec;

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded() throws IOException {
        return engineGetEncoded("ASN.1");
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded(String str) throws IOException {
        e eVar;
        if (!isASN1FormatString(str)) {
            throw new IOException("Unknown parameters format in AlgorithmParameters object: " + str);
        }
        ECParameterSpec eCParameterSpec = this.ecParameterSpec;
        if (eCParameterSpec == null) {
            eVar = new e((k) w0.f4105c);
        } else {
            String str2 = this.curveName;
            if (str2 != null) {
                eVar = new e(ECUtil.getNamedCurveOid(str2));
            } else {
                g.a.c.e.e eVarConvertSpec = EC5Util.convertSpec(eCParameterSpec, false);
                eVar = new e(new g(eVarConvertSpec.a(), eVarConvertSpec.b(), eVarConvertSpec.d(), eVarConvertSpec.c(), eVarConvertSpec.e()));
            }
        }
        return eVar.e();
    }

    @Override // java.security.AlgorithmParametersSpi
    public <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (ECParameterSpec.class.isAssignableFrom(cls) || cls == AlgorithmParameterSpec.class) {
            return this.ecParameterSpec;
        }
        if (ECGenParameterSpec.class.isAssignableFrom(cls)) {
            String str = this.curveName;
            if (str != null) {
                m namedCurveOid = ECUtil.getNamedCurveOid(str);
                return namedCurveOid != null ? new ECGenParameterSpec(namedCurveOid.r()) : new ECGenParameterSpec(this.curveName);
            }
            m namedCurveOid2 = ECUtil.getNamedCurveOid(EC5Util.convertSpec(this.ecParameterSpec, false));
            if (namedCurveOid2 != null) {
                return new ECGenParameterSpec(namedCurveOid2.r());
            }
        }
        throw new InvalidParameterSpecException("EC AlgorithmParameters cannot convert to " + cls.getName());
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        ECParameterSpec eCParameterSpecConvertToSpec;
        if (algorithmParameterSpec instanceof ECGenParameterSpec) {
            ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec) algorithmParameterSpec;
            g domainParametersFromGenSpec = ECUtils.getDomainParametersFromGenSpec(eCGenParameterSpec);
            if (domainParametersFromGenSpec == null) {
                throw new InvalidParameterSpecException("EC curve name not recognized: " + eCGenParameterSpec.getName());
            }
            this.curveName = eCGenParameterSpec.getName();
            eCParameterSpecConvertToSpec = EC5Util.convertToSpec(domainParametersFromGenSpec);
        } else {
            if (!(algorithmParameterSpec instanceof ECParameterSpec)) {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
            this.curveName = algorithmParameterSpec instanceof d ? ((d) algorithmParameterSpec).c() : null;
            eCParameterSpecConvertToSpec = (ECParameterSpec) algorithmParameterSpec;
        }
        this.ecParameterSpec = eCParameterSpecConvertToSpec;
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr) throws IOException {
        engineInit(bArr, "ASN.1");
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr, String str) throws IOException {
        if (!isASN1FormatString(str)) {
            throw new IOException("Unknown encoded parameters format in AlgorithmParameters object: " + str);
        }
        e eVarG = e.g(bArr);
        g.a.d.b.d curve = EC5Util.getCurve(BouncyCastleProvider.CONFIGURATION, eVarG);
        if (eVarG.j()) {
            m mVarS = m.s(eVarG.h());
            String strE = b.e(mVarS);
            this.curveName = strE;
            if (strE == null) {
                this.curveName = mVarS.r();
            }
        }
        this.ecParameterSpec = EC5Util.convertToSpec(eVarG, curve);
    }

    @Override // java.security.AlgorithmParametersSpi
    public String engineToString() {
        return "EC AlgorithmParameters ";
    }

    public boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }
}
