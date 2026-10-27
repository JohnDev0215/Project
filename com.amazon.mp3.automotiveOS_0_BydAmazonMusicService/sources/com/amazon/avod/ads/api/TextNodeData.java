package com.amazon.avod.ads.api;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TextNodeData implements TextNode {
    private final String mValue;

    public TextNodeData(@Nonnull String str) {
        this.mValue = (String) Preconditions.checkNotNull(str, "value");
    }

    @Override // com.amazon.avod.ads.api.TextNode
    @Nonnull
    public String getValue() {
        return this.mValue;
    }

    @Override // com.amazon.avod.ads.api.Node
    public NodeType getNodeType() {
        return NodeType.Text;
    }
}
