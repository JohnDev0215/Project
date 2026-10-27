package com.google.android.exoplayer2.util;

import android.os.Build;
import android.os.SystemProperties;
import android.util.Log;
import com.google.android.exoplayer2.util.Logger;

/* JADX INFO: loaded from: classes6.dex */
public final class AmazonQuirks {
    private static final String DEVICEMODEL;
    private static final String MANUFACTURER;
    private static final String TAG = "AmazonQuirks";
    private static boolean ignoreDwellMode;
    private static final boolean isAmazonDevice;
    private static final boolean isFirePhone;
    private static final boolean isFireTVCubeGen1;
    private static final boolean isFireTVGen1;
    private static final boolean isFireTVGen2;
    private static final boolean isFireTVGen3;
    private static final boolean isFireTVStick;
    private static final boolean isKindleTablet;
    private static final boolean isSOHOKindleTablet;
    private static boolean isSnappingToVsyncDisabled;

    public static int getAudioHWLatency() {
        return 90000;
    }

    static {
        String str = Build.MODEL;
        DEVICEMODEL = str;
        String str2 = Build.MANUFACTURER;
        MANUFACTURER = str2;
        boolean z = false;
        isSnappingToVsyncDisabled = false;
        ignoreDwellMode = false;
        boolean zEqualsIgnoreCase = str2.equalsIgnoreCase("Amazon");
        isAmazonDevice = zEqualsIgnoreCase;
        isFireTVGen1 = zEqualsIgnoreCase && str.equalsIgnoreCase("AFTB");
        isFireTVGen2 = zEqualsIgnoreCase && str.equalsIgnoreCase("AFTS");
        isFireTVStick = zEqualsIgnoreCase && str.equalsIgnoreCase("AFTM");
        isFireTVGen3 = zEqualsIgnoreCase && str.equalsIgnoreCase("AFTN");
        isFireTVCubeGen1 = zEqualsIgnoreCase && str.equalsIgnoreCase("AFTA");
        isKindleTablet = zEqualsIgnoreCase && str.startsWith("KF");
        isFirePhone = zEqualsIgnoreCase && str.startsWith("SD");
        if (zEqualsIgnoreCase && str.equalsIgnoreCase("KFSOWI")) {
            z = true;
        }
        isSOHOKindleTablet = z;
        loadForcedLogSettings();
    }

    private AmazonQuirks() {
    }

    public static boolean isDolbyPassthroughQuirkEnabled() {
        return isFireTVGen1Family();
    }

    public static boolean isAmazonDevice() {
        return isAmazonDevice;
    }

    public static boolean isFireTVGen1Family() {
        return isFireTVGen1 || isFireTVStick;
    }

    public static boolean isFireTVGen2() {
        return isFireTVGen2;
    }

    public static boolean useDefaultPassthroughDecoder() {
        if (isFireTVGen1Family()) {
            Log.i(TAG, "Using platform Dolby decoder");
            return false;
        }
        Log.i(TAG, "Using default Dolby pass-through decoder");
        return true;
    }

    public static boolean isLatencyQuirkEnabled() {
        return Util.SDK_INT <= 19 && (isKindleTablet || isFirePhone);
    }

    public static boolean waitForDRMKeysBeforeInitCodec() {
        return isFireTVGen1Family();
    }

    public static boolean codecNeedsEosPropagationWorkaround(String str) {
        boolean z = isFireTVGen2() && str.endsWith(".secure");
        if (z) {
            Log.i(TAG, "Codec Needs EOS Propagation Workaround " + str);
        }
        return z;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static void loadForcedLogSettings() {
        String systemProperty = getSystemProperty("com.amazon.exoplayer.forcelog");
        if (systemProperty == null || systemProperty.equals("")) {
            return;
        }
        try {
            for (String str : systemProperty.split("#")) {
                String[] strArrSplit = str.split(":");
                Logger.Module moduleValueOf = Logger.Module.valueOf(strArrSplit[0]);
                String lowerCase = strArrSplit[1].toLowerCase();
                byte b = -1;
                int i = 3;
                switch (lowerCase.hashCode()) {
                    case 3237038:
                        if (lowerCase.equals("info")) {
                            b = 1;
                        }
                        break;
                    case 3641990:
                        if (lowerCase.equals("warn")) {
                            b = 3;
                        }
                        break;
                    case 96784904:
                        if (lowerCase.equals("error")) {
                            b = 0;
                        }
                        break;
                    case 351107458:
                        if (lowerCase.equals("verbose")) {
                            b = 2;
                        }
                        break;
                }
                if (b == 0) {
                    i = 6;
                } else if (b == 1) {
                    i = 4;
                } else if (b == 2) {
                    i = 2;
                } else if (b == 3) {
                    i = 5;
                }
                Logger.setLogLevel(moduleValueOf, i);
            }
        } catch (Exception e) {
            Log.e(TAG, "Could not set logging level.", e);
        }
    }

    public static String getSystemProperty(String str) {
        try {
            return (String) SystemProperties.class.getMethod("get", String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isMaxInputSizeSupported(java.lang.String r3, int r4) {
        /*
            boolean r0 = com.google.android.exoplayer2.util.AmazonQuirks.isFireTVGen2
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L1c
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L19
            java.lang.String r0 = "AVC.secure"
            boolean r3 = r3.endsWith(r0)
            if (r3 == 0) goto L19
            r3 = 2936012(0x2ccccc, float:4.114229E-39)
            if (r4 > r3) goto L1a
        L19:
            r1 = r2
        L1a:
            r2 = r1
            goto L2f
        L1c:
            boolean r4 = com.google.android.exoplayer2.util.AmazonQuirks.isSOHOKindleTablet
            if (r4 == 0) goto L2f
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L19
            java.lang.String r4 = "OMX.TI.DUCATI1.VIDEO.DECODER"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 != 0) goto L1a
            goto L19
        L2f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.AmazonQuirks.isMaxInputSizeSupported(java.lang.String, int):boolean");
    }

    public static boolean isSnappingToVsyncDisabled() {
        return isSnappingToVsyncDisabled;
    }

    public static boolean isDwellModeIgnored() {
        return ignoreDwellMode;
    }

    public static boolean codecNeedsSetOutputSurfaceWorkaround() {
        return isFireTVGen3 || isFireTVCubeGen1;
    }
}
