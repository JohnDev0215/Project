package com.amazon.music.arcus;

import android.content.Context;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class RemoteConfigurationManagerFactory {
    RemoteConfigurationManagerFactory() {
    }

    public RemoteConfigurationManager get(String str) {
        return RemoteConfigurationManager.getInstance(str);
    }

    public RemoteConfigurationManager createAndGet(Context context, String str) {
        Validate.notNull(context);
        Validate.notNull(str);
        return RemoteConfigurationManager.forAppId(context, str).createOrGet();
    }
}
