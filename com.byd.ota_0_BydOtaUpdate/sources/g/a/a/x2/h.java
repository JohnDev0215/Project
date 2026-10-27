package g.a.a.x2;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f4188a;

    public abstract g a();

    public synchronized g b() {
        if (this.f4188a == null) {
            this.f4188a = a();
        }
        return this.f4188a;
    }
}
