package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.os.Build;
import defpackage.C0653dH;
import defpackage.C0987jH;
import defpackage.C1043kH;
import defpackage.C1099lH;
import defpackage.Ts;
import defpackage.Z7;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import org.chromium.base.BuildInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaCodecUtil {
    public static final String[] a = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};

    public static void a(C0653dH c0653dH, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        int[][] iArr = {new int[]{200, 10}, new int[]{800, 11}, new int[]{1800, 20}, new int[]{3600, 21}, new int[]{7200, 30}, new int[]{12000, 31}, new int[]{18000, 40}, new int[]{30000, 41}, new int[]{60000, 50}, new int[]{120000, 51}, new int[]{180000, 52}};
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        for (int i = 0; i < 11; i++) {
            int[] iArr2 = iArr[i];
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            if (videoCapabilities.getBitrateRange().contains(Integer.valueOf(i2))) {
                c0653dH.a.add(new CodecProfileLevelList$CodecProfileLevelAdapter(7, 12, i3));
            }
        }
    }

    public static boolean b(MediaCodec mediaCodec, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        if (mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
            if (codecInfo.isEncoder() || e(str) || (capabilitiesForType = codecInfo.getCapabilitiesForType(str)) == null) {
                return false;
            }
            return capabilitiesForType.isFeatureSupported("adaptive-playback");
        } catch (IllegalArgumentException e) {
            Ts.a("MediaCodecUtil", "Cannot retrieve codec information", e);
            return false;
        }
    }

    public static C0987jH c(String str, int i, MediaCrypto mediaCrypto) {
        C0987jH c0987jH = new C0987jH();
        if (!isDecoderSupportedForDevice(str)) {
            Ts.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return c0987jH;
        }
        try {
            if ((str.startsWith("video") && i == 1) || (str.startsWith("audio") && mediaCrypto != null && mediaCrypto.requiresSecureDecoderComponent(str))) {
                String defaultCodecName = getDefaultCodecName(str, 0, false);
                if (defaultCodecName.equals("")) {
                    return c0987jH;
                }
                MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(defaultCodecName);
                c0987jH.b = b(mediaCodecCreateByCodecName, str);
                mediaCodecCreateByCodecName.release();
                c0987jH.a = MediaCodec.createByCodecName(defaultCodecName + ".secure");
            } else {
                if (i == 2) {
                    c0987jH.a = MediaCodec.createByCodecName(getDefaultCodecName(str, 0, true));
                } else if (str.equals("audio/raw")) {
                    c0987jH.a = MediaCodec.createByCodecName("OMX.google.raw.decoder");
                } else {
                    c0987jH.a = MediaCodec.createDecoderByType(str);
                }
                c0987jH.b = b(c0987jH.a, str);
            }
        } catch (Exception e) {
            Ts.a("MediaCodecUtil", "Failed to create MediaCodec: %s, codecType: %d", str, Integer.valueOf(i), e);
            c0987jH.a = null;
        }
        return c0987jH;
    }

    public static boolean canDecode(String str, boolean z) {
        if (!isDecoderSupportedForDevice(str)) {
            Ts.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return false;
        }
        C1099lH c1099lH = new C1099lH();
        if (!c1099lH.f()) {
            MediaCodec mediaCodec = c(str, z ? 1 : 0, null).a;
            if (mediaCodec == null) {
                return false;
            }
            try {
                mediaCodec.release();
            } catch (IllegalStateException e) {
                Ts.a("MediaCodecUtil", "Cannot release media codec", e);
            }
            return true;
        }
        Iterator it = c1099lH.iterator();
        while (true) {
            C1043kH c1043kH = (C1043kH) it;
            if (!c1043kH.hasNext()) {
                return false;
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) c1043kH.next();
            if (!mediaCodecInfo.isEncoder()) {
                try {
                    MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                    if (capabilitiesForType == null) {
                        continue;
                    } else {
                        if (z && capabilitiesForType.isFeatureSupported("secure-playback")) {
                            return true;
                        }
                        if (!z && !capabilitiesForType.isFeatureRequired("secure-playback")) {
                            return true;
                        }
                    }
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Integer d(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecUtil.d(java.lang.String):java.lang.Integer");
    }

    public static boolean e(String str) {
        if ((str.equals("video/avc") || str.equals("video/avc1")) && Build.VERSION.RELEASE.equals("4.4.2") && Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
            return Build.MODEL.startsWith("GT-I9300") || Build.MODEL.startsWith("SCH-I535");
        }
        return false;
    }

    public static boolean f(String str) {
        return str.startsWith("OMX.google.") || !str.startsWith("OMX.");
    }

    public static String getDefaultCodecName(String str, int i, boolean z) {
        Iterator it = new C1099lH().iterator();
        while (true) {
            C1043kH c1043kH = (C1043kH) it;
            if (!c1043kH.hasNext()) {
                Ts.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
                return "";
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) c1043kH.next();
            if (mediaCodecInfo.isEncoder() == i && (!z || f(mediaCodecInfo.getName()))) {
                for (String str2 : mediaCodecInfo.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return mediaCodecInfo.getName();
                    }
                }
            }
        }
    }

    public static int[] getEncoderColorFormatsForMime(String str) {
        Iterator it = new C1099lH().iterator();
        while (true) {
            C1043kH c1043kH = (C1043kH) it;
            if (!c1043kH.hasNext()) {
                return null;
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) c1043kH.next();
            if (mediaCodecInfo.isEncoder()) {
                for (String str2 : mediaCodecInfo.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        try {
                            return mediaCodecInfo.getCapabilitiesForType(str2).colorFormats;
                        } catch (IllegalArgumentException unused) {
                            continue;
                        }
                    }
                }
            }
        }
    }

    public static Object[] getSupportedCodecProfileLevels() {
        C0653dH c0653dH = new C0653dH();
        Iterator it = new C1099lH().iterator();
        while (true) {
            C1043kH c1043kH = (C1043kH) it;
            if (!c1043kH.hasNext()) {
                return c0653dH.a.toArray();
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) c1043kH.next();
            for (String str : mediaCodecInfo.getSupportedTypes()) {
                if (isDecoderSupportedForDevice(str)) {
                    try {
                        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                        if (!str.endsWith("vp9") || Build.VERSION.SDK_INT > 23) {
                            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
                                c0653dH.a(str, codecProfileLevel);
                            }
                        } else {
                            a(c0653dH, capabilitiesForType);
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                } else {
                    Ts.f("MediaCodecUtil", "Decoder for type %s disabled on this device", str);
                }
            }
        }
    }

    public static boolean isDecoderSupportedForDevice(String str) {
        if (str.equals("video/x-vnd.on2.vp8")) {
            return ((Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung") && (Build.MODEL.startsWith("GT-I9190") || Build.MODEL.startsWith("GT-I9195"))) || Build.HARDWARE.startsWith("mt")) ? false : true;
        }
        if (str.equals("video/x-vnd.on2.vp9")) {
            return !Build.MODEL.equals("Nexus Player");
        }
        if (str.equals("video/av01")) {
            return BuildInfo.a();
        }
        str.equals("audio/opus");
        str.equals("video/hevc");
        return true;
    }

    public static boolean isEncoderSupportedByDevice(String str) {
        if (!str.equals("video/avc") || !Arrays.asList(a).contains(Build.MODEL)) {
            return d(str) != null;
        }
        StringBuilder sbF = Z7.f("Model: ");
        sbF.append(Build.MODEL);
        sbF.append(" has blacklisted H.264 encoder.");
        Ts.f("MediaCodecUtil", sbF.toString(), new Object[0]);
        return false;
    }

    public static boolean isSetOutputSurfaceSupported() {
        return (Build.VERSION.SDK_INT < 23 || Build.HARDWARE.equalsIgnoreCase("hi6210sft") || Build.HARDWARE.equalsIgnoreCase("hi6250")) ? false : true;
    }

    public static boolean platformSupportsCbcsEncryption(int i) {
        return i >= 25;
    }
}
