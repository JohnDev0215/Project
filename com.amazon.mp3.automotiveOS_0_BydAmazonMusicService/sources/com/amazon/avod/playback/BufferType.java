package com.amazon.avod.playback;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum BufferType implements MetricParameter {
    Video,
    Audio;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }

    @Nonnull
    public static BufferType forValue(@Nonnull String str) {
        Preconditions.checkNotNull(str, "value");
        BufferType bufferType = Video;
        return bufferType.toString().equalsIgnoreCase(str) ? bufferType : Audio;
    }
}
