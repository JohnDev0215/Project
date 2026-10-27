package org.chromium.ui.base;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import defpackage.xz;
import java.util.concurrent.TimeUnit;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class IdleDetector extends BroadcastReceiver {
    public boolean a;
    public long b;

    private IdleDetector() {
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
        xz.g(xz.a, this, intentFilter);
    }

    @CalledByNative
    public static IdleDetector create() {
        return new IdleDetector();
    }

    @CalledByNative
    public final long getIdleTime() {
        if (this.a) {
            return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.b);
        }
        return 0L;
    }

    @CalledByNative
    public final boolean isScreenLocked() {
        Context context = xz.a;
        if (((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return true;
        }
        if (((PowerManager) context.getSystemService("power")) == null) {
            return false;
        }
        return !r2.isInteractive();
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            this.a = true;
            this.b = SystemClock.elapsedRealtime();
        } else if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            this.a = false;
            this.b = 0L;
        }
    }
}
