package com.amazon.music.featuregating.configuration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem;
import com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: ConfigurationSource.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&J@\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000bH&¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ConfigurationSource;", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "getMetaConfiguration", "", "reload", "T", "", "key", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "getConfigurationItemSync", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public interface ConfigurationSource {
    <T> ConfigurationItem<T> getConfigurationItemSync(String key, KClass<T> type, KSerializer<T> serializer);

    MetaConfiguration getMetaConfiguration();

    void reload();
}
