package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastVideoClickLink {
    private final String mId;
    private final URI mUri;

    public VastVideoClickLink(@Nullable String str, @Nonnull URI uri) {
        this.mId = str;
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
