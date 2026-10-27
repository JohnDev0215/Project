package io.sentry.android.core;

/* JADX INFO: renamed from: io.sentry.android.core.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1442s implements io.sentry.hints.a, io.sentry.hints.k {
    public final boolean l;

    public C1442s(boolean z6) {
        this.l = z6;
    }

    @Override // io.sentry.hints.a
    public final Long b() {
        return null;
    }

    @Override // io.sentry.hints.a
    public final boolean c() {
        return true;
    }

    @Override // io.sentry.hints.a
    public final String e() {
        return this.l ? "anr_background" : "anr_foreground";
    }
}
