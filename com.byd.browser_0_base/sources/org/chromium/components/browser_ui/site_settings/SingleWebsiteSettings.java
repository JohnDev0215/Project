package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import defpackage.a11;
import defpackage.es3;
import defpackage.ft1;
import defpackage.kr;
import defpackage.l51;
import defpackage.n3;
import defpackage.oo;
import defpackage.p20;
import defpackage.qj2;
import defpackage.ts3;
import defpackage.un2;
import defpackage.xn2;
import defpackage.xy;
import defpackage.yn2;
import defpackage.zn2;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreferenceSecondary;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.browser_ui.site_settings.d;
import org.chromium.components.browser_ui.site_settings.g;
import org.chromium.components.browser_ui.site_settings.g.b;
import org.chromium.components.browser_ui.site_settings.g.b.C0126g;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.e;

/* JADX INFO: loaded from: classes2.dex */
public class SingleWebsiteSettings extends SiteSettingsPreferenceFragment implements Preference.c, Preference.d, p20 {
    public static final String[] u0 = {"site_heading", "site_title", "site_usage", "related_sites_header", "related_sites", "site_permissions", "clear_data"};
    public boolean i0;
    public int k0;
    public a l0;
    public f m0;
    public int n0;
    public int o0;
    public Integer p0;
    public HashMap q0;
    public AlertDialog r0;
    public int s0;
    public int j0 = -1;
    public final es3 t0 = new es3(4, this);

    public interface a {
    }

    public class b implements g.c {
        public final ts3 e;

        public b(ts3 ts3Var) {
            this.e = ts3Var;
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            SingleWebsiteSettings singleWebsiteSettings = SingleWebsiteSettings.this;
            if (singleWebsiteSettings.D() == null) {
                return;
            }
            singleWebsiteSettings.m0 = SingleWebsiteSettings.U0(this.e, collection);
            singleWebsiteSettings.O0();
        }
    }

    public static Bundle M0(String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("org.chromium.chrome.preferences.site_address", ts3.e(ft1.c(str).toString()));
        return bundle;
    }

    public static String R0(int i) {
        if (i == 0) {
            return "cookies_permission_list";
        }
        if (i == 15) {
            return "protected_media_identifier_permission_list";
        }
        if (i == 21) {
            return "background_sync_permission_list";
        }
        if (i == 25) {
            return "ads_permission_list";
        }
        if (i == 30) {
            return "sound_permission_list";
        }
        if (i == 32) {
            return "sensors_permission_list";
        }
        if (i == 38) {
            return "idle_detection_permission_list";
        }
        if (i == 43) {
            return "bluetooth_scanning_permission_list";
        }
        if (i == 50) {
            return "nfc_permission_list";
        }
        if (i == 52) {
            return "clipboard_permission_list";
        }
        if (i == 2) {
            return "javascript_permission_list";
        }
        if (i == 3) {
            return "popup_permission_list";
        }
        if (i == 4) {
            return "location_access_list";
        }
        if (i == 5) {
            return "push_notifications_list";
        }
        if (i == 8) {
            return "microphone_permission_list";
        }
        if (i == 9) {
            return "camera_permission_list";
        }
        if (i == 12) {
            return "automatic_downloads_permission_list";
        }
        if (i == 13) {
            return "midi_sysex_permission_list";
        }
        if (i == 55) {
            return "vr_permission_list";
        }
        if (i == 56) {
            return "ar_permission_list";
        }
        switch (i) {
            case 72:
                return "auto_dark_web_content_permission_list";
            case 73:
                return "request_desktop_site_permission_list";
            case 74:
                return "federated_identity_api_list";
            default:
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x004b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.components.browser_ui.site_settings.f U0(defpackage.ts3 r10, java.util.Collection<org.chromium.components.browser_ui.site_settings.f> r11) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings.U0(ts3, java.util.Collection):org.chromium.components.browser_ui.site_settings.f");
    }

    public final ChromeImageViewPreference N0(ChromeSwitchPreferenceSecondary chromeSwitchPreferenceSecondary, String str, Integer num) {
        ChromeImageViewPreference chromeImageViewPreference = new ChromeImageViewPreference(chromeSwitchPreferenceSecondary.e);
        chromeImageViewPreference.M(chromeSwitchPreferenceSecondary.p);
        W0(chromeImageViewPreference, num);
        chromeImageViewPreference.R(str);
        if (Q0(chromeImageViewPreference.p) == this.j0) {
            int i = this.k0;
            Integer num2 = chromeImageViewPreference.W;
            if (num2 == null || num2.intValue() != i) {
                chromeImageViewPreference.W = Integer.valueOf(i);
                chromeImageViewPreference.a0();
            }
        }
        return chromeImageViewPreference;
    }

    /* JADX WARN: Removed duplicated region for block: B:157:0x03df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void O0() {
        /*
            Method dump skipped, instruction units count: 1153
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings.O0():void");
    }

    public final Drawable P0(int i, Integer num) {
        Context contextK = K();
        Drawable drawableB = qj2.b(contextK, xy.c(i, this.h0).a);
        return (num == null || num.intValue() != 2) ? drawableB : xy.a(contextK.getResources(), drawableB);
    }

    public final int Q0(String str) {
        if (this.q0 == null) {
            this.q0 = new HashMap();
            for (int i = 0; i < 87; i++) {
                String strR0 = R0(i);
                if (strR0 != null) {
                    this.q0.put(strR0, Integer.valueOf(i));
                }
            }
        }
        Integer num = (Integer) this.q0.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public final boolean S0() {
        if (this.n0 > 0 || this.o0 > 0) {
            return true;
        }
        PreferenceScreen preferenceScreenI0 = I0();
        for (int i = 0; i < preferenceScreenI0.b0(); i++) {
            if (Q0(preferenceScreenI0.a0(i).p) != -1) {
                return true;
            }
        }
        return false;
    }

    public final void T0(Preference preference) {
        if (this.m0.k(5)) {
            this.m0.m(((kr) this.h0).b, 5, 2);
        }
        zn2 zn2Var = this.h0;
        String strH = this.m0.e.h();
        ((kr) zn2Var).getClass();
        String strB = xn2.a.a.b(strH);
        Context context = preference.e;
        this.p0 = this.m0.d(((kr) this.h0).b, 5);
        Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.CHANNEL_ID", strB);
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        G0(intent, 1);
    }

    public final void V0(String str) {
        Preference preferenceF = f(str);
        if (preferenceF != null) {
            I0().d0(preferenceF);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void W0(androidx.preference.Preference r7, java.lang.Integer r8) {
        /*
            r6 = this;
            java.lang.String r0 = r7.p
            int r0 = r6.Q0(r0)
            zn2 r1 = r6.h0
            xy$a r1 = defpackage.xy.c(r0, r1)
            int r1 = r1.b
            if (r1 == 0) goto L13
            r7.S(r1)
        L13:
            zn2 r1 = r6.h0
            kr r1 = (defpackage.kr) r1
            org.chromium.chrome.browser.profiles.Profile r1 = r1.b
            r2 = 0
            r3 = r2
        L1b:
            r4 = 29
            if (r3 >= r4) goto L2d
            int r4 = defpackage.yn2.b(r3)
            if (r4 != r0) goto L2a
            yn2 r1 = defpackage.yn2.c(r1, r3)
            goto L2e
        L2a:
            int r3 = r3 + 1
            goto L1b
        L2d:
            r1 = 0
        L2e:
            r3 = 1
            if (r1 == 0) goto L5e
            if (r8 == 0) goto L5e
            int r4 = r8.intValue()
            r5 = 2
            if (r4 == r5) goto L5e
            androidx.fragment.app.FragmentActivity r4 = r6.D()
            boolean r5 = r1.e()
            if (r5 == 0) goto L4c
            boolean r1 = r1.d(r4)
            if (r1 == 0) goto L4c
            r1 = r3
            goto L4d
        L4c:
            r1 = r2
        L4d:
            if (r1 != 0) goto L5e
            android.content.Context r8 = r6.K()
            android.graphics.drawable.Drawable r8 = defpackage.yn2.g(r8)
            r7.L(r8)
            r7.J(r2)
            goto L65
        L5e:
            android.graphics.drawable.Drawable r8 = r6.P0(r0, r8)
            r7.L(r8)
        L65:
            r7.w = r2
            int r8 = r6.s0
            int r8 = r8 + r3
            r6.s0 = r8
            r7.N(r8)
            androidx.preference.PreferenceScreen r6 = r6.I0()
            r6.Y(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings.W0(androidx.preference.Preference, java.lang.Integer):void");
    }

    public final boolean X0(ChromeSwitchPreferenceSecondary chromeSwitchPreferenceSecondary, int i, int i2, Integer num) {
        String string;
        String string2;
        ft1 ft1VarB = ft1.b(this.m0.e.h());
        if (ft1VarB == null) {
            return false;
        }
        ((kr) this.h0).getClass();
        if (i2 == 5) {
            a11 a11Var = org.chromium.chrome.browser.browserservices.permissiondelegation.a.a().a;
            a11Var.getClass();
            string = a11Var.a.getString(a11.b(ft1VarB), null);
        } else {
            string = null;
        }
        if (string == null) {
            return false;
        }
        ((kr) this.h0).getClass();
        if (i2 == 5) {
            a11 a11Var2 = org.chromium.chrome.browser.browserservices.permissiondelegation.a.a().a;
            a11Var2.getClass();
            string2 = a11Var2.a.getString(a11.c(ft1VarB), null);
        } else {
            string2 = null;
        }
        final Intent intent = new Intent();
        if (i2 == 5) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", string2);
        } else {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + string2));
        }
        ChromeImageViewPreference chromeImageViewPreferenceN0 = N0(chromeSwitchPreferenceSecondary, P(R$string.website_setting_managed_by_app, string), num);
        chromeImageViewPreferenceN0.Z(R$drawable.permission_popups, i, null);
        if (chromeImageViewPreferenceN0.Y) {
            chromeImageViewPreferenceN0.Y = false;
            chromeImageViewPreferenceN0.Y();
        }
        chromeImageViewPreferenceN0.setOnPreferenceClickListener(new Preference.d() { // from class: rn2
            @Override // androidx.preference.Preference.d
            public final boolean r(Preference preference) {
                String[] strArr = SingleWebsiteSettings.u0;
                this.e.F0(intent);
                return true;
            }
        });
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        D().setTitle(K().getString(R$string.prefs_site_settings));
        if (this.h0 != null) {
            Serializable serializable = this.j.getSerializable("org.chromium.chrome.preferences.site");
            Serializable serializable2 = this.j.getSerializable("org.chromium.chrome.preferences.site_address");
            if (serializable != null && serializable2 == null) {
                this.m0 = (f) serializable;
                O0();
            } else if (serializable2 != null && serializable == null) {
                g gVar = new g(((kr) this.h0).b, false);
                b bVar = new b((ts3) serializable2);
                g.b bVar2 = gVar.new b();
                g.b.i iVar = new g.b.i();
                bVar2.a(iVar);
                iVar.add(bVar2.new C0126g(bVar));
                iVar.b();
            }
            this.b0.setItemAnimator(null);
        } else {
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(M());
            aVar.k(this);
            aVar.g();
        }
        this.H = true;
    }

    public final void Y0(ChromeSwitchPreferenceSecondary chromeSwitchPreferenceSecondary, Integer num, boolean z) {
        if (num == null) {
            return;
        }
        W0(chromeSwitchPreferenceSecondary, num);
        chromeSwitchPreferenceSecondary.Y(num.intValue() == 1);
        chromeSwitchPreferenceSecondary.R(z ? O(R$string.automatically_blocked) : O(xy.b(num.intValue())));
        chromeSwitchPreferenceSecondary.setOnPreferenceChangeListener(this);
        if (Q0(chromeSwitchPreferenceSecondary.p) == this.j0) {
            int i = this.k0;
            Integer num2 = chromeSwitchPreferenceSecondary.c0;
            if (num2 == null || num2.intValue() != i) {
                chromeSwitchPreferenceSecondary.c0 = Integer.valueOf(i);
                chromeSwitchPreferenceSecondary.c0();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        f fVar;
        if (I0() == null || (fVar = this.m0) == null || i != 1) {
            return;
        }
        int iIntValue = fVar.d(((kr) this.h0).b, 5).intValue();
        Preference preferenceF = f(R0(5));
        if (preferenceF != null) {
            n(preferenceF, Integer.valueOf(iIntValue));
        }
        if (this.p0.intValue() != 1 || iIntValue == 1) {
            return;
        }
        N.M$1c3w6w(((kr) this.h0).b, this.m0.e.h(), iIntValue);
        this.p0 = null;
    }

    public final boolean Z0(int i) {
        Profile profile = ((kr) this.h0).b;
        Integer numD = this.m0.d(profile, yn2.b(i));
        if (numD == null || numD.intValue() == 2) {
            return false;
        }
        return yn2.c(profile, i).n(K());
    }

    @Override // org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment, androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        super.g0();
        AlertDialog alertDialog = this.r0;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (this.f12J == null) {
            return true;
        }
        Profile profile = ((kr) this.h0).b;
        int iQ0 = Q0(preference.p);
        if (iQ0 == -1) {
            return false;
        }
        int iIntValue = obj instanceof Boolean ? ((Boolean) obj).booleanValue() ? 1 : 2 : ((Integer) obj).intValue();
        this.m0.m(profile, iQ0, iIntValue);
        preference.R(O(xy.b(iIntValue)));
        preference.L(P0(iQ0, Integer.valueOf(iIntValue)));
        a aVar = this.l0;
        if (aVar != null) {
            org.chromium.components.page_info.e eVar = (org.chromium.components.page_info.e) aVar;
            if (eVar.m != -1) {
                eVar.o.a(3);
            }
            ((PageInfoController) eVar.g).f(5);
            eVar.l = true;
        }
        return true;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        Integer num = this.p0;
        if (num != null) {
            bundle.putInt("previous_notification_permission", num.intValue());
        }
        super.n0(bundle);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = 1;
        if (this.i0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(K(), R$style.ThemeOverlay_BrowserUI_AlertDialog);
            builder.i(R$string.page_info_permissions_reset_dialog_title);
            builder.a.f = P(R$string.page_info_permissions_reset_confirmation, this.m0.e.h);
            builder.f(R$string.reset, new DialogInterface.OnClickListener() { // from class: pn2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    String[] strArr = SingleWebsiteSettings.u0;
                    SingleWebsiteSettings singleWebsiteSettings = this.e;
                    d.c(((kr) singleWebsiteSettings.h0).b, singleWebsiteSettings.m0);
                    SingleWebsiteSettings.a aVar = singleWebsiteSettings.l0;
                    if (aVar != null) {
                        e eVar = (e) aVar;
                        PageInfoController pageInfoController = (PageInfoController) eVar.g;
                        pageInfoController.f(15);
                        eVar.l = true;
                        pageInfoController.d();
                    }
                }
            });
            builder.e(R$string.cancel, new oo(i, this));
            this.r0 = builder.k();
        } else {
            Dialog dialog = new Dialog(K(), R$style.Dialog_Style);
            View viewInflate = D().getLayoutInflater().inflate(R$layout.clear_reset_dialog, (ViewGroup) null);
            ((TextView) viewInflate.findViewById(R$id.main_message)).setText(R$string.website_reset_confirmation);
            ((TextView) viewInflate.findViewById(R$id.signed_out_text)).setText(R$string.webstorage_clear_data_dialog_sign_out_message);
            ((TextView) viewInflate.findViewById(R$id.offline_text)).setText(R$string.webstorage_clear_data_dialog_offline_message);
            if (((kr) this.h0).c()) {
                ((TextView) viewInflate.findViewById(R$id.ad_personalization_text)).setVisibility(0);
            }
            Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
            Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
            LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
            if (n3.Q0()) {
                linearLayout.setLayoutDirection(1);
            } else {
                linearLayout.setLayoutDirection(0);
            }
            dialog.setContentView(viewInflate);
            Window window = dialog.getWindow();
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
            attributes.height = -2;
            window.setAttributes(attributes);
            dialog.show();
            button.setOnClickListener(new c(this));
            button2.setOnClickListener(new un2(dialog));
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void r0(Bundle bundle) {
        this.H = true;
        if (bundle != null && bundle.containsKey("previous_notification_permission")) {
            this.p0 = Integer.valueOf(bundle.getInt("previous_notification_permission"));
        }
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.a
    public final void y(Preference preference) {
        if (!(preference instanceof ClearWebsiteStorage)) {
            super.y(preference);
        } else {
            if (this.v.L()) {
                return;
            }
            ClearWebsiteStorageDialog clearWebsiteStorageDialogS0 = ClearWebsiteStorageDialog.S0(preference, new l51(8, this), ((kr) this.h0).c(), false);
            clearWebsiteStorageDialogS0.E0(0, this);
            clearWebsiteStorageDialogS0.M0(this.v, "ClearWebsiteStorageDialog");
        }
    }

    @Override // defpackage.p20
    public final void t() {
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
    }
}
