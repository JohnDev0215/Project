package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbn implements com.google.android.gms.cast.internal.zzam {
    final /* synthetic */ RemoteMediaClient zza;

    /* synthetic */ zzbn(RemoteMediaClient remoteMediaClient, zzbm zzbmVar) {
        this.zza = remoteMediaClient;
    }

    private final void zzn() {
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient.zzk == null || (mediaStatus = remoteMediaClient.getMediaStatus()) == null) {
            return;
        }
        mediaStatus.getWriter().setIsPlayingAd(this.zza.zzk.parseIsPlayingAdFromMediaStatus(mediaStatus));
        List<AdBreakInfo> adBreaksFromMediaStatus = this.zza.zzk.parseAdBreaksFromMediaStatus(mediaStatus);
        MediaInfo mediaInfo = this.zza.getMediaInfo();
        if (mediaInfo != null) {
            mediaInfo.getWriter().setAdBreaks(adBreaksFromMediaStatus);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zza() {
        Iterator it = this.zza.zzh.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onAdBreakStatusUpdated();
        }
        Iterator it2 = this.zza.zza.iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onAdBreakStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzb(MediaError mediaError) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).onMediaError(mediaError);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzc() {
        zzn();
        Iterator it = this.zza.zzh.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onMetadataUpdated();
        }
        Iterator it2 = this.zza.zza.iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onMetadataUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzd() {
        Iterator it = this.zza.zzh.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onPreloadStatusUpdated();
        }
        Iterator it2 = this.zza.zza.iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zze(int[] iArr) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zza(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzf(int[] iArr, int i) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzb(iArr, i);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzg(MediaQueueItem[] mediaQueueItemArr) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzc(mediaQueueItemArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzh(int[] iArr) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzd(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzi(List list, List list2, int i) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zze(list, list2, i);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzj(int[] iArr) {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzf(iArr);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzk() {
        Iterator it = this.zza.zzh.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onQueueStatusUpdated();
        }
        Iterator it2 = this.zza.zza.iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onQueueStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzl() {
        Iterator it = this.zza.zza.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Callback) it.next()).zzg();
        }
    }

    @Override // com.google.android.gms.cast.internal.zzam
    public final void zzm() {
        zzn();
        RemoteMediaClient.zzl(this.zza);
        Iterator it = this.zza.zzh.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onStatusUpdated();
        }
        Iterator it2 = this.zza.zza.iterator();
        while (it2.hasNext()) {
            ((RemoteMediaClient.Callback) it2.next()).onStatusUpdated();
        }
    }
}
