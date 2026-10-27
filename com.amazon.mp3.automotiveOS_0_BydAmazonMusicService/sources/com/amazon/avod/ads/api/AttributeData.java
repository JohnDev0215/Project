package com.amazon.avod.ads.api;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AttributeData implements Attribute {
    private final String mName;
    private final String mValue;

    public AttributeData(@Nonnull String str, @Nonnull String str2) {
        this.mName = (String) Preconditions.checkNotNull(str, "name");
        this.mValue = (String) Preconditions.checkNotNull(str2, "value");
    }

    @Override // com.amazon.avod.ads.api.Attribute
    @Nonnull
    public String getName() {
        return this.mName;
    }

    @Override // com.amazon.avod.ads.api.Attribute
    @Nonnull
    public String getValue() {
        return this.mValue;
    }

    @Override // com.amazon.avod.ads.api.Node
    public NodeType getNodeType() {
        return NodeType.Attribute;
    }
}
