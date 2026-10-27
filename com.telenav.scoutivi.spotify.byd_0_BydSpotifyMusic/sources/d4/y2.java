package d4;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.search.SearchListViewModel;

/* JADX INFO: compiled from: FragmentSearchPlayListBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y2 extends ViewDataBinding {
    public final BaseRecyclerView G;
    protected SearchListViewModel H;

    protected y2(Object obj, View view, int i7, BaseRecyclerView baseRecyclerView) {
        super(obj, view, i7);
        this.G = baseRecyclerView;
    }

    public SearchListViewModel d0() {
        return this.H;
    }

    public abstract void e0(SearchListViewModel searchListViewModel);
}
