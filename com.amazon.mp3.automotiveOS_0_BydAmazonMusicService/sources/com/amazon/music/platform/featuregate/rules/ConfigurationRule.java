package com.amazon.music.platform.featuregate.rules;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ConfigurationRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/featuregate/rules/ConfigurationRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "isFeatureSupportedConfigKey", "", "(Ljava/lang/String;)V", "configurationProvider", "Lcom/amazon/music/platform/providers/ConfigurationProvider;", "(Ljava/lang/String;Lcom/amazon/music/platform/providers/ConfigurationProvider;)V", "isSatisfied", "", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConfigurationRule implements Rule {
    private ConfigurationProvider configurationProvider;
    private String isFeatureSupportedConfigKey;

    /* JADX WARN: Illegal instructions before constructor call */
    public ConfigurationRule(String isFeatureSupportedConfigKey) {
        Intrinsics.checkNotNullParameter(isFeatureSupportedConfigKey, "isFeatureSupportedConfigKey");
        InterfaceProvider interfaceProvider = Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(interfaceProvider);
        this(isFeatureSupportedConfigKey, (ConfigurationProvider) interfaceProvider);
    }

    public ConfigurationRule(String isFeatureSupportedConfigKey, ConfigurationProvider configurationProvider) {
        Intrinsics.checkNotNullParameter(isFeatureSupportedConfigKey, "isFeatureSupportedConfigKey");
        Intrinsics.checkNotNullParameter(configurationProvider, "configurationProvider");
        this.isFeatureSupportedConfigKey = isFeatureSupportedConfigKey;
        this.configurationProvider = configurationProvider;
        PlatformProviderUtilKt.freeze(this);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        return this.configurationProvider.getBooleanForKey(this.isFeatureSupportedConfigKey);
    }
}
