package org.chromium.chrome.browser.app.reengagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import defpackage.ku2;
import org.chromium.chrome.browser.b;

/* JADX INFO: loaded from: classes.dex */
public class ReengagementActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ("launch_ntp".equals(getIntent().getAction())) {
            Intent intentB = b.b(this, false);
            ku2 ku2VarC = ku2.C();
            try {
                startActivity(intentB);
                ku2VarC.close();
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        finish();
    }
}
