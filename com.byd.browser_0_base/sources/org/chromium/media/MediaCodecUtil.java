package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.os.Build;
import android.util.Log;
import defpackage.fa1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.media.CodecProfileLevelList;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCodecUtil {

    public static class a {
        public MediaCodec a;
        public boolean b;
        public int c = 0;
    }

    public static class b implements Iterable<MediaCodecInfo> {
        public final MediaCodecInfo[] e;

        public class a implements Iterator<MediaCodecInfo> {
            public int e;

            public a() {
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                int codecCount;
                int i = this.e;
                MediaCodecInfo[] mediaCodecInfoArr = b.this.e;
                if (mediaCodecInfoArr != null) {
                    codecCount = mediaCodecInfoArr.length;
                } else {
                    try {
                        codecCount = MediaCodecList.getCodecCount();
                    } catch (RuntimeException unused) {
                        codecCount = 0;
                    }
                }
                return i < codecCount;
            }

            @Override // java.util.Iterator
            public final MediaCodecInfo next() {
                int codecCount;
                int i = this.e;
                b bVar = b.this;
                MediaCodecInfo[] mediaCodecInfoArr = bVar.e;
                if (mediaCodecInfoArr != null) {
                    codecCount = mediaCodecInfoArr.length;
                } else {
                    try {
                        codecCount = MediaCodecList.getCodecCount();
                    } catch (RuntimeException unused) {
                        codecCount = 0;
                    }
                }
                if (i == codecCount) {
                    throw new NoSuchElementException();
                }
                int i2 = this.e;
                this.e = i2 + 1;
                MediaCodecInfo[] mediaCodecInfoArr2 = bVar.e;
                return mediaCodecInfoArr2 != null ? mediaCodecInfoArr2[i2] : MediaCodecList.getCodecInfoAt(i2);
            }

            @Override // java.util.Iterator
            public final void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public b() {
            try {
                this.e = new MediaCodecList(1).getCodecInfos();
            } catch (Throwable unused) {
            }
        }

        @Override // java.lang.Iterable
        public final Iterator<MediaCodecInfo> iterator() {
            return new a();
        }
    }

    public static boolean a(MediaCodec mediaCodec, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        if (mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
            if (codecInfo.isEncoder() || (capabilitiesForType = codecInfo.getCapabilitiesForType(str)) == null) {
                return false;
            }
            return capabilitiesForType.isFeatureSupported("adaptive-playback");
        } catch (IllegalArgumentException e) {
            Log.e("cr_MediaCodecUtil", "Cannot retrieve codec information", e);
            return false;
        }
    }

    public static a b(String str, int i, MediaCrypto mediaCrypto) {
        a aVar = new a();
        if (!isDecoderSupportedForDevice(str)) {
            fa1.b("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return aVar;
        }
        try {
            if ((str.startsWith("video") && i == 1) || (str.startsWith("audio") && mediaCrypto != null && mediaCrypto.requiresSecureDecoderComponent(str))) {
                String defaultCodecName = getDefaultCodecName(str, 0, false, false);
                if (defaultCodecName.equals("")) {
                    return aVar;
                }
                MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(defaultCodecName);
                aVar.b = a(mediaCodecCreateByCodecName, str);
                mediaCodecCreateByCodecName.release();
                aVar.a = MediaCodec.createByCodecName(defaultCodecName.concat(".secure"));
            } else {
                if (i == 2) {
                    aVar.a = MediaCodec.createByCodecName(getDefaultCodecName(str, 0, true, false));
                } else if (str.equals("audio/raw")) {
                    aVar.a = MediaCodec.createByCodecName("OMX.google.raw.decoder");
                } else {
                    aVar.a = MediaCodec.createDecoderByType(str);
                }
                aVar.b = a(aVar.a, str);
            }
        } catch (Exception e) {
            fa1.d("MediaCodecUtil", "Failed to create MediaCodec: %s, codecType: %d", str, Integer.valueOf(i), e);
            aVar.a = null;
        }
        return aVar;
    }

    public static a c(String str) {
        a aVar = new a();
        Integer numD = d(str);
        if (numD == null) {
            return aVar;
        }
        try {
            aVar.a = MediaCodec.createEncoderByType(str);
            int i = 0;
            aVar.b = false;
            int iIntValue = numD.intValue();
            if (iIntValue != 0 && iIntValue != 1 && iIntValue != 2) {
                if (iIntValue != 4 && iIntValue != 5 && iIntValue != 6 && iIntValue != 7) {
                    throw new IllegalArgumentException("Invalid HWEncoder decoder parameter.");
                }
                i = 1;
            }
            aVar.c = i;
        } catch (Exception e) {
            fa1.c("MediaCodecUtil", "Failed to create MediaCodec: %s", str, e);
        }
        return aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CalledByNative
    public static boolean canDecode(String str, boolean z) {
        if (!isDecoderSupportedForDevice(str)) {
            fa1.b("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return false;
        }
        b bVar = new b();
        if ((bVar.e != null) != true) {
            MediaCodec mediaCodec = b(str, z ? 1 : 0, null).a;
            if (mediaCodec == null) {
                return false;
            }
            try {
                mediaCodec.release();
            } catch (IllegalStateException e) {
                Log.e("cr_MediaCodecUtil", "Cannot release media codec", e);
            }
            return true;
        }
        b.a aVar = bVar.new a();
        while (aVar.hasNext()) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) aVar.next();
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
                    continue;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Integer d(java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecUtil.d(java.lang.String):java.lang.Integer");
    }

    @CalledByNative
    public static String getDefaultCodecName(String str, int i, boolean z, boolean z2) {
        b.a aVar = new b().new a();
        while (true) {
            if (!aVar.hasNext()) {
                Object[] objArr = new Object[4];
                objArr[0] = i == 1 ? "Encoder" : "Decoder";
                objArr[1] = str;
                objArr[2] = Boolean.valueOf(z);
                objArr[3] = Boolean.valueOf(z2);
                fa1.d("MediaCodecUtil", "%s for type %s is not supported on this device [requireSoftware=%b, requireHardware=%b].", objArr);
                return "";
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) aVar.next();
            if (mediaCodecInfo.isEncoder() == i) {
                boolean zIsHardwareAccelerated = true ^ mediaCodecInfo.isHardwareAccelerated();
                if (!z || zIsHardwareAccelerated) {
                    if (!z2 || !zIsHardwareAccelerated) {
                        for (String str2 : mediaCodecInfo.getSupportedTypes()) {
                            if (str2.equalsIgnoreCase(str)) {
                                return mediaCodecInfo.getName();
                            }
                        }
                    }
                }
            }
        }
    }

    @CalledByNative
    public static int[] getEncoderColorFormatsForMime(String str) {
        b.a aVar = new b().new a();
        while (aVar.hasNext()) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) aVar.next();
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
        return null;
    }

    @CalledByNative
    public static Object[] getSupportedCodecProfileLevels() {
        ArrayList arrayList = new ArrayList();
        b.a aVar = new b().new a();
        while (aVar.hasNext()) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) aVar.next();
            for (String str : mediaCodecInfo.getSupportedTypes()) {
                if (isDecoderSupportedForDevice(str)) {
                    try {
                        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : mediaCodecInfo.getCapabilitiesForType(str).profileLevels) {
                            try {
                                int iA = CodecProfileLevelList.a(str);
                                arrayList.add(new CodecProfileLevelList.CodecProfileLevelAdapter(iA, CodecProfileLevelList.c(iA, codecProfileLevel.profile), CodecProfileLevelList.b(iA, codecProfileLevel.level)));
                            } catch (CodecProfileLevelList.a unused) {
                            }
                        }
                    } catch (IllegalArgumentException unused2) {
                    }
                } else {
                    fa1.k("MediaCodecUtil", "Decoder for type %s disabled on this device", str);
                }
            }
        }
        return arrayList.toArray();
    }

    @CalledByNative
    public static boolean isDecoderSupportedForDevice(String str) {
        if (str.equals("video/x-vnd.on2.vp8")) {
            String str2 = Build.HARDWARE;
            if (str2.startsWith("mt")) {
                return str2.startsWith("mt5599") || str2.startsWith("mt5895") || str2.startsWith("mt8768") || str2.startsWith("mt5887");
            }
        } else if (!str.equals("video/x-vnd.on2.vp9")) {
            str.equals("video/av01");
        } else if (Build.MODEL.equals("Nexus Player")) {
            return false;
        }
        return true;
    }

    @CalledByNative
    public static boolean isEncoderSupportedByDevice(String str) {
        return d(str) != null;
    }

    @CalledByNative
    public static boolean isSetOutputSurfaceSupported() {
        String str = Build.HARDWARE;
        return (str.equalsIgnoreCase("hi6210sft") || str.equalsIgnoreCase("hi6250")) ? false : true;
    }

    @CalledByNative
    public static boolean platformSupportsCbcsEncryption(int i) {
        return i >= 25;
    }
}
