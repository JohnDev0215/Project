package defpackage;

/* JADX INFO: compiled from: CallbackWithHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class ql implements Runnable {
    public final /* synthetic */ a8 e;
    public final /* synthetic */ int f;

    public ql(a8 a8Var, int i) {
        this.e = a8Var;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.Y(this.f);
    }
}
