package com.google.android.gms.cast.framework.media.uicontroller;

import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.text.DateFormat;
import java.util.Date;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
public final class zza {

    @Nullable
    @VisibleForTesting
    RemoteMediaClient zza;

    private zza() {
    }

    @VisibleForTesting(otherwise = 3)
    public static zza zzf() {
        return new zza();
    }

    @Nullable
    private final MediaMetadata zzo() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = this.zza.getMediaInfo()) == null) {
            return null;
        }
        return mediaInfo.getMetadata();
    }

    private static final String zzp(long j) {
        if (j >= 0) {
            return DateUtils.formatElapsedTime(j / 1000);
        }
        String strValueOf = String.valueOf(DateUtils.formatElapsedTime((-j) / 1000));
        return strValueOf.length() != 0 ? "-".concat(strValueOf) : new String("-");
    }

    public final int zza() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return 0;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (!remoteMediaClient2.isLiveStream() && remoteMediaClient2.isLoadingNextItem()) {
            return 0;
        }
        int approximateStreamPosition = (int) (remoteMediaClient2.getApproximateStreamPosition() - zze());
        if (remoteMediaClient2.zzq()) {
            approximateStreamPosition = CastUtils.zza(approximateStreamPosition, zzd(), zzc());
        }
        return CastUtils.zza(approximateStreamPosition, 0, zzb());
    }

    public final int zzb() {
        MediaInfo media;
        RemoteMediaClient remoteMediaClient = this.zza;
        long jLongValue = 1;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            if (remoteMediaClient2.isLiveStream()) {
                Long lZzi = zzi();
                if (lZzi != null) {
                    jLongValue = lZzi.longValue();
                } else {
                    Long lZzg = zzg();
                    jLongValue = lZzg != null ? lZzg.longValue() : Math.max(remoteMediaClient2.getApproximateStreamPosition(), 1L);
                }
            } else if (remoteMediaClient2.isLoadingNextItem()) {
                MediaQueueItem loadingItem = remoteMediaClient2.getLoadingItem();
                if (loadingItem != null && (media = loadingItem.getMedia()) != null) {
                    jLongValue = Math.max(media.getStreamDuration(), 1L);
                }
            } else {
                jLongValue = Math.max(remoteMediaClient2.getStreamDuration(), 1L);
            }
        }
        return Math.max((int) (jLongValue - zze()), 1);
    }

    public final int zzc() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return zzb();
        }
        if (this.zza.zzq()) {
            return CastUtils.zza((int) (((Long) Preconditions.checkNotNull(zzg())).longValue() - zze()), 0, zzb());
        }
        return 0;
    }

    public final int zzd() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && this.zza.isLiveStream() && this.zza.zzq()) {
            return CastUtils.zza((int) (((Long) Preconditions.checkNotNull(zzh())).longValue() - zze()), 0, zzb());
        }
        return 0;
    }

    @VisibleForTesting
    public final long zze() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream()) {
            return 0L;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        Long lZzj = zzj();
        if (lZzj != null) {
            return lZzj.longValue();
        }
        Long lZzh = zzh();
        return lZzh != null ? lZzh.longValue() : remoteMediaClient2.getApproximateStreamPosition();
    }

    @Nullable
    @VisibleForTesting
    final Long zzg() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzq() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeEnd());
    }

    @Nullable
    @VisibleForTesting
    final Long zzh() {
        RemoteMediaClient remoteMediaClient;
        MediaStatus mediaStatus;
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || !this.zza.zzq() || (mediaStatus = (remoteMediaClient = this.zza).getMediaStatus()) == null || mediaStatus.getLiveSeekableRange() == null) {
            return null;
        }
        return Long.valueOf(remoteMediaClient.getApproximateLiveSeekableRangeStart());
    }

    @Nullable
    public final Long zzi() {
        MediaMetadata mediaMetadataZzo;
        Long lZzj;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaMetadataZzo = zzo()) == null || !mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || (lZzj = zzj()) == null) {
            return null;
        }
        return Long.valueOf(lZzj.longValue() + mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_DURATION));
    }

    @Nullable
    public final Long zzj() {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && this.zza.isLiveStream()) {
            RemoteMediaClient remoteMediaClient2 = this.zza;
            MediaInfo mediaInfo = remoteMediaClient2.getMediaInfo();
            MediaMetadata mediaMetadataZzo = zzo();
            if (mediaInfo != null && mediaMetadataZzo != null && mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA) && (mediaMetadataZzo.containsKey(MediaMetadata.KEY_SECTION_DURATION) || remoteMediaClient2.zzq())) {
                return Long.valueOf(mediaMetadataZzo.getTimeMillis(MediaMetadata.KEY_SECTION_START_TIME_IN_MEDIA));
            }
        }
        return null;
    }

    @Nullable
    @VisibleForTesting
    final Long zzk() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !this.zza.isLiveStream() || (mediaInfo = this.zza.getMediaInfo()) == null || mediaInfo.getStartAbsoluteTime() == -1) {
            return null;
        }
        return Long.valueOf(mediaInfo.getStartAbsoluteTime());
    }

    @Nullable
    public final String zzl(long j) {
        RemoteMediaClient remoteMediaClient = this.zza;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return null;
        }
        RemoteMediaClient remoteMediaClient2 = this.zza;
        if (((remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession() || !this.zza.isLiveStream() || zzk() == null) ? 1 : 2) - 1 != 1) {
            return (remoteMediaClient2.isLiveStream() && zzj() == null) ? zzp(j) : zzp(j - zze());
        }
        return DateFormat.getTimeInstance().format(new Date(((Long) Preconditions.checkNotNull(zzk())).longValue() + j));
    }

    public final boolean zzm() {
        return zzn(((long) zza()) + zze());
    }

    public final boolean zzn(long j) {
        RemoteMediaClient remoteMediaClient = this.zza;
        return remoteMediaClient != null && remoteMediaClient.hasMediaSession() && this.zza.zzq() && (((long) zzc()) + zze()) - j < 10000;
    }
}
