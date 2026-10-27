package com.amazon.music.nautilus;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
class MSOSScheduleCache {
    private static AtomicReference<MSOSSchedule> cachedSchedule = new AtomicReference<>();

    MSOSScheduleCache() {
    }

    void setCachedSchedule(MSOSSchedule mSOSSchedule) {
        cachedSchedule.set(mSOSSchedule);
    }
}
