package defpackage;

/* JADX INFO: compiled from: ThreadedInputConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i93 implements Runnable {
    public final /* synthetic */ CharSequence e;
    public final /* synthetic */ int f;
    public final /* synthetic */ boolean g = false;
    public final /* synthetic */ f93 h;

    public i93(f93 f93Var, CharSequence charSequence, int i) {
        this.h = f93Var;
        this.e = charSequence;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f93 f93Var = this.h;
        int i = this.g ? f93Var.h | Integer.MIN_VALUE : 0;
        f93Var.h = 0;
        f93Var.d.l(this.e, this.f, false, i);
    }
}
