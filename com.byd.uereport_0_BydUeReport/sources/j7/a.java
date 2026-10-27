package j7;

/* JADX INFO: compiled from: AsyncPoster.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Runnable, k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j f5602d = new j();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f5603e;

    public a(c cVar) {
        this.f5603e = cVar;
    }

    @Override // j7.k
    public void a(p pVar, Object obj) {
        this.f5602d.a(i.a(pVar, obj));
        this.f5603e.d().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        i iVarB = this.f5602d.b();
        if (iVarB == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.f5603e.g(iVarB);
    }
}
