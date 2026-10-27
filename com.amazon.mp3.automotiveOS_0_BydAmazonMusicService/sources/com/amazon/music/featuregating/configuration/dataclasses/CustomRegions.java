package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: CustomRegions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002 \u001fB\u001d\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0010\u0012\u0006\u0010\u0015\u001a\u00020\t¢\u0006\u0004\b\u0019\u0010\u001aB5\b\u0017\u0012\u0006\u0010\u001b\u001a\u00020\u000b\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0010\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u0019\u0010\u001eJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/CustomRegions;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "", "regions", "Ljava/util/Set;", "getRegions", "()Ljava/util/Set;", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/util/Set;Ljava/lang/String;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/util/Set;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class CustomRegions {
    private final Set<String> regions;
    private final String value;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomRegions)) {
            return false;
        }
        CustomRegions customRegions = (CustomRegions) other;
        return Intrinsics.areEqual(this.regions, customRegions.regions) && Intrinsics.areEqual(this.value, customRegions.value);
    }

    public int hashCode() {
        return (this.regions.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "CustomRegions(regions=" + this.regions + ", value=" + this.value + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CustomRegions(int i, Set set, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CustomRegions$$serializer.INSTANCE.getDescriptor());
        }
        this.regions = set;
        this.value = str;
    }

    public CustomRegions(Set<String> regions, String value) {
        Intrinsics.checkNotNullParameter(regions, "regions");
        Intrinsics.checkNotNullParameter(value, "value");
        this.regions = regions;
        this.value = value;
    }

    @JvmStatic
    public static final void write$Self(CustomRegions self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, new LinkedHashSetSerializer(StringSerializer.INSTANCE), self.regions);
        output.encodeStringElement(serialDesc, 1, self.value);
    }

    public final Set<String> getRegions() {
        return this.regions;
    }

    public final String getValue() {
        return this.value;
    }
}
