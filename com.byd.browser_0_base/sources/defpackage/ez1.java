package defpackage;

import android.app.Activity;
import defpackage.fz1;
import org.chromium.components.omnibox.R$string;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PaymentHandlerToolbarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ez1 implements fz1.a {
    public final WebContents a;
    public final Activity b;
    public final boolean c;
    public final PropertyModel d;
    public final hz1 e;
    public final nw2<bi1> f;

    public ez1(Activity activity, WebContents webContents, GURL gurl, yo yoVar) {
        this.a = webContents;
        this.b = activity;
        this.f = yoVar;
        PropertyModel.b bVar = new PropertyModel.b(gz1.i);
        bVar.b(gz1.d, true);
        bVar.f(gz1.c, 0.05f);
        bVar.c(gz1.e, z7.C(0, this.c, false, false));
        bVar.e(gz1.f, activity.getResources().getString(R$string.accessibility_security_btn_warn));
        bVar.e(gz1.a, gurl);
        bVar.e(gz1.g, new eu1(6, this));
        PropertyModel propertyModelA = bVar.a();
        this.d = propertyModelA;
        this.c = !DeviceFormFactor.a(activity);
        fz1 fz1Var = new fz1(propertyModelA, webContents, this);
        hz1 hz1Var = new hz1(activity);
        this.e = hz1Var;
        webContents.C(fz1Var);
        j.b(propertyModelA, hz1Var, new bg(17));
    }
}
