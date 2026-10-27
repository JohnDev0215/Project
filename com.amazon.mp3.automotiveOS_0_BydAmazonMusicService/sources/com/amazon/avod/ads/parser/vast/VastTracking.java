package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastTracking {
    private final VastTrackingEventType mEventType;
    private final VastOffset mOffset;
    private final URI mUri;

    public VastTracking(@Nonnull VastTrackingEventType vastTrackingEventType, @Nullable VastOffset vastOffset, @Nonnull URI uri) {
        this.mEventType = vastTrackingEventType;
        this.mOffset = vastOffset;
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public VastTrackingEventType getEventType() {
        return this.mEventType;
    }

    @Nullable
    public VastOffset getOffset() {
        return this.mOffset;
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
