package org.chromium.content.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class TimeZoneMonitor {
    private static final String TAG = "cr.TimeZoneMonitor";
    private final Context mAppContext;
    private final BroadcastReceiver mBroadcastReceiver;
    private final IntentFilter mFilter;
    private long mNativePtr;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeTimeZoneChangedFromJava(long j);

    private TimeZoneMonitor(Context context, long j) {
        IntentFilter intentFilter = new IntentFilter(MotsBroadcastReceiver.TIMEZONE_CHANGED);
        this.mFilter = intentFilter;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.chromium.content.browser.TimeZoneMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (!intent.getAction().equals(MotsBroadcastReceiver.TIMEZONE_CHANGED)) {
                    Log.e(TimeZoneMonitor.TAG, "unexpected intent", new Object[0]);
                } else {
                    TimeZoneMonitor timeZoneMonitor = TimeZoneMonitor.this;
                    timeZoneMonitor.nativeTimeZoneChangedFromJava(timeZoneMonitor.mNativePtr);
                }
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mNativePtr = j;
        applicationContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    static TimeZoneMonitor getInstance(Context context, long j) {
        return new TimeZoneMonitor(context, j);
    }

    void stop() {
        this.mAppContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mNativePtr = 0L;
    }
}
