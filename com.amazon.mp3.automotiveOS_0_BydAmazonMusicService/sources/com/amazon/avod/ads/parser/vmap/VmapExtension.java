package com.amazon.avod.ads.parser.vmap;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapExtension {
    private final String mExtensionType;
    private final String mXMLData;

    public VmapExtension(@Nonnull String str, @Nullable String str2) {
        this.mExtensionType = (String) Preconditions.checkNotNull(str, "extensionType");
        this.mXMLData = str2;
    }
}
