package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Encoding.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0017J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH&J \u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\fH&J \u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000eH&J \u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0010H&J \u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H&J \u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0013H&J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0015H&J \u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0017H&J \u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0019H&J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&J;\u0010 \u001a\u00020\u0004\"\u0004\b\u0000\u0010\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0004\b \u0010!JA\u0010\"\u001a\u00020\u0004\"\b\b\u0000\u0010\u001d*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\b\u0010\n\u001a\u0004\u0018\u00018\u0000H'¢\u0006\u0004\b\"\u0010!¨\u0006#"}, d2 = {"Lkotlinx/serialization/encoding/CompositeEncoder;", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "", "endStructure", "", "index", "", "shouldEncodeElementDefault", "value", "encodeBooleanElement", "", "encodeByteElement", "", "encodeShortElement", "", "encodeCharElement", "encodeIntElement", "", "encodeLongElement", "", "encodeFloatElement", "", "encodeDoubleElement", "", "encodeStringElement", "Lkotlinx/serialization/encoding/Encoder;", "encodeInlineElement", "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "encodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNullableSerializableElement", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public interface CompositeEncoder {

    /* JADX INFO: compiled from: Encoding.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static boolean shouldEncodeElementDefault(CompositeEncoder compositeEncoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return true;
        }
    }

    void encodeBooleanElement(SerialDescriptor descriptor, int index, boolean value);

    void encodeByteElement(SerialDescriptor descriptor, int index, byte value);

    void encodeCharElement(SerialDescriptor descriptor, int index, char value);

    void encodeDoubleElement(SerialDescriptor descriptor, int index, double value);

    void encodeFloatElement(SerialDescriptor descriptor, int index, float value);

    Encoder encodeInlineElement(SerialDescriptor descriptor, int index);

    void encodeIntElement(SerialDescriptor descriptor, int index, int value);

    void encodeLongElement(SerialDescriptor descriptor, int index, long value);

    <T> void encodeNullableSerializableElement(SerialDescriptor descriptor, int index, SerializationStrategy<? super T> serializer, T value);

    <T> void encodeSerializableElement(SerialDescriptor descriptor, int index, SerializationStrategy<? super T> serializer, T value);

    void encodeShortElement(SerialDescriptor descriptor, int index, short value);

    void encodeStringElement(SerialDescriptor descriptor, int index, String value);

    void endStructure(SerialDescriptor descriptor);

    boolean shouldEncodeElementDefault(SerialDescriptor descriptor, int index);
}
