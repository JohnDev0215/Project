package com.amazon.avod.qos.reporter;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public interface SmoothStreamingReporter {
    void reportBufferFullnessInNanos(long j);

    void reportFragment(int i, int i2, int i3, long j, long j2, TimeSpan timeSpan, String str);

    void reportSessionEnd(TimeSpan timeSpan);

    void setMaxBufferFullnessMillis(long j);
}
