package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.rootui.GridViewModel;

/* JADX INFO: compiled from: FragmentMainGridBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k1 extends ViewDataBinding {
    public final LinearLayout G;
    public final ImageView H;
    public final ShimmerRecyclerView I;
    public final ImageView J;
    public final View K;
    protected GridViewModel L;

    protected k1(Object obj, View view, int i7, LinearLayout linearLayout, ImageView imageView, ShimmerRecyclerView shimmerRecyclerView, ImageView imageView2, View view2) {
        super(obj, view, i7);
        this.G = linearLayout;
        this.H = imageView;
        this.I = shimmerRecyclerView;
        this.J = imageView2;
        this.K = view2;
    }

    public static k1 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static k1 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (k1) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_main_grid, viewGroup, z6, obj);
    }

    public abstract void f0(GridViewModel gridViewModel);
}
