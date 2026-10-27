package f.a.a;

/* JADX INFO: loaded from: classes.dex */
public class a implements Runnable, l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f2534a = new k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f2535b;

    public a(c cVar) {
        this.f2535b = cVar;
    }

    @Override // f.a.a.l
    public void a(q qVar, Object obj) {
        this.f2534a.a(j.a(qVar, obj));
        this.f2535b.j.execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        j jVarB = this.f2534a.b();
        if (jVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f2535b.c(jVarB);
    }
}
