package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import defpackage.xt2;
import defpackage.zf1;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.R$color;
import org.chromium.components.permissions.PermissionDialogController;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StatusMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wt2 implements PermissionDialogController.b, TemplateUrlService.c, zf1.a {
    public final f A;
    public final PermissionDialogController B;
    public final su1 F;
    public final WindowAndroid H;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f101J;
    public float K;
    public final PropertyModel e;
    public final jg2 f;
    public final qs1<TemplateUrlService> g;
    public final nw2<Profile> h;
    public final nw2<zf1> i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public final boolean o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int u;
    public int v;
    public int w;
    public int x;
    public final Resources y;
    public final Context z;
    public int t = 3;
    public final Handler C = new Handler();
    public final Handler D = new Handler();
    public int E = -1;
    public final qu1 G = new qu1();
    public boolean I = true;
    public final int L = 8500;

    public wt2(PropertyModel propertyModel, Resources resources, Context context, boolean z, f fVar, PermissionDialogController permissionDialogController, jg2 jg2Var, rs1 rs1Var, nw2 nw2Var, su1 su1Var, g3 g3Var, aq1 aq1Var) {
        this.e = propertyModel;
        this.A = fVar;
        this.f = jg2Var;
        this.g = rs1Var;
        rs1Var.h(new td2(7, this));
        this.h = nw2Var;
        this.y = resources;
        this.z = context;
        this.F = su1Var;
        this.H = g3Var;
        this.i = aq1Var;
        this.o = z;
        this.n = z;
        this.B = permissionDialogController;
        permissionDialogController.e.a(this);
        e();
        propertyModel.l(xt2.e, !fVar.isIncognito());
        f(0);
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        f(0);
    }

    @Override // org.chromium.components.permissions.PermissionDialogController.b
    public final void a(WindowAndroid windowAndroid, int[] iArr, int i) {
        int i2;
        if (windowAndroid != this.H) {
            return;
        }
        int[] iArr2 = ac2.b;
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= 23) {
                int[] iArr3 = ac2.c;
                int i4 = 0;
                loop2: while (true) {
                    if (i4 >= 2) {
                        i2 = -1;
                        break;
                    }
                    int i5 = iArr3[i4];
                    int length = iArr.length;
                    for (int i6 = 0; i6 < length; i6++) {
                        i2 = iArr[i6];
                        if ((i2 != 51 || N.Mudil8Bg("WebBluetoothNewPermissionsBackend")) && i5 == i2) {
                            break loop2;
                        }
                    }
                    i4++;
                }
            } else {
                int i7 = iArr2[i3];
                int length2 = iArr.length;
                for (int i8 = 0; i8 < length2; i8++) {
                    i2 = iArr[i8];
                    if (i7 == i2) {
                        break loop0;
                    }
                }
                i3++;
            }
        }
        if (i2 == -1) {
            return;
        }
        c();
        this.E = i2;
        boolean zIsIncognito = this.A.isIncognito();
        int i9 = this.E;
        Integer numValueOf = Integer.valueOf(i);
        int i10 = zIsIncognito ? R$color.default_icon_color_blue_light : R$color.default_icon_color_accent1_tint_list;
        int i11 = xy.c(i9, null).a;
        Context context = this.z;
        Drawable drawableC = qj2.c(context, i11, i10);
        if (numValueOf != null && numValueOf.intValue() == 2) {
            drawableC = xy.a(context.getResources(), drawableC);
        }
        xt2.a aVar = new xt2.a(drawableC, zIsIncognito);
        aVar.e = 1;
        aVar.f = new ql0(19, this);
        Handler handler = this.C;
        handler.removeCallbacksAndMessages(null);
        this.e.p(xt2.l, aVar);
        handler.postDelayed(new ut2(this, 0), this.L);
        this.G.a(0);
    }

    public final boolean b() {
        f fVar = this.A;
        return fVar.g().i() || fVar.e();
    }

    public final void c() {
        this.C.removeCallbacksAndMessages(null);
        this.E = -1;
        this.D.removeCallbacksAndMessages(null);
        this.f101J = false;
    }

    public final boolean d() {
        if (this.A.isIncognito()) {
            return false;
        }
        boolean z = this.j;
        if (z && this.n) {
            return true;
        }
        return (z || this.K > 0.0f) && b() && this.h.get() != null;
    }

    public final void e() {
        int color;
        int i = this.t;
        Context context = this.z;
        int color2 = i == 0 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_separator_color_dark) : i == 1 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_separator_color_light) : i == 2 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_separator_color_incognito) : oj2.n(R$attr.colorOutline, context, "OmniboxResourceProvider");
        PropertyModel.p pVar = xt2.d;
        PropertyModel propertyModel = this.e;
        propertyModel.n(pVar, color2);
        this.x = kp2.a();
        if (this.l) {
            int i2 = this.t;
            color = i2 == 0 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_preview_color_dark) : i2 == 1 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_preview_color_light) : i2 == 2 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_preview_color_incognito) : oj2.n(R$attr.colorPrimary, context, "OmniboxResourceProvider");
        } else if (this.m) {
            int i3 = this.t;
            color = i3 == 0 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_offline_color_dark) : i3 == 1 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_offline_color_light) : i3 == 2 ? context.getColor(org.chromium.chrome.R$color.locationbar_status_offline_color_incognito) : context.getColor(org.chromium.chrome.R$color.default_text_color_secondary_list);
        } else {
            color = 0;
        }
        if (color != 0) {
            propertyModel.n(xt2.m, color);
        }
        f(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r18) {
        /*
            Method dump skipped, instruction units count: 481
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wt2.f(int):void");
    }

    public final void g() {
        if (this.o) {
            return;
        }
        boolean z = true;
        boolean z2 = !this.A.isIncognito();
        if (this.n != z2) {
            this.n = z2;
            f(0);
        }
        if (z2) {
            Profile profile = this.h.get();
            PropertyModel propertyModel = this.e;
            if (profile == null || !b()) {
                propertyModel.l(xt2.e, true);
                return;
            }
            if (!z2 || (!this.j && this.K <= 0.0f)) {
                z = false;
            }
            propertyModel.l(xt2.e, z);
        }
    }

    public final void h() {
        boolean z = this.l;
        boolean z2 = false;
        int i = z ? R$string.location_bar_paint_preview_page_status : this.m ? R$string.location_bar_verbose_status_offline : 0;
        if ((this.m || z) && this.k && !this.j && i != 0) {
            z2 = true;
        }
        PropertyModel propertyModel = this.e;
        if (z2) {
            propertyModel.n(xt2.n, i);
        }
        propertyModel.l(xt2.o, z2);
    }

    public void setStatusClickListener(View.OnClickListener onClickListener) {
        this.e.p(xt2.g, onClickListener);
    }
}
