package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.CustomerRequest;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CustomerClientRequest extends CustomerRequest {
    public final Client client;

    @NotThreadSafe
    public static abstract class Builder extends CustomerRequest.Builder {
        public Client client;
    }

    protected CustomerClientRequest(Builder builder) {
        super(builder);
        this.client = (Client) Preconditions.checkNotNull(builder.client, "Unexpected null field: client");
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CustomerClientRequest) {
            return super.equals(obj) && Objects.equal(this.client, ((CustomerClientRequest) obj).client);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.client);
    }

    @Override // com.amazon.atv.parentalcontrols.CustomerRequest, com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, this.client).toString();
    }
}
