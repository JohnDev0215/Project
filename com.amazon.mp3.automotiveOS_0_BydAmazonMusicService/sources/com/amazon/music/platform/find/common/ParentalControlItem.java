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
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: ParentalControlItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002 \u001fB\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u000e¢\u0006\u0004\b\u0019\u0010\u001aB-\b\u0017\u0012\u0006\u0010\u001b\u001a\u00020\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u000e\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u0019\u0010\u001eJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/amazon/music/platform/find/common/ParentalControlItem;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/find/common/ContentTier;", "contentTier", "Lcom/amazon/music/platform/find/common/ContentTier;", "getContentTier", "()Lcom/amazon/music/platform/find/common/ContentTier;", "hasExplicitLanguage", "Z", "getHasExplicitLanguage", "()Z", "<init>", "(Lcom/amazon/music/platform/find/common/ContentTier;Z)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILcom/amazon/music/platform/find/common/ContentTier;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class ParentalControlItem {
    private final ContentTier contentTier;
    private final boolean hasExplicitLanguage;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParentalControlItem)) {
            return false;
        }
        ParentalControlItem parentalControlItem = (ParentalControlItem) other;
        return this.contentTier == parentalControlItem.contentTier && this.hasExplicitLanguage == parentalControlItem.hasExplicitLanguage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        int iHashCode = this.contentTier.hashCode() * 31;
        boolean z = this.hasExplicitLanguage;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "ParentalControlItem(contentTier=" + this.contentTier + ", hasExplicitLanguage=" + this.hasExplicitLanguage + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ParentalControlItem(int i, ContentTier contentTier, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, ParentalControlItem$$serializer.INSTANCE.getDescriptor());
        }
        this.contentTier = contentTier;
        this.hasExplicitLanguage = z;
    }

    public ParentalControlItem(ContentTier contentTier, boolean z) {
        Intrinsics.checkNotNullParameter(contentTier, "contentTier");
        this.contentTier = contentTier;
        this.hasExplicitLanguage = z;
    }

    @JvmStatic
    public static final void write$Self(ParentalControlItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, new EnumSerializer("com.amazon.music.platform.find.common.ContentTier", ContentTier.values()), self.contentTier);
        output.encodeBooleanElement(serialDesc, 1, self.hasExplicitLanguage);
    }

    public final ContentTier getContentTier() {
        return this.contentTier;
    }

    public final boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }
}
