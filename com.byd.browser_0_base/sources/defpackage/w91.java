package defpackage;

import android.content.Context;
import android.view.View;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LocationBarFocusScrimHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w91 implements vj3 {
    public final PropertyModel e;
    public final ef2 f;
    public boolean g;
    public final int h;
    public final f i;
    public final Context j;

    public w91(ef2 ef2Var, g.b bVar, Context context, LocationBarModel locationBarModel, hc3 hc3Var, View view) {
        this.f = ef2Var;
        this.i = locationBarModel;
        this.j = context;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.tab_strip_height);
        this.h = context.getColor(R$color.omnibox_focused_fading_background_color_light);
        PropertyModel.b bVar2 = new PropertyModel.b(jf2.m);
        bVar2.e(jf2.c, view);
        bVar2.b(jf2.d, true);
        bVar2.b(jf2.b, false);
        bVar2.c(jf2.a, dimensionPixelSize);
        bVar2.e(jf2.f, hc3Var);
        bVar2.e(jf2.e, bVar);
        bVar2.c(jf2.h, 0);
        this.e = bVar2.a();
    }

    @Override // defpackage.vj3
    public final void J0(boolean z) {
        if (z && this.i.g().j()) {
            this.f.a(this.e);
            this.g = true;
        }
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        Context context = this.j;
        boolean zA = DeviceFormFactor.a(context);
        f fVar = this.i;
        boolean z2 = (zA || fVar.isIncognito() || z7.I(context)) ? false : true;
        PropertyModel propertyModel = this.e;
        propertyModel.n(jf2.h, z2 ? this.h : 0);
        ef2 ef2Var = this.f;
        if (z && !fVar.g().j()) {
            ef2Var.a(propertyModel);
            this.g = true;
        } else {
            if (z || !this.g) {
                return;
            }
            ef2Var.b.a(300, true);
            this.g = false;
        }
    }
}
