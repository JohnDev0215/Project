package com.amazon.avod.ads.api;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ElementNodeData implements ElementNode {
    private final List<Attribute> mAttributes;
    private final List<Node> mChildren;
    private final String mName;
    private TextNode mTextNode;

    public ElementNodeData(@Nonnull String str) {
        this(str, Lists.newArrayList(), Lists.newArrayList(), null);
    }

    public ElementNodeData(@Nonnull String str, @Nonnull List<Attribute> list, @Nonnull List<Node> list2, @Nullable TextNode textNode) {
        this.mName = (String) Preconditions.checkNotNull(str, "name");
        this.mAttributes = (List) Preconditions.checkNotNull(list, "attributes");
        this.mChildren = (List) Preconditions.checkNotNull(list2, "children");
        this.mTextNode = textNode;
    }

    @Override // com.amazon.avod.ads.api.ElementNode
    @Nonnull
    public String getName() {
        return this.mName;
    }

    @Override // com.amazon.avod.ads.api.ElementNode
    @Nonnull
    public List<Attribute> getAttributes() {
        return this.mAttributes;
    }

    @Override // com.amazon.avod.ads.api.ElementNode
    @Nonnull
    public List<Node> getChildren() {
        return this.mChildren;
    }

    @Override // com.amazon.avod.ads.api.ElementNode
    @Nullable
    public TextNode getTextNode() {
        return this.mTextNode;
    }

    @Override // com.amazon.avod.ads.api.Node
    public NodeType getNodeType() {
        return NodeType.Element;
    }

    public void setTextNode(@Nonnull TextNode textNode) {
        this.mTextNode = (TextNode) Preconditions.checkNotNull(textNode, "textNode");
    }
}
