package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventBufferFullnessChanged extends ContentEventBase {
    final long mBufferFullnessInNanoseconds;
    final StreamIndex mStream;

    public ContentEventBufferFullnessChanged(PlayableContent playableContent, ContentSessionType contentSessionType, long j, StreamIndex streamIndex) {
        super(playableContent, contentSessionType);
        this.mBufferFullnessInNanoseconds = j;
        this.mStream = streamIndex;
    }

    public long getBufferFullnessInNanos() {
        return this.mBufferFullnessInNanoseconds;
    }

    public StreamIndex getStream() {
        return this.mStream;
    }
}
