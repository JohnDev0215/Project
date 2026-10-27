package defpackage;

import android.view.ViewGroup;

/* JADX INFO: compiled from: CustomTabBottomBarDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class f30 implements Runnable {
    public final /* synthetic */ d30 e;

    public f30(d30 d30Var) {
        this.e = d30Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        d30 d30Var = this.e;
        ((ViewGroup) d30Var.m.getParent()).removeView(d30Var.m);
        d30Var.m = null;
    }
}
