package com.amazon.music.skyfire.models;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PlaybackModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/music/skyfire/models/SkyfirePlaybackStateModel.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackStateModel;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class SkyfirePlaybackStateModel$$serializer implements GeneratedSerializer<SkyfirePlaybackStateModel> {
    public static final SkyfirePlaybackStateModel$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SkyfirePlaybackStateModel$$serializer skyfirePlaybackStateModel$$serializer = new SkyfirePlaybackStateModel$$serializer();
        INSTANCE = skyfirePlaybackStateModel$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.skyfire.models.SkyfirePlaybackStateModel", skyfirePlaybackStateModel$$serializer, 13);
        pluginGeneratedSerialDescriptor.addElement("title", true);
        pluginGeneratedSerialDescriptor.addElement("albumTitle", true);
        pluginGeneratedSerialDescriptor.addElement("artist", true);
        pluginGeneratedSerialDescriptor.addElement("playbackDuration", false);
        pluginGeneratedSerialDescriptor.addElement("elapsedPlaybackTime", false);
        pluginGeneratedSerialDescriptor.addElement("playbackRate", false);
        pluginGeneratedSerialDescriptor.addElement("isLoading", true);
        pluginGeneratedSerialDescriptor.addElement("artworkUrl", true);
        pluginGeneratedSerialDescriptor.addElement(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, false);
        pluginGeneratedSerialDescriptor.addElement("playbackSpeed", true);
        pluginGeneratedSerialDescriptor.addElement("shuffleState", true);
        pluginGeneratedSerialDescriptor.addElement("repeatState", true);
        pluginGeneratedSerialDescriptor.addElement("controls", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SkyfirePlaybackStateModel$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        FloatSerializer floatSerializer = FloatSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), IntSerializer.INSTANCE, floatSerializer, floatSerializer, BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(stringSerializer), floatSerializer, floatSerializer, BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.ShuffleControlState", ShuffleControlState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.RepeatControlState", RepeatControlState.values())), SkyfirePlaybackControlsModel$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SkyfirePlaybackStateModel deserialize(Decoder decoder) {
        Object objDecodeNullableSerializableElement;
        float fDecodeFloatElement;
        Object objDecodeSerializableElement;
        Object objDecodeNullableSerializableElement2;
        Object obj;
        float f;
        boolean z;
        float f2;
        int i;
        float f3;
        Object objDecodeNullableSerializableElement3;
        Object objDecodeNullableSerializableElement4;
        int i2;
        Object objDecodeNullableSerializableElement5;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i3 = 9;
        int i4 = 7;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            objDecodeNullableSerializableElement5 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, stringSerializer, null);
            objDecodeNullableSerializableElement4 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, stringSerializer, null);
            objDecodeNullableSerializableElement3 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, stringSerializer, null);
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 3);
            float fDecodeFloatElement2 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 4);
            float fDecodeFloatElement3 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 5);
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
            Object objDecodeNullableSerializableElement6 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, stringSerializer, null);
            float fDecodeFloatElement4 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 8);
            float fDecodeFloatElement5 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 9);
            Object objDecodeNullableSerializableElement7 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, new EnumSerializer("com.amazon.music.skyfire.models.ShuffleControlState", ShuffleControlState.values()), null);
            objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, new EnumSerializer("com.amazon.music.skyfire.models.RepeatControlState", RepeatControlState.values()), null);
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 12, SkyfirePlaybackControlsModel$$serializer.INSTANCE, null);
            fDecodeFloatElement = fDecodeFloatElement5;
            f = fDecodeFloatElement4;
            z = zDecodeBooleanElement;
            f2 = fDecodeFloatElement3;
            f3 = fDecodeFloatElement2;
            i2 = 8191;
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement7;
            obj = objDecodeNullableSerializableElement6;
            i = iDecodeIntElement;
        } else {
            int i5 = 12;
            boolean z2 = true;
            boolean zDecodeBooleanElement2 = false;
            int iDecodeIntElement2 = 0;
            Object objDecodeNullableSerializableElement8 = null;
            Object objDecodeSerializableElement2 = null;
            objDecodeNullableSerializableElement = null;
            Object objDecodeNullableSerializableElement9 = null;
            Object objDecodeNullableSerializableElement10 = null;
            Object objDecodeNullableSerializableElement11 = null;
            fDecodeFloatElement = 0.0f;
            float fDecodeFloatElement6 = 0.0f;
            float fDecodeFloatElement7 = 0.0f;
            float fDecodeFloatElement8 = 0.0f;
            Object objDecodeNullableSerializableElement12 = null;
            int i6 = 0;
            while (z2) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z2 = false;
                        i3 = 9;
                        i4 = 7;
                        break;
                    case 0:
                        objDecodeNullableSerializableElement11 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, objDecodeNullableSerializableElement11);
                        i6 |= 1;
                        i5 = 12;
                        i3 = 9;
                        i4 = 7;
                        break;
                    case 1:
                        objDecodeNullableSerializableElement12 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, StringSerializer.INSTANCE, objDecodeNullableSerializableElement12);
                        i6 |= 2;
                        i5 = 12;
                        i3 = 9;
                        break;
                    case 2:
                        objDecodeNullableSerializableElement8 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, StringSerializer.INSTANCE, objDecodeNullableSerializableElement8);
                        i6 |= 4;
                        i5 = 12;
                        i3 = 9;
                        break;
                    case 3:
                        iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 3);
                        i6 |= 8;
                        i5 = 12;
                        break;
                    case 4:
                        fDecodeFloatElement8 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 4);
                        i6 |= 16;
                        i5 = 12;
                        break;
                    case 5:
                        fDecodeFloatElement7 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 5);
                        i6 |= 32;
                        i5 = 12;
                        break;
                    case 6:
                        zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
                        i6 |= 64;
                        i5 = 12;
                        break;
                    case 7:
                        objDecodeNullableSerializableElement10 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, i4, StringSerializer.INSTANCE, objDecodeNullableSerializableElement10);
                        i6 |= 128;
                        i5 = 12;
                        break;
                    case 8:
                        fDecodeFloatElement6 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 8);
                        i6 |= 256;
                        i5 = 12;
                        break;
                    case 9:
                        fDecodeFloatElement = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, i3);
                        i6 |= 512;
                        i5 = 12;
                        break;
                    case 10:
                        objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, new EnumSerializer("com.amazon.music.skyfire.models.ShuffleControlState", ShuffleControlState.values()), objDecodeNullableSerializableElement);
                        i6 |= 1024;
                        i5 = 12;
                        break;
                    case 11:
                        objDecodeNullableSerializableElement9 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, new EnumSerializer("com.amazon.music.skyfire.models.RepeatControlState", RepeatControlState.values()), objDecodeNullableSerializableElement9);
                        i6 |= 2048;
                        i5 = 12;
                        break;
                    case 12:
                        objDecodeSerializableElement2 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i5, SkyfirePlaybackControlsModel$$serializer.INSTANCE, objDecodeSerializableElement2);
                        i6 |= 4096;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Object obj2 = objDecodeNullableSerializableElement11;
            objDecodeSerializableElement = objDecodeSerializableElement2;
            objDecodeNullableSerializableElement2 = objDecodeNullableSerializableElement9;
            obj = objDecodeNullableSerializableElement10;
            f = fDecodeFloatElement6;
            z = zDecodeBooleanElement2;
            f2 = fDecodeFloatElement7;
            i = iDecodeIntElement2;
            f3 = fDecodeFloatElement8;
            objDecodeNullableSerializableElement3 = objDecodeNullableSerializableElement8;
            objDecodeNullableSerializableElement4 = objDecodeNullableSerializableElement12;
            i2 = i6;
            objDecodeNullableSerializableElement5 = obj2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SkyfirePlaybackStateModel(i2, (String) objDecodeNullableSerializableElement5, (String) objDecodeNullableSerializableElement4, (String) objDecodeNullableSerializableElement3, i, f3, f2, z, (String) obj, f, fDecodeFloatElement, (ShuffleControlState) objDecodeNullableSerializableElement, (RepeatControlState) objDecodeNullableSerializableElement2, (SkyfirePlaybackControlsModel) objDecodeSerializableElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SkyfirePlaybackStateModel value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SkyfirePlaybackStateModel.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
