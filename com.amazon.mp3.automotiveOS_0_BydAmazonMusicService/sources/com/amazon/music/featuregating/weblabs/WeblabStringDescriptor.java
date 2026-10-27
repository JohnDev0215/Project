package com.amazon.music.featuregating.weblabs;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: WeblabConfigurationReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0081\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001e\u001dB/\b\u0017\u0012\u0006\u0010\u0018\u001a\u00020\r\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\u0006\u0010\n\u001a\u00020\tJ\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0012\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabStringDescriptor;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "getWeblabDescriptor", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "defaultTreatment", "getDefaultTreatment", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class WeblabStringDescriptor {
    private final String defaultTreatment;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeblabStringDescriptor)) {
            return false;
        }
        WeblabStringDescriptor weblabStringDescriptor = (WeblabStringDescriptor) other;
        return Intrinsics.areEqual(this.name, weblabStringDescriptor.name) && Intrinsics.areEqual(this.defaultTreatment, weblabStringDescriptor.defaultTreatment);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.defaultTreatment.hashCode();
    }

    public String toString() {
        return "WeblabStringDescriptor(name=" + this.name + ", defaultTreatment=" + this.defaultTreatment + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ WeblabStringDescriptor(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, WeblabStringDescriptor$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        if ((i & 2) == 0) {
            this.defaultTreatment = PlatformWeblabs.C;
        } else {
            this.defaultTreatment = str2;
        }
        PlatformProviderUtilKt.freeze(this);
    }

    @JvmStatic
    public static final void write$Self(WeblabStringDescriptor self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.name);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.defaultTreatment, PlatformWeblabs.C)) {
            output.encodeStringElement(serialDesc, 1, self.defaultTreatment);
        }
    }

    public final WeblabDescriptor getWeblabDescriptor() {
        return new WeblabDescriptor(this.name, WeblabTreatment.INSTANCE.from(this.defaultTreatment));
    }
}
