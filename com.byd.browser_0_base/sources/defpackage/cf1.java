package defpackage;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import defpackage.af1;
import defpackage.df1;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MenuButtonMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cf1 implements l7 {
    public m7 a;
    public a7 b;
    public final aq1<z6> c;
    public h7 d;
    public final vj e;
    public final af1.a f;
    public final PropertyModel g;
    public final Runnable h;
    public final Activity i;
    public final t41 j;
    public final boolean k;
    public final nw2<Boolean> l;
    public int m = -1;
    public int n = -1;
    public final nw2<Boolean> o;
    public boolean p;
    public final Resources q;
    public final nw2<ef1> r;
    public final Runnable s;
    public final int t;

    public cf1(PropertyModel propertyModel, boolean z, p11 p11Var, ww wwVar, ThemeColorProvider themeColorProvider, nw2 nw2Var, vj vjVar, af1.a aVar, rs1 rs1Var, g3 g3Var, zq2 zq2Var, wz wzVar) {
        this.g = propertyModel;
        this.k = z;
        this.l = p11Var;
        this.h = wwVar;
        this.o = nw2Var;
        this.e = vjVar;
        this.f = aVar;
        themeColorProvider.i.a(new ThemeColorProvider.b() { // from class: bf1
            @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.b
            public final void e(int i, ColorStateList colorStateList) {
                cf1 cf1Var = this.e;
                cf1Var.getClass();
                cf1Var.g.p(df1.i, new df1.b(i, colorStateList));
            }
        });
        rs1Var.h(new on0(26, this));
        Activity activity = (Activity) g3Var.k().get();
        this.i = activity;
        Resources resources = activity.getResources();
        this.q = resources;
        this.c = new aq1<>();
        this.j = (p2) g3Var.f;
        this.r = zq2Var;
        this.s = wzVar;
        this.t = resources.getDimensionPixelSize(R$dimen.toolbar_url_focus_translation_x);
    }

    @Override // defpackage.l7
    public final void a(boolean z) {
        vj vjVar = this.e;
        if (z) {
            this.f.u();
            View currentFocus = this.i.getCurrentFocus();
            if (currentFocus != null) {
                this.j.e(currentFocus);
            }
            if (!this.o.get().booleanValue() && ((df1.a) this.g.j(df1.h)).a) {
                c(true);
                this.h.run();
            }
            this.m = vjVar.w(this.m);
        } else {
            vjVar.u(this.m);
        }
        if (z) {
            this.s.run();
        }
    }

    @Override // defpackage.l7
    public final void b(boolean z) {
        this.g.l(df1.e, z);
        vj vjVar = this.e;
        if (z) {
            this.n = vjVar.w(this.n);
        } else {
            vjVar.u(this.n);
        }
    }

    public final void c(boolean z) {
        PropertyModel.r<df1.a> rVar = df1.h;
        df1.a aVar = new df1.a(false, z);
        PropertyModel propertyModel = this.g;
        propertyModel.p(rVar, aVar);
        propertyModel.p(df1.c, this.q.getString(R$string.accessibility_toolbar_btn_menu));
    }

    public final void d(boolean z) {
        if (this.p) {
            return;
        }
        int i = this.r.get().a;
        PropertyModel.r<String> rVar = df1.c;
        String string = this.q.getString(i);
        PropertyModel propertyModel = this.g;
        propertyModel.p(rVar, string);
        propertyModel.p(df1.h, new df1.a(true, z));
    }
}
