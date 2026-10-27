package b.c.a.e;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends ViewDataBinding {
    public final View A;
    public final TextView B;
    public final TextView C;
    public w D;
    public NotifyMsgBean E;
    public final FrameLayout u;
    public final LinearLayout v;
    public final LinearLayout w;
    public final ShapeableImageView x;
    public final ImageView y;
    public final ConstraintLayout z;

    public g(Object obj, View view, int i, FrameLayout frameLayout, LinearLayout linearLayout, View view2, LinearLayout linearLayout2, ShapeableImageView shapeableImageView, ImageView imageView, ConstraintLayout constraintLayout, View view3, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.u = frameLayout;
        this.v = linearLayout;
        this.w = linearLayout2;
        this.x = shapeableImageView;
        this.y = imageView;
        this.z = constraintLayout;
        this.A = view3;
        this.B = textView;
        this.C = textView2;
    }
}
