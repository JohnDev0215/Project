package com.google.android.gms.cast;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
public interface zzr extends HasApiKey {
    double zza();

    int zzb();

    int zzc();

    @Nullable
    ApplicationMetadata zzd();

    Task zze();

    Task zzf();

    Task zzg(String str);

    Task zzh(String str, String str2);

    Task zzi(String str, Cast.MessageReceivedCallback messageReceivedCallback);

    @Nullable
    String zzj();

    void zzk(zzq zzqVar);

    boolean zzl();
}
