package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: CustomToastBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class v extends u {
    private static final ViewDataBinding.i J = null;
    private static final SparseIntArray K;
    private long I;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        K = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.content, 1);
    }

    public v(androidx.databinding.f fVar, View view) {
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

    private v(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0, (TextView) objArr[1], (LinearLayout) objArr[0]);
        this.I = -1L;
        this.H.setTag(null);
        X(view);
        K();
    }
}
