package com.bumptech.glide.o.l;

/* JADX INFO: compiled from: StateVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: compiled from: StateVerifier.java */
    private static class b extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile boolean f3475a;

        b() {
            super();
        }

        @Override // com.bumptech.glide.o.l.c
        public void b(boolean z) {
            this.f3475a = z;
        }

        @Override // com.bumptech.glide.o.l.c
        public void c() {
            if (this.f3475a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    public static c a() {
        return new b();
    }

    abstract void b(boolean z);

    public abstract void c();

    private c() {
    }
}
