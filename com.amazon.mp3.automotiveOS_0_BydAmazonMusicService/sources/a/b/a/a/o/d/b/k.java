package a.b.a.a.o.d.b;

import a.b.a.a.n.b;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.util.Range;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final k b = new k();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a.b.a.a.i.b f121a = a.b.a.a.i.b.d.a("VideoDecoderUtil", b.a.Video);

    public enum a {
        KEEP_CODEC_RESULT_YES_WITHOUT_RECONFIGURATION,
        KEEP_CODEC_RESULT_YES_WITH_RECONFIGURATION,
        KEEP_CODEC_RESULT_NO
    }

    public final MediaCodecInfo.CodecCapabilities a(MediaCodecInfo mediaCodecInfo, String str) {
        try {
            return mediaCodecInfo.getCapabilitiesForType(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @RequiresApi(21)
    public final MediaCodecList a(boolean z, boolean z2) {
        return new MediaCodecList((z || z2) ? 1 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    @androidx.annotation.RequiresApi(21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.media.MediaCodecInfo r11, a.b.a.a.o.f.a r12) {
        /*
            r10 = this;
            boolean r0 = r11.isEncoder()
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.String r0 = r12.d
            android.media.MediaCodecInfo$CodecCapabilities r0 = r10.a(r11, r0)
            if (r0 != 0) goto L11
            return r1
        L11:
            java.lang.String r2 = r12.d
            android.media.MediaCodecInfo$CodecProfileLevel r3 = r12.j
            boolean r2 = r10.b(r11, r2)
            r4 = 1
            if (r2 != 0) goto L1d
            goto L48
        L1d:
            if (r3 != 0) goto L20
            goto L46
        L20:
            android.media.MediaCodecInfo$CodecProfileLevel[] r2 = r0.profileLevels
            java.lang.String r5 = "decoderCapabilities.profileLevels"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r5)
            int r5 = r2.length
            r6 = r1
        L29:
            if (r6 >= r5) goto L43
            r7 = r2[r6]
            int r8 = r7.profile
            int r9 = r3.profile
            if (r8 != r9) goto L3b
            int r7 = r7.level
            int r8 = r3.level
            if (r7 < r8) goto L3b
            r7 = r4
            goto L3c
        L3b:
            r7 = r1
        L3c:
            if (r7 == 0) goto L40
            r2 = r4
            goto L44
        L40:
            int r6 = r6 + 1
            goto L29
        L43:
            r2 = r1
        L44:
            if (r2 == 0) goto L48
        L46:
            r2 = r4
            goto L49
        L48:
            r2 = r1
        L49:
            if (r2 != 0) goto L4c
            return r1
        L4c:
            android.media.MediaCodecInfo$VideoCapabilities r0 = r0.getVideoCapabilities()
            int r2 = r12.e
            int r3 = r12.f
            float r12 = r12.g
            double r5 = (double) r12
            boolean r12 = r0.areSizeAndRateSupported(r2, r3, r5)
            if (r12 != 0) goto L5e
            return r1
        L5e:
            java.lang.String r11 = r11.getName()
            java.lang.String r12 = "codecInfo.name"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
            java.lang.String r12 = ".secure"
            boolean r11 = kotlin.text.StringsKt.endsWith(r11, r12, r4)
            if (r11 == 0) goto L70
            return r1
        L70:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.a.o.d.b.k.a(android.media.MediaCodecInfo, a.b.a.a.o.f.a):boolean");
    }

    public final MediaCodecInfo.VideoCapabilities b(String str) {
        for (MediaCodecInfo codecInfo : a(false, false).getCodecInfos()) {
            Intrinsics.checkExpressionValueIsNotNull(codecInfo, "codecInfo");
            MediaCodecInfo.CodecCapabilities codecCapabilitiesA = a(codecInfo, str);
            if (codecCapabilitiesA != null && codecCapabilitiesA.getVideoCapabilities() != null) {
                return codecCapabilitiesA.getVideoCapabilities();
            }
        }
        return null;
    }

    public final boolean b(MediaCodecInfo mediaCodecInfo, String str) {
        String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
        Intrinsics.checkExpressionValueIsNotNull(supportedTypes, "codecInfo.supportedTypes");
        for (String str2 : supportedTypes) {
            if (StringsKt__StringsJVMKt.equals(str2, str, true)) {
                return true;
            }
        }
        return false;
    }

    public final boolean a() {
        boolean z;
        MediaCodecInfo[] codecInfos = a(false, false).getCodecInfos();
        Intrinsics.checkExpressionValueIsNotNull(codecInfos, "getDeviceCodecsSortedByD…              .codecInfos");
        ArrayList<MediaCodecInfo> arrayList = new ArrayList();
        for (MediaCodecInfo it : codecInfos) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (!it.isEncoder()) {
                arrayList.add(it);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        for (MediaCodecInfo it2 : arrayList) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            String[] supportedTypes = it2.getSupportedTypes();
            Intrinsics.checkExpressionValueIsNotNull(supportedTypes, "codecInfo.supportedTypes");
            int length = supportedTypes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                if (StringsKt__StringsJVMKt.equals(supportedTypes[i], "video/hevc", true)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final List<MediaCodecInfo.CodecProfileLevel> a(String str) {
        List<MediaCodecInfo.CodecProfileLevel> arrayList;
        MediaCodecInfo[] codecInfos = a(false, false).getCodecInfos();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (MediaCodecInfo codecInfo : codecInfos) {
            Intrinsics.checkExpressionValueIsNotNull(codecInfo, "codecInfo");
            MediaCodecInfo.CodecCapabilities codecCapabilitiesA = a(codecInfo, str);
            if (codecCapabilitiesA != null) {
                MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = codecCapabilitiesA.profileLevels;
                Intrinsics.checkExpressionValueIsNotNull(codecProfileLevelArr, "decoderCapabilities.profileLevels");
                arrayList = ArraysKt___ArraysKt.toMutableList(codecProfileLevelArr);
            } else {
                arrayList = new ArrayList();
            }
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : arrayList) {
                if (!linkedHashMap.containsKey(Integer.valueOf(codecProfileLevel.profile))) {
                    linkedHashMap.put(Integer.valueOf(codecProfileLevel.profile), codecProfileLevel);
                }
                Object obj = linkedHashMap.get(Integer.valueOf(codecProfileLevel.profile));
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                if (codecProfileLevel.level > ((MediaCodecInfo.CodecProfileLevel) obj).level) {
                    linkedHashMap.put(Integer.valueOf(codecProfileLevel.profile), codecProfileLevel);
                }
            }
        }
        return CollectionsKt___CollectionsKt.toList(linkedHashMap.values());
    }

    public final a.b.a.a.o.e.c a(String str, int i, int i2) {
        a.b.a.a.o.e.h hVar = new a.b.a.a.o.e.h();
        if (str != null) {
            MediaCodecInfo.VideoCapabilities videoCapabilitiesB = b(str);
            if (videoCapabilitiesB == null) {
                return hVar;
            }
            Range<Integer> supportedWidths = videoCapabilitiesB.getSupportedWidths();
            Intrinsics.checkExpressionValueIsNotNull(supportedWidths, "videoCapabilities.supportedWidths");
            Integer width = (Integer) supportedWidths.getUpper();
            Intrinsics.checkExpressionValueIsNotNull(width, "width");
            Range<Integer> supportedHeightsFor = videoCapabilitiesB.getSupportedHeightsFor(width.intValue());
            Intrinsics.checkExpressionValueIsNotNull(supportedHeightsFor, "videoCapabilities.getSupportedHeightsFor(width)");
            Integer height = (Integer) supportedHeightsFor.getUpper();
            int iIntValue = width.intValue();
            Intrinsics.checkExpressionValueIsNotNull(height, "height");
            int iIntValue2 = height.intValue();
            synchronized (hVar) {
                hVar.f139a = iIntValue;
                hVar.b = iIntValue2;
            }
            return hVar;
        }
        synchronized (hVar) {
            hVar.f139a = i;
            hVar.b = i2;
        }
        return hVar;
    }
}
