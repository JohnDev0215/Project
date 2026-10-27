package com.amazon.music.featuregating.configuration;

import android.content.Context;
import com.amazon.music.platform.Outcome;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import com.facebook.react.bridge.BaseJavaModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArcusManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ArcusManager;", "Lcom/amazon/music/featuregating/configuration/AWSConfigProvider;", "context", "Landroid/content/Context;", "configId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "remoteConfigurationManager", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/RemoteConfigurationManager;", "openAttributes", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Attributes;", "openConfiguration", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/Configuration;", BaseJavaModule.METHOD_TYPE_SYNC, "", "callback", "Lcom/amazonaws/mobileconnectors/remoteconfiguration/ConfigurationSyncCallback;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ArcusManager implements AWSConfigProvider {
    private final RemoteConfigurationManager remoteConfigurationManager;

    public ArcusManager(Context context, String configId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configId, "configId");
        RemoteConfigurationManager remoteConfigurationManagerCreateOrGet = RemoteConfigurationManager.forAppId(context, configId).createOrGet();
        Intrinsics.checkNotNullExpressionValue(remoteConfigurationManagerCreateOrGet, "forAppId(context, config…           .createOrGet()");
        this.remoteConfigurationManager = remoteConfigurationManagerCreateOrGet;
    }

    @Override // com.amazon.music.featuregating.configuration.AWSConfigProvider
    public Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Configuration> openConfiguration() {
        try {
            com.amazonaws.mobileconnectors.remoteconfiguration.Configuration configurationOpenConfiguration = this.remoteConfigurationManager.openConfiguration();
            if (configurationOpenConfiguration == null) {
                throw new NoSavedConfigurationFound();
            }
            return new Outcome.Success(configurationOpenConfiguration);
        } catch (Exception e) {
            return new Outcome.Failure(e);
        }
    }

    @Override // com.amazon.music.featuregating.configuration.AWSConfigProvider
    public Outcome<com.amazonaws.mobileconnectors.remoteconfiguration.Attributes> openAttributes() {
        try {
            com.amazonaws.mobileconnectors.remoteconfiguration.Attributes attributesOpenAttributes = this.remoteConfigurationManager.openAttributes();
            if (attributesOpenAttributes == null) {
                throw new RuntimeException("No Saved Attributes Found");
            }
            return new Outcome.Success(attributesOpenAttributes);
        } catch (Exception e) {
            return new Outcome.Failure(e);
        }
    }

    @Override // com.amazon.music.featuregating.configuration.AWSConfigProvider
    public void sync(ConfigurationSyncCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.remoteConfigurationManager.sync(callback);
    }
}
