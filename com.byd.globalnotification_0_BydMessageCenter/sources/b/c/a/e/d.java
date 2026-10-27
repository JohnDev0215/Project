package b.c.a.e;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.notification.NotifyMsgBean;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends ViewDataBinding {
    public final LinearLayout u;
    public final ImageView v;
    public w w;
    public NotifyMsgBean x;

    public d(Object obj, View view, int i, View view2, LinearLayout linearLayout, View view3, ImageView imageView, View view4, View view5) {
        super(obj, view, i);
        this.u = linearLayout;
        this.v = imageView;
    }
}
