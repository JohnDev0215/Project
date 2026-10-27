package b.a.c;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f244a = new k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f245b;

    public <T> T a(l lVar) {
        b bVar = this.f245b;
        if (bVar != null) {
            return (T) bVar.c(lVar);
        }
        this.f244a.a("notify message failed: MVC isn't init");
        return null;
    }

    public synchronized void b(b bVar) {
        this.f245b = bVar;
    }

    public void c(k kVar) {
        this.f244a = kVar;
    }
}
