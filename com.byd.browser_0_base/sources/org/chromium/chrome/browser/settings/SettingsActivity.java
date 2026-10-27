package org.chromium.chrome.browser.settings;

import J.N;
import android.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.d;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a8;
import defpackage.aq1;
import defpackage.bi1;
import defpackage.bp0;
import defpackage.c01;
import defpackage.ef2;
import defpackage.eu1;
import defpackage.fm3;
import defpackage.fu0;
import defpackage.gp0;
import defpackage.gx;
import defpackage.hu0;
import defpackage.i52;
import defpackage.i62;
import defpackage.i83;
import defpackage.kj2;
import defpackage.kr;
import defpackage.lp0;
import defpackage.n3;
import defpackage.o90;
import defpackage.oj2;
import defpackage.p20;
import defpackage.pd;
import defpackage.po;
import defpackage.pp0;
import defpackage.ps;
import defpackage.qh1;
import defpackage.qh3;
import defpackage.rd2;
import defpackage.rj2;
import defpackage.sp;
import defpackage.t41;
import defpackage.uv;
import defpackage.vw0;
import defpackage.wd0;
import defpackage.wp2;
import defpackage.xb;
import defpackage.yo;
import defpackage.yq2;
import defpackage.z7;
import defpackage.zx1;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentBasic;
import org.chromium.chrome.browser.image_descriptions.ImageDescriptionsSettings;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.a;
import org.chromium.chrome.browser.password_check.PasswordCheckFragmentView;
import org.chromium.chrome.browser.password_check.c;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditBridge;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment;
import org.chromium.chrome.browser.privacy_sandbox.AdMeasurementFragment;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.safety_check.SafetyCheckCoordinator;
import org.chromium.chrome.browser.safety_check.SafetyCheckSettingsFragment;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;
import org.chromium.components.browser_ui.accessibility.AccessibilitySettings;
import org.chromium.components.browser_ui.accessibility.FontSizePrefs;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment;

/* JADX INFO: loaded from: classes2.dex */
public class SettingsActivity extends ChromeBaseAppCompatActivity implements d.e, wp2.c {
    public static SettingsActivity P;
    public static boolean Q;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final n3 f76J = new n3();
    public wp2 K;
    public ef2 L;
    public f M;
    public qh3 N;
    public Profile O;

    public interface a {
        boolean a();
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    public final void K1() {
        qh3 qh3Var = this.N;
        if (qh3Var != null) {
            qh3Var.c();
            return;
        }
        View viewFindViewById = findViewById(org.chromium.chrome.R$id.content);
        RecyclerView recyclerView = (RecyclerView) findViewById(org.chromium.chrome.R$id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setVerticalScrollBarEnabled(false);
        }
        if (recyclerView != null) {
            viewFindViewById = recyclerView;
        }
        this.N = new qh3(viewFindViewById);
        new rj2(viewFindViewById, this.N, getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.settings_wide_display_min_padding)).a();
        if (recyclerView == null || !(L1() instanceof d)) {
            return;
        }
        ((d) L1()).K0(null);
        p20 p20Var = L1() instanceof p20 ? (p20) L1() : null;
        if (p20Var != null) {
            p20Var.t();
        }
    }

    @Override // androidx.preference.d.e
    public final void L0(Preference preference) {
        String str = preference.r;
        Bundle bundleM = preference.m();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClass(this, getClass());
        intent.putExtra("show_fragment", str);
        intent.putExtra("show_fragment_args", bundleM);
        startActivity(intent);
    }

    public final Fragment L1() {
        return x1().A(org.chromium.chrome.R$id.content);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.K = new wp2(this, (ViewGroup) findViewById(R.id.content), null);
        Fragment fragmentL1 = L1();
        if (fragmentL1 instanceof SiteSettingsPreferenceFragment) {
            kr krVar = (kr) ((SiteSettingsPreferenceFragment) fragmentL1).h0;
            wp2 wp2Var = this.K;
            if (wp2Var != null) {
                krVar.d = new i52(krVar.a, wp2Var, new n3());
            } else {
                krVar.getClass();
            }
        }
        if (fragmentL1 instanceof PrivacySandboxSettingsBaseFragment) {
            ((PrivacySandboxSettingsBaseFragment) fragmentL1).j0 = this.K;
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        K1();
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"InlinedApi"})
    public final void onCreate(Bundle bundle) {
        setTitle(R$string.settings);
        if (!Q) {
            Q = true;
            try {
                if (getPackageManager().getActivityInfo(getComponentName(), 0).exported) {
                    throw new IllegalStateException("SettingsActivity must not be exported.");
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new uv(e);
            }
        }
        sp.a().d(false);
        this.O = Profile.d();
        super.onCreate(bundle);
        setContentView(org.chromium.chrome.R$layout.settings_activity);
        C1((Toolbar) findViewById(org.chromium.chrome.R$id.action_bar));
        B1().t(true);
        this.I = bundle == null;
        String stringExtra = getIntent().getStringExtra("show_fragment");
        Bundle bundleExtra = getIntent().getBundleExtra("show_fragment_args");
        int i = 12;
        if (bundle == null) {
            if (stringExtra == null) {
                stringExtra = MainSettings.class.getName();
            }
            Fragment fragmentU = Fragment.U(this, stringExtra, bundleExtra);
            lp0 lp0VarX1 = x1();
            lp0VarX1.getClass();
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(lp0VarX1);
            aVar.e(org.chromium.chrome.R$id.content, fragmentU, null);
            zx1 zx1Var = new zx1(9, this);
            aVar.c();
            if (aVar.q == null) {
                aVar.q = new ArrayList<>();
            }
            aVar.q.add(zx1Var);
            aVar.g();
        } else {
            lp0 lp0VarX12 = x1();
            lp0VarX12.getClass();
            androidx.fragment.app.a aVar2 = new androidx.fragment.app.a(lp0VarX12);
            eu1 eu1Var = new eu1(i, this);
            aVar2.c();
            if (aVar2.q == null) {
                aVar2.q = new ArrayList<>();
            }
            aVar2.q.add(eu1Var);
            aVar2.g();
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(org.chromium.chrome.R$id.sheet_container);
        this.L = new ef2(this, new kj2(), (ViewGroup) viewGroup.getParent(), getColor(org.chromium.chrome.R$color.default_scrim_color));
        this.M = new f(new yo(13, this), new ps(i), getWindow(), t41.f, new yq2(17, viewGroup));
        xb.b(this, this.k, new c01(18, this));
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        Fragment fragmentL1 = L1();
        if (fragmentL1 != null && fragmentL1.k0(menuItem)) {
            return true;
        }
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        super.onPause();
        z7.i();
    }

    @Override // android.app.Activity
    public final boolean onPrepareOptionsMenu(Menu menu) {
        if (menu.size() == 1) {
            MenuItem item = menu.getItem(0);
            if (item.getIcon() != null) {
                item.setShowAsAction(1);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        SettingsActivity settingsActivity = P;
        if (settingsActivity != null && settingsActivity.getTaskId() != getTaskId() && !this.I) {
            finish();
            return;
        }
        SettingsActivity settingsActivity2 = P;
        if (settingsActivity2 != null && settingsActivity2.getTaskId() != getTaskId()) {
            P.finish();
        }
        P = this;
        this.I = false;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
        if (P == this) {
            P = null;
        }
    }

    @Override // wp2.c
    public final wp2 q0() {
        return this.K;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public final void z1(Fragment fragment) {
        if (fragment instanceof i62) {
            ((i62) fragment).m(this.O);
        }
        boolean z = fragment instanceof MainSettings;
        aq1<bi1> aq1Var = this.D;
        if (z) {
            ((MainSettings) fragment).l0 = aq1Var;
        }
        if (fragment instanceof SiteSettingsPreferenceFragment) {
            ((SiteSettingsPreferenceFragment) fragment).h0 = new kr(this, this.O);
        }
        boolean z2 = fragment instanceof pp0;
        n3 n3Var = this.f76J;
        if (z2) {
            ((pp0) fragment).i(n3Var);
        }
        if (fragment instanceof gp0) {
            ((gp0) fragment).d(hu0.a());
        }
        if (fragment instanceof SafetyCheckSettingsFragment) {
            new SafetyCheckCoordinator((SafetyCheckSettingsFragment) fragment, new rd2(), n3Var, a8.y(), aq1Var);
        }
        int i = 13;
        if (fragment instanceof PasswordCheckFragmentView) {
            c.a((PasswordCheckFragmentView) fragment, hu0.a(), n3Var, new pd(i), new i83(15));
        }
        if (fragment instanceof CredentialEntryFragmentViewBase) {
            CredentialEntryFragmentViewBase credentialEntryFragmentViewBase = (CredentialEntryFragmentViewBase) fragment;
            fu0 fu0VarA = hu0.a();
            CredentialEditBridge credentialEditBridge = CredentialEditBridge.c;
            if (credentialEditBridge == null) {
                credentialEntryFragmentViewBase.M0();
            } else {
                credentialEditBridge.b = new org.chromium.chrome.browser.password_entry_edit.a(credentialEntryFragmentViewBase, credentialEditBridge, credentialEditBridge, fu0VarA);
                N.MGXq90Cw(credentialEditBridge.a);
                N.MxEiaAZZ(credentialEditBridge.a);
            }
        }
        int i2 = 10;
        if (fragment instanceof SearchEngineSettings) {
            SearchEngineSettings searchEngineSettings = (SearchEngineSettings) fragment;
            o90 o90Var = new o90(i2);
            searchEngineSettings.K0();
            searchEngineSettings.i0.m = o90Var;
            searchEngineSettings.K0();
            searchEngineSettings.i0.n = n3Var;
        }
        if (fragment instanceof ImageDescriptionsSettings) {
            ImageDescriptionsSettings imageDescriptionsSettings = (ImageDescriptionsSettings) fragment;
            Bundle bundle = imageDescriptionsSettings.j;
            if (bundle != null) {
                vw0 vw0VarA = vw0.a();
                Profile profile = this.O;
                vw0VarA.getClass();
                bundle.putBoolean("image_descriptions_switch", oj2.k(profile).a("settings.a11y.enable_accessibility_image_labels_android"));
                vw0 vw0VarA2 = vw0.a();
                Profile profile2 = this.O;
                vw0VarA2.getClass();
                bundle.putBoolean("image_descriptions_data_policy", oj2.k(profile2).a("settings.a11y.enable_accessibility_image_labels_only_on_wifi"));
            }
            imageDescriptionsSettings.j0 = vw0.a().a;
        }
        if (fragment instanceof PrivacySandboxSettingsBaseFragment) {
            PrivacySandboxSettingsBaseFragment privacySandboxSettingsBaseFragment = (PrivacySandboxSettingsBaseFragment) fragment;
            privacySandboxSettingsBaseFragment.h0 = new wd0(17);
            privacySandboxSettingsBaseFragment.k0 = new fm3(5);
        }
        if (fragment instanceof AdMeasurementFragment) {
            ((AdMeasurementFragment) fragment).l0 = new bp0(1, fragment);
        }
        if (fragment instanceof LanguageSettings) {
            gx gxVar = new gx(i, new qh1(i2));
            org.chromium.chrome.browser.language.settings.a aVar = ((LanguageSettings) fragment).i0;
            aVar.getClass();
            aVar.c = new a.b(gxVar);
        }
        if (fragment instanceof ClearBrowsingDataFragmentBasic) {
            ((ClearBrowsingDataFragmentBasic) fragment).p0 = new wd0(18);
        }
        if (fragment instanceof PrivacyGuideFragment) {
            PrivacyGuideFragment privacyGuideFragment = (PrivacyGuideFragment) fragment;
            privacyGuideFragment.Z = this.M;
            privacyGuideFragment.a0 = new pd(14);
            privacyGuideFragment.b0 = n3Var;
        }
        if (fragment instanceof AccessibilitySettings) {
            AccessibilitySettings accessibilitySettings = (AccessibilitySettings) fragment;
            accessibilitySettings.m0 = new po();
            Profile profileD = Profile.d();
            Object obj = ThreadUtils.a;
            if (FontSizePrefs.c == null) {
                FontSizePrefs.c = new FontSizePrefs(profileD);
            }
            accessibilitySettings.p0 = FontSizePrefs.c;
        }
    }
}
