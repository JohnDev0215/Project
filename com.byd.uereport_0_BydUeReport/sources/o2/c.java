package o2;

/* JADX INFO: compiled from: ServiceFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public o2.b f6868a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o2.a f6869b;

    /* JADX INFO: compiled from: ServiceFactory.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6870a = new c();
    }

    public static c b() {
        return b.f6870a;
    }

    public o2.a a() {
        return this.f6869b;
    }

    public o2.b c() {
        return this.f6868a;
    }

    public void d(o2.a aVar) {
        this.f6869b = aVar;
        x3.a.a("ServiceFactory", "setDateService:" + aVar);
    }

    public void e(o2.b bVar) {
        this.f6868a = bVar;
    }

    public c() {
    }
}
