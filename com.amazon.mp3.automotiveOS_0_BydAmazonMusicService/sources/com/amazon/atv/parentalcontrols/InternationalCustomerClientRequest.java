package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.InternationalCustomerRequest;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternationalCustomerClientRequest extends InternationalCustomerRequest {
    public final Client client;

    @NotThreadSafe
    public static abstract class Builder extends InternationalCustomerRequest.Builder {
        public Client client;
    }

    protected InternationalCustomerClientRequest(Builder builder) {
        super(builder);
        this.client = (Client) Preconditions.checkNotNull(builder.client, "Unexpected null field: client");
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternationalCustomerClientRequest) {
            return super.equals(obj) && Objects.equal(this.client, ((InternationalCustomerClientRequest) obj).client);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest, com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.client);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, this.client).toString();
    }
}
