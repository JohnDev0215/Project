package com.byd.auto_photo;

import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    private void a(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return;
        }
        try {
            context.startService(new Intent(context, (Class<?>) ApiService.class));
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(context, (Class<?>) StartServiceActivity.class);
            intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
            intent.setAction("com.byd.boot_complete");
            context.startActivity(intent);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            MoreDevShareService.f(context);
            a(context);
        }
    }
}
