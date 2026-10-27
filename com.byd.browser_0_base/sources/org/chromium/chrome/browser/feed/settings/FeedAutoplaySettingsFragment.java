package org.chromium.chrome.browser.feed.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.pp0;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: loaded from: classes.dex */
public class FeedAutoplaySettingsFragment extends d implements pp0, Preference.c {
    public RadioButtonGroupVideoPreviewsPreference h0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.feed_autoplay_preferences);
        D().setTitle(R$string.feed_autoplay_title);
        RadioButtonGroupVideoPreviewsPreference radioButtonGroupVideoPreviewsPreference = (RadioButtonGroupVideoPreviewsPreference) f("video_previews_pref");
        this.h0 = radioButtonGroupVideoPreviewsPreference;
        radioButtonGroupVideoPreviewsPreference.setOnPreferenceChangeListener(this);
        this.h0.S = N.MC3MPpYa();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        N.MEA3kwB5(((Integer) obj).intValue());
        return true;
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
    }
}
