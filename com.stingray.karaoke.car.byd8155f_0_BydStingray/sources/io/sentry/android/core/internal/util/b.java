package io.sentry.android.core.internal.util;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class b implements io.sentry.util.thread.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f16209a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f16210b = new b();

    @Override // io.sentry.util.thread.a
    public boolean a() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }
}
