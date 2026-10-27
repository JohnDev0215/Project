package com.amazon.avod.playback.smoothstream.diagnostics;

import com.google.common.collect.Maps;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class VideoFragmentQualityCounter {
    private final Map<Integer, Integer> mBitrateCountMap = Maps.newHashMap();
    private int mTotalSampleCount = 0;

    VideoFragmentQualityCounter() {
    }

    void addSample(int i) {
        if (this.mBitrateCountMap.containsKey(Integer.valueOf(i))) {
            this.mBitrateCountMap.put(Integer.valueOf(i), Integer.valueOf(this.mBitrateCountMap.get(Integer.valueOf(i)).intValue() + 1));
        } else {
            this.mBitrateCountMap.put(Integer.valueOf(i), 1);
        }
        this.mTotalSampleCount++;
    }

    int getSampleCountForBitrate(int i) {
        if (this.mBitrateCountMap.containsKey(Integer.valueOf(i))) {
            return this.mBitrateCountMap.get(Integer.valueOf(i)).intValue();
        }
        return 0;
    }

    int getTotalSampleCount() {
        return this.mTotalSampleCount;
    }
}
