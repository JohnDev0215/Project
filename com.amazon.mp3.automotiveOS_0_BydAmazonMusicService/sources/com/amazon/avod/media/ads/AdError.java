package com.amazon.avod.media.ads;

import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum AdError {
    EXCESSIVE_BUFFERING,
    FILE_NOT_AVAILABLE,
    PLAYBACK_ERROR,
    LIVE_TEVS_TRACKING_REQUEST_ERROR,
    LIVE_TEVS_RESPONSE_ERROR,
    LIVE_TEVS_MALFORMED_URI_ERROR,
    LIVE_TEVS_ADVERTISING_ID_ERROR,
    LIVE_TEVS_EMPTY_AVAILS_RESPONSE,
    AUDIT_PING_NETWORK_ERROR,
    PLAYBACK_PLAY_HEAD_OUT_OF_BOUNDS;

    private static final Set<String> ERROR_NAMES = new HashSet();

    static {
        for (AdError adError : values()) {
            ERROR_NAMES.add(adError.name());
        }
    }

    public static ReportableString toReportableString(@Nonnull AdError adError) {
        Preconditions.checkNotNull(adError, "error");
        return new ReportableString(adError.name(), ERROR_NAMES, "UNKNOWN_ERROR");
    }
}
