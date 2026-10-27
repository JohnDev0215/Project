package org.chromium.chrome.browser.login;

import J.N;
import android.app.Activity;
import android.view.WindowManager;
import defpackage.ja1;
import defpackage.nh0;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeHttpAuthHandler extends nh0 implements ja1.a {
    public long e;
    public String f;
    public String g;
    public ja1 h;
    public Tab i;

    public ChromeHttpAuthHandler(long j) {
        this.e = j;
    }

    @CalledByNative
    public static ChromeHttpAuthHandler create(long j) {
        return new ChromeHttpAuthHandler(j);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        y1();
    }

    @CalledByNative
    public final void closeDialog() {
        ja1 ja1Var = this.h;
        if (ja1Var != null) {
            ja1Var.b.dismiss();
        }
    }

    @CalledByNative
    public final void onAutofillDataAvailable(String str, String str2) {
        this.f = str;
        this.g = str2;
        ja1 ja1Var = this.h;
        if (ja1Var != null) {
            ja1Var.c.setText(str);
            ja1Var.d.setText(str2);
            ja1Var.c.selectAll();
        }
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.e = 0L;
        Tab tab = this.i;
        if (tab != null) {
            tab.t(this);
        }
        this.i = null;
    }

    @CalledByNative
    public final void showDialog(Tab tab, WindowAndroid windowAndroid) {
        String str;
        if (tab == null || tab.isHidden() || windowAndroid == null) {
            y1();
            return;
        }
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            y1();
            return;
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            y1();
            return;
        }
        this.i = tab;
        tab.x(this);
        ja1 ja1Var = new ja1(activity, N.MDNVFLnS(this.e, this), this);
        this.h = ja1Var;
        String str2 = this.f;
        if (str2 != null && (str = this.g) != null) {
            ja1Var.c.setText(str2);
            ja1Var.d.setText(str);
            ja1Var.c.selectAll();
        }
        try {
            ja1 ja1Var2 = this.h;
            ja1Var2.b.show();
            ja1Var2.c.requestFocus();
        } catch (WindowManager.BadTokenException unused) {
            y1();
        }
    }

    public final void y1() {
        N.MbTC7yfl(this.e, this);
    }
}
