package com.android.server.telecom.components;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public class UserCallActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, "UserCallActivity");
        wakeLockNewWakeLock.acquire();
        Log.startSession("UCA.oC");
        try {
            Intent intent = getIntent();
            verifyCallAction(intent);
            new UserCallIntentProcessor(this, new UserHandle(((UserManager) getSystemService("user")).getUserHandle())).processIntent(new Intent(intent), getCallingPackage(), true, false);
            Log.endSession();
            wakeLockNewWakeLock.release();
            Log.i(this, "onCreate done", new Object[0]);
            finish();
        } catch (Throwable th) {
            Log.endSession();
            wakeLockNewWakeLock.release();
            throw th;
        }
    }

    private void verifyCallAction(Intent intent) {
        if (!UserCallActivity.class.getName().equals(intent.getComponent().getClassName()) || "android.intent.action.CALL".equals(intent.getAction())) {
            return;
        }
        Log.w(this, "Attempt to deliver non-CALL action; forcing to CALL", new Object[0]);
        intent.setAction("android.intent.action.CALL");
    }
}
