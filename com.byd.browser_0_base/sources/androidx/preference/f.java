package androidx.preference;

import android.os.Handler;
import androidx.preference.Preference;
import androidx.preference.e;
import org.chromium.chrome.browser.language.settings.LanguageSettings;

/* JADX INFO: compiled from: PreferenceGroupAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Preference.d {
    public final /* synthetic */ PreferenceGroup e;
    public final /* synthetic */ e f;

    public f(e eVar, PreferenceGroup preferenceGroup) {
        this.f = eVar;
        this.e = preferenceGroup;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        PreferenceGroup preferenceGroup = this.e;
        preferenceGroup.f0(Integer.MAX_VALUE);
        e eVar = this.f;
        Handler handler = eVar.l;
        e.a aVar = eVar.m;
        handler.removeCallbacks(aVar);
        handler.post(aVar);
        if (preferenceGroup.Z == null) {
            return true;
        }
        int i = LanguageSettings.l0;
        org.chromium.chrome.browser.language.settings.c.h(4);
        return true;
    }
}
