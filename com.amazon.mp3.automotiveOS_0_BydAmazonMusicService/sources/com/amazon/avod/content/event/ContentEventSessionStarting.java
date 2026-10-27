package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentEventSessionStarting extends ContentEventBase {
    private final ContentSessionContext mContext;
    private final ContentUrl mInitialUrl;
    private final PlaybackSessionProtocol mProtocol;

    public ContentEventSessionStarting(ContentSessionContext contentSessionContext, PlaybackSessionProtocol playbackSessionProtocol, ContentUrl contentUrl) {
        super(contentSessionContext.getContent(), contentSessionContext.getSessionType());
        this.mContext = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext);
        this.mProtocol = (PlaybackSessionProtocol) Preconditions.checkNotNull(playbackSessionProtocol);
        this.mInitialUrl = (ContentUrl) Preconditions.checkNotNull(contentUrl);
    }

    @Nonnull
    public ContentSessionContext getContext() {
        return this.mContext;
    }

    @Nonnull
    public ContentUrl getInitialUrl() {
        return this.mInitialUrl;
    }
}
