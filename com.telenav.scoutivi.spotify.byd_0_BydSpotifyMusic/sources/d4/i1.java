package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.spotify.MainViewModel;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: FragmentMainBindingW600dpH900dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class i1 extends f1 {
    private static final ViewDataBinding.i S;
    private static final SparseIntArray T;
    private final ConstraintLayout Q;
    private long R;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(7);
        S = iVar;
        iVar.a(2, new String[]{"now_playing_bar"}, new int[]{3}, new int[]{com.vivid.spotify.v.now_playing_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        T = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.view_pager, 4);
        sparseIntArray.put(com.vivid.spotify.t.now_playing_page_bg, 5);
        sparseIntArray.put(com.vivid.spotify.t.now_playing_page, 6);
    }

    public i1(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 7, S, T));
    }

    private boolean i0(o4 o4Var, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 1;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 4;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 2;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.R |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.R != 0) {
                return true;
            }
            return this.G.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.R = 128L;
        }
        this.G.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return i0((o4) obj, i8);
        }
        if (i7 == 1) {
            return k0((ObservableBoolean) obj, i8);
        }
        if (i7 == 2) {
            return j0((ObservableBoolean) obj, i8);
        }
        if (i7 != 3) {
            return false;
        }
        return l0((ObservableBoolean) obj, i8);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.G.W(oVar);
    }

    @Override // d4.f1
    public void f0(MainViewModel mainViewModel) {
        this.P = mainViewModel;
    }

    @Override // d4.f1
    public void g0(NowPlayingViewModel nowPlayingViewModel) {
        this.O = nowPlayingViewModel;
        synchronized (this) {
            this.R |= 16;
        }
        p(com.vivid.spotify.a.f5200e);
        super.S();
    }

    @Override // d4.f1
    public void h0(MainUIViewModel mainUIViewModel) {
        this.N = mainUIViewModel;
        synchronized (this) {
            this.R |= 64;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009a A[PHI: r2
  0x009a: PHI (r2v2 long) = (r2v1 long), (r2v5 long) binds: [B:48:0x0083, B:57:0x0093] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.i1.x():void");
    }

    private i1(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 4, (o4) objArr[3], (FrameLayout) objArr[6], (FrameLayout) objArr[5], null, (RelativeLayout) objArr[2], (RecyclerView) objArr[1], (ViewPager2) objArr[4]);
        this.R = -1L;
        V(this.G);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.Q = constraintLayout;
        constraintLayout.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        X(view);
        K();
    }
}
