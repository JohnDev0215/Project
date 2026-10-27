package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0179d;
import defpackage.bk3;
import defpackage.bq;
import defpackage.dt3;
import defpackage.fn2;
import defpackage.gn2;
import defpackage.hn2;
import defpackage.ii2;
import defpackage.kr;
import defpackage.mb1;
import defpackage.n3;
import defpackage.nb1;
import defpackage.no;
import defpackage.oj2;
import defpackage.p20;
import defpackage.ph1;
import defpackage.pp0;
import defpackage.qj2;
import defpackage.sk;
import defpackage.xn2;
import defpackage.xy;
import defpackage.yn2;
import defpackage.zn2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreferenceSecondary;
import org.chromium.components.browser_ui.settings.ExpandablePreferenceGroup;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.site_settings.AddExceptionPreference;
import org.chromium.components.browser_ui.site_settings.FourStateCookieSettingsPreference;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.browser_ui.site_settings.TriStateCookieSettingsPreference;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.browser_ui.site_settings.g;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;
import org.chromium.components.prefs.PrefService;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: loaded from: classes2.dex */
@UsedByReflection
public class SingleCategorySettings extends SiteSettingsPreferenceFragment implements Preference.c, Preference.d, AddExceptionPreference.a, pp0, FourStateCookieSettingsPreference.b, TriStateCookieSettingsPreference.a, p20 {
    public static final /* synthetic */ int w0 = 0;
    public SettingsLauncher i0;
    public RecyclerView j0;
    public yn2 k0;
    public boolean l0;
    public boolean m0;
    public boolean o0;
    public int q0;
    public ChromeSwitchPreferenceSecondary s0;
    public ChromeBaseCheckBoxPreference t0;
    public ChromeBaseCheckBoxPreference u0;
    public HashSet v0;
    public boolean n0 = true;
    public boolean p0 = true;
    public int r0 = 0;

    public class a implements g.c {
        public a() {
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            Set setEmptySet;
            int i;
            SingleCategorySettings singleCategorySettings = SingleCategorySettings.this;
            if (singleCategorySettings.D() == null) {
                return;
            }
            singleCategorySettings.S0();
            int i2 = singleCategorySettings.k0.b;
            int i3 = 0;
            if (i2 == 26 || i2 == 27) {
                ArrayList arrayList = new ArrayList();
                boolean z = i2 == 26;
                for (f fVar : collection) {
                    if (fVar.l() == z) {
                        arrayList.add(fVar);
                    }
                }
                collection = arrayList;
            }
            int iB = yn2.b(singleCategorySettings.k0.b);
            if ((iB != 20 ? iB != 35 ? (byte) -1 : (byte) 19 : (byte) 51) != -1) {
                HashMap map = new HashMap();
                for (f fVar2 : collection) {
                    fVar2.getClass();
                    for (no noVar : new ArrayList(fVar2.m)) {
                        Pair pairCreate = (Pair) map.get(noVar.h);
                        if (pairCreate == null) {
                            pairCreate = Pair.create(new ArrayList(), new ArrayList());
                            map.put(noVar.h, pairCreate);
                        }
                        ((ArrayList) pairCreate.first).add(noVar);
                        ((ArrayList) pairCreate.second).add(fVar2);
                    }
                }
                singleCategorySettings.V0(0);
                singleCategorySettings.U0(0, true);
                singleCategorySettings.X0(0);
                for (Pair pair : map.values()) {
                    Preference preference = new Preference(singleCategorySettings.a0.a);
                    Bundle bundleM = preference.m();
                    bundleM.putInt("org.chromium.chrome.preferences.content_settings_type", singleCategorySettings.k0.f());
                    bundleM.putString("title", singleCategorySettings.D().getTitle().toString());
                    bundleM.putSerializable("org.chromium.chrome.preferences.object_infos", (Serializable) pair.first);
                    bundleM.putSerializable("org.chromium.chrome.preferences.site_set", (Serializable) pair.second);
                    preference.L(qj2.b(singleCategorySettings.K(), xy.c(singleCategorySettings.k0.f(), singleCategorySettings.h0).a));
                    preference.T(((no) ((ArrayList) pair.first).get(0)).g);
                    preference.r = ChosenObjectSettings.class.getCanonicalName();
                    singleCategorySettings.I0().Y(preference);
                }
                map.size();
                return;
            }
            if (singleCategorySettings.v0 != null) {
                Iterator<f> it = collection.iterator();
                while (it.hasNext()) {
                    if (!singleCategorySettings.v0.contains(bk3.b(it.next().e.h(), true))) {
                        it.remove();
                    }
                }
            }
            ArrayList<dt3> arrayList2 = new ArrayList();
            hn2 hn2Var = new hn2(singleCategorySettings, ((kr) singleCategorySettings.h0).a());
            Iterator<f> it2 = collection.iterator();
            while (it2.hasNext()) {
                dt3 dt3Var = new dt3(singleCategorySettings.a0.a, singleCategorySettings.h0, it2.next(), singleCategorySettings.k0);
                arrayList2.add(dt3Var);
                dt3Var.S = hn2Var;
                nb1.c(hn2Var, dt3Var, false, true);
            }
            singleCategorySettings.q0 = 0;
            if (arrayList2.size() != 0) {
                int i4 = singleCategorySettings.k0.b;
                if ((i4 == 28 || (i4 == 24 && !N.Mudil8Bg("RequestDesktopSiteExceptions") && N.ManEQDnV("RequestDesktopSiteExceptionsDowngrade"))) ? false : true) {
                    Collections.sort(arrayList2);
                    if (singleCategorySettings.l0) {
                        PreferenceGroup preferenceGroup = (PreferenceGroup) singleCategorySettings.I0().Z("allowed_group");
                        PreferenceGroup preferenceGroup2 = (PreferenceGroup) singleCategorySettings.I0().Z("blocked_group");
                        PreferenceGroup preferenceGroup3 = (PreferenceGroup) singleCategorySettings.I0().Z("managed_group");
                        if (singleCategorySettings.k0.b == 14) {
                            ((kr) singleCategorySettings.h0).getClass();
                            setEmptySet = org.chromium.chrome.browser.browserservices.permissiondelegation.a.a().a.g();
                        } else {
                            setEmptySet = Collections.emptySet();
                        }
                        i = 0;
                        int i5 = 0;
                        for (dt3 dt3Var2 : arrayList2) {
                            if (setEmptySet.contains(dt3Var2.c0.e.h())) {
                                preferenceGroup3.Y(dt3Var2);
                                i++;
                            } else {
                                Integer numD = dt3Var2.c0.d(((kr) singleCategorySettings.h0).b, singleCategorySettings.k0.f());
                                if (numD != null && 2 == numD.intValue()) {
                                    preferenceGroup2.Y(dt3Var2);
                                    i5++;
                                } else {
                                    preferenceGroup.Y(dt3Var2);
                                    singleCategorySettings.q0++;
                                }
                            }
                        }
                        if (singleCategorySettings.k0.b == 1) {
                            preferenceGroup2.N(preferenceGroup.k + 1);
                        }
                        if (singleCategorySettings.p0) {
                            if (singleCategorySettings.q0 == 0) {
                                if (i5 != 0 || i <= 0) {
                                    singleCategorySettings.m0 = true;
                                } else {
                                    singleCategorySettings.o0 = true;
                                }
                            }
                            singleCategorySettings.p0 = false;
                        }
                        if (!singleCategorySettings.m0) {
                            preferenceGroup2.c0();
                        }
                        if (!singleCategorySettings.n0) {
                            preferenceGroup.c0();
                        }
                        if (!singleCategorySettings.o0) {
                            preferenceGroup3.c0();
                        }
                        i3 = i5;
                    } else {
                        Iterator it3 = arrayList2.iterator();
                        while (it3.hasNext()) {
                            singleCategorySettings.I0().Y((dt3) it3.next());
                        }
                        i = 0;
                    }
                    singleCategorySettings.V0(i3);
                    singleCategorySettings.U0(singleCategorySettings.q0, !singleCategorySettings.Q0());
                    singleCategorySettings.X0(i);
                    arrayList2.size();
                    return;
                }
            }
            singleCategorySettings.V0(0);
            singleCategorySettings.U0(0, true);
            singleCategorySettings.X0(0);
        }
    }

    public class b extends ph1 {
        public b(mb1 mb1Var) {
            super(mb1Var);
        }

        @Override // defpackage.ph1, defpackage.mb1
        public final boolean b(Preference preference) {
            return SingleCategorySettings.this.k0.l();
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            SingleCategorySettings singleCategorySettings = SingleCategorySettings.this;
            return singleCategorySettings.k0.k() && !singleCategorySettings.k0.l();
        }
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.c
    public final boolean C(Preference preference) throws Exception {
        if (I0().Z("binary_toggle") != null && this.k0.k()) {
            T0();
            return false;
        }
        if (preference instanceof dt3) {
            dt3 dt3Var = (dt3) preference;
            mb1 mb1Var = dt3Var.S;
            if (mb1Var != null && (mb1Var.r(dt3Var) || dt3Var.S.b(dt3Var))) {
                T0();
                return false;
            }
            boolean zEquals = dt3Var.N.p.equals("managed_group");
            final f fVar = dt3Var.c0;
            if (zEquals) {
                dt3Var.r = SingleWebsiteSettings.class.getName();
                dt3Var.m().putSerializable("org.chromium.chrome.preferences.site_address", fVar.e);
                dt3Var.m().putInt("org.chromium.chrome.preferences.navigation_source", this.j.getInt("org.chromium.chrome.preferences.navigation_source", 0));
            } else {
                yn2 yn2Var = this.k0;
                if (yn2Var.b == 14) {
                    zn2 zn2Var = this.h0;
                    String strH = fVar.e.h();
                    ((kr) zn2Var).getClass();
                    String strB = xn2.a.a.b(strH);
                    Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
                    intent.putExtra("android.provider.extra.CHANNEL_ID", strB);
                    intent.putExtra("android.provider.extra.APP_PACKAGE", preference.e.getPackageName());
                    G0(intent, 1);
                } else {
                    final Profile profile = ((kr) this.h0).b;
                    final int iF = yn2Var.f();
                    Integer numD = fVar.d(profile, iF);
                    final Dialog dialog = new Dialog(K(), R$style.Dialog_Style);
                    LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) K().getSystemService("layout_inflater")).inflate(R$layout.edit_site_dialog_content, (ViewGroup) null);
                    ((TextView) linearLayout.findViewById(R$id.tv_title)).setText(R$string.website_settings_edit_site_dialog_title);
                    Button button = (Button) linearLayout.findViewById(R$id.btn_sure);
                    Button button2 = (Button) linearLayout.findViewById(R$id.btn_cancel);
                    LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R$id.ll_sure_cancel);
                    if (n3.Q0()) {
                        linearLayout2.setLayoutDirection(1);
                    } else {
                        linearLayout2.setLayoutDirection(0);
                    }
                    ((TextView) linearLayout.findViewById(R$id.message)).setText(K().getString(R$string.website_settings_edit_site_dialog_description, fVar.g()));
                    RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) linearLayout.findViewById(R$id.radio_button_group);
                    final RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) radioButtonWithDescriptionLayout.findViewById(R$id.allow);
                    radioButtonWithDescription.setPrimaryText(O(xy.d(iF, 1)));
                    RadioButtonWithDescription radioButtonWithDescription2 = (RadioButtonWithDescription) radioButtonWithDescriptionLayout.findViewById(R$id.block);
                    radioButtonWithDescription2.setPrimaryText(O(xy.d(iF, 2)));
                    if (numD.intValue() == 1) {
                        radioButtonWithDescription.setChecked(true);
                    } else {
                        radioButtonWithDescription2.setChecked(true);
                    }
                    radioButtonWithDescriptionLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: cn2
                        @Override // android.widget.RadioGroup.OnCheckedChangeListener
                        public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                            int i2 = SingleCategorySettings.w0;
                            SingleCategorySettings singleCategorySettings = this.e;
                            singleCategorySettings.getClass();
                            int i3 = radioButtonWithDescription.b() ? 1 : 2;
                            BrowserContextHandle browserContextHandle = profile;
                            int i4 = iF;
                            f fVar2 = fVar;
                            fVar2.m(browserContextHandle, i4, i3);
                            if (singleCategorySettings.k0.b == 24) {
                                boolean z = i3 == 1;
                                if (fVar2.e.e.startsWith("[*.]")) {
                                    oj2.O("Android.RequestDesktopSite.DomainSettingChanged", z);
                                } else {
                                    oj2.O("Android.RequestDesktopSite.SubDomainSettingChanged", z);
                                }
                            }
                            singleCategorySettings.P0();
                            dialog.dismiss();
                        }
                    });
                    dialog.getWindow().setContentView(linearLayout);
                    Window window = dialog.getWindow();
                    window.setGravity(17);
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
                    attributes.height = -2;
                    window.setAttributes(attributes);
                    button.setOnClickListener(new fn2(this, fVar, profile, iF, dialog));
                    button2.setOnClickListener(new gn2(dialog));
                    dialog.show();
                    if (this.k0.b == 24) {
                        n3.X0("DesktopSiteContentSetting.SettingsPage.SiteException.Opened");
                    }
                }
            }
        }
        return super.C(preference);
    }

    public final void M0() {
        int iF = this.k0.f();
        PreferenceScreen preferenceScreenI0 = I0();
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) preferenceScreenI0.Z("binary_toggle");
        TriStateSiteSettingsPreference triStateSiteSettingsPreference = (TriStateSiteSettingsPreference) preferenceScreenI0.Z("tri_state_toggle");
        TriStateCookieSettingsPreference triStateCookieSettingsPreference = (TriStateCookieSettingsPreference) preferenceScreenI0.Z("tri_state_cookie_toggle");
        FourStateCookieSettingsPreference fourStateCookieSettingsPreference = (FourStateCookieSettingsPreference) preferenceScreenI0.Z("four_state_cookie_toggle");
        Preference preferenceZ = preferenceScreenI0.Z("notifications_vibrate");
        this.s0 = (ChromeSwitchPreferenceSecondary) preferenceScreenI0.Z("notifications_quiet_ui");
        this.t0 = (ChromeBaseCheckBoxPreference) preferenceScreenI0.Z("desktop_site_peripheral");
        this.u0 = (ChromeBaseCheckBoxPreference) preferenceScreenI0.Z("desktop_site_display");
        Preference preferenceZ2 = preferenceScreenI0.Z("protected_content_learn_more");
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreenI0.Z("allowed_group");
        PreferenceGroup preferenceGroup2 = (PreferenceGroup) preferenceScreenI0.Z("blocked_group");
        PreferenceGroup preferenceGroup3 = (PreferenceGroup) preferenceScreenI0.Z("managed_group");
        boolean zN = this.k0.n(K());
        if (this.r0 != 0) {
            preferenceScreenI0.d0(chromeSwitchPreference);
        }
        if (this.r0 != 1) {
            preferenceScreenI0.d0(triStateSiteSettingsPreference);
        }
        if (this.r0 != 2) {
            preferenceScreenI0.d0(triStateCookieSettingsPreference);
        }
        if (this.r0 != 3) {
            preferenceScreenI0.d0(fourStateCookieSettingsPreference);
        }
        int i = this.r0;
        if (i == 0) {
            chromeSwitchPreference.setOnPreferenceChangeListener(this);
            chromeSwitchPreference.S(xy.c(iF, this.h0).b);
            Profile profile = ((kr) this.h0).b;
            int i2 = this.k0.b;
            Context context = chromeSwitchPreference.e;
            if (i2 == 9 && N.M__mL5j3(profile)) {
                chromeSwitchPreference.T = context.getString(R$string.website_settings_category_allowed);
                if (chromeSwitchPreference.S) {
                    chromeSwitchPreference.s();
                }
            } else {
                xy.a aVarC = xy.c(iF, this.h0);
                int iB = aVarC.e;
                if (iB == 0) {
                    iB = xy.b(aVarC.c.intValue());
                }
                chromeSwitchPreference.T = context.getString(iB);
                if (chromeSwitchPreference.S) {
                    chromeSwitchPreference.s();
                }
            }
            xy.a aVarC2 = xy.c(iF, this.h0);
            int iB2 = aVarC2.f;
            if (iB2 == 0) {
                iB2 = xy.b(aVarC2.d.intValue());
            }
            chromeSwitchPreference.U = context.getString(iB2);
            if (!chromeSwitchPreference.S) {
                chromeSwitchPreference.s();
            }
            chromeSwitchPreference.b0(new b(((kr) this.h0).a()));
            chromeSwitchPreference.Y(N.MJSt3Ocq(profile, iF));
        } else if (i == 1) {
            triStateSiteSettingsPreference.setOnPreferenceChangeListener(this);
            int iM7ddkyN4 = N.M7ddkyN4(((kr) this.h0).b, iF);
            int[] iArr = iF == 15 ? new int[]{R$string.website_settings_category_protected_content_allowed_recommended, R$string.website_settings_category_protected_content_ask, R$string.website_settings_category_protected_content_blocked} : null;
            triStateSiteSettingsPreference.S = iM7ddkyN4;
            triStateSiteSettingsPreference.T = iArr;
        } else if (i == 2) {
            triStateCookieSettingsPreference.setOnPreferenceChangeListener(this);
            triStateCookieSettingsPreference.S = this;
            TriStateCookieSettingsPreference.b bVar = new TriStateCookieSettingsPreference.b();
            bVar.b = N0();
            bVar.d = this.k0.k();
            ((kr) this.h0).getClass();
            bVar.c = N.M$3vpOHw();
            bVar.a = ((kr) this.h0).b();
            ((kr) this.h0).getClass();
            bVar.e = N.MhilDEgf();
            if (triStateCookieSettingsPreference.X != null) {
                triStateCookieSettingsPreference.b0(bVar);
                triStateCookieSettingsPreference.Y(bVar);
            } else {
                triStateCookieSettingsPreference.T = bVar;
            }
        } else if (i == 3) {
            fourStateCookieSettingsPreference.setOnPreferenceChangeListener(this);
            fourStateCookieSettingsPreference.S = this;
            FourStateCookieSettingsPreference.c cVar = new FourStateCookieSettingsPreference.c();
            cVar.b = N.MJSt3Ocq(((kr) this.h0).b, 0);
            PrefService prefServiceK = oj2.k(((kr) this.h0).b);
            cVar.c = N0();
            cVar.e = this.k0.k();
            cVar.f = prefServiceK.d("profile.cookie_controls_mode");
            ((kr) this.h0).getClass();
            cVar.d = N.M$3vpOHw();
            cVar.a = ((kr) this.h0).b();
            ((kr) this.h0).getClass();
            cVar.g = N.MhilDEgf();
            if (fourStateCookieSettingsPreference.Y != null) {
                fourStateCookieSettingsPreference.b0(cVar);
                fourStateCookieSettingsPreference.Y(cVar);
            } else {
                fourStateCookieSettingsPreference.T = cVar;
            }
        }
        Preference preferenceZ3 = preferenceScreenI0.Z("info_text");
        int i3 = this.k0.b;
        if (i3 == 8) {
            preferenceZ3.Q(R$string.website_settings_cookie_info);
        } else if (i3 == 27) {
            preferenceZ3.Q(R$string.website_settings_site_data_page_description);
        } else if (i3 == 26) {
            preferenceZ3.Q(R$string.website_settings_third_party_cookies_page_description);
        } else {
            preferenceScreenI0.d0(preferenceZ3);
        }
        if (this.k0.b != 28) {
            Preference preferenceZ4 = preferenceScreenI0.Z("anti_abuse_when_on_header");
            Preference preferenceZ5 = preferenceScreenI0.Z("anti_abuse_when_on_section_one");
            Preference preferenceZ6 = preferenceScreenI0.Z("anti_abuse_when_on_section_two");
            Preference preferenceZ7 = preferenceScreenI0.Z("anti_abuse_things_to_consider_header");
            Preference preferenceZ8 = preferenceScreenI0.Z("anti_abuse_things_to_consider_section_one");
            preferenceScreenI0.d0(preferenceZ4);
            preferenceScreenI0.d0(preferenceZ5);
            preferenceScreenI0.d0(preferenceZ6);
            preferenceScreenI0.d0(preferenceZ7);
            preferenceScreenI0.d0(preferenceZ8);
        }
        if (zN) {
            if (!Q0()) {
                ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.a0.a, null);
                int i4 = R$layout.preference_settings_no_padding;
                chromeBasePreference.f17J = i4;
                ChromeBasePreference chromeBasePreference2 = new ChromeBasePreference(this.a0.a, null);
                chromeBasePreference2.f17J = i4;
                this.k0.a(chromeBasePreference, chromeBasePreference2, K(), true, ((kr) this.h0).a.getString(org.chromium.chrome.R$string.app_name));
                if (chromeBasePreference.l != null) {
                    chromeBasePreference.M("os_permissions_warning");
                    preferenceScreenI0.Y(chromeBasePreference);
                }
                if (chromeBasePreference2.l != null) {
                    chromeBasePreference2.M("os_permissions_warning_extra");
                    preferenceScreenI0.Y(chromeBasePreference2);
                }
            }
            preferenceScreenI0.d0(preferenceZ);
            preferenceScreenI0.d0(this.s0);
            preferenceScreenI0.d0(this.t0);
            preferenceScreenI0.d0(this.u0);
            preferenceScreenI0.d0(preferenceZ2);
            preferenceScreenI0.d0(preferenceGroup);
            preferenceScreenI0.d0(preferenceGroup2);
            preferenceScreenI0.d0(preferenceGroup3);
            return;
        }
        if (this.k0.b == 14) {
            preferenceScreenI0.d0(preferenceZ);
            ((kr) this.h0).getClass();
            sk skVar = bq.a;
            if (N.M09VlOh_("QuietNotificationPrompts")) {
                this.s0.setOnPreferenceChangeListener(this);
            } else {
                preferenceScreenI0.d0(this.s0);
            }
            Y0();
        } else {
            preferenceScreenI0.d0(preferenceZ);
            preferenceScreenI0.d0(this.s0);
        }
        if (this.k0.b == 24 && N.Mudil8Bg("RequestDesktopSiteAdditions")) {
            this.t0.setOnPreferenceChangeListener(this);
            this.u0.setOnPreferenceChangeListener(this);
            W0();
        } else {
            preferenceScreenI0.d0(this.t0);
            preferenceScreenI0.d0(this.u0);
        }
        if (this.k0.b == 16) {
            this.h0.getClass();
            preferenceZ2.setOnPreferenceClickListener(new C0179d(4, this));
            this.j0.setFocusable(false);
        } else {
            preferenceScreenI0.d0(preferenceZ2);
            this.j0.setFocusable(true);
        }
        if (!this.l0) {
            this.m0 = false;
            this.n0 = true;
            this.o0 = false;
        }
        this.l0 = true;
        preferenceGroup.setOnPreferenceClickListener(this);
        preferenceGroup2.setOnPreferenceClickListener(this);
        preferenceGroup3.setOnPreferenceClickListener(this);
    }

    public final int N0() {
        return oj2.k(((kr) this.h0).b).b("profile.cookie_controls_mode");
    }

    public final SpannableStringBuilder O0(int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(O(i));
        String str = String.format(Locale.getDefault(), " - %d", Integer.valueOf(i2));
        spannableStringBuilder.append((CharSequence) str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(K().getColor(R$color.group_title_color)), 0, spannableStringBuilder.length() - str.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ii2.g(K(), org.chromium.components.browser_ui.styles.R$attr.colorOnSurfaceVariant)), spannableStringBuilder.length() - str.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public final void P0() {
        yn2 yn2Var = this.k0;
        if (yn2Var.e() && yn2Var.d(D())) {
            new g(((kr) this.h0).b, false).a(this.k0, new a());
        } else {
            S0();
        }
    }

    public final boolean Q0() {
        int i = this.r0;
        if (i != 0) {
            return i != 1 ? i != 2 ? i == 3 && ((FourStateCookieSettingsPreference) I0().Z("four_state_cookie_toggle")).a0() == FourStateCookieSettingsPreference.a.BLOCK : ((TriStateCookieSettingsPreference) I0().Z("tri_state_cookie_toggle")).a0().intValue() != 0 : ((TriStateSiteSettingsPreference) I0().Z("tri_state_toggle")).S == 2;
        }
        if (((ChromeSwitchPreference) I0().Z("binary_toggle")) != null) {
            return !r4.S;
        }
        return false;
    }

    public final void R0(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("cookie_page_state", i);
        this.i0.b(D(), FPSCookieSettings.class, bundle);
    }

    public final void S0() {
        int i;
        I0().c0();
        qj2.a(this, R$xml.website_preferences);
        M0();
        Profile profile = ((kr) this.h0).b;
        int iF = this.k0.f();
        int i2 = this.k0.b;
        boolean zMJSt3Ocq = true;
        int i3 = 0;
        if (i2 != 3 && i2 != 4) {
            if (i2 != 8 && i2 != 11 && i2 != 18) {
                switch (i2) {
                    case 24:
                        zMJSt3Ocq = N.Mudil8Bg("RequestDesktopSiteExceptions");
                        break;
                    case 25:
                    case 27:
                        break;
                    case 26:
                        if (N0() == 0) {
                        }
                    default:
                        zMJSt3Ocq = false;
                        break;
                }
            }
        } else {
            zMJSt3Ocq = true ^ N.MJSt3Ocq(profile, iF);
        }
        if (zMJSt3Ocq) {
            Context context = this.a0.a;
            Profile profile2 = ((kr) this.h0).b;
            int i4 = this.k0.b;
            if (i4 == 3) {
                i3 = R$string.website_settings_add_site_description_automatic_downloads;
            } else if (i4 != 4) {
                if (i4 == 8) {
                    i = ((FourStateCookieSettingsPreference) I0().Z("four_state_cookie_toggle")).a0() == FourStateCookieSettingsPreference.a.ALLOW ? R$string.website_settings_add_site_description_cookies_block : R$string.website_settings_add_site_description_cookies_allow;
                } else if (i4 == 11) {
                    i = N.MJSt3Ocq(profile2, 2) ? R$string.website_settings_add_site_description_javascript_block : R$string.website_settings_add_site_description_javascript_allow;
                } else if (i4 != 18) {
                    switch (i4) {
                        case 23:
                            i3 = R$string.website_settings_add_site_description_auto_dark_block;
                            break;
                        case 24:
                            i = !N.MJSt3Ocq(profile2, 73) ? R$string.website_settings_allowed_group_heading_request_desktop_site : R$string.website_settings_blocked_group_heading_request_desktop_site;
                            break;
                        case 25:
                            i = !N.MJSt3Ocq(profile2, 74) ? R$string.website_settings_add_site_description_federated_identity_allow : R$string.website_settings_add_site_description_federated_identity_block;
                            break;
                        case 26:
                            i = N0() != 0 ? R$string.website_settings_third_party_cookies_page_add_allow_exception_description : R$string.website_settings_third_party_cookies_page_add_block_exception_description;
                            break;
                        case 27:
                            i = !N.MJSt3Ocq(profile2, 0) ? R$string.website_settings_site_data_page_add_allow_exception_description : R$string.website_settings_site_data_page_add_block_exception_description;
                            break;
                    }
                } else {
                    i = N.MJSt3Ocq(profile2, 30) ? R$string.website_settings_add_site_description_sound_block : R$string.website_settings_add_site_description_sound_allow;
                }
                i3 = i;
            } else {
                i3 = R$string.website_settings_add_site_description_background_sync;
            }
            AddExceptionPreference addExceptionPreference = new AddExceptionPreference(context, O(i3), this.k0, this);
            addExceptionPreference.f17J = org.chromium.components.browser_ui.settings.R$layout.preference_settings_no_padding;
            I0().Y(addExceptionPreference);
        }
    }

    public final void T0() {
        if (this.k0.l()) {
            nb1.h(K(), ((kr) this.h0).a().e() ? org.chromium.components.browser_ui.settings.R$string.managed_by_your_parents : org.chromium.components.browser_ui.settings.R$string.managed_by_your_parent);
        } else {
            nb1.h(K(), org.chromium.components.browser_ui.settings.R$string.managed_by_your_organization);
        }
    }

    public final void U0(int i, boolean z) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) I0().Z("allowed_group");
        if (expandablePreferenceGroup == null) {
            return;
        }
        if (i == 0) {
            I0().d0(expandablePreferenceGroup);
            return;
        }
        if (this.l0) {
            expandablePreferenceGroup.T(O0(this.k0.b == 24 ? R$string.website_settings_allowed_group_heading_request_desktop_site : z ? R$string.website_settings_allowed_group_heading : R$string.website_settings_exceptions_group_heading, i));
            boolean z2 = this.n0;
            if (expandablePreferenceGroup.b0 == z2) {
                return;
            }
            expandablePreferenceGroup.b0 = z2;
            expandablePreferenceGroup.s();
        }
    }

    public final void V0(int i) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) I0().Z("blocked_group");
        if (i == 0) {
            if (expandablePreferenceGroup != null) {
                I0().d0(expandablePreferenceGroup);
            }
        } else if (this.l0) {
            int i2 = this.k0.b;
            expandablePreferenceGroup.T(O0(i2 == 18 ? R$string.website_settings_blocked_group_heading_sound : i2 == 24 ? R$string.website_settings_blocked_group_heading_request_desktop_site : R$string.website_settings_blocked_group_heading, i));
            boolean z = this.m0;
            if (expandablePreferenceGroup.b0 == z) {
                return;
            }
            expandablePreferenceGroup.b0 = z;
            expandablePreferenceGroup.s();
        }
    }

    public final void W0() {
        if (N.Mudil8Bg("RequestDesktopSiteAdditions")) {
            Profile profile = ((kr) this.h0).b;
            if (Boolean.valueOf(N.MJSt3Ocq(profile, 73)).booleanValue()) {
                I0().d0(this.t0);
                I0().d0(this.u0);
                return;
            }
            I0().Y(this.t0);
            I0().Y(this.u0);
            PrefService prefServiceK = oj2.k(profile);
            this.t0.Y(prefServiceK.a("desktop_site.peripheral_setting"));
            this.u0.Y(prefServiceK.a("desktop_site.display_setting"));
        }
    }

    public final void X0(int i) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) I0().Z("managed_group");
        if (i == 0) {
            if (expandablePreferenceGroup != null) {
                I0().d0(expandablePreferenceGroup);
            }
        } else if (this.l0) {
            expandablePreferenceGroup.T(O0(R$string.website_settings_managed_group_heading, i));
            boolean z = this.o0;
            if (expandablePreferenceGroup.b0 == z) {
                return;
            }
            expandablePreferenceGroup.b0 = z;
            expandablePreferenceGroup.s();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        qj2.a(this, R$xml.website_preferences);
        String string = this.j.getString("title");
        if (string != null) {
            D().setTitle(string);
        }
        this.v0 = this.j.containsKey("selected_domains") ? new HashSet(this.j.getStringArrayList("selected_domains")) : null;
        M0();
        if (this.k0.b == 24) {
            n3.X0("DesktopSiteContentSetting.SettingsPage.Entered");
            ((kr) this.h0).getClass();
            oj2.t(Profile.d()).notifyEvent("desktop_site_settings_page_opened");
        }
        this.H = true;
    }

    public final void Y0() {
        Profile profile = ((kr) this.h0).b;
        Boolean boolValueOf = Boolean.valueOf(N.MJSt3Ocq(profile, 5));
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference = (ChromeBaseCheckBoxPreference) I0().Z("notifications_vibrate");
        if (chromeBaseCheckBoxPreference != null) {
            chromeBaseCheckBoxPreference.J(boolValueOf.booleanValue());
        }
        ((kr) this.h0).getClass();
        sk skVar = bq.a;
        if (N.M09VlOh_("QuietNotificationPrompts")) {
            if (!boolValueOf.booleanValue()) {
                I0().d0(this.s0);
                return;
            }
            I0().Y(this.s0);
            this.s0.Y(oj2.k(profile).a("profile.content_settings.enable_quiet_permission_ui.notifications"));
        }
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        yn2 yn2VarC;
        Profile profile = ((kr) this.h0).b;
        Bundle bundle2 = this.j;
        if (bundle2 != null) {
            String string = bundle2.getString("category", "");
            int i = 0;
            while (true) {
                if (i >= 29) {
                    yn2VarC = null;
                    break;
                }
                if (yn2.m(i).equals(string)) {
                    yn2VarC = yn2.c(profile, i);
                    break;
                }
                i++;
            }
            this.k0 = yn2VarC;
        }
        yn2 yn2Var = this.k0;
        int i2 = yn2Var.b;
        if (i2 == 0 || i2 == 22) {
            throw new IllegalArgumentException("Use AllSiteSettings instead.");
        }
        int iF = yn2Var.f();
        int i3 = this.k0.b;
        if (i3 == 26) {
            this.r0 = 2;
        } else if (i3 == 8) {
            this.r0 = 3;
        } else {
            if (iF == 15) {
                this.r0 = 1;
            }
        }
        ViewGroup viewGroup2 = (ViewGroup) super.e0(layoutInflater, viewGroup, bundle);
        RecyclerView recyclerView = this.b0;
        this.j0 = recyclerView;
        recyclerView.setItemAnimator(null);
        return viewGroup2;
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.i0 = settingsLauncher;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        P0();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0114  */
    @Override // androidx.preference.Preference.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(androidx.preference.Preference r8, java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 465
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleCategorySettings.n(androidx.preference.Preference, java.lang.Object):boolean");
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if ("allowed_group".equals(preference.p)) {
            this.n0 = !this.n0;
        } else if ("blocked_group".equals(preference.p)) {
            this.m0 = !this.m0;
        } else {
            this.o0 = !this.o0;
        }
        P0();
        return true;
    }

    @Override // defpackage.p20
    public final void t() {
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
    }
}
