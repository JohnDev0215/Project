package com.dee.app.contacts.common.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes5.dex */
public interface ContactsCoreComponent {
    ActivityLifecycleUtil getActivityLifecycleUtil();

    String getApplicationName();

    Context getContext();

    String getDeviceId();

    AlexaCommsCoreIdentityService getIdentityService();

    AlexaCommsCoreMetricsService getMetricsService();

    INetworkingClient getNetworkClient();

    ObjectMapper getObjectMapper();

    SharedPreferences getSharedPreferences();
}
