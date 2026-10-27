package com.amazon.avod.drm.playready;

import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyException extends MediaException {
    public PlayReadyException(String str, LicenseError licenseError) {
        super(licenseError, str, null);
    }
}
