package com.amazon.avod.qos.reporter.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingNoOpReporter implements SmoothStreamingReporter {
    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportBufferFullnessInNanos(long j) {
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportFragment(int i, int i2, int i3, long j, long j2, TimeSpan timeSpan, @Nullable String str) {
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void reportSessionEnd(TimeSpan timeSpan) {
    }

    @Override // com.amazon.avod.qos.reporter.SmoothStreamingReporter
    public void setMaxBufferFullnessMillis(long j) {
    }
}
