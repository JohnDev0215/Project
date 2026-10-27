package io.sentry.android.core.internal.util;

import android.content.Context;
import io.sentry.K;
import io.sentry.android.core.B;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Charset f16219g = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f16220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B f16221b;
    public final K c;
    public final String[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f16222e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runtime f16223f;

    public g(Context context, K k10, B b10) {
        Runtime runtime = Runtime.getRuntime();
        this.f16220a = context;
        h6.e.q(b10, "The BuildInfoProvider is required.");
        this.f16221b = b10;
        h6.e.q(k10, "The Logger is required.");
        this.c = k10;
        this.d = new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "/su/bin", "/system/xbin/daemonsu"};
        this.f16222e = new String[]{"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "com.koushikdutta.superuser", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.noshufou.android.su"};
        h6.e.q(runtime, "The Runtime is required.");
        this.f16223f = runtime;
    }
}
