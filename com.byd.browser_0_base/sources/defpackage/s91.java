package defpackage;

import defpackage.wp2;
import java.lang.ref.WeakReference;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: LocaleManagerDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class s91 {
    public boolean a;
    public u91 c;
    public SettingsLauncher d;
    public WeakReference<wp2> b = new WeakReference<>(null);
    public final a e = new a();

    public s91() {
        SharedPreferencesManager.getInstance().d(-1, "com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN");
    }

    /* JADX INFO: compiled from: LocaleManagerDelegate.java */
    public class a implements wp2.b {
        public a() {
        }

        @Override // wp2.b
        public final void j(Object obj) {
            s91.this.d.g(xz.a, SearchEngineSettings.class);
        }

        @Override // wp2.b
        public final void d(Object obj) {
        }
    }
}
