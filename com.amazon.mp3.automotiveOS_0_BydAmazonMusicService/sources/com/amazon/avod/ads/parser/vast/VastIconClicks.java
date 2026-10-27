package com.amazon.avod.ads.parser.vast;

import java.net.URI;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastIconClicks {
    private final URI mIconClickThrough;
    private final List<URI> mIconClickTracking;

    public VastIconClicks(@Nullable List<URI> list, @Nullable URI uri) {
        this.mIconClickTracking = list;
        this.mIconClickThrough = uri;
    }
}
