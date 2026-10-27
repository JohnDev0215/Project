package defpackage;

import android.content.res.Resources;
import defpackage.ag3;
import defpackage.wp2;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: DisclosureInfobar.java */
/* JADX INFO: loaded from: classes.dex */
public class pa0 implements k.a<v62>, kr2 {
    public final Resources e;
    public final p61<wp2> f;
    public final ag3 g;
    public final a h = new a();

    /* JADX INFO: compiled from: DisclosureInfobar.java */
    public class a implements wp2.b {
        public a() {
        }

        @Override // wp2.b
        public final void j(Object obj) {
            ((ag3.a) pa0.this.g.j(ag3.f)).a();
        }
    }

    public pa0(Resources resources, p61<wp2> p61Var, ag3 ag3Var, q2 q2Var) {
        this.e = resources;
        this.f = p61Var;
        this.g = ag3Var;
        ag3Var.a(this);
        q2Var.f(this);
    }

    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k<v62> kVar, v62 v62Var) {
        v62 v62Var2 = v62Var;
        PropertyModel.p pVar = ag3.c;
        if (v62Var2 != pVar) {
            return;
        }
        int iH = this.g.h(pVar);
        if (iH == 0) {
            this.f.get().a(this.h);
        } else {
            if (iH != 1) {
                return;
            }
            c();
        }
    }

    public tp2 b(a aVar) {
        int i = R$string.twa_running_in_chrome;
        Resources resources = this.e;
        String string = resources.getString(i);
        String string2 = resources.getString(R$string.ok);
        tp2 tp2VarA = tp2.a(string, this.h, 2, 28);
        tp2VarA.d = string2;
        tp2VarA.e = null;
        tp2VarA.i = false;
        return tp2VarA;
    }

    public final void c() {
        tp2 tp2VarB;
        PropertyModel.p pVar = ag3.c;
        ag3 ag3Var = this.g;
        if (ag3Var.h(pVar) == 1 && (tp2VarB = b(this.h)) != null) {
            this.f.get().c(tp2VarB);
            ((ag3.a) ag3Var.j(ag3.f)).b();
        }
    }

    @Override // defpackage.kr2
    public final void j() {
        c();
    }

    @Override // defpackage.kr2
    public final void l() {
    }
}
