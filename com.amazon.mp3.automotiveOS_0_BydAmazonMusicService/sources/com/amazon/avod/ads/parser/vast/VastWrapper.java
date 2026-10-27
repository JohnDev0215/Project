package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastWrapper implements VastAdData {
    private final VastAdSystem mAdSystem;
    private final List<VastWrapperCreative> mCreatives;
    private final List<URI> mErrors;
    private final List<VastExtension> mExtensions;
    private final List<URI> mImpressions;
    private final URI mVASTAdTagURI;

    public VastWrapper(@Nonnull VastAdSystem vastAdSystem, @Nonnull URI uri, @Nullable List<URI> list, @Nonnull List<URI> list2, @Nullable List<VastWrapperCreative> list3, @Nullable List<VastExtension> list4) {
        this.mAdSystem = (VastAdSystem) Preconditions.checkNotNull(vastAdSystem, "adSystem");
        this.mVASTAdTagURI = (URI) Preconditions.checkNotNull(uri, "VASTAdTagURI");
        this.mErrors = list;
        this.mImpressions = (List) Preconditions.checkNotNull(list2, "impressions");
        this.mCreatives = list3;
        this.mExtensions = list4;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    @Nonnull
    public VastAdSystem getAdSystem() {
        return this.mAdSystem;
    }

    @Nonnull
    public URI getVASTAdTagURI() {
        return this.mVASTAdTagURI;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    @Nullable
    public List<URI> getErrors() {
        return this.mErrors;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    @Nonnull
    public List<URI> getImpressions() {
        return this.mImpressions;
    }

    @Nullable
    public List<VastWrapperCreative> getCreatives() {
        return this.mCreatives;
    }

    @Override // com.amazon.avod.ads.parser.vast.VastAdData
    public List<VastExtension> getExtensions() {
        return this.mExtensions;
    }
}
