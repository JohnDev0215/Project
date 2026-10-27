package com.spotify.connectivity.auth;

/* JADX INFO: loaded from: classes.dex */
public interface LoginControllerAnalyticsDelegate {
    void onBeforeLogout();

    void onBeforeLogoutForced();

    void onBeforeLogoutRemote();

    void onLogoutDone();

    void onOfflineUserRemoved();

    void onStoredCredentialsRemoved();
}
