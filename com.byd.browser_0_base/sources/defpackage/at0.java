package defpackage;

import androidx.appcompat.app.AppCompatDelegate;
import defpackage.on1;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: GlobalNightModeStateProviderHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at0 {
    public static on1 a;

    public static on1 a() {
        if (a == null) {
            if (iv.e().g("force-enable-night-mode")) {
                a = new a();
            } else {
                fy2 fy2VarA = fy2.a();
                if (i32.e == null) {
                    i32.e = new i32();
                }
                a = new zs0(fy2VarA, i32.e, SharedPreferencesManager.getInstance());
            }
        }
        return a;
    }

    /* JADX INFO: compiled from: GlobalNightModeStateProviderHolder.java */
    public static class a implements on1 {
        public final boolean e;

        public a() {
            boolean zG = iv.e().g("force-enable-night-mode");
            this.e = zG;
            AppCompatDelegate.B(zG ? 2 : 1);
        }

        @Override // defpackage.on1
        public final boolean d() {
            return this.e;
        }

        @Override // defpackage.on1
        public final void b(on1.a aVar) {
        }

        @Override // defpackage.on1
        public final void e(on1.a aVar) {
        }
    }
}
