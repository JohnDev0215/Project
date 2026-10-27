package io.sentry.util;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f16917a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f16918b;

    public d(c cVar) {
        this.f16918b = cVar;
    }

    public final Object a() {
        if (this.f16917a == null) {
            synchronized (this) {
                try {
                    if (this.f16917a == null) {
                        this.f16917a = this.f16918b.g();
                    }
                } finally {
                }
            }
        }
        return this.f16917a;
    }
}
