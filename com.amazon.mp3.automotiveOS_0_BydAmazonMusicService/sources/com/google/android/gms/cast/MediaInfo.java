package com.google.android.gms.cast;

import UpsellInterface.v1_0.PlaybackMode;
import android.os.Parcel;
import android.os.Parcelable;
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
import com.google.android.gms.internal.cast.zzeb;
import com.google.android.gms.internal.cast.zzee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "MediaInfoCreator")
@SafeParcelable.Reserved({1})
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    public static final long UNKNOWN_START_ABSOLUTE_TIME = -1;

    @Nullable
    @SafeParcelable.Field(id = 9)
    String zzb;

    @SafeParcelable.Field(getter = "getContentId", id = 2)
    private String zzc;

    @SafeParcelable.Field(getter = "getStreamType", id = 3)
    private int zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getContentType", id = 4)
    private String zze;

    @Nullable
    @SafeParcelable.Field(getter = "getMetadata", id = 5)
    private MediaMetadata zzf;

    @SafeParcelable.Field(getter = "getStreamDuration", id = 6)
    private long zzg;

    @Nullable
    @SafeParcelable.Field(getter = "getMediaTracks", id = 7)
    private List zzh;

    @Nullable
    @SafeParcelable.Field(getter = "getTextTrackStyle", id = 8)
    private TextTrackStyle zzi;

    @Nullable
    @SafeParcelable.Field(getter = "getAdBreaks", id = 10)
    private List zzj;

    @Nullable
    @SafeParcelable.Field(getter = "getAdBreakClips", id = 11)
    private List zzk;

    @Nullable
    @SafeParcelable.Field(getter = "getEntity", id = 12)
    private String zzl;

    @Nullable
    @SafeParcelable.Field(getter = "getVmapAdsRequest", id = 13)
    private VastAdsRequest zzm;

    @SafeParcelable.Field(getter = "getStartAbsoluteTime", id = 14)
    private long zzn;

    @Nullable
    @SafeParcelable.Field(getter = "getAtvEntity", id = 15)
    private String zzo;

    @Nullable
    @SafeParcelable.Field(getter = "getContentUrl", id = 16)
    private String zzp;

    @Nullable
    @HlsSegmentFormat
    @SafeParcelable.Field(getter = "getHlsSegmentFormat", id = 17)
    private String zzq;

    @Nullable
    @HlsVideoSegmentFormat
    @SafeParcelable.Field(getter = "getHlsVideoSegmentFormat", id = 18)
    private String zzr;

    @Nullable
    private JSONObject zzs;
    private final Writer zzt;
    public static final long zza = CastUtils.secToMillisec(-1L);

    @NonNull
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzbw();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private final MediaInfo zza;

        public Builder(@NonNull String str) throws IllegalArgumentException {
            this.zza = new MediaInfo(str);
        }

        @NonNull
        public MediaInfo build() {
            return this.zza;
        }

        @NonNull
        public Builder setAdBreakClips(@NonNull List<AdBreakClipInfo> list) {
            this.zza.getWriter().setAdBreakClips(list);
            return this;
        }

        @NonNull
        public Builder setAdBreaks(@NonNull List<AdBreakInfo> list) {
            this.zza.getWriter().setAdBreaks(list);
            return this;
        }

        @NonNull
        public Builder setAtvEntity(@NonNull String str) {
            this.zza.zzo = str;
            return this;
        }

        @NonNull
        public Builder setContentType(@NonNull String str) {
            this.zza.getWriter().setContentType(str);
            return this;
        }

        @NonNull
        public Builder setContentUrl(@NonNull String str) {
            this.zza.getWriter().setContentUrl(str);
            return this;
        }

        @NonNull
        public Builder setCustomData(@NonNull JSONObject jSONObject) {
            this.zza.getWriter().setCustomData(jSONObject);
            return this;
        }

        @NonNull
        public Builder setEntity(@NonNull String str) {
            this.zza.getWriter().setEntity(str);
            return this;
        }

        @NonNull
        public Builder setHlsSegmentFormat(@Nullable @HlsSegmentFormat String str) {
            this.zza.getWriter().setHlsSegmentFormat(str);
            return this;
        }

        @NonNull
        public Builder setHlsVideoSegmentFormat(@Nullable @HlsVideoSegmentFormat String str) {
            this.zza.getWriter().setHlsVideoSegmentFormat(str);
            return this;
        }

        @NonNull
        public Builder setMediaTracks(@NonNull List<MediaTrack> list) {
            this.zza.getWriter().setMediaTracks(list);
            return this;
        }

        @NonNull
        public Builder setMetadata(@NonNull MediaMetadata mediaMetadata) {
            this.zza.getWriter().setMetadata(mediaMetadata);
            return this;
        }

        @NonNull
        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zza.getWriter().setStreamDuration(j);
            return this;
        }

        @NonNull
        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zza.getWriter().setStreamType(i);
            return this;
        }

        @NonNull
        public Builder setTextTrackStyle(@NonNull TextTrackStyle textTrackStyle) {
            this.zza.getWriter().setTextTrackStyle(textTrackStyle);
            return this;
        }

        @NonNull
        public Builder setVmapAdsRequest(@NonNull VastAdsRequest vastAdsRequest) {
            this.zza.getWriter().setVmapAdsRequest(vastAdsRequest);
            return this;
        }

        public Builder(@NonNull String str, @NonNull String str2) throws IllegalArgumentException {
            this.zza = new MediaInfo(str, str2);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @KeepForSdk
    public class Writer {
        public Writer() {
        }

        @KeepForSdk
        public void setAdBreakClips(@Nullable List<AdBreakClipInfo> list) {
            MediaInfo.this.zzk = list;
        }

        @KeepForSdk
        public void setAdBreaks(@Nullable List<AdBreakInfo> list) {
            MediaInfo.this.zzj = list;
        }

        @KeepForSdk
        public void setContentId(@NonNull String str) {
            MediaInfo.this.zzc = str;
        }

        @KeepForSdk
        public void setContentType(@Nullable String str) {
            MediaInfo.this.zze = str;
        }

        @KeepForSdk
        public void setContentUrl(@Nullable String str) {
            MediaInfo.this.zzp = str;
        }

        @KeepForSdk
        public void setCustomData(@Nullable JSONObject jSONObject) {
            MediaInfo.this.zzs = jSONObject;
        }

        @KeepForSdk
        public void setEntity(@Nullable String str) {
            MediaInfo.this.zzl = str;
        }

        @KeepForSdk
        public void setHlsSegmentFormat(@Nullable @HlsSegmentFormat String str) {
            MediaInfo.this.zzq = str;
        }

        @KeepForSdk
        public void setHlsVideoSegmentFormat(@Nullable @HlsVideoSegmentFormat String str) {
            MediaInfo.this.zzr = str;
        }

        @KeepForSdk
        public void setMediaTracks(@Nullable List<MediaTrack> list) {
            MediaInfo.this.zzh = list;
        }

        @KeepForSdk
        public void setMetadata(@Nullable MediaMetadata mediaMetadata) {
            MediaInfo.this.zzf = mediaMetadata;
        }

        @KeepForSdk
        public void setStreamType(int i) {
            if (i < -1 || i > 2) {
                throw new IllegalArgumentException("invalid stream type");
            }
            MediaInfo.this.zzd = i;
        }

        @KeepForSdk
        public void setTextTrackStyle(@Nullable TextTrackStyle textTrackStyle) {
            MediaInfo.this.zzi = textTrackStyle;
        }

        @KeepForSdk
        public void setVmapAdsRequest(@Nullable VastAdsRequest vastAdsRequest) {
            MediaInfo.this.zzm = vastAdsRequest;
        }

        @KeepForSdk
        public void setStartAbsoluteTime(long j) {
            if (j < 0 && j != -1) {
                throw new IllegalArgumentException("Invalid start absolute time");
            }
            MediaInfo.this.zzn = j;
        }

        @KeepForSdk
        public void setStreamDuration(long j) {
            if (j < 0 && j != -1) {
                throw new IllegalArgumentException("Invalid stream duration");
            }
            MediaInfo.this.zzg = j;
        }
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, null, null, -1L, null, null, null, null, null, null, null, -1L, null, null, null, null);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        JSONObject jSONObject = this.zzs;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaInfo.zzs;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && CastUtils.zzh(this.zzc, mediaInfo.zzc) && this.zzd == mediaInfo.zzd && CastUtils.zzh(this.zze, mediaInfo.zze) && CastUtils.zzh(this.zzf, mediaInfo.zzf) && this.zzg == mediaInfo.zzg && CastUtils.zzh(this.zzh, mediaInfo.zzh) && CastUtils.zzh(this.zzi, mediaInfo.zzi) && CastUtils.zzh(this.zzj, mediaInfo.zzj) && CastUtils.zzh(this.zzk, mediaInfo.zzk) && CastUtils.zzh(this.zzl, mediaInfo.zzl) && CastUtils.zzh(this.zzm, mediaInfo.zzm) && this.zzn == mediaInfo.zzn && CastUtils.zzh(this.zzo, mediaInfo.zzo) && CastUtils.zzh(this.zzp, mediaInfo.zzp) && CastUtils.zzh(this.zzq, mediaInfo.zzq) && CastUtils.zzh(this.zzr, mediaInfo.zzr);
    }

    @Nullable
    public List<AdBreakClipInfo> getAdBreakClips() {
        List list = this.zzk;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    @Nullable
    public List<AdBreakInfo> getAdBreaks() {
        List list = this.zzj;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    @NonNull
    public String getContentId() {
        return this.zzc;
    }

    @Nullable
    public String getContentType() {
        return this.zze;
    }

    @Nullable
    public String getContentUrl() {
        return this.zzp;
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzs;
    }

    @Nullable
    public String getEntity() {
        return this.zzl;
    }

    @Nullable
    @HlsSegmentFormat
    public String getHlsSegmentFormat() {
        return this.zzq;
    }

    @Nullable
    @HlsVideoSegmentFormat
    public String getHlsVideoSegmentFormat() {
        return this.zzr;
    }

    @Nullable
    public List<MediaTrack> getMediaTracks() {
        return this.zzh;
    }

    @Nullable
    public MediaMetadata getMetadata() {
        return this.zzf;
    }

    public long getStartAbsoluteTime() {
        return this.zzn;
    }

    public long getStreamDuration() {
        return this.zzg;
    }

    public int getStreamType() {
        return this.zzd;
    }

    @Nullable
    public TextTrackStyle getTextTrackStyle() {
        return this.zzi;
    }

    @Nullable
    public VastAdsRequest getVmapAdsRequest() {
        return this.zzm;
    }

    @NonNull
    @KeepForSdk
    public Writer getWriter() {
        return this.zzt;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzc, Integer.valueOf(this.zzd), this.zze, this.zzf, Long.valueOf(this.zzg), String.valueOf(this.zzs), this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn), this.zzo, this.zzq, this.zzr);
    }

    public void setTextTrackStyle(@NonNull TextTrackStyle textTrackStyle) {
        this.zzi = textTrackStyle;
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzc);
            jSONObject.putOpt("contentUrl", this.zzp);
            int i = this.zzd;
            jSONObject.put("streamType", i != 1 ? i != 2 ? PlaybackMode.NONE : "LIVE" : "BUFFERED");
            String str = this.zze;
            if (str != null) {
                jSONObject.put("contentType", str);
            }
            MediaMetadata mediaMetadata = this.zzf;
            if (mediaMetadata != null) {
                jSONObject.put("metadata", mediaMetadata.zza());
            }
            long j = this.zzg;
            if (j <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", CastUtils.millisecToSec(j));
            }
            if (this.zzh != null) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzh.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaTrack) it.next()).zza());
                }
                jSONObject.put("tracks", jSONArray);
            }
            TextTrackStyle textTrackStyle = this.zzi;
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.zza());
            }
            JSONObject jSONObject2 = this.zzs;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str2 = this.zzl;
            if (str2 != null) {
                jSONObject.put("entity", str2);
            }
            if (this.zzj != null) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it2 = this.zzj.iterator();
                while (it2.hasNext()) {
                    jSONArray2.put(((AdBreakInfo) it2.next()).zza());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzk != null) {
                JSONArray jSONArray3 = new JSONArray();
                Iterator it3 = this.zzk.iterator();
                while (it3.hasNext()) {
                    jSONArray3.put(((AdBreakClipInfo) it3.next()).zza());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
            VastAdsRequest vastAdsRequest = this.zzm;
            if (vastAdsRequest != null) {
                jSONObject.put("vmapAdsRequest", vastAdsRequest.zza());
            }
            long j2 = this.zzn;
            if (j2 != -1) {
                jSONObject.put("startAbsoluteTime", CastUtils.millisecToSec(j2));
            }
            jSONObject.putOpt("atvEntity", this.zzo);
            String str3 = this.zzq;
            if (str3 != null) {
                jSONObject.put("hlsSegmentFormat", str3);
            }
            String str4 = this.zzr;
            if (str4 != null) {
                jSONObject.put("hlsVideoSegmentFormat", str4);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00aa A[LOOP:0: B:5:0x0022->B:26:0x00aa, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0187 A[LOOP:2: B:32:0x00d1->B:59:0x0187, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x018e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void zzs(org.json.JSONObject r40) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaInfo.zzs(org.json.JSONObject):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        JSONObject jSONObject = this.zzs;
        this.zzb = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getContentId(), false);
        SafeParcelWriter.writeInt(parcel, 3, getStreamType());
        SafeParcelWriter.writeString(parcel, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getMetadata(), i, false);
        SafeParcelWriter.writeLong(parcel, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(parcel, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getTextTrackStyle(), i, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(parcel, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(parcel, 12, getEntity(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVmapAdsRequest(), i, false);
        SafeParcelWriter.writeLong(parcel, 14, getStartAbsoluteTime());
        SafeParcelWriter.writeString(parcel, 15, this.zzo, false);
        SafeParcelWriter.writeString(parcel, 16, getContentUrl(), false);
        SafeParcelWriter.writeString(parcel, 17, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeString(parcel, 18, getHlsVideoSegmentFormat(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    MediaInfo(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @Nullable @SafeParcelable.Param(id = 4) String str2, @Nullable @SafeParcelable.Param(id = 5) MediaMetadata mediaMetadata, @SafeParcelable.Param(id = 6) long j, @Nullable @SafeParcelable.Param(id = 7) List list, @Nullable @SafeParcelable.Param(id = 8) TextTrackStyle textTrackStyle, @Nullable @SafeParcelable.Param(id = 9) String str3, @Nullable @SafeParcelable.Param(id = 10) List list2, @Nullable @SafeParcelable.Param(id = 11) List list3, @Nullable @SafeParcelable.Param(id = 12) String str4, @Nullable @SafeParcelable.Param(id = 13) VastAdsRequest vastAdsRequest, @SafeParcelable.Param(id = 14) long j2, @Nullable @SafeParcelable.Param(id = 15) String str5, @Nullable @SafeParcelable.Param(id = 16) String str6, @Nullable @HlsSegmentFormat @SafeParcelable.Param(id = 17) String str7, @Nullable @SafeParcelable.Param(id = 18) @HlsVideoSegmentFormat String str8) {
        this.zzt = new Writer();
        this.zzc = str;
        this.zzd = i;
        this.zze = str2;
        this.zzf = mediaMetadata;
        this.zzg = j;
        this.zzh = list;
        this.zzi = textTrackStyle;
        this.zzb = str3;
        if (str3 != null) {
            try {
                this.zzs = new JSONObject(this.zzb);
            } catch (JSONException unused) {
                this.zzs = null;
                this.zzb = null;
            }
        } else {
            this.zzs = null;
        }
        this.zzj = list2;
        this.zzk = list3;
        this.zzl = str4;
        this.zzm = vastAdsRequest;
        this.zzn = j2;
        this.zzo = str5;
        this.zzp = str6;
        this.zzq = str7;
        this.zzr = str8;
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, null, null, -1L, null, null, null, null, null, str2, null, -1L, null, null, null, null);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        MediaInfo mediaInfo;
        int i;
        int i2;
        zzee zzeeVarZzd;
        int i3;
        this(jSONObject.optString("contentId"), -1, null, null, -1L, null, null, null, null, null, null, null, -1L, null, null, null, null);
        String strOptString = jSONObject.optString("streamType", PlaybackMode.NONE);
        int i4 = 2;
        int i5 = 1;
        if (PlaybackMode.NONE.equals(strOptString)) {
            mediaInfo = this;
            mediaInfo.zzd = 0;
        } else {
            mediaInfo = this;
            if ("BUFFERED".equals(strOptString)) {
                mediaInfo.zzd = 1;
            } else if ("LIVE".equals(strOptString)) {
                mediaInfo.zzd = 2;
            } else {
                mediaInfo.zzd = -1;
            }
        }
        mediaInfo.zze = CastUtils.optStringOrNull(jSONObject, "contentType");
        if (jSONObject.has("metadata")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            mediaInfo.zzf = mediaMetadata;
            mediaMetadata.zzc(jSONObject2);
        }
        mediaInfo.zzg = -1L;
        if (mediaInfo.zzd != 2 && jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double dOptDouble = jSONObject.optDouble("duration", 0.0d);
            if (!Double.isNaN(dOptDouble) && !Double.isInfinite(dOptDouble) && dOptDouble >= 0.0d) {
                mediaInfo.zzg = CastUtils.secToMillisec(dOptDouble);
            }
        }
        if (jSONObject.has("tracks")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            int i6 = 0;
            while (i6 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i6);
                String str = MediaTrack.ROLE_ALTERNATE;
                long j = jSONObject3.getLong("trackId");
                String strOptString2 = jSONObject3.optString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                if ("TEXT".equals(strOptString2)) {
                    i = i5;
                } else if ("AUDIO".equals(strOptString2)) {
                    i = i4;
                } else {
                    i = "VIDEO".equals(strOptString2) ? 3 : 0;
                }
                String strOptStringOrNull = CastUtils.optStringOrNull(jSONObject3, "trackContentId");
                String strOptStringOrNull2 = CastUtils.optStringOrNull(jSONObject3, "trackContentType");
                String strOptStringOrNull3 = CastUtils.optStringOrNull(jSONObject3, "name");
                String strOptStringOrNull4 = CastUtils.optStringOrNull(jSONObject3, "language");
                if (jSONObject3.has("subtype")) {
                    String string = jSONObject3.getString("subtype");
                    if ("SUBTITLES".equals(string)) {
                        i2 = i5;
                    } else if ("CAPTIONS".equals(string)) {
                        i2 = i4;
                    } else if ("DESCRIPTIONS".equals(string)) {
                        i2 = 3;
                    } else {
                        if ("CHAPTERS".equals(string)) {
                            i3 = 4;
                        } else if ("METADATA".equals(string)) {
                            i3 = 5;
                        } else {
                            i2 = -1;
                        }
                        i2 = i3;
                    }
                } else {
                    i2 = 0;
                }
                if (jSONObject3.has("roles")) {
                    zzeb zzebVarZzi = zzee.zzi();
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("roles");
                    for (int i7 = 0; i7 < jSONArray2.length(); i7++) {
                        zzebVarZzi.zzc(jSONArray2.optString(i7));
                    }
                    zzeeVarZzd = zzebVarZzi.zzd();
                } else {
                    zzeeVarZzd = null;
                }
                arrayList.add(new MediaTrack(j, i, strOptStringOrNull, strOptStringOrNull2, strOptStringOrNull3, strOptStringOrNull4, i2, zzeeVarZzd, jSONObject3.optJSONObject("customData")));
                i6++;
                i4 = 2;
                i5 = 1;
            }
            mediaInfo.zzh = new ArrayList(arrayList);
        } else {
            mediaInfo.zzh = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject4 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.fromJson(jSONObject4);
            mediaInfo.zzi = textTrackStyle;
        } else {
            mediaInfo.zzi = null;
        }
        zzs(jSONObject);
        mediaInfo.zzs = jSONObject.optJSONObject("customData");
        mediaInfo.zzl = CastUtils.optStringOrNull(jSONObject, "entity");
        mediaInfo.zzo = CastUtils.optStringOrNull(jSONObject, "atvEntity");
        mediaInfo.zzm = VastAdsRequest.fromJson(jSONObject.optJSONObject("vmapAdsRequest"));
        if (jSONObject.has("startAbsoluteTime") && !jSONObject.isNull("startAbsoluteTime")) {
            double dOptDouble2 = jSONObject.optDouble("startAbsoluteTime");
            if (!Double.isNaN(dOptDouble2) && !Double.isInfinite(dOptDouble2) && dOptDouble2 >= 0.0d) {
                mediaInfo.zzn = CastUtils.secToMillisec(dOptDouble2);
            }
        }
        if (jSONObject.has("contentUrl")) {
            mediaInfo.zzp = jSONObject.optString("contentUrl");
        }
        mediaInfo.zzq = CastUtils.optStringOrNull(jSONObject, "hlsSegmentFormat");
        mediaInfo.zzr = CastUtils.optStringOrNull(jSONObject, "hlsVideoSegmentFormat");
    }
}
