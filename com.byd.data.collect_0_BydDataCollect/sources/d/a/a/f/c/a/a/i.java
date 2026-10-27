package d.a.a.f.c.a.a;

import android.view.View;
import android.widget.ImageView;

/* JADX INFO: compiled from: AttrSrc.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends l {
    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                imageView.setImageDrawable("drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b));
            }
        }
    }
}
