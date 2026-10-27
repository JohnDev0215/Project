package com.google.android.gms.cast;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdk implements com.google.android.gms.cast.internal.zzaq {
    final /* synthetic */ RemoteMediaPlayer zza;

    @Nullable
    private GoogleApiClient zzb;
    private long zzc = 0;

    public zzdk(RemoteMediaPlayer remoteMediaPlayer) {
        this.zza = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.internal.zzaq
    public final long zza() {
        long j = this.zzc + 1;
        this.zzc = j;
        return j;
    }

    @Override // com.google.android.gms.cast.internal.zzaq
    public final void zzb(String str, String str2, long j, @Nullable String str3) {
        GoogleApiClient googleApiClient = this.zzb;
        if (googleApiClient == null) {
            throw new IllegalStateException("No GoogleApiClient available");
        }
        Cast.CastApi.sendMessage(googleApiClient, str, str2).setResultCallback(new zzdj(this, j));
    }

    public final void zzc(@Nullable GoogleApiClient googleApiClient) {
        this.zzb = googleApiClient;
    }
}
