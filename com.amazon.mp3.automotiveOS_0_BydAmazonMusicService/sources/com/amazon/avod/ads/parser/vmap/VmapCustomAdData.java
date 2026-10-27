package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapCustomAdData {
    private final String mAdData;
    private final String mTemplateType;

    public VmapCustomAdData(@Nonnull String str, @Nullable String str2) {
        this.mTemplateType = (String) Preconditions.checkNotNull(str, "templateType");
        this.mAdData = str2;
    }
}
