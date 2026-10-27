package g.a.c.d;

import g.a.b.j;
import g.a.b.j0.i;
import g.a.b.j0.r;
import g.a.c.e.e;
import java.security.Permission;
import java.security.spec.DSAParameterSpec;
import java.security.spec.ECParameterSpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class a implements ProviderConfiguration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Permission f5225a = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.THREAD_LOCAL_EC_IMPLICITLY_CA);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Permission f5226b = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.EC_IMPLICITLY_CA);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Permission f5227c = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.THREAD_LOCAL_DH_DEFAULT_PARAMS);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Permission f5228d = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.DH_DEFAULT_PARAMS);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Permission f5229e = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.ACCEPTABLE_EC_CURVES);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Permission f5230f = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.ADDITIONAL_EC_PARAMETERS);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile e f5233i;
    public volatile Object j;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ThreadLocal f5231g = new ThreadLocal();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ThreadLocal f5232h = new ThreadLocal();
    public volatile Set k = new HashSet();
    public volatile Map l = new HashMap();

    public void a(String str, Object obj) {
        ThreadLocal threadLocal;
        SecurityManager securityManager = System.getSecurityManager();
        if (str.equals(ConfigurableProvider.THREAD_LOCAL_EC_IMPLICITLY_CA)) {
            if (securityManager != null) {
                securityManager.checkPermission(f5225a);
            }
            e eVarConvertSpec = ((obj instanceof e) || obj == null) ? (e) obj : EC5Util.convertSpec((ECParameterSpec) obj, false);
            if (eVarConvertSpec != null) {
                this.f5231g.set(eVarConvertSpec);
                return;
            }
            threadLocal = this.f5231g;
        } else {
            if (str.equals(ConfigurableProvider.EC_IMPLICITLY_CA)) {
                if (securityManager != null) {
                    securityManager.checkPermission(f5226b);
                }
                if ((obj instanceof e) || obj == null) {
                    this.f5233i = (e) obj;
                    return;
                } else {
                    this.f5233i = EC5Util.convertSpec((ECParameterSpec) obj, false);
                    return;
                }
            }
            if (!str.equals(ConfigurableProvider.THREAD_LOCAL_DH_DEFAULT_PARAMS)) {
                if (str.equals(ConfigurableProvider.DH_DEFAULT_PARAMS)) {
                    if (securityManager != null) {
                        securityManager.checkPermission(f5228d);
                    }
                    if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                        throw new IllegalArgumentException("not a valid DHParameterSpec or DHParameterSpec[]");
                    }
                    this.j = obj;
                    return;
                }
                if (str.equals(ConfigurableProvider.ACCEPTABLE_EC_CURVES)) {
                    if (securityManager != null) {
                        securityManager.checkPermission(f5229e);
                    }
                    this.k = (Set) obj;
                    return;
                } else {
                    if (str.equals(ConfigurableProvider.ADDITIONAL_EC_PARAMETERS)) {
                        if (securityManager != null) {
                            securityManager.checkPermission(f5230f);
                        }
                        this.l = (Map) obj;
                        return;
                    }
                    return;
                }
            }
            if (securityManager != null) {
                securityManager.checkPermission(f5227c);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec");
            }
            threadLocal = this.f5232h;
            if (obj != null) {
                threadLocal.set(obj);
                return;
            }
        }
        threadLocal.remove();
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public Set getAcceptableNamedCurves() {
        return Collections.unmodifiableSet(this.k);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public Map getAdditionalECParameters() {
        return Collections.unmodifiableMap(this.l);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public DHParameterSpec getDHDefaultParameters(int i2) {
        Object obj = this.f5232h.get();
        if (obj == null) {
            obj = this.j;
        }
        if (obj instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) obj;
            if (dHParameterSpec.getP().bitLength() == i2) {
                return dHParameterSpec;
            }
        } else if (obj instanceof DHParameterSpec[]) {
            DHParameterSpec[] dHParameterSpecArr = (DHParameterSpec[]) obj;
            for (int i3 = 0; i3 != dHParameterSpecArr.length; i3++) {
                if (dHParameterSpecArr[i3].getP().bitLength() == i2) {
                    return dHParameterSpecArr[i3];
                }
            }
        }
        i iVar = (i) j.c(j.a.f5003b, i2);
        if (iVar != null) {
            return new DHDomainParameterSpec(iVar);
        }
        return null;
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public DSAParameterSpec getDSADefaultParameters(int i2) {
        r rVar = (r) j.c(j.a.f5004c, i2);
        if (rVar != null) {
            return new DSAParameterSpec(rVar.b(), rVar.c(), rVar.a());
        }
        return null;
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public e getEcImplicitlyCa() {
        e eVar = (e) this.f5231g.get();
        return eVar != null ? eVar : this.f5233i;
    }
}
