package com.amazon.avod.smoothstream.dash;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EssentialProperty {
    private final String mSchemeIdUri;
    private final String mValue;

    public EssentialProperty(@Nonnull String str, @Nonnull String str2) {
        this.mSchemeIdUri = (String) Preconditions.checkNotNull(str, "schemeIdUri");
        this.mValue = (String) Preconditions.checkNotNull(str2, "value");
    }

    @Nonnull
    public String getValue() {
        return this.mValue;
    }
}
