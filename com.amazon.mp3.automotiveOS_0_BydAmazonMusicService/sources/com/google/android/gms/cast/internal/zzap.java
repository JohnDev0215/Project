package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLiveSeekableRange;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public final class zzap extends zzd {
    public static final String zzb = CastUtils.zzc("com.google.cast.media");
    private int zzA;
    private TaskCompletionSource zzB;

    @VisibleForTesting
    final zzat zzc;

    @VisibleForTesting
    final zzat zzd;

    @VisibleForTesting
    final zzat zze;

    @VisibleForTesting
    final zzat zzf;

    @VisibleForTesting
    final zzat zzg;

    @VisibleForTesting
    final zzat zzh;

    @VisibleForTesting
    final zzat zzi;

    @VisibleForTesting
    final zzat zzj;

    @VisibleForTesting
    final zzat zzk;

    @VisibleForTesting
    final zzat zzl;

    @VisibleForTesting
    final zzat zzm;

    @VisibleForTesting
    final zzat zzn;

    @VisibleForTesting
    final zzat zzo;

    @VisibleForTesting
    final zzat zzp;

    @VisibleForTesting
    final zzat zzq;

    @VisibleForTesting
    final zzat zzr;

    @VisibleForTesting
    final zzat zzs;

    @VisibleForTesting
    final zzat zzt;

    @VisibleForTesting
    final zzat zzu;

    @VisibleForTesting
    final zzat zzv;
    private long zzw;

    @Nullable
    private MediaStatus zzx;

    @Nullable
    private Long zzy;
    private zzam zzz;

    public zzap(@Nullable String str) {
        super(zzb, "MediaControlChannel", null);
        this.zzA = -1;
        zzat zzatVar = new zzat(86400000L);
        this.zzc = zzatVar;
        zzat zzatVar2 = new zzat(86400000L);
        this.zzd = zzatVar2;
        zzat zzatVar3 = new zzat(86400000L);
        this.zze = zzatVar3;
        zzat zzatVar4 = new zzat(86400000L);
        this.zzf = zzatVar4;
        zzat zzatVar5 = new zzat(10000L);
        this.zzg = zzatVar5;
        zzat zzatVar6 = new zzat(86400000L);
        this.zzh = zzatVar6;
        zzat zzatVar7 = new zzat(86400000L);
        this.zzi = zzatVar7;
        zzat zzatVar8 = new zzat(86400000L);
        this.zzj = zzatVar8;
        zzat zzatVar9 = new zzat(86400000L);
        this.zzk = zzatVar9;
        zzat zzatVar10 = new zzat(86400000L);
        this.zzl = zzatVar10;
        zzat zzatVar11 = new zzat(86400000L);
        this.zzm = zzatVar11;
        zzat zzatVar12 = new zzat(86400000L);
        this.zzn = zzatVar12;
        zzat zzatVar13 = new zzat(86400000L);
        this.zzo = zzatVar13;
        zzat zzatVar14 = new zzat(86400000L);
        this.zzp = zzatVar14;
        zzat zzatVar15 = new zzat(86400000L);
        this.zzq = zzatVar15;
        zzat zzatVar16 = new zzat(86400000L);
        this.zzs = zzatVar16;
        this.zzr = new zzat(86400000L);
        zzat zzatVar17 = new zzat(86400000L);
        this.zzt = zzatVar17;
        zzat zzatVar18 = new zzat(86400000L);
        this.zzu = zzatVar18;
        zzat zzatVar19 = new zzat(86400000L);
        this.zzv = zzatVar19;
        zzc(zzatVar);
        zzc(zzatVar2);
        zzc(zzatVar3);
        zzc(zzatVar4);
        zzc(zzatVar5);
        zzc(zzatVar6);
        zzc(zzatVar7);
        zzc(zzatVar8);
        zzc(zzatVar9);
        zzc(zzatVar10);
        zzc(zzatVar11);
        zzc(zzatVar12);
        zzc(zzatVar13);
        zzc(zzatVar14);
        zzc(zzatVar15);
        zzc(zzatVar16);
        zzc(zzatVar16);
        zzc(zzatVar17);
        zzc(zzatVar18);
        zzc(zzatVar19);
        zzV();
    }

    private final long zzT(double d, long j, long j2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.zzw;
        if (jElapsedRealtime < 0) {
            jElapsedRealtime = 0;
        }
        if (jElapsedRealtime == 0) {
            return j;
        }
        long j3 = j + ((long) (jElapsedRealtime * d));
        if (j2 > 0 && j3 > j2) {
            return j2;
        }
        if (j3 >= 0) {
            return j3;
        }
        return 0L;
    }

    private static zzao zzU(JSONObject jSONObject) {
        MediaError mediaErrorZza = MediaError.zza(jSONObject);
        zzao zzaoVar = new zzao();
        zzaoVar.zza = CastUtils.zzg(jSONObject, "customData");
        zzaoVar.zzb = mediaErrorZza;
        return zzaoVar;
    }

    private final void zzV() {
        this.zzw = 0L;
        this.zzx = null;
        Iterator it = zza().iterator();
        while (it.hasNext()) {
            ((zzat) it.next()).zzc(CastStatusCodes.CANCELED);
        }
    }

    private final void zzW(JSONObject jSONObject, String str) {
        if (jSONObject.has("sequenceNumber")) {
            this.zzA = jSONObject.optInt("sequenceNumber", -1);
        } else {
            this.zza.w(str.concat(" message is missing a sequence number."), new Object[0]);
        }
    }

    private final void zzX() {
        zzam zzamVar = this.zzz;
        if (zzamVar != null) {
            zzamVar.zzc();
        }
    }

    private final void zzY() {
        zzam zzamVar = this.zzz;
        if (zzamVar != null) {
            zzamVar.zzd();
        }
    }

    private final void zzZ() {
        zzam zzamVar = this.zzz;
        if (zzamVar != null) {
            zzamVar.zzk();
        }
    }

    private final void zzaa() {
        zzam zzamVar = this.zzz;
        if (zzamVar != null) {
            zzamVar.zzm();
        }
    }

    private final boolean zzab() {
        return this.zzA != -1;
    }

    @Nullable
    private static int[] zzac(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    public final long zzB(zzar zzarVar) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "GET_STATUS");
            MediaStatus mediaStatus = this.zzx;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.zzb());
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzj.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzC(zzar zzarVar, MediaSeekOptions mediaSeekOptions) throws zzan, IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        long position = mediaSeekOptions.isSeekToInfinite() ? 4294967296000L : mediaSeekOptions.getPosition();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "SEEK");
            jSONObject.put("mediaSessionId", zzn());
            jSONObject.put("currentTime", CastUtils.millisecToSec(position));
            if (mediaSeekOptions.getResumeState() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (mediaSeekOptions.getResumeState() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (mediaSeekOptions.getCustomData() != null) {
                jSONObject.put("customData", mediaSeekOptions.getCustomData());
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzy = Long.valueOf(position);
        this.zzg.zzb(jZzd, new zzaj(this, zzarVar));
        return jZzd;
    }

    public final long zzE(zzar zzarVar, double d, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException {
        if (this.zzx == null) {
            throw new zzan();
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d);
            Preconditions.checkNotNull(this.zzx, "mediaStatus should not be null");
            jSONObject2.put("mediaSessionId", this.zzx.zzb());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzt.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzF(zzar zzarVar, boolean z, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzn());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzi.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzG(zzar zzarVar, double d, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzn());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzh.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzI(zzar zzarVar) throws zzan, IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "SKIP_AD");
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException e) {
            this.zza.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", e.getMessage()), new Object[0]);
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzu.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzJ(zzar zzarVar, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "STOP");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzf.zzb(jZzd, zzarVar);
        return jZzd;
    }

    @Nullable
    public final MediaInfo zzK() {
        MediaStatus mediaStatus = this.zzx;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getMediaInfo();
    }

    @Nullable
    public final MediaStatus zzL() {
        return this.zzx;
    }

    public final Task zzN(@Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "STORE_SESSION");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("assistant_supported", true);
            jSONObject3.put("display_supported", true);
            jSONObject3.put("is_group", false);
            jSONObject2.put("targetDeviceCapabilities", jSONObject3);
        } catch (JSONException e) {
            this.zza.w(e, "store session failed to create JSON message", new Object[0]);
        }
        try {
            zzg(jSONObject2.toString(), jZzd, null);
            this.zzv.zzb(jZzd, new zzak(this));
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.zzB = taskCompletionSource;
            return taskCompletionSource.getTask();
        } catch (IllegalStateException e2) {
            return Tasks.forException(e2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0283 A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x028d A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02a0 A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02b0 A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02bd A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02c7 A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02ce A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02d5 A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02dc A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02ed A[Catch: JSONException -> 0x031e, TryCatch #0 {JSONException -> 0x031e, blocks: (B:3:0x0014, B:41:0x00a0, B:43:0x00a9, B:45:0x00b1, B:47:0x00bb, B:49:0x00c7, B:50:0x00d4, B:52:0x00da, B:53:0x00ec, B:55:0x00f2, B:57:0x00fe, B:59:0x0112, B:80:0x014d, B:82:0x0162, B:84:0x017e, B:86:0x0184, B:88:0x018a, B:90:0x0190, B:92:0x0196, B:94:0x01a2, B:96:0x01ac, B:98:0x01b2, B:99:0x01ba, B:101:0x01c0, B:102:0x01d0, B:105:0x01d6, B:107:0x01e0, B:108:0x01f1, B:110:0x01f7, B:111:0x0207, B:113:0x0213, B:115:0x021f, B:116:0x0230, B:118:0x0236, B:119:0x0246, B:121:0x0252, B:123:0x0264, B:134:0x0283, B:137:0x0288, B:139:0x029c, B:141:0x02a0, B:142:0x02ac, B:144:0x02b0, B:145:0x02b9, B:147:0x02bd, B:148:0x02c3, B:150:0x02c7, B:151:0x02ca, B:153:0x02ce, B:154:0x02d1, B:156:0x02d5, B:157:0x02d8, B:159:0x02dc, B:161:0x02e6, B:162:0x02e9, B:164:0x02ed, B:166:0x0305, B:167:0x030d, B:169:0x0313, B:138:0x028d, B:127:0x026f, B:129:0x0277, B:165:0x02f7), top: B:175:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzQ(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 902
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.internal.zzap.zzQ(java.lang.String):void");
    }

    public final void zzR(long j, int i) {
        Iterator it = zza().iterator();
        while (it.hasNext()) {
            ((zzat) it.next()).zzd(j, i, null);
        }
    }

    public final void zzS(zzam zzamVar) {
        this.zzz = zzamVar;
    }

    @Override // com.google.android.gms.cast.internal.zzp
    public final void zzf() {
        zzb();
        zzV();
    }

    public final long zzj() {
        MediaStatus mediaStatus;
        AdBreakStatus adBreakStatus;
        if (this.zzw == 0 || (mediaStatus = this.zzx) == null || (adBreakStatus = mediaStatus.getAdBreakStatus()) == null) {
            return 0L;
        }
        double playbackRate = mediaStatus.getPlaybackRate();
        if (playbackRate == 0.0d) {
            playbackRate = 1.0d;
        }
        return zzT(mediaStatus.getPlayerState() != 2 ? 0.0d : playbackRate, adBreakStatus.getCurrentBreakClipTimeInMs(), 0L);
    }

    public final long zzk() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzx;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long endTime = liveSeekableRange.getEndTime();
        return !liveSeekableRange.isLiveDone() ? zzT(1.0d, endTime, -1L) : endTime;
    }

    public final long zzl() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzx;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long startTime = liveSeekableRange.getStartTime();
        if (liveSeekableRange.isMovingWindow()) {
            startTime = zzT(1.0d, startTime, -1L);
        }
        return liveSeekableRange.isLiveDone() ? Math.min(startTime, liveSeekableRange.getEndTime()) : startTime;
    }

    public final long zzm() {
        MediaStatus mediaStatus;
        MediaInfo mediaInfoZzK = zzK();
        if (mediaInfoZzK == null || (mediaStatus = this.zzx) == null) {
            return 0L;
        }
        Long l = this.zzy;
        if (l == null) {
            if (this.zzw == 0) {
                return 0L;
            }
            double playbackRate = mediaStatus.getPlaybackRate();
            long streamPosition = mediaStatus.getStreamPosition();
            return (playbackRate == 0.0d || mediaStatus.getPlayerState() != 2) ? streamPosition : zzT(playbackRate, streamPosition, mediaInfoZzK.getStreamDuration());
        }
        if (l.equals(4294967296000L)) {
            if (this.zzx.getLiveSeekableRange() != null) {
                return Math.min(l.longValue(), zzk());
            }
            if (zzo() >= 0) {
                return Math.min(l.longValue(), zzo());
            }
        }
        return l.longValue();
    }

    public final long zzn() throws zzan {
        MediaStatus mediaStatus = this.zzx;
        if (mediaStatus != null) {
            return mediaStatus.zzb();
        }
        throw new zzan();
    }

    public final long zzo() {
        MediaInfo mediaInfoZzK = zzK();
        if (mediaInfoZzK != null) {
            return mediaInfoZzK.getStreamDuration();
        }
        return 0L;
    }

    public final long zzp(zzar zzarVar, MediaLoadRequestData mediaLoadRequestData) throws IllegalStateException, IllegalArgumentException {
        if (mediaLoadRequestData.getMediaInfo() == null && mediaLoadRequestData.getQueueData() == null) {
            throw new IllegalArgumentException("MediaInfo and MediaQueueData should not be both null");
        }
        JSONObject json = mediaLoadRequestData.toJson();
        if (json == null) {
            throw new IllegalArgumentException("Failed to jsonify the load request due to malformed request");
        }
        long jZzd = zzd();
        try {
            json.put(TetheredMessageKey.requestId, jZzd);
            json.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "LOAD");
        } catch (JSONException unused) {
        }
        zzg(json.toString(), jZzd, null);
        this.zzc.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzq(zzar zzarVar, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "PAUSE");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzd.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzr(zzar zzarVar, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException {
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "PLAY");
            jSONObject2.put("mediaSessionId", zzn());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zze.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzs(@Nullable String str, @Nullable List list) throws IllegalStateException {
        long jZzd = zzd();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "PRECACHE");
            jSONObject.put("precacheData", str);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        return jZzd;
    }

    public final long zzt(zzar zzarVar, int i, int i2, int i3) throws zzan, IllegalArgumentException {
        if (i2 > 0 && i3 == 0) {
            i3 = 0;
        } else if (i2 != 0 || i3 <= 0) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzn());
            jSONObject.put("itemId", i);
            if (i2 > 0) {
                jSONObject.put("nextCount", i2);
            }
            if (i3 > 0) {
                jSONObject.put("prevCount", i3);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzs.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzu(zzar zzarVar) throws zzan, IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzq.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzv(zzar zzarVar, int[] iArr) throws zzan, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i : iArr) {
                jSONArray.put(i);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzr.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzD(zzar zzarVar, long[] jArr) throws zzan, IllegalStateException {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzk.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzH(zzar zzarVar, TextTrackStyle textTrackStyle) throws zzan, IllegalStateException {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject.put(TetheredMessageKey.requestId, jZzd);
            jSONObject.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "EDIT_TRACKS_INFO");
            jSONObject.put("textTrackStyle", textTrackStyle.zza());
            jSONObject.put("mediaSessionId", zzn());
        } catch (JSONException unused) {
        }
        zzg(jSONObject.toString(), jZzd, null);
        this.zzl.zzb(jZzd, zzarVar);
        return jZzd;
    }

    public final long zzy(zzar zzarVar, int[] iArr, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzab()) {
                jSONObject2.put("sequenceNumber", this.zzA);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzo.zzb(jZzd, new zzal(this, zzarVar));
        return jZzd;
    }

    public final long zzz(zzar zzarVar, int[] iArr, int i, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzab()) {
                jSONObject2.put("sequenceNumber", this.zzA);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzp.zzb(jZzd, new zzal(this, zzarVar));
        return jZzd;
    }

    public final long zzw(zzar zzarVar, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        }
        if (j != -1 && j < 0) {
            StringBuilder sb = new StringBuilder(54);
            sb.append("playPosition can not be negative: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_INSERT");
            jSONObject2.put("mediaSessionId", zzn());
            JSONArray jSONArray = new JSONArray();
            for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
            }
            jSONObject2.put(ContextMappingConstants.ITEMS, jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (i3 != -1) {
                jSONObject2.put("currentItemIndex", 0);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzab()) {
                jSONObject2.put("sequenceNumber", this.zzA);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzm.zzb(jZzd, new zzal(this, zzarVar));
        return jZzd;
    }

    public final long zzA(zzar zzarVar, int i, long j, @Nullable MediaQueueItem[] mediaQueueItemArr, int i2, @Nullable Boolean bool, @Nullable Integer num, @Nullable JSONObject jSONObject) throws zzan, IllegalStateException, IllegalArgumentException {
        if (j != -1 && j < 0) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("playPosition cannot be negative: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_UPDATE");
            jSONObject2.put("mediaSessionId", zzn());
            if (i != 0) {
                jSONObject2.put("currentItemId", i);
            }
            if (i2 != 0) {
                jSONObject2.put("jump", i2);
            }
            if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                    jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
                }
                jSONObject2.put(ContextMappingConstants.ITEMS, jSONArray);
            }
            if (bool != null) {
                jSONObject2.put("shuffle", bool);
            }
            String strZza = MediaCommon.zza(num);
            if (strZza != null) {
                jSONObject2.put("repeatMode", strZza);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzab()) {
                jSONObject2.put("sequenceNumber", this.zzA);
            }
        } catch (JSONException unused) {
        }
        zzg(jSONObject2.toString(), jZzd, null);
        this.zzn.zzb(jZzd, new zzal(this, zzarVar));
        return jZzd;
    }

    public final long zzx(zzar zzarVar, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, @Nullable JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException {
        int length;
        String strZza;
        if (mediaQueueItemArr == null || (length = mediaQueueItemArr.length) == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        }
        if (i < 0 || i >= length) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Invalid startIndex: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        if (j != -1 && j < 0) {
            StringBuilder sb2 = new StringBuilder(54);
            sb2.append("playPosition can not be negative: ");
            sb2.append(j);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzd = zzd();
        this.zzc.zzb(jZzd, zzarVar);
        try {
            jSONObject2.put(TetheredMessageKey.requestId, jZzd);
            jSONObject2.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "QUEUE_LOAD");
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
            }
            jSONObject2.put(ContextMappingConstants.ITEMS, jSONArray);
            strZza = MediaCommon.zza(Integer.valueOf(i2));
        } catch (JSONException unused) {
        }
        if (strZza == null) {
            StringBuilder sb3 = new StringBuilder(32);
            sb3.append("Invalid repeat mode: ");
            sb3.append(i2);
            throw new IllegalArgumentException(sb3.toString());
        }
        jSONObject2.put("repeatMode", strZza);
        jSONObject2.put("startIndex", i);
        if (j != -1) {
            jSONObject2.put("currentTime", CastUtils.millisecToSec(j));
        }
        if (jSONObject != null) {
            jSONObject2.put("customData", jSONObject);
        }
        if (zzab()) {
            jSONObject2.put("sequenceNumber", this.zzA);
        }
        zzg(jSONObject2.toString(), jZzd, null);
        return jZzd;
    }
}
