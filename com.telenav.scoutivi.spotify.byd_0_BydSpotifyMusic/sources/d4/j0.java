package d4;

import android.support.v4.media.MediaBrowserCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.spotify.common.ui.BaseTabLayout;
import com.vivid.spotify.rootui.category.CategoryFragment;
import com.vivid.spotify.rootui.category.CategoryViewModel;
import java.util.List;

/* JADX INFO: compiled from: FragmentCategoryBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j0 extends g0 {
    private static final ViewDataBinding.i R = null;
    private static final SparseIntArray S;
    private long Q;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        S = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.header, 3);
        sparseIntArray.put(com.vivid.spotify.t.topBg, 4);
        sparseIntArray.put(com.vivid.spotify.t.iv_logo, 5);
        sparseIntArray.put(com.vivid.spotify.t.tv_name, 6);
        sparseIntArray.put(com.vivid.spotify.t.iv_right_top, 7);
    }

    public j0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 8, R, S));
    }

    private boolean h0(LiveData<List<MediaBrowserCompat.MediaItem>> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 2;
        }
        return true;
    }

    private boolean i0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.Q |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.Q != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.Q = 16L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((ObservableBoolean) obj, i8);
        }
        if (i7 != 1) {
            return false;
        }
        return h0((LiveData) obj, i8);
    }

    @Override // d4.g0
    public void f0(FragmentStateAdapter fragmentStateAdapter) {
        this.P = fragmentStateAdapter;
        synchronized (this) {
            this.Q |= 4;
        }
        p(com.vivid.spotify.a.f5198c);
        super.S();
    }

    @Override // d4.g0
    public void g0(CategoryViewModel categoryViewModel) {
        this.O = categoryViewModel;
        synchronized (this) {
            this.Q |= 8;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void x() {
        long j7;
        synchronized (this) {
            j7 = this.Q;
            this.Q = 0L;
        }
        FragmentStateAdapter fragmentStateAdapter = this.P;
        CategoryViewModel categoryViewModel = this.O;
        long j8 = 20 & j7;
        boolean zQ = false;
        List<MediaBrowserCompat.MediaItem> listE = null;
        if ((27 & j7) != 0) {
            if ((j7 & 25) != 0) {
                ObservableBoolean observableBoolean = categoryViewModel != null ? categoryViewModel.scrollable : null;
                a0(0, observableBoolean);
                if (observableBoolean != null) {
                    zQ = observableBoolean.q();
                }
            }
            if ((j7 & 26) != 0) {
                LiveData<?> liveDataObserverCategory = categoryViewModel != null ? categoryViewModel.observerCategory() : null;
                Z(1, liveDataObserverCategory);
                if (liveDataObserverCategory != null) {
                    listE = liveDataObserverCategory.e();
                }
            }
        }
        if ((25 & j7) != 0) {
            this.G.setUserInputEnabled(zQ);
        }
        if (j8 != 0) {
            a0.j(this.G, fragmentStateAdapter);
        }
        if ((j7 & 26) != 0) {
            CategoryFragment.i2(this.L, listE, this.G);
        }
    }

    private j0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 2, (ViewPager2) objArr[1], (View) objArr[3], (ImageView) objArr[5], (ImageView) objArr[7], (MotionLayout) objArr[0], (BaseTabLayout) objArr[2], (ImageView) objArr[4], (TextView) objArr[6]);
        this.Q = -1L;
        this.G.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        X(view);
        K();
    }
}
