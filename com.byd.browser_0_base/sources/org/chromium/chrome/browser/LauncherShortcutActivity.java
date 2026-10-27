package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class LauncherShortcutActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if (!action.equals("chromium.shortcut.action.OPEN_NEW_TAB") && !action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB")) {
            finish();
            return;
        }
        Intent intentB = b.b(this, action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB"));
        intentB.putExtra("com.android.chrome.invoked_from_shortcut", true);
        startActivity(intentB);
        finish();
    }
}
