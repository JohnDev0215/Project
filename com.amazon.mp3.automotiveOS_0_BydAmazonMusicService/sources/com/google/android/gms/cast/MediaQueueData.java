package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
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
@SafeParcelable.Class(creator = "MediaQueueDataCreator")
@SafeParcelable.Reserved({1})
public class MediaQueueData extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<MediaQueueData> CREATOR = new zzcg();
    public static final int MEDIA_QUEUE_TYPE_ALBUM = 1;
    public static final int MEDIA_QUEUE_TYPE_AUDIO_BOOK = 3;
    public static final int MEDIA_QUEUE_TYPE_GENERIC = 0;
    public static final int MEDIA_QUEUE_TYPE_LIVE_TV = 8;
    public static final int MEDIA_QUEUE_TYPE_MOVIE = 9;
    public static final int MEDIA_QUEUE_TYPE_PLAYLIST = 2;
    public static final int MEDIA_QUEUE_TYPE_PODCAST_SERIES = 5;
    public static final int MEDIA_QUEUE_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_QUEUE_TYPE_TV_SERIES = 6;
    public static final int MEDIA_QUEUE_TYPE_VIDEO_PLAYLIST = 7;

    @Nullable
    @SafeParcelable.Field(getter = "getQueueId", id = 2)
    private String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getEntity", id = 3)
    private String zzb;

    @SafeParcelable.Field(getter = "getQueueType", id = 4)
    private int zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 5)
    private String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getContainerMetadata", id = 6)
    private MediaQueueContainerMetadata zze;

    @SafeParcelable.Field(getter = "getRepeatMode", id = 7)
    private int zzf;

    @Nullable
    @SafeParcelable.Field(getter = "getItems", id = 8)
    private List zzg;

    @SafeParcelable.Field(getter = "getStartIndex", id = 9)
    private int zzh;

    @SafeParcelable.Field(getter = "getStartTime", id = 10)
    private long zzi;

    @SafeParcelable.Field(getter = "getShuffle", id = 11)
    private boolean zzj;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private final MediaQueueData zza;

        public Builder() {
            this.zza = new MediaQueueData(null);
        }

        @NonNull
        public MediaQueueData build() {
            return new MediaQueueData(this.zza, null);
        }

        @NonNull
        public Builder setContainerMetadata(@Nullable MediaQueueContainerMetadata mediaQueueContainerMetadata) {
            this.zza.zze = mediaQueueContainerMetadata;
            return this;
        }

        @NonNull
        public Builder setEntity(@Nullable String str) {
            this.zza.zzb = str;
            return this;
        }

        @NonNull
        public Builder setItems(@Nullable List<MediaQueueItem> list) {
            MediaQueueData.zze(this.zza, list);
            return this;
        }

        @NonNull
        public Builder setName(@Nullable String str) {
            this.zza.zzd = str;
            return this;
        }

        @NonNull
        public Builder setQueueId(@Nullable String str) {
            this.zza.zza = str;
            return this;
        }

        @NonNull
        public Builder setQueueType(int i) {
            this.zza.zzc = i;
            return this;
        }

        @NonNull
        public Builder setRepeatMode(int i) {
            this.zza.setRepeatMode(i);
            return this;
        }

        @NonNull
        public Builder setStartIndex(int i) {
            this.zza.zzh = i;
            return this;
        }

        @NonNull
        public Builder setStartTime(long j) {
            this.zza.zzi = j;
            return this;
        }

        @NonNull
        public final Builder zza(@NonNull JSONObject jSONObject) {
            MediaQueueData.zzb(this.zza, jSONObject);
            return this;
        }

        @KeepForSdk
        public Builder(@NonNull MediaQueueData mediaQueueData) {
            this.zza = new MediaQueueData(mediaQueueData, null);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueType {
    }

    private MediaQueueData() {
        zzl();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* bridge */ /* synthetic */ void zzb(com.google.android.gms.cast.MediaQueueData r11, org.json.JSONObject r12) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaQueueData.zzb(com.google.android.gms.cast.MediaQueueData, org.json.JSONObject):void");
    }

    static /* bridge */ /* synthetic */ void zze(MediaQueueData mediaQueueData, List list) {
        mediaQueueData.zzg = list == null ? null : new ArrayList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzl() {
        this.zza = null;
        this.zzb = null;
        this.zzc = 0;
        this.zzd = null;
        this.zzf = 0;
        this.zzg = null;
        this.zzh = 0;
        this.zzi = -1L;
        this.zzj = false;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueData)) {
            return false;
        }
        MediaQueueData mediaQueueData = (MediaQueueData) obj;
        return TextUtils.equals(this.zza, mediaQueueData.zza) && TextUtils.equals(this.zzb, mediaQueueData.zzb) && this.zzc == mediaQueueData.zzc && TextUtils.equals(this.zzd, mediaQueueData.zzd) && Objects.equal(this.zze, mediaQueueData.zze) && this.zzf == mediaQueueData.zzf && Objects.equal(this.zzg, mediaQueueData.zzg) && this.zzh == mediaQueueData.zzh && this.zzi == mediaQueueData.zzi && this.zzj == mediaQueueData.zzj;
    }

    @Nullable
    public MediaQueueContainerMetadata getContainerMetadata() {
        return this.zze;
    }

    @Nullable
    public String getEntity() {
        return this.zzb;
    }

    @Nullable
    public List<MediaQueueItem> getItems() {
        List list = this.zzg;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    @Nullable
    public String getName() {
        return this.zzd;
    }

    @Nullable
    public String getQueueId() {
        return this.zza;
    }

    public int getQueueType() {
        return this.zzc;
    }

    public int getRepeatMode() {
        return this.zzf;
    }

    public int getStartIndex() {
        return this.zzh;
    }

    public long getStartTime() {
        return this.zzi;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, Integer.valueOf(this.zzc), this.zzd, this.zze, Integer.valueOf(this.zzf), this.zzg, Integer.valueOf(this.zzh), Long.valueOf(this.zzi), Boolean.valueOf(this.zzj));
    }

    @KeepForSdk
    public void setRepeatMode(int i) {
        this.zzf = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getQueueId(), false);
        SafeParcelWriter.writeString(parcel, 3, getEntity(), false);
        SafeParcelWriter.writeInt(parcel, 4, getQueueType());
        SafeParcelWriter.writeString(parcel, 5, getName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getContainerMetadata(), i, false);
        SafeParcelWriter.writeInt(parcel, 7, getRepeatMode());
        SafeParcelWriter.writeTypedList(parcel, 8, getItems(), false);
        SafeParcelWriter.writeInt(parcel, 9, getStartIndex());
        SafeParcelWriter.writeLong(parcel, 10, getStartTime());
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.zza)) {
                jSONObject.put("id", this.zza);
            }
            if (!TextUtils.isEmpty(this.zzb)) {
                jSONObject.put("entity", this.zzb);
            }
            switch (this.zzc) {
                case 1:
                    jSONObject.put("queueType", "ALBUM");
                    break;
                case 2:
                    jSONObject.put("queueType", "PLAYLIST");
                    break;
                case 3:
                    jSONObject.put("queueType", "AUDIOBOOK");
                    break;
                case 4:
                    jSONObject.put("queueType", "RADIO_STATION");
                    break;
                case 5:
                    jSONObject.put("queueType", "PODCAST_SERIES");
                    break;
                case 6:
                    jSONObject.put("queueType", "TV_SERIES");
                    break;
                case 7:
                    jSONObject.put("queueType", "VIDEO_PLAYLIST");
                    break;
                case 8:
                    jSONObject.put("queueType", "LIVE_TV");
                    break;
                case 9:
                    jSONObject.put("queueType", "MOVIE");
                    break;
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                jSONObject.put("name", this.zzd);
            }
            MediaQueueContainerMetadata mediaQueueContainerMetadata = this.zze;
            if (mediaQueueContainerMetadata != null) {
                jSONObject.put("containerMetadata", mediaQueueContainerMetadata.zza());
            }
            String strZza = MediaCommon.zza(Integer.valueOf(this.zzf));
            if (strZza != null) {
                jSONObject.put("repeatMode", strZza);
            }
            List list = this.zzg;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzg.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaQueueItem) it.next()).toJson());
                }
                jSONObject.put(ContextMappingConstants.ITEMS, jSONArray);
            }
            jSONObject.put("startIndex", this.zzh);
            long j = this.zzi;
            if (j != -1) {
                jSONObject.put("startTime", CastUtils.millisecToSec(j));
            }
            jSONObject.put("shuffle", this.zzj);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @ShowFirstParty
    public final boolean zzk() {
        return this.zzj;
    }

    /* synthetic */ MediaQueueData(zzcf zzcfVar) {
        zzl();
    }

    /* synthetic */ MediaQueueData(MediaQueueData mediaQueueData, zzcf zzcfVar) {
        this.zza = mediaQueueData.zza;
        this.zzb = mediaQueueData.zzb;
        this.zzc = mediaQueueData.zzc;
        this.zzd = mediaQueueData.zzd;
        this.zze = mediaQueueData.zze;
        this.zzf = mediaQueueData.zzf;
        this.zzg = mediaQueueData.zzg;
        this.zzh = mediaQueueData.zzh;
        this.zzi = mediaQueueData.zzi;
        this.zzj = mediaQueueData.zzj;
    }

    @SafeParcelable.Constructor
    MediaQueueData(@Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) int i, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 6) MediaQueueContainerMetadata mediaQueueContainerMetadata, @SafeParcelable.Param(id = 7) int i2, @Nullable @SafeParcelable.Param(id = 8) List list, @SafeParcelable.Param(id = 9) int i3, @SafeParcelable.Param(id = 10) long j, @SafeParcelable.Param(id = 11) boolean z) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = i;
        this.zzd = str3;
        this.zze = mediaQueueContainerMetadata;
        this.zzf = i2;
        this.zzg = list;
        this.zzh = i3;
        this.zzi = j;
        this.zzj = z;
    }
}
