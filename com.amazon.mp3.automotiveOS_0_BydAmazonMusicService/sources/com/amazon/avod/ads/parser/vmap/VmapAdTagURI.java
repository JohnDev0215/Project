package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdTagURI {
    private final VmapAdTagURITemplateType mTemplateType;
    private final URI mUri;

    public VmapAdTagURI(@Nonnull VmapAdTagURITemplateType vmapAdTagURITemplateType, @Nonnull URI uri) {
        this.mTemplateType = (VmapAdTagURITemplateType) Preconditions.checkNotNull(vmapAdTagURITemplateType, "templateType");
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
