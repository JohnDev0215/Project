package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentEventBase {
    private final PlayableContent mContent;
    private final long mEpochTimeInNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    private final ContentSessionType mSessionType;

    public ContentEventBase(PlayableContent playableContent, ContentSessionType contentSessionType) {
        this.mContent = playableContent;
        this.mSessionType = contentSessionType;
    }

    public PlayableContent getContent() {
        return this.mContent;
    }

    public String getTitleId() {
        return this.mContent.getTitleId();
    }

    public long getEpochTimeInNanos() {
        return this.mEpochTimeInNanos;
    }

    public ContentSessionType getSessionType() {
        return this.mSessionType;
    }
}
