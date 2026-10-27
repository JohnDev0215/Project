package com.spotify.connectivity.logoutanalyticsdelegate;

import p.sk2;
import p.u4;
import p.ul0;
import p.v6;
import p.vr2;
import p.wi0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class AuthAnalyticsDelegateModule_ProvideAuthAnalyticsDelegateFactory implements ul0 {
    private final xy1 eventPublisherProvider;
    private final xy1 propertiesProvider;
    private final xy1 timeKeeperProvider;

    public AuthAnalyticsDelegateModule_ProvideAuthAnalyticsDelegateFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.eventPublisherProvider = xy1Var;
        this.timeKeeperProvider = xy1Var2;
        this.propertiesProvider = xy1Var3;
    }

    public static AuthAnalyticsDelegateModule_ProvideAuthAnalyticsDelegateFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new AuthAnalyticsDelegateModule_ProvideAuthAnalyticsDelegateFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static AuthAnalyticsDelegate provideAuthAnalyticsDelegate(wi0 wi0Var, sk2 sk2Var, v6 v6Var) {
        AuthAnalyticsDelegate authAnalyticsDelegateProvideAuthAnalyticsDelegate = AuthAnalyticsDelegateModule.INSTANCE.provideAuthAnalyticsDelegate(wi0Var, sk2Var, v6Var);
        vr2.b(authAnalyticsDelegateProvideAuthAnalyticsDelegate);
        return authAnalyticsDelegateProvideAuthAnalyticsDelegate;
    }

    @Override // p.xy1
    public AuthAnalyticsDelegate get() {
        wi0 wi0Var = (wi0) this.eventPublisherProvider.get();
        sk2 sk2Var = (sk2) this.timeKeeperProvider.get();
        u4.z(this.propertiesProvider.get());
        return provideAuthAnalyticsDelegate(wi0Var, sk2Var, null);
    }
}
