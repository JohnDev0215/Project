package d4;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: RootMenuTabItemBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class u4 extends t4 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M = null;
    private long K;

    public u4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, L, M));
    }

    private boolean h0(androidx.databinding.l<Drawable> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 8;
        }
        return true;
    }

    private boolean i0(androidx.databinding.l<Uri> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 1;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 16;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 2;
        }
        return true;
    }

    private boolean l0(androidx.databinding.l<String> lVar, int i7) {
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
            this.K = 64L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 1) {
            return k0((ObservableBoolean) obj, i8);
        }
        if (i7 == 2) {
            return l0((androidx.databinding.l) obj, i8);
        }
        if (i7 == 3) {
            return h0((androidx.databinding.l) obj, i8);
        }
        if (i7 != 4) {
            return false;
        }
        return j0((ObservableBoolean) obj, i8);
    }

    @Override // d4.t4
    public void g0(com.vivid.spotify.common.ui.e eVar) {
        this.J = eVar;
        synchronized (this) {
            this.K |= 32;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e6  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.u4.x():void");
    }

    private u4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 5, (ImageView) objArr[1], (ConstraintLayout) objArr[0], (TextView) objArr[2]);
        this.K = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        this.I.setTag(null);
        X(view);
        K();
    }
}
