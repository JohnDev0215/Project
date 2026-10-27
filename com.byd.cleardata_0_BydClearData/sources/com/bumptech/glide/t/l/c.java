package com.bumptech.glide.t.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    private static class b extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile boolean f2561a;

        b() {
            super();
        }

        @Override // com.bumptech.glide.t.l.c
        public void b(boolean z) {
            this.f2561a = z;
        }

        @Override // com.bumptech.glide.t.l.c
        public void c() {
            if (this.f2561a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    private c() {
    }

    public static c a() {
        return new b();
    }

    abstract void b(boolean z);

    public abstract void c();
}
