package com.amazon.music.media.playback.concurrency;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ConcurrentDeviceInfo {
    private final String name;
    private final long timeStreamingBegan;

    public ConcurrentDeviceInfo(String str, long j) {
        this.name = StringUtils.nullToEmpty(str);
        this.timeStreamingBegan = j;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public long getTimeStreamingBegan() {
        return this.timeStreamingBegan;
    }

    public String toString() {
        return "ConcurrentDeviceInfo{name='" + this.name + "', timeStreamingBegan=" + this.timeStreamingBegan + '}';
    }
}
