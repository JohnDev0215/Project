package b.c.a.e;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public abstract class l extends ViewDataBinding {
    public final View A;
    public final TextView B;
    public final TextView C;
    public final TextView D;
    public final TextView E;
    public w F;
    public NotifyMsgBean G;
    public final FrameLayout u;
    public final LinearLayout v;
    public final ShapeableImageView w;
    public final ImageView x;
    public final ConstraintLayout y;
    public final ProgressBar z;

    public l(Object obj, View view, int i, FrameLayout frameLayout, LinearLayout linearLayout, ShapeableImageView shapeableImageView, ImageView imageView, ConstraintLayout constraintLayout, ProgressBar progressBar, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.u = frameLayout;
        this.v = linearLayout;
        this.w = shapeableImageView;
        this.x = imageView;
        this.y = constraintLayout;
        this.z = progressBar;
        this.A = view2;
        this.B = textView;
        this.C = textView2;
        this.D = textView3;
        this.E = textView4;
    }
}
