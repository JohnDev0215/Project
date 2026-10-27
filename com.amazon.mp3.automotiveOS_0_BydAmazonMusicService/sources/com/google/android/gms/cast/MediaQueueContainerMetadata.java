package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "MediaQueueContainerMetadataCreator")
@SafeParcelable.Reserved({1})
public class MediaQueueContainerMetadata extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<MediaQueueContainerMetadata> CREATOR = new zzce();
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_AUDIO_BOOK = 1;
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_GENERIC = 0;

    @SafeParcelable.Field(getter = "getContainerType", id = 2)
    private int zza;

    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    private String zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getSections", id = 4)
    private List zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getContainerImages", id = 5)
    private List zzd;

    @SafeParcelable.Field(getter = "getContainerDuration", id = 6)
    private double zze;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private final MediaQueueContainerMetadata zza = new MediaQueueContainerMetadata(null);

        @NonNull
        public MediaQueueContainerMetadata build() {
            return new MediaQueueContainerMetadata(this.zza, null);
        }

        @NonNull
        public Builder setContainerDuration(double d) {
            this.zza.zze = d;
            return this;
        }

        @NonNull
        public Builder setContainerImages(@Nullable List<WebImage> list) {
            MediaQueueContainerMetadata.zzd(this.zza, list);
            return this;
        }

        @NonNull
        public Builder setContainerType(int i) {
            this.zza.zza = i;
            return this;
        }

        @NonNull
        public Builder setSections(@Nullable List<MediaMetadata> list) {
            this.zza.zzg(list);
            return this;
        }

        @NonNull
        public Builder setTitle(@Nullable String str) {
            this.zza.zzb = str;
            return this;
        }

        @NonNull
        public final Builder zza(@NonNull JSONObject jSONObject) {
            MediaQueueContainerMetadata.zzb(this.zza, jSONObject);
            return this;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueContainerType {
    }

    private MediaQueueContainerMetadata() {
        zzh();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* bridge */ /* synthetic */ void zzb(com.google.android.gms.cast.MediaQueueContainerMetadata r5, org.json.JSONObject r6) {
        /*
            r5.zzh()
            java.lang.String r0 = "containerType"
            java.lang.String r1 = ""
            java.lang.String r0 = r6.optString(r0, r1)
            int r1 = r0.hashCode()
            r2 = 6924225(0x69a7c1, float:9.702906E-39)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L26
            r2 = 828666841(0x316473d9, float:3.3244218E-9)
            if (r1 == r2) goto L1c
            goto L30
        L1c:
            java.lang.String r1 = "GENERIC_CONTAINER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L30
            r0 = r3
            goto L31
        L26:
            java.lang.String r1 = "AUDIOBOOK_CONTAINER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L30
            r0 = r4
            goto L31
        L30:
            r0 = -1
        L31:
            if (r0 == 0) goto L39
            if (r0 == r4) goto L36
            goto L3b
        L36:
            r5.zza = r4
            goto L3b
        L39:
            r5.zza = r3
        L3b:
            java.lang.String r0 = "title"
            java.lang.String r0 = com.google.android.gms.cast.internal.CastUtils.optStringOrNull(r6, r0)
            r5.zzb = r0
            java.lang.String r0 = "sections"
            org.json.JSONArray r0 = r6.optJSONArray(r0)
            if (r0 == 0) goto L6c
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzc = r1
        L52:
            int r2 = r0.length()
            if (r3 >= r2) goto L6c
            org.json.JSONObject r2 = r0.optJSONObject(r3)
            if (r2 == 0) goto L69
            com.google.android.gms.cast.MediaMetadata r4 = new com.google.android.gms.cast.MediaMetadata
            r4.<init>()
            r4.zzc(r2)
            r1.add(r4)
        L69:
            int r3 = r3 + 1
            goto L52
        L6c:
            java.lang.String r0 = "containerImages"
            org.json.JSONArray r0 = r6.optJSONArray(r0)
            if (r0 == 0) goto L7e
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzd = r1
            com.google.android.gms.cast.internal.media.zza.zzd(r1, r0)
        L7e:
            double r0 = r5.zze
            java.lang.String r2 = "containerDuration"
            double r0 = r6.optDouble(r2, r0)
            r5.zze = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaQueueContainerMetadata.zzb(com.google.android.gms.cast.MediaQueueContainerMetadata, org.json.JSONObject):void");
    }

    static /* bridge */ /* synthetic */ void zzd(MediaQueueContainerMetadata mediaQueueContainerMetadata, List list) {
        mediaQueueContainerMetadata.zzd = list == null ? null : new ArrayList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh() {
        this.zza = 0;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = 0.0d;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueContainerMetadata)) {
            return false;
        }
        MediaQueueContainerMetadata mediaQueueContainerMetadata = (MediaQueueContainerMetadata) obj;
        return this.zza == mediaQueueContainerMetadata.zza && TextUtils.equals(this.zzb, mediaQueueContainerMetadata.zzb) && Objects.equal(this.zzc, mediaQueueContainerMetadata.zzc) && Objects.equal(this.zzd, mediaQueueContainerMetadata.zzd) && this.zze == mediaQueueContainerMetadata.zze;
    }

    public double getContainerDuration() {
        return this.zze;
    }

    @Nullable
    public List<WebImage> getContainerImages() {
        List list = this.zzd;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public int getContainerType() {
        return this.zza;
    }

    @Nullable
    public List<MediaMetadata> getSections() {
        List list = this.zzc;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    @Nullable
    public String getTitle() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd, Double.valueOf(this.zze));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getContainerType());
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getSections(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getContainerImages(), false);
        SafeParcelWriter.writeDouble(parcel, 6, getContainerDuration());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = this.zza;
            if (i == 0) {
                jSONObject.put("containerType", "GENERIC_CONTAINER");
            } else if (i == 1) {
                jSONObject.put("containerType", "AUDIOBOOK_CONTAINER");
            }
            if (!TextUtils.isEmpty(this.zzb)) {
                jSONObject.put("title", this.zzb);
            }
            List list = this.zzc;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzc.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaMetadata) it.next()).zza());
                }
                jSONObject.put("sections", jSONArray);
            }
            List list2 = this.zzd;
            if (list2 != null && !list2.isEmpty()) {
                jSONObject.put("containerImages", com.google.android.gms.cast.internal.media.zza.zzc(this.zzd));
            }
            jSONObject.put("containerDuration", this.zze);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    final void zzg(@Nullable List list) {
        this.zzc = list == null ? null : new ArrayList(list);
    }

    @SafeParcelable.Constructor
    MediaQueueContainerMetadata(@SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) List list, @Nullable @SafeParcelable.Param(id = 5) List list2, @SafeParcelable.Param(id = 6) double d) {
        this.zza = i;
        this.zzb = str;
        this.zzc = list;
        this.zzd = list2;
        this.zze = d;
    }

    /* synthetic */ MediaQueueContainerMetadata(zzcd zzcdVar) {
        zzh();
    }

    /* synthetic */ MediaQueueContainerMetadata(MediaQueueContainerMetadata mediaQueueContainerMetadata, zzcd zzcdVar) {
        this.zza = mediaQueueContainerMetadata.zza;
        this.zzb = mediaQueueContainerMetadata.zzb;
        this.zzc = mediaQueueContainerMetadata.zzc;
        this.zzd = mediaQueueContainerMetadata.zzd;
        this.zze = mediaQueueContainerMetadata.zze;
    }
}
