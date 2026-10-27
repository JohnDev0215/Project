package g2;

/* JADX INFO: compiled from: RequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public interface d {

    /* JADX INFO: compiled from: RequestCoordinator.java */
    public enum a {
        RUNNING(false),
        PAUSED(false),
        CLEARED(false),
        SUCCESS(true),
        FAILED(true);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f6491e;

        a(boolean z6) {
            this.f6491e = z6;
        }

        boolean a() {
            return this.f6491e;
        }
    }

    boolean a(c cVar);

    boolean b(c cVar);

    void e(c cVar);

    boolean f(c cVar);

    boolean j();

    void k(c cVar);
}
