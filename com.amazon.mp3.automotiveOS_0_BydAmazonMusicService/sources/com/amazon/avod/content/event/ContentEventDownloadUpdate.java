package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.playback.sampling.SampleType;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventDownloadUpdate extends ContentEventBase {
    private final SampleType mSampleType;

    public ContentEventDownloadUpdate(PlayableContent playableContent, ContentSessionType contentSessionType, SampleType sampleType) {
        super(playableContent, contentSessionType);
        this.mSampleType = sampleType;
    }

    public SampleType getSampleType() {
        return this.mSampleType;
    }
}
