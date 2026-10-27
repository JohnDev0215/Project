package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastResourceIFrame implements VastResource {
    private final URI mUri;

    public VastResourceIFrame(@Nonnull URI uri) {
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
