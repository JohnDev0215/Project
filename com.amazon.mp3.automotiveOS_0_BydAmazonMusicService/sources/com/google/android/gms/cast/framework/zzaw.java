package com.google.android.gms.cast.framework;

import android.content.Context;
import android.preference.PreferenceManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzaw {
    public static void zza(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("googlecast-introOverlayShown", true).apply();
    }

    public static boolean zzb(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("googlecast-introOverlayShown", false);
    }
}
