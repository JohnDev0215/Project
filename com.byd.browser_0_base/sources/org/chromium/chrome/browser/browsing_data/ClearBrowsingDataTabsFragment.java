package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.e;
import defpackage.gq2;
import defpackage.ke0;
import defpackage.n3;
import defpackage.uv;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class ClearBrowsingDataTabsFragment extends Fragment {
    public static final /* synthetic */ int a0 = 0;
    public ClearBrowsingDataFetcher Z;

    public static class a extends FragmentStateAdapter {
        public final ClearBrowsingDataFetcher q;

        public a(ClearBrowsingDataFetcher clearBrowsingDataFetcher, FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            this.q = clearBrowsingDataFetcher;
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public final Fragment F(int i) {
            ClearBrowsingDataFragment clearBrowsingDataFragmentBasic;
            if (i == 0) {
                clearBrowsingDataFragmentBasic = new ClearBrowsingDataFragmentBasic();
            } else {
                if (i != 1) {
                    throw new uv(gq2.e("invalid position: ", i));
                }
                clearBrowsingDataFragmentBasic = new ClearBrowsingDataFragmentAdvanced();
            }
            clearBrowsingDataFragmentBasic.l0 = this.q;
            return clearBrowsingDataFragmentBasic;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            return 2;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        D0();
        if (bundle == null) {
            ClearBrowsingDataFetcher clearBrowsingDataFetcher = new ClearBrowsingDataFetcher();
            this.Z = clearBrowsingDataFetcher;
            N.MCILE93S(Profile.d(), clearBrowsingDataFetcher);
            ClearBrowsingDataFetcher clearBrowsingDataFetcher2 = this.Z;
            clearBrowsingDataFetcher2.getClass();
            if (!SharedPreferencesManager.getInstance().readBoolean("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", false)) {
                BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
                browsingDataBridgeB.getClass();
                N.MxCHuwXz(browsingDataBridgeB, Profile.d(), clearBrowsingDataFetcher2);
            }
        } else {
            this.Z = (ClearBrowsingDataFetcher) bundle.getParcelable("clearBrowsingDataFetcher");
        }
        n3.X0("ClearBrowsingData_DialogCreated");
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence charSequence;
        View viewInflate = layoutInflater.inflate(R$layout.clear_browsing_data_tabs, viewGroup, false);
        ViewPager2 viewPager2 = (ViewPager2) viewInflate.findViewById(R$id.clear_browsing_data_viewpager);
        viewPager2.setAdapter(new a(this.Z, D()));
        TabLayout tabLayout = (TabLayout) viewInflate.findViewById(R$id.clear_browsing_data_tabs);
        new e(tabLayout, viewPager2, new ke0(8, this)).a();
        BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
        browsingDataBridgeB.getClass();
        int iMD5TSIMJ = N.MD5TSIMJ(browsingDataBridgeB);
        viewPager2.c(iMD5TSIMJ, false);
        TabLayout.g gVarK = tabLayout.k(iMD5TSIMJ);
        if (gVarK != null) {
            gVarK.b();
        }
        if (gVarK != null && (charSequence = gVarK.c) != null) {
            String strTrim = charSequence.toString().trim();
            SpannableString spannableString = new SpannableString(strTrim);
            spannableString.setSpan(new StyleSpan(0), 0, strTrim.length(), 17);
            spannableString.setSpan(new RelativeSizeSpan(1.1f), 0, strTrim.length(), 17);
            gVarK.d(spannableString);
        }
        tabLayout.addOnTabSelectedListener((TabLayout.d) new b());
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        bundle.putParcelable("clearBrowsingDataFetcher", this.Z);
    }

    public static class b implements TabLayout.d {
        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            CharSequence charSequence;
            if (gVar == null || (charSequence = gVar.c) == null) {
                return;
            }
            String strTrim = charSequence.toString().trim();
            SpannableString spannableString = new SpannableString(strTrim);
            spannableString.setSpan(new StyleSpan(0), 0, strTrim.length(), 17);
            spannableString.setSpan(new RelativeSizeSpan(1.1f), 0, strTrim.length(), 17);
            gVar.d(spannableString);
            int i = gVar.e;
            BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
            browsingDataBridgeB.getClass();
            N.MrfS11o2(browsingDataBridgeB, i);
            if (i == 0) {
                n3.X0("ClearBrowsingData_SwitchTo_BasicTab");
            } else {
                n3.X0("ClearBrowsingData_SwitchTo_AdvancedTab");
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
            CharSequence charSequence = gVar.c;
            if (charSequence == null) {
                return;
            }
            String strTrim = charSequence.toString().trim();
            SpannableString spannableString = new SpannableString(strTrim);
            spannableString.setSpan(new StyleSpan(0), 0, strTrim.length(), 17);
            spannableString.setSpan(new RelativeSizeSpan(1.0f), 0, strTrim.length(), 17);
            gVar.d(spannableString);
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
        }
    }
}
