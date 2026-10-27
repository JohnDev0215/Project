package defpackage;

import J.N;
import androidx.preference.Preference;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.kp1;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.notifications.NotificationTriggerScheduler;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.password_check.b;
import org.chromium.chrome.browser.prefetch.settings.PreloadPagesSettingsFragment;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsSettings;
import org.chromium.chrome.browser.privacy_sandbox.v4.AdMeasurementFragmentV4;
import org.chromium.chrome.browser.privacy_sandbox.v4.TopicsFragmentV4;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.n;
import org.chromium.ui.modelutil.o;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i83 implements TextInputLayout.f, Preference.c, a.f, NotificationTriggerScheduler.a, PermissionCallback, o.a, gq, g.d, b.c, r02, LocationBarModel.b, n.a, WebContentsImpl.d {
    public final /* synthetic */ int e;

    public /* synthetic */ i83(int i) {
        this.e = i;
    }

    @Override // defpackage.r02
    public final q02 a() {
        return m02.e();
    }

    @Override // org.chromium.chrome.browser.omnibox.g.d
    public final void c(String str, int i, boolean z) {
        Object obj = SearchActivity.q0;
    }

    @Override // org.chromium.ui.modelutil.o.a
    public final int f(Object obj) {
        return ((xa1) obj).a;
    }

    @Override // org.chromium.ui.modelutil.n.a
    public final void h(Object obj, Object obj2) {
        oe3 oe3Var = (oe3) obj;
        j.a(((xa1) obj2).b, oe3Var.e, oe3Var.y, true);
    }

    @Override // org.chromium.content.browser.webcontents.WebContentsImpl.d
    public final Object i(WebContentsImpl webContentsImpl) {
        switch (this.e) {
            case 21:
                return new u31(webContentsImpl);
            default:
                return new kt3(webContentsImpl);
        }
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        int i = AutofillProfilesFragment.i0;
        PersonalDataManager.d().e("autofill.profile_enabled", ((Boolean) obj).booleanValue());
        return true;
    }

    @Override // org.chromium.chrome.browser.customtabs.a.f
    public final Object o(a.e eVar) {
        return eVar.j;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        switch (this.e) {
            case 9:
                int i = PreloadPagesSettingsFragment.k0;
                String str = preference.p;
                return N.MBM0bbSB();
            case 10:
                int i2 = SecureDnsSettings.j0;
                return N.M_qct0Io();
            case 11:
                int i3 = AdMeasurementFragmentV4.l0;
                if ("ad_measurement_toggle".equals(preference.p)) {
                    return oj2.k(Profile.d()).d("privacy_sandbox.m1.ad_measurement_enabled");
                }
                return false;
            case 12:
                int i4 = TopicsFragmentV4.r0;
                if ("topics_toggle".equals(preference.p)) {
                    return oj2.k(Profile.d()).d("privacy_sandbox.m1.topics_enabled");
                }
                return false;
            default:
                int i5 = SafeBrowsingSettingsFragment.l0;
                String str2 = preference.p;
                if ("managed_disclaimer_text".equals(str2) || "text_managed_legacy".equals(str2) || "safe_browsing_radio_button_group".equals(str2)) {
                    return N.MAU7_6Tq();
                }
                return false;
        }
    }

    @Override // org.chromium.ui.permissions.PermissionCallback
    public final void s(int[] iArr, String[] strArr) {
        aj3<to1> aj3Var = to1.g;
        kp1.a.a.getClass();
        if (strArr.length == 1 && iArr.length == 1 && strArr[0].equals("android.permission.POST_NOTIFICATIONS")) {
            oj2.O("Mobile.SystemNotification.Permission.OSPromptResult", iArr[0] == 0);
        }
    }
}
