package com.amazon.music.platform.find.service.common;

import com.amazon.music.platform.find.common.AssetQuality$$serializer;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.ParentalControls;
import com.amazon.music.platform.find.common.ParentalControls$$serializer;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier$$serializer;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: EntityProperties.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/music/platform/find/service/common/EntityProperties.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class EntityProperties$$serializer implements GeneratedSerializer<EntityProperties> {
    public static final EntityProperties$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        EntityProperties$$serializer entityProperties$$serializer = new EntityProperties$$serializer();
        INSTANCE = entityProperties$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.amazon.music.platform.find.service.common.EntityProperties", entityProperties$$serializer, 19);
        pluginGeneratedSerialDescriptor.addElement("artistAsin", true);
        pluginGeneratedSerialDescriptor.addElement("artistName", true);
        pluginGeneratedSerialDescriptor.addElement("albumAsin", true);
        pluginGeneratedSerialDescriptor.addElement("albumName", true);
        pluginGeneratedSerialDescriptor.addElement("subTitle", true);
        pluginGeneratedSerialDescriptor.addElement("contentTier", true);
        pluginGeneratedSerialDescriptor.addElement("parentalControlsPerTier", true);
        pluginGeneratedSerialDescriptor.addElement("parentControls", true);
        pluginGeneratedSerialDescriptor.addElement("podcastEntityVariantId", true);
        pluginGeneratedSerialDescriptor.addElement("isOnDemand", true);
        pluginGeneratedSerialDescriptor.addElement("isSfa", true);
        pluginGeneratedSerialDescriptor.addElement("curator", true);
        pluginGeneratedSerialDescriptor.addElement("purchased", true);
        pluginGeneratedSerialDescriptor.addElement("durationInMilliseconds", true);
        pluginGeneratedSerialDescriptor.addElement("assetQualities", true);
        pluginGeneratedSerialDescriptor.addElement("languageOfPerformances", true);
        pluginGeneratedSerialDescriptor.addElement("variantId", true);
        pluginGeneratedSerialDescriptor.addElement("onDemand", true);
        pluginGeneratedSerialDescriptor.addElement("sfa", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private EntityProperties$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(new ArrayListSerializer(new EnumSerializer("com.amazon.music.platform.find.common.ContentTier", ContentTier.values()))), BuiltinSerializersKt.getNullable(ParentalControlsPerContentTier$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ParentalControls$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(booleanSerializer), BuiltinSerializersKt.getNullable(booleanSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(booleanSerializer), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(new ArrayListSerializer(AssetQuality$$serializer.INSTANCE)), BuiltinSerializersKt.getNullable(new ArrayListSerializer(stringSerializer)), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(booleanSerializer), BuiltinSerializersKt.getNullable(booleanSerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public EntityProperties deserialize(Decoder decoder) {
        Object objDecodeNullableSerializableElement;
        Object objDecodeNullableSerializableElement2;
        Object objDecodeNullableSerializableElement3;
        Object objDecodeNullableSerializableElement4;
        Object obj;
        int i;
        Object objDecodeNullableSerializableElement5;
        Object objDecodeNullableSerializableElement6;
        Object obj2;
        Object objDecodeNullableSerializableElement7;
        Object objDecodeNullableSerializableElement8;
        Object objDecodeNullableSerializableElement9;
        Object obj3;
        Object objDecodeNullableSerializableElement10;
        Object objDecodeNullableSerializableElement11;
        Object objDecodeNullableSerializableElement12;
        Object objDecodeNullableSerializableElement13;
        Object obj4;
        Object objDecodeNullableSerializableElement14;
        Object objDecodeNullableSerializableElement15;
        Object obj5;
        Object obj6;
        Object objDecodeNullableSerializableElement16;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            Object objDecodeNullableSerializableElement17 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, stringSerializer, null);
            objDecodeNullableSerializableElement13 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, stringSerializer, null);
            Object objDecodeNullableSerializableElement18 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, stringSerializer, null);
            Object objDecodeNullableSerializableElement19 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, stringSerializer, null);
            objDecodeNullableSerializableElement10 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, stringSerializer, null);
            Object objDecodeNullableSerializableElement20 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, new ArrayListSerializer(new EnumSerializer("com.amazon.music.platform.find.common.ContentTier", ContentTier.values())), null);
            objDecodeNullableSerializableElement12 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, ParentalControlsPerContentTier$$serializer.INSTANCE, null);
            Object objDecodeNullableSerializableElement21 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, ParentalControls$$serializer.INSTANCE, null);
            objDecodeNullableSerializableElement15 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, stringSerializer, null);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            objDecodeNullableSerializableElement5 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 9, booleanSerializer, null);
            objDecodeNullableSerializableElement14 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, booleanSerializer, null);
            objDecodeNullableSerializableElement11 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, stringSerializer, null);
            objDecodeNullableSerializableElement9 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 12, booleanSerializer, null);
            objDecodeNullableSerializableElement8 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 13, IntSerializer.INSTANCE, null);
            objDecodeNullableSerializableElement7 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 14, new ArrayListSerializer(AssetQuality$$serializer.INSTANCE), null);
            Object objDecodeNullableSerializableElement22 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 15, new ArrayListSerializer(stringSerializer), null);
            objDecodeNullableSerializableElement6 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 16, stringSerializer, null);
            objDecodeNullableSerializableElement4 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 17, booleanSerializer, null);
            obj2 = objDecodeNullableSerializableElement22;
            obj4 = objDecodeNullableSerializableElement19;
            objDecodeNullableSerializableElement2 = objDecodeNullableSerializableElement18;
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement20;
            i = 524287;
            obj3 = objDecodeNullableSerializableElement21;
            objDecodeNullableSerializableElement3 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 18, booleanSerializer, null);
            obj = objDecodeNullableSerializableElement17;
        } else {
            boolean z = true;
            Object objDecodeNullableSerializableElement23 = null;
            Object objDecodeNullableSerializableElement24 = null;
            Object objDecodeNullableSerializableElement25 = null;
            objDecodeNullableSerializableElement = null;
            Object objDecodeNullableSerializableElement26 = null;
            Object obj13 = null;
            objDecodeNullableSerializableElement2 = null;
            Object objDecodeNullableSerializableElement27 = null;
            Object objDecodeNullableSerializableElement28 = null;
            Object objDecodeNullableSerializableElement29 = null;
            Object objDecodeNullableSerializableElement30 = null;
            Object objDecodeNullableSerializableElement31 = null;
            Object objDecodeNullableSerializableElement32 = null;
            Object objDecodeNullableSerializableElement33 = null;
            Object objDecodeNullableSerializableElement34 = null;
            Object objDecodeNullableSerializableElement35 = null;
            Object objDecodeNullableSerializableElement36 = null;
            Object objDecodeNullableSerializableElement37 = null;
            int i2 = 0;
            Object objDecodeNullableSerializableElement38 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        obj5 = objDecodeNullableSerializableElement38;
                        obj6 = objDecodeNullableSerializableElement23;
                        objDecodeNullableSerializableElement16 = obj13;
                        z = false;
                        objDecodeNullableSerializableElement38 = obj5;
                        obj13 = objDecodeNullableSerializableElement16;
                        objDecodeNullableSerializableElement23 = obj6;
                        break;
                    case 0:
                        obj5 = objDecodeNullableSerializableElement38;
                        obj6 = objDecodeNullableSerializableElement23;
                        obj7 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement16 = obj13;
                        i2 |= 1;
                        objDecodeNullableSerializableElement37 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, objDecodeNullableSerializableElement37);
                        objDecodeNullableSerializableElement24 = obj7;
                        objDecodeNullableSerializableElement38 = obj5;
                        obj13 = objDecodeNullableSerializableElement16;
                        objDecodeNullableSerializableElement23 = obj6;
                        break;
                    case 1:
                        obj6 = objDecodeNullableSerializableElement23;
                        obj7 = objDecodeNullableSerializableElement24;
                        obj5 = objDecodeNullableSerializableElement38;
                        objDecodeNullableSerializableElement16 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, StringSerializer.INSTANCE, obj13);
                        i2 |= 2;
                        objDecodeNullableSerializableElement24 = obj7;
                        objDecodeNullableSerializableElement38 = obj5;
                        obj13 = objDecodeNullableSerializableElement16;
                        objDecodeNullableSerializableElement23 = obj6;
                        break;
                    case 2:
                        obj8 = objDecodeNullableSerializableElement23;
                        obj9 = objDecodeNullableSerializableElement24;
                        obj10 = obj13;
                        objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, StringSerializer.INSTANCE, objDecodeNullableSerializableElement2);
                        i2 |= 4;
                        objDecodeNullableSerializableElement24 = obj9;
                        obj13 = obj10;
                        objDecodeNullableSerializableElement23 = obj8;
                        break;
                    case 3:
                        obj8 = objDecodeNullableSerializableElement23;
                        obj9 = objDecodeNullableSerializableElement24;
                        obj10 = obj13;
                        objDecodeNullableSerializableElement38 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, objDecodeNullableSerializableElement38);
                        i2 |= 8;
                        objDecodeNullableSerializableElement24 = obj9;
                        obj13 = obj10;
                        objDecodeNullableSerializableElement23 = obj8;
                        break;
                    case 4:
                        obj8 = objDecodeNullableSerializableElement23;
                        obj9 = objDecodeNullableSerializableElement24;
                        obj10 = obj13;
                        objDecodeNullableSerializableElement26 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, StringSerializer.INSTANCE, objDecodeNullableSerializableElement26);
                        i2 |= 16;
                        objDecodeNullableSerializableElement24 = obj9;
                        obj13 = obj10;
                        objDecodeNullableSerializableElement23 = obj8;
                        break;
                    case 5:
                        obj9 = objDecodeNullableSerializableElement24;
                        obj10 = obj13;
                        obj8 = objDecodeNullableSerializableElement23;
                        objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, new ArrayListSerializer(new EnumSerializer("com.amazon.music.platform.find.common.ContentTier", ContentTier.values())), objDecodeNullableSerializableElement);
                        i2 |= 32;
                        objDecodeNullableSerializableElement24 = obj9;
                        obj13 = obj10;
                        objDecodeNullableSerializableElement23 = obj8;
                        break;
                    case 6:
                        obj11 = objDecodeNullableSerializableElement24;
                        obj12 = obj13;
                        objDecodeNullableSerializableElement23 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, ParentalControlsPerContentTier$$serializer.INSTANCE, objDecodeNullableSerializableElement23);
                        i2 |= 64;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 7:
                        obj11 = objDecodeNullableSerializableElement24;
                        obj12 = obj13;
                        objDecodeNullableSerializableElement25 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, ParentalControls$$serializer.INSTANCE, objDecodeNullableSerializableElement25);
                        i2 |= 128;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 8:
                        obj11 = objDecodeNullableSerializableElement24;
                        obj12 = obj13;
                        objDecodeNullableSerializableElement29 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, objDecodeNullableSerializableElement29);
                        i2 |= 256;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 9:
                        obj11 = objDecodeNullableSerializableElement24;
                        obj12 = obj13;
                        objDecodeNullableSerializableElement28 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 9, BooleanSerializer.INSTANCE, objDecodeNullableSerializableElement28);
                        i2 |= 512;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 10:
                        obj11 = objDecodeNullableSerializableElement24;
                        obj12 = obj13;
                        objDecodeNullableSerializableElement27 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, BooleanSerializer.INSTANCE, objDecodeNullableSerializableElement27);
                        i2 |= 1024;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 11:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement30 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, StringSerializer.INSTANCE, objDecodeNullableSerializableElement30);
                        i2 |= 2048;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement31 = objDecodeNullableSerializableElement31;
                        obj13 = obj12;
                        break;
                    case 12:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement31 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 12, BooleanSerializer.INSTANCE, objDecodeNullableSerializableElement31);
                        i2 |= 4096;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement32 = objDecodeNullableSerializableElement32;
                        obj13 = obj12;
                        break;
                    case 13:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement32 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 13, IntSerializer.INSTANCE, objDecodeNullableSerializableElement32);
                        i2 |= 8192;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement33 = objDecodeNullableSerializableElement33;
                        obj13 = obj12;
                        break;
                    case 14:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement33 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 14, new ArrayListSerializer(AssetQuality$$serializer.INSTANCE), objDecodeNullableSerializableElement33);
                        i2 |= 16384;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement34 = objDecodeNullableSerializableElement34;
                        obj13 = obj12;
                        break;
                    case 15:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement34 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 15, new ArrayListSerializer(StringSerializer.INSTANCE), objDecodeNullableSerializableElement34);
                        i2 |= 32768;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement35 = objDecodeNullableSerializableElement35;
                        obj13 = obj12;
                        break;
                    case 16:
                        obj12 = obj13;
                        objDecodeNullableSerializableElement35 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 16, StringSerializer.INSTANCE, objDecodeNullableSerializableElement35);
                        i2 |= 65536;
                        objDecodeNullableSerializableElement24 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement36 = objDecodeNullableSerializableElement36;
                        obj13 = obj12;
                        break;
                    case 17:
                        obj12 = obj13;
                        obj11 = objDecodeNullableSerializableElement24;
                        objDecodeNullableSerializableElement36 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 17, BooleanSerializer.INSTANCE, objDecodeNullableSerializableElement36);
                        i2 |= 131072;
                        objDecodeNullableSerializableElement24 = obj11;
                        obj13 = obj12;
                        break;
                    case 18:
                        objDecodeNullableSerializableElement24 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 18, BooleanSerializer.INSTANCE, objDecodeNullableSerializableElement24);
                        i2 |= 262144;
                        obj13 = obj13;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            Object obj14 = objDecodeNullableSerializableElement38;
            Object obj15 = objDecodeNullableSerializableElement23;
            objDecodeNullableSerializableElement3 = objDecodeNullableSerializableElement24;
            Object obj16 = obj13;
            objDecodeNullableSerializableElement4 = objDecodeNullableSerializableElement36;
            obj = objDecodeNullableSerializableElement37;
            i = i2;
            objDecodeNullableSerializableElement5 = objDecodeNullableSerializableElement28;
            objDecodeNullableSerializableElement6 = objDecodeNullableSerializableElement35;
            obj2 = objDecodeNullableSerializableElement34;
            objDecodeNullableSerializableElement7 = objDecodeNullableSerializableElement33;
            objDecodeNullableSerializableElement8 = objDecodeNullableSerializableElement32;
            objDecodeNullableSerializableElement9 = objDecodeNullableSerializableElement31;
            obj3 = objDecodeNullableSerializableElement25;
            objDecodeNullableSerializableElement10 = objDecodeNullableSerializableElement26;
            objDecodeNullableSerializableElement11 = objDecodeNullableSerializableElement30;
            objDecodeNullableSerializableElement12 = obj15;
            objDecodeNullableSerializableElement13 = obj16;
            obj4 = obj14;
            Object obj17 = objDecodeNullableSerializableElement29;
            objDecodeNullableSerializableElement14 = objDecodeNullableSerializableElement27;
            objDecodeNullableSerializableElement15 = obj17;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new EntityProperties(i, (String) obj, (String) objDecodeNullableSerializableElement13, (String) objDecodeNullableSerializableElement2, (String) obj4, (String) objDecodeNullableSerializableElement10, (List) objDecodeNullableSerializableElement, (ParentalControlsPerContentTier) objDecodeNullableSerializableElement12, (ParentalControls) obj3, (String) objDecodeNullableSerializableElement15, (Boolean) objDecodeNullableSerializableElement5, (Boolean) objDecodeNullableSerializableElement14, (String) objDecodeNullableSerializableElement11, (Boolean) objDecodeNullableSerializableElement9, (Integer) objDecodeNullableSerializableElement8, (List) objDecodeNullableSerializableElement7, (List) obj2, (String) objDecodeNullableSerializableElement6, (Boolean) objDecodeNullableSerializableElement4, (Boolean) objDecodeNullableSerializableElement3, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, EntityProperties value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        EntityProperties.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
