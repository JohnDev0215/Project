package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventStartDownloading extends ContentEventBase {
    private final long mTotalNeededSizeInBytes;

    public ContentEventStartDownloading(PlayableContent playableContent, ContentSessionType contentSessionType, long j) {
        super(playableContent, contentSessionType);
        this.mTotalNeededSizeInBytes = j;
    }

    public long getTotalNeededSizeInBytes() {
        return this.mTotalNeededSizeInBytes;
    }
}
