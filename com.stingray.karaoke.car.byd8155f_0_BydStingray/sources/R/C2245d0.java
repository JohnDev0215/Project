package r;

import android.os.Handler;
import android.widget.AbsListView;

/* JADX INFO: renamed from: r.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2245d0 implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ j0 f21474a;

    public C2245d0(j0 j0Var) {
        this.f21474a = j0Var;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i7, int i10, int i11) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i7) {
        if (i7 == 1) {
            j0 j0Var = this.f21474a;
            if (j0Var.f21505G.getInputMethodMode() == 2 || j0Var.f21505G.getContentView() == null) {
                return;
            }
            Handler handler = j0Var.f21501C;
            RunnableC2241b0 runnableC2241b0 = j0Var.f21518y;
            handler.removeCallbacks(runnableC2241b0);
            runnableC2241b0.run();
        }
    }
}
