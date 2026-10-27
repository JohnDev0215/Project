package com.engine.tbox.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.engine.tbox.core.utils.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class NitzUtils {
    private static final String NITZ_DATA_STRING = "nitz_data";
    private static final String TAG = "NitzUtils";
    private ContentResolver mContentResolver;
    private Context mContext;

    public interface NitzCallback {
        void onDelete();
    }

    public NitzUtils(Context context, Handler handler, final NitzCallback callback) {
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        contentResolver.registerContentObserver(Settings.Global.getUriFor(NITZ_DATA_STRING), true, new ContentObserver(handler) { // from class: com.engine.tbox.utils.NitzUtils.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri, int flags) {
                LogUtils.d(NitzUtils.TAG, "onChange:" + selfChange + " uri:" + uri + " flags:" + flags, new Object[0]);
                String value = Settings.Global.getString(NitzUtils.this.mContentResolver, NitzUtils.NITZ_DATA_STRING);
                if (TextUtils.isEmpty(value)) {
                    LogUtils.d(NitzUtils.TAG, "delete the nitz data", new Object[0]);
                    callback.onDelete();
                }
            }
        });
    }

    public void setNitzData(String nitzData) {
        LogUtils.i(TAG, "setNitzData:" + nitzData, new Object[0]);
        if (nitzData != null) {
            Settings.Global.putString(this.mContentResolver, NITZ_DATA_STRING, nitzData);
        }
    }

    public String getNitz() {
        return Settings.Global.getString(this.mContentResolver, NITZ_DATA_STRING);
    }
}
