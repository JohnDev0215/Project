package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "AdBreakInfoCreator")
@SafeParcelable.Reserved({1})
public class AdBreakInfo extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<AdBreakInfo> CREATOR = new zzb();

    @SafeParcelable.Field(getter = "getPlaybackPositionInMs", id = 2)
    private final long zza;

    @SafeParcelable.Field(getter = "getId", id = 3)
    private final String zzb;

    @SafeParcelable.Field(getter = "getDurationInMs", id = 4)
    private final long zzc;

    @SafeParcelable.Field(getter = "isWatched", id = 5)
    private final boolean zzd;

    @SafeParcelable.Field(getter = "getBreakClipIds", id = 6)
    private final String[] zze;

    @SafeParcelable.Field(getter = "isEmbedded", id = 7)
    private final boolean zzf;

    @SafeParcelable.Field(getter = "isExpanded", id = 8)
    private final boolean zzg;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private final long zza;
        private String zzb;
        private long zzc;
        private boolean zzd;
        private boolean zze;
        private String[] zzf;
        private boolean zzg;

        public Builder(long j) {
            this.zza = j;
        }

        @NonNull
        public AdBreakInfo build() {
            return new AdBreakInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg);
        }

        @NonNull
        public Builder setBreakClipIds(@NonNull String[] strArr) {
            this.zzf = strArr;
            return this;
        }

        @NonNull
        public Builder setDurationInMs(long j) {
            this.zzc = j;
            return this;
        }

        @NonNull
        public Builder setId(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public Builder setIsEmbedded(boolean z) {
            this.zze = z;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setIsExpanded(boolean z) {
            this.zzg = z;
            return this;
        }

        @NonNull
        public Builder setIsWatched(boolean z) {
            this.zzd = z;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public AdBreakInfo(@SafeParcelable.Param(id = 2) long j, @NonNull @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) boolean z, @NonNull @SafeParcelable.Param(id = 6) String[] strArr, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) boolean z3) {
        this.zza = j;
        this.zzb = str;
        this.zzc = j2;
        this.zzd = z;
        this.zze = strArr;
        this.zzf = z2;
        this.zzg = z3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return CastUtils.zzh(this.zzb, adBreakInfo.zzb) && this.zza == adBreakInfo.zza && this.zzc == adBreakInfo.zzc && this.zzd == adBreakInfo.zzd && Arrays.equals(this.zze, adBreakInfo.zze) && this.zzf == adBreakInfo.zzf && this.zzg == adBreakInfo.zzg;
    }

    @NonNull
    public String[] getBreakClipIds() {
        return this.zze;
    }

    public long getDurationInMs() {
        return this.zzc;
    }

    @NonNull
    public String getId() {
        return this.zzb;
    }

    public long getPlaybackPositionInMs() {
        return this.zza;
    }

    public int hashCode() {
        return this.zzb.hashCode();
    }

    public boolean isEmbedded() {
        return this.zzf;
    }

    @KeepForSdk
    public boolean isExpanded() {
        return this.zzg;
    }

    public boolean isWatched() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getPlaybackPositionInMs());
        SafeParcelWriter.writeString(parcel, 3, getId(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeBoolean(parcel, 5, isWatched());
        SafeParcelWriter.writeStringArray(parcel, 6, getBreakClipIds(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmbedded());
        SafeParcelWriter.writeBoolean(parcel, 8, isExpanded());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zzb);
            jSONObject.put("position", CastUtils.millisecToSec(this.zza));
            jSONObject.put("isWatched", this.zzd);
            jSONObject.put("isEmbedded", this.zzf);
            jSONObject.put("duration", CastUtils.millisecToSec(this.zzc));
            jSONObject.put("expanded", this.zzg);
            if (this.zze != null) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.zze) {
                    jSONArray.put(str);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
