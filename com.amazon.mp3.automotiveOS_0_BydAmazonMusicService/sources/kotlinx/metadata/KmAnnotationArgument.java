package kotlinx.metadata;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import java.util.List;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: annotations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0012\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument;", "", "()V", "AnnotationValue", "ArrayValue", "BooleanValue", "ByteValue", "CharValue", "DoubleValue", "EnumValue", "FloatValue", "IntValue", "KClassValue", "LiteralValue", "LongValue", "ShortValue", "StringValue", "UByteValue", "UIntValue", "ULongValue", "UShortValue", "Lkotlinx/metadata/KmAnnotationArgument$AnnotationValue;", "Lkotlinx/metadata/KmAnnotationArgument$ArrayValue;", "Lkotlinx/metadata/KmAnnotationArgument$EnumValue;", "Lkotlinx/metadata/KmAnnotationArgument$KClassValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class KmAnnotationArgument {
    public /* synthetic */ KmAnnotationArgument(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KmAnnotationArgument() {
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\r\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "T", "Lkotlinx/metadata/KmAnnotationArgument;", "<init>", "()V", "Lkotlinx/metadata/KmAnnotationArgument$BooleanValue;", "Lkotlinx/metadata/KmAnnotationArgument$ByteValue;", "Lkotlinx/metadata/KmAnnotationArgument$CharValue;", "Lkotlinx/metadata/KmAnnotationArgument$DoubleValue;", "Lkotlinx/metadata/KmAnnotationArgument$FloatValue;", "Lkotlinx/metadata/KmAnnotationArgument$IntValue;", "Lkotlinx/metadata/KmAnnotationArgument$LongValue;", "Lkotlinx/metadata/KmAnnotationArgument$ShortValue;", "Lkotlinx/metadata/KmAnnotationArgument$StringValue;", "Lkotlinx/metadata/KmAnnotationArgument$UByteValue;", "Lkotlinx/metadata/KmAnnotationArgument$UIntValue;", "Lkotlinx/metadata/KmAnnotationArgument$ULongValue;", "Lkotlinx/metadata/KmAnnotationArgument$UShortValue;", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static abstract class LiteralValue<T> extends KmAnnotationArgument {
        public /* synthetic */ LiteralValue(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private LiteralValue() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$ByteValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "B", "getValue", "()Ljava/lang/Byte;", "<init>", "(B)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ByteValue extends LiteralValue<Byte> {
        private final byte value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ByteValue) && getValue().byteValue() == ((ByteValue) other).getValue().byteValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "ByteValue(value=" + ((int) getValue().byteValue()) + ')';
        }

        public ByteValue(byte b) {
            super(null);
            this.value = b;
        }

        public Byte getValue() {
            return Byte.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$CharValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", PlatformWeblabs.C, "getValue", "()Ljava/lang/Character;", "<init>", "(C)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class CharValue extends LiteralValue<Character> {
        private final char value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CharValue) && getValue().charValue() == ((CharValue) other).getValue().charValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "CharValue(value=" + getValue().charValue() + ')';
        }

        public CharValue(char c) {
            super(null);
            this.value = c;
        }

        public Character getValue() {
            return Character.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$ShortValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "S", "getValue", "()Ljava/lang/Short;", "<init>", "(S)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ShortValue extends LiteralValue<Short> {
        private final short value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ShortValue) && getValue().shortValue() == ((ShortValue) other).getValue().shortValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "ShortValue(value=" + ((int) getValue().shortValue()) + ')';
        }

        public ShortValue(short s) {
            super(null);
            this.value = s;
        }

        public Short getValue() {
            return Short.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$IntValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "hashCode", "", "other", "", "equals", "value", "I", "getValue", "()Ljava/lang/Integer;", "<init>", "(I)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class IntValue extends LiteralValue<Integer> {
        private final int value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof IntValue) && getValue().intValue() == ((IntValue) other).getValue().intValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "IntValue(value=" + getValue().intValue() + ')';
        }

        public IntValue(int i) {
            super(null);
            this.value = i;
        }

        public Integer getValue() {
            return Integer.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$LongValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "J", "getValue", "()Ljava/lang/Long;", "<init>", "(J)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class LongValue extends LiteralValue<Long> {
        private final long value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LongValue) && getValue().longValue() == ((LongValue) other).getValue().longValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "LongValue(value=" + getValue().longValue() + ')';
        }

        public LongValue(long j) {
            super(null);
            this.value = j;
        }

        public Long getValue() {
            return Long.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$FloatValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "F", "getValue", "()Ljava/lang/Float;", "<init>", "(F)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FloatValue extends LiteralValue<Float> {
        private final float value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FloatValue) && Intrinsics.areEqual((Object) getValue(), (Object) ((FloatValue) other).getValue());
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "FloatValue(value=" + getValue().floatValue() + ')';
        }

        public FloatValue(float f) {
            super(null);
            this.value = f;
        }

        public Float getValue() {
            return Float.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$DoubleValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "D", "getValue", "()Ljava/lang/Double;", "<init>", "(D)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class DoubleValue extends LiteralValue<Double> {
        private final double value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DoubleValue) && Intrinsics.areEqual((Object) getValue(), (Object) ((DoubleValue) other).getValue());
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "DoubleValue(value=" + getValue().doubleValue() + ')';
        }

        public DoubleValue(double d) {
            super(null);
            this.value = d;
        }

        public Double getValue() {
            return Double.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\t\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$BooleanValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "", "toString", "", "hashCode", "", "other", "equals", "value", "Z", "getValue", "()Ljava/lang/Boolean;", "<init>", "(Z)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class BooleanValue extends LiteralValue<Boolean> {
        private final boolean value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BooleanValue) && getValue().booleanValue() == ((BooleanValue) other).getValue().booleanValue();
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "BooleanValue(value=" + getValue().booleanValue() + ')';
        }

        public BooleanValue(boolean z) {
            super(null);
            this.value = z;
        }

        public Boolean getValue() {
            return Boolean.valueOf(this.value);
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0012\u0012\u0006\u0010\u000b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R#\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$UByteValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "Lkotlin/UByte;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "B", "getValue-w2LRezQ", "()B", "<init>", "(BLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    @ExperimentalUnsignedTypes
    public static final /* data */ class UByteValue extends LiteralValue<UByte> {
        private final byte value;

        public /* synthetic */ UByteValue(byte b, DefaultConstructorMarker defaultConstructorMarker) {
            this(b);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UByteValue) && getValue() == ((UByteValue) other).getValue();
        }

        public int hashCode() {
            return UByte.m2840hashCodeimpl(getValue());
        }

        public String toString() {
            return "UByteValue(value=" + ((Object) UByte.m2871toStringimpl(getValue())) + ')';
        }

        private UByteValue(byte b) {
            super(null);
            this.value = b;
        }

        /* JADX INFO: renamed from: getValue-w2LRezQ, reason: not valid java name and from getter */
        public byte getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0012\u0012\u0006\u0010\u000b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R#\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$UShortValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "Lkotlin/UShort;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "S", "getValue-Mh2AYeg", "()S", "<init>", "(SLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    @ExperimentalUnsignedTypes
    public static final /* data */ class UShortValue extends LiteralValue<UShort> {
        private final short value;

        public /* synthetic */ UShortValue(short s, DefaultConstructorMarker defaultConstructorMarker) {
            this(s);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UShortValue) && getValue() == ((UShortValue) other).getValue();
        }

        public int hashCode() {
            return UShort.m3100hashCodeimpl(getValue());
        }

        public String toString() {
            return "UShortValue(value=" + ((Object) UShort.m3131toStringimpl(getValue())) + ')';
        }

        private UShortValue(short s) {
            super(null);
            this.value = s;
        }

        /* JADX INFO: renamed from: getValue-Mh2AYeg, reason: not valid java name and from getter */
        public short getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0012\u0012\u0006\u0010\u000b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R#\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$UIntValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "Lkotlin/UInt;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "I", "getValue-pVg5ArA", "()I", "<init>", "(ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    @ExperimentalUnsignedTypes
    public static final /* data */ class UIntValue extends LiteralValue<UInt> {
        private final int value;

        public /* synthetic */ UIntValue(int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UIntValue) && getValue() == ((UIntValue) other).getValue();
        }

        public int hashCode() {
            return UInt.m2916hashCodeimpl(getValue());
        }

        public String toString() {
            return "UIntValue(value=" + ((Object) UInt.m2949toStringimpl(getValue())) + ')';
        }

        private UIntValue(int i) {
            super(null);
            this.value = i;
        }

        /* JADX INFO: renamed from: getValue-pVg5ArA, reason: not valid java name and from getter */
        public int getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0012\u0012\u0006\u0010\u000b\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R#\u0010\u000b\u001a\u00020\u00028\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$ULongValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "Lkotlin/ULong;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "J", "getValue-s-VKNKU", "()J", "<init>", "(JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    @ExperimentalUnsignedTypes
    public static final /* data */ class ULongValue extends LiteralValue<ULong> {
        private final long value;

        public /* synthetic */ ULongValue(long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ULongValue) && getValue() == ((ULongValue) other).getValue();
        }

        public int hashCode() {
            return ULong.m2994hashCodeimpl(getValue());
        }

        public String toString() {
            return "ULongValue(value=" + ((Object) ULong.m3027toStringimpl(getValue())) + ')';
        }

        private ULongValue(long j) {
            super(null);
            this.value = j;
        }

        /* JADX INFO: renamed from: getValue-s-VKNKU, reason: not valid java name and from getter */
        public long getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$StringValue;", "Lkotlinx/metadata/KmAnnotationArgument$LiteralValue;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class StringValue extends LiteralValue<String> {
        private final String value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StringValue) && Intrinsics.areEqual(getValue(), ((StringValue) other).getValue());
        }

        public int hashCode() {
            return getValue().hashCode();
        }

        public String toString() {
            return "StringValue(value=" + getValue() + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StringValue(String value) {
            super(null);
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u000b\u001a\u00060\u0002j\u0002`\n\u0012\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001b\u0010\u000b\u001a\u00060\u0002j\u0002`\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$KClassValue;", "Lkotlinx/metadata/KmAnnotationArgument;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lkotlinx/metadata/ClassName;", "className", "Ljava/lang/String;", "getClassName", "()Ljava/lang/String;", "arrayDimensionCount", "I", "getArrayDimensionCount", "()I", "<init>", "(Ljava/lang/String;I)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class KClassValue extends KmAnnotationArgument {
        private final int arrayDimensionCount;
        private final String className;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KClassValue)) {
                return false;
            }
            KClassValue kClassValue = (KClassValue) other;
            return Intrinsics.areEqual(this.className, kClassValue.className) && this.arrayDimensionCount == kClassValue.arrayDimensionCount;
        }

        public int hashCode() {
            return (this.className.hashCode() * 31) + Integer.hashCode(this.arrayDimensionCount);
        }

        public String toString() {
            return "KClassValue(className=" + this.className + ", arrayDimensionCount=" + this.arrayDimensionCount + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KClassValue(String className, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(className, "className");
            this.className = className;
            this.arrayDimensionCount = i;
        }

        public final int getArrayDimensionCount() {
            return this.arrayDimensionCount;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u000b\u001a\u00060\u0002j\u0002`\n\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001b\u0010\u000b\u001a\u00060\u0002j\u0002`\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$EnumValue;", "Lkotlinx/metadata/KmAnnotationArgument;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lkotlinx/metadata/ClassName;", "enumClassName", "Ljava/lang/String;", "getEnumClassName", "()Ljava/lang/String;", "enumEntryName", "getEnumEntryName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class EnumValue extends KmAnnotationArgument {
        private final String enumClassName;
        private final String enumEntryName;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnumValue)) {
                return false;
            }
            EnumValue enumValue = (EnumValue) other;
            return Intrinsics.areEqual(this.enumClassName, enumValue.enumClassName) && Intrinsics.areEqual(this.enumEntryName, enumValue.enumEntryName);
        }

        public int hashCode() {
            return (this.enumClassName.hashCode() * 31) + this.enumEntryName.hashCode();
        }

        public String toString() {
            return "EnumValue(enumClassName=" + this.enumClassName + ", enumEntryName=" + this.enumEntryName + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnumValue(String enumClassName, String enumEntryName) {
            super(null);
            Intrinsics.checkNotNullParameter(enumClassName, "enumClassName");
            Intrinsics.checkNotNullParameter(enumEntryName, "enumEntryName");
            this.enumClassName = enumClassName;
            this.enumEntryName = enumEntryName;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$AnnotationValue;", "Lkotlinx/metadata/KmAnnotationArgument;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lkotlinx/metadata/KmAnnotation;", "annotation", "Lkotlinx/metadata/KmAnnotation;", "getAnnotation", "()Lkotlinx/metadata/KmAnnotation;", "<init>", "(Lkotlinx/metadata/KmAnnotation;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class AnnotationValue extends KmAnnotationArgument {
        private final KmAnnotation annotation;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AnnotationValue) && Intrinsics.areEqual(this.annotation, ((AnnotationValue) other).annotation);
        }

        public int hashCode() {
            return this.annotation.hashCode();
        }

        public String toString() {
            return "AnnotationValue(annotation=" + this.annotation + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnnotationValue(KmAnnotation annotation) {
            super(null);
            Intrinsics.checkNotNullParameter(annotation, "annotation");
            this.annotation = annotation;
        }
    }

    /* JADX INFO: compiled from: annotations.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lkotlinx/metadata/KmAnnotationArgument$ArrayValue;", "Lkotlinx/metadata/KmAnnotationArgument;", "", "toString", "", "hashCode", "", "other", "", "equals", "", ContextMappingConstants.ELEMENTS, "Ljava/util/List;", "getElements", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ArrayValue extends KmAnnotationArgument {
        private final List<KmAnnotationArgument> elements;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ArrayValue) && Intrinsics.areEqual(this.elements, ((ArrayValue) other).elements);
        }

        public int hashCode() {
            return this.elements.hashCode();
        }

        public String toString() {
            return "ArrayValue(elements=" + this.elements + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ArrayValue(List<? extends KmAnnotationArgument> elements) {
            super(null);
            Intrinsics.checkNotNullParameter(elements, "elements");
            this.elements = elements;
        }
    }
}
