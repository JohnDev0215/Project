package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.exception.DbException;
import io.objectbox.relation.RelationInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class QueryBuilder<T> {
    public static final int CASE_SENSITIVE = 2;
    public static final int DESCENDING = 1;
    public static final int NULLS_LAST = 8;
    public static final int NULLS_ZERO = 16;
    public static final int UNSIGNED = 4;
    private final Box<T> box;
    private Operator combineNextWith;

    @Nullable
    private Comparator<T> comparator;

    @Nullable
    private List<EagerRelation<T, ?>> eagerRelations;

    @Nullable
    private QueryFilter<T> filter;
    private long handle;
    private final boolean isSubQuery;
    private long lastCondition;
    private long lastPropertyCondition;
    private final long storeHandle;

    enum Operator {
        NONE,
        AND,
        OR
    }

    public enum StringOrder {
        CASE_INSENSITIVE,
        CASE_SENSITIVE
    }

    private native long nativeBetween(long j, int i, double d, double d2);

    private native long nativeBetween(long j, int i, long j2, long j3);

    private native long nativeBuild(long j);

    private native long nativeCombine(long j, long j2, long j3, boolean z);

    private native long nativeContains(long j, int i, String str, boolean z);

    private native long nativeContainsElement(long j, int i, String str, boolean z);

    private native long nativeContainsKeyValue(long j, int i, String str, String str2, boolean z);

    private native long nativeCreate(long j, String str);

    private native void nativeDestroy(long j);

    private native long nativeEndsWith(long j, int i, String str, boolean z);

    private native long nativeEqual(long j, int i, long j2);

    private native long nativeEqual(long j, int i, String str, boolean z);

    private native long nativeEqual(long j, int i, byte[] bArr);

    private native long nativeGreater(long j, int i, double d, boolean z);

    private native long nativeGreater(long j, int i, long j2, boolean z);

    private native long nativeGreater(long j, int i, String str, boolean z, boolean z2);

    private native long nativeGreater(long j, int i, byte[] bArr, boolean z);

    private native long nativeIn(long j, int i, int[] iArr, boolean z);

    private native long nativeIn(long j, int i, long[] jArr, boolean z);

    private native long nativeIn(long j, int i, String[] strArr, boolean z);

    private native long nativeLess(long j, int i, double d, boolean z);

    private native long nativeLess(long j, int i, long j2, boolean z);

    private native long nativeLess(long j, int i, String str, boolean z, boolean z2);

    private native long nativeLess(long j, int i, byte[] bArr, boolean z);

    private native long nativeLink(long j, long j2, int i, int i2, int i3, int i4, boolean z);

    private native long nativeNotEqual(long j, int i, long j2);

    private native long nativeNotEqual(long j, int i, String str, boolean z);

    private native long nativeNotNull(long j, int i);

    private native long nativeNull(long j, int i);

    private native void nativeOrder(long j, int i, int i2);

    private native long nativeRelationCount(long j, long j2, int i, int i2, int i3);

    private native void nativeSetParameterAlias(long j, String str);

    private native long nativeStartsWith(long j, int i, String str, boolean z);

    public QueryBuilder(Box<T> box, long j, String str) {
        this.combineNextWith = Operator.NONE;
        this.box = box;
        this.storeHandle = j;
        long jNativeCreate = nativeCreate(j, str);
        this.handle = jNativeCreate;
        if (jNativeCreate == 0) {
            throw new DbException("Could not create native query builder");
        }
        this.isSubQuery = false;
    }

    private QueryBuilder(long j, long j2) {
        this.combineNextWith = Operator.NONE;
        this.box = null;
        this.storeHandle = j;
        this.handle = j2;
        this.isSubQuery = true;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public synchronized void close() {
        if (this.handle != 0) {
            long j = this.handle;
            this.handle = 0L;
            if (!this.isSubQuery) {
                nativeDestroy(j);
            }
        }
    }

    public Query<T> build() {
        verifyNotSubQuery();
        verifyHandle();
        if (this.combineNextWith != Operator.NONE) {
            throw new IllegalStateException("Incomplete logic condition. Use or()/and() between two conditions only.");
        }
        long jNativeBuild = nativeBuild(this.handle);
        if (jNativeBuild == 0) {
            throw new DbException("Could not create native query");
        }
        Query<T> query = new Query<>(this.box, jNativeBuild, this.eagerRelations, this.filter, this.comparator);
        close();
        return query;
    }

    private void verifyNotSubQuery() {
        if (this.isSubQuery) {
            throw new IllegalStateException("This call is not supported on sub query builders (links)");
        }
    }

    private void verifyHandle() {
        if (this.handle == 0) {
            throw new IllegalStateException("This QueryBuilder has already been closed. Please use a new instance.");
        }
    }

    public QueryBuilder<T> apply(QueryCondition<T> queryCondition) {
        ((QueryConditionImpl) queryCondition).apply(this);
        return this;
    }

    public QueryBuilder<T> order(Property<T> property) {
        return order(property, 0);
    }

    public QueryBuilder<T> orderDesc(Property<T> property) {
        return order(property, 1);
    }

    public QueryBuilder<T> order(Property<T> property, int i) {
        verifyNotSubQuery();
        verifyHandle();
        checkNoOperatorPending();
        nativeOrder(this.handle, property.getId(), i);
        return this;
    }

    public QueryBuilder<T> sort(Comparator<T> comparator) {
        this.comparator = comparator;
        return this;
    }

    public QueryBuilder<T> parameterAlias(String str) {
        verifyHandle();
        long j = this.lastPropertyCondition;
        if (j == 0) {
            throw new IllegalStateException("No previous condition. Before you can assign an alias, you must first have a condition.");
        }
        nativeSetParameterAlias(j, str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <TARGET> QueryBuilder<TARGET> link(RelationInfo<?, TARGET> relationInfo) {
        boolean zIsBacklink = relationInfo.isBacklink();
        return link(relationInfo, zIsBacklink ? relationInfo.targetInfo : relationInfo.sourceInfo, relationInfo.targetInfo, zIsBacklink);
    }

    private <TARGET> QueryBuilder<TARGET> link(RelationInfo<?, ?> relationInfo, EntityInfo<?> entityInfo, EntityInfo<?> entityInfo2, boolean z) {
        return new QueryBuilder<>(this.storeHandle, nativeLink(this.handle, this.storeHandle, entityInfo.getEntityId(), entityInfo2.getEntityId(), relationInfo.targetIdProperty != null ? relationInfo.targetIdProperty.id : 0, relationInfo.targetRelationId != 0 ? relationInfo.targetRelationId : relationInfo.relationId, z));
    }

    public <TARGET> QueryBuilder<TARGET> backlink(RelationInfo<TARGET, ?> relationInfo) {
        if (relationInfo.isBacklink()) {
            throw new IllegalArgumentException("Double backlink: The relation is already a backlink, please use a regular link on the original relation instead.");
        }
        return link(relationInfo, relationInfo.sourceInfo, relationInfo.sourceInfo, true);
    }

    public QueryBuilder<T> eager(RelationInfo relationInfo, RelationInfo... relationInfoArr) {
        return eager(0, relationInfo, relationInfoArr);
    }

    public QueryBuilder<T> eager(int i, RelationInfo relationInfo, @Nullable RelationInfo... relationInfoArr) {
        verifyNotSubQuery();
        if (this.eagerRelations == null) {
            this.eagerRelations = new ArrayList();
        }
        this.eagerRelations.add(new EagerRelation<>(i, relationInfo));
        if (relationInfoArr != null) {
            for (RelationInfo relationInfo2 : relationInfoArr) {
                this.eagerRelations.add(new EagerRelation<>(i, relationInfo2));
            }
        }
        return this;
    }

    public QueryBuilder<T> filter(QueryFilter<T> queryFilter) {
        verifyNotSubQuery();
        if (this.filter != null) {
            throw new IllegalStateException("A filter was already defined, you can only assign one filter");
        }
        this.filter = queryFilter;
        return this;
    }

    public QueryBuilder<T> or() {
        combineOperator(Operator.OR);
        return this;
    }

    public QueryBuilder<T> and() {
        combineOperator(Operator.AND);
        return this;
    }

    private void combineOperator(Operator operator) {
        verifyHandle();
        if (this.lastCondition == 0) {
            throw new IllegalStateException("No previous condition. Use operators like and() and or() only between two conditions.");
        }
        checkNoOperatorPending();
        this.combineNextWith = operator;
    }

    private void checkNoOperatorPending() {
        if (this.combineNextWith != Operator.NONE) {
            throw new IllegalStateException("Another operator is pending. Use operators like and() and or() only between two conditions.");
        }
    }

    private void checkCombineCondition(long j) {
        if (this.combineNextWith != Operator.NONE) {
            this.lastCondition = nativeCombine(this.handle, this.lastCondition, j, this.combineNextWith == Operator.OR);
            this.combineNextWith = Operator.NONE;
        } else {
            this.lastCondition = j;
        }
        this.lastPropertyCondition = j;
    }

    long internalGetLastCondition() {
        return this.lastCondition;
    }

    void internalAnd(long j, long j2) {
        this.lastCondition = nativeCombine(this.handle, j, j2, false);
    }

    void internalOr(long j, long j2) {
        this.lastCondition = nativeCombine(this.handle, j, j2, true);
    }

    public QueryBuilder<T> isNull(Property<T> property) {
        verifyHandle();
        checkCombineCondition(nativeNull(this.handle, property.getId()));
        return this;
    }

    public QueryBuilder<T> notNull(Property<T> property) {
        verifyHandle();
        checkCombineCondition(nativeNotNull(this.handle, property.getId()));
        return this;
    }

    public QueryBuilder<T> relationCount(RelationInfo<T, ?> relationInfo, int i) {
        verifyHandle();
        checkCombineCondition(nativeRelationCount(this.handle, this.storeHandle, relationInfo.targetInfo.getEntityId(), relationInfo.targetIdProperty.id, i));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeEqual(this.handle, property.getId(), j));
        return this;
    }

    public QueryBuilder<T> notEqual(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeNotEqual(this.handle, property.getId(), j));
        return this;
    }

    public QueryBuilder<T> less(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), j, false));
        return this;
    }

    public QueryBuilder<T> lessOrEqual(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), j, true));
        return this;
    }

    public QueryBuilder<T> greater(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), j, false));
        return this;
    }

    public QueryBuilder<T> greaterOrEqual(Property<T> property, long j) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), j, true));
        return this;
    }

    public QueryBuilder<T> between(Property<T> property, long j, long j2) {
        verifyHandle();
        checkCombineCondition(nativeBetween(this.handle, property.getId(), j, j2));
        return this;
    }

    public QueryBuilder<T> in(Property<T> property, long[] jArr) {
        verifyHandle();
        checkCombineCondition(nativeIn(this.handle, property.getId(), jArr, false));
        return this;
    }

    public QueryBuilder<T> notIn(Property<T> property, long[] jArr) {
        verifyHandle();
        checkCombineCondition(nativeIn(this.handle, property.getId(), jArr, true));
        return this;
    }

    public QueryBuilder<T> in(Property<T> property, int[] iArr) {
        verifyHandle();
        checkCombineCondition(nativeIn(this.handle, property.getId(), iArr, false));
        return this;
    }

    public QueryBuilder<T> notIn(Property<T> property, int[] iArr) {
        verifyHandle();
        checkCombineCondition(nativeIn(this.handle, property.getId(), iArr, true));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, boolean z) {
        verifyHandle();
        checkCombineCondition(nativeEqual(this.handle, property.getId(), z ? 1L : 0L));
        return this;
    }

    public QueryBuilder<T> notEqual(Property<T> property, boolean z) {
        verifyHandle();
        checkCombineCondition(nativeNotEqual(this.handle, property.getId(), z ? 1L : 0L));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeEqual(this.handle, property.getId(), date.getTime()));
        return this;
    }

    public QueryBuilder<T> notEqual(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeNotEqual(this.handle, property.getId(), date.getTime()));
        return this;
    }

    public QueryBuilder<T> less(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), date.getTime(), false));
        return this;
    }

    public QueryBuilder<T> lessOrEqual(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), date.getTime(), true));
        return this;
    }

    public QueryBuilder<T> greater(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), date.getTime(), false));
        return this;
    }

    public QueryBuilder<T> greaterOrEqual(Property<T> property, Date date) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), date.getTime(), true));
        return this;
    }

    public QueryBuilder<T> between(Property<T> property, Date date, Date date2) {
        verifyHandle();
        checkCombineCondition(nativeBetween(this.handle, property.getId(), date.getTime(), date2.getTime()));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeEqual(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> notEqual(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeNotEqual(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> contains(Property<T> property, String str, StringOrder stringOrder) {
        if (String[].class == property.type) {
            throw new UnsupportedOperationException("For String[] only containsElement() is supported at this time.");
        }
        verifyHandle();
        checkCombineCondition(nativeContains(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> containsElement(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeContainsElement(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> containsKeyValue(Property<T> property, String str, String str2, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeContainsKeyValue(this.handle, property.getId(), str, str2, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> startsWith(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeStartsWith(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> endsWith(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeEndsWith(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> less(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE, false));
        return this;
    }

    public QueryBuilder<T> lessOrEqual(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE, true));
        return this;
    }

    public QueryBuilder<T> greater(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE, false));
        return this;
    }

    public QueryBuilder<T> greaterOrEqual(Property<T> property, String str, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), str, stringOrder == StringOrder.CASE_SENSITIVE, true));
        return this;
    }

    public QueryBuilder<T> in(Property<T> property, String[] strArr, StringOrder stringOrder) {
        verifyHandle();
        checkCombineCondition(nativeIn(this.handle, property.getId(), strArr, stringOrder == StringOrder.CASE_SENSITIVE));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, double d, double d2) {
        return between(property, d - d2, d + d2);
    }

    public QueryBuilder<T> less(Property<T> property, double d) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), d, false));
        return this;
    }

    public QueryBuilder<T> lessOrEqual(Property<T> property, double d) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), d, true));
        return this;
    }

    public QueryBuilder<T> greater(Property<T> property, double d) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), d, false));
        return this;
    }

    public QueryBuilder<T> greaterOrEqual(Property<T> property, double d) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), d, true));
        return this;
    }

    public QueryBuilder<T> between(Property<T> property, double d, double d2) {
        verifyHandle();
        checkCombineCondition(nativeBetween(this.handle, property.getId(), d, d2));
        return this;
    }

    public QueryBuilder<T> equal(Property<T> property, byte[] bArr) {
        verifyHandle();
        checkCombineCondition(nativeEqual(this.handle, property.getId(), bArr));
        return this;
    }

    public QueryBuilder<T> less(Property<T> property, byte[] bArr) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), bArr, false));
        return this;
    }

    public QueryBuilder<T> lessOrEqual(Property<T> property, byte[] bArr) {
        verifyHandle();
        checkCombineCondition(nativeLess(this.handle, property.getId(), bArr, true));
        return this;
    }

    public QueryBuilder<T> greater(Property<T> property, byte[] bArr) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), bArr, false));
        return this;
    }

    public QueryBuilder<T> greaterOrEqual(Property<T> property, byte[] bArr) {
        verifyHandle();
        checkCombineCondition(nativeGreater(this.handle, property.getId(), bArr, true));
        return this;
    }
}
