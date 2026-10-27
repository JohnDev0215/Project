package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.e;
import g.a.a.k;
import g.a.a.m;
import g.a.a.p2.n;
import g.a.a.p2.u;
import g.a.a.r;
import g.a.a.w0;
import g.a.a.w2.a;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PSSParameterSpec;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class X509SignatureUtil {
    private static final k derNull = w0.f4105c;

    private static String getDigestAlgName(m mVar) {
        String digestName = MessageDigestUtils.getDigestName(mVar);
        int iIndexOf = digestName.indexOf(45);
        if (iIndexOf <= 0 || digestName.startsWith("SHA3")) {
            return MessageDigestUtils.getDigestName(mVar);
        }
        return digestName.substring(0, iIndexOf) + digestName.substring(iIndexOf + 1);
    }

    public static String getSignatureName(a aVar) {
        e eVarJ = aVar.j();
        if (eVarJ != null && !derNull.equals(eVarJ)) {
            if (aVar.g().equals(n.n)) {
                return getDigestAlgName(u.h(eVarJ).g().g()) + "withRSAandMGF1";
            }
            if (aVar.g().equals(g.a.a.x2.m.A2)) {
                return getDigestAlgName((m) r.n(eVarJ).q(0)) + "withECDSA";
            }
        }
        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider != null) {
            String property = provider.getProperty("Alg.Alias.Signature." + aVar.g().r());
            if (property != null) {
                return property;
            }
        }
        Provider[] providers = Security.getProviders();
        for (int i2 = 0; i2 != providers.length; i2++) {
            String property2 = providers[i2].getProperty("Alg.Alias.Signature." + aVar.g().r());
            if (property2 != null) {
                return property2;
            }
        }
        return aVar.g().r();
    }

    public static void setSignatureParameters(Signature signature, e eVar) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (eVar == null || derNull.equals(eVar)) {
            return;
        }
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
        try {
            algorithmParameters.init(eVar.c().e());
            if (signature.getAlgorithm().endsWith("MGF1")) {
                try {
                    signature.setParameter(algorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (GeneralSecurityException e2) {
                    throw new SignatureException("Exception extracting parameters: " + e2.getMessage());
                }
            }
        } catch (IOException e3) {
            throw new SignatureException("IOException decoding parameters: " + e3.getMessage());
        }
    }
}
