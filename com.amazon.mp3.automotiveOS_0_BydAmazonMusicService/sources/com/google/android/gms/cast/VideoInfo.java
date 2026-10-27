package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "VideoInfoCreator")
@SafeParcelable.Reserved({1})
public final class VideoInfo extends AbstractSafeParcelable {
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;

    @SafeParcelable.Field(getter = "getWidth", id = 2)
    private int zzb;

    @SafeParcelable.Field(getter = "getHeight", id = 3)
    private int zzc;

    @SafeParcelable.Field(getter = "getHdrType", id = 4)
    private int zzd;
    private static final Logger zza = new Logger("VideoInfo");

    @NonNull
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzds();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private int zza;
        private int zzb;
        private int zzc;

        @NonNull
        public VideoInfo build() {
            return new VideoInfo(this.zza, this.zzb, this.zzc);
        }

        @NonNull
        public Builder setHdrType(int i) {
            this.zzc = i;
            return this;
        }

        @NonNull
        public Builder setHeight(int i) {
            this.zzb = i;
            return this;
        }

        @NonNull
        public Builder setWidth(int i) {
            this.zza = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    VideoInfo(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.cast.VideoInfo zza(@androidx.annotation.Nullable org.json.JSONObject r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            r1 = 0
            java.lang.String r2 = "hdrType"
            java.lang.String r2 = r8.getString(r2)     // Catch: org.json.JSONException -> L7b
            int r3 = r2.hashCode()     // Catch: org.json.JSONException -> L7b
            r4 = 3218(0xc92, float:4.51E-42)
            r5 = 3
            r6 = 2
            r7 = 1
            if (r3 == r4) goto L44
            r4 = 103158(0x192f6, float:1.44555E-40)
            if (r3 == r4) goto L3a
            r4 = 113729(0x1bc41, float:1.59368E-40)
            if (r3 == r4) goto L30
            r4 = 99136405(0x5e8b395, float:2.1883143E-35)
            if (r3 == r4) goto L26
            goto L4e
        L26:
            java.lang.String r3 = "hdr10"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4e
            r3 = r7
            goto L4f
        L30:
            java.lang.String r3 = "sdr"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4e
            r3 = r5
            goto L4f
        L3a:
            java.lang.String r3 = "hdr"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4e
            r3 = r6
            goto L4f
        L44:
            java.lang.String r3 = "dv"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4e
            r3 = r1
            goto L4f
        L4e:
            r3 = -1
        L4f:
            if (r3 == 0) goto L69
            if (r3 == r7) goto L68
            if (r3 == r6) goto L66
            if (r3 == r5) goto L64
            com.google.android.gms.cast.internal.Logger r3 = com.google.android.gms.cast.VideoInfo.zza     // Catch: org.json.JSONException -> L7b
            java.lang.Object[] r4 = new java.lang.Object[r7]     // Catch: org.json.JSONException -> L7b
            r4[r1] = r2     // Catch: org.json.JSONException -> L7b
            java.lang.String r2 = "Unknown HDR type: %s"
            r3.d(r2, r4)     // Catch: org.json.JSONException -> L7b
            r5 = r1
            goto L69
        L64:
            r5 = r7
            goto L69
        L66:
            r5 = 4
            goto L69
        L68:
            r5 = r6
        L69:
            com.google.android.gms.cast.VideoInfo r2 = new com.google.android.gms.cast.VideoInfo     // Catch: org.json.JSONException -> L7b
            java.lang.String r3 = "width"
            int r3 = r8.getInt(r3)     // Catch: org.json.JSONException -> L7b
            java.lang.String r4 = "height"
            int r8 = r8.getInt(r4)     // Catch: org.json.JSONException -> L7b
            r2.<init>(r3, r8, r5)     // Catch: org.json.JSONException -> L7b
            return r2
        L7b:
            r8 = move-exception
            com.google.android.gms.cast.internal.Logger r2 = com.google.android.gms.cast.VideoInfo.zza
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "Error while creating a VideoInfo instance from JSON"
            r2.d(r8, r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.VideoInfo.zza(org.json.JSONObject):com.google.android.gms.cast.VideoInfo");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.zzc == videoInfo.getHeight() && this.zzb == videoInfo.getWidth() && this.zzd == videoInfo.getHdrType();
    }

    public int getHdrType() {
        return this.zzd;
    }

    public int getHeight() {
        return this.zzc;
    }

    public int getWidth() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(this.zzd));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.writeInt(parcel, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.zzb);
            jSONObject.put("height", this.zzc);
            int i = this.zzd;
            jSONObject.put("hdrType", i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : "hdr" : "dv" : "hdr10" : "sdr");
            return jSONObject;
        } catch (JSONException unused) {
            zza.e("Failed to transform VideoInfo into Json", new Object[0]);
            return new JSONObject();
        }
    }
}
