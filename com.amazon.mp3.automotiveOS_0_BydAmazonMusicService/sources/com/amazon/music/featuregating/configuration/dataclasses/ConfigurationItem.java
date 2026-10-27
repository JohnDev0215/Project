package com.amazon.music.featuregating.configuration.dataclasses;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.ConfigurationMetrics;
import com.amazon.music.featuregating.configuration.dataclasses.ConfigurationValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: ConfigurationItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\b\u0087\b\u0018\u0000 \u0012*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0012B\u001b\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\n¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÖ\u0003R#\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\n8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "T", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue;", "values", "Ljava/util/List;", "getValues", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class ConfigurationItem<T> {
    private static final SerialDescriptor $cachedDescriptor;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<ConfigurationValue<T>> values;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConfigurationItem) && Intrinsics.areEqual(this.values, ((ConfigurationItem) other).values);
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public String toString() {
        return "ConfigurationItem(values=" + this.values + ')';
    }

    public ConfigurationItem(List<ConfigurationValue<T>> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = values;
    }

    /* JADX INFO: compiled from: ConfigurationItem.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJN\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem$Companion;", "", "T", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationItem;", "Lkotlinx/serialization/json/JsonElement;", "obj", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "configurationMetrics", "convert", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ConfigurationItem convert$default(Companion companion, ConfigurationItem configurationItem, KClass kClass, KSerializer kSerializer, ConfigurationMetrics configurationMetrics, int i, Object obj) {
            if ((i & 4) != 0) {
                kSerializer = null;
            }
            if ((i & 8) != 0) {
                configurationMetrics = new ConfigurationMetrics();
            }
            return companion.convert(configurationItem, kClass, kSerializer, configurationMetrics);
        }

        public final <T> ConfigurationItem<T> convert(ConfigurationItem<JsonElement> obj, KClass<T> type, KSerializer<T> serializer, ConfigurationMetrics configurationMetrics) {
            Intrinsics.checkNotNullParameter(obj, "obj");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(configurationMetrics, "configurationMetrics");
            List<ConfigurationValue<JsonElement>> values = obj.getValues();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = values.iterator();
            while (it.hasNext()) {
                ConfigurationValue configurationValueConvert$default = ConfigurationValue.Companion.convert$default(ConfigurationValue.INSTANCE, (ConfigurationValue) it.next(), type, serializer, null, 8, null);
                if (configurationValueConvert$default == null) {
                    configurationMetrics.decodingErrorNoValueForConfigKey("Configuration Type not supported");
                }
                if (configurationValueConvert$default != null) {
                    arrayList.add(configurationValueConvert$default);
                }
            }
            if (!(obj.getValues().size() == arrayList.size())) {
                throw new IllegalArgumentException("ConfigurationItem transformation from JsonPrimitive failed!!".toString());
            }
            return new ConfigurationItem<>(arrayList);
        }
    }

    public final List<ConfigurationValue<T>> getValues() {
        return this.values;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.featuregating.configuration.dataclasses.ConfigurationItem", null, 1);
        pluginGeneratedSerialDescriptor.addElement("values", false);
        $cachedDescriptor = pluginGeneratedSerialDescriptor;
    }
}
