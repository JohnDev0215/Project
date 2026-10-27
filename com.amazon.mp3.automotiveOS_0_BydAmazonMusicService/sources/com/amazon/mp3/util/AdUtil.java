package com.amazon.mp3.util;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* JADX INFO: loaded from: classes4.dex */
public class AdUtil {
    private static String TAG = "com.amazon.mp3.util.AdUtil";

    @Nullable
    @WorkerThread
    public static String getADId(Context context) {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (Exception e) {
            Log.error(TAG, e.toString());
            return null;
        }
    }
}
