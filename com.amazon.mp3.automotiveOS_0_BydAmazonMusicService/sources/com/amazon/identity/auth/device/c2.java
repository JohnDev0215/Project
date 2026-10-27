package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Bundle f256a;
    public final /* synthetic */ RemoteCallbackWrapper b;
    public final /* synthetic */ AuthPortalUIActivity c;

    public c2(AuthPortalUIActivity authPortalUIActivity, Bundle bundle, RemoteCallbackWrapper remoteCallbackWrapper) {
        this.c = authPortalUIActivity;
        this.f256a = bundle;
        this.b = remoteCallbackWrapper;
    }

    @Override // java.lang.Runnable
    public void run() {
        Bundle bundle = this.f256a;
        if (bundle == null) {
            this.c.finish();
            return;
        }
        RemoteCallbackWrapper remoteCallbackWrapper = this.b;
        if (remoteCallbackWrapper != null) {
            remoteCallbackWrapper.onError(bundle);
        }
        AuthPortalUIActivity.a(this.c);
    }
}
