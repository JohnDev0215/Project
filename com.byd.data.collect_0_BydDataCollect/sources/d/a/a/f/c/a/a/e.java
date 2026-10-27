package d.a.a.f.c.a.a;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

/* JADX INFO: compiled from: AttrDivider.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends l {
    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if ("color".equals(this.f4654d)) {
                listView.setDivider(new ColorDrawable(d.a.a.f.c.a.c.c.n().j(this.f4652b)));
            } else if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                listView.setDivider("drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b));
            }
        }
    }
}
