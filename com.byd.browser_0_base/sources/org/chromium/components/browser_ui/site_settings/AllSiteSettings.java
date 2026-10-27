package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import defpackage.C0179d;
import defpackage.bk3;
import defpackage.bt3;
import defpackage.by1;
import defpackage.dt3;
import defpackage.hu0;
import defpackage.ie0;
import defpackage.jp2;
import defpackage.ke0;
import defpackage.kr;
import defpackage.n3;
import defpackage.of;
import defpackage.p20;
import defpackage.qj2;
import defpackage.re3;
import defpackage.ug2;
import defpackage.vg2;
import defpackage.ws3;
import defpackage.yn2;
import defpackage.zn2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.history_clusters.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.site_settings.g;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
@UsedByReflection
public class AllSiteSettings extends SiteSettingsPreferenceFragment implements View.OnClickListener, p20 {
    public static final /* synthetic */ int q0 = 0;
    public Button i0;
    public TextView j0;
    public LinearLayout k0;
    public MenuItem l0;
    public yn2 m0;
    public String n0;
    public List<dt3> o0;
    public HashSet p0;

    public class a implements g.c {
        public a() {
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            boolean zIsEmpty;
            AllSiteSettings allSiteSettings = AllSiteSettings.this;
            if (allSiteSettings.D() == null) {
                return;
            }
            allSiteSettings.o0 = null;
            allSiteSettings.I0().c0();
            allSiteSettings.M0();
            if (allSiteSettings.p0 != null) {
                Iterator<f> it = collection.iterator();
                while (it.hasNext()) {
                    if (!allSiteSettings.p0.contains(bk3.b(it.next().e.h(), true))) {
                        it.remove();
                    }
                }
            }
            if (allSiteSettings.m0.b == 0 && N.ManEQDnV("SiteDataImprovements")) {
                HashMap map = new HashMap();
                for (f fVar : collection) {
                    String strF = fVar.e.f();
                    List arrayList = (List) map.get(strF);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(strF, arrayList);
                    }
                    arrayList.add(fVar);
                }
                ArrayList<ws3> arrayList2 = new ArrayList();
                for (Map.Entry entry : map.entrySet()) {
                    arrayList2.add(((List) entry.getValue()).size() == 1 ? (ws3) ((List) entry.getValue()).get(0) : new bt3((String) entry.getKey(), (List) entry.getValue()));
                }
                ArrayList arrayList3 = new ArrayList();
                for (ws3 ws3Var : arrayList2) {
                    String str = allSiteSettings.n0;
                    if (str == null || str.isEmpty() || ws3Var.r(allSiteSettings.n0)) {
                        arrayList3.add(new WebsiteRowPreference(allSiteSettings.a0.a, allSiteSettings.h0, ws3Var));
                    }
                }
                Collections.sort(arrayList3);
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    allSiteSettings.I0().Y((WebsiteRowPreference) it2.next());
                }
                zIsEmpty = arrayList3.isEmpty();
            } else {
                ArrayList arrayList4 = new ArrayList();
                for (f fVar2 : collection) {
                    String str2 = allSiteSettings.n0;
                    if (str2 == null || str2.isEmpty() || fVar2.h().contains(allSiteSettings.n0)) {
                        dt3 dt3Var = new dt3(allSiteSettings.a0.a, allSiteSettings.h0, fVar2, allSiteSettings.m0);
                        dt3Var.f17J = R$layout.preference_material_site;
                        arrayList4.add(dt3Var);
                    }
                }
                Collections.sort(arrayList4);
                Iterator it3 = arrayList4.iterator();
                while (it3.hasNext()) {
                    allSiteSettings.I0().Y((dt3) it3.next());
                }
                allSiteSettings.o0 = arrayList4;
                zIsEmpty = arrayList4.isEmpty();
            }
            boolean z = !zIsEmpty;
            LinearLayout linearLayout = allSiteSettings.k0;
            if (linearLayout != null) {
                if (z) {
                    linearLayout.setVisibility(8);
                } else {
                    linearLayout.setVisibility(0);
                }
            }
            TextView textView = allSiteSettings.j0;
            if (textView == null) {
                return;
            }
            textView.setVisibility(z ? 8 : 0);
        }
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.c
    public final boolean C(Preference preference) {
        if (preference instanceof dt3) {
            dt3 dt3Var = (dt3) preference;
            dt3Var.r = SingleWebsiteSettings.class.getName();
            dt3Var.m().putSerializable("org.chromium.chrome.preferences.site", dt3Var.c0);
            dt3Var.m().putInt("org.chromium.chrome.preferences.navigation_source", this.j.getInt("org.chromium.chrome.preferences.navigation_source", 0));
        } else if (preference instanceof WebsiteRowPreference) {
            ((WebsiteRowPreference) preference).b0(this.j);
        }
        return super.C(preference);
    }

    public final void M0() {
        if (!(this.m0.b == 0 && N.ManEQDnV("SiteDataImprovements"))) {
            qj2.a(this, R$xml.all_site_preferences);
            return;
        }
        qj2.a(this, R$xml.all_site_preferences_v2);
        ChromeBasePreference chromeBasePreference = (ChromeBasePreference) f("clear_browsing_data_link");
        this.h0.getClass();
        SpannableString spannableString = new SpannableString(N().getString(R$string.clear_browsing_data_link));
        spannableString.setSpan(new ForegroundColorSpan(K().getColor(R$color.default_text_color_link_baseline)), 0, spannableString.length(), 17);
        chromeBasePreference.R(spannableString);
        chromeBasePreference.setOnPreferenceClickListener(new C0179d(3, this));
    }

    public final void N0() {
        new g(((kr) this.h0).b, false).b(this.h0, this.m0, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        M0();
        String string = this.j.getString("title");
        if (string != null) {
            D().setTitle(string);
        }
        this.p0 = this.j.containsKey("selected_domains") ? new HashSet(this.j.getStringArrayList("selected_domains")) : null;
        D0();
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R$menu.website_preferences_menu, menu);
        final MenuItem menuItemFindItem = menu.findItem(R$id.search);
        this.l0 = menuItemFindItem;
        String str = this.n0;
        final FragmentActivity fragmentActivityD = D();
        final ke0 ke0Var = new ke0(29, this);
        final SearchView searchView = (SearchView) menuItemFindItem.getActionView();
        searchView.setImeOptions(33554432);
        if (str != null) {
            menuItemFindItem.expandActionView();
            searchView.setIconified(false);
            searchView.q(str);
            vg2.a(menuItemFindItem, str, fragmentActivityD);
        }
        menuItemFindItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: sg2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                vg2.a(menuItemFindItem, "", fragmentActivityD);
                ((ke0) ke0Var).t("");
                return false;
            }
        });
        int i = org.chromium.components.browser_ui.settings.R$id.search_close_btn;
        ((ImageView) searchView.findViewById(i)).setOnClickListener(new of(searchView, menuItemFindItem, fragmentActivityD, ke0Var, 1));
        ((ImageView) searchView.findViewById(i)).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: tg2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                vg2.a(menuItemFindItem, searchView.getQuery().toString(), fragmentActivityD);
            }
        });
        final int i2 = 1;
        searchView.setOnSearchClickListener(new View.OnClickListener() { // from class: yu0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                Object obj = ke0Var;
                Object obj2 = fragmentActivityD;
                Object obj3 = menuItemFindItem;
                switch (i3) {
                    case 0:
                        b bVar = (b) obj3;
                        xa1 xa1Var = (xa1) obj2;
                        bVar.getClass();
                        PropertyModel propertyModel = xa1Var.b;
                        propertyModel.p(wu0.b, new uf(bVar, 2, xa1Var, (List) obj));
                        propertyModel.p(wu0.e, Boolean.FALSE);
                        uh3.e(null, R$drawable.ic_expand_less_black_24dp, R$color.default_icon_color_tint_list);
                        throw null;
                    default:
                        vg2.a((MenuItem) obj3, "", (Activity) obj2);
                        ((ke0) ((vg2.a) obj)).t("");
                        return;
                }
            }
        });
        searchView.setOnQueryTextListener(new ug2(menuItemFindItem, fragmentActivityD, ke0Var));
        this.h0.getClass();
        menu.add(0, R$id.menu_id_site_settings_help, 0, R$string.menu_help).setIcon(re3.a(N(), R$drawable.ic_help_and_feedback, K().getTheme()));
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int iIntValue;
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
            this.m0 = yn2VarC;
        }
        if (this.m0 == null) {
            this.m0 = yn2.c(profile, 0);
        }
        int i2 = this.m0.b;
        if (i2 != 0 && i2 != 22) {
            throw new IllegalArgumentException("Use SingleCategorySettings instead.");
        }
        ViewGroup viewGroup2 = (ViewGroup) super.e0(layoutInflater, viewGroup, bundle);
        if (this.m0.b == 22) {
            try {
                iIntValue = ((Integer) jp2.a.getMethod("getStoragePreferencesView", new Class[0]).invoke(null, new Object[0])).intValue();
            } catch (Exception unused) {
                iIntValue = R$layout.storage_preferences_view;
            }
            layoutInflater.inflate(iIntValue, viewGroup2, true);
            this.j0 = (TextView) viewGroup2.findViewById(R$id.empty_storage);
            Button button = (Button) viewGroup2.findViewById(R$id.clear_button);
            this.i0 = button;
            button.setOnClickListener(this);
        }
        if (this.m0.b == 0) {
            layoutInflater.inflate(R$layout.allsite_empty, viewGroup2, true);
            this.k0 = (LinearLayout) viewGroup2.findViewById(R$id.empty_text_ll);
        }
        this.b0.setItemAnimator(null);
        return viewGroup2;
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        boolean z;
        if (menuItem.getItemId() == R$id.menu_id_site_settings_help) {
            zn2 zn2Var = this.h0;
            FragmentActivity fragmentActivityD = D();
            ((kr) zn2Var).getClass();
            ((hu0) hu0.a()).c(fragmentActivityD, fragmentActivityD.getString(org.chromium.chrome.R$string.help_context_settings), null, Profile.d());
            return true;
        }
        MenuItem menuItem2 = this.l0;
        String str = this.n0;
        FragmentActivity fragmentActivityD2 = D();
        boolean z2 = false;
        if (menuItem.getItemId() != 16908332 || str == null) {
            z = false;
        } else {
            SearchView searchView = (SearchView) menuItem2.getActionView();
            searchView.q(null);
            searchView.setIconified(true);
            menuItem2.collapseActionView();
            vg2.a(menuItem2, null, fragmentActivityD2);
            z = true;
        }
        if (!z) {
            return false;
        }
        String str2 = this.n0;
        if (str2 != null && !str2.isEmpty()) {
            z2 = true;
        }
        this.n0 = null;
        if (z2) {
            N0();
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        MenuItem menuItem;
        this.H = true;
        if (this.n0 == null && (menuItem = this.l0) != null) {
            FragmentActivity fragmentActivityD = D();
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.q(null);
            searchView.setIconified(true);
            menuItem.collapseActionView();
            vg2.a(menuItem, null, fragmentActivityD);
            this.n0 = null;
        }
        N0();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean zContains;
        if (K() == null || view != this.i0) {
            return;
        }
        ((kr) this.h0).getClass();
        HashSet hashSetA = WebappRegistry.b.a.a();
        List<dt3> list = this.o0;
        long j = 0;
        if (list != null) {
            zContains = false;
            for (dt3 dt3Var : list) {
                j += dt3Var.c0.j();
                if (!zContains) {
                    zContains = hashSetA.contains(dt3Var.c0.e.h());
                }
            }
        } else {
            zContains = false;
        }
        Dialog dialog = new Dialog(K(), R$style.Dialog_Style);
        View viewInflate = ((LayoutInflater) K().getSystemService("layout_inflater")).inflate(R$layout.clear_all_data_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.message);
        TextView textView2 = (TextView) viewInflate.findViewById(R$id.signed_out_text);
        TextView textView3 = (TextView) viewInflate.findViewById(R$id.offline_text);
        Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        if (((kr) this.h0).c()) {
            ((RelativeLayout) viewInflate.findViewById(R$id.ad_personalization)).setVisibility(0);
        }
        textView2.setText(R$string.webstorage_clear_data_dialog_sign_out_all_message);
        textView3.setText(R$string.webstorage_clear_data_dialog_offline_message);
        textView.setText(P(zContains ? R$string.webstorage_clear_data_dialog_message_with_app : R$string.webstorage_clear_data_dialog_message, Formatter.formatShortFileSize(K(), j)));
        dialog.setContentView(viewInflate);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
        button.setOnClickListener(new ie0(27, this));
        button2.setOnClickListener(new by1(24, dialog));
    }

    @Override // defpackage.p20
    public final void t() {
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
    }
}
