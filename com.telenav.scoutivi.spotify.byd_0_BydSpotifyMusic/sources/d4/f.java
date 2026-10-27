package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.BaseListItemViewModel;

/* JADX INFO: compiled from: BaseListItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f extends ViewDataBinding {
    public final ImageView G;
    public final ImageView H;
    public final ImageView I;
    public final FrameLayout J;
    public final TextView K;
    public final TextView L;
    protected BaseListItemViewModel M;

    protected f(Object obj, View view, int i7, ImageView imageView, ImageView imageView2, ImageView imageView3, FrameLayout frameLayout, TextView textView, TextView textView2) {
        super(obj, view, i7);
        this.G = imageView;
        this.H = imageView2;
        this.I = imageView3;
        this.J = frameLayout;
        this.K = textView;
        this.L = textView2;
    }

    public static f e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static f f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (f) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.base_list_item, viewGroup, z6, obj);
    }

    public BaseListItemViewModel d0() {
        return this.M;
    }

    public abstract void g0(BaseListItemViewModel baseListItemViewModel);
}
