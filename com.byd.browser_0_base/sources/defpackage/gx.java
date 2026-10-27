package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import androidx.core.view.accessibility.a;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.Preference;
import defpackage.af1;
import defpackage.et2;
import defpackage.h62;
import defpackage.jk2;
import defpackage.lr2;
import defpackage.m60;
import defpackage.mk0;
import defpackage.xb;
import defpackage.xq3;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.ChromeBackupWatcher;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.commerce.PriceNotificationPreferenceFragment;
import org.chromium.chrome.browser.download.home.filter.a;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.a;
import org.chromium.chrome.browser.language.settings.c;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.safe_browsing.settings.StandardProtectionSettingsFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.toolbar.adaptive.settings.AdaptiveToolbarPreferenceFragment;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gx implements a, SharedPreferencesManager.b, i.a, xb.c, Preference.c, a.InterfaceC0079a, m60.f, LargeIconBridge.LargeIconCallback, a.InterfaceC0091a, gq, k32, UmaUtils.a, mk0.b, n02, OptimizationGuideBridge.OptimizationGuideCallback, af1.a, et2.a, AccountManagerFacade.a, lr2.a, jk2.d, f.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ gx(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // xb.c
    public final boolean a() {
        return ((gv0) this.f).a();
    }

    @Override // mk0.b
    public final void c(int i, Bitmap bitmap) {
        ((PropertyModel) this.f).p(na3.c, new BitmapDrawable(bitmap));
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        DrawerLayout drawerLayout = (DrawerLayout) this.f;
        int[] iArr = DrawerLayout.I;
        drawerLayout.getClass();
        if (!DrawerLayout.k(view) || drawerLayout.g(view) == 2) {
            return false;
        }
        drawerLayout.b(view);
        return true;
    }

    @Override // org.chromium.chrome.browser.language.settings.a.InterfaceC0091a
    public final void h() {
        a.InterfaceC0091a interfaceC0091a = (a.InterfaceC0091a) this.f;
        int i = LanguageSettings.l0;
        c.g(16);
        interfaceC0091a.h();
    }

    @Override // org.chromium.chrome.browser.preferences.SharedPreferencesManager.b
    public final void i(String str) {
        ChromeBackupWatcher chromeBackupWatcher = (ChromeBackupWatcher) this.f;
        chromeBackupWatcher.getClass();
        String[] strArr = rp.a;
        for (int i = 0; i < 5; i++) {
            if (str.equals(strArr[i])) {
                chromeBackupWatcher.onBackupPrefsChanged();
                return;
            }
        }
    }

    @Override // org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge.OptimizationGuideCallback
    public final void j(int i, nv nvVar) {
        Callback callback = (Callback) this.f;
        int i2 = xl2.t;
        boolean z = true;
        if (i != 1 && i != 0) {
            z = false;
        }
        callback.onResult(Boolean.valueOf(z));
    }

    @Override // org.chromium.components.signin.AccountManagerFacade.a
    public final void k(boolean z) {
        km2 km2Var = (km2) this.f;
        km2Var.j.l(lm2.c, z);
        int i = z ? R$drawable.ic_account_child_20dp : 0;
        h62 h62Var = km2Var.k;
        if (i == 0 && h62Var.c == null) {
            return;
        }
        h62Var.c = i == 0 ? null : new h62.a(h62Var.a, i);
        h62Var.g.clear();
        n3.m0().g(new g62(h62Var, 1));
    }

    @Override // org.chromium.components.browser_ui.site_settings.f.a
    public final void l() {
        ((Runnable) this.f).run();
    }

    @Override // et2.a
    public final void m(Tab tab, wq wqVar) {
        tq tqVar = (tq) this.f;
        tqVar.getClass();
        WebContents webContentsC = tab.c();
        if (webContentsC == null || !ProfileManager.b) {
            return;
        }
        Activity activityB = TabUtils.b(tab);
        PageInfoController.g(activityB, webContentsC, null, 2, new vq(activityB, webContentsC, tqVar.a, new d.e(tab), tqVar.b, tqVar.c, wqVar), wqVar);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 7:
                PriceNotificationPreferenceFragment priceNotificationPreferenceFragment = (PriceNotificationPreferenceFragment) obj2;
                int i2 = PriceNotificationPreferenceFragment.n0;
                priceNotificationPreferenceFragment.getClass();
                if ("send_email_switch".equals(preference.p)) {
                    priceNotificationPreferenceFragment.i0.e("price_tracking.email_notifications_enabled", ((Boolean) obj).booleanValue());
                }
                break;
            case 18:
                by0 by0Var = (by0) obj2;
                by0Var.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (!by0Var.b) {
                    boolean zL = gq2.l("incognito.incognito_reauthentication");
                    if (by0Var.c == null) {
                        by0Var.c = new ny0();
                    }
                    by0Var.c.c(new ay0(by0Var, zL, zBooleanValue));
                }
                break;
            default:
                AdaptiveToolbarPreferenceFragment adaptiveToolbarPreferenceFragment = (AdaptiveToolbarPreferenceFragment) obj2;
                int i3 = AdaptiveToolbarPreferenceFragment.j0;
                adaptiveToolbarPreferenceFragment.getClass();
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                SharedPreferencesManager.getInstance().l("Chrome.AdaptiveToolbarCustomization.Enabled", zBooleanValue2);
                adaptiveToolbarPreferenceFragment.i0.J(zBooleanValue2);
                oj2.O("Android.AdaptiveToolbarButton.SettingsToggle.Changed", SharedPreferencesManager.getInstance().readBoolean("Chrome.AdaptiveToolbarCustomization.Enabled", true));
                break;
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.feed.webfeed.i.a
    public final void o() {
        ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) this.f;
        n3 n3Var = ChromeTabbedActivity.S1;
        chromeTabbedActivity.p0(false).g(2, vm1.b());
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        xq3.a aVar = (xq3.a) this.f;
        if (bitmap == null) {
            int i3 = aVar.b;
            bitmap = new uc2(i3, i3, i3 / 2, i, aVar.c).c(aVar.a);
        }
        aVar.d.onResult(bitmap);
    }

    @Override // defpackage.n02
    public final void p(ByteBuffer byteBuffer, i10 i10Var) {
        Tab tab = (Tab) this.f;
        int i = xl2.t;
        PostTask.c(rh3.a, new w70(tab, 4, byteBuffer, i10Var));
    }

    @Override // defpackage.k32
    public final boolean q(f51 f51Var) {
        return f51Var.e && !f51Var.equals((f51) this.f);
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 14:
                int i2 = LanguageSettings.l0;
                return ((LanguageSettings) obj).M0().d("translate.enabled");
            default:
                StandardProtectionSettingsFragment standardProtectionSettingsFragment = (StandardProtectionSettingsFragment) obj;
                int i3 = StandardProtectionSettingsFragment.m0;
                standardProtectionSettingsFragment.getClass();
                String str = preference.p;
                if ("extended_reporting".equals(str)) {
                    return N.Mp340wGB();
                }
                if ("password_leak_detection".equals(str)) {
                    return standardProtectionSettingsFragment.l0.d("profile.password_manager_leak_detection");
                }
                return false;
        }
    }

    @Override // jk2.d
    public final void s(FileOutputStream fileOutputStream, sl0 sl0Var) throws IOException {
        fileOutputStream.write((byte[]) this.f);
        sl0Var.onResult(Boolean.TRUE);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.b
    public final void t(int i, long j) {
        ((l43) this.f).I(i, true);
    }

    @Override // af1.a
    public final void u() {
        ((g) this.f).v(12, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String v(defpackage.ml r7) {
        /*
            r6 = this;
            java.lang.Object r6 = r6.f
            hx r6 = (defpackage.hx) r6
            int r0 = r6.g
            if (r0 == 0) goto L58
            r1 = 1
            if (r0 == r1) goto L24
            r7 = 2
            if (r0 == r7) goto L1c
            r7 = 3
            if (r0 == r7) goto L19
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Connection state is invalid"
            r6.<init>(r7)
            throw r6
        L19:
            java.lang.Exception r6 = r6.j
            throw r6
        L1c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Service has been disconnected."
            r6.<init>(r7)
            throw r6
        L24:
            dg3 r0 = r6.h
            if (r0 == 0) goto L50
            r7.d = r1
            ol<T> r2 = r7.b
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L46
            ol$a r2 = r2.f
            r2.getClass()
            s$a r5 = defpackage.AbstractC0208s.i
            boolean r0 = r5.b(r2, r3, r0)
            if (r0 == 0) goto L42
            defpackage.AbstractC0208s.c(r2)
            r0 = r1
            goto L43
        L42:
            r0 = r4
        L43:
            if (r0 == 0) goto L46
            goto L47
        L46:
            r1 = r4
        L47:
            if (r1 == 0) goto L5d
            r7.a = r3
            r7.b = r3
            r7.c = r3
            goto L5d
        L50:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "ConnectionHolder state is incorrect."
            r6.<init>(r7)
            throw r6
        L58:
            java.util.ArrayList r0 = r6.i
            r0.add(r7)
        L5d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "ConnectionHolder, state = "
            r7.<init>(r0)
            int r6 = r6.g
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gx.v(ml):java.lang.String");
    }

    public final void w(boolean z) {
        C0175b.r(this.f);
        throw null;
    }
}
