package com.amazon.atv.purchase;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseRequest {
    public final Optional<String> associateTag;
    public final Optional<String> authSupportedModel;
    public final Optional<String> clientAppVersion;
    public final Optional<String> clientIP;
    public final Optional<String> firmware;
    public final boolean multiFactorAuthSupportedInApp;
    public final Optional<String> purchaseChannel;
    public final Optional<String> testAuthorization;
    public final Optional<String> ubid;

    @NotThreadSafe
    public static class Builder {
        public String associateTag;
        public String authSupportedModel;
        public String clientAppVersion;
        public String clientIP;
        public String firmware;
        public boolean multiFactorAuthSupportedInApp;
        public String purchaseChannel;
        public String testAuthorization;
        public String ubid;
    }

    protected PurchaseRequest(Builder builder) {
        this.purchaseChannel = Optional.fromNullable(builder.purchaseChannel);
        this.clientIP = Optional.fromNullable(builder.clientIP);
        this.ubid = Optional.fromNullable(builder.ubid);
        this.authSupportedModel = Optional.fromNullable(builder.authSupportedModel);
        this.firmware = Optional.fromNullable(builder.firmware);
        this.multiFactorAuthSupportedInApp = builder.multiFactorAuthSupportedInApp;
        this.associateTag = Optional.fromNullable(builder.associateTag);
        this.testAuthorization = Optional.fromNullable(builder.testAuthorization);
        this.clientAppVersion = Optional.fromNullable(builder.clientAppVersion);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PurchaseRequest)) {
            return false;
        }
        PurchaseRequest purchaseRequest = (PurchaseRequest) obj;
        return Objects.equal(this.purchaseChannel, purchaseRequest.purchaseChannel) && Objects.equal(this.clientIP, purchaseRequest.clientIP) && Objects.equal(this.ubid, purchaseRequest.ubid) && Objects.equal(this.authSupportedModel, purchaseRequest.authSupportedModel) && Objects.equal(this.firmware, purchaseRequest.firmware) && Objects.equal(Boolean.valueOf(this.multiFactorAuthSupportedInApp), Boolean.valueOf(purchaseRequest.multiFactorAuthSupportedInApp)) && Objects.equal(this.associateTag, purchaseRequest.associateTag) && Objects.equal(this.testAuthorization, purchaseRequest.testAuthorization) && Objects.equal(this.clientAppVersion, purchaseRequest.clientAppVersion);
    }

    public int hashCode() {
        return Objects.hashCode(this.purchaseChannel, this.clientIP, this.ubid, this.authSupportedModel, this.firmware, Boolean.valueOf(this.multiFactorAuthSupportedInApp), this.associateTag, this.testAuthorization, this.clientAppVersion);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("purchaseChannel", this.purchaseChannel).add("clientIP", this.clientIP).add("ubid", this.ubid).add("authSupportedModel", this.authSupportedModel).add("firmware", this.firmware).add("multiFactorAuthSupportedInApp", this.multiFactorAuthSupportedInApp).add("associateTag", this.associateTag).add("testAuthorization", this.testAuthorization).add("clientAppVersion", this.clientAppVersion).toString();
    }
}
