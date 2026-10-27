package io.objectbox;

import io.objectbox.converter.PropertyConverter;
import io.objectbox.exception.DbException;
import io.objectbox.query.PropertyQueryCondition;
import io.objectbox.query.PropertyQueryConditionImpl;
import io.objectbox.query.QueryBuilder;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Property<ENTITY> implements Serializable {
    private static final long serialVersionUID = 8613291105982758093L;
    public final Class<? extends PropertyConverter> converterClass;
    public final Class<?> customType;
    public final String dbName;
    public final EntityInfo<ENTITY> entity;
    public final int id;
    private boolean idVerified;
    public final boolean isId;
    public final boolean isVirtual;
    public final String name;
    public final int ordinal;
    public final Class<?> type;

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str) {
        this(entityInfo, i, i2, cls, str, false, str, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z) {
        this(entityInfo, i, i2, cls, str, false, z, str, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, @Nullable String str2) {
        this(entityInfo, i, i2, cls, str, z, str2, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, @Nullable String str2, @Nullable Class<? extends PropertyConverter> cls2, @Nullable Class<?> cls3) {
        this(entityInfo, i, i2, cls, str, z, false, str2, cls2, cls3);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, boolean z2, @Nullable String str2, @Nullable Class<? extends PropertyConverter> cls2, @Nullable Class<?> cls3) {
        this.entity = entityInfo;
        this.ordinal = i;
        this.id = i2;
        this.type = cls;
        this.name = str;
        this.isId = z;
        this.isVirtual = z2;
        this.dbName = str2;
        this.converterClass = cls2;
        this.customType = cls3;
    }

    public PropertyQueryCondition<ENTITY> isNull() {
        return new PropertyQueryConditionImpl.NullCondition(this, PropertyQueryConditionImpl.NullCondition.Operation.IS_NULL);
    }

    public PropertyQueryCondition<ENTITY> notNull() {
        return new PropertyQueryConditionImpl.NullCondition(this, PropertyQueryConditionImpl.NullCondition.Operation.NOT_NULL);
    }

    public PropertyQueryCondition<ENTITY> equal(boolean z) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.EQUAL, z);
    }

    public PropertyQueryCondition<ENTITY> notEqual(boolean z) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.NOT_EQUAL, z);
    }

    public PropertyQueryCondition<ENTITY> equal(short s) {
        return equal(s);
    }

    public PropertyQueryCondition<ENTITY> notEqual(short s) {
        return notEqual(s);
    }

    public PropertyQueryCondition<ENTITY> greater(short s) {
        return greater(s);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(short s) {
        return greaterOrEqual(s);
    }

    public PropertyQueryCondition<ENTITY> less(short s) {
        return less(s);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(short s) {
        return lessOrEqual(s);
    }

    public PropertyQueryCondition<ENTITY> between(short s, short s2) {
        return between(s, s2);
    }

    public PropertyQueryCondition<ENTITY> equal(int i) {
        return equal(i);
    }

    public PropertyQueryCondition<ENTITY> notEqual(int i) {
        return notEqual(i);
    }

    public PropertyQueryCondition<ENTITY> greater(int i) {
        return greater(i);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(int i) {
        return greaterOrEqual(i);
    }

    public PropertyQueryCondition<ENTITY> less(int i) {
        return less(i);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(int i) {
        return lessOrEqual(i);
    }

    public PropertyQueryCondition<ENTITY> between(int i, int i2) {
        return between(i, i2);
    }

    public PropertyQueryCondition<ENTITY> oneOf(int[] iArr) {
        return new PropertyQueryConditionImpl.IntArrayCondition(this, PropertyQueryConditionImpl.IntArrayCondition.Operation.IN, iArr);
    }

    public PropertyQueryCondition<ENTITY> notOneOf(int[] iArr) {
        return new PropertyQueryConditionImpl.IntArrayCondition(this, PropertyQueryConditionImpl.IntArrayCondition.Operation.NOT_IN, iArr);
    }

    public PropertyQueryCondition<ENTITY> equal(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.EQUAL, j);
    }

    public PropertyQueryCondition<ENTITY> notEqual(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.NOT_EQUAL, j);
    }

    public PropertyQueryCondition<ENTITY> greater(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.GREATER, j);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.GREATER_OR_EQUAL, j);
    }

    public PropertyQueryCondition<ENTITY> less(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.LESS, j);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(long j) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.LESS_OR_EQUAL, j);
    }

    public PropertyQueryCondition<ENTITY> between(long j, long j2) {
        return new PropertyQueryConditionImpl.LongLongCondition(this, PropertyQueryConditionImpl.LongLongCondition.Operation.BETWEEN, j, j2);
    }

    public PropertyQueryCondition<ENTITY> oneOf(long[] jArr) {
        return new PropertyQueryConditionImpl.LongArrayCondition(this, PropertyQueryConditionImpl.LongArrayCondition.Operation.IN, jArr);
    }

    public PropertyQueryCondition<ENTITY> notOneOf(long[] jArr) {
        return new PropertyQueryConditionImpl.LongArrayCondition(this, PropertyQueryConditionImpl.LongArrayCondition.Operation.NOT_IN, jArr);
    }

    public PropertyQueryCondition<ENTITY> equal(double d, double d2) {
        return new PropertyQueryConditionImpl.DoubleDoubleCondition(this, PropertyQueryConditionImpl.DoubleDoubleCondition.Operation.BETWEEN, d - d2, d + d2);
    }

    public PropertyQueryCondition<ENTITY> greater(double d) {
        return new PropertyQueryConditionImpl.DoubleCondition(this, PropertyQueryConditionImpl.DoubleCondition.Operation.GREATER, d);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(double d) {
        return new PropertyQueryConditionImpl.DoubleCondition(this, PropertyQueryConditionImpl.DoubleCondition.Operation.GREATER_OR_EQUAL, d);
    }

    public PropertyQueryCondition<ENTITY> less(double d) {
        return new PropertyQueryConditionImpl.DoubleCondition(this, PropertyQueryConditionImpl.DoubleCondition.Operation.LESS, d);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(double d) {
        return new PropertyQueryConditionImpl.DoubleCondition(this, PropertyQueryConditionImpl.DoubleCondition.Operation.LESS_OR_EQUAL, d);
    }

    public PropertyQueryCondition<ENTITY> between(double d, double d2) {
        return new PropertyQueryConditionImpl.DoubleDoubleCondition(this, PropertyQueryConditionImpl.DoubleDoubleCondition.Operation.BETWEEN, d, d2);
    }

    public PropertyQueryCondition<ENTITY> equal(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.EQUAL, date);
    }

    public PropertyQueryCondition<ENTITY> notEqual(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.NOT_EQUAL, date);
    }

    public PropertyQueryCondition<ENTITY> greater(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.GREATER, date);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.GREATER_OR_EQUAL, date);
    }

    public PropertyQueryCondition<ENTITY> less(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.LESS, date);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(Date date) {
        return new PropertyQueryConditionImpl.LongCondition(this, PropertyQueryConditionImpl.LongCondition.Operation.LESS_OR_EQUAL, date);
    }

    public PropertyQueryCondition<ENTITY> between(Date date, Date date2) {
        return new PropertyQueryConditionImpl.LongLongCondition(this, PropertyQueryConditionImpl.LongLongCondition.Operation.BETWEEN, date, date2);
    }

    public PropertyQueryCondition<ENTITY> equal(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.EQUAL, str);
    }

    public PropertyQueryCondition<ENTITY> equal(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.EQUAL, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> notEqual(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.NOT_EQUAL, str);
    }

    public PropertyQueryCondition<ENTITY> notEqual(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.NOT_EQUAL, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> greater(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.GREATER, str);
    }

    public PropertyQueryCondition<ENTITY> greater(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.GREATER, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.GREATER_OR_EQUAL, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> less(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.LESS, str);
    }

    public PropertyQueryCondition<ENTITY> less(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.LESS, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.LESS_OR_EQUAL, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> contains(String str) {
        checkNotStringArray();
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.CONTAINS, str);
    }

    public PropertyQueryCondition<ENTITY> contains(String str, QueryBuilder.StringOrder stringOrder) {
        checkNotStringArray();
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.CONTAINS, str, stringOrder);
    }

    private void checkNotStringArray() {
        if (String[].class == this.type) {
            throw new IllegalArgumentException("For a String[] property use containsElement() instead.");
        }
    }

    public PropertyQueryCondition<ENTITY> containsElement(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.CONTAINS_ELEMENT, str);
    }

    public PropertyQueryCondition<ENTITY> containsElement(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.CONTAINS_ELEMENT, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> containsKeyValue(String str, String str2) {
        return new PropertyQueryConditionImpl.StringStringCondition(this, PropertyQueryConditionImpl.StringStringCondition.Operation.CONTAINS_KEY_VALUE, str, str2, QueryBuilder.StringOrder.CASE_SENSITIVE);
    }

    public PropertyQueryCondition<ENTITY> containsKeyValue(String str, String str2, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringStringCondition(this, PropertyQueryConditionImpl.StringStringCondition.Operation.CONTAINS_KEY_VALUE, str, str2, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> startsWith(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.STARTS_WITH, str);
    }

    public PropertyQueryCondition<ENTITY> startsWith(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.STARTS_WITH, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> endsWith(String str) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.ENDS_WITH, str);
    }

    public PropertyQueryCondition<ENTITY> endsWith(String str, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringCondition(this, PropertyQueryConditionImpl.StringCondition.Operation.ENDS_WITH, str, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> oneOf(String[] strArr) {
        return new PropertyQueryConditionImpl.StringArrayCondition(this, PropertyQueryConditionImpl.StringArrayCondition.Operation.IN, strArr);
    }

    public PropertyQueryCondition<ENTITY> oneOf(String[] strArr, QueryBuilder.StringOrder stringOrder) {
        return new PropertyQueryConditionImpl.StringArrayCondition(this, PropertyQueryConditionImpl.StringArrayCondition.Operation.IN, strArr, stringOrder);
    }

    public PropertyQueryCondition<ENTITY> equal(byte[] bArr) {
        return new PropertyQueryConditionImpl.ByteArrayCondition(this, PropertyQueryConditionImpl.ByteArrayCondition.Operation.EQUAL, bArr);
    }

    public PropertyQueryCondition<ENTITY> greater(byte[] bArr) {
        return new PropertyQueryConditionImpl.ByteArrayCondition(this, PropertyQueryConditionImpl.ByteArrayCondition.Operation.GREATER, bArr);
    }

    public PropertyQueryCondition<ENTITY> greaterOrEqual(byte[] bArr) {
        return new PropertyQueryConditionImpl.ByteArrayCondition(this, PropertyQueryConditionImpl.ByteArrayCondition.Operation.GREATER_OR_EQUAL, bArr);
    }

    public PropertyQueryCondition<ENTITY> less(byte[] bArr) {
        return new PropertyQueryConditionImpl.ByteArrayCondition(this, PropertyQueryConditionImpl.ByteArrayCondition.Operation.LESS, bArr);
    }

    public PropertyQueryCondition<ENTITY> lessOrEqual(byte[] bArr) {
        return new PropertyQueryConditionImpl.ByteArrayCondition(this, PropertyQueryConditionImpl.ByteArrayCondition.Operation.LESS_OR_EQUAL, bArr);
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> eq(Object obj) {
        if (obj instanceof Long) {
            return equal(((Long) obj).longValue());
        }
        if (obj instanceof Integer) {
            return equal(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            return equal((String) obj);
        }
        throw new IllegalArgumentException("Only LONG, INTEGER or STRING values are supported.");
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> notEq(Object obj) {
        if (obj instanceof Long) {
            return notEqual(((Long) obj).longValue());
        }
        if (obj instanceof Integer) {
            return notEqual(((Integer) obj).intValue());
        }
        if (obj instanceof String) {
            return notEqual((String) obj);
        }
        throw new IllegalArgumentException("Only LONG, INTEGER or STRING values are supported.");
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> in(Object... objArr) {
        int i = 0;
        if (objArr[0] instanceof Long) {
            long[] jArr = new long[objArr.length];
            while (i < objArr.length) {
                jArr[i] = ((Long) objArr[i]).longValue();
                i++;
            }
            return oneOf(jArr);
        }
        if (objArr[0] instanceof Integer) {
            int[] iArr = new int[objArr.length];
            while (i < objArr.length) {
                iArr[i] = ((Integer) objArr[i]).intValue();
                i++;
            }
            return oneOf(iArr);
        }
        throw new IllegalArgumentException("The IN condition only supports LONG or INTEGER values.");
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> in(Collection<?> collection) {
        return in(collection.toArray());
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> gt(Object obj) {
        if (obj instanceof Long) {
            return greater(((Long) obj).longValue());
        }
        if (obj instanceof Integer) {
            return greater(((Integer) obj).intValue());
        }
        if (obj instanceof Double) {
            return greater(((Double) obj).doubleValue());
        }
        if (obj instanceof Float) {
            return greater(((Float) obj).floatValue());
        }
        throw new IllegalArgumentException("Only LONG, INTEGER, DOUBLE or FLOAT values are supported.");
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> lt(Object obj) {
        if (obj instanceof Long) {
            return less(((Long) obj).longValue());
        }
        if (obj instanceof Integer) {
            return less(((Integer) obj).intValue());
        }
        if (obj instanceof Double) {
            return less(((Double) obj).doubleValue());
        }
        if (obj instanceof Float) {
            return less(((Float) obj).floatValue());
        }
        throw new IllegalArgumentException("Only LONG, INTEGER, DOUBLE or FLOAT values are supported.");
    }

    @Deprecated
    public PropertyQueryCondition<ENTITY> isNotNull() {
        return notNull();
    }

    public int getEntityId() {
        return this.entity.getEntityId();
    }

    public int getId() {
        int i = this.id;
        if (i > 0) {
            return i;
        }
        throw new IllegalStateException("Illegal property ID " + this.id + " for " + this);
    }

    boolean isIdVerified() {
        return this.idVerified;
    }

    void verifyId(int i) {
        int i2 = this.id;
        if (i2 <= 0) {
            throw new IllegalStateException("Illegal property ID " + this.id + " for " + this);
        }
        if (i2 != i) {
            throw new DbException(this + " does not match ID in DB: " + i);
        }
        this.idVerified = true;
    }

    public String toString() {
        return "Property \"" + this.name + "\" (ID: " + this.id + ")";
    }
}
