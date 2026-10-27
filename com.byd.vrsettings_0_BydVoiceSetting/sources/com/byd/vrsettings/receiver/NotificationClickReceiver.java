package com.byd.vrsettings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrsettings.activity.OtaUpgradeActivity;
import com.byd.vrsettings.service.NotifyManager;

/* JADX INFO: loaded from: classes.dex */
public class NotificationClickReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NotifyManager.getInstance().cancelAll();
        String stringExtra = intent.getStringExtra("NotifyManager");
        int intExtra = intent.getIntExtra("notifyid", 0);
        Intent intent2 = new Intent(context, (Class<?>) OtaUpgradeActivity.class);
        intent2.setAction(NotifyManager.notifyAction);
        intent2.putExtra("NotifyManager", stringExtra);
        intent2.putExtra("notifyid", intExtra);
        intent2.addFlags(268435456);
        context.startActivity(intent2);
    }
}
