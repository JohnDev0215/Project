package com.spotify.connectivity.logoutanalyticsdelegate;

import p.sk2;
import p.ul0;
import p.wi0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LogoutAnalyticsDelegate_Factory implements ul0 {
    private final xy1 eventPublisherProvider;
    private final xy1 timeKeeperProvider;

    public LogoutAnalyticsDelegate_Factory(xy1 xy1Var, xy1 xy1Var2) {
        this.eventPublisherProvider = xy1Var;
        this.timeKeeperProvider = xy1Var2;
    }

    public static LogoutAnalyticsDelegate_Factory create(xy1 xy1Var, xy1 xy1Var2) {
        return new LogoutAnalyticsDelegate_Factory(xy1Var, xy1Var2);
    }

    public static LogoutAnalyticsDelegate newInstance(wi0 wi0Var, sk2 sk2Var) {
        return new LogoutAnalyticsDelegate(wi0Var, sk2Var);
    }

    @Override // p.xy1
    public LogoutAnalyticsDelegate get() {
        return newInstance((wi0) this.eventPublisherProvider.get(), (sk2) this.timeKeeperProvider.get());
    }
}
