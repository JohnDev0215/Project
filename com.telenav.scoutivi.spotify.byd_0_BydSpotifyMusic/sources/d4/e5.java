package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: TitleItemBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e5 extends d5 {
    private static final ViewDataBinding.i K = null;
    private static final SparseIntArray L = null;
    private final FrameLayout H;
    private final TextView I;
    private long J;

    public e5(androidx.databinding.f fVar, View view) {
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

    @Override // d4.d5
    public void g0(com.vivid.spotify.queue.j jVar) {
        this.G = jVar;
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
        com.vivid.spotify.queue.j jVar = this.G;
        long j8 = j7 & 7;
        String strQ = null;
        if (j8 != 0) {
            androidx.databinding.l<String> lVar = jVar != null ? jVar.f5382a : null;
            a0(0, lVar);
            if (lVar != null) {
                strQ = lVar.q();
            }
        }
        if (j8 != 0) {
            n0.e.c(this.I, strQ);
        }
    }

    private e5(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1);
        this.J = -1L;
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.H = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) objArr[1];
        this.I = textView;
        textView.setTag(null);
        X(view);
        K();
    }
}
