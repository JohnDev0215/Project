package com.spotify.connectivity.connectivityapi;

import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeConnectionTypeProvider;
import com.spotify.connectivity.NativeConnectivityPolicyProvider;
import com.spotify.connectivity.auth.LoginControllerDelegate;
import com.spotify.connectivity.auth.NativeCredentialsStorage;
import com.spotify.connectivity.auth.NativeLoginController;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import io.reactivex.rxjava3.core.Single;
import p.py0;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityApi {
    void addLoginControllerDelegate(LoginControllerDelegate loginControllerDelegate);

    NativeSession adoptNativeSession();

    void blockingLogout();

    NativeConnectionTypeProvider getNativeConnectionTypeProvider();

    NativeApplicationScope getNativeConnectivityApplicationScope();

    NativeConnectivityManager getNativeConnectivityManager();

    NativeConnectivityPolicyProvider getNativeConnectivityPolicyProvider();

    NativeCredentialsStorage getNativeCredentialsStorage();

    NativeLoginController getNativeLoginController();

    void setPreShutdownHook(py0 py0Var);

    Single<StoredCredentials> storedCredentials();
}
