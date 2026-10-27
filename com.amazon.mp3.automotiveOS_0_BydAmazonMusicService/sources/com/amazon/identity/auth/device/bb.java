package com.amazon.identity.auth.device;

import android.content.Context;
import android.webkit.CookieSyncManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f247a = new AtomicBoolean(false);

    public static void a(Context context) {
        if (f247a.compareAndSet(false, true)) {
            CookieSyncManager.createInstance(context.getApplicationContext());
        }
        CookieSyncManager.getInstance().sync();
    }
}
