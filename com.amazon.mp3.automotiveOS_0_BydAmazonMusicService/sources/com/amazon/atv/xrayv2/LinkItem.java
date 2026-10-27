package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Item;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LinkItem extends Item {
    public final NavigationalAction link;
    public final boolean selected;

    @NotThreadSafe
    public static abstract class Builder extends Item.Builder {
        public NavigationalAction link;
        public boolean selected;
    }

    protected LinkItem(Builder builder) {
        super(builder);
        this.link = (NavigationalAction) Preconditions.checkNotNull(builder.link, "Unexpected null field: link");
        this.selected = builder.selected;
    }

    @Override // com.amazon.atv.xrayv2.Item
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinkItem)) {
            return false;
        }
        LinkItem linkItem = (LinkItem) obj;
        return super.equals(obj) && Objects.equal(this.link, linkItem.link) && Objects.equal(Boolean.valueOf(this.selected), Boolean.valueOf(linkItem.selected));
    }

    @Override // com.amazon.atv.xrayv2.Item
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.link, Boolean.valueOf(this.selected));
    }

    @Override // com.amazon.atv.xrayv2.Item
    public String toString() {
        return MoreObjects.toStringHelper(this).add("link", this.link).add("selected", this.selected).toString();
    }
}
