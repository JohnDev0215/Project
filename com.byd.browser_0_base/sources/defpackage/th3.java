package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: UiUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class th3 {
    public final Context a;
    public final int b;
    public final int c;
    public final Drawable d;
    public final Drawable e;
    public final LargeIconBridge f;
    public final uc2 g;

    public th3(Activity activity, LargeIconBridge largeIconBridge) {
        this.a = activity;
        this.f = largeIconBridge;
        Resources resources = activity.getResources();
        this.b = (int) resources.getDimension(R$dimen.default_favicon_min_size);
        this.c = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
        int i = R$drawable.incognito_simple;
        int i2 = R$color.default_icon_color_tint_list;
        this.d = uh3.e(activity, i, i2);
        this.e = uh3.e(activity, R$drawable.ic_globe_24dp, i2);
        this.g = a.d(activity);
    }

    public static int d(b11 b11Var) {
        return b11Var.f + (b11Var.b == -1 ? 0 : b11Var.g);
    }

    public final String a(b11 b11Var) {
        int i = b11Var.b == -1 ? 0 : b11Var.g;
        int iD = d(b11Var);
        Resources resources = this.a.getResources();
        int i2 = b11Var.c;
        if (i2 == 1) {
            return resources.getString(R$string.instance_switcher_current_window);
        }
        if (i2 == 2) {
            return resources.getString(R$string.instance_switcher_adjacent_window);
        }
        if (iD == 0) {
            return resources.getString(R$string.instance_switcher_tab_count_zero);
        }
        boolean z = b11Var.h;
        int i3 = b11Var.f;
        return (!z || i <= 0) ? i == 0 ? resources.getQuantityString(R$plurals.instance_switcher_tab_count_nonzero, i3, Integer.valueOf(i3)) : resources.getQuantityString(R$plurals.instance_switcher_desc_mixed, iD, Integer.valueOf(i), Integer.valueOf(iD), Integer.valueOf(i)) : i3 == 0 ? resources.getQuantityString(R$plurals.instance_switcher_desc_incognito, i, Integer.valueOf(i)) : resources.getQuantityString(R$plurals.instance_switcher_desc_mixed, iD, Integer.valueOf(i), Integer.valueOf(iD), Integer.valueOf(i));
    }

    public final String b(b11 b11Var) {
        boolean z = false;
        int i = b11Var.b == -1 ? 0 : b11Var.g;
        int iD = d(b11Var);
        Resources resources = this.a.getResources();
        if (iD != 0) {
            String str = b11Var.e;
            if (iD == 1 && TextUtils.isEmpty(b11Var.d) && TextUtils.isEmpty(str)) {
                z = true;
            }
            if (!z) {
                return (!b11Var.h || i <= 0) ? str : resources.getString(R$string.notification_incognito_tab);
            }
        }
        return resources.getString(R$string.instance_switcher_entry_empty_window);
    }

    public final void c(PropertyModel propertyModel, PropertyModel.r<Drawable> rVar, b11 b11Var) {
        boolean z = false;
        int i = b11Var.b == -1 ? 0 : b11Var.g;
        int iD = d(b11Var);
        if (iD != 0) {
            String str = b11Var.d;
            if (iD == 1 && TextUtils.isEmpty(str) && TextUtils.isEmpty(b11Var.e)) {
                z = true;
            }
            if (!z) {
                if (!b11Var.h || i <= 0) {
                    this.f.b(new GURL(str), this.b, new ec0(this, propertyModel, rVar, b11Var));
                    return;
                } else {
                    propertyModel.p(rVar, this.d);
                    return;
                }
            }
        }
        propertyModel.p(rVar, this.e);
    }
}
