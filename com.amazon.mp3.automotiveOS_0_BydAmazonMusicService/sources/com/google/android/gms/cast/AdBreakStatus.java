package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "AdBreakStatusCreator")
@SafeParcelable.Reserved({1})
public class AdBreakStatus extends AbstractSafeParcelable {

    @Deprecated
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;

    @SafeParcelable.Field(getter = "getCurrentBreakTimeInMs", id = 2)
    private final long zzb;

    @SafeParcelable.Field(getter = "getCurrentBreakClipTimeInMs", id = 3)
    private final long zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getBreakId", id = 4)
    private final String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getBreakClipId", id = 5)
    private final String zze;

    @SafeParcelable.Field(getter = "getWhenSkippableInMs", id = 6)
    private final long zzf;
    private static final Logger zza = new Logger("AdBreakStatus");

    @NonNull
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private long zza;
        private long zzb;
        private String zzc;
        private String zzd;
        private long zze = -1;

        @NonNull
        public AdBreakStatus build() {
            return new AdBreakStatus(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        @NonNull
        public Builder setBreakClipId(@NonNull String str) {
            this.zzd = str;
            return this;
        }

        @NonNull
        public Builder setBreakId(@NonNull String str) {
            this.zzc = str;
            return this;
        }

        @NonNull
        public Builder setCurrentBreakClipTimeInMs(long j) {
            this.zzb = j;
            return this;
        }

        @NonNull
        public Builder setCurrentBreakTimeInMs(long j) {
            this.zza = j;
            return this;
        }

        @NonNull
        public Builder setWhenSkippableInMs(long j) {
            this.zze = j;
            return this;
        }
    }

    @SafeParcelable.Constructor
    AdBreakStatus(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2, @Nullable @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) long j3) {
        this.zzb = j;
        this.zzc = j2;
        this.zzd = str;
        this.zze = str2;
        this.zzf = j3;
    }

    @Nullable
    static AdBreakStatus zza(@Nullable JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("currentBreakTime") && jSONObject.has("currentBreakClipTime")) {
            try {
                long jSecToMillisec = CastUtils.secToMillisec(jSONObject.getLong("currentBreakTime"));
                long jSecToMillisec2 = CastUtils.secToMillisec(jSONObject.getLong("currentBreakClipTime"));
                String strOptStringOrNull = CastUtils.optStringOrNull(jSONObject, "breakId");
                String strOptStringOrNull2 = CastUtils.optStringOrNull(jSONObject, "breakClipId");
                long jOptLong = jSONObject.optLong("whenSkippable", -1L);
                return new AdBreakStatus(jSecToMillisec, jSecToMillisec2, strOptStringOrNull, strOptStringOrNull2, jOptLong != -1 ? CastUtils.secToMillisec(jOptLong) : jOptLong);
            } catch (JSONException e) {
                zza.e(e, "Error while creating an AdBreakClipInfo from JSON", new Object[0]);
            }
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.zzb == adBreakStatus.zzb && this.zzc == adBreakStatus.zzc && CastUtils.zzh(this.zzd, adBreakStatus.zzd) && CastUtils.zzh(this.zze, adBreakStatus.zze) && this.zzf == adBreakStatus.zzf;
    }

    @Nullable
    public String getBreakClipId() {
        return this.zze;
    }

    @Nullable
    public String getBreakId() {
        return this.zzd;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzc;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzb;
    }

    public long getWhenSkippableInMs() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzb), Long.valueOf(this.zzc), this.zzd, this.zze, Long.valueOf(this.zzf));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentBreakTimeInMs());
        SafeParcelWriter.writeLong(parcel, 3, getCurrentBreakClipTimeInMs());
        SafeParcelWriter.writeString(parcel, 4, getBreakId(), false);
        SafeParcelWriter.writeString(parcel, 5, getBreakClipId(), false);
        SafeParcelWriter.writeLong(parcel, 6, getWhenSkippableInMs());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("currentBreakTime", CastUtils.millisecToSec(this.zzb));
            jSONObject.put("currentBreakClipTime", CastUtils.millisecToSec(this.zzc));
            jSONObject.putOpt("breakId", this.zzd);
            jSONObject.putOpt("breakClipId", this.zze);
            long j = this.zzf;
            if (j != -1) {
                jSONObject.put("whenSkippable", CastUtils.millisecToSec(j));
            }
            return jSONObject;
        } catch (JSONException e) {
            zza.e(e, "Error transforming AdBreakStatus into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }
}
