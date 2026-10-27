package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "AdBreakClipInfoCreator")
@SafeParcelable.Reserved({1})
public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;

    @NonNull
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getId", id = 2)
    private final String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    private final String zzb;

    @SafeParcelable.Field(getter = "getDurationInMs", id = 4)
    private final long zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getContentUrl", id = 5)
    private final String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getMimeType", id = 6)
    private final String zze;

    @Nullable
    @SafeParcelable.Field(getter = "getClickThroughUrl", id = 7)
    private final String zzf;

    @Nullable
    @SafeParcelable.Field(getter = "getCustomDataAsString", id = 8)
    private String zzg;

    @Nullable
    @SafeParcelable.Field(getter = "getContentId", id = 9)
    private final String zzh;

    @Nullable
    @SafeParcelable.Field(getter = "getImageUrl", id = 10)
    private final String zzi;

    @SafeParcelable.Field(getter = "getWhenSkippableInMs", id = 11)
    private final long zzj;

    @Nullable
    @HlsSegmentFormat
    @SafeParcelable.Field(getter = "getHlsSegmentFormat", id = 12)
    private final String zzk;

    @Nullable
    @SafeParcelable.Field(getter = "getVastAdsRequest", id = 13)
    private final VastAdsRequest zzl;
    private JSONObject zzm;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private final String zza;
        private String zzb;
        private long zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;
        private String zzi;
        private long zzj = -1;

        @HlsSegmentFormat
        private String zzk;
        private VastAdsRequest zzl;

        public Builder(@NonNull String str) {
            this.zza = str;
        }

        @NonNull
        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl);
        }

        @NonNull
        public Builder setClickThroughUrl(@NonNull String str) {
            this.zzf = str;
            return this;
        }

        @NonNull
        public Builder setContentId(@NonNull String str) {
            this.zzh = str;
            return this;
        }

        @NonNull
        public Builder setContentUrl(@NonNull String str) {
            this.zzd = str;
            return this;
        }

        @NonNull
        public Builder setCustomDataJsonString(@NonNull String str) {
            this.zzg = str;
            return this;
        }

        @NonNull
        public Builder setDurationInMs(long j) {
            this.zzc = j;
            return this;
        }

        @NonNull
        public Builder setHlsSegmentFormat(@NonNull String str) {
            this.zzk = str;
            return this;
        }

        @NonNull
        public Builder setImageUrl(@NonNull String str) {
            this.zzi = str;
            return this;
        }

        @NonNull
        public Builder setMimeType(@NonNull String str) {
            this.zze = str;
            return this;
        }

        @NonNull
        public Builder setTitle(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setVastAdsRequest(@NonNull VastAdsRequest vastAdsRequest) {
            this.zzl = vastAdsRequest;
            return this;
        }

        @NonNull
        public Builder setWhenSkippableInMs(long j) {
            this.zzj = j;
            return this;
        }
    }

    @SafeParcelable.Constructor
    AdBreakClipInfo(@SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) long j, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 6) String str4, @Nullable @SafeParcelable.Param(id = 7) String str5, @Nullable @SafeParcelable.Param(id = 8) String str6, @Nullable @SafeParcelable.Param(id = 9) String str7, @Nullable @SafeParcelable.Param(id = 10) String str8, @SafeParcelable.Param(id = 11) long j2, @Nullable @HlsSegmentFormat @SafeParcelable.Param(id = 12) String str9, @Nullable @SafeParcelable.Param(id = 13) VastAdsRequest vastAdsRequest) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = str3;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
        this.zzh = str7;
        this.zzi = str8;
        this.zzj = j2;
        this.zzk = str9;
        this.zzl = vastAdsRequest;
        if (TextUtils.isEmpty(str6)) {
            this.zzm = new JSONObject();
            return;
        }
        try {
            this.zzm = new JSONObject(this.zzg);
        } catch (JSONException e) {
            Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", e.getMessage()));
            this.zzg = null;
            this.zzm = new JSONObject();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        return CastUtils.zzh(this.zza, adBreakClipInfo.zza) && CastUtils.zzh(this.zzb, adBreakClipInfo.zzb) && this.zzc == adBreakClipInfo.zzc && CastUtils.zzh(this.zzd, adBreakClipInfo.zzd) && CastUtils.zzh(this.zze, adBreakClipInfo.zze) && CastUtils.zzh(this.zzf, adBreakClipInfo.zzf) && CastUtils.zzh(this.zzg, adBreakClipInfo.zzg) && CastUtils.zzh(this.zzh, adBreakClipInfo.zzh) && CastUtils.zzh(this.zzi, adBreakClipInfo.zzi) && this.zzj == adBreakClipInfo.zzj && CastUtils.zzh(this.zzk, adBreakClipInfo.zzk) && CastUtils.zzh(this.zzl, adBreakClipInfo.zzl);
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.zzf;
    }

    @Nullable
    public String getContentId() {
        return this.zzh;
    }

    @Nullable
    public String getContentUrl() {
        return this.zzd;
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzm;
    }

    public long getDurationInMs() {
        return this.zzc;
    }

    @Nullable
    public String getHlsSegmentFormat() {
        return this.zzk;
    }

    @NonNull
    public String getId() {
        return this.zza;
    }

    @Nullable
    public String getImageUrl() {
        return this.zzi;
    }

    @Nullable
    public String getMimeType() {
        return this.zze;
    }

    @Nullable
    public String getTitle() {
        return this.zzb;
    }

    @Nullable
    public VastAdsRequest getVastAdsRequest() {
        return this.zzl;
    }

    public long getWhenSkippableInMs() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, Long.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, Long.valueOf(this.zzj), this.zzk, this.zzl);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeString(parcel, 5, getContentUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getMimeType(), false);
        SafeParcelWriter.writeString(parcel, 7, getClickThroughUrl(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 10, getImageUrl(), false);
        SafeParcelWriter.writeLong(parcel, 11, getWhenSkippableInMs());
        SafeParcelWriter.writeString(parcel, 12, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVastAdsRequest(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zza);
            jSONObject.put("duration", CastUtils.millisecToSec(this.zzc));
            long j = this.zzj;
            if (j != -1) {
                jSONObject.put("whenSkippable", CastUtils.millisecToSec(j));
            }
            String str = this.zzh;
            if (str != null) {
                jSONObject.put("contentId", str);
            }
            String str2 = this.zze;
            if (str2 != null) {
                jSONObject.put("contentType", str2);
            }
            String str3 = this.zzb;
            if (str3 != null) {
                jSONObject.put("title", str3);
            }
            String str4 = this.zzd;
            if (str4 != null) {
                jSONObject.put("contentUrl", str4);
            }
            String str5 = this.zzf;
            if (str5 != null) {
                jSONObject.put("clickThroughUrl", str5);
            }
            JSONObject jSONObject2 = this.zzm;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str6 = this.zzi;
            if (str6 != null) {
                jSONObject.put("posterUrl", str6);
            }
            String str7 = this.zzk;
            if (str7 != null) {
                jSONObject.put("hlsSegmentFormat", str7);
            }
            VastAdsRequest vastAdsRequest = this.zzl;
            if (vastAdsRequest != null) {
                jSONObject.put("vastAdsRequest", vastAdsRequest.zza());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
