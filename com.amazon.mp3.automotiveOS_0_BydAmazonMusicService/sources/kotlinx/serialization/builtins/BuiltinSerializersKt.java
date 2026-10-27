package kotlinx.serialization.builtins;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import java.util.List;
import java.util.Map;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.jvm.internal.BooleanCompanionObject;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.time.Duration;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanArraySerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.CharArraySerializer;
import kotlinx.serialization.internal.CharSerializer;
import kotlinx.serialization.internal.DoubleArraySerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.DurationSerializer;
import kotlinx.serialization.internal.FloatArraySerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LongArraySerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.MapEntrySerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.ShortArraySerializer;
import kotlinx.serialization.internal.ShortSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.TripleSerializer;
import kotlinx.serialization.internal.UByteArraySerializer;
import kotlinx.serialization.internal.UByteSerializer;
import kotlinx.serialization.internal.UIntArraySerializer;
import kotlinx.serialization.internal.UIntSerializer;
import kotlinx.serialization.internal.ULongArraySerializer;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.internal.UShortArraySerializer;
import kotlinx.serialization.internal.UShortSerializer;
import kotlinx.serialization.internal.UnitSerializer;

/* JADX INFO: compiled from: BuiltinSerializers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010&\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a@\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u001a@\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u001aZ\u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000f0\u0002\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u0002\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0002*\u00020\u0011\u001a\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0002\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00170\u0002*\u00020\u0016\u001a\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0002\u001a\u0011\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0002H\u0007ø\u0001\u0000\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0002*\u00020\u001c\u001a\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0002\u001a\u0011\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0002H\u0007ø\u0001\u0000\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020#0\u0002*\u00020\"\u001a\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u0002\u001a\u0011\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u0002H\u0007ø\u0001\u0000\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020)0\u0002*\u00020(\u001a\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u0002\u001a\u0011\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u0002H\u0007ø\u0001\u0000\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020/0\u0002*\u00020.\u001a\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u0002\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002030\u0002*\u000202\u001a\f\u00105\u001a\b\u0012\u0004\u0012\u0002040\u0002\u001a\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002070\u0002*\u000206¢\u0006\u0004\b\u0013\u00108\u001a\f\u0010:\u001a\b\u0012\u0004\u0012\u0002090\u0002\u001a\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020;0\u0002*\u00020;¢\u0006\u0004\b\u0013\u0010<\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020>0\u0002*\u00020=\u001aF\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010E0\u0002\"\b\b\u0000\u0010@*\u00020?\"\n\b\u0001\u0010A*\u0004\u0018\u00018\u00002\f\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000B2\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0007\u001a&\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000G0\u0002\"\u0004\b\u0000\u0010@2\f\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u001a@\u0010J\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010I0\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u001a\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020L0\u0002*\u00020Kø\u0001\u0000\u001a\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020N0\u0002*\u00020Mø\u0001\u0000\u001a\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020P0\u0002*\u00020Oø\u0001\u0000\u001a\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020R0\u0002*\u00020Qø\u0001\u0000\u001a\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020T0\u0002*\u00020Sø\u0001\u0000\"3\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0002\"\b\b\u0000\u0010@*\u00020?*\b\u0012\u0004\u0012\u00028\u00000\u00028F¢\u0006\f\u0012\u0004\bW\u0010X\u001a\u0004\bU\u0010V\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Z"}, d2 = {"K", "V", "Lkotlinx/serialization/KSerializer;", "keySerializer", "valueSerializer", "Lkotlin/Pair;", "PairSerializer", "", "MapEntrySerializer", "A", "B", PlatformWeblabs.C, "aSerializer", "bSerializer", "cSerializer", "Lkotlin/Triple;", "TripleSerializer", "Lkotlin/Char$Companion;", "", "serializer", "", "CharArraySerializer", "Lkotlin/Byte$Companion;", "", "", "ByteArraySerializer", "Lkotlin/UByteArray;", "UByteArraySerializer", "Lkotlin/Short$Companion;", "", "", "ShortArraySerializer", "Lkotlin/UShortArray;", "UShortArraySerializer", "Lkotlin/Int$Companion;", "", "", "IntArraySerializer", "Lkotlin/UIntArray;", "UIntArraySerializer", "Lkotlin/Long$Companion;", "", "", "LongArraySerializer", "Lkotlin/ULongArray;", "ULongArraySerializer", "Lkotlin/Float$Companion;", "", "", "FloatArraySerializer", "Lkotlin/Double$Companion;", "", "", "DoubleArraySerializer", "Lkotlin/Boolean$Companion;", "", "(Lkotlin/jvm/internal/BooleanCompanionObject;)Lkotlinx/serialization/KSerializer;", "", "BooleanArraySerializer", "", "(Lkotlin/Unit;)Lkotlinx/serialization/KSerializer;", "Lkotlin/String$Companion;", "", "", "T", "E", "Lkotlin/reflect/KClass;", "kClass", "elementSerializer", "", "ArraySerializer", "", "ListSerializer", "", "MapSerializer", "Lkotlin/UInt$Companion;", "Lkotlin/UInt;", "Lkotlin/ULong$Companion;", "Lkotlin/ULong;", "Lkotlin/UByte$Companion;", "Lkotlin/UByte;", "Lkotlin/UShort$Companion;", "Lkotlin/UShort;", "Lkotlin/time/Duration$Companion;", "Lkotlin/time/Duration;", "getNullable", "(Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "getNullable$annotations", "(Lkotlinx/serialization/KSerializer;)V", "nullable", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class BuiltinSerializersKt {
    public static final <T> KSerializer<T> getNullable(KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        return kSerializer.getDescriptor().isNullable() ? kSerializer : new NullableSerializer(kSerializer);
    }

    public static final <K, V> KSerializer<Pair<K, V>> PairSerializer(KSerializer<K> keySerializer, KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new PairSerializer(keySerializer, valueSerializer);
    }

    public static final <K, V> KSerializer<Map.Entry<K, V>> MapEntrySerializer(KSerializer<K> keySerializer, KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new MapEntrySerializer(keySerializer, valueSerializer);
    }

    public static final <A, B, C> KSerializer<Triple<A, B, C>> TripleSerializer(KSerializer<A> aSerializer, KSerializer<B> bSerializer, KSerializer<C> cSerializer) {
        Intrinsics.checkNotNullParameter(aSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(bSerializer, "bSerializer");
        Intrinsics.checkNotNullParameter(cSerializer, "cSerializer");
        return new TripleSerializer(aSerializer, bSerializer, cSerializer);
    }

    public static final KSerializer<Character> serializer(CharCompanionObject charCompanionObject) {
        Intrinsics.checkNotNullParameter(charCompanionObject, "<this>");
        return CharSerializer.INSTANCE;
    }

    public static final KSerializer<char[]> CharArraySerializer() {
        return CharArraySerializer.INSTANCE;
    }

    public static final KSerializer<Byte> serializer(ByteCompanionObject byteCompanionObject) {
        Intrinsics.checkNotNullParameter(byteCompanionObject, "<this>");
        return ByteSerializer.INSTANCE;
    }

    public static final KSerializer<byte[]> ByteArraySerializer() {
        return ByteArraySerializer.INSTANCE;
    }

    @ExperimentalUnsignedTypes
    public static final KSerializer<UByteArray> UByteArraySerializer() {
        return UByteArraySerializer.INSTANCE;
    }

    public static final KSerializer<Short> serializer(ShortCompanionObject shortCompanionObject) {
        Intrinsics.checkNotNullParameter(shortCompanionObject, "<this>");
        return ShortSerializer.INSTANCE;
    }

    public static final KSerializer<short[]> ShortArraySerializer() {
        return ShortArraySerializer.INSTANCE;
    }

    @ExperimentalUnsignedTypes
    public static final KSerializer<UShortArray> UShortArraySerializer() {
        return UShortArraySerializer.INSTANCE;
    }

    public static final KSerializer<Integer> serializer(IntCompanionObject intCompanionObject) {
        Intrinsics.checkNotNullParameter(intCompanionObject, "<this>");
        return IntSerializer.INSTANCE;
    }

    public static final KSerializer<int[]> IntArraySerializer() {
        return IntArraySerializer.INSTANCE;
    }

    @ExperimentalUnsignedTypes
    public static final KSerializer<UIntArray> UIntArraySerializer() {
        return UIntArraySerializer.INSTANCE;
    }

    public static final KSerializer<Long> serializer(LongCompanionObject longCompanionObject) {
        Intrinsics.checkNotNullParameter(longCompanionObject, "<this>");
        return LongSerializer.INSTANCE;
    }

    public static final KSerializer<long[]> LongArraySerializer() {
        return LongArraySerializer.INSTANCE;
    }

    @ExperimentalUnsignedTypes
    public static final KSerializer<ULongArray> ULongArraySerializer() {
        return ULongArraySerializer.INSTANCE;
    }

    public static final KSerializer<Float> serializer(FloatCompanionObject floatCompanionObject) {
        Intrinsics.checkNotNullParameter(floatCompanionObject, "<this>");
        return FloatSerializer.INSTANCE;
    }

    public static final KSerializer<float[]> FloatArraySerializer() {
        return FloatArraySerializer.INSTANCE;
    }

    public static final KSerializer<Double> serializer(DoubleCompanionObject doubleCompanionObject) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject, "<this>");
        return DoubleSerializer.INSTANCE;
    }

    public static final KSerializer<double[]> DoubleArraySerializer() {
        return DoubleArraySerializer.INSTANCE;
    }

    public static final KSerializer<Boolean> serializer(BooleanCompanionObject booleanCompanionObject) {
        Intrinsics.checkNotNullParameter(booleanCompanionObject, "<this>");
        return BooleanSerializer.INSTANCE;
    }

    public static final KSerializer<boolean[]> BooleanArraySerializer() {
        return BooleanArraySerializer.INSTANCE;
    }

    public static final KSerializer<Unit> serializer(Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "<this>");
        return UnitSerializer.INSTANCE;
    }

    public static final KSerializer<String> serializer(StringCompanionObject stringCompanionObject) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        return StringSerializer.INSTANCE;
    }

    public static final <T, E extends T> KSerializer<E[]> ArraySerializer(KClass<T> kClass, KSerializer<E> elementSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ReferenceArraySerializer(kClass, elementSerializer);
    }

    public static final <T> KSerializer<List<T>> ListSerializer(KSerializer<T> elementSerializer) {
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ArrayListSerializer(elementSerializer);
    }

    public static final <K, V> KSerializer<Map<K, V>> MapSerializer(KSerializer<K> keySerializer, KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new LinkedHashMapSerializer(keySerializer, valueSerializer);
    }

    public static final KSerializer<UInt> serializer(UInt.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UIntSerializer.INSTANCE;
    }

    public static final KSerializer<ULong> serializer(ULong.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ULongSerializer.INSTANCE;
    }

    public static final KSerializer<UByte> serializer(UByte.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UByteSerializer.INSTANCE;
    }

    public static final KSerializer<UShort> serializer(UShort.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UShortSerializer.INSTANCE;
    }

    public static final KSerializer<Duration> serializer(Duration.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return DurationSerializer.INSTANCE;
    }
}
