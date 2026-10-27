package com.dee.app.contacts.common.dagger.modules;

import android.content.Context;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import com.dee.app.contacts.common.utils.ContactsMetricsRecorder;
import com.dee.app.contacts.common.utils.NetworkUtils;
import com.dee.app.contacts.common.utils.PermissionsUtils;
import com.dee.app.contacts.common.utils.UserAgent;

/* JADX INFO: loaded from: classes5.dex */
public class ContactsCommonModule {
    public ContactsMetricsRecorder getContactsMetricsRecorder(AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        return new ContactsMetricsRecorder(alexaCommsCoreMetricsService);
    }

    public NetworkUtils getNetworkUtils(AlexaCommsCoreIdentityService alexaCommsCoreIdentityService) {
        return new NetworkUtils(alexaCommsCoreIdentityService);
    }

    public PermissionsUtils getPermissionsUtils(ActivityLifecycleUtil activityLifecycleUtil, Context context) {
        return new PermissionsUtils(activityLifecycleUtil, context);
    }

    public UserAgent getUserAgent(String str) {
        return new UserAgent(str);
    }
}
