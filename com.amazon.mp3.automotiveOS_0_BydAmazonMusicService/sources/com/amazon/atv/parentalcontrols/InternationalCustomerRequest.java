package com.amazon.atv.parentalcontrols;

import com.amazon.atv.parentalcontrols.InternationalRequest;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternationalCustomerRequest extends InternationalRequest {

    @NotThreadSafe
    public static abstract class Builder extends InternationalRequest.Builder {
    }

    protected InternationalCustomerRequest(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InternationalCustomerRequest)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.parentalcontrols.InternationalRequest
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}
