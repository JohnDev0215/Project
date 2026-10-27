package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;

/* JADX INFO: compiled from: FinalConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002#\"BG\b\u0017\u0012\u0006\u0010\u001d\u001a\u00020\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012 \u0010\u0019\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016\u0018\u00010\u0015\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R/\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u00160\u00158\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "configuration", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "getConfiguration", "()Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue;", "Lkotlinx/serialization/json/JsonElement;", "entries", "Ljava/util/Map;", "getEntries", "()Ljava/util/Map;", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class FinalConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MetaConfiguration configuration;
    private final Map<String, List<ConfigurationValue<JsonElement>>> entries;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FinalConfiguration)) {
            return false;
        }
        FinalConfiguration finalConfiguration = (FinalConfiguration) other;
        return Intrinsics.areEqual(this.configuration, finalConfiguration.configuration) && Intrinsics.areEqual(this.entries, finalConfiguration.entries);
    }

    public int hashCode() {
        MetaConfiguration metaConfiguration = this.configuration;
        return ((metaConfiguration == null ? 0 : metaConfiguration.hashCode()) * 31) + this.entries.hashCode();
    }

    public String toString() {
        return "FinalConfiguration(configuration=" + this.configuration + ", entries=" + this.entries + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FinalConfiguration(int i, MetaConfiguration metaConfiguration, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i, 2, FinalConfiguration$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.configuration = null;
        } else {
            this.configuration = metaConfiguration;
        }
        this.entries = map;
    }

    @JvmStatic
    public static final void write$Self(FinalConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.configuration != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, MetaConfiguration$$serializer.INSTANCE, self.configuration);
        }
        output.encodeSerializableElement(serialDesc, 1, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ArrayListSerializer(ConfigurationValue.INSTANCE.serializer(JsonElementSerializer.INSTANCE))), self.entries);
    }

    public final MetaConfiguration getConfiguration() {
        return this.configuration;
    }

    public final Map<String, List<ConfigurationValue<JsonElement>>> getEntries() {
        return this.entries;
    }

    /* JADX INFO: compiled from: FinalConfiguration.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration$Companion;", "", "()V", "configurationKey", "", "entriesKey", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<FinalConfiguration> serializer() {
            return FinalConfiguration$$serializer.INSTANCE;
        }
    }
}
