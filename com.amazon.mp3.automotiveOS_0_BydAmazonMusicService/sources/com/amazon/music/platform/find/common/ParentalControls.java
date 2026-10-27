package com.amazon.music.platform.find.common;

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

/* JADX INFO: compiled from: ParentalControls.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001b\u001aB\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015B#\b\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0014\u0010\u0019J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0010\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/platform/find/common/ParentalControls;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "hasExplicitLanguage", "Z", "getHasExplicitLanguage", "()Z", "<init>", "(Z)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class ParentalControls {
    private final boolean hasExplicitLanguage;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ParentalControls) && this.hasExplicitLanguage == ((ParentalControls) other).hasExplicitLanguage;
    }

    public int hashCode() {
        boolean z = this.hasExplicitLanguage;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "ParentalControls(hasExplicitLanguage=" + this.hasExplicitLanguage + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ParentalControls(int i, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, ParentalControls$$serializer.INSTANCE.getDescriptor());
        }
        this.hasExplicitLanguage = z;
    }

    public ParentalControls(boolean z) {
        this.hasExplicitLanguage = z;
    }

    @JvmStatic
    public static final void write$Self(ParentalControls self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeBooleanElement(serialDesc, 0, self.hasExplicitLanguage);
    }

    public final boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }
}
