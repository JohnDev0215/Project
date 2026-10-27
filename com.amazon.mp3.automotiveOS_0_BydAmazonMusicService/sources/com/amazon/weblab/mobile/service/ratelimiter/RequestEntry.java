package com.amazon.weblab.mobile.service.ratelimiter;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.service.ServiceCallStatus;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
class RequestEntry {
    private final CustomerInfo mCustomerInfo;
    private final long mElapsedRealtime;
    private final ServiceCallStatus mServiceCallStatus;
    private final SessionInfo mSessionInfo;
    private final Collection<String> mWeblabs;

    RequestEntry(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection, ServiceCallStatus serviceCallStatus, long j) {
        this.mSessionInfo = sessionInfo;
        this.mCustomerInfo = customerInfo;
        this.mWeblabs = collection;
        this.mServiceCallStatus = serviceCallStatus;
        this.mElapsedRealtime = j;
    }

    public ServiceCallStatus getServiceCallStatus() {
        return this.mServiceCallStatus;
    }

    public long getElapsedRealtime() {
        return this.mElapsedRealtime;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestEntry requestEntry = (RequestEntry) obj;
        return this.mElapsedRealtime == requestEntry.mElapsedRealtime && this.mSessionInfo.equals(requestEntry.mSessionInfo) && this.mCustomerInfo.equals(requestEntry.mCustomerInfo) && this.mWeblabs.equals(requestEntry.mWeblabs) && this.mServiceCallStatus == requestEntry.mServiceCallStatus;
    }

    public int hashCode() {
        int iHashCode = ((((((this.mSessionInfo.hashCode() * 31) + this.mCustomerInfo.hashCode()) * 31) + this.mWeblabs.hashCode()) * 31) + this.mServiceCallStatus.hashCode()) * 31;
        long j = this.mElapsedRealtime;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }
}
