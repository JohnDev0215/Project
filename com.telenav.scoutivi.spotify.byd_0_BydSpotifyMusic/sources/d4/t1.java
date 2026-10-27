package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;

/* JADX INFO: compiled from: FragmentMainRouteBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class t1 extends p1 {
    private static final ViewDataBinding.i J = null;
    private static final SparseIntArray K;
    private final LinearLayout H;
    private long I;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        K = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.nav_host_fragment, 1);
    }

    public t1(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 2, J, K));
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.I != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.I = 1L;
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
            this.I = 0L;
        }
    }

    private t1(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0, (FragmentContainerView) objArr[1]);
        this.I = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.H = linearLayout;
        linearLayout.setTag(null);
        X(view);
        K();
    }
}
