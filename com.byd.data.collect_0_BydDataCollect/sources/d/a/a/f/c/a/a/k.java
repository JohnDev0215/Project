package d.a.a.f.c.a.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: compiled from: AttrTin.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends l {
    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if ("color".equals(this.f4654d)) {
                Drawable drawableR = androidx.core.graphics.drawable.a.r(imageView.getDrawable());
                androidx.core.graphics.drawable.a.n(drawableR, d.a.a.f.c.a.c.c.n().j(this.f4652b));
                imageView.setImageDrawable(drawableR);
            }
        }
    }
}
