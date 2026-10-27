package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: RootMenuTabItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t4 extends ViewDataBinding {
    public final ImageView G;
    public final ConstraintLayout H;
    public final TextView I;
    protected com.vivid.spotify.common.ui.e J;

    protected t4(Object obj, View view, int i7, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i7);
        this.G = imageView;
        this.H = constraintLayout;
        this.I = textView;
    }

    public static t4 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static t4 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (t4) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.root_menu_tab_item, viewGroup, z6, obj);
    }

    public com.vivid.spotify.common.ui.e d0() {
        return this.J;
    }

    public abstract void g0(com.vivid.spotify.common.ui.e eVar);
}
