package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "SessionStateCreator")
@SafeParcelable.Reserved({1})
public class SessionState extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<SessionState> CREATOR = new zzdp();

    @Nullable
    @SafeParcelable.Field(id = 3)
    String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getLoadRequestData", id = 2)
    private final MediaLoadRequestData zzb;

    @Nullable
    private final JSONObject zzc;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {

        @Nullable
        private MediaLoadRequestData zza;

        @Nullable
        private JSONObject zzb;

        @NonNull
        public SessionState build() {
            return new SessionState(this.zza, this.zzb);
        }

        @NonNull
        public Builder setCustomData(@Nullable JSONObject jSONObject) {
            this.zzb = jSONObject;
            return this;
        }

        @NonNull
        public Builder setLoadRequestData(@Nullable MediaLoadRequestData mediaLoadRequestData) {
            this.zza = mediaLoadRequestData;
            return this;
        }
    }

    SessionState(@Nullable MediaLoadRequestData mediaLoadRequestData, @Nullable JSONObject jSONObject) {
        this.zzb = mediaLoadRequestData;
        this.zzc = jSONObject;
    }

    @NonNull
    @KeepForSdk
    public static SessionState fromJson(@NonNull JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("loadRequestData");
        return new SessionState(jSONObjectOptJSONObject != null ? MediaLoadRequestData.fromJson(jSONObjectOptJSONObject) : null, jSONObject.optJSONObject("customData"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionState)) {
            return false;
        }
        SessionState sessionState = (SessionState) obj;
        if (JsonUtils.areJsonValuesEquivalent(this.zzc, sessionState.zzc)) {
            return Objects.equal(this.zzb, sessionState.zzb);
        }
        return false;
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzc;
    }

    @Nullable
    public MediaLoadRequestData getLoadRequestData() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, String.valueOf(this.zzc));
    }

    @Nullable
    @KeepForSdk
    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            MediaLoadRequestData mediaLoadRequestData = this.zzb;
            if (mediaLoadRequestData != null) {
                jSONObject.put("loadRequestData", mediaLoadRequestData.toJson());
            }
            jSONObject.put("customData", this.zzc);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        JSONObject jSONObject = this.zzc;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getLoadRequestData(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
