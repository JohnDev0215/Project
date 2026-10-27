package com.amazon.music.featuregating.rules;

import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: BooleanConfigurationRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u0002H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/featuregating/rules/BooleanConfigurationRule;", "Lcom/amazon/music/featuregating/rules/ConfigurationRule;", "", "key", "", "(Ljava/lang/String;)V", "configuration", "Lcom/amazon/music/featuregating/configuration/Configuration;", "(Lcom/amazon/music/featuregating/configuration/Configuration;Ljava/lang/String;)V", "getConfiguration", "()Lcom/amazon/music/featuregating/configuration/Configuration;", "getKey", "()Ljava/lang/String;", "isSatisfied", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public class BooleanConfigurationRule extends ConfigurationRule<Boolean> {
    private final Configuration configuration;
    private final String key;

    @Override // com.amazon.music.featuregating.rules.ConfigurationRule
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override // com.amazon.music.featuregating.rules.ConfigurationRule
    public String getKey() {
        return this.key;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BooleanConfigurationRule(Configuration configuration, String key) {
        super(configuration, key, Reflection.getOrCreateKotlinClass(Boolean.TYPE), null, new Function1<Boolean, Boolean>() { // from class: com.amazon.music.featuregating.rules.BooleanConfigurationRule.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Boolean bool) {
                return Boolean.valueOf(Intrinsics.areEqual(bool, Boolean.TRUE));
            }
        });
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(key, "key");
        this.configuration = configuration;
        this.key = key;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BooleanConfigurationRule(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        InterfaceProvider interfaceProvider = Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(interfaceProvider, "null cannot be cast to non-null type com.amazon.music.featuregating.configuration.Configuration");
        this((Configuration) interfaceProvider, key);
    }

    @Override // com.amazon.music.featuregating.rules.ConfigurationRule, com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        return getEvaluate().invoke((Boolean) Configuration.DefaultImpls.value$default(getConfiguration(), getKey(), getType(), null, 4, null)).booleanValue();
    }
}
