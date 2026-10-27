package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VmapTracking {
    private final VmapTrackingEventType mEventType;
    private final URI mUri;

    public VmapTracking(@Nonnull VmapTrackingEventType vmapTrackingEventType, @Nonnull URI uri) {
        this.mEventType = (VmapTrackingEventType) Preconditions.checkNotNull(vmapTrackingEventType, "eventType");
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public VmapTrackingEventType getEventType() {
        return this.mEventType;
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
