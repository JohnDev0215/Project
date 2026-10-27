package com.amplifyframework.core.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class CustomTypeSchema {
    private final Map<String, CustomTypeField> fields;
    private final String name;
    private final String pluralName;

    private CustomTypeSchema(Builder builder) {
        this.name = builder.name;
        this.pluralName = builder.pluralName;
        this.fields = builder.fields;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getPluralName() {
        return this.pluralName;
    }

    @NonNull
    public Map<String, CustomTypeField> getFields() {
        return this.fields;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CustomTypeSchema.class != obj.getClass()) {
            return false;
        }
        CustomTypeSchema customTypeSchema = (CustomTypeSchema) obj;
        return ObjectsCompat.equals(getName(), customTypeSchema.getName()) && ObjectsCompat.equals(getPluralName(), customTypeSchema.getPluralName()) && ObjectsCompat.equals(getFields(), customTypeSchema.getFields());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getName(), getPluralName(), getFields());
    }

    public String toString() {
        return "CustomTypeSchema{name='" + this.name + "', pluralName='" + this.pluralName + "', fields=" + this.fields + '}';
    }

    public static final class Builder {
        private final Map<String, CustomTypeField> fields = new TreeMap();
        private String name;
        private String pluralName;

        Builder() {
        }

        @NonNull
        public Builder name(@NonNull String str) {
            Objects.requireNonNull(str);
            this.name = str;
            return this;
        }

        @NonNull
        public Builder pluralName(@Nullable String str) {
            this.pluralName = str;
            return this;
        }

        @NonNull
        public Builder fields(@NonNull Map<String, CustomTypeField> map) {
            Objects.requireNonNull(map);
            this.fields.clear();
            this.fields.putAll(map);
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public CustomTypeSchema build() {
            Objects.requireNonNull(this.name);
            return new CustomTypeSchema(this);
        }
    }
}
