package com.amazon.avod.ads.parser.vmap;

import com.amazon.avod.ads.parser.vast.VastDocument;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapVastAdData {
    private final VastDocument mVastDocument;

    public VmapVastAdData(@Nullable VastDocument vastDocument) {
        this.mVastDocument = vastDocument;
    }

    @Nullable
    public VastDocument getVastDocument() {
        return this.mVastDocument;
    }
}
