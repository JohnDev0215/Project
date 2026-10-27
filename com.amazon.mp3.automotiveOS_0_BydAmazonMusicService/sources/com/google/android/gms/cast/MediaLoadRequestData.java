package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.MediaQueueData;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "MediaLoadRequestDataCreator")
@SafeParcelable.Reserved({1})
public class MediaLoadRequestData extends AbstractSafeParcelable implements RequestData {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    public static final long PLAY_POSITION_UNASSIGNED = -1;

    @Nullable
    @SafeParcelable.Field(id = 8)
    String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getMediaInfo", id = 2)
    private final MediaInfo zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getQueueData", id = 3)
    private final MediaQueueData zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getAutoplay", id = 4)
    private final Boolean zze;

    @SafeParcelable.Field(getter = "getCurrentTime", id = 5)
    private final long zzf;

    @SafeParcelable.Field(getter = "getPlaybackRate", id = 6)
    private final double zzg;

    @Nullable
    @SafeParcelable.Field(getter = "getActiveTrackIds", id = 7)
    private final long[] zzh;

    @Nullable
    private final JSONObject zzi;

    @Nullable
    @SafeParcelable.Field(getter = "getCredentials", id = 9)
    private final String zzj;

    @Nullable
    @SafeParcelable.Field(getter = "getCredentialsType", id = 10)
    private final String zzk;

    @Nullable
    @SafeParcelable.Field(getter = "getAtvCredentials", id = 11)
    private final String zzl;

    @Nullable
    @SafeParcelable.Field(getter = "getAtvCredentialsType", id = 12)
    private final String zzm;

    @SafeParcelable.Field(getter = "getRequestId", id = 13)
    private long zzn;
    private static final Logger zzb = new Logger("MediaLoadRequestData");

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<MediaLoadRequestData> CREATOR = new zzca();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {

        @Nullable
        private MediaInfo zza;

        @Nullable
        private MediaQueueData zzb;

        @Nullable
        private Boolean zzc;
        private long zzd;
        private double zze;

        @Nullable
        private long[] zzf;

        @Nullable
        private JSONObject zzg;

        @Nullable
        private String zzh;

        @Nullable
        private String zzi;

        @Nullable
        private String zzj;

        @Nullable
        private String zzk;
        private long zzl;

        public Builder() {
            this.zzc = Boolean.TRUE;
            this.zzd = -1L;
            this.zze = 1.0d;
        }

        @NonNull
        public MediaLoadRequestData build() {
            return new MediaLoadRequestData(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl);
        }

        @NonNull
        public Builder setActiveTrackIds(@Nullable long[] jArr) {
            this.zzf = jArr;
            return this;
        }

        @NonNull
        public Builder setAtvCredentials(@Nullable String str) {
            this.zzj = str;
            return this;
        }

        @NonNull
        public Builder setAtvCredentialsType(@Nullable String str) {
            this.zzk = str;
            return this;
        }

        @NonNull
        public Builder setAutoplay(@Nullable Boolean bool) {
            this.zzc = bool;
            return this;
        }

        @NonNull
        public Builder setCredentials(@Nullable String str) {
            this.zzh = str;
            return this;
        }

        @NonNull
        public Builder setCredentialsType(@Nullable String str) {
            this.zzi = str;
            return this;
        }

        @NonNull
        public Builder setCurrentTime(long j) {
            this.zzd = j;
            return this;
        }

        @NonNull
        public Builder setCustomData(@Nullable JSONObject jSONObject) {
            this.zzg = jSONObject;
            return this;
        }

        @NonNull
        public Builder setMediaInfo(@Nullable MediaInfo mediaInfo) {
            this.zza = mediaInfo;
            return this;
        }

        @NonNull
        public Builder setPlaybackRate(double d) {
            if (Double.compare(d, 2.0d) > 0 || Double.compare(d, 0.5d) < 0) {
                throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            }
            this.zze = d;
            return this;
        }

        @NonNull
        public Builder setQueueData(@Nullable MediaQueueData mediaQueueData) {
            this.zzb = mediaQueueData;
            return this;
        }

        @NonNull
        public final Builder zza(long j) {
            this.zzl = j;
            return this;
        }

        public Builder(@NonNull MediaLoadRequestData mediaLoadRequestData) {
            this.zzc = Boolean.TRUE;
            this.zzd = -1L;
            this.zze = 1.0d;
            this.zza = mediaLoadRequestData.getMediaInfo();
            this.zzb = mediaLoadRequestData.getQueueData();
            this.zzc = mediaLoadRequestData.getAutoplay();
            this.zzd = mediaLoadRequestData.getCurrentTime();
            this.zze = mediaLoadRequestData.getPlaybackRate();
            this.zzf = mediaLoadRequestData.getActiveTrackIds();
            this.zzg = mediaLoadRequestData.getCustomData();
            this.zzh = mediaLoadRequestData.getCredentials();
            this.zzi = mediaLoadRequestData.getCredentialsType();
            this.zzj = mediaLoadRequestData.zza();
            this.zzk = mediaLoadRequestData.zzb();
            this.zzl = mediaLoadRequestData.getRequestId();
        }
    }

    @SafeParcelable.Constructor
    MediaLoadRequestData(@Nullable @SafeParcelable.Param(id = 2) MediaInfo mediaInfo, @Nullable @SafeParcelable.Param(id = 3) MediaQueueData mediaQueueData, @Nullable @SafeParcelable.Param(id = 4) Boolean bool, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) double d, @Nullable @SafeParcelable.Param(id = 7) long[] jArr, @Nullable @SafeParcelable.Param(id = 8) String str, @Nullable @SafeParcelable.Param(id = 9) String str2, @Nullable @SafeParcelable.Param(id = 10) String str3, @Nullable @SafeParcelable.Param(id = 11) String str4, @Nullable @SafeParcelable.Param(id = 12) String str5, @SafeParcelable.Param(id = 13) long j2) {
        this(mediaInfo, mediaQueueData, bool, j, d, jArr, CastUtils.jsonStringToJsonObject(str), str2, str3, str4, str5, j2);
    }

    @NonNull
    @KeepForSdk
    public static MediaLoadRequestData fromJson(@NonNull JSONObject jSONObject) {
        Builder builder = new Builder();
        try {
            if (jSONObject.has("media")) {
                builder.setMediaInfo(new MediaInfo(jSONObject.getJSONObject("media")));
            }
            if (jSONObject.has("queueData")) {
                MediaQueueData.Builder builder2 = new MediaQueueData.Builder();
                builder2.zza(jSONObject.getJSONObject("queueData"));
                builder.setQueueData(builder2.build());
            }
            if (jSONObject.has("autoplay")) {
                builder.setAutoplay(Boolean.valueOf(jSONObject.getBoolean("autoplay")));
            } else {
                builder.setAutoplay(null);
            }
            if (jSONObject.has("currentTime")) {
                builder.setCurrentTime(CastUtils.secToMillisec(jSONObject.getDouble("currentTime")));
            } else {
                builder.setCurrentTime(-1L);
            }
            builder.setPlaybackRate(jSONObject.optDouble("playbackRate", 1.0d));
            builder.setCredentials(CastUtils.optStringOrNull(jSONObject, "credentials"));
            builder.setCredentialsType(CastUtils.optStringOrNull(jSONObject, "credentialsType"));
            builder.setAtvCredentials(CastUtils.optStringOrNull(jSONObject, "atvCredentials"));
            builder.setAtvCredentialsType(CastUtils.optStringOrNull(jSONObject, "atvCredentialsType"));
            builder.zza(jSONObject.optLong(TetheredMessageKey.requestId));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("activeTrackIds");
            if (jSONArrayOptJSONArray != null) {
                long[] jArr = new long[jSONArrayOptJSONArray.length()];
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    jArr[i] = jSONArrayOptJSONArray.getLong(i);
                }
                builder.setActiveTrackIds(jArr);
            }
            builder.setCustomData(jSONObject.optJSONObject("customData"));
            return builder.build();
        } catch (JSONException unused) {
            return builder.build();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLoadRequestData)) {
            return false;
        }
        MediaLoadRequestData mediaLoadRequestData = (MediaLoadRequestData) obj;
        return JsonUtils.areJsonValuesEquivalent(this.zzi, mediaLoadRequestData.zzi) && Objects.equal(this.zzc, mediaLoadRequestData.zzc) && Objects.equal(this.zzd, mediaLoadRequestData.zzd) && Objects.equal(this.zze, mediaLoadRequestData.zze) && this.zzf == mediaLoadRequestData.zzf && this.zzg == mediaLoadRequestData.zzg && Arrays.equals(this.zzh, mediaLoadRequestData.zzh) && Objects.equal(this.zzj, mediaLoadRequestData.zzj) && Objects.equal(this.zzk, mediaLoadRequestData.zzk) && Objects.equal(this.zzl, mediaLoadRequestData.zzl) && Objects.equal(this.zzm, mediaLoadRequestData.zzm) && this.zzn == mediaLoadRequestData.zzn;
    }

    @Nullable
    public long[] getActiveTrackIds() {
        return this.zzh;
    }

    @Nullable
    public Boolean getAutoplay() {
        return this.zze;
    }

    @Nullable
    public String getCredentials() {
        return this.zzj;
    }

    @Nullable
    public String getCredentialsType() {
        return this.zzk;
    }

    public long getCurrentTime() {
        return this.zzf;
    }

    @Override // com.google.android.gms.cast.RequestData
    @Nullable
    public JSONObject getCustomData() {
        return this.zzi;
    }

    @Nullable
    public MediaInfo getMediaInfo() {
        return this.zzc;
    }

    public double getPlaybackRate() {
        return this.zzg;
    }

    @Nullable
    public MediaQueueData getQueueData() {
        return this.zzd;
    }

    @Override // com.google.android.gms.cast.RequestData
    @KeepForSdk
    public long getRequestId() {
        return this.zzn;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzc, this.zzd, this.zze, Long.valueOf(this.zzf), Double.valueOf(this.zzg), this.zzh, String.valueOf(this.zzi), this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn));
    }

    @KeepForSdk
    public void setRequestId(long j) {
        this.zzn = j;
    }

    @NonNull
    @KeepForSdk
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            MediaInfo mediaInfo = this.zzc;
            if (mediaInfo != null) {
                jSONObject.put("media", mediaInfo.zza());
            }
            MediaQueueData mediaQueueData = this.zzd;
            if (mediaQueueData != null) {
                jSONObject.put("queueData", mediaQueueData.zza());
            }
            jSONObject.putOpt("autoplay", this.zze);
            long j = this.zzf;
            if (j != -1) {
                jSONObject.put("currentTime", CastUtils.millisecToSec(j));
            }
            jSONObject.put("playbackRate", this.zzg);
            jSONObject.putOpt("credentials", this.zzj);
            jSONObject.putOpt("credentialsType", this.zzk);
            jSONObject.putOpt("atvCredentials", this.zzl);
            jSONObject.putOpt("atvCredentialsType", this.zzm);
            if (this.zzh != null) {
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                while (true) {
                    long[] jArr = this.zzh;
                    if (i >= jArr.length) {
                        break;
                    }
                    jSONArray.put(i, jArr[i]);
                    i++;
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            jSONObject.putOpt("customData", this.zzi);
            jSONObject.put(TetheredMessageKey.requestId, this.zzn);
            return jSONObject;
        } catch (JSONException e) {
            zzb.e("Error transforming MediaLoadRequestData into JSONObject", e);
            return new JSONObject();
        }
    }

    @Nullable
    public final String zza() {
        return this.zzl;
    }

    @Nullable
    public final String zzb() {
        return this.zzm;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        JSONObject jSONObject = this.zzi;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getQueueData(), i, false);
        SafeParcelWriter.writeBooleanObject(parcel, 4, getAutoplay(), false);
        SafeParcelWriter.writeLong(parcel, 5, getCurrentTime());
        SafeParcelWriter.writeDouble(parcel, 6, getPlaybackRate());
        SafeParcelWriter.writeLongArray(parcel, 7, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zza, false);
        SafeParcelWriter.writeString(parcel, 9, getCredentials(), false);
        SafeParcelWriter.writeString(parcel, 10, getCredentialsType(), false);
        SafeParcelWriter.writeString(parcel, 11, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzm, false);
        SafeParcelWriter.writeLong(parcel, 13, getRequestId());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private MediaLoadRequestData(@Nullable MediaInfo mediaInfo, @Nullable MediaQueueData mediaQueueData, @Nullable Boolean bool, long j, double d, @Nullable long[] jArr, @Nullable JSONObject jSONObject, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, long j2) {
        this.zzc = mediaInfo;
        this.zzd = mediaQueueData;
        this.zze = bool;
        this.zzf = j;
        this.zzg = d;
        this.zzh = jArr;
        this.zzi = jSONObject;
        this.zzj = str;
        this.zzk = str2;
        this.zzl = str3;
        this.zzm = str4;
        this.zzn = j2;
    }
}
