package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class MAPWebViewEventHelper {

    @FireOsSdk
    public static final String AUTHENTICATION_EVENT = "authentication_event";

    @FireOsSdk
    public static final String AUTHENTICATION_ONLY_EVENT = "authentication_only_event";

    @FireOsSdk
    public static final String ERROR_EVENT = "error_event";

    @FireOsSdk
    public static final String ERROR_EVENT_VALUE = "error_event_value";

    @FireOsSdk
    public static final String KEY_ERRORS = "errors";

    @FireOsSdk
    public static final String KEY_EVENT_TYPE = "event_type";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f228a;

    @FireOsSdk
    public MAPWebViewEventHelper(Bundle bundle) {
        this.f228a = bundle;
    }

    @FireOsSdk
    public boolean isAuthenticationEvent() {
        Bundle bundle = this.f228a;
        if (bundle == null ? false : bundle.containsKey(KEY_EVENT_TYPE)) {
            return this.f228a.getString(KEY_EVENT_TYPE).equals(AUTHENTICATION_EVENT);
        }
        return false;
    }

    @FireOsSdk
    public boolean isErrorEvent() {
        Bundle bundle = this.f228a;
        if (bundle == null ? false : bundle.containsKey(KEY_EVENT_TYPE)) {
            return this.f228a.getString(KEY_EVENT_TYPE).equals(ERROR_EVENT);
        }
        return false;
    }
}
