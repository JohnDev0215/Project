package io.sentry.cache;

import io.sentry.F1;
import io.sentry.M;

/* JADX INFO: loaded from: classes.dex */
public final class e implements M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f16494a;

    public e(F1 f12) {
        this.f16494a = f12;
    }

    public final void a(String str) {
        a.a(this.f16494a, ".options-cache", str);
    }

    public final void b(Object obj, String str) {
        a.c(this.f16494a, obj, ".options-cache", str);
    }
}
