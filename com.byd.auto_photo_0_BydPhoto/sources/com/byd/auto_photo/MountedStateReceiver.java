package com.byd.auto_photo;

import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import b.a.k.h;
import com.byd.util.i;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class MountedStateReceiver extends BroadcastReceiver {
    private void a(Context context, boolean z) {
        try {
            MoreDevShareService.d(context, z);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(context, (Class<?>) StartServiceActivity.class);
            intent.setAction("com.byd.action_dev_share_service");
            intent.putExtra("mount", z);
            intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
            context.startActivity(intent);
        }
    }

    private void b(Context context, boolean z, boolean z2, String str) {
        try {
            i.d(context).f(z, z2, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("device_type");
        Uri data = intent.getData();
        String path = data != null ? data.getPath() : null;
        w.c("Photo MountedStateReceiver : " + action + " " + stringExtra + " mount path:" + path);
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            if ("sdcard".equals(stringExtra)) {
                b(context, true, false, path);
                return;
            }
            if ("usbotg".equals(stringExtra)) {
                b(context, true, true, path);
                return;
            } else {
                if (path == null || !path.startsWith("/storage/FFFF-")) {
                    return;
                }
                a(context, true);
                return;
            }
        }
        if ("android.intent.action.MEDIA_EJECT".equals(action)) {
            if ("sdcard".equals(stringExtra)) {
                b(context, false, false, path);
                h.e(context).l(path, false);
            } else if ("usbotg".equals(stringExtra)) {
                b(context, false, true, path);
                h.e(context).l(path, true);
            } else {
                if (path == null || !path.startsWith("/storage/FFFF-")) {
                    return;
                }
                a(context, false);
            }
        }
    }
}
