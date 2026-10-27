package com.google.android.exoplayer2.extractor;

import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public interface Extractor {
    void init(ExtractorOutput extractorOutput);

    int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException;

    void release();

    void seek(long j, long j2);

    boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException;
}
