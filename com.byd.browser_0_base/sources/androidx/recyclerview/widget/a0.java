package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a0 extends RecyclerView.o {
    public RecyclerView a;
    public final a b = new a();

    /* JADX INFO: compiled from: SnapHelper.java */
    public class a extends RecyclerView.q {
        public boolean e = false;

        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void a(int i, RecyclerView recyclerView) {
            if (i == 0 && this.e) {
                this.e = false;
                a0.this.d();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void b(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.e = true;
        }
    }

    public final void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        a aVar = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(aVar);
            this.a.setOnFlingListener(null);
        }
        this.a = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.getOnFlingListener() != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            this.a.addOnScrollListener(aVar);
            this.a.setOnFlingListener(this);
            new Scroller(this.a.getContext(), new DecelerateInterpolator());
            d();
        }
    }

    public abstract int[] b(RecyclerView.m mVar, View view);

    @SuppressLint({"UnknownNullness"})
    public abstract View c(RecyclerView.m mVar);

    public final void d() {
        RecyclerView.m layoutManager;
        View viewC;
        RecyclerView recyclerView = this.a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewC = c(layoutManager)) == null) {
            return;
        }
        int[] iArrB = b(layoutManager, viewC);
        int i = iArrB[0];
        if (i == 0 && iArrB[1] == 0) {
            return;
        }
        this.a.j0(i, iArrB[1], false);
    }
}
