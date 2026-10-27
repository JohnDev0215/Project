package c.a.b.c;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: ActivityChargeMainBydBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d extends ViewDataBinding {
    public final TextView A;
    public final TextView B;
    public final TextView C;
    public final FrameLayout z;

    public d(Object obj, View view, int i, FrameLayout frameLayout, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.z = frameLayout;
        this.A = textView;
        this.B = textView2;
        this.C = textView3;
    }
}
