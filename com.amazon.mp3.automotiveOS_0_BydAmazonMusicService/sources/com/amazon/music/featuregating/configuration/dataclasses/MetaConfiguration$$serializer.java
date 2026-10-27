package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

/* JADX INFO: compiled from: MetaConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/featuregating/configuration/dataclasses/MetaConfiguration;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class MetaConfiguration$$serializer implements GeneratedSerializer<MetaConfiguration> {
    public static final MetaConfiguration$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        MetaConfiguration$$serializer metaConfiguration$$serializer = new MetaConfiguration$$serializer();
        INSTANCE = metaConfiguration$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.featuregating.configuration.dataclasses.MetaConfiguration", metaConfiguration$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("cacheDuration", true);
        pluginGeneratedSerialDescriptor.addElement("throttleDuration", true);
        pluginGeneratedSerialDescriptor.addElement("customRegions", false);
        pluginGeneratedSerialDescriptor.addElement("marketplaces", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private MetaConfiguration$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        DoubleSerializer doubleSerializer = DoubleSerializer.INSTANCE;
        return new KSerializer[]{doubleSerializer, doubleSerializer, new ArrayListSerializer(CustomRegions$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(new ArrayListSerializer(Marketplace$$serializer.INSTANCE))};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public MetaConfiguration deserialize(Decoder decoder) {
        int i;
        double d;
        double d2;
        Object objDecodeNullableSerializableElement;
        Object objDecodeSerializableElement;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        Object objDecodeSerializableElement2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            double dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(descriptor2, 0);
            double dDecodeDoubleElement2 = compositeDecoderBeginStructure.decodeDoubleElement(descriptor2, 1);
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, new ArrayListSerializer(CustomRegions$$serializer.INSTANCE), null);
            objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, new ArrayListSerializer(Marketplace$$serializer.INSTANCE), null);
            i = 15;
            d2 = dDecodeDoubleElement;
            d = dDecodeDoubleElement2;
        } else {
            Object objDecodeNullableSerializableElement2 = null;
            boolean z = true;
            double dDecodeDoubleElement3 = 0.0d;
            double dDecodeDoubleElement4 = 0.0d;
            int i2 = 0;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    dDecodeDoubleElement3 = compositeDecoderBeginStructure.decodeDoubleElement(descriptor2, 0);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    dDecodeDoubleElement4 = compositeDecoderBeginStructure.decodeDoubleElement(descriptor2, 1);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    objDecodeSerializableElement2 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, new ArrayListSerializer(CustomRegions$$serializer.INSTANCE), objDecodeSerializableElement2);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, new ArrayListSerializer(Marketplace$$serializer.INSTANCE), objDecodeNullableSerializableElement2);
                    i2 |= 8;
                }
            }
            i = i2;
            d = dDecodeDoubleElement4;
            d2 = dDecodeDoubleElement3;
            Object obj = objDecodeSerializableElement2;
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement2;
            objDecodeSerializableElement = obj;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new MetaConfiguration(i, d2, d, (List) objDecodeSerializableElement, (List) objDecodeNullableSerializableElement, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, MetaConfiguration value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        MetaConfiguration.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
