package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.playback.data.ShuffleType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: PlaymodeEligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002%$B%\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u001e\u0010\u001fB9\b\u0017\u0012\u0006\u0010 \u001a\u00020\u0010\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\u0004\b\u001e\u0010#J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\tJ\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0014\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0015\u0010\u0017R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0016\u001a\u0004\b\u0018\u0010\u0017R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\f\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001b¨\u0006&"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "isOnDemand", "Lcom/amazon/music/platform/playback/data/ShuffleType;", "shuffleType", "isStationFromAnything", "", "toString", "", "hashCode", "", "other", "equals", "isOnDemandPlayable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isStationFromAnythingPlayable", "Ljava/lang/String;", "getShuffleType", "()Ljava/lang/String;", "getTYPE", "TYPE", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
@Serializable
public final /* data */ class PlaymodeEligibility implements Eligibility {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Boolean isOnDemandPlayable;
    private final Boolean isStationFromAnythingPlayable;
    private final String shuffleType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaymodeEligibility)) {
            return false;
        }
        PlaymodeEligibility playmodeEligibility = (PlaymodeEligibility) other;
        return Intrinsics.areEqual(this.isOnDemandPlayable, playmodeEligibility.isOnDemandPlayable) && Intrinsics.areEqual(this.isStationFromAnythingPlayable, playmodeEligibility.isStationFromAnythingPlayable) && Intrinsics.areEqual(this.shuffleType, playmodeEligibility.shuffleType);
    }

    public String getTYPE() {
        return "PLAYMODES";
    }

    public int hashCode() {
        Boolean bool = this.isOnDemandPlayable;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.isStationFromAnythingPlayable;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str = this.shuffleType;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "PlaymodeEligibility(isOnDemandPlayable=" + this.isOnDemandPlayable + ", isStationFromAnythingPlayable=" + this.isStationFromAnythingPlayable + ", shuffleType=" + this.shuffleType + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ PlaymodeEligibility(int i, Boolean bool, Boolean bool2, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, PlaymodeEligibility$$serializer.INSTANCE.getDescriptor());
        }
        this.isOnDemandPlayable = bool;
        this.isStationFromAnythingPlayable = bool2;
        this.shuffleType = str;
        PlatformProviderUtilKt.freeze(this);
    }

    public PlaymodeEligibility(Boolean bool, Boolean bool2, String str) {
        this.isOnDemandPlayable = bool;
        this.isStationFromAnythingPlayable = bool2;
        this.shuffleType = str;
        PlatformProviderUtilKt.freeze(this);
    }

    @JvmStatic
    public static final void write$Self(PlaymodeEligibility self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        output.encodeNullableSerializableElement(serialDesc, 0, booleanSerializer, self.isOnDemandPlayable);
        output.encodeNullableSerializableElement(serialDesc, 1, booleanSerializer, self.isStationFromAnythingPlayable);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.shuffleType);
    }

    /* JADX INFO: renamed from: isOnDemandPlayable, reason: from getter */
    public final Boolean getIsOnDemandPlayable() {
        return this.isOnDemandPlayable;
    }

    /* JADX INFO: renamed from: isStationFromAnythingPlayable, reason: from getter */
    public final Boolean getIsStationFromAnythingPlayable() {
        return this.isStationFromAnythingPlayable;
    }

    public final String getShuffleType() {
        return this.shuffleType;
    }

    /* JADX INFO: compiled from: PlaymodeEligibility.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility$Companion;", "", "()V", "ELIGIBILITY_TYPE", "", "getPlaymodeEligibilityFromJson", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "jsonPlaymodeEligibility", "getPlaymodeEligibilityString", "playmodeEligibility", "serializer", "Lkotlinx/serialization/KSerializer;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PlaymodeEligibility> serializer() {
            return PlaymodeEligibility$$serializer.INSTANCE;
        }

        public final PlaymodeEligibility getPlaymodeEligibilityFromJson(String jsonPlaymodeEligibility) {
            if (jsonPlaymodeEligibility == null || jsonPlaymodeEligibility.length() == 0) {
                return null;
            }
            try {
                Json.Companion r0 = Json.INSTANCE;
                KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(r0.getSerializersModule(), Reflection.typeOf(PlaymodeEligibility.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                return (PlaymodeEligibility) r0.decodeFromString(kSerializerSerializer, jsonPlaymodeEligibility);
            } catch (Exception unused) {
                return null;
            }
        }

        public final String getPlaymodeEligibilityString(PlaymodeEligibility playmodeEligibility) {
            if (playmodeEligibility != null) {
                return Json.INSTANCE.encodeToString(PlaymodeEligibility.INSTANCE.serializer(), playmodeEligibility);
            }
            return null;
        }
    }

    public final boolean isOnDemand() {
        Boolean bool = this.isOnDemandPlayable;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final ShuffleType shuffleType() {
        String str = this.shuffleType;
        if (str != null) {
            ShuffleType shuffleType = ShuffleType.INLINE;
            if (!StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) shuffleType.getValue(), false, 2, (Object) null)) {
                shuffleType = ShuffleType.MIXED;
                if (!StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) shuffleType.getValue(), false, 2, (Object) null)) {
                    shuffleType = ShuffleType.NONE;
                }
            }
            if (shuffleType != null) {
                return shuffleType;
            }
        }
        return ShuffleType.NONE;
    }

    public final boolean isStationFromAnything() {
        Boolean bool = this.isStationFromAnythingPlayable;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
