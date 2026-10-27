package org.chromium.components.browser_ui.sms;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import defpackage.oj2;
import defpackage.t41;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class WebOTPServiceInfoBar extends ConfirmInfoBar {
    public final String r;
    public final WindowAndroid s;
    public Long t;

    public WebOTPServiceInfoBar(WindowAndroid windowAndroid, int i, String str, String str2, String str3) {
        super(i, R$color.infobar_icon_drawable_color, null, str, null, str3, null);
        this.r = str2;
        this.s = windowAndroid;
    }

    @CalledByNative
    public static WebOTPServiceInfoBar create(WindowAndroid windowAndroid, int i, String str, String str2, String str3) {
        return new WebOTPServiceInfoBar(windowAndroid, i, str, str2, str3);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.xz0
    public final int e() {
        return 1;
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void k() {
        super.k();
        if (this.t != null) {
            oj2.Y(SystemClock.uptimeMillis() - this.t.longValue(), "Blink.Sms.Receive.TimeCancelOnKeyboardDismissal");
        }
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        oj2.U(0, 2, "Blink.Sms.Receive.Infobar");
        Activity activity = (Activity) this.s.k().get();
        if (activity != null) {
            View currentFocus = activity.getCurrentFocus();
            t41 t41Var = t41.f;
            if (currentFocus != null && t41Var.f(activity, currentFocus)) {
                t41Var.e(currentFocus);
                oj2.U(1, 2, "Blink.Sms.Receive.Infobar");
                this.t = Long.valueOf(SystemClock.uptimeMillis());
            }
        }
        infoBarLayout.getContext();
        infoBarLayout.a().a(0, this.r);
    }
}
