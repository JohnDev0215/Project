package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.device.time_zone_monitor.TimeZoneMonitor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OG extends BroadcastReceiver {
    public final /* synthetic */ TimeZoneMonitor a;

    public OG(TimeZoneMonitor timeZoneMonitor) {
        this.a = timeZoneMonitor;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
            Ts.a("TimeZoneMonitor", "unexpected intent", new Object[0]);
        } else {
            TimeZoneMonitor timeZoneMonitor = this.a;
            N.MjxIGcDd(timeZoneMonitor.c, timeZoneMonitor);
        }
    }
}
