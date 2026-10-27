package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Widget;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CollectionBase extends Widget {

    @NotThreadSafe
    public static abstract class Builder extends Widget.Builder {
    }

    protected CollectionBase(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionBase)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}
