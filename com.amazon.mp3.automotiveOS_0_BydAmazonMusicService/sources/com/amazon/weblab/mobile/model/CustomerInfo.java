package com.amazon.weblab.mobile.model;

/* JADX INFO: loaded from: classes5.dex */
public class CustomerInfo {
    private volatile String mDirectedId;

    public CustomerInfo(String str) {
        setDirectedId(str);
    }

    public String getDirectedId() {
        return this.mDirectedId;
    }

    public void setDirectedId(String str) {
        if (str == null || str.isEmpty()) {
            this.mDirectedId = null;
        } else {
            this.mDirectedId = str;
        }
    }

    public int hashCode() {
        return 31 + (this.mDirectedId == null ? 0 : this.mDirectedId.hashCode());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerInfo)) {
            return false;
        }
        CustomerInfo customerInfo = (CustomerInfo) obj;
        if (this.mDirectedId == null) {
            return customerInfo.mDirectedId == null;
        }
        return this.mDirectedId.equals(customerInfo.mDirectedId);
    }
}
