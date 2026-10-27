package com.amazon.avod.drm.event;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseLicenseAcquisitionEvent {
    private final TimeSpan mTime = TimeSpan.now();

    public TimeSpan getTime() {
        return this.mTime;
    }
}
