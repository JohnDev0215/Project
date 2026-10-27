package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@MainThread
public final class zzl {
    private static final Logger zza = new Logger("FeatureUsageAnalytics");
    private static final String zzb = "21.1.0";

    @Nullable
    private static zzl zzc;
    private final zzd zzd;
    private final SharedPreferences zze;
    private final String zzf;
    private final Runnable zzg;
    private final Handler zzh;
    private final Set zzi;
    private final Set zzj;
    private long zzk;

    private zzl(SharedPreferences sharedPreferences, zzd zzdVar, String str) {
        this.zze = sharedPreferences;
        this.zzd = zzdVar;
        this.zzf = str;
        HashSet hashSet = new HashSet();
        this.zzi = hashSet;
        HashSet hashSet2 = new HashSet();
        this.zzj = hashSet2;
        this.zzh = new zzcv(Looper.getMainLooper());
        this.zzg = new Runnable() { // from class: com.google.android.gms.internal.cast.zzk
            @Override // java.lang.Runnable
            public final void run() {
                zzl.zzc(this.zza);
            }
        };
        String string = sharedPreferences.getString("feature_usage_sdk_version", null);
        String string2 = sharedPreferences.getString("feature_usage_package_name", null);
        hashSet.clear();
        hashSet2.clear();
        this.zzk = 0L;
        if (!zzb.equals(string) || !str.equals(string2)) {
            HashSet hashSet3 = new HashSet();
            for (String str2 : sharedPreferences.getAll().keySet()) {
                if (str2.startsWith("feature_usage_timestamp_")) {
                    hashSet3.add(str2);
                }
            }
            hashSet3.add("feature_usage_last_report_time");
            zzg(hashSet3);
            this.zze.edit().putString("feature_usage_sdk_version", zzb).putString("feature_usage_package_name", this.zzf).apply();
            return;
        }
        this.zzk = sharedPreferences.getLong("feature_usage_last_report_time", 0L);
        long jZzi = zzi();
        HashSet hashSet4 = new HashSet();
        for (String str3 : sharedPreferences.getAll().keySet()) {
            if (str3.startsWith("feature_usage_timestamp_")) {
                long j = this.zze.getLong(str3, 0L);
                if (j != 0 && jZzi - j > 1209600000) {
                    hashSet4.add(str3);
                } else if (str3.startsWith("feature_usage_timestamp_reported_feature_")) {
                    zzkk zzkkVarZze = zze(str3.substring(41));
                    this.zzj.add(zzkkVarZze);
                    this.zzi.add(zzkkVarZze);
                } else if (str3.startsWith("feature_usage_timestamp_detected_feature_")) {
                    this.zzi.add(zze(str3.substring(41)));
                }
            }
        }
        zzg(hashSet4);
        Preconditions.checkNotNull(this.zzh);
        Preconditions.checkNotNull(this.zzg);
        zzh();
    }

    public static synchronized zzl zza(SharedPreferences sharedPreferences, zzd zzdVar, String str) {
        if (zzc == null) {
            zzc = new zzl(sharedPreferences, zzdVar, str);
        }
        return zzc;
    }

    @VisibleForTesting
    static String zzb(String str, String str2) {
        return String.format("%s%s", str, str2);
    }

    public static /* synthetic */ void zzc(zzl zzlVar) {
        if (zzlVar.zzi.isEmpty()) {
            return;
        }
        long j = true != zzlVar.zzj.equals(zzlVar.zzi) ? 86400000L : 172800000L;
        long jZzi = zzi();
        long j2 = zzlVar.zzk;
        if (j2 == 0 || jZzi - j2 >= j) {
            zza.d("Upload the feature usage report.", new Object[0]);
            zzkz zzkzVarZza = zzla.zza();
            zzkzVarZza.zzb(zzb);
            zzkzVarZza.zza(zzlVar.zzf);
            zzla zzlaVar = (zzla) zzkzVarZza.zzp();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(zzlVar.zzi);
            zzkt zzktVarZza = zzku.zza();
            zzktVarZza.zza(arrayList);
            zzktVarZza.zzb(zzlaVar);
            zzku zzkuVar = (zzku) zzktVarZza.zzp();
            zzlj zzljVarZzc = zzlk.zzc();
            zzljVarZzc.zzc(zzkuVar);
            zzlVar.zzd.zzb((zzlk) zzljVarZzc.zzp(), 243);
            SharedPreferences.Editor editorEdit = zzlVar.zze.edit();
            if (!zzlVar.zzj.equals(zzlVar.zzi)) {
                zzlVar.zzj.clear();
                zzlVar.zzj.addAll(zzlVar.zzi);
                Iterator it = zzlVar.zzj.iterator();
                while (it.hasNext()) {
                    String string = Integer.toString(((zzkk) it.next()).zza());
                    String strZzf = zzlVar.zzf(string);
                    String strZzb = zzb("feature_usage_timestamp_reported_feature_", string);
                    if (!TextUtils.equals(strZzf, strZzb)) {
                        long j3 = zzlVar.zze.getLong(strZzf, 0L);
                        editorEdit.remove(strZzf);
                        if (j3 != 0) {
                            editorEdit.putLong(strZzb, j3);
                        }
                    }
                }
            }
            zzlVar.zzk = jZzi;
            editorEdit.putLong("feature_usage_last_report_time", jZzi).apply();
        }
    }

    public static void zzd(zzkk zzkkVar) {
        zzl zzlVar = zzc;
        if (zzlVar == null) {
            return;
        }
        zzlVar.zze.edit().putLong(zzlVar.zzf(Integer.toString(zzkkVar.zza())), zzi()).apply();
        zzlVar.zzi.add(zzkkVar);
        zzlVar.zzh();
    }

    private static zzkk zze(String str) {
        try {
            return zzkk.zzb(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return zzkk.DEVELOPER_FEATURE_FLAG_UNKNOWN;
        }
    }

    private final String zzf(String str) {
        String strZzb = zzb("feature_usage_timestamp_reported_feature_", str);
        return this.zze.contains(strZzb) ? strZzb : zzb("feature_usage_timestamp_detected_feature_", str);
    }

    private final void zzg(Set set) {
        if (set.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.zze.edit();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) it.next());
        }
        editorEdit.apply();
    }

    private final void zzh() {
        this.zzh.post(this.zzg);
    }

    private static final long zzi() {
        return DefaultClock.getInstance().currentTimeMillis();
    }
}
