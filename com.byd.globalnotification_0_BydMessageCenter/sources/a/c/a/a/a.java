package a.c.a.a;

/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile a f417c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f419b;

    public a() {
        b bVar = new b();
        this.f419b = bVar;
        this.f418a = bVar;
    }

    public static a c() {
        if (f417c != null) {
            return f417c;
        }
        synchronized (a.class) {
            if (f417c == null) {
                f417c = new a();
            }
        }
        return f417c;
    }

    @Override // a.c.a.a.c
    public boolean a() {
        return this.f418a.a();
    }

    @Override // a.c.a.a.c
    public void b(Runnable runnable) {
        this.f418a.b(runnable);
    }
}
