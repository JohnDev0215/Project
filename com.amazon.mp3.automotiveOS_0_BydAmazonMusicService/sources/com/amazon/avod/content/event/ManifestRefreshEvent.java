package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestRefreshEvent extends ContentEventBase {
    private final TimeSpan mEventTimeStamp;

    public ManifestRefreshEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull TimeSpan timeSpan) {
        super(playableContent, contentSessionType);
        this.mEventTimeStamp = (TimeSpan) Preconditions.checkNotNull(timeSpan, "eventTimeStamp");
    }

    @Nonnull
    public TimeSpan getEventTimeStamp() {
        return this.mEventTimeStamp;
    }
}
