package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "VastAdsRequestCreator")
@SafeParcelable.Reserved({1})
public class VastAdsRequest extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = new zzdr();

    @Nullable
    @SafeParcelable.Field(getter = "getAdTagUrl", id = 2)
    private final String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getAdsResponse", id = 3)
    private final String zzb;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private String zza;
        private String zzb;

        @NonNull
        public VastAdsRequest build() {
            return new VastAdsRequest(this.zza, this.zzb);
        }

        @NonNull
        public Builder setAdTagUrl(@NonNull String str) {
            this.zza = str;
            return this;
        }

        @NonNull
        public Builder setAdsResponse(@NonNull String str) {
            this.zzb = str;
            return this;
        }
    }

    @SafeParcelable.Constructor
    VastAdsRequest(@Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Nullable
    public static VastAdsRequest fromJson(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(CastUtils.optStringOrNull(jSONObject, "adTagUrl"), CastUtils.optStringOrNull(jSONObject, "adsResponse"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return CastUtils.zzh(this.zza, vastAdsRequest.zza) && CastUtils.zzh(this.zzb, vastAdsRequest.zzb);
    }

    @Nullable
    public String getAdTagUrl() {
        return this.zza;
    }

    @Nullable
    public String getAdsResponse() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(parcel, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.zza;
            if (str != null) {
                jSONObject.put("adTagUrl", str);
            }
            String str2 = this.zzb;
            if (str2 != null) {
                jSONObject.put("adsResponse", str2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
