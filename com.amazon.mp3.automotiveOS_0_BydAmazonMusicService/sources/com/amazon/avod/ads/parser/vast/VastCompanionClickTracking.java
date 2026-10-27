package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastCompanionClickTracking {
    private final int mId;
    private final URI mUri;

    public VastCompanionClickTracking(int i, @Nonnull URI uri) {
        this.mId = i;
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    public int getId() {
        return this.mId;
    }
}
