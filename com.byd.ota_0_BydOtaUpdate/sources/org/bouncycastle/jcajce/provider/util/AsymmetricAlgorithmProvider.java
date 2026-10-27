package org.bouncycastle.jcajce.provider.util;

import g.a.a.m;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    public void addSignatureAlgorithm(ConfigurableProvider configurableProvider, String str, String str2, String str3, m mVar) {
        String str4 = str + "WITH" + str2;
        String str5 = str + "with" + str2;
        String str6 = str + "With" + str2;
        configurableProvider.addAlgorithm("Signature." + str4, str3);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str5, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + str6, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + (str + "/" + str2), str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature." + mVar, str4);
        configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + mVar, str4);
    }

    public void registerOid(ConfigurableProvider configurableProvider, m mVar, String str, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        configurableProvider.addAlgorithm("Alg.Alias.KeyFactory." + mVar, str);
        configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator." + mVar, str);
        configurableProvider.addKeyInfoConverter(mVar, asymmetricKeyInfoConverter);
    }

    public void registerOidAlgorithmParameterGenerator(ConfigurableProvider configurableProvider, m mVar, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + mVar, str);
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + mVar, str);
    }

    public void registerOidAlgorithmParameters(ConfigurableProvider configurableProvider, m mVar, String str) {
        configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters." + mVar, str);
    }
}
