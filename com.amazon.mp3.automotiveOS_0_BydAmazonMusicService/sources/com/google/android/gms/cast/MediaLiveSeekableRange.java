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
import com.google.firebase.perf.plugin.util.AsmString;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "MediaLiveSeekableRangeCreator")
@SafeParcelable.Reserved({1})
public class MediaLiveSeekableRange extends AbstractSafeParcelable {

    @SafeParcelable.Field(getter = "getStartTime", id = 2)
    private final long zzb;

    @SafeParcelable.Field(getter = "getEndTime", id = 3)
    private final long zzc;

    @SafeParcelable.Field(getter = "isMovingWindow", id = 4)
    private final boolean zzd;

    @SafeParcelable.Field(getter = "isLiveDone", id = 5)
    private final boolean zze;
    private static final Logger zza = new Logger("MediaLiveSeekableRange");

    @NonNull
    public static final Parcelable.Creator<MediaLiveSeekableRange> CREATOR = new zzbx();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private long zza;
        private long zzb;
        private boolean zzc;
        private boolean zzd;

        @NonNull
        public MediaLiveSeekableRange build() {
            return new MediaLiveSeekableRange(this.zza, this.zzb, this.zzc, this.zzd);
        }

        @NonNull
        public Builder setEndTime(long j) {
            this.zzb = j;
            return this;
        }

        @NonNull
        public Builder setIsLiveDone(boolean z) {
            this.zzd = z;
            return this;
        }

        @NonNull
        public Builder setIsMovingWindow(boolean z) {
            this.zzc = z;
            return this;
        }

        @NonNull
        public Builder setStartTime(long j) {
            this.zza = j;
            return this;
        }
    }

    @SafeParcelable.Constructor
    MediaLiveSeekableRange(@SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2) {
        this.zzb = Math.max(j, 0L);
        this.zzc = Math.max(j2, 0L);
        this.zzd = z;
        this.zze = z2;
    }

    @Nullable
    static MediaLiveSeekableRange zza(@Nullable JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(AsmString.METHOD_FIREBASE_PERF_TRACE_START) && jSONObject.has("end")) {
            try {
                long jSecToMillisec = CastUtils.secToMillisec(jSONObject.getDouble(AsmString.METHOD_FIREBASE_PERF_TRACE_START));
                double d = jSONObject.getDouble("end");
                return new MediaLiveSeekableRange(jSecToMillisec, CastUtils.secToMillisec(d), jSONObject.optBoolean("isMovingWindow"), jSONObject.optBoolean("isLiveDone"));
            } catch (JSONException unused) {
                zza.e("Ignoring Malformed MediaLiveSeekableRange: ".concat(jSONObject.toString()), new Object[0]);
            }
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLiveSeekableRange)) {
            return false;
        }
        MediaLiveSeekableRange mediaLiveSeekableRange = (MediaLiveSeekableRange) obj;
        return this.zzb == mediaLiveSeekableRange.zzb && this.zzc == mediaLiveSeekableRange.zzc && this.zzd == mediaLiveSeekableRange.zzd && this.zze == mediaLiveSeekableRange.zze;
    }

    public long getEndTime() {
        return this.zzc;
    }

    public long getStartTime() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzb), Long.valueOf(this.zzc), Boolean.valueOf(this.zzd), Boolean.valueOf(this.zze));
    }

    public boolean isLiveDone() {
        return this.zze;
    }

    public boolean isMovingWindow() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getStartTime());
        SafeParcelWriter.writeLong(parcel, 3, getEndTime());
        SafeParcelWriter.writeBoolean(parcel, 4, isMovingWindow());
        SafeParcelWriter.writeBoolean(parcel, 5, isLiveDone());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final JSONObject zzb() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AsmString.METHOD_FIREBASE_PERF_TRACE_START, CastUtils.millisecToSec(this.zzb));
            jSONObject.put("end", CastUtils.millisecToSec(this.zzc));
            jSONObject.put("isMovingWindow", this.zzd);
            jSONObject.put("isLiveDone", this.zze);
            return jSONObject;
        } catch (JSONException unused) {
            zza.e("Error transforming MediaLiveSeekableRange into JSONObject", new Object[0]);
            return new JSONObject();
        }
    }
}
