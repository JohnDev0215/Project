package d4;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: MediaTitleItemBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k4 extends j4 {
    private static final ViewDataBinding.i K = null;
    private static final SparseIntArray L = null;
    private final RelativeLayout I;
    private long J;

    public k4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 2, K, L));
    }

    private boolean h0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.J |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.J != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.J = 4L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 != 0) {
            return false;
        }
        return h0((androidx.databinding.l) obj, i8);
    }

    @Override // d4.j4
    public void g0(com.vivid.spotify.common.ui.d dVar) {
        this.H = dVar;
        synchronized (this) {
            this.J |= 2;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        synchronized (this) {
            j7 = this.J;
            this.J = 0L;
        }
        com.vivid.spotify.common.ui.d dVar = this.H;
        long j8 = j7 & 7;
        int i7 = 0;
        if (j8 != 0) {
            androidx.databinding.l<String> lVar = dVar != null ? dVar.f5231a : null;
            a0(0, lVar);
            strQ = lVar != null ? lVar.q() : null;
            boolean zIsEmpty = TextUtils.isEmpty(strQ);
            if (j8 != 0) {
                j7 |= zIsEmpty ? 16L : 8L;
            }
            if (zIsEmpty) {
                i7 = 8;
            }
        }
        if ((j7 & 7) != 0) {
            n0.e.c(this.G, strQ);
            this.G.setVisibility(i7);
        }
    }

    private k4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (TextView) objArr[1]);
        this.J = -1L;
        this.G.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.I = relativeLayout;
        relativeLayout.setTag(null);
        X(view);
        K();
    }
}
