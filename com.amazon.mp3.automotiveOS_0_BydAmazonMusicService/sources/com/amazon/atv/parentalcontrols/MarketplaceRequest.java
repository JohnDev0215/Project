package com.amazon.atv.parentalcontrols;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MarketplaceRequest {
    public final String marketplaceId;

    @NotThreadSafe
    public static abstract class Builder {
        public String marketplaceId;
    }

    protected MarketplaceRequest(Builder builder) {
        this.marketplaceId = (String) Preconditions.checkNotNull(builder.marketplaceId, "Unexpected null field: marketplaceId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MarketplaceRequest) {
            return Objects.equal(this.marketplaceId, ((MarketplaceRequest) obj).marketplaceId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.marketplaceId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("marketplaceId", this.marketplaceId).toString();
    }
}
