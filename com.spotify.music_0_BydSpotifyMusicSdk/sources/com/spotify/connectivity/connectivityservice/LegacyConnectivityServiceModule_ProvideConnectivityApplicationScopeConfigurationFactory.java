package com.spotify.connectivity.connectivityservice;

import com.spotify.connectivity.ApplicationScopeConfiguration;
import p.ul0;
import p.ut;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LegacyConnectivityServiceModule_ProvideConnectivityApplicationScopeConfigurationFactory implements ul0 {
    private final xy1 cachePathProvider;
    private final xy1 clientInfoProvider;
    private final xy1 languageProvider;

    public LegacyConnectivityServiceModule_ProvideConnectivityApplicationScopeConfigurationFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.clientInfoProvider = xy1Var;
        this.cachePathProvider = xy1Var2;
        this.languageProvider = xy1Var3;
    }

    public static LegacyConnectivityServiceModule_ProvideConnectivityApplicationScopeConfigurationFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new LegacyConnectivityServiceModule_ProvideConnectivityApplicationScopeConfigurationFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static ApplicationScopeConfiguration provideConnectivityApplicationScopeConfiguration(ut utVar, String str, String str2) {
        ApplicationScopeConfiguration applicationScopeConfigurationProvideConnectivityApplicationScopeConfiguration = LegacyConnectivityServiceModule.INSTANCE.provideConnectivityApplicationScopeConfiguration(utVar, str, str2);
        vr2.b(applicationScopeConfigurationProvideConnectivityApplicationScopeConfiguration);
        return applicationScopeConfigurationProvideConnectivityApplicationScopeConfiguration;
    }

    @Override // p.xy1
    public ApplicationScopeConfiguration get() {
        return provideConnectivityApplicationScopeConfiguration((ut) this.clientInfoProvider.get(), (String) this.cachePathProvider.get(), (String) this.languageProvider.get());
    }
}
