package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.MarketplaceRequest;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CustomerRequest extends MarketplaceRequest {
    public final Optional<String> customerId;
    public final Optional<String> identityContext;

    @NotThreadSafe
    public static abstract class Builder extends MarketplaceRequest.Builder {
        public String customerId;
        public String identityContext;
    }

    protected CustomerRequest(Builder builder) {
        super(builder);
        this.identityContext = Optional.fromNullable(builder.identityContext);
        this.customerId = Optional.fromNullable(builder.customerId);
    }

    @Override // com.amazon.atv.parentalcontrols.MarketplaceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomerRequest)) {
            return false;
        }
        CustomerRequest customerRequest = (CustomerRequest) obj;
        return super.equals(obj) && Objects.equal(this.identityContext, customerRequest.identityContext) && Objects.equal(this.customerId, customerRequest.customerId);
    }

    @Override // com.amazon.atv.parentalcontrols.MarketplaceRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.identityContext, this.customerId);
    }

    @Override // com.amazon.atv.parentalcontrols.MarketplaceRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add("identityContext", this.identityContext).add("customerId", this.customerId).toString();
    }
}
