package com.amazon.music.featuregating.configuration;

import com.amazon.music.platform.Outcome;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.facebook.react.bridge.BaseJavaModule;
import kotlin.Metadata;

/* JADX INFO: compiled from: AWSConfigProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/featuregating/configuration/AWSConfigProvider;", "", "openAttributes", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Attributes;", "openConfiguration", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Configuration;", BaseJavaModule.METHOD_TYPE_SYNC, "", "callback", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/ConfigurationSyncCallback;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface AWSConfigProvider {
    Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Attributes> openAttributes();

    Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Configuration> openConfiguration();

    void sync(ConfigurationSyncCallback callback);
}
