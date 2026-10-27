package com.amazon.avod.qos.internal;

import android.content.Context;
import android.content.Intent;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.reporter.PlaybackEventReporter;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class HdmiReceiver extends ReportingBroadcastReceiver<PlaybackEventReporter> {
    public HdmiReceiver(Context context) {
        super(context, "android.intent.action.HDMI_AUDIO_PLUG");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.avod.qos.internal.ReportingBroadcastReceiver
    public void onReceive(Intent intent) {
        int intExtra = intent.getIntExtra("state", 0);
        T t = this.mReporter;
        if (t != 0) {
            ((PlaybackEventReporter) t).onHdmiStatusChange(intExtra == 1, TimeSpan.now());
        } else {
            DLog.warnf("Reporter is null, not sending HDMI event.");
        }
    }
}
