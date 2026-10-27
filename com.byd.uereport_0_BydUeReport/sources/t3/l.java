package t3;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f8046a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f8047b;

    public <T> T a(j jVar) {
        b bVar = this.f8047b;
        if (bVar != null) {
            return (T) bVar.c(jVar);
        }
        this.f8046a.a("notify message failed: MVC isn't init");
        return null;
    }

    public synchronized void b(b bVar) {
        this.f8047b = bVar;
    }

    public void c(i iVar) {
        this.f8046a = iVar;
    }
}
