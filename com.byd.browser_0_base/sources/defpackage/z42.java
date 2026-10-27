package defpackage;

import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class z42 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ c52 f;

    public /* synthetic */ z42(c52 c52Var, int i) {
        this.e = i;
        this.f = c52Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        c52 c52Var = this.f;
        switch (i) {
            case 0:
                c52Var.l.fullScroll(130);
                break;
            default:
                d52 d52Var = new d52(c52Var.getContext(), c52Var.n);
                d52Var.show();
                new WeakReference(d52Var);
                break;
        }
    }
}
