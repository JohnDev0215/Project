package d4;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.details.DetailChildViewModel;
import com.vivid.spotify.details.DetailViewModel;

/* JADX INFO: compiled from: FragmentPlayListBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e2 extends ViewDataBinding {
    public final BaseRecyclerView G;
    protected DetailChildViewModel H;
    protected DetailViewModel I;

    protected e2(Object obj, View view, int i7, BaseRecyclerView baseRecyclerView) {
        super(obj, view, i7);
        this.G = baseRecyclerView;
    }

    public abstract void d0(DetailViewModel detailViewModel);

    public abstract void e0(DetailChildViewModel detailChildViewModel);
}
