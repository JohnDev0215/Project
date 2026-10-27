package com.amazon.avod.qos.internal;

import android.content.Context;
import android.content.Intent;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class LowMemoryReceiver extends ReportingBroadcastReceiver<LowMemoryReporter> {
    public LowMemoryReceiver(Context context) {
        super(context, "com.amazon.avod.action.onLowMemory");
    }

    @Override // com.amazon.avod.qos.internal.ReportingBroadcastReceiver
    public void onReceive(Intent intent) {
        T t = this.mReporter;
        if (t != 0) {
            ((LowMemoryReporter) t).reportLowMemory();
        } else {
            DLog.warnf("Reporter is null, not sending low memory event.");
        }
    }
}
