package com.ts.appservice.settings.impl.cluster;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.android.common.speech.LoggingEvents;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class VersatileThemeContentObserver extends ContentObserver {
    private static final String BYD_APP_THEME = "byd_app_theme";
    private static final String TAG = VersatileThemeContentObserver.class.getSimpleName();
    private Context mContext;
    private Handler mHandler;

    public VersatileThemeContentObserver(Handler handler) {
        super(handler);
    }

    public VersatileThemeContentObserver(Context context, Handler handler) {
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
    }

    public void onChange(boolean selfChange, Uri uri, String key, int operationFlag) {
        super.onChange(selfChange, uri);
        if (BYD_APP_THEME.equals(key) && operationFlag == 3) {
            int uuid = getInt(this.mContext.getContentResolver(), BYD_APP_THEME);
            LogUtil.info(TAG, "get byd app theme :" + uuid);
            Message message = this.mHandler.obtainMessage(3);
            message.arg1 = uuid;
            this.mHandler.sendMessage(message);
        }
    }

    public int getInt(ContentResolver resolver, String name) {
        int value = 0;
        Cursor cursor = resolver.query(IClusterSettingServiceBinder.CONTENT_URI, null, "key='" + name + "'", null, null);
        if (cursor != null) {
            try {
                int count = cursor.getCount();
                if (count > 0) {
                    while (!cursor.isLast()) {
                        cursor.moveToNext();
                        value = cursor.getInt(cursor.getColumnIndex(LoggingEvents.VoiceSearch.EXTRA_QUERY_UPDATED_VALUE));
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return value;
    }
}
