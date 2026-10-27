package defpackage;

import android.content.Context;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: DeviceClassManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class g90 {
    public static g90 g;
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;

    public g90() {
        if (SysUtils.isLowEndDevice()) {
            this.a = true;
            this.b = true;
            this.c = false;
            this.d = false;
            this.e = false;
        } else {
            this.a = true;
            this.b = false;
            this.c = true;
            this.d = true;
            this.e = true;
        }
        if (DeviceFormFactor.isTablet()) {
            this.b = false;
        }
        boolean zG = this.b | iv.e().g("enable-accessibility-tab-switcher");
        this.b = zG;
        this.f = !r0.g("disable-fullscreen");
        if (zG) {
            this.c = false;
        }
    }

    public static boolean a(Context context) {
        if (((DeviceFormFactor.a(context) ^ true) || bq.B.a()) && bq.b0.a() && bq.a0.a()) {
            return false;
        }
        if (c().b) {
            return true;
        }
        if (qo.f().c()) {
            return SharedPreferencesManager.getInstance().readBoolean("accessibility_tab_switcher", true);
        }
        return false;
    }

    public static boolean b() {
        if (!c().c) {
            return false;
        }
        if (qo.f().c()) {
            return !SharedPreferencesManager.getInstance().readBoolean("accessibility_tab_switcher", true);
        }
        return true;
    }

    public static g90 c() {
        if (g == null) {
            g = new g90();
        }
        return g;
    }
}
