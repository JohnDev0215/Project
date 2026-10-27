package B4;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f641a;

    public final void a() {
        if (this.f641a) {
            throw new IllegalStateException("Already released");
        }
    }
}
