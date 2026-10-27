package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;

/* JADX INFO: compiled from: MediaListParentItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e4 extends ViewDataBinding {
    public final TextView G;
    public final BaseRecyclerView H;
    protected com.vivid.spotify.common.ui.d I;

    protected e4(Object obj, View view, int i7, TextView textView, BaseRecyclerView baseRecyclerView) {
        super(obj, view, i7);
        this.G = textView;
        this.H = baseRecyclerView;
    }

    public static e4 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static e4 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (e4) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.media_list_parent_item, viewGroup, z6, obj);
    }

    public com.vivid.spotify.common.ui.d d0() {
        return this.I;
    }

    public abstract void g0(com.vivid.spotify.common.ui.d dVar);
}
