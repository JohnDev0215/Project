package com.amazon.music.featuregating.configuration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationSerializer;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationValue;
import com.amazon.music.featuregating.configuration.dataclasses.FinalConfiguration;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: ConfigurationReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\"B5\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u001c\b\u0002\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0016j\u0004\u0018\u0001`\u0017¢\u0006\u0004\b \u0010!J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\b\u001a\u00020\u0007J\b\u0010\n\u001a\u0004\u0018\u00010\tJ@\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000eR\u0014\u0010\u0011\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R(\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0016j\u0004\u0018\u0001`\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ConfigurationReader;", "", "", "key", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "Lkotlinx/serialization/json/JsonElement;", "getDefaultConfigurationItem", "", "discardCache", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "getMetaConfiguration", "T", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "getConfigurationItem", "unparsedConfig", "Ljava/lang/String;", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "configurationMetrics", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/configuration/ConfigurationLogger;", "logger", "Lkotlin/jvm/functions/Function1;", "Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration;", "parsedConfig", "Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration;", "Ljava/util/concurrent/ConcurrentHashMap;", "dataTypedConfigCache", "Ljava/util/concurrent/ConcurrentHashMap;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;Lkotlin/jvm/functions/Function1;)V", "UnexpectedConfigFormat", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class ConfigurationReader {
    private final ConfigurationMetrics configurationMetrics;
    private final ConcurrentHashMap<String, Object> dataTypedConfigCache;
    private final Function1<String, Unit> logger;
    private final FinalConfiguration parsedConfig;
    private final String unparsedConfig;

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigurationReader(String unparsedConfig, ConfigurationMetrics configurationMetrics, Function1<? super String, Unit> function1) throws UnexpectedConfigFormat {
        Intrinsics.checkNotNullParameter(unparsedConfig, "unparsedConfig");
        Intrinsics.checkNotNullParameter(configurationMetrics, "configurationMetrics");
        this.unparsedConfig = unparsedConfig;
        this.configurationMetrics = configurationMetrics;
        this.logger = function1;
        try {
            this.parsedConfig = (FinalConfiguration) Json.INSTANCE.decodeFromString(new ConfigurationSerializer(), unparsedConfig);
            this.dataTypedConfigCache = new ConcurrentHashMap<>();
        } catch (Exception unused) {
            this.configurationMetrics.unexpectedConfigFormat(this.unparsedConfig);
            throw new UnexpectedConfigFormat(this, this.unparsedConfig);
        }
    }

    public final void discardCache() {
        this.dataTypedConfigCache.clear();
    }

    public final MetaConfiguration getMetaConfiguration() {
        return this.parsedConfig.getConfiguration();
    }

    private final ConfigurationItem<JsonElement> getDefaultConfigurationItem(String key) {
        List<ConfigurationValue<JsonElement>> list = this.parsedConfig.getEntries().get(key);
        if (list != null) {
            return new ConfigurationItem<>(list);
        }
        return null;
    }

    public final <T> ConfigurationItem<T> getConfigurationItem(String key, KClass<T> type, KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        Object obj = this.dataTypedConfigCache.get(key);
        ConfigurationItem<T> configurationItem = obj instanceof ConfigurationItem ? (ConfigurationItem) obj : null;
        if (configurationItem != null) {
            return configurationItem;
        }
        ConfigurationItem<JsonElement> defaultConfigurationItem = getDefaultConfigurationItem(key);
        if (defaultConfigurationItem != null) {
            try {
                ConfigurationItem<T> configurationItemConvert$default = ConfigurationItem.Companion.convert$default(ConfigurationItem.INSTANCE, defaultConfigurationItem, type, serializer, null, 8, null);
                this.dataTypedConfigCache.put(key, configurationItemConvert$default);
                Function1<String, Unit> function1 = this.logger;
                if (function1 != null) {
                    function1.invoke("Fetched Key: " + key + " from configuration with value: " + configurationItemConvert$default);
                }
                return configurationItemConvert$default;
            } catch (Exception e) {
                Function1<String, Unit> function12 = this.logger;
                if (function12 != null) {
                    function12.invoke("Failed to fetch config value for key: " + key);
                }
                this.configurationMetrics.failedToReadConfigurationItem(key, e.getMessage());
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: ConfigurationReader.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ConfigurationReader$UnexpectedConfigFormat;", "", "unparsedConfig", "", "(Lcom/amazon/music/featuregating/configuration/ConfigurationReader;Ljava/lang/String;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class UnexpectedConfigFormat extends Throwable {
        final /* synthetic */ ConfigurationReader this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnexpectedConfigFormat(ConfigurationReader configurationReader, String unparsedConfig) {
            super(unparsedConfig);
            Intrinsics.checkNotNullParameter(unparsedConfig, "unparsedConfig");
            this.this$0 = configurationReader;
        }
    }
}
