package io.sentry.util.thread;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f16928a = Thread.currentThread().getId();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f16929b = new b();

    @Override // io.sentry.util.thread.a
    public final boolean a() {
        return f16928a == Thread.currentThread().getId();
    }
}
