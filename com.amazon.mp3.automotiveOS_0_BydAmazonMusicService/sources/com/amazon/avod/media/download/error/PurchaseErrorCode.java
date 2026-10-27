package com.amazon.avod.media.download.error;

import com.amazon.avod.media.error.MediaErrorCode;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PurchaseErrorCode implements MediaErrorCode {
    PURCHASE_CHARGE_ERROR(0),
    GENERIC_PURCHASE_ERROR(0);

    private final int mNumber;

    PurchaseErrorCode(int i) {
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
