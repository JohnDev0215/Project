package com.amazon.music.featuregating.configuration.dataclasses;

import com.amazon.music.featuregating.configuration.customsubscriptiontypes.MusicSubscriptionType$$serializer;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: ConfigurationValue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0004B\u0015\b\u0017\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\rHÖ\u0001¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u001f\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003HÖ\u0001J\u0018\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\rHÖ\u0001¢\u0006\u0002\u0010\u000eR\u0014\u0010\b\u001a\u00020\t8VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"com/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue.$serializer", "T", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationValue;", "()V", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "typeParametersSerializers", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class ConfigurationValue$$serializer<T> implements GeneratedSerializer<ConfigurationValue<T>> {
    public final /* synthetic */ SerialDescriptor descriptor;
    private final /* synthetic */ KSerializer<T> typeSerial0;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    private ConfigurationValue$$serializer() {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.featuregating.configuration.dataclasses.ConfigurationValue", this, 3);
        pluginGeneratedSerialDescriptor.addElement("regions", true);
        pluginGeneratedSerialDescriptor.addElement("subscriptions", true);
        pluginGeneratedSerialDescriptor.addElement("value", false);
        this.descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ConfigurationValue$$serializer(KSerializer typeSerial0) {
        this();
        Intrinsics.checkNotNullParameter(typeSerial0, "typeSerial0");
        this.typeSerial0 = typeSerial0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(new ArrayListSerializer(StringSerializer.INSTANCE)), BuiltinSerializersKt.getNullable(new ArrayListSerializer(MusicSubscriptionType$$serializer.INSTANCE)), this.typeSerial0};
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    public ConfigurationValue<T> deserialize(Decoder decoder) {
        Object objDecodeSerializableElement;
        Object objDecodeNullableSerializableElement;
        Object objDecodeNullableSerializableElement2;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        Object objDecodeNullableSerializableElement3 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor, 0, new ArrayListSerializer(StringSerializer.INSTANCE), null);
            objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor, 1, new ArrayListSerializer(MusicSubscriptionType$$serializer.INSTANCE), null);
            i = 7;
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor, 2, this.typeSerial0, null);
        } else {
            Object objDecodeNullableSerializableElement4 = null;
            objDecodeSerializableElement = null;
            int i2 = 0;
            boolean z = true;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    objDecodeNullableSerializableElement3 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor, 0, new ArrayListSerializer(StringSerializer.INSTANCE), objDecodeNullableSerializableElement3);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    objDecodeNullableSerializableElement4 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor, 1, new ArrayListSerializer(MusicSubscriptionType$$serializer.INSTANCE), objDecodeNullableSerializableElement4);
                    i2 |= 2;
                } else {
                    if (iDecodeElementIndex != 2) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor, 2, this.typeSerial0, objDecodeSerializableElement);
                    i2 |= 4;
                }
            }
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement4;
            objDecodeNullableSerializableElement2 = objDecodeNullableSerializableElement3;
            i = i2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor);
        return new ConfigurationValue<>(i, (List) objDecodeNullableSerializableElement2, (List) objDecodeNullableSerializableElement, objDecodeSerializableElement, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, ConfigurationValue<T> value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor);
        ConfigurationValue.write$Self(value, compositeEncoderBeginStructure, descriptor, this.typeSerial0);
        compositeEncoderBeginStructure.endStructure(descriptor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return new KSerializer[]{this.typeSerial0};
    }
}
