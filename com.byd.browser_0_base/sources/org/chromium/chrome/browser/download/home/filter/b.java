package org.chromium.chrome.browser.download.home.filter;

import android.content.Context;
import android.view.ViewGroup;
import defpackage.gx;
import defpackage.lg2;
import defpackage.mo;
import defpackage.nw2;
import defpackage.p32;
import defpackage.sm0;
import defpackage.xr;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: FilterCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final org.chromium.base.b<a> a = new org.chromium.base.b<>();
    public final PropertyModel b;
    public final FilterView c;
    public final mo d;
    public final org.chromium.chrome.browser.download.home.filter.a e;
    public final nw2<Boolean> f;

    /* JADX INFO: compiled from: FilterCoordinator.java */
    public interface a {
        void a(int i);
    }

    public b(Context context, lg2 lg2Var, p32 p32Var) {
        PropertyModel propertyModel = new PropertyModel(sm0.e);
        this.b = propertyModel;
        org.chromium.chrome.browser.download.home.filter.a aVar = new org.chromium.chrome.browser.download.home.filter.a(context, new gx(9, this), lg2Var);
        this.e = aVar;
        mo moVar = new mo(context, aVar.j);
        this.d = moVar;
        moVar.a.g(new mo.a(context.getResources().getDimensionPixelSize(R$dimen.chip_list_chip_spacing), context.getResources().getDimensionPixelSize(R$dimen.chip_list_side_padding)));
        this.f = p32Var;
        FilterView filterView = new FilterView(context);
        this.c = filterView;
        j.b(propertyModel, filterView, new c());
        propertyModel.p(sm0.c, new xr(15, this));
        c(0);
        propertyModel.l(sm0.d, ((Boolean) p32Var.get()).booleanValue());
    }

    public final ViewGroup a() {
        return this.c.a;
    }

    public final void b(int i) {
        c(i);
        int iF = i == 0 ? this.e.f() : 7;
        Iterator<a> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(iF);
            }
        }
    }

    public final void c(int i) {
        PropertyModel.p pVar = sm0.b;
        PropertyModel propertyModel = this.b;
        propertyModel.n(pVar, i);
        if (i == 0) {
            propertyModel.p(sm0.a, this.d.a);
        } else if (i == 1) {
            propertyModel.p(sm0.a, null);
        }
    }
}
