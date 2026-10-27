package io.objectbox.query;

import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public abstract class PropertyQueryConditionImpl<T> extends QueryConditionImpl<T> implements PropertyQueryCondition<T> {
    private String alias;
    public final Property<T> property;

    abstract void applyCondition(QueryBuilder<T> queryBuilder);

    @Override // io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
    public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
        return super.and(queryCondition);
    }

    @Override // io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
    public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
        return super.or(queryCondition);
    }

    PropertyQueryConditionImpl(Property<T> property) {
        this.property = property;
    }

    @Override // io.objectbox.query.PropertyQueryCondition
    public QueryCondition<T> alias(String str) {
        this.alias = str;
        return this;
    }

    @Override // io.objectbox.query.QueryConditionImpl
    public void apply(QueryBuilder<T> queryBuilder) {
        applyCondition(queryBuilder);
        String str = this.alias;
        if (str == null || str.length() == 0) {
            return;
        }
        queryBuilder.parameterAlias(this.alias);
    }

    public static class NullCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;

        public enum Operation {
            IS_NULL,
            NOT_NULL
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public NullCondition(Property<T> property, Operation operation) {
            super(property);
            this.op = operation;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            int i = AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$NullCondition$Operation[this.op.ordinal()];
            if (i == 1) {
                queryBuilder.isNull(this.property);
            } else {
                if (i == 2) {
                    queryBuilder.notNull(this.property);
                    return;
                }
                throw new UnsupportedOperationException(this.op + " is not supported");
            }
        }
    }

    public static class IntArrayCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final int[] value;

        public enum Operation {
            IN,
            NOT_IN
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public IntArrayCondition(Property<T> property, Operation operation, int[] iArr) {
            super(property);
            this.op = operation;
            this.value = iArr;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            int i = AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$IntArrayCondition$Operation[this.op.ordinal()];
            if (i == 1) {
                queryBuilder.in((Property) this.property, this.value);
            } else {
                if (i == 2) {
                    queryBuilder.notIn((Property) this.property, this.value);
                    return;
                }
                throw new UnsupportedOperationException(this.op + " is not supported for int[]");
            }
        }
    }

    public static class LongCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final long value;

        public enum Operation {
            EQUAL,
            NOT_EQUAL,
            GREATER,
            GREATER_OR_EQUAL,
            LESS,
            LESS_OR_EQUAL
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public LongCondition(Property<T> property, Operation operation, long j) {
            super(property);
            this.op = operation;
            this.value = j;
        }

        public LongCondition(Property<T> property, Operation operation, boolean z) {
            this(property, operation, z ? 1L : 0L);
        }

        public LongCondition(Property<T> property, Operation operation, Date date) {
            this(property, operation, date.getTime());
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            switch (AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[this.op.ordinal()]) {
                case 1:
                    queryBuilder.equal(this.property, this.value);
                    return;
                case 2:
                    queryBuilder.notEqual(this.property, this.value);
                    return;
                case 3:
                    queryBuilder.greater((Property) this.property, this.value);
                    return;
                case 4:
                    queryBuilder.greaterOrEqual((Property) this.property, this.value);
                    return;
                case 5:
                    queryBuilder.less((Property) this.property, this.value);
                    return;
                case 6:
                    queryBuilder.lessOrEqual((Property) this.property, this.value);
                    return;
                default:
                    throw new UnsupportedOperationException(this.op + " is not supported for String");
            }
        }
    }

    public static class LongLongCondition<T> extends PropertyQueryConditionImpl<T> {
        private final long leftValue;
        private final Operation op;
        private final long rightValue;

        public enum Operation {
            BETWEEN
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public LongLongCondition(Property<T> property, Operation operation, long j, long j2) {
            super(property);
            this.op = operation;
            this.leftValue = j;
            this.rightValue = j2;
        }

        public LongLongCondition(Property<T> property, Operation operation, Date date, Date date2) {
            this(property, operation, date.getTime(), date2.getTime());
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            if (this.op == Operation.BETWEEN) {
                queryBuilder.between((Property) this.property, this.leftValue, this.rightValue);
                return;
            }
            throw new UnsupportedOperationException(this.op + " is not supported with two long values");
        }
    }

    public static class LongArrayCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final long[] value;

        public enum Operation {
            IN,
            NOT_IN
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public LongArrayCondition(Property<T> property, Operation operation, long[] jArr) {
            super(property);
            this.op = operation;
            this.value = jArr;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            int i = AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongArrayCondition$Operation[this.op.ordinal()];
            if (i == 1) {
                queryBuilder.in(this.property, this.value);
            } else {
                if (i == 2) {
                    queryBuilder.notIn(this.property, this.value);
                    return;
                }
                throw new UnsupportedOperationException(this.op + " is not supported for long[]");
            }
        }
    }

    public static class DoubleCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final double value;

        public enum Operation {
            GREATER,
            GREATER_OR_EQUAL,
            LESS,
            LESS_OR_EQUAL
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public DoubleCondition(Property<T> property, Operation operation, double d) {
            super(property);
            this.op = operation;
            this.value = d;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            int i = AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation[this.op.ordinal()];
            if (i == 1) {
                queryBuilder.greater(this.property, this.value);
                return;
            }
            if (i == 2) {
                queryBuilder.greaterOrEqual(this.property, this.value);
                return;
            }
            if (i == 3) {
                queryBuilder.less(this.property, this.value);
            } else {
                if (i == 4) {
                    queryBuilder.lessOrEqual(this.property, this.value);
                    return;
                }
                throw new UnsupportedOperationException(this.op + " is not supported for double");
            }
        }
    }

    public static class DoubleDoubleCondition<T> extends PropertyQueryConditionImpl<T> {
        private final double leftValue;
        private final Operation op;
        private final double rightValue;

        public enum Operation {
            BETWEEN
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public DoubleDoubleCondition(Property<T> property, Operation operation, double d, double d2) {
            super(property);
            this.op = operation;
            this.leftValue = d;
            this.rightValue = d2;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            if (this.op == Operation.BETWEEN) {
                queryBuilder.between(this.property, this.leftValue, this.rightValue);
                return;
            }
            throw new UnsupportedOperationException(this.op + " is not supported with two double values");
        }
    }

    public static class StringCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final QueryBuilder.StringOrder order;
        private final String value;

        public enum Operation {
            EQUAL,
            NOT_EQUAL,
            GREATER,
            GREATER_OR_EQUAL,
            LESS,
            LESS_OR_EQUAL,
            CONTAINS,
            CONTAINS_ELEMENT,
            STARTS_WITH,
            ENDS_WITH
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public StringCondition(Property<T> property, Operation operation, String str, QueryBuilder.StringOrder stringOrder) {
            super(property);
            this.op = operation;
            this.value = str;
            this.order = stringOrder;
        }

        public StringCondition(Property<T> property, Operation operation, String str) {
            this(property, operation, str, QueryBuilder.StringOrder.CASE_SENSITIVE);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            switch (AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[this.op.ordinal()]) {
                case 1:
                    queryBuilder.equal(this.property, this.value, this.order);
                    return;
                case 2:
                    queryBuilder.notEqual(this.property, this.value, this.order);
                    return;
                case 3:
                    queryBuilder.greater(this.property, this.value, this.order);
                    return;
                case 4:
                    queryBuilder.greaterOrEqual(this.property, this.value, this.order);
                    return;
                case 5:
                    queryBuilder.less(this.property, this.value, this.order);
                    return;
                case 6:
                    queryBuilder.lessOrEqual(this.property, this.value, this.order);
                    return;
                case 7:
                    queryBuilder.contains(this.property, this.value, this.order);
                    return;
                case 8:
                    queryBuilder.containsElement(this.property, this.value, this.order);
                    return;
                case 9:
                    queryBuilder.startsWith(this.property, this.value, this.order);
                    return;
                case 10:
                    queryBuilder.endsWith(this.property, this.value, this.order);
                    return;
                default:
                    throw new UnsupportedOperationException(this.op + " is not supported for String");
            }
        }
    }

    public static class StringStringCondition<T> extends PropertyQueryConditionImpl<T> {
        private final String leftValue;
        private final Operation op;
        private final QueryBuilder.StringOrder order;
        private final String rightValue;

        public enum Operation {
            CONTAINS_KEY_VALUE
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public StringStringCondition(Property<T> property, Operation operation, String str, String str2, QueryBuilder.StringOrder stringOrder) {
            super(property);
            this.op = operation;
            this.leftValue = str;
            this.rightValue = str2;
            this.order = stringOrder;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            if (this.op == Operation.CONTAINS_KEY_VALUE) {
                queryBuilder.containsKeyValue(this.property, this.leftValue, this.rightValue, this.order);
                return;
            }
            throw new UnsupportedOperationException(this.op + " is not supported with two String values");
        }
    }

    public static class StringArrayCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final QueryBuilder.StringOrder order;
        private final String[] value;

        public enum Operation {
            IN
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public StringArrayCondition(Property<T> property, Operation operation, String[] strArr, QueryBuilder.StringOrder stringOrder) {
            super(property);
            this.op = operation;
            this.value = strArr;
            this.order = stringOrder;
        }

        public StringArrayCondition(Property<T> property, Operation operation, String[] strArr) {
            this(property, operation, strArr, QueryBuilder.StringOrder.CASE_SENSITIVE);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            if (this.op == Operation.IN) {
                queryBuilder.in(this.property, this.value, this.order);
                return;
            }
            throw new UnsupportedOperationException(this.op + " is not supported for String[]");
        }
    }

    public static class ByteArrayCondition<T> extends PropertyQueryConditionImpl<T> {
        private final Operation op;
        private final byte[] value;

        public enum Operation {
            EQUAL,
            GREATER,
            GREATER_OR_EQUAL,
            LESS,
            LESS_OR_EQUAL
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
            return super.and(queryCondition);
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl, io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
        public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
            return super.or(queryCondition);
        }

        public ByteArrayCondition(Property<T> property, Operation operation, byte[] bArr) {
            super(property);
            this.op = operation;
            this.value = bArr;
        }

        @Override // io.objectbox.query.PropertyQueryConditionImpl
        void applyCondition(QueryBuilder<T> queryBuilder) {
            int i = AnonymousClass1.$SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation[this.op.ordinal()];
            if (i == 1) {
                queryBuilder.equal(this.property, this.value);
                return;
            }
            if (i == 2) {
                queryBuilder.greater(this.property, this.value);
                return;
            }
            if (i == 3) {
                queryBuilder.greaterOrEqual(this.property, this.value);
                return;
            }
            if (i == 4) {
                queryBuilder.less(this.property, this.value);
            } else {
                if (i == 5) {
                    queryBuilder.lessOrEqual(this.property, this.value);
                    return;
                }
                throw new UnsupportedOperationException(this.op + " is not supported for byte[]");
            }
        }
    }

    /* JADX INFO: renamed from: io.objectbox.query.PropertyQueryConditionImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$IntArrayCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongArrayCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$NullCondition$Operation;
        static final /* synthetic */ int[] $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation;

        static {
            int[] iArr = new int[ByteArrayCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation = iArr;
            try {
                iArr[ByteArrayCondition.Operation.EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation[ByteArrayCondition.Operation.GREATER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation[ByteArrayCondition.Operation.GREATER_OR_EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation[ByteArrayCondition.Operation.LESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$ByteArrayCondition$Operation[ByteArrayCondition.Operation.LESS_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[StringCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation = iArr2;
            try {
                iArr2[StringCondition.Operation.EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.NOT_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.GREATER.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.GREATER_OR_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.LESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.LESS_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.CONTAINS_ELEMENT.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.STARTS_WITH.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$StringCondition$Operation[StringCondition.Operation.ENDS_WITH.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr3 = new int[DoubleCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation = iArr3;
            try {
                iArr3[DoubleCondition.Operation.GREATER.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation[DoubleCondition.Operation.GREATER_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation[DoubleCondition.Operation.LESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$DoubleCondition$Operation[DoubleCondition.Operation.LESS_OR_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            int[] iArr4 = new int[LongArrayCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongArrayCondition$Operation = iArr4;
            try {
                iArr4[LongArrayCondition.Operation.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongArrayCondition$Operation[LongArrayCondition.Operation.NOT_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
            int[] iArr5 = new int[LongCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation = iArr5;
            try {
                iArr5[LongCondition.Operation.EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[LongCondition.Operation.NOT_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[LongCondition.Operation.GREATER.ordinal()] = 3;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[LongCondition.Operation.GREATER_OR_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[LongCondition.Operation.LESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$LongCondition$Operation[LongCondition.Operation.LESS_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused27) {
            }
            int[] iArr6 = new int[IntArrayCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$IntArrayCondition$Operation = iArr6;
            try {
                iArr6[IntArrayCondition.Operation.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$IntArrayCondition$Operation[IntArrayCondition.Operation.NOT_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused29) {
            }
            int[] iArr7 = new int[NullCondition.Operation.values().length];
            $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$NullCondition$Operation = iArr7;
            try {
                iArr7[NullCondition.Operation.IS_NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$io$objectbox$query$PropertyQueryConditionImpl$NullCondition$Operation[NullCondition.Operation.NOT_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }
}
