package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: ChromeAccessibilitySettingsDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class po implements InterfaceC0182e0 {

    /* JADX INFO: compiled from: ChromeAccessibilitySettingsDelegate.java */
    public static class a {
        public final boolean a() {
            return SharedPreferencesManager.getInstance().readBoolean("accessibility_tab_switcher", true);
        }
    }

    /* JADX INFO: compiled from: ChromeAccessibilitySettingsDelegate.java */
    public static class b {
        public final boolean a() {
            return gq2.l("dom_distiller.reader_for_accessibility");
        }

        public final void b(boolean z) {
            oj2.O("DomDistiller.ReaderModeAccessibilitySettingSelected", Boolean.valueOf(z).booleanValue());
            oj2.k(Profile.d()).e("dom_distiller.reader_for_accessibility", Boolean.valueOf(z).booleanValue());
        }
    }
}
