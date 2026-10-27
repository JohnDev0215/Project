package defpackage;

/* JADX INFO: compiled from: BindingManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class se implements Runnable {
    public final /* synthetic */ re e;

    public se(re reVar) {
        this.e = reVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        re reVar = this.e;
        fa1.g("BindingManager", "Release visible connections: %d", Integer.valueOf(reVar.e.g));
        reVar.e(reVar.e.g);
    }
}
