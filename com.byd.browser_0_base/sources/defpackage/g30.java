package defpackage;

/* JADX INFO: compiled from: CustomTabBrowserControlsVisibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class g30 extends lj {
    public final p61<mj> h;
    public int i = 3;

    public g30(p61 p61Var) {
        this.h = p61Var;
        ((mj) p61Var.get()).i().p(new xr(13, this));
        t();
    }

    public final void t() {
        int i;
        int iIntValue = ((Integer) this.h.get().i().f).intValue();
        if (iIntValue == 2 || (i = this.i) == 2) {
            iIntValue = 2;
        } else if (i != 3) {
            iIntValue = i;
        }
        r(Integer.valueOf(iIntValue));
    }
}
