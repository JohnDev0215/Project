package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.details.DetailChildViewModel;
import com.vivid.spotify.details.DetailViewModel;

/* JADX INFO: compiled from: FragmentPlayListBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class f2 extends e2 {
    private static final ViewDataBinding.i L = null;
    private static final SparseIntArray M = null;
    private final LinearLayout J;
    private long K;

    public f2(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 2, L, M));
    }

    private boolean f0(androidx.databinding.k<MediaBrowserCompat.MediaItem> kVar, int i7) {
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
            this.K = 8L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 != 0) {
            return false;
        }
        return f0((androidx.databinding.k) obj, i8);
    }

    @Override // d4.e2
    public void d0(DetailViewModel detailViewModel) {
        this.I = detailViewModel;
    }

    @Override // d4.e2
    public void e0(DetailChildViewModel detailChildViewModel) {
        this.H = detailChildViewModel;
        synchronized (this) {
            this.K |= 4;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        androidx.databinding.k<MediaBrowserCompat.MediaItem> kVar;
        synchronized (this) {
            j7 = this.K;
            this.K = 0L;
        }
        DetailChildViewModel detailChildViewModel = this.H;
        long j8 = 13 & j7;
        if (j8 != 0) {
            kVar = detailChildViewModel != null ? detailChildViewModel.playlistItems : null;
            b0(0, kVar);
        } else {
            kVar = null;
        }
        if ((j7 & 8) != 0) {
            a0.d(this.G, null);
        }
        if (j8 != 0) {
            com.vivid.spotify.details.e.i2(this.G, kVar);
        }
    }

    private f2(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (BaseRecyclerView) objArr[1]);
        this.K = -1L;
        this.G.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.J = linearLayout;
        linearLayout.setTag(null);
        X(view);
        K();
    }
}
