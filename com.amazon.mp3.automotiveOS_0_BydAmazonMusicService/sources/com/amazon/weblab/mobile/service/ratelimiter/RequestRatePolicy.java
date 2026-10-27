package com.amazon.weblab.mobile.service.ratelimiter;

import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.SessionInfo;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
interface RequestRatePolicy {
    boolean allowRequest(SessionInfo sessionInfo, CustomerInfo customerInfo, Collection<String> collection, RequestHistory requestHistory);

    int historySizeRequired();

    boolean isPolicyApplicable(RequestHistory requestHistory);
}
