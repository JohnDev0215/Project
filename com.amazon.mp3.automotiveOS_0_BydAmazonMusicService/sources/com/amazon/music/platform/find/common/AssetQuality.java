package com.amazon.music.platform.find.common;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: AssetQuality.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001f\u001eB\u001f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0018\u0010\u0019B1\b\u0017\u0012\u0006\u0010\u001a\u001a\u00020\u000b\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u0018\u0010\u001dJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013¨\u0006 "}, d2 = {"Lcom/amazon/music/platform/find/common/AssetQuality;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "playbackId", "Ljava/lang/String;", "getPlaybackId", "()Ljava/lang/String;", "getPlaybackId$annotations", "()V", "quality", "getQuality", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class AssetQuality {
    private final String playbackId;
    private final String quality;

    public AssetQuality() {
        this((String) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssetQuality)) {
            return false;
        }
        AssetQuality assetQuality = (AssetQuality) other;
        return Intrinsics.areEqual(this.playbackId, assetQuality.playbackId) && Intrinsics.areEqual(this.quality, assetQuality.quality);
    }

    public int hashCode() {
        String str = this.playbackId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.quality;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AssetQuality(playbackId=" + this.playbackId + ", quality=" + this.quality + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AssetQuality(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, AssetQuality$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.playbackId = null;
        } else {
            this.playbackId = str;
        }
        if ((i & 2) == 0) {
            this.quality = null;
        } else {
            this.quality = str2;
        }
    }

    public AssetQuality(String str, String str2) {
        this.playbackId = str;
        this.quality = str2;
    }

    @JvmStatic
    public static final void write$Self(AssetQuality self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.playbackId != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.playbackId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.quality != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.quality);
        }
    }

    public /* synthetic */ AssetQuality(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getPlaybackId() {
        return this.playbackId;
    }

    public final String getQuality() {
        return this.quality;
    }
}
