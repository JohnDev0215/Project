package kotlinx.serialization;

import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;

/* JADX INFO: compiled from: SealedSerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003J$\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J'\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\t\u0010\u000eR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0019\u001a\u00020\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R0\u0010\u001c\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000f\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001b0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR(\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001b0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/SealedClassSerializer;", "", "T", "Lkotlinx/serialization/internal/AbstractPolymorphicSerializer;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decoder", "", "klassName", "Lkotlinx/serialization/DeserializationStrategy;", "findPolymorphicSerializerOrNull", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)Lkotlinx/serialization/SerializationStrategy;", "Lkotlin/reflect/KClass;", "baseClass", "Lkotlin/reflect/KClass;", "getBaseClass", "()Lkotlin/reflect/KClass;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor$delegate", "Lkotlin/Lazy;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "", "Lkotlinx/serialization/KSerializer;", "class2Serializer", "Ljava/util/Map;", "serialName2Serializer", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public final class SealedClassSerializer<T> extends AbstractPolymorphicSerializer<T> {
    private final KClass<T> baseClass;
    private final Map<KClass<? extends T>, KSerializer<? extends T>> class2Serializer;

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final Lazy descriptor;
    private final Map<String, KSerializer<? extends T>> serialName2Serializer;

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public KClass<T> getBaseClass() {
        return this.baseClass;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor.getValue();
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public DeserializationStrategy<? extends T> findPolymorphicSerializerOrNull(CompositeDecoder decoder, String klassName) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        KSerializer<? extends T> kSerializer = this.serialName2Serializer.get(klassName);
        return kSerializer != null ? kSerializer : super.findPolymorphicSerializerOrNull(decoder, klassName);
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public SerializationStrategy<T> findPolymorphicSerializerOrNull(Encoder encoder, T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        KSerializer<? extends T> kSerializerFindPolymorphicSerializerOrNull = this.class2Serializer.get(Reflection.getOrCreateKotlinClass(value.getClass()));
        if (kSerializerFindPolymorphicSerializerOrNull == null) {
            kSerializerFindPolymorphicSerializerOrNull = super.findPolymorphicSerializerOrNull(encoder, value);
        }
        if (kSerializerFindPolymorphicSerializerOrNull != null) {
            return kSerializerFindPolymorphicSerializerOrNull;
        }
        return null;
    }
}
