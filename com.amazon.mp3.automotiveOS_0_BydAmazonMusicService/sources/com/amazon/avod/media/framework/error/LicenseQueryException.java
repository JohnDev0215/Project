package com.amazon.avod.media.framework.error;

import com.amazon.avod.media.error.MediaErrorCode;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LicenseQueryException extends Exception {
    private static final long serialVersionUID = -815972966755182102L;
    private final MediaErrorCode mErrorCode;

    public LicenseQueryException(@Nonnull MediaErrorCode mediaErrorCode, String str, Throwable th) {
        super(str, th);
        this.mErrorCode = mediaErrorCode;
    }

    @Nonnull
    public MediaErrorCode getErrorCode() {
        return this.mErrorCode;
    }
}
