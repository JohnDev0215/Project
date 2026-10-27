package com.amazon.avod.ads.api;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ElementNode extends Node {
    @Nonnull
    List<Attribute> getAttributes();

    @Nonnull
    List<Node> getChildren();

    @Nonnull
    String getName();

    @Nullable
    TextNode getTextNode();
}
