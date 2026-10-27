package com.amazon.music.skyfire.models;

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
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: TetheredModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016HÇ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/skyfire/models/TetheredErrorMessage;", "", "seen1", "", "error", "Lcom/amazon/music/skyfire/models/TetheredError;", "originalRequestId", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/amazon/music/skyfire/models/TetheredError;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/amazon/music/skyfire/models/TetheredError;Ljava/lang/Integer;)V", "getError", "()Lcom/amazon/music/skyfire/models/TetheredError;", "getOriginalRequestId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@Serializable
public final class TetheredErrorMessage {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final TetheredError error;
    private final Integer originalRequestId;

    /* JADX INFO: compiled from: TetheredModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/models/TetheredErrorMessage$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/skyfire/models/TetheredErrorMessage;", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<TetheredErrorMessage> serializer() {
            return TetheredErrorMessage$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TetheredErrorMessage(int i, TetheredError tetheredError, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, TetheredErrorMessage$$serializer.INSTANCE.getDescriptor());
        }
        this.error = tetheredError;
        this.originalRequestId = num;
    }

    public TetheredErrorMessage(TetheredError error, Integer num) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.error = error;
        this.originalRequestId = num;
    }

    @JvmStatic
    public static final void write$Self(TetheredErrorMessage self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, new EnumSerializer("com.amazon.music.skyfire.models.TetheredError", TetheredError.values()), self.error);
        output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.originalRequestId);
    }

    public final TetheredError getError() {
        return this.error;
    }

    public final Integer getOriginalRequestId() {
        return this.originalRequestId;
    }
}
