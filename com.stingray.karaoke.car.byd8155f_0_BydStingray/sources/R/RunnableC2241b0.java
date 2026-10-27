package r;

/* JADX INFO: renamed from: r.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC2241b0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ j0 f21472m;

    public /* synthetic */ RunnableC2241b0(j0 j0Var, int i7) {
        this.l = i7;
        this.f21472m = j0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                i0 i0Var = this.f21472m.f21508n;
                if (i0Var != null) {
                    i0Var.setListSelectionHidden(true);
                    i0Var.requestLayout();
                }
                break;
            default:
                j0 j0Var = this.f21472m;
                i0 i0Var2 = j0Var.f21508n;
                if (i0Var2 != null && i0Var2.isAttachedToWindow() && j0Var.f21508n.getCount() > j0Var.f21508n.getChildCount() && j0Var.f21508n.getChildCount() <= Integer.MAX_VALUE) {
                    j0Var.f21505G.setInputMethodMode(2);
                    j0Var.c();
                    break;
                }
                break;
        }
    }
}
