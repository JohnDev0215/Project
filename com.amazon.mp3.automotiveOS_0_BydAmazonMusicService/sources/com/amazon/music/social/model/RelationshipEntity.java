package com.amazon.music.social.model;

/* JADX INFO: loaded from: classes4.dex */
public final class RelationshipEntity {
    private String id;
    private ObjectType type;

    public enum ObjectType {
        ARTIST,
        CUSTOMER,
        PROFILE
    }

    public RelationshipEntity(String str, ObjectType objectType) {
        this.id = str;
        this.type = objectType;
    }

    public String getId() {
        return this.id;
    }

    public ObjectType getType() {
        return this.type;
    }
}
