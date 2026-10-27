package org.chromium.device.time_zone_monitor;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import defpackage.Is;
import defpackage.OG;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TimeZoneMonitor {
    public final IntentFilter a = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
    public final BroadcastReceiver b;
    public long c;

    public TimeZoneMonitor(long j) {
        OG og = new OG(this);
        this.b = og;
        this.c = j;
        Is.a.registerReceiver(og, this.a);
    }

    public static TimeZoneMonitor getInstance(long j) {
        return new TimeZoneMonitor(j);
    }

    public void stop() {
        Is.a.unregisterReceiver(this.b);
        this.c = 0L;
    }
}
