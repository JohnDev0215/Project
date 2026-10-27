package d4;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.shimmer.ShimmerLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;

/* JADX INFO: compiled from: FragmentSearchShimmerBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class g3 extends d3 {
    private static final ViewDataBinding.i P = null;
    private static final SparseIntArray Q;
    private final ConstraintLayout N;
    private long O;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        Q = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_1, 1);
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_2, 2);
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_3, 3);
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_4, 4);
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_5, 5);
        sparseIntArray.put(com.vivid.spotify.t.viewholder_tab_item_6, 6);
        sparseIntArray.put(com.vivid.spotify.t.list_viewholder, 7);
    }

    public g3(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 8, P, Q));
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.O != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.O = 1L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        synchronized (this) {
            this.O = 0L;
        }
    }

    private g3(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0, (ShimmerRecyclerView) objArr[7], (ShimmerLayout) objArr[1], (ShimmerLayout) objArr[2], (ShimmerLayout) objArr[3], (ShimmerLayout) objArr[4], (ShimmerLayout) objArr[5], (ShimmerLayout) objArr[6]);
        this.O = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.N = constraintLayout;
        constraintLayout.setTag(null);
        X(view);
        K();
    }
}
