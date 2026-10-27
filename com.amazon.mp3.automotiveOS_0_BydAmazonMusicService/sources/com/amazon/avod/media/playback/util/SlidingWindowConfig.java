package com.amazon.avod.media.playback.util;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public interface SlidingWindowConfig {
    TimeSpan getSlidingWindowLength();

    int getSlidingWindowThreshold();
}
