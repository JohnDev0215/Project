package defpackage;

/* JADX INFO: compiled from: ThreadedInputConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g93 implements Runnable {
    public final /* synthetic */ f93 e;

    public g93(f93 f93Var) {
        this.e = f93Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f93 f93Var = this.e;
        f93Var.f = 0;
        f93Var.h = 0;
        f93Var.j = 0;
        f93Var.k = false;
    }
}
