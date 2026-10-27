package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.fm2;
import defpackage.gq2;
import defpackage.iq2;
import defpackage.oj2;
import defpackage.p20;
import defpackage.pt;
import defpackage.q8;
import defpackage.qj2;
import defpackage.r20;
import defpackage.so;
import defpackage.u90;
import defpackage.un1;
import defpackage.v82;
import defpackage.vj1;
import defpackage.z7;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;
import org.chromium.chrome.browser.browsing_data.BrowsingDataCounterBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.browser_ui.settings.ClickableSpansTextMessagePreference;
import org.chromium.components.browser_ui.settings.R$layout;
import org.chromium.components.browser_ui.settings.SpinnerPreference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ClearBrowsingDataFragment extends d implements BrowsingDataBridge.a, Preference.d, Preference.c, fm2.a, SigninManager.b, p20 {
    public static final /* synthetic */ int o0 = 0;
    public OtherFormsOfHistoryDialogFragment h0;
    public SigninManager i0;
    public r20 j0;
    public a[] k0;
    public ClearBrowsingDataFetcher l0;
    public ConfirmImportantSitesDialogFragment m0;
    public pt n0 = null;

    public static class a implements BrowsingDataCounterBridge.a, Preference.d {
        public final ClearBrowsingDataFragment e;
        public final int f;
        public final ClearBrowsingDataCheckBoxPreference g;
        public final BrowsingDataCounterBridge h;
        public boolean i;

        public a(FragmentActivity fragmentActivity, ClearBrowsingDataFragment clearBrowsingDataFragment, int i, ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference, boolean z, boolean z2) {
            int i2;
            this.e = clearBrowsingDataFragment;
            this.f = i;
            this.g = clearBrowsingDataCheckBoxPreference;
            this.h = new BrowsingDataCounterBridge(this, ClearBrowsingDataFragment.P0(i), clearBrowsingDataFragment.O0());
            clearBrowsingDataCheckBoxPreference.setOnPreferenceClickListener(this);
            clearBrowsingDataCheckBoxPreference.J(z2);
            clearBrowsingDataCheckBoxPreference.Y(z);
            if (clearBrowsingDataFragment.N().getConfiguration().smallestScreenWidthDp >= 360) {
                if (i == 0) {
                    i2 = R$drawable.ic_watch_later_24dp;
                } else if (i == 1) {
                    i2 = R$drawable.permission_cookie;
                } else if (i == 2) {
                    i2 = R$drawable.ic_collections_grey;
                } else if (i == 3) {
                    i2 = R$drawable.ic_vpn_key_grey;
                } else if (i == 4) {
                    i2 = R$drawable.ic_edit_24dp;
                } else {
                    if (i != 5) {
                        throw new IllegalArgumentException();
                    }
                    i2 = R$drawable.ic_tv_options_input_settings_rotated_grey;
                }
                clearBrowsingDataCheckBoxPreference.L(qj2.b(fragmentActivity, i2));
            }
        }

        @Override // androidx.preference.Preference.d
        public final boolean r(Preference preference) {
            int i = ClearBrowsingDataFragment.o0;
            ClearBrowsingDataFragment clearBrowsingDataFragment = this.e;
            ((Button) clearBrowsingDataFragment.f12J.findViewById(R$id.clear_button)).setEnabled(!clearBrowsingDataFragment.S0().isEmpty());
            this.i = true;
            BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
            int iP0 = ClearBrowsingDataFragment.P0(this.f);
            int iO0 = clearBrowsingDataFragment.O0();
            boolean z = this.g.S;
            browsingDataBridgeB.getClass();
            N.MBI7g3zY(browsingDataBridgeB, iP0, iO0, z);
            return true;
        }
    }

    public static class b {
        public final int a;
        public final String b;

        public b(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public final String toString() {
            return this.b;
        }
    }

    public static int P0(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                if (i == 5) {
                    return 5;
                }
                throw new IllegalArgumentException();
            }
        }
        return i2;
    }

    public static String R0(int i) {
        if (i == 0) {
            return "clear_history_checkbox";
        }
        if (i == 1) {
            return "clear_cookies_checkbox";
        }
        if (i == 2) {
            return "clear_cache_checkbox";
        }
        if (i == 3) {
            return "clear_passwords_checkbox";
        }
        if (i == 4) {
            return "clear_form_data_checkbox";
        }
        if (i == 5) {
            return "clear_site_settings_checkbox";
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.preference.d
    public void J0(Bundle bundle, String str) {
        int i;
        boolean z;
        if (bundle != null) {
            this.l0 = (ClearBrowsingDataFetcher) bundle.getParcelable("clearBrowsingDataFetcher");
        }
        D().setTitle(R$string.clear_browsing_data_title);
        qj2.a(this, R$xml.clear_browsing_data_preferences_tab);
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        this.i0 = z7.D(profileD);
        List<Integer> listQ0 = Q0();
        this.k0 = new a[listQ0.size()];
        int i2 = 0;
        for (int i3 = 0; i3 < listQ0.size(); i3++) {
            int iIntValue = listQ0.get(i3).intValue();
            if (iIntValue != 0 || gq2.l("history.deleting_enabled")) {
                z = true;
            } else {
                BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
                int iP0 = P0(0);
                browsingDataBridgeB.getClass();
                N.MBI7g3zY(browsingDataBridgeB, iP0, 0, false);
                BrowsingDataBridge browsingDataBridgeB2 = BrowsingDataBridge.b();
                int iP02 = P0(0);
                browsingDataBridgeB2.getClass();
                N.MBI7g3zY(browsingDataBridgeB2, iP02, 1, false);
                z = false;
            }
            a[] aVarArr = this.k0;
            FragmentActivity fragmentActivityD = D();
            ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference = (ClearBrowsingDataCheckBoxPreference) f(R0(iIntValue));
            BrowsingDataBridge browsingDataBridgeB3 = BrowsingDataBridge.b();
            int iP03 = P0(iIntValue);
            int iO0 = O0();
            browsingDataBridgeB3.getClass();
            aVarArr[i3] = new a(fragmentActivityD, this, iIntValue, clearBrowsingDataCheckBoxPreference, N.MK1rP8DI(browsingDataBridgeB3, iP03, iO0), z);
        }
        q8 q8Var = new q8(0);
        for (int i4 = 0; i4 < 6; i4++) {
            q8Var.add(Integer.valueOf(i4));
        }
        q8Var.removeAll(listQ0);
        q8.a aVar = new q8.a();
        while (aVar.hasNext()) {
            I0().d0(f(R0(((Integer) aVar.next()).intValue())));
        }
        SpinnerPreference spinnerPreference = (SpinnerPreference) f("time_period_spinner");
        FragmentActivity fragmentActivityD2 = D();
        ArrayList arrayList = new ArrayList();
        if (v82.b.a()) {
            arrayList.add(new b(6, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_15_minutes)));
        }
        arrayList.add(new b(0, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_hour)));
        arrayList.add(new b(1, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_24_hours)));
        arrayList.add(new b(2, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_7_days)));
        arrayList.add(new b(3, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_four_weeks)));
        arrayList.add(new b(4, fragmentActivityD2.getString(R$string.clear_browsing_data_tab_period_everything)));
        b[] bVarArr = (b[]) arrayList.toArray(new b[0]);
        BrowsingDataBridge browsingDataBridgeB4 = BrowsingDataBridge.b();
        int iO02 = O0();
        browsingDataBridgeB4.getClass();
        int iMWrAQRuo = N.MWrAQRuo(browsingDataBridgeB4, iO02);
        int i5 = 0;
        while (true) {
            i = -1;
            if (i5 >= bVarArr.length) {
                i5 = -1;
                break;
            } else if (bVarArr[i5].a == iMWrAQRuo) {
                break;
            } else {
                i5++;
            }
        }
        if (i5 == -1) {
            while (true) {
                if (i2 >= bVarArr.length) {
                    break;
                }
                if (bVarArr[i2].a == 0) {
                    i = i2;
                    break;
                }
                i2++;
            }
            i5 = i;
        }
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<>(spinnerPreference.e, spinnerPreference.V ? R$layout.preference_spinner_single_line_item : R.layout.simple_spinner_item, bVarArr);
        spinnerPreference.T = arrayAdapter;
        arrayAdapter.setDropDownViewResource(R$layout.settings_spinner_dropdown_item);
        spinnerPreference.U = i5;
        spinnerPreference.setOnPreferenceChangeListener(this);
        V0();
        this.i0.i(this);
    }

    public final void M0(q8 q8Var, String[] strArr, int[] iArr, String[] strArr2, int[] iArr2) {
        if (this.n0 == null) {
            this.n0 = new pt(this, Looper.myLooper());
        }
        this.n0.sendEmptyMessageDelayed(1, 80000L);
        this.n0.postDelayed(new u90(this, q8Var, strArr, iArr, strArr2, iArr2, 1), 100L);
    }

    public final void N0() {
        try {
            r20 r20Var = this.j0;
            if (r20Var != null && r20Var.isShowing()) {
                this.j0.dismiss();
            }
            this.j0 = null;
        } catch (Exception unused) {
        }
    }

    public abstract int O0();

    public abstract List<Integer> Q0();

    public final q8 S0() {
        q8 q8Var = new q8(0);
        for (a aVar : this.k0) {
            if (aVar.g.S) {
                q8Var.add(Integer.valueOf(aVar.f));
            }
        }
        return q8Var;
    }

    public abstract void T0();

    public final void U0() {
        q8 q8VarS0 = S0();
        boolean z = false;
        if (q8VarS0.contains(2) || q8VarS0.contains(1)) {
            String[] strArr = this.l0.f;
            if (strArr != null && strArr.length != 0) {
                z = true;
            }
            oj2.O("History.ClearBrowsingData.ImportantDialogShown", z);
        }
        if (!z) {
            M0(S0(), null, null, null, null);
            return;
        }
        ClearBrowsingDataFetcher clearBrowsingDataFetcher = this.l0;
        String[] strArr2 = clearBrowsingDataFetcher.f;
        int[] iArr = clearBrowsingDataFetcher.g;
        String[] strArr3 = clearBrowsingDataFetcher.h;
        ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = new ConfirmImportantSitesDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("ImportantDomains", strArr2);
        bundle.putIntArray("ImportantDomainReasons", iArr);
        bundle.putStringArray("FaviconURLs", strArr3);
        confirmImportantSitesDialogFragment.C0(bundle);
        this.m0 = confirmImportantSitesDialogFragment;
        confirmImportantSitesDialogFragment.E0(1, this);
        this.m0.M0(this.v, "ConfirmImportantSitesDialogFragment");
    }

    public final void V0() {
        ClickableSpansTextMessagePreference clickableSpansTextMessagePreference = (ClickableSpansTextMessagePreference) f("sign_out_of_chrome_text");
        if (!this.i0.b()) {
            clickableSpansTextMessagePreference.U(false);
        } else {
            clickableSpansTextMessagePreference.R(iq2.a(K().getString(R$string.sign_out_of_chrome_link), new iq2.a(new un1(x0(), new so(13, this)), "<link1>", "</link1>")));
            clickableSpansTextMessagePreference.U(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        this.H = true;
        ((Button) this.f12J.findViewById(R$id.clear_button)).setEnabled(!S0().isEmpty());
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            String[] stringArrayExtra = intent.getStringArrayExtra("DeselectedDomains");
            int[] intArrayExtra = intent.getIntArrayExtra("DeselectedDomainReasons");
            String[] stringArrayExtra2 = intent.getStringArrayExtra("IgnoredDomains");
            int[] intArrayExtra2 = intent.getIntArrayExtra("IgnoredDomainReasons");
            if (stringArrayExtra != null) {
                ClearBrowsingDataFetcher clearBrowsingDataFetcher = this.l0;
                if (clearBrowsingDataFetcher.f != null) {
                    int length = stringArrayExtra.length;
                    int i3 = clearBrowsingDataFetcher.e + 1;
                    oj2.S(length, 1, i3, i3, "History.ClearBrowsingData.ImportantDeselectedNum");
                    int length2 = stringArrayExtra2.length;
                    int i4 = this.l0.e + 1;
                    oj2.S(length2, 1, i4, i4, "History.ClearBrowsingData.ImportantIgnoredNum");
                    oj2.U((stringArrayExtra.length * 20) / this.l0.f.length, 21, "History.ClearBrowsingData.ImportantDeselectedPercent");
                    oj2.U((stringArrayExtra2.length * 20) / this.l0.f.length, 21, "History.ClearBrowsingData.ImportantIgnoredPercent");
                }
            }
            M0(S0(), stringArrayExtra, intArrayExtra, stringArrayExtra2, intArrayExtra2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View e0(android.view.LayoutInflater r4, android.view.ViewGroup r5, android.os.Bundle r6) {
        /*
            r3 = this;
            android.view.View r5 = super.e0(r4, r5, r6)
            android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
            androidx.recyclerview.widget.RecyclerView r6 = r3.b0
            r0 = 0
            if (r6 == 0) goto Le
            r6.setVerticalScrollBarEnabled(r0)
        Le:
            java.lang.String r6 = defpackage.kp2.a
            int r1 = r6.hashCode()
            r2 = 1307376552(0x4decfba8, float:4.9698944E8)
            if (r1 == r2) goto L29
            r2 = 1418153572(0x54874e64, float:4.649086E12)
            if (r1 == r2) goto L1f
            goto L33
        L1f:
            java.lang.String r1 = "8155f_dynasty_def"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L33
            r6 = 1
            goto L34
        L29:
            java.lang.String r1 = "8155f_denza_def"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L33
            r6 = r0
            goto L34
        L33:
            r6 = -1
        L34:
            if (r6 == 0) goto L39
            int r6 = org.chromium.chrome.R$layout.clear_browsing_data_button
            goto L3b
        L39:
            int r6 = org.chromium.chrome.R$layout.clear_browsing_data_button_8155f_denza_def
        L3b:
            android.view.View r4 = r4.inflate(r6, r5, r0)
            android.widget.Button r4 = (android.widget.Button) r4
            ie0 r6 = new ie0
            r0 = 5
            r6.<init>(r0, r3)
            r4.setOnClickListener(r6)
            r5.addView(r4)
            androidx.recyclerview.widget.RecyclerView r4 = r3.b0
            r6 = 0
            r4.setItemAnimator(r6)
            r3.K0(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment.e0(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        pt ptVar = this.n0;
        if (ptVar != null) {
            ptVar.removeCallbacksAndMessages(null);
        }
        this.n0 = null;
        this.H = true;
        N0();
        for (a aVar : this.k0) {
            BrowsingDataCounterBridge browsingDataCounterBridge = aVar.h;
            long j = browsingDataCounterBridge.a;
            if (j != 0) {
                N.MdFUmBu6(j, browsingDataCounterBridge);
                browsingDataCounterBridge.a = 0L;
            }
        }
        this.i0.n(this);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if (!preference.p.equals("time_period_spinner")) {
            return false;
        }
        for (a aVar : this.k0) {
            aVar.i = false;
        }
        BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
        int iO0 = O0();
        int i = ((b) obj).a;
        browsingDataBridgeB.getClass();
        N.MyZiGmx0(browsingDataBridgeB, iO0, i);
        return true;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putParcelable("clearBrowsingDataFetcher", this.l0);
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        if (!preference.p.equals("clear_button")) {
            return false;
        }
        U0();
        return true;
    }

    @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.a
    public final void s() {
        if (z7.M(D())) {
            return;
        }
        if (vj1.m(D()) && S0().contains(0) && this.l0.i && !SharedPreferencesManager.getInstance().readBoolean("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", false)) {
            this.h0 = new OtherFormsOfHistoryDialogFragment();
            FragmentActivity fragmentActivityD = D();
            OtherFormsOfHistoryDialogFragment otherFormsOfHistoryDialogFragment = this.h0;
            otherFormsOfHistoryDialogFragment.getClass();
            otherFormsOfHistoryDialogFragment.M0(fragmentActivityD.x1(), "OtherFormsOfHistoryDialogFragment");
            N0();
            oj2.O("History.ClearBrowsingData.ShownHistoryNoticeAfterClearing", true);
        } else {
            N0();
            D().finish();
            oj2.O("History.ClearBrowsingData.ShownHistoryNoticeAfterClearing", false);
        }
        pt ptVar = this.n0;
        if (ptVar != null) {
            ptVar.removeCallbacksAndMessages(null);
        }
        BrowsingDataBridge.b().setmClearBrowsingDataListener(null);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void w() {
        V0();
    }

    @Override // defpackage.p20
    public final void t() {
    }
}
