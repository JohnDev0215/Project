package defpackage;

import J.N;
import android.os.Build;
import org.chromium.chrome.browser.device_reauth.ReauthenticatorBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: IncognitoReauthManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ny0 {
    public final ReauthenticatorBridge a = new ReauthenticatorBridge(6);

    /* JADX INFO: compiled from: IncognitoReauthManager.java */
    public interface a {
        void a();

        void b();

        void c();
    }

    public static boolean a(Profile profile) {
        return b() && wy0.a() && oj2.k(profile).a("incognito.incognito_reauthentication");
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 30 && bq.D.a();
    }

    public final void c(a aVar) {
        ReauthenticatorBridge reauthenticatorBridge = this.a;
        if (!N.MuzO7zcq(reauthenticatorBridge.a) || !b()) {
            aVar.b();
        } else {
            reauthenticatorBridge.b = new xr(23, aVar);
            N.Mt502WDx(reauthenticatorBridge.a, false);
        }
    }
}
