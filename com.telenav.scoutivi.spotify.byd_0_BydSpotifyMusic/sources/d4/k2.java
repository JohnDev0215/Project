package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.queue.QueueFragment;
import com.vivid.spotify.queue.QueueViewModel;

/* JADX INFO: compiled from: FragmentQueueBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k2 extends j2 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M;
    private final ConstraintLayout J;
    private long K;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        M = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.btn_back, 2);
    }

    public k2(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, L, M));
    }

    private boolean e0(androidx.databinding.k<com.vivid.spotify.queue.k> kVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 1;
        }
        return true;
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
            this.K = 4L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 != 0) {
            return false;
        }
        return e0((androidx.databinding.k) obj, i8);
    }

    @Override // d4.j2
    public void d0(QueueViewModel queueViewModel) {
        this.I = queueViewModel;
        synchronized (this) {
            this.K |= 2;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        androidx.databinding.k<com.vivid.spotify.queue.k> kVar;
        synchronized (this) {
            j7 = this.K;
            this.K = 0L;
        }
        QueueViewModel queueViewModel = this.I;
        long j8 = 7 & j7;
        if (j8 != 0) {
            kVar = queueViewModel != null ? queueViewModel.queueItems : null;
            b0(0, kVar);
        } else {
            kVar = null;
        }
        if ((j7 & 4) != 0) {
            a0.d(this.H, null);
        }
        if (j8 != 0) {
            QueueFragment.a2(this.H, kVar);
        }
    }

    private k2(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (FrameLayout) objArr[2], (ShimmerRecyclerView) objArr[1]);
        this.K = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.J = constraintLayout;
        constraintLayout.setTag(null);
        this.H.setTag(null);
        X(view);
        K();
    }
}
