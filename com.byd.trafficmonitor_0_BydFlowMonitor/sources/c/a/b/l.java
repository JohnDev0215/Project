package c.a.b;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f3474a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3475b;

    public <T> T a(j jVar) {
        b bVar = this.f3475b;
        if (bVar != null) {
            return (T) bVar.c(jVar);
        }
        this.f3474a.a("notify message failed: MVC isn't init");
        return null;
    }

    public synchronized void b(b bVar) {
        this.f3475b = bVar;
    }

    public void c(i iVar) {
        this.f3474a = iVar;
    }
}
