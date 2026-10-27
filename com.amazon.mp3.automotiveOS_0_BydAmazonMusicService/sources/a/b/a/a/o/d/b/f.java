package a.b.a.a.o.d.b;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f115a;
    public final /* synthetic */ a.b.a.a.o.e.c b;

    public f(a aVar, a.b.a.a.o.e.c cVar) {
        this.f115a = aVar;
        this.b = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f115a.v = this.b;
    }
}
