package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.media.error.MediaErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public enum SyeMediaErrorCodeExtra implements MediaErrorCode {
    UDP_BLOCKED(0),
    FALLBACK_MIDSTREAM(0);

    private final int mNumber;

    SyeMediaErrorCodeExtra(int i) {
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
    public String toReportableString() {
        return name();
    }
}
