package com.amazon.avod.media.download.error;

import com.amazon.avod.media.error.MediaErrorCode;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadErrorCode implements MediaErrorCode {
    DISK_FULL(0),
    INTERNAL_DISK_FULL(4067),
    EXTERNAL_DISK_FULL(4068),
    INTERNAL_STORAGE_NOT_WRITABLE(4072),
    EXTERNAL_STORAGE_NOT_WRITABLE(4073),
    NO_SERVER_ENTITLEMENTS(0),
    AV_MARKETPLACE_CHANGE(0);

    private final int mNumber;

    DownloadErrorCode(int i) {
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
