package com.google.android.gms.internal.wearable;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzd {
    public static final int zza;

    static {
        int i = 0;
        if (Build.VERSION.SDK_INT >= 30) {
            String str = Build.VERSION.CODENAME;
            if (str.length() == 1 && str.charAt(0) >= 'S' && str.charAt(0) <= 'Z') {
                i = voOSType.VOOSMP_SRC_FFAUDIO_WMA;
            }
        }
        zza = i;
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getActivity(context, 0, intent, i2);
    }
}
