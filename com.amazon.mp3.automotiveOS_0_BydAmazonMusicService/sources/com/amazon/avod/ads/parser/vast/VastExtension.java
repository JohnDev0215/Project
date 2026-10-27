package com.amazon.avod.ads.parser.vast;

import com.amazon.avod.ads.api.Attribute;
import com.amazon.avod.ads.api.ElementNode;
import com.amazon.avod.ads.api.Node;
import com.amazon.avod.ads.api.NodeType;
import com.amazon.avod.ads.api.TextNode;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastExtension implements ElementNode {
    private final List<Attribute> mAttributes;
    private final List<Node> mChildren;
    private final String mContentType;
    private final String mExtensionType;
    private final VastTimeSpan mMinAdLength;
    private final String mName;
    private final VastExtensionSkipOffset mSkipOffset;
    private final TextNode mTextNode;
    private final String mTitle;
    private final String mXMLData;

    public VastExtension(@Nullable String str, @Nullable String str2, @Nullable VastTimeSpan vastTimeSpan, @Nullable VastExtensionSkipOffset vastExtensionSkipOffset, @Nullable String str3, @Nonnull String str4, @Nullable String str5) {
        this(str, str2, vastTimeSpan, vastExtensionSkipOffset, str3, str4, Lists.newArrayList(), Lists.newArrayList(), null, str5);
    }

    public VastExtension(@Nullable String str, @Nullable String str2, @Nullable VastTimeSpan vastTimeSpan, @Nullable VastExtensionSkipOffset vastExtensionSkipOffset, @Nullable String str3, @Nonnull String str4, @Nonnull List<Attribute> list, @Nonnull List<Node> list2, @Nullable TextNode textNode, @Nullable String str5) {
        this.mExtensionType = str;
        this.mXMLData = str2;
        this.mMinAdLength = vastTimeSpan;
        this.mSkipOffset = vastExtensionSkipOffset;
        this.mTitle = str3;
        this.mContentType = str5;
        this.mName = (String) Preconditions.checkNotNull(str4, "name");
        this.mAttributes = (List) Preconditions.checkNotNull(list, "attributes");
        this.mChildren = (List) Preconditions.checkNotNull(list2, "children");
        this.mTextNode = textNode;
    }

    @Nullable
    public String getExtensionType() {
        return this.mExtensionType;
    }

    @Nullable
    public String getContentType() {
        return this.mContentType;
    }

    @Nullable
    public VastTimeSpan getMinAdLength() {
        return this.mMinAdLength;
    }

    @Nullable
    public VastExtensionSkipOffset getSkipOffset() {
        return this.mSkipOffset;
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
}
