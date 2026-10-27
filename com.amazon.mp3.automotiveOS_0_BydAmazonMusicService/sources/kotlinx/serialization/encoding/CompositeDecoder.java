package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: Decoding.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0006H\u0017J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J?\u0010\"\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0004\b\"\u0010#JG\u0010$\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u001e*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\b2\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00018\u0000H'¢\u0006\u0004\b$\u0010#R\u0014\u0010(\u001a\u00020%8&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lkotlinx/serialization/encoding/CompositeDecoder;", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "", "endStructure", "", "decodeSequentially", "", "decodeElementIndex", "decodeCollectionSize", "index", "decodeBooleanElement", "", "decodeByteElement", "", "decodeCharElement", "", "decodeShortElement", "decodeIntElement", "", "decodeLongElement", "", "decodeFloatElement", "", "decodeDoubleElement", "", "decodeStringElement", "Lkotlinx/serialization/encoding/Decoder;", "decodeInlineElement", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "previousValue", "decodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeNullableSerializableElement", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public interface CompositeDecoder {
    boolean decodeBooleanElement(SerialDescriptor descriptor, int index);

    byte decodeByteElement(SerialDescriptor descriptor, int index);

    char decodeCharElement(SerialDescriptor descriptor, int index);

    int decodeCollectionSize(SerialDescriptor descriptor);

    double decodeDoubleElement(SerialDescriptor descriptor, int index);

    int decodeElementIndex(SerialDescriptor descriptor);

    float decodeFloatElement(SerialDescriptor descriptor, int index);

    Decoder decodeInlineElement(SerialDescriptor descriptor, int index);

    int decodeIntElement(SerialDescriptor descriptor, int index);

    long decodeLongElement(SerialDescriptor descriptor, int index);

    <T> T decodeNullableSerializableElement(SerialDescriptor descriptor, int index, DeserializationStrategy<T> deserializer, T previousValue);

    boolean decodeSequentially();

    <T> T decodeSerializableElement(SerialDescriptor descriptor, int index, DeserializationStrategy<T> deserializer, T previousValue);

    short decodeShortElement(SerialDescriptor descriptor, int index);

    String decodeStringElement(SerialDescriptor descriptor, int index);

    void endStructure(SerialDescriptor descriptor);

    SerializersModule getSerializersModule();

    /* JADX INFO: compiled from: Decoding.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static int decodeCollectionSize(CompositeDecoder compositeDecoder, SerialDescriptor descriptor) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return -1;
        }

        public static boolean decodeSequentially(CompositeDecoder compositeDecoder) {
            return false;
        }

        public static /* synthetic */ Object decodeSerializableElement$default(CompositeDecoder compositeDecoder, SerialDescriptor serialDescriptor, int i, DeserializationStrategy deserializationStrategy, Object obj, int i2, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeSerializableElement");
            }
            if ((i2 & 8) != 0) {
                obj = null;
            }
            return compositeDecoder.decodeSerializableElement(serialDescriptor, i, deserializationStrategy, obj);
        }
    }
}
