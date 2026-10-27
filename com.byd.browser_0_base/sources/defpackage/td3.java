package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.widget.ClipDrawableProgressBar;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TopToolbarOverlayMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class td3 {
    public final Context a;
    public final l61 b;
    public final qd3 c;
    public final Callback<ClipDrawableProgressBar.a> d;
    public final i20 e;
    public final kj f;
    public final sd3 g;
    public final xd3 h;
    public final PropertyModel i;
    public final boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;

    public td3(PropertyModel propertyModel, Context context, l61 l61Var, Callback callback, aq1 aq1Var, jj jjVar, xd3 xd3Var, int i, boolean z) {
        this.a = context;
        this.b = l61Var;
        this.d = callback;
        this.f = jjVar;
        this.h = xd3Var;
        this.i = propertyModel;
        this.j = z;
        this.n = (l61Var.d() & i) > 0;
        d();
        qd3 qd3Var = new qd3(this, i);
        this.c = qd3Var;
        l61Var.j(qd3Var);
        jb1 jb1Var = new jb1(17, this);
        i20 i20Var = new i20(aq1Var, new rd3(this), jb1Var);
        this.e = i20Var;
        jb1Var.onResult((Tab) aq1Var.f);
        i20Var.c.onResult((Tab) aq1Var.get());
        sd3 sd3Var = new sd3(this);
        this.g = sd3Var;
        jjVar.o(sd3Var);
        if (cc3.b()) {
            this.l = jjVar.t() == 0;
        }
    }

    public final void a() {
        if (DeviceFormFactor.a(this.a)) {
            return;
        }
        PropertyModel.r<ClipDrawableProgressBar.a> rVar = ud3.b;
        PropertyModel propertyModel = this.i;
        if (propertyModel.j(rVar) == null) {
            propertyModel.p(rVar, new ClipDrawableProgressBar.a());
        }
        this.d.onResult((ClipDrawableProgressBar.a) propertyModel.j(rVar));
        propertyModel.p(rVar, (ClipDrawableProgressBar.a) propertyModel.j(rVar));
    }

    public final void b() {
        this.i.l(ud3.d, (cc3.b() ? this.l ^ true : (this.f.p() > 0.0f ? 1 : (this.f.p() == 0.0f ? 0 : -1)) > 0) || !this.k || this.j);
    }

    public final void c(Tab tab) {
        int iF = this.h.f(tab);
        PropertyModel.p pVar = ud3.e;
        PropertyModel propertyModel = this.i;
        propertyModel.n(pVar, iF);
        propertyModel.n(ud3.f, n3.v0(this.a, tab, iF));
    }

    public final void d() {
        boolean z = this.j;
        PropertyModel propertyModel = this.i;
        if (z) {
            propertyModel.l(ud3.h, this.m && this.n);
        } else {
            propertyModel.l(ud3.h, !((this.f.p() > 1.0f ? 1 : (this.f.p() == 1.0f ? 0 : -1)) == 0) && this.n);
        }
    }
}
