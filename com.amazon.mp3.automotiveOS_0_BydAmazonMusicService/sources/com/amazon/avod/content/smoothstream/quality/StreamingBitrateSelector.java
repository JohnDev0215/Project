package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public interface StreamingBitrateSelector {
    FragmentDownloadParameters getDownloadParameters(SmoothStreamingURI smoothStreamingURI, int i);

    void updateBandwidth(int i, int i2);

    void updateBufferSizeInNanos(long j);

    void updateStreamState(StreamingState streamingState);
}
