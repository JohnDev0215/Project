package com.amazon.avod.ads.api;

import com.amazon.avod.ads.parser.vast.VastResource;
import com.amazon.avod.ads.parser.vast.VastResourceHTML;
import com.amazon.avod.ads.parser.vast.VastResourceIFrame;
import com.amazon.avod.ads.parser.vast.VastResourceStatic;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class Resource {
    private final String mContentType;
    private final String mResourceContent;
    private final ResourceType mType;

    public Resource(VastResource vastResource) {
        if (vastResource instanceof VastResourceStatic) {
            VastResourceStatic vastResourceStatic = (VastResourceStatic) vastResource;
            this.mType = ResourceType.STATIC_URL;
            this.mContentType = vastResourceStatic.getCreativeType();
            this.mResourceContent = vastResourceStatic.getUri().toString();
        } else if (vastResource instanceof VastResourceIFrame) {
            this.mType = ResourceType.IFRAME_URL;
            this.mContentType = "text/html";
            this.mResourceContent = ((VastResourceIFrame) vastResource).getUri().toString();
        } else if (vastResource instanceof VastResourceHTML) {
            this.mType = ResourceType.HTML;
            this.mContentType = "text/html";
            this.mResourceContent = ((VastResourceHTML) vastResource).getHTML();
        } else {
            this.mType = null;
            this.mContentType = null;
            this.mResourceContent = null;
        }
        Preconditions.checkNotNull(this.mType);
        Preconditions.checkNotNull(this.mContentType);
        Preconditions.checkNotNull(this.mResourceContent);
    }

    @Nonnull
    public ResourceType getResourceType() {
        return this.mType;
    }

    @Nonnull
    public String getContentType() {
        return this.mContentType;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Resource Type", getResourceType()).add("Content Type", getContentType()).toString();
    }
}
