package com.amazon.atv.parentalcontrols;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class GeoCustomerRequest {
    public final String geoToken;

    @NotThreadSafe
    public static abstract class Builder {
        public String geoToken;
    }

    protected GeoCustomerRequest(Builder builder) {
        this.geoToken = (String) Preconditions.checkNotNull(builder.geoToken, "Unexpected null field: geoToken");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GeoCustomerRequest) {
            return Objects.equal(this.geoToken, ((GeoCustomerRequest) obj).geoToken);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.geoToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("geoToken", this.geoToken).toString();
    }
}
