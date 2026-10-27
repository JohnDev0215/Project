package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.spotify.MainViewModel;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: FragmentMainBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j1 extends f1 {
    private static final ViewDataBinding.i U;
    private static final SparseIntArray V;
    private final RelativeLayout Q;
    private final ImageView R;
    private final ImageView S;
    private long T;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(10);
        U = iVar;
        iVar.a(5, new String[]{"now_playing_bar"}, new int[]{6}, new int[]{com.vivid.spotify.v.now_playing_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        V = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.view_pager, 7);
        sparseIntArray.put(com.vivid.spotify.t.now_playing_page_bg, 8);
        sparseIntArray.put(com.vivid.spotify.t.now_playing_page, 9);
    }

    public j1(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 10, U, V));
    }

    private boolean i0(o4 o4Var, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 1;
        }
        return true;
    }

    private boolean j0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 4;
        }
        return true;
    }

    private boolean k0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 2;
        }
        return true;
    }

    private boolean l0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.T |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.T != 0) {
                return true;
            }
            return this.G.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.T = 128L;
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
            this.T |= 16;
        }
        p(com.vivid.spotify.a.f5200e);
        super.S();
    }

    @Override // d4.f1
    public void h0(MainUIViewModel mainUIViewModel) {
        this.N = mainUIViewModel;
        synchronized (this) {
            this.T |= 64;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bb A[PHI: r2
  0x00bb: PHI (r2v2 long) = (r2v1 long), (r2v5 long) binds: [B:54:0x00a4, B:63:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.j1.x():void");
    }

    private j1(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 4, (o4) objArr[6], (FrameLayout) objArr[9], (FrameLayout) objArr[8], (RecyclerView) objArr[2], (RelativeLayout) objArr[5], (RecyclerView) objArr[1], (ViewPager2) objArr[7]);
        this.T = -1L;
        V(this.G);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.Q = relativeLayout;
        relativeLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[3];
        this.R = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) objArr[4];
        this.S = imageView2;
        imageView2.setTag(null);
        this.J.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        X(view);
        K();
    }
}
