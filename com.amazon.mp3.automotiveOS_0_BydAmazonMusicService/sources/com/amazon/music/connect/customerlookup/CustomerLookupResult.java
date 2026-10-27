package com.amazon.music.connect.customerlookup;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerLookupResult.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "", "other", "", "isSameResult", "", "toString", "", "hashCode", "equals", "customerPublicName", "Ljava/lang/String;", "getCustomerPublicName", "()Ljava/lang/String;", "avatarUrl", "getAvatarUrl", "profileId", "getProfileId", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CustomerLookupResult {

    @SerializedName("image_url")
    private final String avatarUrl;

    @SerializedName("name")
    private final String customerPublicName;

    @SerializedName("profile_id")
    private final String profileId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerLookupResult)) {
            return false;
        }
        CustomerLookupResult customerLookupResult = (CustomerLookupResult) other;
        return Intrinsics.areEqual(this.customerPublicName, customerLookupResult.customerPublicName) && Intrinsics.areEqual(this.avatarUrl, customerLookupResult.avatarUrl) && Intrinsics.areEqual(this.profileId, customerLookupResult.profileId);
    }

    public int hashCode() {
        return (((this.customerPublicName.hashCode() * 31) + this.avatarUrl.hashCode()) * 31) + this.profileId.hashCode();
    }

    public String toString() {
        return "CustomerLookupResult(customerPublicName=" + this.customerPublicName + ", avatarUrl=" + this.avatarUrl + ", profileId=" + this.profileId + ')';
    }

    public final String getCustomerPublicName() {
        return this.customerPublicName;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final boolean isSameResult(Object other) {
        if (other instanceof CustomerLookupResult) {
            return this.profileId.equals(((CustomerLookupResult) other).profileId);
        }
        return super.equals(other);
    }
}
