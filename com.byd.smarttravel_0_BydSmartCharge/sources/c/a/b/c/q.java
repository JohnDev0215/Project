package c.a.b.c;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: AnimChargeViewBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q extends ViewDataBinding {
    public final ImageView A;
    public final ImageView B;
    public final TextView C;
    public final ImageView z;

    public q(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView) {
        super(obj, view, i);
        this.z = imageView;
        this.A = imageView2;
        this.B = imageView3;
        this.C = textView;
    }
}
