package com.ts.appservice.settings.impl.cluster;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ScreenAngleContentObserver extends ContentObserver {
    private static final int MSG_SCREEN_ANGLE = 1;
    private static final String TAG = ScreenAngleContentObserver.class.getSimpleName();
    private Context mContext;
    private Handler mHandler;

    public ScreenAngleContentObserver(Handler handler) {
        super(handler);
    }

    public ScreenAngleContentObserver(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        int angle = Settings.System.getInt(this.mContext.getContentResolver(), "orientation_angle", 0);
        LogUtil.info(TAG, "get orientation angle value : " + angle);
        Message message = this.mHandler.obtainMessage(1);
        message.arg1 = angle;
        this.mHandler.sendMessage(message);
    }
}
