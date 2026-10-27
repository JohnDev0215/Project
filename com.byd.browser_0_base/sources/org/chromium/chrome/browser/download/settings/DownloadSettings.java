package org.chromium.chrome.browser.download.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.gq;
import defpackage.i62;
import defpackage.oj2;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class DownloadSettings extends d implements Preference.c, i62 {
    public Profile h0;
    public DownloadLocationPreference i0;
    public ChromeSwitchPreference j0;

    public class a implements gq {
        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return N.MGOzH4qx();
        }
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.menu_downloads);
        qj2.a(this, R$xml.download_preferences);
        oj2.k(this.h0);
        N.MGOzH4qx();
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("location_prompt_enabled");
        this.j0 = chromeSwitchPreference;
        chromeSwitchPreference.setOnPreferenceChangeListener(this);
        this.j0.b0(new a());
        this.i0 = (DownloadLocationPreference) f("location_change");
    }

    @Override // defpackage.i62
    public final void m(Profile profile) {
        this.h0 = profile;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        this.i0.Y();
        if (N.MGOzH4qx()) {
            this.j0.Y(oj2.k(Profile.d()).a("download.prompt_for_download"));
        } else {
            this.j0.Y(oj2.k(Profile.d()).b("download.prompt_for_download_android") != 2);
            this.j0.J(true);
        }
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        if ("location_prompt_enabled".equals(preference.p)) {
            if (!((Boolean) obj).booleanValue()) {
                N.MPBZLcVx(oj2.k(Profile.d()).a, "download.prompt_for_download_android", 2);
            } else if (oj2.k(Profile.d()).b("download.prompt_for_download_android") != 0) {
                N.MPBZLcVx(oj2.k(Profile.d()).a, "download.prompt_for_download_android", 1);
            }
        }
        return true;
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.a
    public final void y(Preference preference) {
        if (!(preference instanceof DownloadLocationPreference)) {
            super.y(preference);
            return;
        }
        DownloadLocationPreferenceDialog downloadLocationPreferenceDialog = new DownloadLocationPreferenceDialog();
        Bundle bundle = new Bundle(1);
        bundle.putString("key", ((DownloadLocationPreference) preference).p);
        downloadLocationPreferenceDialog.C0(bundle);
        downloadLocationPreferenceDialog.E0(0, this);
        downloadLocationPreferenceDialog.M0(this.v, "DownloadLocationPreferenceDialog");
    }
}
