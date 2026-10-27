package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
@SafeParcelable.Class(creator = "MediaStatusCreator")
@SafeParcelable.Reserved({1})
public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_DISLIKE = 32768;
    public static final long COMMAND_EDIT_TRACKS = 4096;
    public static final long COMMAND_FOLLOW = 65536;
    public static final long COMMAND_LIKE = 16384;
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_PLAYBACK_RATE = 8192;
    public static final long COMMAND_QUEUE_NEXT = 64;
    public static final long COMMAND_QUEUE_PREVIOUS = 128;
    public static final long COMMAND_QUEUE_REPEAT = 3072;
    public static final long COMMAND_QUEUE_REPEAT_ALL = 1024;
    public static final long COMMAND_QUEUE_REPEAT_ONE = 2048;
    public static final long COMMAND_QUEUE_SHUFFLE = 256;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_AD = 512;

    @Deprecated
    public static final long COMMAND_SKIP_BACKWARD = 32;

    @Deprecated
    public static final long COMMAND_SKIP_FORWARD = 16;

    @ShowFirstParty
    @KeepForSdk
    public static final long COMMAND_STREAM_TRANSFER = 262144;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final long COMMAND_UNFOLLOW = 131072;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_LOADING = 5;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getMediaInfo", id = 2)
    MediaInfo zza;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getMediaSessionId", id = 3)
    long zzb;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getCurrentItemId", id = 4)
    int zzc;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getPlaybackRate", id = 5)
    double zzd;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getPlayerState", id = 6)
    int zze;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getIdleReason", id = 7)
    int zzf;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getStreamPosition", id = 8)
    long zzg;

    @SafeParcelable.Field(id = 9)
    long zzh;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getStreamVolume", id = 10)
    double zzi;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "isMute", id = 11)
    boolean zzj;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getActiveTrackIds", id = 12)
    long[] zzk;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getLoadingItemId", id = 13)
    int zzl;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "getPreloadedItemId", id = 14)
    int zzm;

    @Nullable
    @SafeParcelable.Field(id = 15)
    String zzn;

    @Nullable
    @VisibleForTesting
    JSONObject zzo;

    @SafeParcelable.Field(id = 16)
    int zzp;

    @SafeParcelable.Field(id = 17)
    final List zzq;

    @VisibleForTesting
    @SafeParcelable.Field(getter = "isPlayingAd", id = 18)
    boolean zzr;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getAdBreakStatus", id = 19)
    AdBreakStatus zzs;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getVideoInfo", id = 20)
    VideoInfo zzt;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getLiveSeekableRange", id = 21)
    MediaLiveSeekableRange zzu;

    @Nullable
    @VisibleForTesting
    @SafeParcelable.Field(getter = "getQueueData", id = 22)
    MediaQueueData zzv;

    @VisibleForTesting
    boolean zzw;
    private final SparseArray zzy;
    private final Writer zzz;
    private static final Logger zzx = new Logger("MediaStatus");

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzck();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @KeepForSdk
    public static class Builder {

        @Nullable
        private MediaInfo zza;
        private long zzb;
        private double zzd;
        private long zzg;
        private long zzh;
        private double zzi;
        private boolean zzj;
        private long[] zzk;
        private JSONObject zzn;
        private boolean zzq;
        private AdBreakStatus zzr;
        private VideoInfo zzs;
        private MediaLiveSeekableRange zzt;
        private MediaQueueData zzu;
        private int zzc = 0;
        private int zze = 0;
        private int zzf = 0;
        private int zzl = 0;
        private int zzm = 0;
        private int zzo = 0;
        private final List zzp = new ArrayList();

        @NonNull
        @KeepForSdk
        public MediaStatus build() {
            MediaStatus mediaStatus = new MediaStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, null, this.zzo, this.zzp, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu);
            mediaStatus.zzo = this.zzn;
            return mediaStatus;
        }

        @NonNull
        @KeepForSdk
        public Builder setActiveTrackIds(@NonNull long[] jArr) {
            this.zzk = jArr;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setAdBreakStatus(@NonNull AdBreakStatus adBreakStatus) {
            this.zzr = adBreakStatus;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setCurrentItemId(int i) {
            this.zzc = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setCustomData(@NonNull JSONObject jSONObject) {
            this.zzn = jSONObject;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setIdleReason(int i) {
            this.zzf = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setIsMute(boolean z) {
            this.zzj = z;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setIsPlayingAd(boolean z) {
            this.zzq = z;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setLiveSeekableRange(@NonNull MediaLiveSeekableRange mediaLiveSeekableRange) {
            this.zzt = mediaLiveSeekableRange;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setLoadingItemId(int i) {
            this.zzl = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setMediaInfo(@Nullable MediaInfo mediaInfo) {
            this.zza = mediaInfo;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setMediaSessionId(long j) {
            this.zzb = j;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setPlaybackRate(double d) {
            this.zzd = d;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setPlayerState(int i) {
            this.zze = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setPreloadedItemId(int i) {
            this.zzm = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setQueueData(@NonNull MediaQueueData mediaQueueData) {
            this.zzu = mediaQueueData;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setQueueItems(@NonNull List<MediaQueueItem> list) {
            this.zzp.clear();
            this.zzp.addAll(list);
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setQueueRepeatMode(int i) {
            this.zzo = i;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setStreamPosition(long j) {
            this.zzg = j;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setStreamVolume(double d) {
            this.zzi = d;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setSupportedMediaCommands(long j) {
            this.zzh = j;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setVideoInfo(@NonNull VideoInfo videoInfo) {
            this.zzs = videoInfo;
            return this;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @KeepForSdk
    public class Writer {
        public Writer() {
        }

        @KeepForSdk
        public void setActiveTrackIds(@Nullable long[] jArr) {
            MediaStatus.this.zzk = jArr;
        }

        @KeepForSdk
        public void setAdBreakStatus(@Nullable AdBreakStatus adBreakStatus) {
            MediaStatus.this.zzs = adBreakStatus;
        }

        @KeepForSdk
        public void setCurrentItemId(int i) {
            MediaStatus.this.zzc = i;
        }

        @KeepForSdk
        public void setCustomData(@Nullable JSONObject jSONObject) {
            MediaStatus mediaStatus = MediaStatus.this;
            mediaStatus.zzo = jSONObject;
            mediaStatus.zzn = null;
        }

        @KeepForSdk
        public void setIdleReason(int i) {
            MediaStatus.this.zzf = i;
        }

        @KeepForSdk
        public void setIsPlayingAd(boolean z) {
            MediaStatus.this.zzr = z;
        }

        @KeepForSdk
        public void setLiveSeekableRange(@Nullable MediaLiveSeekableRange mediaLiveSeekableRange) {
            MediaStatus.this.zzu = mediaLiveSeekableRange;
        }

        @KeepForSdk
        public void setLoadingItemId(int i) {
            MediaStatus.this.zzl = i;
        }

        @KeepForSdk
        public void setMediaInfo(@Nullable MediaInfo mediaInfo) {
            MediaStatus.this.zza = mediaInfo;
        }

        @KeepForSdk
        public void setMute(boolean z) {
            MediaStatus.this.zzj = z;
        }

        @KeepForSdk
        public void setPlaybackRate(double d) {
            MediaStatus.this.zzd = d;
        }

        @KeepForSdk
        public void setPlayerState(int i) {
            MediaStatus.this.zze = i;
        }

        @KeepForSdk
        public void setPreloadedItemId(int i) {
            MediaStatus.this.zzm = i;
        }

        @KeepForSdk
        public void setQueueData(@Nullable MediaQueueData mediaQueueData) {
            MediaStatus.this.zzv = mediaQueueData;
        }

        @KeepForSdk
        public void setQueueItems(@Nullable List<MediaQueueItem> list) {
            MediaStatus.this.zze(list);
        }

        @KeepForSdk
        public void setQueueRepeatMode(int i) {
            MediaStatus.this.zzp = i;
        }

        @KeepForSdk
        public void setShuffle(boolean z) {
            MediaStatus.this.zzw = z;
        }

        @KeepForSdk
        public void setStreamPosition(long j) {
            MediaStatus.this.zzg = j;
        }

        @KeepForSdk
        public void setStreamVolume(double d) {
            MediaStatus.this.zzi = d;
        }

        @KeepForSdk
        public void setSupportedMediaCommands(long j) {
            MediaStatus.this.zzh = j;
        }

        @KeepForSdk
        public void setVideoInfo(@Nullable VideoInfo videoInfo) {
            MediaStatus.this.zzt = videoInfo;
        }
    }

    @SafeParcelable.Constructor
    @SuppressLint({"NonSdkVisibleApi"})
    public MediaStatus(@Nullable @SafeParcelable.Param(id = 2) MediaInfo mediaInfo, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) double d, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) int i3, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 9) long j3, @SafeParcelable.Param(id = 10) double d2, @SafeParcelable.Param(id = 11) boolean z, @Nullable @SafeParcelable.Param(id = 12) long[] jArr, @SafeParcelable.Param(id = 13) int i4, @SafeParcelable.Param(id = 14) int i5, @Nullable @SafeParcelable.Param(id = 15) String str, @SafeParcelable.Param(id = 16) int i6, @Nullable @SafeParcelable.Param(id = 17) List list, @SafeParcelable.Param(id = 18) boolean z2, @Nullable @SafeParcelable.Param(id = 19) AdBreakStatus adBreakStatus, @Nullable @SafeParcelable.Param(id = 20) VideoInfo videoInfo, @Nullable @SafeParcelable.Param(id = 21) MediaLiveSeekableRange mediaLiveSeekableRange, @Nullable @SafeParcelable.Param(id = 22) MediaQueueData mediaQueueData) {
        this.zzq = new ArrayList();
        this.zzy = new SparseArray();
        this.zzz = new Writer();
        this.zza = mediaInfo;
        this.zzb = j;
        this.zzc = i;
        this.zzd = d;
        this.zze = i2;
        this.zzf = i3;
        this.zzg = j2;
        this.zzh = j3;
        this.zzi = d2;
        this.zzj = z;
        this.zzk = jArr;
        this.zzl = i4;
        this.zzm = i5;
        this.zzn = str;
        if (str != null) {
            try {
                this.zzo = new JSONObject(this.zzn);
            } catch (JSONException unused) {
                this.zzo = null;
                this.zzn = null;
            }
        } else {
            this.zzo = null;
        }
        this.zzp = i6;
        if (list != null && !list.isEmpty()) {
            zze(list);
        }
        this.zzr = z2;
        this.zzs = adBreakStatus;
        this.zzt = videoInfo;
        this.zzu = mediaLiveSeekableRange;
        this.zzv = mediaQueueData;
        boolean z3 = false;
        if (mediaQueueData != null && mediaQueueData.zzk()) {
            z3 = true;
        }
        this.zzw = z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(@Nullable List list) {
        this.zzq.clear();
        this.zzy.clear();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                MediaQueueItem mediaQueueItem = (MediaQueueItem) list.get(i);
                this.zzq.add(mediaQueueItem);
                this.zzy.put(mediaQueueItem.getItemId(), Integer.valueOf(i));
            }
        }
    }

    private static final boolean zzf(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return i4 != 2;
            }
            if (i2 != 3) {
                return true;
            }
        }
        return i3 == 0;
    }

    public boolean equals(@Nullable Object obj) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        return (this.zzo == null) == (mediaStatus.zzo == null) && this.zzb == mediaStatus.zzb && this.zzc == mediaStatus.zzc && this.zzd == mediaStatus.zzd && this.zze == mediaStatus.zze && this.zzf == mediaStatus.zzf && this.zzg == mediaStatus.zzg && this.zzi == mediaStatus.zzi && this.zzj == mediaStatus.zzj && this.zzl == mediaStatus.zzl && this.zzm == mediaStatus.zzm && this.zzp == mediaStatus.zzp && Arrays.equals(this.zzk, mediaStatus.zzk) && CastUtils.zzh(Long.valueOf(this.zzh), Long.valueOf(mediaStatus.zzh)) && CastUtils.zzh(this.zzq, mediaStatus.zzq) && CastUtils.zzh(this.zza, mediaStatus.zza) && ((jSONObject = this.zzo) == null || (jSONObject2 = mediaStatus.zzo) == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && this.zzr == mediaStatus.isPlayingAd() && CastUtils.zzh(this.zzs, mediaStatus.zzs) && CastUtils.zzh(this.zzt, mediaStatus.zzt) && CastUtils.zzh(this.zzu, mediaStatus.zzu) && Objects.equal(this.zzv, mediaStatus.zzv) && this.zzw == mediaStatus.zzw;
    }

    @Nullable
    public long[] getActiveTrackIds() {
        return this.zzk;
    }

    @Nullable
    public AdBreakStatus getAdBreakStatus() {
        return this.zzs;
    }

    @Nullable
    public AdBreakInfo getCurrentAdBreak() {
        MediaInfo mediaInfo;
        List<AdBreakInfo> adBreaks;
        AdBreakStatus adBreakStatus = this.zzs;
        if (adBreakStatus == null) {
            return null;
        }
        String breakId = adBreakStatus.getBreakId();
        if (!TextUtils.isEmpty(breakId) && (mediaInfo = this.zza) != null && (adBreaks = mediaInfo.getAdBreaks()) != null && !adBreaks.isEmpty()) {
            for (AdBreakInfo adBreakInfo : adBreaks) {
                if (breakId.equals(adBreakInfo.getId())) {
                    return adBreakInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public AdBreakClipInfo getCurrentAdBreakClip() {
        MediaInfo mediaInfo;
        List<AdBreakClipInfo> adBreakClips;
        AdBreakStatus adBreakStatus = this.zzs;
        if (adBreakStatus == null) {
            return null;
        }
        String breakClipId = adBreakStatus.getBreakClipId();
        if (!TextUtils.isEmpty(breakClipId) && (mediaInfo = this.zza) != null && (adBreakClips = mediaInfo.getAdBreakClips()) != null && !adBreakClips.isEmpty()) {
            for (AdBreakClipInfo adBreakClipInfo : adBreakClips) {
                if (breakClipId.equals(adBreakClipInfo.getId())) {
                    return adBreakClipInfo;
                }
            }
        }
        return null;
    }

    public int getCurrentItemId() {
        return this.zzc;
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzo;
    }

    public int getIdleReason() {
        return this.zzf;
    }

    @NonNull
    public Integer getIndexById(int i) {
        return (Integer) this.zzy.get(i);
    }

    @Nullable
    public MediaQueueItem getItemById(int i) {
        Integer num = (Integer) this.zzy.get(i);
        if (num == null) {
            return null;
        }
        return (MediaQueueItem) this.zzq.get(num.intValue());
    }

    @Nullable
    public MediaQueueItem getItemByIndex(int i) {
        if (i < 0 || i >= this.zzq.size()) {
            return null;
        }
        return (MediaQueueItem) this.zzq.get(i);
    }

    @Nullable
    public MediaLiveSeekableRange getLiveSeekableRange() {
        return this.zzu;
    }

    public int getLoadingItemId() {
        return this.zzl;
    }

    @Nullable
    public MediaInfo getMediaInfo() {
        return this.zza;
    }

    public double getPlaybackRate() {
        return this.zzd;
    }

    public int getPlayerState() {
        return this.zze;
    }

    public int getPreloadedItemId() {
        return this.zzm;
    }

    @Nullable
    public MediaQueueData getQueueData() {
        return this.zzv;
    }

    @Nullable
    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    @Nullable
    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public int getQueueItemCount() {
        return this.zzq.size();
    }

    @NonNull
    public List<MediaQueueItem> getQueueItems() {
        return this.zzq;
    }

    public int getQueueRepeatMode() {
        return this.zzp;
    }

    public long getStreamPosition() {
        return this.zzg;
    }

    public double getStreamVolume() {
        return this.zzi;
    }

    @KeepForSdk
    public long getSupportedMediaCommands() {
        return this.zzh;
    }

    @Nullable
    public VideoInfo getVideoInfo() {
        return this.zzt;
    }

    @NonNull
    @KeepForSdk
    public Writer getWriter() {
        return this.zzz;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, Long.valueOf(this.zzb), Integer.valueOf(this.zzc), Double.valueOf(this.zzd), Integer.valueOf(this.zze), Integer.valueOf(this.zzf), Long.valueOf(this.zzg), Long.valueOf(this.zzh), Double.valueOf(this.zzi), Boolean.valueOf(this.zzj), Integer.valueOf(Arrays.hashCode(this.zzk)), Integer.valueOf(this.zzl), Integer.valueOf(this.zzm), String.valueOf(this.zzo), Integer.valueOf(this.zzp), this.zzq, Boolean.valueOf(this.zzr), this.zzs, this.zzt, this.zzu, this.zzv);
    }

    public boolean isMediaCommandSupported(long j) {
        return (j & this.zzh) != 0;
    }

    public boolean isMute() {
        return this.zzj;
    }

    public boolean isPlayingAd() {
        return this.zzr;
    }

    @NonNull
    @KeepForSdk
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mediaSessionId", this.zzb);
            int i = this.zze;
            String str = "IDLE";
            if (i != 1) {
                if (i == 2) {
                    str = "PLAYING";
                } else if (i == 3) {
                    str = "PAUSED";
                } else if (i == 4) {
                    str = "BUFFERING";
                } else if (i == 5) {
                    str = "LOADING";
                }
            }
            jSONObject.put("playerState", str);
            JSONArray jSONArray = null;
            if (this.zze == 1) {
                int i2 = this.zzf;
                jSONObject.putOpt("idleReason", i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? null : MediaError.ERROR_TYPE_ERROR : "INTERRUPTED" : "CANCELLED" : "FINISHED");
            }
            jSONObject.put("playbackRate", this.zzd);
            jSONObject.put("currentTime", CastUtils.millisecToSec(this.zzg));
            jSONObject.put("supportedMediaCommands", this.zzh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("level", this.zzi);
            jSONObject2.put("muted", this.zzj);
            jSONObject.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject2);
            if (this.zzk != null) {
                jSONArray = new JSONArray();
                for (long j : this.zzk) {
                    jSONArray.put(j);
                }
            }
            jSONObject.putOpt("activeTrackIds", jSONArray);
            jSONObject.putOpt("customData", this.zzo);
            jSONObject.putOpt("shuffle", Boolean.valueOf(this.zzw));
            MediaInfo mediaInfo = this.zza;
            if (mediaInfo != null) {
                jSONObject.putOpt("media", mediaInfo.zza());
            }
            int i3 = this.zzc;
            if (i3 != 0) {
                jSONObject.put("currentItemId", i3);
            }
            int i4 = this.zzm;
            if (i4 != 0) {
                jSONObject.put("preloadedItemId", i4);
            }
            int i5 = this.zzl;
            if (i5 != 0) {
                jSONObject.put("loadingItemId", i5);
            }
            AdBreakStatus adBreakStatus = this.zzs;
            if (adBreakStatus != null) {
                jSONObject.putOpt("breakStatus", adBreakStatus.zzb());
            }
            VideoInfo videoInfo = this.zzt;
            if (videoInfo != null) {
                jSONObject.putOpt("videoInfo", videoInfo.zzb());
            }
            MediaQueueData mediaQueueData = this.zzv;
            if (mediaQueueData != null) {
                jSONObject.putOpt("queueData", mediaQueueData.zza());
            }
            MediaLiveSeekableRange mediaLiveSeekableRange = this.zzu;
            if (mediaLiveSeekableRange != null) {
                jSONObject.putOpt("liveSeekableRange", mediaLiveSeekableRange.zzb());
            }
            jSONObject.putOpt("repeatMode", MediaCommon.zza(Integer.valueOf(this.zzp)));
            List list = this.zzq;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = this.zzq.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(((MediaQueueItem) it.next()).toJson());
                }
                jSONObject.put(ContextMappingConstants.ITEMS, jSONArray2);
            }
            return jSONObject;
        } catch (JSONException e) {
            zzx.e(e, "Error transforming MediaStatus into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x018c A[EDGE_INSN: B:102:0x018c->B:103:0x0190 BREAK  A[LOOP:0: B:95:0x0178->B:99:0x0185]] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(@androidx.annotation.NonNull org.json.JSONObject r14, int r15) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 922
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zza(org.json.JSONObject, int):int");
    }

    public final long zzb() {
        return this.zzb;
    }

    public final boolean zzd() {
        MediaInfo mediaInfo = this.zza;
        return zzf(this.zze, this.zzf, this.zzl, mediaInfo == null ? -1 : mediaInfo.getStreamType());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        JSONObject jSONObject = this.zzo;
        this.zzn = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(parcel, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(parcel, 6, getPlayerState());
        SafeParcelWriter.writeInt(parcel, 7, getIdleReason());
        SafeParcelWriter.writeLong(parcel, 8, getStreamPosition());
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeDouble(parcel, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(parcel, 11, isMute());
        SafeParcelWriter.writeLongArray(parcel, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(parcel, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(parcel, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(parcel, 15, this.zzn, false);
        SafeParcelWriter.writeInt(parcel, 16, this.zzp);
        SafeParcelWriter.writeTypedList(parcel, 17, this.zzq, false);
        SafeParcelWriter.writeBoolean(parcel, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(parcel, 19, getAdBreakStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 20, getVideoInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 21, getLiveSeekableRange(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 22, getQueueData(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @KeepForSdk
    public MediaStatus(@NonNull JSONObject jSONObject) throws JSONException {
        this(null, 0L, 0, 0.0d, 0, 0, 0L, 0L, 0.0d, false, null, 0, 0, null, 0, null, false, null, null, null, null);
        zza(jSONObject, 0);
    }
}
