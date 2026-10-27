package d4;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: CustomToastBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u extends ViewDataBinding {
    public final TextView G;
    public final LinearLayout H;

    protected u(Object obj, View view, int i7, TextView textView, LinearLayout linearLayout) {
        super(obj, view, i7);
        this.G = textView;
        this.H = linearLayout;
    }
}
