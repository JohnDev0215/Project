package d4;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: FragmentWelcomeBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j3 extends i3 {
    private static final ViewDataBinding.i I = null;
    private static final SparseIntArray J = null;
    private final ConstraintLayout G;
    private long H;

    public j3(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 1, I, J));
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.H != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.H = 1L;
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
            this.H = 0L;
        }
    }

    private j3(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0);
        this.H = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.G = constraintLayout;
        constraintLayout.setTag(null);
        X(view);
        K();
    }
}
