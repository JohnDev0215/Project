package defpackage;

/* JADX INFO: compiled from: RouterImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wc2 implements Runnable {
    public final /* synthetic */ xc2 e;

    public wc2(xc2 xc2Var) {
        this.e = xc2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.close();
    }
}
