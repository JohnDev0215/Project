package com.amazon.music.arcus;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class RemoteConfigProvider {
    protected final ConfigurationManager configurationManager;
    protected final Map<String, Object> segmentAttributes;

    protected abstract ConfigurationManager getConfigurationManager(Context context, RemoteConfigRequest remoteConfigRequest);

    protected abstract Map<String, Object> getSegmentAttributes(RemoteConfigRequest remoteConfigRequest);

    public RemoteConfigProvider(Context context, RemoteConfigRequest remoteConfigRequest) {
        this.segmentAttributes = getSegmentAttributes(remoteConfigRequest);
        this.configurationManager = getConfigurationManager(context, remoteConfigRequest);
    }

    protected <T> T getRemoteValue(String str, Class<T> cls) throws Exception {
        return (T) this.configurationManager.getConfiguration(this.segmentAttributes, str, cls);
    }
}
