package com.amazon.minerva.client.thirdparty.metric;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Objects;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'STRING' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ValueType {
    private static final /* synthetic */ ValueType[] $VALUES;
    public static final ValueType AGGREGATED_FLOAT;
    public static final ValueType AGGREGATED_INTEGER;
    public static final ValueType BOOLEAN;
    public static final ValueType FLOAT;
    public static final ValueType INTEGER;
    public static final ValueType STRING;
    public static final ValueType SYMBOL;
    public static final ValueType TIMESTAMP;
    private final Class<?> clazz;

    public abstract <T> int getSizeInBytes(T t);

    public abstract <T> boolean isEmpty(T t);

    public abstract <T> String toString(T t);

    public static ValueType valueOf(String str) {
        return (ValueType) Enum.valueOf(ValueType.class, str);
    }

    public static ValueType[] values() {
        return (ValueType[]) $VALUES.clone();
    }

    static {
        Class<String> cls = String.class;
        ValueType valueType = new ValueType("BOOLEAN", 0, Boolean.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.1
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                return 1;
            }

            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                return Boolean.toString(((Boolean) t).booleanValue());
            }
        };
        BOOLEAN = valueType;
        ValueType valueType2 = new ValueType("INTEGER", 1, Long.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.2
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                return 8;
            }

            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                return Long.toString(((Long) t).longValue());
            }
        };
        INTEGER = valueType2;
        ValueType valueType3 = new ValueType("FLOAT", 2, Double.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.3
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                return 8;
            }

            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                return Double.toString(((Double) t).doubleValue());
            }
        };
        FLOAT = valueType3;
        ValueType valueType4 = new ValueType(CognitoServiceConstants.CHLG_RESP_TIMESTAMP, 3, Timestamp.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.4
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                return 8;
            }

            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                return false;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                Timestamp timestamp = (Timestamp) t;
                return timestamp.getEpochMillis() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + timestamp.getTimeZoneOffset();
            }
        };
        TIMESTAMP = valueType4;
        ValueType valueType5 = new ValueType("STRING", 4, cls) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((String) t).isEmpty();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((String) t).length();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                return (String) t;
            }
        };
        STRING = valueType5;
        ValueType valueType6 = new ValueType("SYMBOL", 5, cls) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.6
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((String) t).isEmpty();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((String) t).length();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                return (String) t;
            }
        };
        SYMBOL = valueType6;
        ValueType valueType7 = new ValueType("AGGREGATED_INTEGER", 6, AggregatedLong.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.7
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedLong) t).isEmpty();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedLong) t).getSizeInBytes();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedLong) t).toString();
            }
        };
        AGGREGATED_INTEGER = valueType7;
        ValueType valueType8 = new ValueType("AGGREGATED_FLOAT", 7, AggregatedDouble.class) { // from class: com.amazon.minerva.client.thirdparty.metric.ValueType.8
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> boolean isEmpty(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedDouble) t).isEmpty();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> int getSizeInBytes(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedDouble) t).getSizeInBytes();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
            public <T> String toString(T t) {
                Objects.requireNonNull(t, "value can not be null.");
                return ((AggregatedDouble) t).toString();
            }
        };
        AGGREGATED_FLOAT = valueType8;
        $VALUES = new ValueType[]{valueType, valueType2, valueType3, valueType4, valueType5, valueType6, valueType7, valueType8};
    }

    private ValueType(String str, int i, Class cls) {
        this.clazz = cls;
    }

    public static ValueType fromInt(int i) {
        if (i < 0 || i > values().length) {
            throw new IllegalArgumentException("invalid index: " + i);
        }
        return values()[i];
    }

    public <T> TypedValue<T> of(T t) {
        return new TypedValue<>(this, t);
    }

    Class<?> getCompatibleClass() {
        return this.clazz;
    }
}
