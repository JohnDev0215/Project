package d4;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: ListSpeedItemBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class y3 extends u3 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M = null;
    private final ConstraintLayout J;
    private long K;

    public y3(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, L, M));
    }

    private boolean h0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 2;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 1;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<Integer> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 4;
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
            this.K = 16L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((ObservableBoolean) obj, i8);
        }
        if (i7 == 1) {
            return h0((androidx.databinding.l) obj, i8);
        }
        if (i7 != 2) {
            return false;
        }
        return j0((androidx.databinding.l) obj, i8);
    }

    @Override // d4.u3
    public void g0(com.vivid.spotify.nowplaying.j jVar) {
        this.I = jVar;
        synchronized (this) {
            this.K |= 8;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009a  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.y3.x():void");
    }

    private y3(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 3, (ImageView) objArr[2], (ImageView) objArr[1]);
        this.K = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.J = constraintLayout;
        constraintLayout.setTag(null);
        X(view);
        K();
    }
}
