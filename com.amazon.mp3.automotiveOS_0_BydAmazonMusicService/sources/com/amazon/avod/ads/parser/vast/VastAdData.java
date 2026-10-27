package com.amazon.avod.ads.parser.vast;

import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VastAdData {
    @Nonnull
    VastAdSystem getAdSystem();

    List<URI> getErrors();

    @Nullable
    List<VastExtension> getExtensions();

    List<URI> getImpressions();
}
