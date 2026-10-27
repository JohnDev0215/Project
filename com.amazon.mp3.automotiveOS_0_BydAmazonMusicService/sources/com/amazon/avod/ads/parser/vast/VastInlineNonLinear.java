package com.amazon.avod.ads.parser.vast;

import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastInlineNonLinear extends VastWrapperNonLinear {
    private final VastAdParameters mAdParameters;
    private final URI mNonLinearClickThrough;
    private final VastResource mResource;

    public VastInlineNonLinear(@Nullable String str, @Nonnull Integer num, @Nonnull Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable VastTimeSpan vastTimeSpan, @Nullable String str2, @Nullable List<URI> list, @Nullable List<VastTracking> list2, @Nullable VastResource vastResource, @Nullable URI uri, @Nullable VastAdParameters vastAdParameters) {
        super(str, num, num2, num3, num4, bool, bool2, vastTimeSpan, str2, list, list2);
        this.mResource = vastResource;
        this.mNonLinearClickThrough = uri;
        this.mAdParameters = vastAdParameters;
    }

    @Nullable
    public VastResource getResource() {
        return this.mResource;
    }
}
