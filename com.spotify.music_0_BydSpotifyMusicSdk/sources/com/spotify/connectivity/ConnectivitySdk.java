package com.spotify.connectivity;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivitySdk {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void setMobileDeviceInfo(MobileDeviceInfo mobileDeviceInfo) {
            ConnectivitySdk.setMobileDeviceInfo(mobileDeviceInfo);
        }
    }

    public static final native void setMobileDeviceInfo(MobileDeviceInfo mobileDeviceInfo);
}
