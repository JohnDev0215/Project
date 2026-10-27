package com.google.zxing.oned;

import com.google.android.gms.cast.MediaError;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private final int[] counters = new int[6];

    static {
        int[] iArr = {276, 328, 324, MediaError.DetailedErrorCode.DASH_NO_INIT, 296, voOSType.VOOSMP_PID_DISABLE_FORCE_OPENGL, 290, 336, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_MIXING_BALANCE, 266, 424, MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, MediaError.DetailedErrorCode.SMOOTH_NO_MEDIA_DATA, 326, 300, 278, 436, 434, 428, MediaError.DetailedErrorCode.DASH_MANIFEST_NO_MIMETYPE, 406, 410, 364, 358, 310, MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }
}
