package io.objectbox.relation;

import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.ToManyGetter;
import io.objectbox.internal.ToOneGetter;
import io.objectbox.query.QueryCondition;
import io.objectbox.query.RelationCountCondition;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RelationInfo<SOURCE, TARGET> implements Serializable {
    private static final long serialVersionUID = 7412962174183812632L;
    public final ToManyGetter<TARGET, SOURCE> backlinkToManyGetter;
    public final ToOneGetter<TARGET, SOURCE> backlinkToOneGetter;
    public final int relationId;
    public final EntityInfo<SOURCE> sourceInfo;
    public final Property<?> targetIdProperty;
    public final EntityInfo<TARGET> targetInfo;
    public final int targetRelationId;
    public final ToManyGetter<SOURCE, TARGET> toManyGetter;
    public final ToOneGetter<SOURCE, TARGET> toOneGetter;

    /* JADX WARN: Multi-variable type inference failed */
    public RelationInfo(EntityInfo<SOURCE> entityInfo, EntityInfo<TARGET> entityInfo2, Property<SOURCE> property, ToOneGetter<SOURCE, TARGET> toOneGetter) {
        this.sourceInfo = entityInfo;
        this.targetInfo = entityInfo2;
        this.targetIdProperty = property;
        this.toOneGetter = toOneGetter;
        this.targetRelationId = 0;
        this.backlinkToOneGetter = null;
        this.backlinkToManyGetter = null;
        this.toManyGetter = null;
        this.relationId = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RelationInfo(EntityInfo<SOURCE> entityInfo, EntityInfo<TARGET> entityInfo2, ToManyGetter<SOURCE, TARGET> toManyGetter, Property<TARGET> property, ToOneGetter<TARGET, SOURCE> toOneGetter) {
        this.sourceInfo = entityInfo;
        this.targetInfo = entityInfo2;
        this.targetIdProperty = property;
        this.toManyGetter = toManyGetter;
        this.backlinkToOneGetter = toOneGetter;
        this.targetRelationId = 0;
        this.toOneGetter = null;
        this.backlinkToManyGetter = null;
        this.relationId = 0;
    }

    public RelationInfo(EntityInfo<SOURCE> entityInfo, EntityInfo<TARGET> entityInfo2, ToManyGetter<SOURCE, TARGET> toManyGetter, ToManyGetter<TARGET, SOURCE> toManyGetter2, int i) {
        this.sourceInfo = entityInfo;
        this.targetInfo = entityInfo2;
        this.toManyGetter = toManyGetter;
        this.targetRelationId = i;
        this.backlinkToManyGetter = toManyGetter2;
        this.targetIdProperty = null;
        this.toOneGetter = null;
        this.backlinkToOneGetter = null;
        this.relationId = 0;
    }

    public RelationInfo(EntityInfo<SOURCE> entityInfo, EntityInfo<TARGET> entityInfo2, ToManyGetter<SOURCE, TARGET> toManyGetter, int i) {
        this.sourceInfo = entityInfo;
        this.targetInfo = entityInfo2;
        this.toManyGetter = toManyGetter;
        this.relationId = i;
        this.targetRelationId = 0;
        this.targetIdProperty = null;
        this.toOneGetter = null;
        this.backlinkToOneGetter = null;
        this.backlinkToManyGetter = null;
    }

    public boolean isBacklink() {
        return (this.backlinkToManyGetter == null && this.backlinkToOneGetter == null) ? false : true;
    }

    public String toString() {
        return "RelationInfo from " + this.sourceInfo.getEntityClass() + " to " + this.targetInfo.getEntityClass();
    }

    public QueryCondition<SOURCE> relationCount(int i) {
        if (this.targetIdProperty == null) {
            throw new IllegalStateException("The relation count condition is only supported for 1:N (ToMany using @Backlink) relations.");
        }
        return new RelationCountCondition(this, i);
    }
}
