package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastResourceStatic implements VastResource {
    private final String mCreativeType;
    private final URI mUri;

    public VastResourceStatic(@Nonnull String str, @Nonnull URI uri) {
        this.mCreativeType = (String) Preconditions.checkNotNull(str, "creativeType");
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public String getCreativeType() {
        return this.mCreativeType;
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
