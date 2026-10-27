package com.byd.auto_photo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class EventCenterReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("event_center_data");
        w.c("PhotoApp EventCenterReceiver receive : " + intent.getAction());
        if (bundleExtra != null) {
            Intent intent2 = new Intent(context, (Class<?>) MoreDevShareService.class);
            intent2.putExtra("opt", 7);
            intent2.putExtras(bundleExtra);
            context.startService(intent2);
        }
    }
}
