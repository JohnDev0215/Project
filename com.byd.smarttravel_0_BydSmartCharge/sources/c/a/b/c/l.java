package c.a.b.c;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.byd.widget.BydSwitch;

/* JADX INFO: compiled from: ActivitySetinfoBydBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l extends ViewDataBinding {
    public final RelativeLayout A;
    public final BydSwitch B;
    public final BydSwitch C;
    public final TextView D;
    public final TextView E;
    public final TextView F;
    public final LinearLayout G;
    public final LinearLayout z;

    public l(Object obj, View view, int i, LinearLayout linearLayout, RelativeLayout relativeLayout, BydSwitch bydSwitch, BydSwitch bydSwitch2, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.z = linearLayout;
        this.A = relativeLayout;
        this.B = bydSwitch;
        this.C = bydSwitch2;
        this.D = textView;
        this.E = textView2;
        this.F = textView3;
        this.G = linearLayout2;
    }
}
