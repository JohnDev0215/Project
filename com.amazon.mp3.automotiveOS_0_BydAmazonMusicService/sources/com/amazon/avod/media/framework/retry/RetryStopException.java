package com.amazon.avod.media.framework.retry;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RetryStopException extends MediaException {

    public enum RetryStopError implements MediaInternalErrorCode {
        UNKNOWN_ERROR;

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode
        public MediaErrorCode getExternalCode() {
            return ServiceErrorCode.SERVICE_ERROR;
        }

        @Override // com.amazon.avod.media.framework.error.MediaInternalErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return String.format(Locale.US, "RetryStopError:%s", name());
        }
    }

    public RetryStopException(String str) {
        super(RetryStopError.UNKNOWN_ERROR, str, null);
    }
}
