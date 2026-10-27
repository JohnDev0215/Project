package s2;

import H7.H;
import android.media.MediaCodecInfo;
import android.util.Pair;
import d5.C1077F;
import d5.I;
import d5.d0;
import e2.C1208p;
import e2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import p7.C2111a;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f22170a = new HashMap();

    public static void a(String str, ArrayList arrayList) {
        if ("audio/raw".equals(str)) {
            if (AbstractC1343A.f15195a < 26 && AbstractC1343A.f15196b.equals("R9") && arrayList.size() == 1 && ((n) arrayList.get(0)).f22095a.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                arrayList.add(n.h("OMX.google.raw.decoder", "audio/raw", "audio/raw", null, false, true, false, false));
            }
            Collections.sort(arrayList, new C2111a(new i(), 1));
        }
        if (AbstractC1343A.f15195a >= 32 || arrayList.size() <= 1 || !"OMX.qti.audio.decoder.flac".equals(((n) arrayList.get(0)).f22095a)) {
            return;
        }
        arrayList.add((n) arrayList.remove(0));
    }

    public static String b(C1208p c1208p) {
        Pair pairD;
        if ("audio/eac3-joc".equals(c1208p.f14316n)) {
            return "audio/eac3";
        }
        String str = c1208p.f14316n;
        if ("video/dolby-vision".equals(str) && (pairD = d(c1208p)) != null) {
            int iIntValue = ((Integer) pairD.first).intValue();
            if (iIntValue == 16 || iIntValue == 256) {
                return "video/hevc";
            }
            if (iIntValue == 512) {
                return "video/avc";
            }
            if (iIntValue == 1024) {
                return "video/av01";
            }
        }
        if ("video/mv-hevc".equals(str)) {
            return "video/hevc";
        }
        return null;
    }

    public static String c(MediaCodecInfo mediaCodecInfo, String str, String str2) {
        for (String str3 : mediaCodecInfo.getSupportedTypes()) {
            if (str3.equalsIgnoreCase(str2)) {
                return str3;
            }
        }
        if (str2.equals("video/dolby-vision")) {
            if ("OMX.MS.HEVCDV.Decoder".equals(str)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(str) || "OMX.realtek.video.decoder.tunneled".equals(str)) {
                return "video/dv_hevc";
            }
            return null;
        }
        if (str2.equals("video/mv-hevc")) {
            if ("c2.qti.mvhevc.decoder".equals(str)) {
                return "video/x-mvhevc";
            }
            return null;
        }
        if (str2.equals("audio/alac") && "OMX.lge.alac.decoder".equals(str)) {
            return "audio/x-lg-alac";
        }
        if (str2.equals("audio/flac") && "OMX.lge.flac.decoder".equals(str)) {
            return "audio/x-lg-flac";
        }
        if (str2.equals("audio/ac3") && "OMX.lge.ac3.decoder".equals(str)) {
            return "audio/lg-ac3";
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007b A[PHI: r11
  0x007b: PHI (r11v35 java.lang.Object) = 
  (r11v5 java.lang.Object)
  (r11v6 java.lang.Object)
  (r11v8 java.lang.Object)
  (r11v11 java.lang.Object)
  (r11v14 java.lang.Object)
  (r11v17 java.lang.Object)
  (r11v20 java.lang.Object)
  (r11v23 java.lang.Object)
  (r11v26 java.lang.Object)
  (r11v29 java.lang.Object)
  (r11v32 java.lang.Object)
  (r11v36 java.lang.Object)
 binds: [B:62:0x00fb, B:58:0x00ee, B:427:?, B:426:?, B:425:?, B:424:?, B:423:?, B:422:?, B:421:?, B:420:?, B:419:?, B:19:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0405 A[PHI: r2
  0x0405: PHI (r2v34 int) = (r2v33 int), (r2v36 int), (r2v37 int), (r2v38 int), (r2v39 int) binds: [B:265:0x03d8, B:268:0x03de, B:270:0x03e2, B:272:0x03e6, B:274:0x03ea] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x040b A[Catch: NumberFormatException -> 0x041b, TRY_LEAVE, TryCatch #4 {NumberFormatException -> 0x041b, blocks: (B:262:0x03bb, B:264:0x03cf, B:275:0x03ec, B:287:0x040b), top: B:417:0x03bb }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair d(e2.C1208p r27) {
        /*
            Method dump skipped, instruction units count: 1818
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.v.d(e2.p):android.util.Pair");
    }

    public static synchronized List e(String str, boolean z6, boolean z10) {
        try {
            r rVar = new r(str, z6, z10);
            HashMap map = f22170a;
            List list = (List) map.get(rVar);
            if (list != null) {
                return list;
            }
            C1.j jVar = new C1.j();
            jVar.l = (z6 || z10) ? 1 : 0;
            ArrayList arrayListF = f(rVar, jVar);
            if (z6 && arrayListF.isEmpty() && AbstractC1343A.f15195a <= 23) {
                arrayListF = f(rVar, new m6.b());
                if (!arrayListF.isEmpty()) {
                    AbstractC1346c.z("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + ((n) arrayListF.get(0)).f22095a);
                }
            }
            a(str, arrayListF);
            I iW = I.w(arrayListF);
            map.put(rVar, iW);
            return iW;
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList f(s2.r r23, s2.t r24) throws s2.s {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.v.f(s2.r, s2.t):java.util.ArrayList");
    }

    public static d0 g(i iVar, C1208p c1208p, boolean z6, boolean z10) {
        List listE;
        String str = c1208p.f14316n;
        iVar.getClass();
        List listE2 = e(str, z6, z10);
        String strB = b(c1208p);
        if (strB == null) {
            listE = d0.f13415p;
        } else {
            iVar.getClass();
            listE = e(strB, z6, z10);
        }
        C1077F c1077fU = I.u();
        c1077fU.e(listE2);
        c1077fU.e(listE);
        return c1077fU.h();
    }

    public static boolean h(MediaCodecInfo mediaCodecInfo, String str, boolean z6, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z6 && str.endsWith(".secure"))) {
            return false;
        }
        int i7 = AbstractC1343A.f15195a;
        if (i7 < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && "samsung".equals(AbstractC1343A.c))) {
            String str3 = AbstractC1343A.f15196b;
            if (str3.startsWith("zeroflte") || str3.startsWith("zerolte") || str3.startsWith("zenlte") || "SC-05G".equals(str3) || "marinelteatt".equals(str3) || "404SC".equals(str3) || "SC-04G".equals(str3) || "SCV31".equals(str3)) {
                return false;
            }
        }
        return (i7 <= 23 && "audio/eac3-joc".equals(str2) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) ? false : true;
    }

    public static boolean i(MediaCodecInfo mediaCodecInfo, String str) {
        if (AbstractC1343A.f15195a >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        if (L.h(str)) {
            return true;
        }
        String strR = H.R(mediaCodecInfo.getName());
        if (strR.startsWith("arc.")) {
            return false;
        }
        if (strR.startsWith("omx.google.") || strR.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((strR.startsWith("omx.sec.") && strR.contains(".sw.")) || strR.equals("omx.qcom.video.decoder.hevcswvdec") || strR.startsWith("c2.android.") || strR.startsWith("c2.google.")) {
            return true;
        }
        return (strR.startsWith("omx.") || strR.startsWith("c2.")) ? false : true;
    }
}
