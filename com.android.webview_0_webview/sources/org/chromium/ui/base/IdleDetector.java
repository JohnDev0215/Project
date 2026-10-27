package org.chromium.ui.base;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import defpackage.Is;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IdleDetector extends BroadcastReceiver {
    public boolean a;
    public long b;

    public IdleDetector() {
        if (isScreenLocked()) {
            this.a = true;
            this.b = SystemClock.elapsedRealtime();
        } else {
            this.a = false;
            this.b = 0L;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        Is.a.registerReceiver(this, intentFilter);
    }

    public static IdleDetector create() {
        return new IdleDetector();
    }

    private long getIdleTime() {
        if (this.a) {
            return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.b);
        }
        return 0L;
    }

    private boolean isScreenLocked() {
        return ((KeyguardManager) Is.a.getSystemService("keyguard")).inKeyguardRestrictedInputMode() || !((PowerManager) Is.a.getSystemService("power")).isInteractive();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            this.a = true;
            this.b = SystemClock.elapsedRealtime();
        } else if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            this.a = false;
            this.b = 0L;
        }
    }
}
