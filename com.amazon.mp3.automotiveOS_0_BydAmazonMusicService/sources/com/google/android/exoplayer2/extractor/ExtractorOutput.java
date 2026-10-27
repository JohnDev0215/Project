package com.google.android.exoplayer2.extractor;

/* JADX INFO: loaded from: classes6.dex */
public interface ExtractorOutput {
    void endTracks();

    void seekMap(SeekMap seekMap);

    TrackOutput track(int i, int i2);
}
