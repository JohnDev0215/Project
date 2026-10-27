package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.search.SearchListViewModel;

/* JADX INFO: compiled from: FragmentSearchPlayListBindingW600dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a3 extends y2 {
    private static final ViewDataBinding.i K = null;
    private static final SparseIntArray L = null;
    private final LinearLayout I;
    private long J;

    public a3(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 2, K, L));
    }

    private boolean f0(androidx.databinding.k<MediaBrowserCompat.MediaItem> kVar, int i7) {
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
        return f0((androidx.databinding.k) obj, i8);
    }

    @Override // d4.y2
    public void e0(SearchListViewModel searchListViewModel) {
        this.H = searchListViewModel;
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
        SearchListViewModel searchListViewModel = this.H;
        long j8 = 7 & j7;
        if (j8 != 0) {
            kVar = searchListViewModel != null ? searchListViewModel.playlistItems : null;
            b0(0, kVar);
        }
        if ((j7 & 4) != 0) {
            BaseRecyclerView.F1(this.G, true);
        }
        if (j8 != 0) {
            com.vivid.spotify.search.j.b2(this.G, kVar);
        }
    }

    private a3(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (BaseRecyclerView) objArr[1]);
        this.J = -1L;
        this.G.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.I = linearLayout;
        linearLayout.setTag(null);
        X(view);
        K();
    }
}
