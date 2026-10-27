package com.spotify.connectivity.connectivitysdkpolicyimpl;

import com.spotify.connectivity.NativeConnectivityPolicyProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConnectivityPolicyProvider {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeConnectivityPolicyProvider create(NativeConnectivityManager nativeConnectivityManager) {
            return DefaultConnectivityPolicyProvider.create(nativeConnectivityManager);
        }
    }

    public static final native NativeConnectivityPolicyProvider create(NativeConnectivityManager nativeConnectivityManager);
}
