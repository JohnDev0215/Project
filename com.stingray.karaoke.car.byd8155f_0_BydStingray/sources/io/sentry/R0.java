package io.sentry;

import io.sentry.android.core.C1436l;

/* JADX INFO: loaded from: classes.dex */
public final class R0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15926a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1436l f15927b;

    public /* synthetic */ R0(C1436l c1436l, int i7) {
        this.f15926a = i7;
        this.f15927b = c1436l;
    }

    public static boolean b(String str, K k10) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        k10.k(EnumC1495p1.INFO, "No cached dir path is defined in options.", new Object[0]);
        return false;
    }

    public final Q0 a(E e10, F1 f12) {
        switch (this.f15926a) {
            case 0:
                h6.e.q(e10, "Hub is required");
                h6.e.q(f12, "SentryOptions is required");
                String cacheDirPath = this.f15927b.l.getCacheDirPath();
                if (cacheDirPath == null || !b(cacheDirPath, f12.getLogger())) {
                    f12.getLogger().k(EnumC1495p1.ERROR, "No cache dir path is defined in options.", new Object[0]);
                }
                break;
            default:
                h6.e.q(e10, "Hub is required");
                h6.e.q(f12, "SentryOptions is required");
                String outboxPath = this.f15927b.l.getOutboxPath();
                if (outboxPath == null || !b(outboxPath, f12.getLogger())) {
                    f12.getLogger().k(EnumC1495p1.ERROR, "No outbox dir path is defined in options.", new Object[0]);
                }
                break;
        }
        return null;
    }
}
