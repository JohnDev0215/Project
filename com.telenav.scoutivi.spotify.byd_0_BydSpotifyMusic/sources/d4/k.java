package d4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: BaseListItemViewholderBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k extends ViewDataBinding {
    public final ImageView G;
    public final TextView H;
    public final TextView I;

    protected k(Object obj, View view, int i7, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i7);
        this.G = imageView;
        this.H = textView;
        this.I = textView2;
    }
}
