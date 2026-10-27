package com.ecarx.sdk.openapi;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ECarXApiClient {

    @Deprecated
    public static final int CAUSE_NETWORK_LOST = 2;

    @Deprecated
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;

    public interface Callback {
        void onAPIReady(boolean z);
    }

    @Deprecated
    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }
}
