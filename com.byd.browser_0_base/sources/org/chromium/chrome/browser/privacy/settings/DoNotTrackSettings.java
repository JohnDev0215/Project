package org.chromium.chrome.browser.privacy.settings;

import android.os.Bundle;
import androidx.preference.d;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes2.dex */
public class DoNotTrackSettings extends d {
    public static final /* synthetic */ int h0 = 0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.do_not_track_preferences);
        D().setTitle(R$string.do_not_track_title);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f("do_not_track_switch");
        PrefService prefServiceK = oj2.k(Profile.d());
        chromeSwitchPreference.Y(prefServiceK.a("enable_do_not_track"));
        chromeSwitchPreference.setOnPreferenceChangeListener(new pq2(23, prefServiceK));
    }
}
