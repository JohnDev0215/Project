package com.amazon.avod.content.smoothstream.quality;

/* JADX INFO: loaded from: classes2.dex */
public interface StreamingBitrateSelectionComponentFactory {
    StreamingBitrateSelectionComponentSet newAudioComponentSet(boolean z);

    StreamingBitrateSelectionComponentSet newDownloadAudioComponentSet(boolean z);

    StreamingBitrateSelectionComponentSet newDownloadVideoComponentSet(boolean z);

    StreamingBitrateSelectionComponentSet newVideoComponentSet(boolean z);
}
