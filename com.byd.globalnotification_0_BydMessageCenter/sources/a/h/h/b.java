package a.h.h;

/* JADX INFO: loaded from: classes.dex */
public class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i f733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f734b;

    public b(c cVar, i iVar, int i) {
        this.f733a = iVar;
        this.f734b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f733a.a(this.f734b);
    }
}
