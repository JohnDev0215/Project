package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSVideoPerformance {
    int AverageDecodeTime();

    int AverageRenderTime();

    int CPULoad();

    int CodecDropNum();

    int[] CodecErrors();

    int CodecErrorsNum();

    int CodecTimeNum();

    int DecodedNum();

    int Frequency();

    int JitterNum();

    int LastTime();

    int MaxFrequency();

    int RenderDropNum();

    int RenderNum();

    int RenderTimeNum();

    int SourceDropNum();

    int SourceTimeNum();

    int TotalCPULoad();

    int TotalCodecDropNum();

    int TotalDecodedNum();

    int TotalPlaybackDuration();

    int TotalRenderDropNum();

    int TotalRenderNum();

    int TotalSourceDropNum();

    int WorstDecodeTime();

    int WorstRenderTime();
}
