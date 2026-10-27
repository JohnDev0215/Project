package com.spotify.connectivity.auth;

/* JADX INFO: loaded from: classes.dex */
public interface LoginController {
    void blockingLogout();

    Session getSession();

    void setAnalyticsDelegate(LoginControllerAnalyticsDelegate loginControllerAnalyticsDelegate);

    void setDelegate(LoginControllerDelegate loginControllerDelegate);

    void tryReconnectNow(boolean z);
}
