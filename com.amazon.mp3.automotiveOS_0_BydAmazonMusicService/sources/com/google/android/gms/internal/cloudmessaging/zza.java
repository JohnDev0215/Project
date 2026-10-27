package com.google.android.gms.internal.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zza {
    public static final int zza;

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028  */
    static {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33554432(0x2000000, float:9.403955E-38)
            r2 = 0
            r3 = 31
            if (r0 < r3) goto La
            goto L29
        La:
            r3 = 30
            if (r0 < r3) goto L28
            java.lang.String r0 = android.os.Build.VERSION.CODENAME
            int r3 = r0.length()
            r4 = 1
            if (r3 != r4) goto L28
            char r3 = r0.charAt(r2)
            r4 = 83
            if (r3 < r4) goto L28
            char r0 = r0.charAt(r2)
            r3 = 90
            if (r0 > r3) goto L28
            goto L29
        L28:
            r1 = r2
        L29:
            com.google.android.gms.internal.cloudmessaging.zza.zza = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cloudmessaging.zza.<clinit>():void");
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, 0, intent, i2);
    }
}
