package com.google.zxing.oned;

import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.MediaError;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public final class Code39Reader extends OneDReader {
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, voOSType.VOOSMP_PID_DISABLE_FORCE_OPENGL, 100, 265, 73, 328, 25, 280, 88, 13, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_ENDPOINT, 76, 28, MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 67, MediaError.DetailedErrorCode.DASH_NO_INIT, 19, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_MIXING_BALANCE, 82, 7, MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, 70, 22, 385, 193, 448, voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID, 400, 208, 133, 388, 196, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }
}
