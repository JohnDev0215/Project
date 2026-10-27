package com.android.textclassifier.notification;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class CopyCodeActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        handleIntent();
        finish();
    }

    private void handleIntent() {
        String stringExtra = getIntent().getStringExtra("android.intent.extra.TEXT");
        if (TextUtils.isEmpty(stringExtra)) {
            Log.w("CopyCodeActivity", "handleIntent: empty code");
        } else {
            ((ClipboardManager) getSystemService(ClipboardManager.class)).setPrimaryClip(ClipData.newPlainText(null, stringExtra));
            Toast.makeText(getApplicationContext(), R$string.tc_notif_code_copied_to_clipboard, 0).show();
        }
    }
}
