package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzda extends zzdn {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzda(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzd = remoteMediaPlayer;
        this.zza = i;
        this.zzb = i2;
        this.zzc = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        int iZza = RemoteMediaPlayer.zza(this.zzd, this.zza);
        if (iZza == -1) {
            setResult(new zzdm(this, new Status(0)));
            return;
        }
        int i = this.zzb;
        if (i < 0) {
            setResult(new zzdm(this, new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", Integer.valueOf(this.zzb)))));
            return;
        }
        if (iZza == i) {
            setResult(new zzdm(this, new Status(0)));
            return;
        }
        MediaStatus mediaStatus = this.zzd.getMediaStatus();
        if (mediaStatus == null) {
            setResult(new zzdm(this, new Status(CastStatusCodes.INVALID_REQUEST, String.format(Locale.ROOT, "Invalid request: Invalid MediaStatus", new Object[0]))));
            return;
        }
        int i2 = this.zzb;
        if (i2 > iZza) {
            i2++;
        }
        MediaQueueItem queueItem = mediaStatus.getQueueItem(i2);
        this.zzd.zzb.zzz(zzb(), new int[]{this.zza}, queueItem != null ? queueItem.getItemId() : 0, this.zzc);
    }
}
