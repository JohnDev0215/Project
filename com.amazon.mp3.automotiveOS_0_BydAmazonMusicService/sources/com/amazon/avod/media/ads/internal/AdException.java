package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdException extends MediaException {

    public enum AdError implements MediaInternalErrorCode {
        UNKNOWN_ERROR;

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
        public MediaErrorCode getExternalCode() {
            return StandardErrorCode.AD_ERROR;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return String.format(Locale.US, "AdError:%s", name());
        }
    }

    public AdException(String str) {
        super(AdError.UNKNOWN_ERROR, str, null);
    }

    public AdException(@Nonnull Throwable th) {
        super(AdError.UNKNOWN_ERROR, th.getMessage(), th);
    }
}
