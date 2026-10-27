package com.amazon.music.platform.find.common;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

/* JADX INFO: compiled from: ParentalControlsPerContentTier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/music/platform/find/common/ParentalControlsPerContentTier.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class ParentalControlsPerContentTier$$serializer implements GeneratedSerializer<ParentalControlsPerContentTier> {
    public static final ParentalControlsPerContentTier$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        ParentalControlsPerContentTier$$serializer parentalControlsPerContentTier$$serializer = new ParentalControlsPerContentTier$$serializer();
        INSTANCE = parentalControlsPerContentTier$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.platform.find.common.ParentalControlsPerContentTier", parentalControlsPerContentTier$$serializer, 1);
        pluginGeneratedSerialDescriptor.addElement("parentalControlItems", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private ParentalControlsPerContentTier$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new ArrayListSerializer(ParentalControlItem$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public ParentalControlsPerContentTier deserialize(Decoder decoder) {
        Object objDecodeSerializableElement;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i = 1;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, new ArrayListSerializer(ParentalControlItem$$serializer.INSTANCE), null);
        } else {
            objDecodeSerializableElement = null;
            int i2 = 0;
            while (i != 0) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    i = 0;
                } else {
                    if (iDecodeElementIndex != 0) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, new ArrayListSerializer(ParentalControlItem$$serializer.INSTANCE), objDecodeSerializableElement);
                    i2 |= 1;
                }
            }
            i = i2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new ParentalControlsPerContentTier(i, (List) objDecodeSerializableElement, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, ParentalControlsPerContentTier value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        ParentalControlsPerContentTier.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
