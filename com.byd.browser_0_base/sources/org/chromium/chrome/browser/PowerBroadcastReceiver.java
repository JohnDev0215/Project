package org.chromium.chrome.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import defpackage.or1;
import defpackage.xz;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public class PowerBroadcastReceiver extends BroadcastReceiver {
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final a b = new a();

    public static class a implements Runnable {
        public final Handler e = new Handler(Looper.getMainLooper());
        public int f = 0;

        @Override // java.lang.Runnable
        public final void run() {
            if (this.f != 1) {
                return;
            }
            this.f = 3;
            if (or1.e) {
                return;
            }
            or1.c(0L);
        }
    }

    public final void a() {
        Object obj = ThreadUtils.a;
        if (!((PowerManager) xz.a.getSystemService("power")).isInteractive()) {
            if (this.a.getAndSet(true)) {
                return;
            }
            xz.g(xz.a, this, new IntentFilter("android.intent.action.SCREEN_ON"));
        } else {
            a aVar = this.b;
            if (aVar.f == 1) {
                return;
            }
            aVar.f = 1;
            aVar.e.postDelayed(aVar, 5000L);
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && ApplicationStatus.hasVisibleActivities()) {
            a aVar = this.b;
            if (aVar.f != 1) {
                aVar.f = 1;
                aVar.e.postDelayed(aVar, 5000L);
            }
            if (this.a.getAndSet(false)) {
                xz.a.unregisterReceiver(this);
            }
        }
    }
}
