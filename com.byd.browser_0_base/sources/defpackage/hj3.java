package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import defpackage.nj3;
import org.chromium.base.BuildInfo;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: UpdateMenuItemHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hj3 {
    public static hj3 e;
    public static final Object f = new Object();
    public nj3.c c;
    public final b<Runnable> a = new b<>();
    public final lj1 b = new lj1(4, this);
    public um0 d = new um0();

    public static hj3 a() {
        hj3 hj3Var;
        synchronized (f) {
            if (e == null) {
                e = new hj3();
            }
            hj3Var = e;
        }
        return hj3Var;
    }

    public final void b() {
        boolean zG = iv.e().g("force-show-update-menu-badge");
        Resources resources = xz.a.getResources();
        this.d = new um0();
        nj3.c cVar = this.c;
        int i = cVar.a;
        if (i == 1) {
            if (zG | (!TextUtils.equals(oj2.k(Profile.d()).c("omaha.latest_version_when_clicked_upate_menu_item"), this.c.d))) {
                this.d.b = new ef1();
                ef1 ef1Var = (ef1) this.d.b;
                ef1Var.a = R$string.accessibility_toolbar_btn_menu_update;
                ef1Var.b = R$drawable.badge_update_dark;
                ef1Var.c = R$drawable.badge_update_light;
                ef1Var.d = R$drawable.badge_update;
            }
            um0 um0Var = this.d;
            if1 if1Var = new if1();
            um0Var.a = if1Var;
            if1Var.a = R$string.menu_update;
            if1Var.b = R$color.default_text_color_error;
            if1Var.d = R$drawable.badge_update;
            if1Var.e = true;
            if1Var.c = n3.q0("custom_summary");
            if (TextUtils.isEmpty(((if1) this.d.a).c)) {
                ((if1) this.d.a).c = resources.getString(R$string.menu_update_summary_default);
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        if (zG | (cVar.d == null) | (!TextUtils.equals(BuildInfo.a.a.e, r2))) {
            this.d.b = new ef1();
            ef1 ef1Var2 = (ef1) this.d.b;
            ef1Var2.a = R$string.accessibility_toolbar_btn_menu_os_version_unsupported;
            ef1Var2.b = R$drawable.ic_error_grey800_24dp_filled;
            ef1Var2.c = R$drawable.ic_error_white_24dp_filled;
            ef1Var2.d = R$drawable.ic_error_24dp_filled;
        }
        um0 um0Var2 = this.d;
        if1 if1Var2 = new if1();
        um0Var2.a = if1Var2;
        if1Var2.a = R$string.menu_update_unsupported;
        if1Var2.b = R$color.default_text_color_list;
        if1Var2.c = resources.getString(R$string.menu_update_unsupported_summary_default);
        if1 if1Var3 = (if1) this.d.a;
        if1Var3.d = R$drawable.ic_error_24dp_filled;
        if1Var3.e = false;
    }

    public final void c(Runnable runnable) {
        if (this.a.a(runnable)) {
            if (this.c != null) {
                PostTask.c(rh3.a, new a9(this, 12, runnable));
                return;
            }
            nj3 nj3Var = nj3.a.a;
            lj1 lj1Var = this.b;
            if (nj3Var.a.b(lj1Var)) {
                return;
            }
            nj3Var.a.a(lj1Var);
            nj3.c cVar = nj3Var.d;
            if (cVar != null) {
                PostTask.c(rh3.a, lj1Var.p1(cVar));
            } else if (nj3Var.b.b == 0) {
                nj3Var.b.c(i9.e);
            }
        }
    }
}
