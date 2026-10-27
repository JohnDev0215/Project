package b.c.a.f;

/* JADX INFO: loaded from: classes.dex */
public class v implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ w f1757a;

    public v(w wVar) {
        this.f1757a = wVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1757a.p();
        this.f1757a.i.postDelayed(this, 60000L);
    }
}
