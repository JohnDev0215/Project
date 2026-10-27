package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SuppressLint({"MissingRemoteException"})
@Deprecated
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {

    @NonNull
    public static final String NAMESPACE = com.google.android.gms.cast.internal.zzap.zzb;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final Object zza;
    private final com.google.android.gms.cast.internal.zzap zzb;
    private final zzdk zzc;

    @Nullable
    private OnPreloadStatusUpdatedListener zzd;

    @Nullable
    private OnQueueStatusUpdatedListener zze;

    @Nullable
    private OnMetadataUpdatedListener zzf;

    @Nullable
    private OnStatusUpdatedListener zzg;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface MediaChannelResult extends Result {
        @Nullable
        JSONObject getCustomData();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    public RemoteMediaPlayer() {
        com.google.android.gms.cast.internal.zzap zzapVar = new com.google.android.gms.cast.internal.zzap(null);
        this.zza = new Object();
        this.zzb = zzapVar;
        zzapVar.zzS(new zzcw(this));
        zzdk zzdkVar = new zzdk(this);
        this.zzc = zzdkVar;
        zzapVar.zzh(zzdkVar);
    }

    static /* bridge */ /* synthetic */ int zza(RemoteMediaPlayer remoteMediaPlayer, int i) {
        MediaStatus mediaStatus = remoteMediaPlayer.getMediaStatus();
        if (mediaStatus == null) {
            return -1;
        }
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            MediaQueueItem queueItem = mediaStatus.getQueueItem(i2);
            if (queueItem != null && queueItem.getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    static /* bridge */ /* synthetic */ void zze(RemoteMediaPlayer remoteMediaPlayer) {
        OnMetadataUpdatedListener onMetadataUpdatedListener = remoteMediaPlayer.zzf;
        if (onMetadataUpdatedListener != null) {
            onMetadataUpdatedListener.onMetadataUpdated();
        }
    }

    static /* bridge */ /* synthetic */ void zzf(RemoteMediaPlayer remoteMediaPlayer) {
        OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener = remoteMediaPlayer.zzd;
        if (onPreloadStatusUpdatedListener != null) {
            onPreloadStatusUpdatedListener.onPreloadStatusUpdated();
        }
    }

    static /* bridge */ /* synthetic */ void zzg(RemoteMediaPlayer remoteMediaPlayer) {
        OnQueueStatusUpdatedListener onQueueStatusUpdatedListener = remoteMediaPlayer.zze;
        if (onQueueStatusUpdatedListener != null) {
            onQueueStatusUpdatedListener.onQueueStatusUpdated();
        }
    }

    static /* bridge */ /* synthetic */ void zzh(RemoteMediaPlayer remoteMediaPlayer) {
        OnStatusUpdatedListener onStatusUpdatedListener = remoteMediaPlayer.zzg;
        if (onStatusUpdatedListener != null) {
            onStatusUpdatedListener.onStatusUpdated();
        }
    }

    public long getApproximateStreamPosition() {
        long jZzm;
        synchronized (this.zza) {
            jZzm = this.zzb.zzm();
        }
        return jZzm;
    }

    @Nullable
    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfoZzK;
        synchronized (this.zza) {
            mediaInfoZzK = this.zzb.zzK();
        }
        return mediaInfoZzK;
    }

    @Nullable
    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatusZzL;
        synchronized (this.zza) {
            mediaStatusZzL = this.zzb.zzL();
        }
        return mediaStatusZzL;
    }

    @NonNull
    public String getNamespace() {
        return this.zzb.zze();
    }

    public long getStreamDuration() {
        long jZzo;
        synchronized (this.zza) {
            jZzo = this.zzb.zzo();
        }
        return jZzo;
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull GoogleApiClient googleApiClient, @NonNull MediaInfo mediaInfo) {
        return load(googleApiClient, mediaInfo, true, -1L, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(@NonNull CastDevice castDevice, @NonNull String str, @NonNull String str2) {
        this.zzb.zzQ(str2);
    }

    @NonNull
    public PendingResult<MediaChannelResult> pause(@NonNull GoogleApiClient googleApiClient) {
        return pause(googleApiClient, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> play(@NonNull GoogleApiClient googleApiClient) {
        return play(googleApiClient, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueAppendItem(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem mediaQueueItem, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(googleApiClient, new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem mediaQueueItem, int i, long j, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcq(this, googleApiClient, mediaQueueItem, i, j, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertItems(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem[] mediaQueueItemArr, int i, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzcp(this, googleApiClient, mediaQueueItemArr, i, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueJumpToItem(@NonNull GoogleApiClient googleApiClient, int i, long j, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcz(this, googleApiClient, i, j, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueLoad(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzco(this, googleApiClient, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(@NonNull GoogleApiClient googleApiClient, int i, int i2, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzda(this, googleApiClient, i, i2, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueNext(@NonNull GoogleApiClient googleApiClient, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcv(this, googleApiClient, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queuePrev(@NonNull GoogleApiClient googleApiClient, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcu(this, googleApiClient, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueRemoveItem(@NonNull GoogleApiClient googleApiClient, int i, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcy(this, googleApiClient, i, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueRemoveItems(@NonNull GoogleApiClient googleApiClient, @NonNull int[] iArr, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzcs(this, googleApiClient, iArr, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueReorderItems(@NonNull GoogleApiClient googleApiClient, @NonNull int[] iArr, int i, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzct(this, googleApiClient, iArr, i, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueSetRepeatMode(@NonNull GoogleApiClient googleApiClient, int i, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcx(this, googleApiClient, i, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueUpdateItems(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem[] mediaQueueItemArr, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzcr(this, googleApiClient, mediaQueueItemArr, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> requestStatus(@NonNull GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzdi(this, googleApiClient));
    }

    @NonNull
    public PendingResult<MediaChannelResult> seek(@NonNull GoogleApiClient googleApiClient, long j) {
        return seek(googleApiClient, j, 0, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setActiveMediaTracks(@NonNull GoogleApiClient googleApiClient, @NonNull long[] jArr) {
        return googleApiClient.execute(new zzcm(this, googleApiClient, jArr));
    }

    public void setOnMetadataUpdatedListener(@Nullable OnMetadataUpdatedListener onMetadataUpdatedListener) {
        this.zzf = onMetadataUpdatedListener;
    }

    public void setOnPreloadStatusUpdatedListener(@Nullable OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener) {
        this.zzd = onPreloadStatusUpdatedListener;
    }

    public void setOnQueueStatusUpdatedListener(@Nullable OnQueueStatusUpdatedListener onQueueStatusUpdatedListener) {
        this.zze = onQueueStatusUpdatedListener;
    }

    public void setOnStatusUpdatedListener(@Nullable OnStatusUpdatedListener onStatusUpdatedListener) {
        this.zzg = onStatusUpdatedListener;
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamMute(@NonNull GoogleApiClient googleApiClient, boolean z) {
        return setStreamMute(googleApiClient, z, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamVolume(@NonNull GoogleApiClient googleApiClient, double d) throws IllegalArgumentException {
        return setStreamVolume(googleApiClient, d, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setTextTrackStyle(@NonNull GoogleApiClient googleApiClient, @NonNull TextTrackStyle textTrackStyle) {
        return googleApiClient.execute(new zzcn(this, googleApiClient, textTrackStyle));
    }

    @NonNull
    public PendingResult<MediaChannelResult> stop(@NonNull GoogleApiClient googleApiClient) {
        return stop(googleApiClient, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull GoogleApiClient googleApiClient, @NonNull MediaInfo mediaInfo, boolean z) {
        return load(googleApiClient, mediaInfo, z, -1L, null, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> pause(@NonNull GoogleApiClient googleApiClient, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzdc(this, googleApiClient, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> play(@NonNull GoogleApiClient googleApiClient, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzde(this, googleApiClient, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem mediaQueueItem, int i, @Nullable JSONObject jSONObject) {
        return queueInsertAndPlayItem(googleApiClient, mediaQueueItem, i, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueJumpToItem(@NonNull GoogleApiClient googleApiClient, int i, @Nullable JSONObject jSONObject) {
        return queueJumpToItem(googleApiClient, i, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueLoad(@NonNull GoogleApiClient googleApiClient, @NonNull MediaQueueItem[] mediaQueueItemArr, int i, int i2, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(googleApiClient, mediaQueueItemArr, i, i2, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> seek(@NonNull GoogleApiClient googleApiClient, long j, int i) {
        return seek(googleApiClient, j, i, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamMute(@NonNull GoogleApiClient googleApiClient, boolean z, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzdh(this, googleApiClient, z, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamVolume(@NonNull GoogleApiClient googleApiClient, double d, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzdg(this, googleApiClient, d, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> stop(@NonNull GoogleApiClient googleApiClient, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzdd(this, googleApiClient, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull GoogleApiClient googleApiClient, @NonNull MediaInfo mediaInfo, boolean z, long j) {
        return load(googleApiClient, mediaInfo, z, j, null, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> seek(@NonNull GoogleApiClient googleApiClient, long j, int i, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzdf(this, googleApiClient, j, i, jSONObject));
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull GoogleApiClient googleApiClient, @NonNull MediaInfo mediaInfo, boolean z, long j, @Nullable JSONObject jSONObject) {
        return load(googleApiClient, mediaInfo, z, j, null, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull GoogleApiClient googleApiClient, @NonNull MediaInfo mediaInfo, boolean z, long j, @Nullable long[] jArr, @Nullable JSONObject jSONObject) {
        return googleApiClient.execute(new zzdb(this, googleApiClient, mediaInfo, z, j, jArr, jSONObject));
    }
}
