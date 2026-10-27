package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.DoubleGridViewModel;

/* JADX INFO: compiled from: RvDoubleGridItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y4 extends ViewDataBinding {
    public final LinearLayout G;
    public final FrameLayout H;
    public final FrameLayout I;
    public final TextView J;
    protected DoubleGridViewModel K;

    protected y4(Object obj, View view, int i7, LinearLayout linearLayout, FrameLayout frameLayout, FrameLayout frameLayout2, TextView textView) {
        super(obj, view, i7);
        this.G = linearLayout;
        this.H = frameLayout;
        this.I = frameLayout2;
        this.J = textView;
    }

    public static y4 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static y4 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (y4) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.rv_double_grid_item, viewGroup, z6, obj);
    }

    public abstract void f0(DoubleGridViewModel doubleGridViewModel);
}
