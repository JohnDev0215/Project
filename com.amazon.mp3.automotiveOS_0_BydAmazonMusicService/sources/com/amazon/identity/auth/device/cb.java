package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class cb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f270a = TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
    public static final /* synthetic */ int b = 0;

    public static void a(Bundle bundle, String str, String str2) {
        Bundle bundle2 = bundle.getBundle(AuthenticationMethod.KEY_AUTH_HEADERS);
        if (bundle2 == null) {
            bundle2 = new Bundle();
            bundle.putBundle(AuthenticationMethod.KEY_AUTH_HEADERS, bundle2);
        }
        bundle2.putString(str, str2);
    }
}
