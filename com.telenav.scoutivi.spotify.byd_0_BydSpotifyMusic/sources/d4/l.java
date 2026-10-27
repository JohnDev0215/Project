package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: BaseListItemViewholderBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends k {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M;
    private final ConstraintLayout J;
    private long K;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        M = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.img_cover, 1);
        sparseIntArray.put(com.vivid.spotify.t.title, 2);
        sparseIntArray.put(com.vivid.spotify.t.subtitle, 3);
    }

    public l(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 4, L, M));
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
            this.K = 1L;
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
            this.K = 0L;
        }
    }

    private l(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 0, (ImageView) objArr[1], (TextView) objArr[3], (TextView) objArr[2]);
        this.K = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.J = constraintLayout;
        constraintLayout.setTag(null);
        X(view);
        K();
    }
}
