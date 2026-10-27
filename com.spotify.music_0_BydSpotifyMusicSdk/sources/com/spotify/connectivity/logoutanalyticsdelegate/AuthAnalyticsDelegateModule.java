package com.spotify.connectivity.logoutanalyticsdelegate;

import p.sk2;
import p.v03;
import p.v6;
import p.wi0;

/* JADX INFO: loaded from: classes.dex */
public final class AuthAnalyticsDelegateModule {
    public static final AuthAnalyticsDelegateModule INSTANCE = new AuthAnalyticsDelegateModule();

    private AuthAnalyticsDelegateModule() {
    }

    public final AuthAnalyticsDelegate provideAuthAnalyticsDelegate(wi0 wi0Var, sk2 sk2Var, v6 v6Var) {
        v03.j(wi0Var, "eventPublisher");
        v03.j(sk2Var, "timeKeeper");
        v03.j(v6Var, "properties");
        throw null;
    }
}
