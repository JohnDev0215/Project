package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: MetaConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002&%BK\b\u0017\u0012\u0006\u0010 \u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u0010\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0017\u0012\b\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\u0004\b#\u0010$J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001f\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001a\u001a\u0004\b\u001f\u0010\u001c¨\u0006'"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "", "cacheDuration", "D", "getCacheDuration", "()D", "throttleDuration", "getThrottleDuration", "", "Lcom/amazon/music/featuregating/configuration/dataclasses/CustomRegions;", "customRegions", "Ljava/util/List;", "getCustomRegions", "()Ljava/util/List;", "Lcom/amazon/music/featuregating/configuration/dataclasses/Marketplace;", "marketplaces", "getMarketplaces", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(IDDLjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class MetaConfiguration {
    private final double cacheDuration;
    private final List<CustomRegions> customRegions;
    private final List<Marketplace> marketplaces;
    private final double throttleDuration;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetaConfiguration)) {
            return false;
        }
        MetaConfiguration metaConfiguration = (MetaConfiguration) other;
        return Intrinsics.areEqual((Object) Double.valueOf(this.cacheDuration), (Object) Double.valueOf(metaConfiguration.cacheDuration)) && Intrinsics.areEqual((Object) Double.valueOf(this.throttleDuration), (Object) Double.valueOf(metaConfiguration.throttleDuration)) && Intrinsics.areEqual(this.customRegions, metaConfiguration.customRegions) && Intrinsics.areEqual(this.marketplaces, metaConfiguration.marketplaces);
    }

    public int hashCode() {
        int iHashCode = ((((Double.hashCode(this.cacheDuration) * 31) + Double.hashCode(this.throttleDuration)) * 31) + this.customRegions.hashCode()) * 31;
        List<Marketplace> list = this.marketplaces;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "MetaConfiguration(cacheDuration=" + this.cacheDuration + ", throttleDuration=" + this.throttleDuration + ", customRegions=" + this.customRegions + ", marketplaces=" + this.marketplaces + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MetaConfiguration(int i, double d, double d2, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
        if (4 != (i & 4)) {
            PluginExceptionsKt.throwMissingFieldException(i, 4, MetaConfiguration$$serializer.INSTANCE.getDescriptor());
        }
        this.cacheDuration = (i & 1) == 0 ? 43200.0d : d;
        if ((i & 2) == 0) {
            this.throttleDuration = 60.0d;
        } else {
            this.throttleDuration = d2;
        }
        this.customRegions = list;
        if ((i & 8) == 0) {
            this.marketplaces = null;
        } else {
            this.marketplaces = list2;
        }
    }

    @JvmStatic
    public static final void write$Self(MetaConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual((Object) Double.valueOf(self.cacheDuration), (Object) Double.valueOf(43200.0d))) {
            output.encodeDoubleElement(serialDesc, 0, self.cacheDuration);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual((Object) Double.valueOf(self.throttleDuration), (Object) Double.valueOf(60.0d))) {
            output.encodeDoubleElement(serialDesc, 1, self.throttleDuration);
        }
        output.encodeSerializableElement(serialDesc, 2, new ArrayListSerializer(CustomRegions$$serializer.INSTANCE), self.customRegions);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.marketplaces != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, new ArrayListSerializer(Marketplace$$serializer.INSTANCE), self.marketplaces);
        }
    }

    public final double getCacheDuration() {
        return this.cacheDuration;
    }

    public final double getThrottleDuration() {
        return this.throttleDuration;
    }

    public final List<CustomRegions> getCustomRegions() {
        return this.customRegions;
    }

    public final List<Marketplace> getMarketplaces() {
        return this.marketplaces;
    }
}
