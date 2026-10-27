package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class RemoteMediaClient implements Cast.MessageReceivedCallback {

    @NonNull
    public static final String NAMESPACE = com.google.android.gms.cast.internal.zzap.zzb;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    private final com.google.android.gms.cast.internal.zzap zzd;
    private final zzbf zze;
    private final MediaQueue zzf;

    @Nullable
    private com.google.android.gms.cast.zzr zzg;
    private ParseAdsInfoCallback zzk;
    private final List zzh = new CopyOnWriteArrayList();

    @VisibleForTesting
    final List zza = new CopyOnWriteArrayList();
    private final Map zzi = new ConcurrentHashMap();
    private final Map zzj = new ConcurrentHashMap();
    private final Object zzb = new Object();
    private final Handler zzc = new zzcv(Looper.getMainLooper());

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public static abstract class Callback {
        public void onAdBreakStatusUpdated() {
        }

        public void onMediaError(@NonNull MediaError mediaError) {
        }

        public void onMetadataUpdated() {
        }

        public void onPreloadStatusUpdated() {
        }

        public void onQueueStatusUpdated() {
        }

        public void onSendingRemoteMediaRequest() {
        }

        public void onStatusUpdated() {
        }

        public void zza(@NonNull int[] iArr) {
        }

        public void zzb(@NonNull int[] iArr, int i) {
        }

        public void zzc(@NonNull MediaQueueItem[] mediaQueueItemArr) {
        }

        public void zzd(@NonNull int[] iArr) {
        }

        public void zze(@NonNull List list, @NonNull List list2, int i) {
        }

        public void zzf(@NonNull int[] iArr) {
        }

        public void zzg() {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    @Deprecated
    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public interface MediaChannelResult extends Result {
        @Nullable
        JSONObject getCustomData();

        @Nullable
        MediaError getMediaError();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public interface ParseAdsInfoCallback {
        @NonNull
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(@NonNull MediaStatus mediaStatus);

        boolean parseIsPlayingAdFromMediaStatus(@NonNull MediaStatus mediaStatus);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public interface ProgressListener {
        void onProgressUpdated(long j, long j2);
    }

    public RemoteMediaClient(com.google.android.gms.cast.internal.zzap zzapVar) {
        zzbf zzbfVar = new zzbf(this);
        this.zze = zzbfVar;
        com.google.android.gms.cast.internal.zzap zzapVar2 = (com.google.android.gms.cast.internal.zzap) Preconditions.checkNotNull(zzapVar);
        this.zzd = zzapVar2;
        zzapVar2.zzS(new zzbn(this, null));
        zzapVar2.zzh(zzbfVar);
        this.zzf = new MediaQueue(this, 20, 20);
    }

    @NonNull
    public static PendingResult zzd(int i, @Nullable String str) {
        zzbh zzbhVar = new zzbh();
        zzbhVar.setResult(new zzbg(zzbhVar, new Status(i, str)));
        return zzbhVar;
    }

    static /* bridge */ /* synthetic */ void zzl(RemoteMediaClient remoteMediaClient) {
        for (zzbp zzbpVar : remoteMediaClient.zzj.values()) {
            if (remoteMediaClient.hasMediaSession() && !zzbpVar.zzi()) {
                zzbpVar.zzf();
            } else if (!remoteMediaClient.hasMediaSession() && zzbpVar.zzi()) {
                zzbpVar.zzg();
            }
            if (zzbpVar.zzi() && (remoteMediaClient.isBuffering() || remoteMediaClient.zzp() || remoteMediaClient.isPaused() || remoteMediaClient.isLoadingNextItem())) {
                remoteMediaClient.zzr(zzbpVar.zzb);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr(Set set) {
        MediaInfo media;
        HashSet hashSet = new HashSet(set);
        if (isPlaying() || isPaused() || isBuffering() || zzp()) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((ProgressListener) it.next()).onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
            }
        } else {
            if (!isLoadingNextItem()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((ProgressListener) it2.next()).onProgressUpdated(0L, 0L);
                }
                return;
            }
            MediaQueueItem loadingItem = getLoadingItem();
            if (loadingItem == null || (media = loadingItem.getMedia()) == null) {
                return;
            }
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ((ProgressListener) it3.next()).onProgressUpdated(0L, media.getStreamDuration());
            }
        }
    }

    private final boolean zzs() {
        return this.zzg != null;
    }

    private static final zzbk zzt(zzbk zzbkVar) {
        try {
            zzbkVar.zzc();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Throwable unused) {
            zzbkVar.setResult(new zzbj(zzbkVar, new Status(2100)));
        }
        return zzbkVar;
    }

    @Deprecated
    public void addListener(@NonNull Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzh.add(listener);
        }
    }

    public boolean addProgressListener(@NonNull ProgressListener progressListener, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (progressListener == null || this.zzi.containsKey(progressListener)) {
            return false;
        }
        Map map = this.zzj;
        Long lValueOf = Long.valueOf(j);
        zzbp zzbpVar = (zzbp) map.get(lValueOf);
        if (zzbpVar == null) {
            zzbpVar = new zzbp(this, j);
            this.zzj.put(lValueOf, zzbpVar);
        }
        zzbpVar.zzd(progressListener);
        this.zzi.put(progressListener, zzbpVar);
        if (!hasMediaSession()) {
            return true;
        }
        zzbpVar.zzf();
        return true;
    }

    public long getApproximateAdBreakClipPositionMs() {
        long jZzj;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            jZzj = this.zzd.zzj();
        }
        return jZzj;
    }

    public long getApproximateLiveSeekableRangeEnd() {
        long jZzk;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            jZzk = this.zzd.zzk();
        }
        return jZzk;
    }

    public long getApproximateLiveSeekableRangeStart() {
        long jZzl;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            jZzl = this.zzd.zzl();
        }
        return jZzl;
    }

    public long getApproximateStreamPosition() {
        long jZzm;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            jZzm = this.zzd.zzm();
        }
        return jZzm;
    }

    @Nullable
    public MediaQueueItem getCurrentItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getCurrentItemId());
    }

    public int getIdleReason() {
        int idleReason;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            idleReason = mediaStatus != null ? mediaStatus.getIdleReason() : 0;
        }
        return idleReason;
    }

    @Nullable
    public MediaQueueItem getLoadingItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getLoadingItemId());
    }

    @Nullable
    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfoZzK;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaInfoZzK = this.zzd.zzK();
        }
        return mediaInfoZzK;
    }

    @NonNull
    public MediaQueue getMediaQueue() {
        MediaQueue mediaQueue;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaQueue = this.zzf;
        }
        return mediaQueue;
    }

    @Nullable
    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatusZzL;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaStatusZzL = this.zzd.zzL();
        }
        return mediaStatusZzL;
    }

    @NonNull
    public String getNamespace() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzd.zze();
    }

    public int getPlayerState() {
        int playerState;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            playerState = mediaStatus != null ? mediaStatus.getPlayerState() : 1;
        }
        return playerState;
    }

    @Nullable
    public MediaQueueItem getPreloadedItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getPreloadedItemId());
    }

    public long getStreamDuration() {
        long jZzo;
        synchronized (this.zzb) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            jZzo = this.zzd.zzo();
        }
        return jZzo;
    }

    public boolean hasMediaSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return isBuffering() || zzp() || isPlaying() || isPaused() || isLoadingNextItem();
    }

    public boolean isBuffering() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 4;
    }

    public boolean isLiveStream() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null && mediaInfo.getStreamType() == 2;
    }

    public boolean isLoadingNextItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || mediaStatus.getLoadingItemId() == 0) ? false : true;
    }

    public boolean isPaused() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus != null) {
            if (mediaStatus.getPlayerState() == 3) {
                return true;
            }
            if (isLiveStream() && getIdleReason() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlaying() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 2;
    }

    public boolean isPlayingAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.isPlayingAd();
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo) {
        return load(mediaInfo, new MediaLoadOptions.Builder().build());
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(@NonNull CastDevice castDevice, @NonNull String str, @NonNull String str2) {
        this.zzd.zzQ(str2);
    }

    @NonNull
    public PendingResult<MediaChannelResult> pause() {
        return pause(null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> play() {
        return play(null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueAppendItem(@NonNull MediaQueueItem mediaQueueItem, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(@NonNull MediaQueueItem mediaQueueItem, int i, long j, @NonNull JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzah zzahVar = new zzah(this, mediaQueueItem, i, j, jSONObject);
        zzt(zzahVar);
        return zzahVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertItems(@NonNull MediaQueueItem[] mediaQueueItemArr, int i, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzag zzagVar = new zzag(this, mediaQueueItemArr, i, jSONObject);
        zzt(zzagVar);
        return zzagVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueJumpToItem(int i, long j, @NonNull JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzaq zzaqVar = new zzaq(this, i, j, jSONObject);
        zzt(zzaqVar);
        return zzaqVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueLoad(@NonNull MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzaf zzafVar = new zzaf(this, mediaQueueItemArr, i, i2, j, jSONObject);
        zzt(zzafVar);
        return zzafVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(int i, int i2, @NonNull JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzar zzarVar = new zzar(this, i, i2, jSONObject);
        zzt(zzarVar);
        return zzarVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueNext(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzan zzanVar = new zzan(this, jSONObject);
        zzt(zzanVar);
        return zzanVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queuePrev(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzam zzamVar = new zzam(this, jSONObject);
        zzt(zzamVar);
        return zzamVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueRemoveItem(int i, @NonNull JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzap zzapVar = new zzap(this, i, jSONObject);
        zzt(zzapVar);
        return zzapVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueRemoveItems(@NonNull int[] iArr, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzaj zzajVar = new zzaj(this, iArr, jSONObject);
        zzt(zzajVar);
        return zzajVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueReorderItems(@NonNull int[] iArr, int i, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzak zzakVar = new zzak(this, iArr, i, jSONObject);
        zzt(zzakVar);
        return zzakVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueSetRepeatMode(int i, @Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzao zzaoVar = new zzao(this, i, jSONObject);
        zzt(zzaoVar);
        return zzaoVar;
    }

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public PendingResult<MediaChannelResult> queueShuffle(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzal zzalVar = new zzal(this, true, jSONObject);
        zzt(zzalVar);
        return zzalVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueUpdateItems(@NonNull MediaQueueItem[] mediaQueueItemArr, @NonNull JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzai zzaiVar = new zzai(this, mediaQueueItemArr, jSONObject);
        zzt(zzaiVar);
        return zzaiVar;
    }

    public void registerCallback(@NonNull Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zza.add(callback);
        }
    }

    @Deprecated
    public void removeListener(@NonNull Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzh.remove(listener);
        }
    }

    public void removeProgressListener(@NonNull ProgressListener progressListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzbp zzbpVar = (zzbp) this.zzi.remove(progressListener);
        if (zzbpVar != null) {
            zzbpVar.zze(progressListener);
            if (zzbpVar.zzh()) {
                return;
            }
            this.zzj.remove(Long.valueOf(zzbpVar.zzb()));
            zzbpVar.zzg();
        }
    }

    @NonNull
    public PendingResult<MediaChannelResult> requestStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzac zzacVar = new zzac(this);
        zzt(zzacVar);
        return zzacVar;
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> seek(long j) {
        return seek(j, 0, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setActiveMediaTracks(@NonNull long[] jArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzad zzadVar = new zzad(this, jArr);
        zzt(zzadVar);
        return zzadVar;
    }

    public void setParseAdsInfoCallback(@NonNull ParseAdsInfoCallback parseAdsInfoCallback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzk = parseAdsInfoCallback;
    }

    @NonNull
    public PendingResult<MediaChannelResult> setPlaybackRate(double d) {
        return setPlaybackRate(d, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamMute(boolean z) {
        return setStreamMute(z, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamVolume(double d) throws IllegalArgumentException {
        return setStreamVolume(d, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setTextTrackStyle(@NonNull TextTrackStyle textTrackStyle) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzae zzaeVar = new zzae(this, textTrackStyle);
        zzt(zzaeVar);
        return zzaeVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> skipAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzab zzabVar = new zzab(this);
        zzt(zzabVar);
        return zzabVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> stop() {
        return stop(null);
    }

    public void togglePlayback() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        int playerState = getPlayerState();
        if (playerState == 4 || playerState == 2) {
            pause();
        } else {
            play();
        }
    }

    public void unregisterCallback(@NonNull Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zza.remove(callback);
        }
    }

    @NonNull
    public final PendingResult zze(@Nullable String str, @Nullable List list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzaw zzawVar = new zzaw(this, true, str, null);
        zzt(zzawVar);
        return zzawVar;
    }

    @NonNull
    public final PendingResult zzf(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzau zzauVar = new zzau(this, true, i, i2, i3);
        zzt(zzauVar);
        return zzauVar;
    }

    @NonNull
    public final PendingResult zzg() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzas zzasVar = new zzas(this, true);
        zzt(zzasVar);
        return zzasVar;
    }

    @NonNull
    public final PendingResult zzh(@NonNull int[] iArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzat zzatVar = new zzat(this, true, iArr);
        zzt(zzatVar);
        return zzatVar;
    }

    @NonNull
    public final Task zzi(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return Tasks.forException(new com.google.android.gms.cast.internal.zzan());
        }
        SessionState sessionStateBuild = null;
        if (((MediaStatus) Preconditions.checkNotNull(getMediaStatus())).isMediaCommandSupported(MediaStatus.COMMAND_STREAM_TRANSFER)) {
            return this.zzd.zzN(null);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Log.d("RemoteMediaClient", "create SessionState with cached mediaInfo and mediaStatus");
        MediaInfo mediaInfo = getMediaInfo();
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaInfo != null && mediaStatus != null) {
            MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
            builder.setMediaInfo(mediaInfo);
            builder.setCurrentTime(getApproximateStreamPosition());
            builder.setQueueData(mediaStatus.getQueueData());
            builder.setPlaybackRate(mediaStatus.getPlaybackRate());
            builder.setActiveTrackIds(mediaStatus.getActiveTrackIds());
            builder.setCustomData(mediaStatus.getCustomData());
            MediaLoadRequestData mediaLoadRequestDataBuild = builder.build();
            SessionState.Builder builder2 = new SessionState.Builder();
            builder2.setLoadRequestData(mediaLoadRequestDataBuild);
            sessionStateBuild = builder2.build();
        }
        taskCompletionSource.setResult(sessionStateBuild);
        return taskCompletionSource.getTask();
    }

    public final void zzn() {
        com.google.android.gms.cast.zzr zzrVar = this.zzg;
        if (zzrVar == null) {
            return;
        }
        zzrVar.zzi(getNamespace(), this);
        requestStatus();
    }

    public final void zzo(@Nullable com.google.android.gms.cast.zzr zzrVar) {
        com.google.android.gms.cast.zzr zzrVar2 = this.zzg;
        if (zzrVar2 == zzrVar) {
            return;
        }
        if (zzrVar2 != null) {
            this.zzd.zzf();
            this.zzf.zzl();
            zzrVar2.zzg(getNamespace());
            this.zze.zzc(null);
            this.zzc.removeCallbacksAndMessages(null);
        }
        this.zzg = zzrVar;
        if (zzrVar != null) {
            this.zze.zzc(zzrVar);
        }
    }

    final boolean zzp() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 5;
    }

    public final boolean zzq() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!isLiveStream()) {
            return true;
        }
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || !mediaStatus.isMediaCommandSupported(2L) || mediaStatus.getLiveSeekableRange() == null) ? false : true;
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo, @NonNull MediaLoadOptions mediaLoadOptions) {
        MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
        builder.setMediaInfo(mediaInfo);
        builder.setAutoplay(Boolean.valueOf(mediaLoadOptions.getAutoplay()));
        builder.setCurrentTime(mediaLoadOptions.getPlayPosition());
        builder.setPlaybackRate(mediaLoadOptions.getPlaybackRate());
        builder.setActiveTrackIds(mediaLoadOptions.getActiveTrackIds());
        builder.setCustomData(mediaLoadOptions.getCustomData());
        builder.setCredentials(mediaLoadOptions.getCredentials());
        builder.setCredentialsType(mediaLoadOptions.getCredentialsType());
        return load(builder.build());
    }

    @NonNull
    public PendingResult<MediaChannelResult> pause(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzax zzaxVar = new zzax(this, jSONObject);
        zzt(zzaxVar);
        return zzaxVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> play(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzaz zzazVar = new zzaz(this, jSONObject);
        zzt(zzazVar);
        return zzazVar;
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> seek(long j, int i) {
        return seek(j, i, null);
    }

    @NonNull
    public PendingResult<MediaChannelResult> setPlaybackRate(double d, @Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzbd zzbdVar = new zzbd(this, d, jSONObject);
        zzt(zzbdVar);
        return zzbdVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamMute(boolean z, @Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzbc zzbcVar = new zzbc(this, z, jSONObject);
        zzt(zzbcVar);
        return zzbcVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> setStreamVolume(double d, @Nullable JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzbb zzbbVar = new zzbb(this, d, jSONObject);
        zzt(zzbbVar);
        return zzbbVar;
    }

    @NonNull
    public PendingResult<MediaChannelResult> stop(@Nullable JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzay zzayVar = new zzay(this, jSONObject);
        zzt(zzayVar);
        return zzayVar;
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> seek(long j, int i, @Nullable JSONObject jSONObject) {
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(j);
        builder.setResumeState(i);
        builder.setCustomData(jSONObject);
        return seek(builder.build());
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(@NonNull MediaQueueItem mediaQueueItem, int i, @NonNull JSONObject jSONObject) {
        return queueInsertAndPlayItem(mediaQueueItem, i, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueJumpToItem(int i, @NonNull JSONObject jSONObject) {
        return queueJumpToItem(i, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> queueLoad(@NonNull MediaQueueItem[] mediaQueueItemArr, int i, int i2, @NonNull JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(mediaQueueItemArr, i, i2, -1L, jSONObject);
    }

    @NonNull
    public PendingResult<MediaChannelResult> seek(@NonNull MediaSeekOptions mediaSeekOptions) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzba zzbaVar = new zzba(this, mediaSeekOptions);
        zzt(zzbaVar);
        return zzbaVar;
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo, boolean z) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        return load(mediaInfo, builder.build());
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo, boolean z, long j) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j);
        return load(mediaInfo, builder.build());
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo, boolean z, long j, @NonNull JSONObject jSONObject) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j);
        builder.setCustomData(jSONObject);
        return load(mediaInfo, builder.build());
    }

    @NonNull
    @Deprecated
    public PendingResult<MediaChannelResult> load(@NonNull MediaInfo mediaInfo, boolean z, long j, @NonNull long[] jArr, @NonNull JSONObject jSONObject) {
        MediaLoadOptions.Builder builder = new MediaLoadOptions.Builder();
        builder.setAutoplay(z);
        builder.setPlayPosition(j);
        builder.setActiveTrackIds(jArr);
        builder.setCustomData(jSONObject);
        return load(mediaInfo, builder.build());
    }

    @NonNull
    public PendingResult<MediaChannelResult> load(@NonNull MediaLoadRequestData mediaLoadRequestData) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzs()) {
            return zzd(17, null);
        }
        zzav zzavVar = new zzav(this, mediaLoadRequestData);
        zzt(zzavVar);
        return zzavVar;
    }
}
