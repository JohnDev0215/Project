package d.a.a.f.c.a.a;

import android.view.View;

/* JADX INFO: compiled from: AttrBackground.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends l {
    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if ("color".equals(this.f4654d)) {
            view.setBackgroundColor(d.a.a.f.c.a.c.c.n().j(this.f4652b));
        } else if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
            view.setBackground("drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b));
        }
    }
}
