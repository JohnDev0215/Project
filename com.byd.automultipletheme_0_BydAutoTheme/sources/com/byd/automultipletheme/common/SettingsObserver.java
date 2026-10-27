package com.byd.automultipletheme.common;

import android.content.Intent;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.CarSettings;
import android.net.Uri;
import android.os.Handler;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class SettingsObserver extends ContentObserver {
    public SettingsObserver(Handler handler) {
        super(handler);
    }

    public void onChange(boolean z, Uri uri, String str, int i) {
        super.onChange(z, uri, str, i);
        if ("INSTRUMENT_THEME_SUCCESS".equals(str)) {
            int i2 = CarSettings.UserTableData.getInt(Utils.getApp().getContentResolver(), "INSTRUMENT_THEME_SUCCESS");
            com.blankj.utilcode.util.LogUtils.i("instrument_theme_errorCode:", Integer.valueOf(i2));
            if (i2 == 2) {
                Intent intent = new Intent(Const.ACTION_ERROR_REMINDER);
                intent.putExtra("ErrorCode", 2);
                intent.setPackage(BuildConfig.APPLICATION_ID);
                Utils.getApp().sendBroadcast(intent);
                return;
            }
            if (i2 == 0) {
                Intent intent2 = new Intent(Const.ACTION_ERROR_REMINDER);
                intent2.putExtra("ErrorCode", 0);
                intent2.setPackage(BuildConfig.APPLICATION_ID);
                Utils.getApp().sendBroadcast(intent2);
                return;
            }
            if (i2 == 1) {
                Intent intent3 = new Intent(Const.ACTION_ERROR_REMINDER);
                intent3.putExtra("ErrorCode", 1);
                intent3.setPackage(BuildConfig.APPLICATION_ID);
                Utils.getApp().sendBroadcast(intent3);
            }
        }
    }
}
