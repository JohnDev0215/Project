package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.lk1;

/* JADX INFO: compiled from: BasicNativePage.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class be implements jk1 {
    public final mk1 e;
    public final int f;
    public ij g;
    public jb1 h;
    public View i;
    public String j;

    public be(lk1.b bVar) {
        this.e = bVar;
        this.f = yp.b(bVar.a(), false);
    }

    public final void c(ViewGroup viewGroup) {
        this.i = viewGroup;
        this.h = new jb1(22, this);
        ij ijVar = new ij(((lk1.b) this.e).b);
        this.g = ijVar;
        ijVar.p(this.h);
        Object obj = this.g.f;
        if (obj != null) {
            Rect rect = (Rect) obj;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            int i = rect.left;
            layoutParams.setMargins(i, rect.top, i, rect.bottom);
            this.i.setLayoutParams(layoutParams);
        }
    }

    @Override // defpackage.jk1
    public void destroy() {
        ij ijVar = this.g;
        if (ijVar != null) {
            ijVar.d(this.h);
            this.g.destroy();
        }
    }

    @Override // defpackage.jk1
    public void e(String str) {
        this.j = str;
    }

    @Override // defpackage.jk1
    public String getUrl() {
        return this.j;
    }

    @Override // defpackage.jk1
    public final View getView() {
        return this.i;
    }

    @Override // defpackage.jk1
    public int r() {
        return this.f;
    }
}
