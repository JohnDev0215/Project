package com.amazon.music.skyfire.models;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel;
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
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: PlaybackModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/music/skyfire/models/SkyfirePlaybackControlsModel.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/skyfire/models/SkyfirePlaybackControlsModel;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "DMSFCoreAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class SkyfirePlaybackControlsModel$$serializer implements GeneratedSerializer<SkyfirePlaybackControlsModel> {
    public static final SkyfirePlaybackControlsModel$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        SkyfirePlaybackControlsModel$$serializer skyfirePlaybackControlsModel$$serializer = new SkyfirePlaybackControlsModel$$serializer();
        INSTANCE = skyfirePlaybackControlsModel$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel", skyfirePlaybackControlsModel$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement(PageUriUtils.DO_PLAY_VALUE, true);
        pluginGeneratedSerialDescriptor.addElement("next", true);
        pluginGeneratedSerialDescriptor.addElement("previous", true);
        pluginGeneratedSerialDescriptor.addElement("skipBackward", true);
        pluginGeneratedSerialDescriptor.addElement("skipForward", true);
        pluginGeneratedSerialDescriptor.addElement("repeat", true);
        pluginGeneratedSerialDescriptor.addElement("shuffle", true);
        pluginGeneratedSerialDescriptor.addElement("playbackSpeed", true);
        pluginGeneratedSerialDescriptor.addElement("playbackSpeedOptions", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SkyfirePlaybackControlsModel$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values())), BuiltinSerializersKt.getNullable(new ArrayListSerializer(SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE))};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SkyfirePlaybackControlsModel deserialize(Decoder decoder) {
        Object objDecodeNullableSerializableElement;
        Object objDecodeNullableSerializableElement2;
        Object objDecodeNullableSerializableElement3;
        Object objDecodeNullableSerializableElement4;
        Object objDecodeNullableSerializableElement5;
        Object objDecodeNullableSerializableElement6;
        Object objDecodeNullableSerializableElement7;
        Object objDecodeNullableSerializableElement8;
        int i;
        Object objDecodeNullableSerializableElement9;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        char c = 7;
        Object objDecodeNullableSerializableElement10 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            objDecodeNullableSerializableElement9 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement7 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement8 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement5 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement6 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement4 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement3 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), null);
            objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, new ArrayListSerializer(SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE), null);
            i = 511;
        } else {
            int i2 = 0;
            boolean z = true;
            Object objDecodeNullableSerializableElement11 = null;
            Object objDecodeNullableSerializableElement12 = null;
            Object objDecodeNullableSerializableElement13 = null;
            Object objDecodeNullableSerializableElement14 = null;
            Object objDecodeNullableSerializableElement15 = null;
            Object objDecodeNullableSerializableElement16 = null;
            Object objDecodeNullableSerializableElement17 = null;
            Object objDecodeNullableSerializableElement18 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        objDecodeNullableSerializableElement10 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement10);
                        i2 |= 1;
                        c = 7;
                        break;
                    case 1:
                        objDecodeNullableSerializableElement17 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement17);
                        i2 |= 2;
                        c = 7;
                        break;
                    case 2:
                        objDecodeNullableSerializableElement18 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement18);
                        i2 |= 4;
                        c = 7;
                        break;
                    case 3:
                        objDecodeNullableSerializableElement15 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement15);
                        i2 |= 8;
                        c = 7;
                        break;
                    case 4:
                        objDecodeNullableSerializableElement16 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement16);
                        i2 |= 16;
                        c = 7;
                        break;
                    case 5:
                        objDecodeNullableSerializableElement14 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement14);
                        i2 |= 32;
                        c = 7;
                        break;
                    case 6:
                        objDecodeNullableSerializableElement12 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement12);
                        i2 |= 64;
                        c = 7;
                        break;
                    case 7:
                        objDecodeNullableSerializableElement13 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, new EnumSerializer("com.amazon.music.skyfire.models.SkyfirePlaybackControlsModel.ButtonState", SkyfirePlaybackControlsModel.ButtonState.values()), objDecodeNullableSerializableElement13);
                        i2 |= 128;
                        c = 7;
                        break;
                    case 8:
                        objDecodeNullableSerializableElement11 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, new ArrayListSerializer(SkyfirePlaybackControlsModel$PlaybackSpeedOption$$serializer.INSTANCE), objDecodeNullableSerializableElement11);
                        i2 |= 256;
                        c = 7;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement11;
            objDecodeNullableSerializableElement2 = objDecodeNullableSerializableElement12;
            objDecodeNullableSerializableElement3 = objDecodeNullableSerializableElement13;
            objDecodeNullableSerializableElement4 = objDecodeNullableSerializableElement14;
            objDecodeNullableSerializableElement5 = objDecodeNullableSerializableElement15;
            objDecodeNullableSerializableElement6 = objDecodeNullableSerializableElement16;
            objDecodeNullableSerializableElement7 = objDecodeNullableSerializableElement17;
            objDecodeNullableSerializableElement8 = objDecodeNullableSerializableElement18;
            Object obj = objDecodeNullableSerializableElement10;
            i = i2;
            objDecodeNullableSerializableElement9 = obj;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SkyfirePlaybackControlsModel(i, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement9, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement7, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement8, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement5, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement6, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement4, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement2, (SkyfirePlaybackControlsModel.ButtonState) objDecodeNullableSerializableElement3, (List) objDecodeNullableSerializableElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SkyfirePlaybackControlsModel value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SkyfirePlaybackControlsModel.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
