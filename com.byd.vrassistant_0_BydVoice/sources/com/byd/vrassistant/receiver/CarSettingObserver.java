package com.byd.vrassistant.receiver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.byd.vrassistant.eventbus.SkinEvent;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class CarSettingObserver extends ContentObserver {
    public static String TAG = "NEU_CarSettingObserver";

    public CarSettingObserver(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        RLog.e(TAG, "CarSettingObserver uri:" + uri.toString());
        String string = uri.toString();
        string.hashCode();
        if (string.equals("content://carsettings/global/sys_screen_mode")) {
            int skinType = SystemSettingManager.get().getSkinType();
            RLog.e(TAG, "light value:" + skinType);
            EventBus.getDefault().post(new SkinEvent(skinType));
        }
    }
}
