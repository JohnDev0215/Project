package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelField {
    private final List<AuthRule> authRules;
    private final boolean isArray;
    private final boolean isCustomType;
    private final boolean isEnum;
    private final boolean isModel;
    private final boolean isReadOnly;
    private final boolean isRequired;
    private final Class<?> javaClassForValue;
    private final String name;
    private final String targetType;

    private ModelField(@NonNull ModelFieldBuilder modelFieldBuilder) {
        this.name = modelFieldBuilder.name;
        this.javaClassForValue = modelFieldBuilder.javaClassForValue;
        this.targetType = modelFieldBuilder.targetType;
        this.isReadOnly = modelFieldBuilder.isReadOnly;
        this.isRequired = modelFieldBuilder.isRequired;
        this.isArray = modelFieldBuilder.isArray;
        this.isEnum = modelFieldBuilder.isEnum;
        this.isModel = modelFieldBuilder.isModel;
        this.isCustomType = modelFieldBuilder.isCustomType;
        this.authRules = modelFieldBuilder.authRules;
    }

    public static ModelFieldBuilder builder() {
        return new ModelFieldBuilder();
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public Class<?> getJavaClassForValue() {
        return this.javaClassForValue;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public boolean isArray() {
        return this.isArray;
    }

    public boolean isEnum() {
        return this.isEnum;
    }

    public boolean isModel() {
        return this.isModel;
    }

    public boolean isCustomType() {
        return this.isCustomType;
    }

    public List<AuthRule> getAuthRules() {
        return this.authRules;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelField.class != obj.getClass()) {
            return false;
        }
        ModelField modelField = (ModelField) obj;
        if (this.isReadOnly == modelField.isReadOnly && this.isRequired == modelField.isRequired && this.isArray == modelField.isArray && this.isEnum == modelField.isEnum && this.isModel == modelField.isModel && this.isCustomType == modelField.isCustomType && ObjectsCompat.equals(this.name, modelField.name) && ObjectsCompat.equals(this.javaClassForValue, modelField.javaClassForValue)) {
            return ObjectsCompat.equals(this.targetType, modelField.targetType);
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Class<?> cls = this.javaClassForValue;
        int iHashCode2 = (iHashCode + (cls != null ? cls.hashCode() : 0)) * 31;
        String str2 = this.targetType;
        return ((((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + (this.isReadOnly ? 1 : 0)) * 31) + (this.isRequired ? 1 : 0)) * 31) + (this.isArray ? 1 : 0)) * 31) + (this.isEnum ? 1 : 0)) * 31) + (this.isModel ? 1 : 0)) * 31) + (this.isCustomType ? 1 : 0);
    }

    public String toString() {
        return "ModelField{name='" + this.name + "', javaClassForValue='" + this.javaClassForValue + "', targetType='" + this.targetType + "', isReadOnly=" + this.isReadOnly + ", isRequired=" + this.isRequired + ", isArray=" + this.isArray + ", isEnum=" + this.isEnum + ", isModel=" + this.isModel + ", isCustomType=" + this.isCustomType + '}';
    }

    public static class ModelFieldBuilder {
        private Class<?> javaClassForValue;
        private String name;
        private String targetType;
        private boolean isReadOnly = false;
        private boolean isRequired = false;
        private boolean isArray = false;
        private boolean isEnum = false;
        private boolean isModel = false;
        private boolean isCustomType = false;
        private List<AuthRule> authRules = new ArrayList();

        public ModelFieldBuilder name(String str) {
            this.name = str;
            return this;
        }

        public ModelFieldBuilder javaClassForValue(@Nullable Class<?> cls) {
            this.javaClassForValue = cls;
            return this;
        }

        public ModelFieldBuilder targetType(String str) {
            this.targetType = str;
            return this;
        }

        public ModelFieldBuilder isReadOnly(boolean z) {
            this.isReadOnly = z;
            return this;
        }

        public ModelFieldBuilder isRequired(boolean z) {
            this.isRequired = z;
            return this;
        }

        public ModelFieldBuilder isArray(boolean z) {
            this.isArray = z;
            return this;
        }

        public ModelFieldBuilder isEnum(boolean z) {
            this.isEnum = z;
            return this;
        }

        public ModelFieldBuilder isModel(boolean z) {
            this.isModel = z;
            return this;
        }

        public ModelFieldBuilder isCustomType(boolean z) {
            this.isCustomType = z;
            return this;
        }

        public ModelFieldBuilder authRules(List<AuthRule> list) {
            this.authRules = list;
            return this;
        }

        public ModelField build() {
            return new ModelField(this);
        }
    }
}
