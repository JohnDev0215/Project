package org.chromium.chrome.browser.incognito;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.ku2;
import defpackage.q11;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: loaded from: classes.dex */
public class IncognitoTabLauncher extends Activity {
    public static final /* synthetic */ int e = 0;

    public static boolean a(Intent intent) {
        return q11.g(intent) && q11.i(intent, "org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", false);
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intentB = b.b(this, true);
        intentB.putExtra("org.chromium.chrome.browser.senders_package_name", CustomTabsConnection.e().d(CustomTabsSessionToken.b(getIntent())));
        intentB.putExtra("org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", true);
        ku2 ku2VarC = ku2.C();
        try {
            startActivity(intentB);
            ku2VarC.close();
            finish();
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
