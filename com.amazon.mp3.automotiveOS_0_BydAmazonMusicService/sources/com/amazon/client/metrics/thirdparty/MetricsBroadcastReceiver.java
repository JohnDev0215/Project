package com.amazon.client.metrics.thirdparty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.dp.logger.DPLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsBroadcastReceiver extends BroadcastReceiver {
    private static final DPLogger log = new DPLogger("MetricsBroadcastReceiver");
    private static List<UploadIntentListener> sUploadIntentListenerList = new ArrayList();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        if ("com.amazon.intent.action.UPLOAD_METRICS".equals(action)) {
            if (context.checkCallingOrSelfPermission("amazon.permission.UPLOAD_METRICS") == -1) {
                log.error("onReceive", "Metrics upload permission denied.", new Object[0]);
                return;
            }
            log.info("onReceive", "Upload metrics intent received. Notifying listeners.", new Object[0]);
            Iterator<UploadIntentListener> it = sUploadIntentListenerList.iterator();
            while (it.hasNext()) {
                it.next().onUploadIntentReceived();
            }
            return;
        }
        log.verbose("onReceive", "Received unexpected intent.", " intent: ", intent, " action: ", action);
    }

    protected static void addUploadIntentListener(UploadIntentListener uploadIntentListener) {
        sUploadIntentListenerList.add(uploadIntentListener);
    }

    public static void shutdown() {
        sUploadIntentListenerList.clear();
    }
}
