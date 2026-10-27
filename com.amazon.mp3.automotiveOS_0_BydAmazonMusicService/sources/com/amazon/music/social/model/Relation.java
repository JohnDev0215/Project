package com.amazon.music.social.model;

/* JADX INFO: loaded from: classes4.dex */
public class Relation {
    private final EdgeType edgeType;
    private final RelationshipEntity source;
    private final RelationshipEntity target;

    public enum EdgeType {
        FOLLOWS
    }

    private Relation(RelationshipEntity relationshipEntity, RelationshipEntity relationshipEntity2, EdgeType edgeType) {
        this.source = relationshipEntity;
        this.target = relationshipEntity2;
        this.edgeType = edgeType;
    }

    public RelationshipEntity getSource() {
        return this.source;
    }

    public RelationshipEntity getTarget() {
        return this.target;
    }

    public EdgeType getEdgeType() {
        return this.edgeType;
    }

    public static Builder createBuilder(EdgeType edgeType, RelationshipEntity relationshipEntity) {
        return new Builder(edgeType, relationshipEntity);
    }

    public static final class Builder {
        private final EdgeType edgeType;
        private RelationshipEntity source;
        private final RelationshipEntity target;

        private Builder(EdgeType edgeType, RelationshipEntity relationshipEntity) {
            this.target = relationshipEntity;
            this.edgeType = edgeType;
        }

        public Relation build() {
            return new Relation(this.source, this.target, this.edgeType);
        }
    }
}
