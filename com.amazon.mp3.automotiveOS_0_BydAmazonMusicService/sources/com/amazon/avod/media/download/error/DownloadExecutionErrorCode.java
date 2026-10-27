package com.amazon.avod.media.download.error;

import com.amazon.avod.media.error.MediaErrorCode;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadExecutionErrorCode implements MediaErrorCode {
    NOT_READY_TO_WATCH(0),
    MISSING_ASSET_ID(0),
    BAD_DRM_RECORD(0),
    MISSING_DRM_RECORD(0),
    MISSING_DASH_METADATA(0);

    private final int mNumber;

    DownloadExecutionErrorCode(int i) {
        this.mNumber = i;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public String getName() {
        return name();
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode
    public int getNumber() {
        return this.mNumber;
    }

    @Override // com.amazon.avod.media.error.MediaErrorCode, com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
