package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.rootui.GridFragment;

/* JADX INFO: compiled from: MediaListParentItemBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class f4 extends e4 {
    private static final ViewDataBinding.i M = null;
    private static final SparseIntArray N = null;
    private final RelativeLayout J;
    private final ImageView K;
    private long L;

    public f4(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 4, M, N));
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 4;
        }
        return true;
    }

    private boolean i0(androidx.databinding.k<MediaBrowserCompat.MediaItem> kVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 1;
        }
        return true;
    }

    private boolean j0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.L != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.L = 16L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((androidx.databinding.k) obj, i8);
        }
        if (i7 == 1) {
            return j0((androidx.databinding.l) obj, i8);
        }
        if (i7 != 2) {
            return false;
        }
        return h0((ObservableBoolean) obj, i8);
    }

    @Override // d4.e4
    public void g0(com.vivid.spotify.common.ui.d dVar) {
        this.I = dVar;
        synchronized (this) {
            this.L |= 8;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        int i7;
        int i8;
        androidx.databinding.m mVar;
        String str;
        String strQ;
        synchronized (this) {
            j7 = this.L;
            this.L = 0L;
        }
        com.vivid.spotify.common.ui.d dVar = this.I;
        if ((31 & j7) != 0) {
            if ((j7 & 25) != 0) {
                mVar = dVar != null ? dVar.f5232b : null;
                b0(0, mVar);
            } else {
                mVar = null;
            }
            long j8 = j7 & 26;
            if (j8 != 0) {
                androidx.databinding.l<String> lVar = dVar != null ? dVar.f5231a : null;
                a0(1, lVar);
                strQ = lVar != null ? lVar.q() : null;
                boolean zIsEmpty = TextUtils.isEmpty(strQ);
                if (j8 != 0) {
                    j7 |= zIsEmpty ? 64L : 32L;
                }
                i8 = zIsEmpty ? 8 : 0;
            } else {
                i8 = 0;
                strQ = null;
            }
            long j9 = j7 & 28;
            if (j9 != 0) {
                ObservableBoolean observableBoolean = dVar != null ? dVar.f5233c : null;
                a0(2, observableBoolean);
                boolean zQ = observableBoolean != null ? observableBoolean.q() : false;
                if (j9 != 0) {
                    j7 |= zQ ? 256L : 128L;
                }
                str = strQ;
                i7 = zQ ? 0 : 8;
            } else {
                str = strQ;
                i7 = 0;
            }
        } else {
            i7 = 0;
            i8 = 0;
            mVar = null;
            str = null;
        }
        if ((26 & j7) != 0) {
            n0.e.c(this.G, str);
            this.G.setVisibility(i8);
        }
        if ((16 & j7) != 0) {
            a0.c(this.G, false, 0);
        }
        if ((25 & j7) != 0) {
            GridFragment.l2(this.H, mVar);
        }
        if ((j7 & 28) != 0) {
            this.K.setVisibility(i7);
        }
    }

    private f4(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 3, (TextView) objArr[1], (BaseRecyclerView) objArr[3]);
        this.L = -1L;
        this.G.setTag(null);
        this.H.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.J = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[2];
        this.K = imageView;
        imageView.setTag(null);
        X(view);
        K();
    }
}
