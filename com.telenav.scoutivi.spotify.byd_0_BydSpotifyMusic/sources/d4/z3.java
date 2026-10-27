package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.MediaCoverItemViewModel;

/* JADX INFO: compiled from: MediaCoverItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z3 extends ViewDataBinding {
    public final LinearLayout G;
    public final ImageView H;
    public final TextView I;
    public final TextView J;
    protected MediaCoverItemViewModel K;

    protected z3(Object obj, View view, int i7, LinearLayout linearLayout, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i7);
        this.G = linearLayout;
        this.H = imageView;
        this.I = textView;
        this.J = textView2;
    }

    public static z3 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static z3 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (z3) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.media_cover_item, viewGroup, z6, obj);
    }

    public MediaCoverItemViewModel d0() {
        return this.K;
    }

    public abstract void g0(MediaCoverItemViewModel mediaCoverItemViewModel);
}
