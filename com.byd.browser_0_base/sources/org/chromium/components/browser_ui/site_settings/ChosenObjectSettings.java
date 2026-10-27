package org.chromium.components.browser_ui.site_settings;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import defpackage.c30;
import defpackage.dt3;
import defpackage.hu0;
import defpackage.kr;
import defpackage.mb1;
import defpackage.n40;
import defpackage.nb1;
import defpackage.no;
import defpackage.p20;
import defpackage.ph1;
import defpackage.re3;
import defpackage.yn2;
import defpackage.zn2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.g;

/* JADX INFO: loaded from: classes2.dex */
public class ChosenObjectSettings extends SiteSettingsPreferenceFragment implements p20 {
    public static final /* synthetic */ int n0 = 0;
    public yn2 i0;
    public ArrayList<no> j0;
    public ArrayList<f> k0;
    public SearchView l0;
    public String m0 = "";

    public class b extends ph1 {
        public final /* synthetic */ no f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(mb1 mb1Var, no noVar) {
            super(mb1Var);
            this.f = noVar;
        }

        @Override // defpackage.mb1
        public final boolean g(Preference preference) {
            return false;
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return this.f.i;
        }
    }

    public class c implements g.c {
        public c() {
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            ChosenObjectSettings chosenObjectSettings = ChosenObjectSettings.this;
            if (chosenObjectSettings.D() == null) {
                return;
            }
            String str = chosenObjectSettings.j0.get(0).h;
            chosenObjectSettings.j0.clear();
            chosenObjectSettings.k0 = new ArrayList<>();
            for (f fVar : collection) {
                fVar.getClass();
                for (no noVar : new ArrayList(fVar.m)) {
                    if (noVar.h.equals(str)) {
                        chosenObjectSettings.j0.add(noVar);
                        if (chosenObjectSettings.m0.isEmpty() || fVar.h().toLowerCase(Locale.getDefault()).contains(chosenObjectSettings.m0)) {
                            chosenObjectSettings.k0.add(fVar);
                        }
                    }
                }
            }
            if (chosenObjectSettings.j0.isEmpty()) {
                chosenObjectSettings.D().finish();
            } else {
                chosenObjectSettings.N0();
            }
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        PreferenceManager preferenceManager = this.a0;
        L0(preferenceManager.a(preferenceManager.a));
    }

    public final void M0() {
        new g(((kr) this.h0).b, false).a(this.i0, new c());
    }

    public final void N0() {
        PreferenceScreen preferenceScreenI0 = I0();
        preferenceScreenI0.c0();
        PreferenceScreen preferenceScreenI02 = I0();
        ChromeImageViewPreference chromeImageViewPreference = new ChromeImageViewPreference(this.a0.a);
        String str = this.j0.get(0).g;
        String str2 = String.format(this.f12J.getContext().getString(R$string.chosen_object_website_reset_confirmation_for), str);
        chromeImageViewPreference.T(str);
        chromeImageViewPreference.Z(R$drawable.ic_delete_white_24dp, R$string.website_settings_revoke_all_permissions_for_device, new n40(this, 12, str2));
        preferenceScreenI02.Y(chromeImageViewPreference);
        Preference preference = new Preference(this.a0.a);
        preference.f17J = R$layout.divider_preference;
        preferenceScreenI02.Y(preference);
        for (int i = 0; i < this.k0.size() && i < this.j0.size(); i++) {
            f fVar = this.k0.get(i);
            no noVar = this.j0.get(i);
            dt3 dt3Var = new dt3(this.a0.a, this.h0, fVar, this.i0);
            dt3Var.m().putSerializable("org.chromium.chrome.preferences.site", fVar);
            dt3Var.r = SingleWebsiteSettings.class.getCanonicalName();
            dt3Var.Z(R$drawable.ic_delete_white_24dp, R$string.website_settings_revoke_device_permission, new c30(this, 10, noVar));
            b bVar = new b(((kr) this.h0).a(), noVar);
            dt3Var.S = bVar;
            nb1.c(bVar, dt3Var, false, true);
            preferenceScreenI0.Y(dt3Var);
        }
        this.k0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        yn2 yn2VarC;
        int i = this.j.getInt("org.chromium.chrome.preferences.content_settings_type");
        Profile profile = ((kr) this.h0).b;
        int i2 = 0;
        while (true) {
            if (i2 >= 29) {
                yn2VarC = null;
                break;
            } else {
                if (yn2.b(i2) == i) {
                    yn2VarC = yn2.c(profile, i2);
                    break;
                }
                i2++;
            }
        }
        this.i0 = yn2VarC;
        this.j0 = (ArrayList) this.j.getSerializable("org.chromium.chrome.preferences.object_infos");
        this.k0 = (ArrayList) this.j.getSerializable("org.chromium.chrome.preferences.site_set");
        String string = this.j.getString("title");
        if (string != null) {
            D().setTitle(string);
        }
        D0();
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R$menu.website_preferences_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R$id.search).getActionView();
        this.l0 = searchView;
        searchView.setImeOptions(33554432);
        this.l0.setOnQueryTextListener(new a());
        this.h0.getClass();
        menu.add(0, R$id.menu_id_site_settings_help, 0, R$string.menu_help).setIcon(re3.a(N(), R$drawable.ic_help_and_feedback, K().getTheme()));
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_site_settings_help) {
            return false;
        }
        zn2 zn2Var = this.h0;
        FragmentActivity fragmentActivityD = D();
        ((kr) zn2Var).getClass();
        ((hu0) hu0.a()).c(fragmentActivityD, fragmentActivityD.getString(org.chromium.chrome.R$string.help_context_settings), null, Profile.d());
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        if (this.k0 == null) {
            M0();
        } else {
            N0();
        }
    }

    public class a implements SearchView.OnQueryTextListener {
        public a() {
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public final void onQueryTextChange(String str) {
            String lowerCase = str.toLowerCase(Locale.getDefault());
            ChosenObjectSettings chosenObjectSettings = ChosenObjectSettings.this;
            if (lowerCase.equals(chosenObjectSettings.m0)) {
                return;
            }
            chosenObjectSettings.m0 = lowerCase;
            chosenObjectSettings.M0();
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public final void a() {
        }
    }

    @Override // defpackage.p20
    public final void t() {
    }
}
