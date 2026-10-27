package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapDocument {
    private final List<VmapAdBreak> mAdBreaks;
    private final List<VmapExtension> mExtensions;
    private final String mVersion;

    public VmapDocument(@Nonnull String str, @Nullable List<VmapAdBreak> list, @Nullable List<VmapExtension> list2) {
        this.mVersion = (String) Preconditions.checkNotNull(str, "version");
        this.mAdBreaks = list;
        this.mExtensions = list2;
    }

    @Nullable
    public List<VmapAdBreak> getAdBreaks() {
        return this.mAdBreaks;
    }
}
