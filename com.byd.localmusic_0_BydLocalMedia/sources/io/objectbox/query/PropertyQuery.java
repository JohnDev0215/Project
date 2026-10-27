package io.objectbox.query;

import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes5.dex */
public class PropertyQuery {
    boolean distinct;
    boolean enableNull;
    boolean noCaseIfDistinct = true;
    double nullValueDouble;
    float nullValueFloat;
    long nullValueLong;
    String nullValueString;
    final Property<?> property;
    final int propertyId;
    final Query<?> query;
    final long queryHandle;
    boolean unique;

    native double nativeAvg(long j, long j2, int i);

    native long nativeAvgLong(long j, long j2, int i);

    native long nativeCount(long j, long j2, int i, boolean z);

    native byte[] nativeFindBytes(long j, long j2, int i, boolean z, boolean z2, byte b);

    native char[] nativeFindChars(long j, long j2, int i, boolean z, boolean z2, char c);

    native double[] nativeFindDoubles(long j, long j2, int i, boolean z, boolean z2, double d);

    native float[] nativeFindFloats(long j, long j2, int i, boolean z, boolean z2, float f);

    native int[] nativeFindInts(long j, long j2, int i, boolean z, boolean z2, int i2);

    native long[] nativeFindLongs(long j, long j2, int i, boolean z, boolean z2, long j3);

    native Object nativeFindNumber(long j, long j2, int i, boolean z, boolean z2, boolean z3, long j3, float f, double d);

    native short[] nativeFindShorts(long j, long j2, int i, boolean z, boolean z2, short s);

    native String nativeFindString(long j, long j2, int i, boolean z, boolean z2, boolean z3, boolean z4, String str);

    native String[] nativeFindStrings(long j, long j2, int i, boolean z, boolean z2, boolean z3, String str);

    native long nativeMax(long j, long j2, int i);

    native double nativeMaxDouble(long j, long j2, int i);

    native long nativeMin(long j, long j2, int i);

    native double nativeMinDouble(long j, long j2, int i);

    native long nativeSum(long j, long j2, int i);

    native double nativeSumDouble(long j, long j2, int i);

    PropertyQuery(Query<?> query, Property<?> property) {
        this.query = query;
        this.queryHandle = query.handle;
        this.property = property;
        this.propertyId = property.id;
    }

    public PropertyQuery reset() {
        this.distinct = false;
        this.noCaseIfDistinct = true;
        this.unique = false;
        this.enableNull = false;
        this.nullValueDouble = 0.0d;
        this.nullValueFloat = 0.0f;
        this.nullValueString = null;
        this.nullValueLong = 0L;
        return this;
    }

    public PropertyQuery distinct() {
        this.distinct = true;
        return this;
    }

    public PropertyQuery distinct(QueryBuilder.StringOrder stringOrder) {
        if (this.property.type != String.class) {
            throw new RuntimeException("Reserved for string properties, but got " + this.property);
        }
        this.distinct = true;
        this.noCaseIfDistinct = stringOrder == QueryBuilder.StringOrder.CASE_INSENSITIVE;
        return this;
    }

    public PropertyQuery unique() {
        this.unique = true;
        return this;
    }

    public PropertyQuery nullValue(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Null values are not allowed");
        }
        boolean z = obj instanceof String;
        boolean z2 = obj instanceof Number;
        if (!z && !z2) {
            throw new IllegalArgumentException("Unsupported value class: " + obj.getClass());
        }
        this.enableNull = true;
        this.nullValueString = z ? (String) obj : null;
        boolean z3 = obj instanceof Float;
        this.nullValueFloat = z3 ? ((Float) obj).floatValue() : 0.0f;
        boolean z4 = obj instanceof Double;
        this.nullValueDouble = z4 ? ((Double) obj).doubleValue() : 0.0d;
        this.nullValueLong = (!z2 || z3 || z4) ? 0L : ((Number) obj).longValue();
        return this;
    }

    public String[] findStrings() {
        return (String[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$y4yAqy_hpLVzEbDDtQyhKwJnmBM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findStrings$0$PropertyQuery();
            }
        });
    }

    public /* synthetic */ String[] lambda$findStrings$0$PropertyQuery() throws Exception {
        return nativeFindStrings(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.distinct && this.noCaseIfDistinct, this.enableNull, this.nullValueString);
    }

    public long[] findLongs() {
        return (long[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$MzzBeu6kq6Ld0QxOiYh0-gAwkgA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findLongs$1$PropertyQuery();
            }
        });
    }

    public /* synthetic */ long[] lambda$findLongs$1$PropertyQuery() throws Exception {
        return nativeFindLongs(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, this.nullValueLong);
    }

    public int[] findInts() {
        return (int[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$L9vEEO4TnwzgOAp90o8tE8IO6hA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findInts$2$PropertyQuery();
            }
        });
    }

    public /* synthetic */ int[] lambda$findInts$2$PropertyQuery() throws Exception {
        return nativeFindInts(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, (int) this.nullValueLong);
    }

    public short[] findShorts() {
        return (short[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$4nfmIaAZEimg51SEyr08aMCWLLw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findShorts$3$PropertyQuery();
            }
        });
    }

    public /* synthetic */ short[] lambda$findShorts$3$PropertyQuery() throws Exception {
        return nativeFindShorts(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, (short) this.nullValueLong);
    }

    public char[] findChars() {
        return (char[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$OpYbnCemagy4wOH2M8wOAg9ReBs
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findChars$4$PropertyQuery();
            }
        });
    }

    public /* synthetic */ char[] lambda$findChars$4$PropertyQuery() throws Exception {
        return nativeFindChars(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, (char) this.nullValueLong);
    }

    public byte[] findBytes() {
        return (byte[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$yNucnIchiPuVPr4iWKxkfKVqUM8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findBytes$5$PropertyQuery();
            }
        });
    }

    public /* synthetic */ byte[] lambda$findBytes$5$PropertyQuery() throws Exception {
        return nativeFindBytes(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, (byte) this.nullValueLong);
    }

    public float[] findFloats() {
        return (float[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$RVI4Flu0JJ3k1w8qe5QeLZVYFLY
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findFloats$6$PropertyQuery();
            }
        });
    }

    public /* synthetic */ float[] lambda$findFloats$6$PropertyQuery() throws Exception {
        return nativeFindFloats(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, this.nullValueFloat);
    }

    public double[] findDoubles() {
        return (double[]) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$1bkhTSkSBsWWRUM5UsK6X8ek7lI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findDoubles$7$PropertyQuery();
            }
        });
    }

    public /* synthetic */ double[] lambda$findDoubles$7$PropertyQuery() throws Exception {
        return nativeFindDoubles(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct, this.enableNull, this.nullValueDouble);
    }

    public String findString() {
        return (String) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$_NHNkr8xHDv-85Ms8Iy4wpHy58Y
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findString$8$PropertyQuery();
            }
        });
    }

    public /* synthetic */ String lambda$findString$8$PropertyQuery() throws Exception {
        return nativeFindString(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.unique, this.distinct, this.distinct && !this.noCaseIfDistinct, this.enableNull, this.nullValueString);
    }

    private Object findNumber() {
        return this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$e1dK0CKqIiF3JBnC9nU6pq7C1CE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findNumber$9$PropertyQuery();
            }
        });
    }

    public /* synthetic */ Object lambda$findNumber$9$PropertyQuery() throws Exception {
        return nativeFindNumber(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.unique, this.distinct, this.enableNull, this.nullValueLong, this.nullValueFloat, this.nullValueDouble);
    }

    public Long findLong() {
        return (Long) findNumber();
    }

    public Integer findInt() {
        return (Integer) findNumber();
    }

    public Short findShort() {
        return (Short) findNumber();
    }

    public Character findChar() {
        return (Character) findNumber();
    }

    public Byte findByte() {
        return (Byte) findNumber();
    }

    public Boolean findBoolean() {
        return (Boolean) findNumber();
    }

    public Float findFloat() {
        return (Float) findNumber();
    }

    public Double findDouble() {
        return (Double) findNumber();
    }

    public long sum() {
        return ((Long) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$xGQNwXEVLExjWkIimpUI8hvVpaU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$sum$10$PropertyQuery();
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$sum$10$PropertyQuery() throws Exception {
        return Long.valueOf(nativeSum(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public double sumDouble() {
        return ((Double) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$Iphg-TVsmYtMIv3jQ4Co_HNAfiU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$sumDouble$11$PropertyQuery();
            }
        })).doubleValue();
    }

    public /* synthetic */ Double lambda$sumDouble$11$PropertyQuery() throws Exception {
        return Double.valueOf(nativeSumDouble(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public long max() {
        return ((Long) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$zpJownqfv2Pt0lZ01ntSaIJLyxo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$max$12$PropertyQuery();
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$max$12$PropertyQuery() throws Exception {
        return Long.valueOf(nativeMax(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public double maxDouble() {
        return ((Double) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$sVFXJP5NxjJOit6Dfqi3UmzSIVk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$maxDouble$13$PropertyQuery();
            }
        })).doubleValue();
    }

    public /* synthetic */ Double lambda$maxDouble$13$PropertyQuery() throws Exception {
        return Double.valueOf(nativeMaxDouble(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public long min() {
        return ((Long) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$NFMQlMKnc0MtfdW34-NlDOi8-J8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$min$14$PropertyQuery();
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$min$14$PropertyQuery() throws Exception {
        return Long.valueOf(nativeMin(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public double minDouble() {
        return ((Double) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$EZVXDjMCZWdxS8hpi4Nf46RrOSM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$minDouble$15$PropertyQuery();
            }
        })).doubleValue();
    }

    public /* synthetic */ Double lambda$minDouble$15$PropertyQuery() throws Exception {
        return Double.valueOf(nativeMinDouble(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public double avg() {
        return ((Double) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$_kEHN3aqzVS4oNCAvOPz6_1FHgI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$avg$16$PropertyQuery();
            }
        })).doubleValue();
    }

    public /* synthetic */ Double lambda$avg$16$PropertyQuery() throws Exception {
        return Double.valueOf(nativeAvg(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public long avgLong() {
        return ((Long) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$l1SQlQM8GVz5nLUIpKFQaKfPGYA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$avgLong$17$PropertyQuery();
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$avgLong$17$PropertyQuery() throws Exception {
        return Long.valueOf(nativeAvgLong(this.queryHandle, this.query.cursorHandle(), this.propertyId));
    }

    public long count() {
        return ((Long) this.query.callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$PropertyQuery$-ac5ZrERYgXMUS41-VkM4H7bgUg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$count$18$PropertyQuery();
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$count$18$PropertyQuery() throws Exception {
        return Long.valueOf(nativeCount(this.queryHandle, this.query.cursorHandle(), this.propertyId, this.distinct));
    }
}
