package com.spotify.connectivity.connectivitysdkpolicyimpl;

import com.spotify.connectivity.NativeConnectionTypeProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConnectionTypeProvider {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeConnectionTypeProvider create(NativeConnectivityManager nativeConnectivityManager) {
            return DefaultConnectionTypeProvider.create(nativeConnectivityManager);
        }
    }

    public static final native NativeConnectionTypeProvider create(NativeConnectivityManager nativeConnectivityManager);
}
