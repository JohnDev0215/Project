package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.GeoCustomerRequest;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class GeoCustomerWithClientDetailsRequest extends GeoCustomerRequest {
    public final ClientDetails client;

    @NotThreadSafe
    public static abstract class Builder extends GeoCustomerRequest.Builder {
        public ClientDetails client;
    }

    protected GeoCustomerWithClientDetailsRequest(Builder builder) {
        super(builder);
        this.client = (ClientDetails) Preconditions.checkNotNull(builder.client, "Unexpected null field: client");
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GeoCustomerWithClientDetailsRequest) {
            return super.equals(obj) && Objects.equal(this.client, ((GeoCustomerWithClientDetailsRequest) obj).client);
        }
        return false;
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.client);
    }

    @Override // com.amazon.atv.parentalcontrols.GeoCustomerRequest
    public String toString() {
        return MoreObjects.toStringHelper(this).add(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, this.client).toString();
    }
}
