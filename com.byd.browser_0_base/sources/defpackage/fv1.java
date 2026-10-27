package defpackage;

import J.N;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PageZoomMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fv1 {
    public final PropertyModel a;
    public WebContents b;
    public double c;
    public double d;

    public fv1(PropertyModel propertyModel) {
        this.a = propertyModel;
        propertyModel.p(gv1.a, new aq3(2, this));
        propertyModel.p(gv1.b, new l51(6, this));
        propertyModel.p(gv1.c, new x10(24, this));
        propertyModel.n(gv1.g, 250);
        n3.l = xz.a.getResources().getConfiguration().fontScale;
    }

    public static double a(WebContents webContents) {
        return n3.m(N.M70wUYPG(webContents), 1.0f / n3.l, 1.0f / ((float) N.MiPv6Zp_(webContents)));
    }

    public final void b(int i) {
        double d = n3.k[i];
        int iC = iv1.c(d);
        this.a.n(gv1.h, iC);
        n3.i1(this.b, d);
        c(d);
        this.c = iv1.b(iC);
    }

    public final void c(double d) {
        PropertyModel.n nVar = gv1.e;
        double[] dArr = n3.k;
        boolean z = d > dArr[0];
        PropertyModel propertyModel = this.a;
        propertyModel.l(nVar, z);
        propertyModel.l(gv1.f, d < dArr[13]);
    }
}
