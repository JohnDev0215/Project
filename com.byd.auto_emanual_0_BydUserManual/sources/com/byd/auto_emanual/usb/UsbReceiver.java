package com.byd.auto_emanual.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.auto_emanual.Config;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.util.FileUtil;
import com.byd.auto_emanual.util.MLog;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class UsbReceiver extends BroadcastReceiver {
    public static final String ZIP_NAME_IN_USB = "zip_name_in_usb";
    private final String TAG = UsbReceiver.class.getSimpleName();
    private final String resourceFileName = File.separator + FileUtil.getZipInUsb();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String path;
        String action = intent.getAction();
        path = intent.getData() != null ? intent.getData().getPath() : null;
        MLog.i(this.TAG, "onReceive " + action + " " + path);
        action.hashCode();
        switch (action) {
            case "android.intent.action.MEDIA_MOUNTED":
                String str = path + this.resourceFileName;
                if (!FileUtil.exists(str)) {
                    MLog.i(this.TAG, "no resource file of UserManual in U disk.");
                    break;
                } else {
                    if (!FileUtil.existsAndHasChild(Config.ROOT_PATH_IN_DISK)) {
                        MLog.i(this.TAG, "no partition on device.");
                    } else {
                        startUsbActivity(str);
                    }
                    break;
                }
                break;
            case "android.intent.action.MEDIA_UNMOUNTED":
            case "android.intent.action.MEDIA_EJECT":
                Intent intent2 = new Intent();
                intent2.setAction("usb_removed_state");
                intent2.putExtra("usb_state", "removed");
                MApp.context.sendBroadcast(intent2);
                break;
        }
    }

    private void startUsbActivity(String resourceFile) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setClassName(MApp.context, UsbActivity.class.getName());
        intent.putExtra(ZIP_NAME_IN_USB, resourceFile);
        MApp.context.startActivity(intent);
    }
}
