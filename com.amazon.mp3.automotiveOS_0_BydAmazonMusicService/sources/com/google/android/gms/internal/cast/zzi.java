package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@MainThread
public final class zzi {
    public String zzb;

    @Nullable
    public String zzc;
    public long zzd = zza;
    public int zze = 1;
    public String zzf;
    public int zzg;
    public String zzh;
    public int zzi;
    private static final Logger zzj = new Logger("ApplicationAnalyticsSession");
    public static long zza = System.currentTimeMillis();

    private zzi() {
    }

    public static zzi zza() {
        zzi zziVar = new zzi();
        zza++;
        return zziVar;
    }

    @Nullable
    public static zzi zzb(@Nullable SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return null;
        }
        zzi zziVar = new zzi();
        if (!sharedPreferences.contains("application_id")) {
            return null;
        }
        zziVar.zzb = sharedPreferences.getString("application_id", "");
        if (!sharedPreferences.contains("receiver_metrics_id")) {
            return null;
        }
        zziVar.zzc = sharedPreferences.getString("receiver_metrics_id", "");
        if (!sharedPreferences.contains("analytics_session_id")) {
            return null;
        }
        zziVar.zzd = sharedPreferences.getLong("analytics_session_id", 0L);
        if (!sharedPreferences.contains("event_sequence_number")) {
            return null;
        }
        zziVar.zze = sharedPreferences.getInt("event_sequence_number", 0);
        if (!sharedPreferences.contains("receiver_session_id")) {
            return null;
        }
        zziVar.zzf = sharedPreferences.getString("receiver_session_id", "");
        zziVar.zzg = sharedPreferences.getInt("device_capabilities", 0);
        zziVar.zzh = sharedPreferences.getString("device_model_name", "");
        zziVar.zzi = sharedPreferences.getInt("analytics_session_start_type", 0);
        return zziVar;
    }

    public final void zzc(@Nullable SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return;
        }
        zzj.d("Save the ApplicationAnalyticsSession to SharedPreferences %s", sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("application_id", this.zzb);
        editorEdit.putString("receiver_metrics_id", this.zzc);
        editorEdit.putLong("analytics_session_id", this.zzd);
        editorEdit.putInt("event_sequence_number", this.zze);
        editorEdit.putString("receiver_session_id", this.zzf);
        editorEdit.putInt("device_capabilities", this.zzg);
        editorEdit.putString("device_model_name", this.zzh);
        editorEdit.putInt("analytics_session_start_type", this.zzi);
        editorEdit.apply();
    }
}
