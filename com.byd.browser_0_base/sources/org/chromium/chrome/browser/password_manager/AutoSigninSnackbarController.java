package org.chromium.chrome.browser.password_manager;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ii2;
import defpackage.nh0;
import defpackage.tp2;
import defpackage.wp2;
import defpackage.xp2;
import defpackage.yp2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class AutoSigninSnackbarController implements wp2.b {
    public final wp2 e;
    public final a f;
    public final Tab g;

    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            AutoSigninSnackbarController autoSigninSnackbarController = AutoSigninSnackbarController.this;
            wp2 wp2Var = autoSigninSnackbarController.e;
            yp2 yp2Var = wp2Var.f;
            if (yp2Var != null && yp2Var.b.isShown()) {
                wp2Var.a(autoSigninSnackbarController);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            AutoSigninSnackbarController autoSigninSnackbarController = AutoSigninSnackbarController.this;
            wp2 wp2Var = autoSigninSnackbarController.e;
            yp2 yp2Var = wp2Var.f;
            if (yp2Var != null && yp2Var.b.isShown()) {
                wp2Var.a(autoSigninSnackbarController);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            AutoSigninSnackbarController autoSigninSnackbarController = AutoSigninSnackbarController.this;
            wp2 wp2Var = autoSigninSnackbarController.e;
            yp2 yp2Var = wp2Var.f;
            if (yp2Var != null && yp2Var.b.isShown()) {
                wp2Var.a(autoSigninSnackbarController);
            }
        }
    }

    public AutoSigninSnackbarController(wp2 wp2Var, Tab tab) {
        this.g = tab;
        this.e = wp2Var;
        a aVar = new a();
        this.f = aVar;
        tab.x(aVar);
    }

    @CalledByNative
    public static void showSnackbar(Tab tab, String str) {
        WindowAndroid windowAndroidD;
        Activity activityB = TabUtils.b(tab);
        if (activityB == null || (windowAndroidD = tab.D()) == null) {
            return;
        }
        wp2 wp2VarA = xp2.a(windowAndroidD);
        tp2 tp2VarA = tp2.a(str, new AutoSigninSnackbarController(wp2VarA, tab), 1, 4);
        int iB = ii2.b(activityB);
        Drawable drawableA = AppCompatResources.a(activityB, R$drawable.logo_avatar_anonymous);
        tp2VarA.i = false;
        tp2VarA.g = iB;
        tp2VarA.k = drawableA;
        tp2VarA.h = R$style.TextAppearance_TextMedium_Primary_Baseline_Light;
        wp2VarA.c(tp2VarA);
    }

    @Override // wp2.b
    public final void d(Object obj) {
        this.g.t(this.f);
    }

    @Override // wp2.b
    public final void j(Object obj) {
    }
}
