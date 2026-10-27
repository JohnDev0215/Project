package com.amazon.music.featuregating.configuration.dataclasses;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.ConfigurationMetrics;
import com.amazon.music.featuregating.configuration.customsubscriptiontypes.MusicSubscriptionType;
import com.amazon.music.featuregating.configuration.customsubscriptiontypes.MusicSubscriptionType$$serializer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: ConfigurationValue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 &*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002'&B3\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0014\u0012\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0014\u0012\u0006\u0010\u001c\u001a\u00028\u0000¢\u0006\u0004\b \u0010!BE\b\u0017\u0012\u0006\u0010\"\u001a\u00020\u000f\u0012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0014\u0012\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0014\u0012\b\u0010\u001c\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010$\u001a\u0004\u0018\u00010#¢\u0006\u0004\b \u0010%J;\u0010\f\u001a\u00020\u000b\"\u0004\b\u0001\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\tHÇ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0003R\u001f\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u001c\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue;", "T", "", "T0", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "Lkotlinx/serialization/KSerializer;", "typeSerial0", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "", "regions", "Ljava/util/List;", "getRegions", "()Ljava/util/List;", "Lcom/amazon/music/featuregating/configuration/customsubscriptiontypes/MusicSubscriptionType;", "subscriptions", "getSubscriptions", "value", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/lang/Object;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/util/List;Ljava/util/List;Ljava/lang/Object;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class ConfigurationValue<T> {
    private static final SerialDescriptor $cachedDescriptor;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<String> regions;
    private final List<MusicSubscriptionType> subscriptions;
    private final T value;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigurationValue)) {
            return false;
        }
        ConfigurationValue configurationValue = (ConfigurationValue) other;
        return Intrinsics.areEqual(this.regions, configurationValue.regions) && Intrinsics.areEqual(this.subscriptions, configurationValue.subscriptions) && Intrinsics.areEqual(this.value, configurationValue.value);
    }

    public int hashCode() {
        List<String> list = this.regions;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<MusicSubscriptionType> list2 = this.subscriptions;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        T t = this.value;
        return iHashCode2 + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "ConfigurationValue(regions=" + this.regions + ", subscriptions=" + this.subscriptions + ", value=" + this.value + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ConfigurationValue(int i, List list, List list2, Object obj, SerializationConstructorMarker serializationConstructorMarker) {
        if (4 != (i & 4)) {
            PluginExceptionsKt.throwMissingFieldException(i, 4, $cachedDescriptor);
        }
        if ((i & 1) == 0) {
            this.regions = null;
        } else {
            this.regions = list;
        }
        if ((i & 2) == 0) {
            this.subscriptions = null;
        } else {
            this.subscriptions = list2;
        }
        this.value = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigurationValue(List<String> list, List<? extends MusicSubscriptionType> list2, T t) {
        this.regions = list;
        this.subscriptions = list2;
        this.value = t;
    }

    @JvmStatic
    public static final <T0> void write$Self(ConfigurationValue<T0> self, CompositeEncoder output, SerialDescriptor serialDesc, KSerializer<T0> typeSerial0) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        Intrinsics.checkNotNullParameter(typeSerial0, "typeSerial0");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || ((ConfigurationValue) self).regions != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, new ArrayListSerializer(StringSerializer.INSTANCE), ((ConfigurationValue) self).regions);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || ((ConfigurationValue) self).subscriptions != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, new ArrayListSerializer(MusicSubscriptionType$$serializer.INSTANCE), ((ConfigurationValue) self).subscriptions);
        }
        output.encodeSerializableElement(serialDesc, 2, typeSerial0, ((ConfigurationValue) self).value);
    }

    public final List<String> getRegions() {
        return this.regions;
    }

    public final List<MusicSubscriptionType> getSubscriptions() {
        return this.subscriptions;
    }

    public final T getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: ConfigurationValue.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ)\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00040\u000b\"\u0004\b\u0001\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u000bHÆ\u0001¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue$Companion;", "", "()V", "convert", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue;", "T", "obj", "Lkotlinx/serialization/json/JsonElement;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlin/reflect/KClass;", "serializer", "Lkotlinx/serialization/KSerializer;", "configurationMetrics", "Lcom/amazon/music/featuregating/configuration/ConfigurationMetrics;", "T0", "typeSerial0", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T0> KSerializer<ConfigurationValue<T0>> serializer(KSerializer<T0> typeSerial0) {
            Intrinsics.checkNotNullParameter(typeSerial0, "typeSerial0");
            return new ConfigurationValue$$serializer(typeSerial0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ConfigurationValue convert$default(Companion companion, ConfigurationValue configurationValue, KClass kClass, KSerializer kSerializer, ConfigurationMetrics configurationMetrics, int i, Object obj) {
            if ((i & 4) != 0) {
                kSerializer = null;
            }
            if ((i & 8) != 0) {
                configurationMetrics = new ConfigurationMetrics();
            }
            return companion.convert(configurationValue, kClass, kSerializer, configurationMetrics);
        }

        public final <T> ConfigurationValue<T> convert(ConfigurationValue<JsonElement> obj, KClass<T> type, KSerializer<T> serializer, ConfigurationMetrics configurationMetrics) {
            Intrinsics.checkNotNullParameter(obj, "obj");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(configurationMetrics, "configurationMetrics");
            if (obj.getValue() instanceof JsonPrimitive) {
                return Intrinsics.areEqual(type, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) ? new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), Boolean.valueOf(JsonElementKt.getBoolean((JsonPrimitive) obj.getValue()))) : Intrinsics.areEqual(type, Reflection.getOrCreateKotlinClass(String.class)) ? new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), ((JsonPrimitive) obj.getValue()).getContent()) : Intrinsics.areEqual(type, Reflection.getOrCreateKotlinClass(Integer.TYPE)) ? new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), Integer.valueOf(JsonElementKt.getInt((JsonPrimitive) obj.getValue()))) : Intrinsics.areEqual(type, Reflection.getOrCreateKotlinClass(Double.TYPE)) ? new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), Double.valueOf(JsonElementKt.getDouble((JsonPrimitive) obj.getValue()))) : new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), ((JsonPrimitive) obj.getValue()).getContent());
            }
            if (serializer != null) {
                try {
                    return new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), Json.INSTANCE.decodeFromJsonElement(serializer, obj.getValue()));
                } catch (Exception unused) {
                    configurationMetrics.decodingErrorNoValueForConfigKey("Failed to deserialize configuration");
                    return null;
                }
            }
            return new ConfigurationValue<>(obj.getRegions(), obj.getSubscriptions(), obj.getValue());
        }
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.featuregating.configuration.dataclasses.ConfigurationValue", null, 3);
        pluginGeneratedSerialDescriptor.addElement("regions", true);
        pluginGeneratedSerialDescriptor.addElement("subscriptions", true);
        pluginGeneratedSerialDescriptor.addElement("value", false);
        $cachedDescriptor = pluginGeneratedSerialDescriptor;
    }
}
