package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: PasswordManagerHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qx1 {
    public static void a() {
        oj2.k(Profile.d());
        if (d()) {
            sk skVar = bq.a;
            if (N.M09VlOh_("PasskeyManagementUsingAccountSettingsAndroid")) {
                n3.Z().getClass();
            }
        }
    }

    public static void b(Context context, int i, SettingsLauncher settingsLauncher, boolean z) {
        oj2.U(i, 16, "PasswordManager.ManagePasswordsReferrer");
        if (z) {
            a();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("manage-passwords-referrer", i);
        context.startActivity(settingsLauncher.a(context, "org.chromium.chrome.browser.password_manager.settings.PasswordSettings", bundle));
    }

    public static boolean c() {
        if (!d()) {
            sk skVar = bq.a;
            if (!N.M09VlOh_("UnifiedPasswordManagerAndroidBranding")) {
                return false;
            }
        }
        return true;
    }

    public static boolean d() {
        sk skVar = bq.a;
        if (!N.M09VlOh_("UnifiedPasswordManagerAndroid")) {
            return false;
        }
        int iM37SqSAy = N.M37SqSAy("UnifiedPasswordManagerAndroid", "stage", 0);
        return iM37SqSAy == 0 || !(iM37SqSAy == 1 || iM37SqSAy == 2 || iM37SqSAy != 3);
    }
}
