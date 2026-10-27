package com.amazon.avod.media.download.internal.db;

import com.amazon.avod.media.download.error.DownloadErrorCode;
import com.amazon.avod.media.download.error.DownloadExecutionErrorCode;
import com.amazon.avod.media.download.error.PurchaseErrorCode;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadErrorUtils {
    private static final MediaErrorCode DEFAULT_ERROR = StandardErrorCode.DOWNLOAD_UNKNOWN_ERROR;

    public String getErrorName(@Nonnull MediaErrorCode mediaErrorCode) {
        Preconditions.checkNotNull(mediaErrorCode);
        return mediaErrorCode.getName();
    }

    public MediaErrorCode fromErrorName(@Nonnull String str) {
        Preconditions.checkNotNull(str);
        for (StandardErrorCode standardErrorCode : StandardErrorCode.values()) {
            if (standardErrorCode.getName().equals(str)) {
                return standardErrorCode;
            }
        }
        for (DrmErrorCode drmErrorCode : DrmErrorCode.values()) {
            if (drmErrorCode.getName().equals(str)) {
                return drmErrorCode;
            }
        }
        for (ServiceErrorCode serviceErrorCode : ServiceErrorCode.values()) {
            if (serviceErrorCode.getName().equals(str)) {
                return serviceErrorCode;
            }
        }
        for (PurchaseErrorCode purchaseErrorCode : PurchaseErrorCode.values()) {
            if (purchaseErrorCode.getName().equals(str)) {
                return purchaseErrorCode;
            }
        }
        for (DownloadExecutionErrorCode downloadExecutionErrorCode : DownloadExecutionErrorCode.values()) {
            if (downloadExecutionErrorCode.getName().equals(str)) {
                return downloadExecutionErrorCode;
            }
        }
        for (DownloadErrorCode downloadErrorCode : DownloadErrorCode.values()) {
            if (downloadErrorCode.getName().equals(str)) {
                return downloadErrorCode;
            }
        }
        DLog.logf("Unable to translate the following error to known error codes, returning default error : " + str);
        return DEFAULT_ERROR;
    }
}
