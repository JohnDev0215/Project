package d4;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;

/* JADX INFO: compiled from: FragmentCategoryListBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class t0 extends q0 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M = null;
    private final ShimmerRecyclerView J;
    private long K;

    public t0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 1, L, M));
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.K != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.K = 8L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        synchronized (this) {
            j7 = this.K;
            this.K = 0L;
        }
        RecyclerView.n nVar = this.H;
        RecyclerView.g<?> gVar = this.I;
        RecyclerView.o oVar = this.G;
        long j8 = 9 & j7;
        long j9 = 10 & j7;
        long j10 = j7 & 12;
        if (j9 != 0) {
            this.J.setAdapter(gVar);
        }
        if (j10 != 0) {
            this.J.setLayoutManager(oVar);
        }
        if (j8 != 0) {
            a0.g(this.J, nVar);
        }
    }

    private t0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0);
        this.K = -1L;
        ShimmerRecyclerView shimmerRecyclerView = (ShimmerRecyclerView) objArr[0];
        this.J = shimmerRecyclerView;
        shimmerRecyclerView.setTag(null);
        X(view);
        K();
    }
}
