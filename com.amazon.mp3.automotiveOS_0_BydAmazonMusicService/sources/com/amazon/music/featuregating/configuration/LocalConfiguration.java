package com.amazon.music.featuregating.configuration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: LocalConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u001a\u001a\u00020\b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J@\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e\"\b\b\u0000\u0010\u0007*\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\fH\u0016R\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R(\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0013j\u0004\u0018\u0001`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/featuregating/configuration/LocalConfiguration;", "Lcom/amazon/music/featuregating/configuration/ConfigurationSource;", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "getMetaConfiguration", "", "reload", "", "T", "", "key", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "getConfigurationItemSync", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/configuration/ConfigurationLogger;", "loggerInDebugBuild", "Lkotlin/jvm/functions/Function1;", "Lcom/amazon/music/featuregating/configuration/ConfigurationReader;", "configurationReader", "Lcom/amazon/music/featuregating/configuration/ConfigurationReader;", "data", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "configurationMetrics", "<init>", "(Ljava/lang/String;Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class LocalConfiguration implements ConfigurationSource {
    private final String TAG;
    private final ConfigurationReader configurationReader;
    private final Function1<String, Unit> loggerInDebugBuild;

    public LocalConfiguration(String data, ConfigurationMetrics configurationMetrics) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(configurationMetrics, "configurationMetrics");
        this.TAG = LocalConfiguration.class.getSimpleName();
        this.loggerInDebugBuild = null;
        this.configurationReader = new ConfigurationReader(data, configurationMetrics, null);
    }

    public /* synthetic */ LocalConfiguration(String str, ConfigurationMetrics configurationMetrics, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new ConfigurationMetrics() : configurationMetrics);
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public MetaConfiguration getMetaConfiguration() {
        return this.configurationReader.getMetaConfiguration();
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public void reload() {
        this.configurationReader.discardCache();
    }

    @Override // com.amazon.music.featuregating.configuration.ConfigurationSource
    public <T> ConfigurationItem<T> getConfigurationItemSync(String key, KClass<T> type, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        return this.configurationReader.getConfigurationItem(key, type, serializer);
    }
}
