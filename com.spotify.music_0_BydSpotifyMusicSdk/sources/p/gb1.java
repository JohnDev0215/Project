package p;

import android.os.Handler;
import android.widget.AbsListView;

/* JADX INFO: loaded from: classes.dex */
public final class gb1 implements AbsListView.OnScrollListener {
    public final /* synthetic */ ib1 a;

    public gb1(ib1 ib1Var) {
        this.a = ib1Var;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1) {
            ib1 ib1Var = this.a;
            if (ib1Var.G.getInputMethodMode() == 2 || ib1Var.G.getContentView() == null) {
                return;
            }
            Handler handler = ib1Var.C;
            bb1 bb1Var = ib1Var.y;
            handler.removeCallbacks(bb1Var);
            bb1Var.run();
        }
    }
}
