package com.byd.vrsettings.receiver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class CarSettingObserver extends ContentObserver {
    public static String TAG = "NEU_Setting_CarSettingObserver";

    public CarSettingObserver(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        RLog.e(TAG, "CarSettingObserver uri:" + uri.toString());
        String string = uri.toString();
        if (string.hashCode() != -1627602415) {
            return;
        }
        string.equals("content://carsettings/global/sys_screen_mode");
    }
}
