package e2;

import F1.C0254m;
import android.text.TextUtils;
import h2.AbstractC1343A;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ArrayList f13982a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f13983b = Pattern.compile("^mp4a\\.([a-zA-Z0-9]{2})(?:\\.([0-9]{1,2}))?$");

    public static boolean a(String str, String str2) {
        String string = null;
        if (str != null) {
            String[] strArrR = AbstractC1343A.R(str);
            StringBuilder sb = new StringBuilder();
            for (String str3 : strArrR) {
                if (str2.equals(c(str3))) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(str3);
                }
            }
            if (sb.length() > 0) {
                string = sb.toString();
            }
        }
        return string != null;
    }

    public static int b(String str, String str2) {
        C0254m c0254mE;
        str.getClass();
        switch (str) {
            case "audio/eac3-joc":
                return 18;
            case "audio/vnd.dts.hd;profile=lbr":
                return 8;
            case "audio/vnd.dts":
                return 7;
            case "audio/mp4a-latm":
                if (str2 == null || (c0254mE = e(str2)) == null) {
                    return 0;
                }
                return c0254mE.c();
            case "audio/ac3":
                return 5;
            case "audio/ac4":
                return 17;
            case "audio/vnd.dts.uhd;profile=p2":
                return 30;
            case "audio/eac3":
                return 6;
            case "audio/mpeg":
                return 9;
            case "audio/opus":
                return 20;
            case "audio/vnd.dts.hd":
                return 8;
            case "audio/true-hd":
                return 14;
            default:
                return 0;
        }
    }

    public static String c(String str) {
        C0254m c0254mE;
        String strD = null;
        if (str == null) {
            return null;
        }
        String strR = H7.H.R(str.trim());
        if (strR.startsWith("avc1") || strR.startsWith("avc3")) {
            return "video/avc";
        }
        if (strR.startsWith("hev1") || strR.startsWith("hvc1")) {
            return "video/hevc";
        }
        if (strR.startsWith("dvav") || strR.startsWith("dva1") || strR.startsWith("dvhe") || strR.startsWith("dvh1")) {
            return "video/dolby-vision";
        }
        if (strR.startsWith("av01")) {
            return "video/av01";
        }
        if (strR.startsWith("vp9") || strR.startsWith("vp09")) {
            return "video/x-vnd.on2.vp9";
        }
        if (strR.startsWith("vp8") || strR.startsWith("vp08")) {
            return "video/x-vnd.on2.vp8";
        }
        if (strR.startsWith("mp4a")) {
            if (strR.startsWith("mp4a.") && (c0254mE = e(strR)) != null) {
                strD = d(c0254mE.f2343b);
            }
            return strD == null ? "audio/mp4a-latm" : strD;
        }
        if (strR.startsWith("mha1")) {
            return "audio/mha1";
        }
        if (strR.startsWith("mhm1")) {
            return "audio/mhm1";
        }
        if (strR.startsWith("ac-3") || strR.startsWith("dac3")) {
            return "audio/ac3";
        }
        if (strR.startsWith("ec-3") || strR.startsWith("dec3")) {
            return "audio/eac3";
        }
        if (strR.startsWith("ec+3")) {
            return "audio/eac3-joc";
        }
        if (strR.startsWith("ac-4") || strR.startsWith("dac4")) {
            return "audio/ac4";
        }
        if (strR.startsWith("dtsc")) {
            return "audio/vnd.dts";
        }
        if (strR.startsWith("dtse")) {
            return "audio/vnd.dts.hd;profile=lbr";
        }
        if (strR.startsWith("dtsh") || strR.startsWith("dtsl")) {
            return "audio/vnd.dts.hd";
        }
        if (strR.startsWith("dtsx")) {
            return "audio/vnd.dts.uhd;profile=p2";
        }
        if (strR.startsWith("opus")) {
            return "audio/opus";
        }
        if (strR.startsWith("vorbis")) {
            return "audio/vorbis";
        }
        if (strR.startsWith("flac")) {
            return "audio/flac";
        }
        if (strR.startsWith("stpp")) {
            return "application/ttml+xml";
        }
        if (strR.startsWith("wvtt")) {
            return "text/vtt";
        }
        if (strR.contains("cea708")) {
            return "application/cea-708";
        }
        if (strR.contains("eia608") || strR.contains("cea608")) {
            return "application/cea-608";
        }
        ArrayList arrayList = f13982a;
        if (arrayList.size() <= 0) {
            return null;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public static String d(int i7) {
        if (i7 == 32) {
            return "video/mp4v-es";
        }
        if (i7 == 33) {
            return "video/avc";
        }
        if (i7 == 35) {
            return "video/hevc";
        }
        if (i7 == 64) {
            return "audio/mp4a-latm";
        }
        if (i7 == 163) {
            return "video/wvc1";
        }
        if (i7 == 177) {
            return "video/x-vnd.on2.vp9";
        }
        if (i7 == 221) {
            return "audio/vorbis";
        }
        if (i7 == 165) {
            return "audio/ac3";
        }
        if (i7 == 166) {
            return "audio/eac3";
        }
        switch (i7) {
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
                return "video/mpeg2";
            case 102:
            case 103:
            case 104:
                return "audio/mp4a-latm";
            case 105:
            case 107:
                return "audio/mpeg";
            case 106:
                return "video/mpeg";
            case 108:
                return "image/jpeg";
            default:
                switch (i7) {
                    case 169:
                    case 172:
                        return "audio/vnd.dts";
                    case 170:
                    case 171:
                        return "audio/vnd.dts.hd";
                    case 173:
                        return "audio/opus";
                    case 174:
                        return "audio/ac4";
                    default:
                        return null;
                }
        }
    }

    public static C0254m e(String str) {
        Matcher matcher = f13983b.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        String strGroup2 = matcher.group(2);
        try {
            return new C0254m(Integer.parseInt(strGroup, 16), strGroup2 != null ? Integer.parseInt(strGroup2) : 0, 2);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static String f(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(47)) == -1) {
            return null;
        }
        return str.substring(0, iIndexOf);
    }

    public static int g(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (h(str)) {
            return 1;
        }
        if (k(str)) {
            return 2;
        }
        if (j(str)) {
            return 3;
        }
        if (i(str)) {
            return 4;
        }
        if ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str) || "application/vnd.dvb.ait".equals(str)) {
            return 5;
        }
        if ("application/x-camera-motion".equals(str)) {
            return 6;
        }
        ArrayList arrayList = f13982a;
        if (arrayList.size() <= 0) {
            return -1;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }

    public static boolean h(String str) {
        return "audio".equals(f(str));
    }

    public static boolean i(String str) {
        return "image".equals(f(str)) || "application/x-image-uri".equals(str);
    }

    public static boolean j(String str) {
        return "text".equals(f(str)) || "application/x-media3-cues".equals(str) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/x-subrip".equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str);
    }

    public static boolean k(String str) {
        return "video".equals(f(str));
    }

    public static String l(String str) {
        String strR;
        if (str == null) {
            return null;
        }
        strR = H7.H.R(str);
        strR.getClass();
        switch (strR) {
            case "audio/x-flac":
                return "audio/flac";
            case "application/x-mpegurl":
                return "application/x-mpegURL";
            case "audio/x-wav":
                return "audio/wav";
            case "audio/mpeg-l1":
                return "audio/mpeg-L1";
            case "audio/mpeg-l2":
                return "audio/mpeg-L2";
            case "audio/mp3":
                return "audio/mpeg";
            default:
                return strR;
        }
    }
}
