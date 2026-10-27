package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.service.ratelimiter.RequestsRateLimiter;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.IMobileWeblabRuntimeConfiguration;

/* JADX INFO: loaded from: classes5.dex */
public class ServiceProxyFactory {
    public static IServiceProxy createServiceProxy(ITriggerRequestListener iTriggerRequestListener, IMobileWeblabClientAttributes iMobileWeblabClientAttributes, IMobileWeblabRuntimeConfiguration iMobileWeblabRuntimeConfiguration) {
        return new RequestsRateLimiter(new ServiceProxy(iMobileWeblabClientAttributes, iMobileWeblabRuntimeConfiguration, iTriggerRequestListener), iMobileWeblabClientAttributes.getIdentifier());
    }
}
