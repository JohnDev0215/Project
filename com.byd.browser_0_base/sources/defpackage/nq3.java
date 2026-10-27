package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import defpackage.cq;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.feedback.ScreenshotTask;
import org.chromium.chrome.browser.night_mode.settings.ThemeSettingsFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: WebContentsDarkModeMessageController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nq3 implements di1.a {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ fu0 f;
    public final /* synthetic */ Activity g;
    public final /* synthetic */ Profile h;
    public final /* synthetic */ String i;
    public final /* synthetic */ SettingsLauncher j;
    public final /* synthetic */ bi1 k;
    public final /* synthetic */ xe3 l;

    public nq3(boolean z, fu0 fu0Var, Activity activity, Profile profile, String str, n3 n3Var, bi1 bi1Var, xe3 xe3Var) {
        this.e = z;
        this.f = fu0Var;
        this.g = activity;
        this.h = profile;
        this.i = str;
        this.j = n3Var;
        this.k = bi1Var;
        this.l = xe3Var;
    }

    @Override // di1.a
    public final void b(int i) {
        this.l.dismissed("IPH_AutoDarkOptOut");
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 2) {
            return;
        }
        if (i == 0) {
            if (this.e) {
                final Activity activity = this.g;
                Profile profile = this.h;
                final hu0 hu0Var = (hu0) this.f;
                hu0Var.getClass();
                final long jElapsedRealtime = SystemClock.elapsedRealtime();
                new cq(activity, new ScreenshotTask(activity), new cq.a(this.i, null, profile), new Callback() { // from class: gu0
                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj) {
                        em0 em0Var = (em0) obj;
                        hu0Var.getClass();
                        oj2.X(SystemClock.elapsedRealtime() - jElapsedRealtime, "Feedback.Duration.FormOpenToSubmit");
                        em0Var.getClass();
                        Object obj2 = ThreadUtils.a;
                        em0Var.i = null;
                        Bundle bundle = new Bundle();
                        dm0 dm0Var = new dm0(0, bundle);
                        CollectionUtil.a(em0Var.f, dm0Var);
                        CollectionUtil.a(em0Var.g, dm0Var);
                        bundle.toString();
                        hu0.b(activity);
                    }
                }, profile);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("theme_settings_entry", 2);
                this.j.b(this.g, ThemeSettingsFragment.class, bundle);
            }
        }
        this.k.b(i == 0 ? 1 : 2, propertyModel);
    }
}
