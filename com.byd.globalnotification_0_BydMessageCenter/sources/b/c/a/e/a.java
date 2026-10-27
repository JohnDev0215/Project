package b.c.a.e;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import b.c.a.f.w;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewDataBinding {
    public final ConstraintLayout u;
    public final LinearLayout v;
    public final RecyclerView w;
    public final ImageView x;
    public final TextView y;
    public w z;

    public a(Object obj, View view, int i, ConstraintLayout constraintLayout, LinearLayout linearLayout, RecyclerView recyclerView, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.u = constraintLayout;
        this.v = linearLayout;
        this.w = recyclerView;
        this.x = imageView;
        this.y = textView;
    }
}
