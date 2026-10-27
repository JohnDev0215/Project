package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Decoding.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H'J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'J\b\u0010\u0006\u001a\u00020\u0002H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\tH&J\b\u0010\f\u001a\u00020\u000bH&J\b\u0010\u000e\u001a\u00020\rH&J\b\u0010\u0010\u001a\u00020\u000fH&J\b\u0010\u0012\u001a\u00020\u0011H&J\b\u0010\u0014\u001a\u00020\u0013H&J\b\u0010\u0016\u001a\u00020\u0015H&J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017H&J\u0010\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0017H&J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0017H&J#\u0010!\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016¢\u0006\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lkotlinx/serialization/encoding/Decoder;", "", "", "decodeNotNullMark", "", "decodeNull", "decodeBoolean", "", "decodeByte", "", "decodeShort", "", "decodeChar", "", "decodeInt", "", "decodeLong", "", "decodeFloat", "", "decodeDouble", "", "decodeString", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "enumDescriptor", "decodeEnum", "descriptor", "decodeInline", "Lkotlinx/serialization/encoding/CompositeDecoder;", "beginStructure", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public interface Decoder {
    CompositeDecoder beginStructure(SerialDescriptor descriptor);

    boolean decodeBoolean();

    byte decodeByte();

    char decodeChar();

    double decodeDouble();

    int decodeEnum(SerialDescriptor enumDescriptor);

    float decodeFloat();

    Decoder decodeInline(SerialDescriptor descriptor);

    int decodeInt();

    long decodeLong();

    boolean decodeNotNullMark();

    Void decodeNull();

    <T> T decodeSerializableValue(DeserializationStrategy<T> deserializer);

    short decodeShort();

    String decodeString();

    /* JADX INFO: compiled from: Decoding.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static <T> T decodeSerializableValue(Decoder decoder, DeserializationStrategy<T> deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return deserializer.deserialize(decoder);
        }
    }
}
