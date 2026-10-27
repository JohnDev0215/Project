package org.chromium.device.time_zone_monitor;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class TimeZoneMonitor {
    public final a a;
    public long b;

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
                Log.e("cr_TimeZoneMonitor", "unexpected intent");
            } else {
                TimeZoneMonitor timeZoneMonitor = TimeZoneMonitor.this;
                N.MjxIGcDd(timeZoneMonitor.b, timeZoneMonitor);
            }
        }
    }

    public TimeZoneMonitor(long j) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
        a aVar = new a();
        this.a = aVar;
        this.b = j;
        xz.g(xz.a, aVar, intentFilter);
    }

    @CalledByNative
    public static TimeZoneMonitor getInstance(long j) {
        return new TimeZoneMonitor(j);
    }

    @CalledByNative
    public void stop() {
        xz.a.unregisterReceiver(this.a);
        this.b = 0L;
    }
}
