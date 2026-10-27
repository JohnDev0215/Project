package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: Encoding.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0017J\b\u0010\u0004\u001a\u00020\u0002H'J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\nH&J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\fH&J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u000eH&J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0010H&J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0012H&J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0014H&J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0016H&J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u000eH&J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0018H&J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u0018H&J\u0018\u0010!\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u000eH\u0016J+\u0010%\u001a\u00020\u0002\"\u0004\b\u0000\u0010\"2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010\u0006\u001a\u00028\u0000H\u0016¢\u0006\u0004\b%\u0010&R\u0014\u0010*\u001a\u00020'8&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lkotlinx/serialization/encoding/Encoder;", "", "", "encodeNotNullMark", "encodeNull", "", "value", "encodeBoolean", "", "encodeByte", "", "encodeShort", "", "encodeChar", "", "encodeInt", "", "encodeLong", "", "encodeFloat", "", "encodeDouble", "", "encodeString", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "enumDescriptor", "index", "encodeEnum", "descriptor", "encodeInline", "Lkotlinx/serialization/encoding/CompositeEncoder;", "beginStructure", "collectionSize", "beginCollection", "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "encodeSerializableValue", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public interface Encoder {
    CompositeEncoder beginCollection(SerialDescriptor descriptor, int collectionSize);

    CompositeEncoder beginStructure(SerialDescriptor descriptor);

    void encodeBoolean(boolean value);

    void encodeByte(byte value);

    void encodeChar(char value);

    void encodeDouble(double value);

    void encodeEnum(SerialDescriptor enumDescriptor, int index);

    void encodeFloat(float value);

    Encoder encodeInline(SerialDescriptor descriptor);

    void encodeInt(int value);

    void encodeLong(long value);

    void encodeNotNullMark();

    void encodeNull();

    <T> void encodeSerializableValue(SerializationStrategy<? super T> serializer, T value);

    void encodeShort(short value);

    void encodeString(String value);

    SerializersModule getSerializersModule();

    /* JADX INFO: compiled from: Encoding.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void encodeNotNullMark(Encoder encoder) {
        }

        public static CompositeEncoder beginCollection(Encoder encoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return encoder.beginStructure(descriptor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <T> void encodeSerializableValue(Encoder encoder, SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            serializer.serialize(encoder, t);
        }

        public static <T> void encodeNullableSerializableValue(Encoder encoder, SerializationStrategy<? super T> serializer, T t) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            if (serializer.getDescriptor().isNullable()) {
                encoder.encodeSerializableValue(serializer, t);
            } else if (t == null) {
                encoder.encodeNull();
            } else {
                encoder.encodeNotNullMark();
                encoder.encodeSerializableValue(serializer, t);
            }
        }
    }
}
