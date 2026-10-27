package com.amazon.avod.content.dash.quality.heuristic;

import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface HeuristicsCallbacks {
    long availableBufferSize(int i);

    int averageBandwidthBps();

    long bufferedDuration(int i);

    boolean cancelDownload(int i, int i2, long j);

    int consumptionHead(int i);

    int fragmentBitrate(int i, int i2, int i3);

    int fragmentCount(int i);

    long fragmentDurationTime(int i, int i2);

    int fragmentQualityCount(int i, int i2);

    long fragmentSize(int i, int i2, int i3);

    long fragmentStartTime(int i, int i2);

    int highestQualityObtained(int i, int i2);

    long maxAvailableBufferDuration(int i);

    int missingFragment(int i, int i2);

    void overridePlaybackConfigsCallback(@Nonnull Map<String, String> map);

    long startDownload(int i, int i2, int i3, long j, long j2, FragmentType fragmentType);

    int streamBehavior(int i);

    int streamType(int i);
}
