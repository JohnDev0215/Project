package com.bumptech.glide.r;

/* JADX INFO: loaded from: classes.dex */
public interface d {

    public enum a {
        RUNNING(false),
        PAUSED(false),
        CLEARED(false),
        SUCCESS(true),
        FAILED(true);

        private final boolean h;

        a(boolean z) {
            this.h = z;
        }

        boolean a() {
            return this.h;
        }
    }

    boolean a(c cVar);

    boolean b();

    d c();

    void d(c cVar);

    void g(c cVar);

    boolean k(c cVar);

    boolean l(c cVar);
}
