package org.bouncycastle.jcajce.provider.config;

import g.a.c.e.e;
import java.security.spec.DSAParameterSpec;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public interface ProviderConfiguration {
    Set getAcceptableNamedCurves();

    Map getAdditionalECParameters();

    DHParameterSpec getDHDefaultParameters(int i2);

    DSAParameterSpec getDSADefaultParameters(int i2);

    e getEcImplicitlyCa();
}
