package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.rootui.category.GridViewModel;

/* JADX INFO: compiled from: FragmentCategoryGridBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 extends ViewDataBinding {
    public final ShimmerRecyclerView G;
    protected GridViewModel H;
    protected RecyclerView.n I;

    protected l0(Object obj, View view, int i7, ShimmerRecyclerView shimmerRecyclerView) {
        super(obj, view, i7);
        this.G = shimmerRecyclerView;
    }

    public static l0 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static l0 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (l0) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_category_grid, viewGroup, z6, obj);
    }

    public abstract void f0(RecyclerView.n nVar);

    public abstract void g0(GridViewModel gridViewModel);
}
