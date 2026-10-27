package defpackage;

import J.N;
import android.graphics.drawable.Drawable;
import android.view.View;
import defpackage.ak;
import defpackage.ck;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: BaseButtonDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ad implements ck, View.OnClickListener {
    public final bk e;
    public final nw2<Tab> f;
    public final b<ck.a> g = new b<>();
    public final bi1 h;
    public final zc i;
    public boolean j;

    public ad(nw2 nw2Var, bi1 bi1Var, Drawable drawable, String str, int i, int i2) {
        this.f = nw2Var;
        this.h = bi1Var;
        if (bi1Var != null) {
            zc zcVar = new zc(this);
            this.i = zcVar;
            bi1Var.h.a(zcVar);
        }
        this.e = new bk(drawable, this, str, i, true, null, i2);
    }

    public abstract hw0 a(Tab tab);

    @Override // defpackage.ck
    public final void b(ck.a aVar) {
        this.g.c(aVar);
    }

    @Override // defpackage.ck
    public final bk c(Tab tab) {
        boolean zG = g(tab);
        bk bkVar = this.e;
        bkVar.a = zG;
        if (bkVar.c.f == null && tab != null && a8.P() && n3.H0()) {
            int i = bkVar.c.g;
            boolean zM6bsIDpc = false;
            if (i == 6 || i == 7) {
                String strX = n3.X(i);
                sk skVar = bq.a;
                zM6bsIDpc = N.M6bsIDpc(strX, "action_chip", false);
            }
            if (!zM6bsIDpc) {
                hw0 hw0VarA = a(tab);
                ak.a aVar = bkVar.c;
                bkVar.c = new ak.a(aVar.a, aVar.b, aVar.c, aVar.d, aVar.e, hw0VarA, aVar.g, aVar.i);
            }
        }
        return bkVar;
    }

    @Override // defpackage.ck
    public final void d(ck.a aVar) {
        this.g.a(aVar);
    }

    @Override // defpackage.ck
    public final void destroy() {
        this.g.clear();
        bi1 bi1Var = this.h;
        if (bi1Var != null) {
            bi1Var.h.c(this.i);
        }
    }

    public final void e(boolean z) {
        Iterator<ck.a> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ck.a) aVar.next()).a(z);
            }
        }
    }

    public boolean g(Tab tab) {
        if (tab == null) {
            return false;
        }
        return !tab.isIncognito() || this.j;
    }
}
