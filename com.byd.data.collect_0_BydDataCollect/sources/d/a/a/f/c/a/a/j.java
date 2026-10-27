package d.a.a.f.c.a.a;

import android.view.View;
import android.widget.TextView;
import com.bydux.api.common.views.ctrl.BydButton;

/* JADX INFO: compiled from: AttrTextColor.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends l {
    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if ((view instanceof TextView) || (view instanceof BydButton)) {
            TextView textView = (TextView) view;
            if ("color".equals(this.f4654d)) {
                textView.setTextColor(d.a.a.f.c.a.c.c.n().h(this.f4652b));
            }
        }
    }
}
