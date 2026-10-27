package com.amazon.music.featuregating.rules;

import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: URLConfigurationValue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u0010J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/featuregating/rules/URLConfigurationValue;", "", "Ljava/net/URL;", "getValue", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "Lcom/amazon/music/featuregating/configuration/Configuration;", "getConfiguration", "()Lcom/amazon/music/featuregating/configuration/Configuration;", "", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/featuregating/configuration/Configuration;Ljava/lang/String;)V", "(Ljava/lang/String;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public class URLConfigurationValue {
    private final Configuration configuration;
    private final String key;

    public URLConfigurationValue(Configuration configuration, String key) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(key, "key");
        this.configuration = configuration;
        this.key = key;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public URLConfigurationValue(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        InterfaceProvider interfaceProvider = Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(interfaceProvider, "null cannot be cast to non-null type com.amazon.music.featuregating.configuration.Configuration");
        this((Configuration) interfaceProvider, key);
    }

    public URL getValue() {
        String str = (String) this.configuration.value(this.key, Reflection.getOrCreateKotlinClass(String.class), (KSerializer) null);
        if (str != null) {
            return new URL(str);
        }
        return null;
    }
}
