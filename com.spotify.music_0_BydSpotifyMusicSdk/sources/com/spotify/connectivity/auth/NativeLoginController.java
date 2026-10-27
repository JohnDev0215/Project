package com.spotify.connectivity.auth;

import com.spotify.esperanto.esperanto.Transport;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLoginController implements LoginController {
    private long nThis;

    private NativeLoginController() {
    }

    @Override // com.spotify.connectivity.auth.LoginController
    public native void blockingLogout();

    public final native void destroy();

    public final native NativeSession getNativeSession();

    public final native Transport getNativeTransport();

    @Override // com.spotify.connectivity.auth.LoginController
    public native Session getSession();

    public final native void prepareForShutdown();

    @Override // com.spotify.connectivity.auth.LoginController
    public native void setAnalyticsDelegate(LoginControllerAnalyticsDelegate loginControllerAnalyticsDelegate);

    @Override // com.spotify.connectivity.auth.LoginController
    public native void setDelegate(LoginControllerDelegate loginControllerDelegate);

    public final native NativeSession stealNativeSession();

    @Override // com.spotify.connectivity.auth.LoginController
    public native void tryReconnectNow(boolean z);
}
