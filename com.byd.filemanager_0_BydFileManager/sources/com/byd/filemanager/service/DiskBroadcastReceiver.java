package com.byd.filemanager.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import com.byd.filemanager.e;

/* JADX INFO: loaded from: classes.dex */
public class DiskBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("device_type");
        com.byd.filemanager.k.b.b("volume action = " + action + ",deviceType=" + stringExtra);
        e eVarF = e.f();
        int iD = eVarF.d(stringExtra);
        if (iD <= 0) {
        }
        action.hashCode();
        switch (action) {
            case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                com.byd.filemanager.k.b.b("attached");
                break;
            case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                com.byd.filemanager.k.b.b("detached");
                break;
            case "android.intent.action.MEDIA_MOUNTED":
                StorageVolume storageVolume = (StorageVolume) intent.getParcelableExtra("android.os.storage.extra.STORAGE_VOLUME");
                com.byd.filemanager.k.b.b("volume mounted volume=" + storageVolume);
                if (storageVolume != null && stringExtra != null) {
                    String uuid = storageVolume.getUuid();
                    if (TextUtils.isEmpty(uuid)) {
                        uuid = SystemProperties.get(iD == 1 ? "sys.byd.mSdcardUuid" : "sys.byd.mUsbotgUuid");
                    }
                    eVarF.c(iD, uuid);
                    break;
                }
                break;
            case "android.intent.action.MEDIA_EJECT":
                com.byd.filemanager.k.b.b("volume unmounted");
                eVarF.B(iD, true);
                break;
        }
    }
}
