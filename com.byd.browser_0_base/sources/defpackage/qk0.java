package defpackage;

import J.N;
import android.graphics.Bitmap;
import androidx.preference.Preference;
import defpackage.yw1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.night_mode.settings.RadioButtonGroupThemePreference;
import org.chromium.chrome.browser.night_mode.settings.ThemeSettingsFragment;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qk0 implements FaviconHelper.FaviconImageCallback, LargeIconBridge.LargeIconCallback, Preference.c {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ qk0(yw1 yw1Var, td2 td2Var, qv1 qv1Var) {
        this.e = 3;
        this.g = yw1Var;
        this.f = td2Var;
        this.h = qv1Var;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        ThemeSettingsFragment themeSettingsFragment = (ThemeSettingsFragment) this.g;
        RadioButtonGroupThemePreference radioButtonGroupThemePreference = (RadioButtonGroupThemePreference) this.h;
        SharedPreferencesManager sharedPreferencesManager = (SharedPreferencesManager) this.f;
        int i = ThemeSettingsFragment.j0;
        themeSettingsFragment.getClass();
        sk skVar = bq.a;
        if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting") && radioButtonGroupThemePreference.Y.isChecked() != themeSettingsFragment.h0) {
            boolean zIsChecked = radioButtonGroupThemePreference.Y.isChecked();
            themeSettingsFragment.h0 = zIsChecked;
            N.MM1KTgoi(themeSettingsFragment.i0, 72, zIsChecked);
            n3.Y0(0, zIsChecked);
        }
        sharedPreferencesManager.m(((Integer) obj).intValue(), "ui_theme_setting");
        return true;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        rk0 rk0Var = (rk0) this.g;
        String str = (String) this.h;
        Callback callback = (Callback) this.f;
        if (bitmap != null) {
            rk0Var.c.a.put(str, bitmap);
        } else {
            rk0Var.getClass();
        }
        callback.onResult(bitmap);
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        int i3 = this.e;
        Object obj = this.f;
        Object obj2 = this.h;
        Object obj3 = this.g;
        switch (i3) {
            case 1:
                nk0 nk0Var = (nk0) obj3;
                ((Callback) obj).onResult(a.f(bitmap, (GURL) obj2, i, nk0Var.b, nk0Var.a.getResources(), nk0Var.c));
                break;
            default:
                ((yw1) obj3).getClass();
                ((Callback) obj).onResult(new yw1.a((String) ((qv1) obj2).b, bitmap, i, z));
                break;
        }
    }

    public /* synthetic */ qk0(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.g = obj;
        this.h = obj2;
        this.f = obj3;
    }
}
