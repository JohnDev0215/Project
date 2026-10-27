package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.spotify.MainViewModel;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: FragmentMainBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f1 extends ViewDataBinding {
    public final o4 G;
    public final FrameLayout H;
    public final FrameLayout I;
    public final RecyclerView J;
    public final RelativeLayout K;
    public final RecyclerView L;
    public final ViewPager2 M;
    protected MainUIViewModel N;
    protected NowPlayingViewModel O;
    protected MainViewModel P;

    protected f1(Object obj, View view, int i7, o4 o4Var, FrameLayout frameLayout, FrameLayout frameLayout2, RecyclerView recyclerView, RelativeLayout relativeLayout, RecyclerView recyclerView2, ViewPager2 viewPager2) {
        super(obj, view, i7);
        this.G = o4Var;
        this.H = frameLayout;
        this.I = frameLayout2;
        this.J = recyclerView;
        this.K = relativeLayout;
        this.L = recyclerView2;
        this.M = viewPager2;
    }

    public static f1 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static f1 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (f1) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_main, viewGroup, z6, obj);
    }

    public abstract void f0(MainViewModel mainViewModel);

    public abstract void g0(NowPlayingViewModel nowPlayingViewModel);

    public abstract void h0(MainUIViewModel mainUIViewModel);
}
