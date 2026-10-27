package l2;

/* JADX INFO: compiled from: StateVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: compiled from: StateVerifier.java */
    private static class b extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private volatile boolean f7947a;

        b() {
            super();
        }

        @Override // l2.c
        public void b(boolean z6) {
            this.f7947a = z6;
        }

        @Override // l2.c
        public void c() {
            if (this.f7947a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    private c() {
    }

    public static c a() {
        return new b();
    }

    abstract void b(boolean z6);

    public abstract void c();
}
