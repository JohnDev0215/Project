package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzee;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "MediaTrackCreator")
@SafeParcelable.Reserved({1})
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzcl();

    @NonNull
    public static final String ROLE_ALTERNATE = "alternate";

    @NonNull
    public static final String ROLE_CAPTION = "caption";

    @NonNull
    public static final String ROLE_COMMENTARY = "commentary";

    @NonNull
    public static final String ROLE_DESCRIPTION = "description";

    @NonNull
    public static final String ROLE_DUB = "dub";

    @NonNull
    public static final String ROLE_EMERGENCY = "emergency";

    @NonNull
    public static final String ROLE_FORCED_SUBTITLE = "forced_subtitle";

    @NonNull
    public static final String ROLE_MAIN = "main";

    @NonNull
    public static final String ROLE_SIGN = "sign";

    @NonNull
    public static final String ROLE_SUBTITLE = "subtitle";

    @NonNull
    public static final String ROLE_SUPPLEMENTARY = "supplementary";
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;

    @Nullable
    @SafeParcelable.Field(id = 10)
    String zza;

    @SafeParcelable.Field(getter = "getId", id = 2)
    private long zzb;

    @SafeParcelable.Field(getter = "getType", id = 3)
    private int zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getContentId", id = 4)
    private String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getContentType", id = 5)
    private String zze;

    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 6)
    private String zzf;

    @Nullable
    @SafeParcelable.Field(getter = "getLanguage", id = 7)
    private final String zzg;

    @SafeParcelable.Field(getter = "getSubtype", id = 8)
    private int zzh;

    @Nullable
    @SafeParcelable.Field(getter = "getRoles", id = 9)
    private final List zzi;

    @Nullable
    private final JSONObject zzj;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @VisibleForTesting
    public static class Builder {
        private final long zza;
        private final int zzb;

        @Nullable
        private String zzc;

        @Nullable
        private String zzd;

        @Nullable
        private String zze;

        @Nullable
        private String zzf;
        private int zzg = 0;

        @Nullable
        private List zzh;

        @Nullable
        private JSONObject zzi;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zza = j;
            this.zzb = i;
        }

        @NonNull
        public MediaTrack build() {
            return new MediaTrack(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
        }

        @NonNull
        public Builder setContentId(@Nullable String str) {
            this.zzc = str;
            return this;
        }

        @NonNull
        public Builder setContentType(@Nullable String str) {
            this.zzd = str;
            return this;
        }

        @NonNull
        public Builder setCustomData(@Nullable JSONObject jSONObject) {
            this.zzi = jSONObject;
            return this;
        }

        @NonNull
        public Builder setLanguage(@Nullable String str) {
            this.zzf = str;
            return this;
        }

        @NonNull
        public Builder setLanguage(@NonNull Locale locale) {
            this.zzf = CastUtils.zzd(locale);
            return this;
        }

        @NonNull
        public Builder setName(@Nullable String str) {
            this.zze = str;
            return this;
        }

        @NonNull
        public Builder setRoles(@Nullable List<String> list) {
            if (list != null) {
                list = zzee.zzl(list);
            }
            this.zzh = list;
            return this;
        }

        @NonNull
        public Builder setSubtype(int i) throws IllegalArgumentException {
            if (i < -1 || i > 5) {
                StringBuilder sb = new StringBuilder(27);
                sb.append("invalid subtype ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            if (i != 0 && this.zzb != 1) {
                throw new IllegalArgumentException("subtypes are only valid for text tracks");
            }
            this.zzg = i;
            return this;
        }
    }

    MediaTrack(long j, int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, int i2, @Nullable List list, @Nullable JSONObject jSONObject) {
        this.zzb = j;
        this.zzc = i;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzh = i2;
        this.zzi = list;
        this.zzj = jSONObject;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        JSONObject jSONObject = this.zzj;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaTrack.zzj;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && this.zzb == mediaTrack.zzb && this.zzc == mediaTrack.zzc && CastUtils.zzh(this.zzd, mediaTrack.zzd) && CastUtils.zzh(this.zze, mediaTrack.zze) && CastUtils.zzh(this.zzf, mediaTrack.zzf) && CastUtils.zzh(this.zzg, mediaTrack.zzg) && this.zzh == mediaTrack.zzh && CastUtils.zzh(this.zzi, mediaTrack.zzi);
    }

    @Nullable
    public String getContentId() {
        return this.zzd;
    }

    @Nullable
    public String getContentType() {
        return this.zze;
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzj;
    }

    public long getId() {
        return this.zzb;
    }

    @Nullable
    public String getLanguage() {
        return this.zzg;
    }

    @Nullable
    @TargetApi(21)
    public Locale getLanguageLocale() {
        if (TextUtils.isEmpty(this.zzg)) {
            return null;
        }
        if (PlatformVersion.isAtLeastLollipop()) {
            return Locale.forLanguageTag(this.zzg);
        }
        String[] strArrSplit = this.zzg.split("-", -1);
        return strArrSplit.length == 1 ? new Locale(strArrSplit[0]) : new Locale(strArrSplit[0], strArrSplit[1]);
    }

    @Nullable
    public String getName() {
        return this.zzf;
    }

    @Nullable
    public List<String> getRoles() {
        return this.zzi;
    }

    public int getSubtype() {
        return this.zzh;
    }

    public int getType() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, Integer.valueOf(this.zzh), this.zzi, String.valueOf(this.zzj));
    }

    public void setContentId(@Nullable String str) {
        this.zzd = str;
    }

    public void setContentType(@Nullable String str) {
        this.zze = str;
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.zzb);
            int i = this.zzc;
            if (i == 1) {
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "TEXT");
            } else if (i == 2) {
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "AUDIO");
            } else if (i == 3) {
                jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "VIDEO");
            }
            String str = this.zzd;
            if (str != null) {
                jSONObject.put("trackContentId", str);
            }
            String str2 = this.zze;
            if (str2 != null) {
                jSONObject.put("trackContentType", str2);
            }
            String str3 = this.zzf;
            if (str3 != null) {
                jSONObject.put("name", str3);
            }
            if (!TextUtils.isEmpty(this.zzg)) {
                jSONObject.put("language", this.zzg);
            }
            int i2 = this.zzh;
            if (i2 == 1) {
                jSONObject.put("subtype", "SUBTITLES");
            } else if (i2 == 2) {
                jSONObject.put("subtype", "CAPTIONS");
            } else if (i2 == 3) {
                jSONObject.put("subtype", "DESCRIPTIONS");
            } else if (i2 == 4) {
                jSONObject.put("subtype", "CHAPTERS");
            } else if (i2 == 5) {
                jSONObject.put("subtype", "METADATA");
            }
            List list = this.zzi;
            if (list != null) {
                jSONObject.put("roles", new JSONArray((Collection<?>) list));
            }
            JSONObject jSONObject2 = this.zzj;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        JSONObject jSONObject = this.zzj;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getId());
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeString(parcel, 4, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 5, getContentType(), false);
        SafeParcelWriter.writeString(parcel, 6, getName(), false);
        SafeParcelWriter.writeString(parcel, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(parcel, 8, getSubtype());
        SafeParcelWriter.writeStringList(parcel, 9, getRoles(), false);
        SafeParcelWriter.writeString(parcel, 10, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
