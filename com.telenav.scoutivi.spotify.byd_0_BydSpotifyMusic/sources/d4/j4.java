package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: MediaTitleItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j4 extends ViewDataBinding {
    public final TextView G;
    protected com.vivid.spotify.common.ui.d H;

    protected j4(Object obj, View view, int i7, TextView textView) {
        super(obj, view, i7);
        this.G = textView;
    }

    public static j4 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static j4 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (j4) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.media_title_item, viewGroup, z6, obj);
    }

    public com.vivid.spotify.common.ui.d d0() {
        return this.H;
    }

    public abstract void g0(com.vivid.spotify.common.ui.d dVar);
}
