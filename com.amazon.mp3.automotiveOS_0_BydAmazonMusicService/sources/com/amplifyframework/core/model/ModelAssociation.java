package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.annotations.BelongsTo;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelAssociation {
    private final String associatedName;
    private final String associatedType;
    private final String name;
    private final String targetName;
    private final String[] targetNames;

    private ModelAssociation(@NonNull Builder builder) {
        String str = builder.name;
        Objects.requireNonNull(str);
        this.name = str;
        this.targetName = builder.targetName;
        this.targetNames = builder.targetNames;
        this.associatedName = builder.associatedName;
        this.associatedType = builder.associatedType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return this.name;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public String[] getTargetNames() {
        return this.targetNames;
    }

    public String getAssociatedName() {
        return this.associatedName;
    }

    public String getAssociatedType() {
        return this.associatedType;
    }

    public boolean isOwner() {
        return BelongsTo.class.getSimpleName().equals(getName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelAssociation.class != obj.getClass()) {
            return false;
        }
        ModelAssociation modelAssociation = (ModelAssociation) obj;
        if (ObjectsCompat.equals(this.name, modelAssociation.name) && ObjectsCompat.equals(this.targetName, modelAssociation.targetName) && ObjectsCompat.equals(this.associatedName, modelAssociation.associatedName)) {
            return ObjectsCompat.equals(this.associatedType, modelAssociation.associatedType);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.targetName;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.associatedName;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.associatedType;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "ModelAssociation{name='" + this.name + "', targetName='" + this.targetName + "', associatedName='" + this.associatedName + "', associatedType='" + this.associatedType + "'}";
    }

    public static final class Builder {
        private String associatedName;
        private String associatedType;
        private String name;
        private String targetName;
        private String[] targetNames;

        public Builder name(@NonNull String str) {
            Objects.requireNonNull(str);
            this.name = str;
            return this;
        }

        public Builder targetName(String str) {
            this.targetName = str;
            return this;
        }

        public Builder targetNames(String[] strArr) {
            this.targetNames = strArr;
            return this;
        }

        public Builder associatedName(String str) {
            this.associatedName = str;
            return this;
        }

        public Builder associatedType(String str) {
            this.associatedType = str;
            return this;
        }

        public ModelAssociation build() {
            return new ModelAssociation(this);
        }
    }
}
