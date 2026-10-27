package com.amazon.avod.userdownload;

import com.amazon.avod.media.error.DrmErrorCode;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadErrorConverter {
    protected static final ImmutableMap<DrmErrorCode, DrmErrorCode> LICENSE_EXPIRY_FOR_RENTALS = ImmutableMap.builder().put(DrmErrorCode.LICENSE_EXPIRED, DrmErrorCode.LICENSE_EXPIRED_RENTAL).put(DrmErrorCode.LICENSE_EXPIRED_LICENSE_CLOCK, DrmErrorCode.LICENSE_EXPIRED_RENTAL_LICENSE_CLOCK).put(DrmErrorCode.LICENSE_EXPIRED_PLAYBACK_CLOCK, DrmErrorCode.LICENSE_EXPIRED_RENTAL_PLAYBACK_CLOCK).build();

    public static ImmutableCollection<DrmErrorCode> getBaseLicenseExpiryCodes() {
        return LICENSE_EXPIRY_FOR_RENTALS.keySet();
    }
}
