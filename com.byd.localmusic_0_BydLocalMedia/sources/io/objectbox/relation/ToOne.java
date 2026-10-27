package io.objectbox.relation;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.exception.DbDetachedException;
import io.objectbox.internal.ReflectionCache;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class ToOne<TARGET> implements Serializable {
    private static final long serialVersionUID = 5092547044335989281L;
    private transient BoxStore boxStore;
    private boolean checkIdOfTargetForPut;
    private boolean debugRelations;
    private final Object entity;
    private transient Box<Object> entityBox;
    private final RelationInfo<Object, TARGET> relationInfo;
    private volatile long resolvedTargetId;
    private TARGET target;
    private volatile transient Box<TARGET> targetBox;
    private long targetId;
    private transient Field targetIdField;
    private final boolean virtualProperty;

    public ToOne(Object obj, RelationInfo<?, TARGET> relationInfo) {
        if (obj == null) {
            throw new IllegalArgumentException("No source entity given (null)");
        }
        if (relationInfo == null) {
            throw new IllegalArgumentException("No relation info given (null)");
        }
        this.entity = obj;
        this.relationInfo = relationInfo;
        this.virtualProperty = relationInfo.targetIdProperty.isVirtual;
    }

    public TARGET getTarget() {
        return getTarget(getTargetId());
    }

    public TARGET getTarget(long j) {
        synchronized (this) {
            if (this.resolvedTargetId == j) {
                return this.target;
            }
            ensureBoxes(null);
            TARGET target = this.targetBox.get(j);
            setResolvedTarget(target, j);
            return target;
        }
    }

    private void ensureBoxes(@Nullable TARGET target) {
        if (this.targetBox == null) {
            try {
                BoxStore boxStore = (BoxStore) ReflectionCache.getInstance().getField(this.entity.getClass(), "__boxStore").get(this.entity);
                this.boxStore = boxStore;
                if (boxStore == null) {
                    if (target != null) {
                        this.boxStore = (BoxStore) ReflectionCache.getInstance().getField(target.getClass(), "__boxStore").get(target);
                    }
                    if (this.boxStore == null) {
                        throw new DbDetachedException("Cannot resolve relation for detached entities, call box.attach(entity) beforehand.");
                    }
                }
                this.debugRelations = this.boxStore.isDebugRelations();
                this.entityBox = this.boxStore.boxFor(this.relationInfo.sourceInfo.getEntityClass());
                this.targetBox = this.boxStore.boxFor(this.relationInfo.targetInfo.getEntityClass());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public TARGET getCachedTarget() {
        return this.target;
    }

    public boolean isResolved() {
        return this.resolvedTargetId == getTargetId();
    }

    public boolean isResolvedAndNotNull() {
        return this.resolvedTargetId != 0 && this.resolvedTargetId == getTargetId();
    }

    public boolean isNull() {
        return getTargetId() == 0 && this.target == null;
    }

    public void setTargetId(long j) {
        if (this.virtualProperty) {
            this.targetId = j;
        } else {
            try {
                getTargetIdField().set(this.entity, Long.valueOf(j));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Could not update to-one ID in entity", e);
            }
        }
        if (j != 0) {
            this.checkIdOfTargetForPut = false;
        }
    }

    void setAndUpdateTargetId(long j) {
        setTargetId(j);
        ensureBoxes(null);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void setTarget(@Nullable TARGET target) {
        if (target != null) {
            long id = this.relationInfo.targetInfo.getIdGetter().getId(target);
            this.checkIdOfTargetForPut = id == 0;
            setTargetId(id);
            setResolvedTarget(target, id);
            return;
        }
        setTargetId(0L);
        clearResolved();
    }

    public void setAndPutTarget(@Nullable TARGET target) {
        ensureBoxes(target);
        if (target != null) {
            long id = this.targetBox.getId(target);
            if (id == 0) {
                setAndPutTargetAlways(target);
                return;
            }
            setTargetId(id);
            setResolvedTarget(target, id);
            this.entityBox.put(this.entity);
            return;
        }
        setTargetId(0L);
        clearResolved();
        this.entityBox.put(this.entity);
    }

    public void setAndPutTargetAlways(@Nullable final TARGET target) {
        ensureBoxes(target);
        if (target != null) {
            this.boxStore.runInTx(new Runnable() { // from class: io.objectbox.relation.-$$Lambda$ToOne$GfavJQ_2RcadMPLUwV4ZafUxKGY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setAndPutTargetAlways$0$ToOne(target);
                }
            });
            return;
        }
        setTargetId(0L);
        clearResolved();
        this.entityBox.put(this.entity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$setAndPutTargetAlways$0$ToOne(Object obj) {
        setResolvedTarget(obj, this.targetBox.put(obj));
        this.entityBox.put(this.entity);
    }

    private synchronized void setResolvedTarget(@Nullable TARGET target, long j) {
        if (this.debugRelations) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("Setting resolved ToOne target to ");
            sb.append(target == null ? "null" : "non-null");
            sb.append(" for ID ");
            sb.append(j);
            printStream.println(sb.toString());
        }
        this.resolvedTargetId = j;
        this.target = target;
    }

    private synchronized void clearResolved() {
        this.resolvedTargetId = 0L;
        this.target = null;
    }

    public long getTargetId() {
        if (this.virtualProperty) {
            return this.targetId;
        }
        Field targetIdField = getTargetIdField();
        try {
            Long l = (Long) targetIdField.get(this.entity);
            if (l != null) {
                return l.longValue();
            }
            return 0L;
        } catch (IllegalAccessException unused) {
            throw new RuntimeException("Could not access field " + targetIdField);
        }
    }

    private Field getTargetIdField() {
        if (this.targetIdField == null) {
            this.targetIdField = ReflectionCache.getInstance().getField(this.entity.getClass(), this.relationInfo.targetIdProperty.name);
        }
        return this.targetIdField;
    }

    public boolean internalRequiresPutTarget() {
        return this.checkIdOfTargetForPut && this.target != null && getTargetId() == 0;
    }

    public void internalPutTarget(Cursor<TARGET> cursor) {
        this.checkIdOfTargetForPut = false;
        long jPut = cursor.put(this.target);
        setTargetId(jPut);
        setResolvedTarget(this.target, jPut);
    }

    Object getEntity() {
        return this.entity;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ToOne)) {
            return false;
        }
        ToOne toOne = (ToOne) obj;
        return this.relationInfo == toOne.relationInfo && getTargetId() == toOne.getTargetId();
    }

    public int hashCode() {
        long targetId = getTargetId();
        return (int) (targetId ^ (targetId >>> 32));
    }
}
