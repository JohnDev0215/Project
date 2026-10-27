package com.amplifyframework.core.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.annotations.HasOne;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.Indexes;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import com.amplifyframework.util.FieldFinder;
import com.amplifyframework.util.Immutable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelSchema {
    private final Map<String, ModelAssociation> associations;
    private final List<AuthRule> authRules;
    private final Map<String, ModelField> fields;
    private final Map<String, ModelIndex> indexes;
    private final String listPluralName;
    private final Class<? extends Model> modelClass;
    private final int modelSchemaVersion;
    private final Model.Type modelType;
    private final String name;
    private final String pluralName;
    private final String syncPluralName;

    private ModelSchema(Builder builder) {
        this.name = builder.name;
        this.pluralName = builder.pluralName;
        this.listPluralName = builder.listPluralName;
        this.syncPluralName = builder.syncPluralName;
        this.authRules = builder.authRules;
        this.fields = builder.fields;
        this.associations = builder.associations;
        this.indexes = builder.indexes;
        this.modelClass = builder.modelClass;
        this.modelType = builder.type;
        this.modelSchemaVersion = builder.modelSchemaVersion;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static ModelSchema fromModelClass(@NonNull Class<? extends Model> cls) throws AmplifyException {
        try {
            List<Field> listFindModelFieldsIn = FieldFinder.findModelFieldsIn(cls);
            TreeMap treeMap = new TreeMap();
            TreeMap treeMap2 = new TreeMap();
            TreeMap treeMap3 = new TreeMap();
            ArrayList arrayList = new ArrayList();
            ModelConfig modelConfig = (ModelConfig) cls.getAnnotation(ModelConfig.class);
            String simpleName = cls.getSimpleName();
            String strSyncPluralName = null;
            String strPluralName = (modelConfig == null || modelConfig.pluralName().isEmpty()) ? null : modelConfig.pluralName();
            Model.Type type = modelConfig != null ? modelConfig.type() : Model.Type.USER;
            String strListPluralName = (modelConfig == null || modelConfig.listPluralName().isEmpty()) ? null : modelConfig.listPluralName();
            if (modelConfig != null && !modelConfig.syncPluralName().isEmpty()) {
                strSyncPluralName = modelConfig.syncPluralName();
            }
            int iVersion = modelConfig != null ? modelConfig.version() : 0;
            if (modelConfig != null) {
                com.amplifyframework.core.model.annotations.AuthRule[] authRuleArrAuthRules = modelConfig.authRules();
                int length = authRuleArrAuthRules.length;
                int i = 0;
                while (i < length) {
                    arrayList.add(new AuthRule(authRuleArrAuthRules[i]));
                    i++;
                    authRuleArrAuthRules = authRuleArrAuthRules;
                }
            }
            Annotation[] annotations = cls.getAnnotations();
            int length2 = annotations.length;
            int i2 = 0;
            while (i2 < length2) {
                Annotation annotation = annotations[i2];
                Annotation[] annotationArr = annotations;
                int i3 = length2;
                if (annotation.annotationType().isAssignableFrom(Indexes.class)) {
                    Index[] indexArrValue = ((Indexes) annotation).value();
                    int length3 = indexArrValue.length;
                    int i4 = 0;
                    while (i4 < length3) {
                        Index[] indexArr = indexArrValue;
                        ModelIndex modelIndexCreateModelIndex = createModelIndex(indexArrValue[i4]);
                        treeMap3.put(modelIndexCreateModelIndex.getIndexName(), modelIndexCreateModelIndex);
                        i4++;
                        length3 = length3;
                        indexArrValue = indexArr;
                    }
                } else if (annotation.annotationType().isAssignableFrom(Index.class)) {
                    ModelIndex modelIndexCreateModelIndex2 = createModelIndex((Index) annotation);
                    treeMap3.put(modelIndexCreateModelIndex2.getIndexName(), modelIndexCreateModelIndex2);
                }
                i2++;
                annotations = annotationArr;
                length2 = i3;
            }
            for (Field field : listFindModelFieldsIn) {
                ModelField modelFieldCreateModelField = createModelField(field);
                if (modelFieldCreateModelField != null) {
                    treeMap.put(field.getName(), modelFieldCreateModelField);
                }
                ModelAssociation modelAssociationCreateModelAssociation = createModelAssociation(field);
                if (modelAssociationCreateModelAssociation != null) {
                    treeMap2.put(field.getName(), modelAssociationCreateModelAssociation);
                }
            }
            return builder().name(simpleName).pluralName(strPluralName).listPluralName(strListPluralName).syncPluralName(strSyncPluralName).authRules(arrayList).fields(treeMap).associations(treeMap2).indexes(treeMap3).modelClass(cls).modelType(type).version(iVersion).build();
        } catch (Exception e) {
            throw new AmplifyException("Error in constructing a ModelSchema.", e, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
    }

    public boolean hasModelLevelRules() {
        return this.authRules.size() > 0;
    }

    private static ModelField createModelField(Field field) {
        com.amplifyframework.core.model.annotations.ModelField modelField = (com.amplifyframework.core.model.annotations.ModelField) field.getAnnotation(com.amplifyframework.core.model.annotations.ModelField.class);
        if (modelField == null) {
            return null;
        }
        String name = field.getName();
        Class<?> type = field.getType();
        String strTargetType = modelField.targetType();
        ArrayList arrayList = new ArrayList();
        for (com.amplifyframework.core.model.annotations.AuthRule authRule : modelField.authRules()) {
            arrayList.add(new AuthRule(authRule));
        }
        ModelField.ModelFieldBuilder modelFieldBuilderJavaClassForValue = ModelField.builder().name(name).javaClassForValue(type);
        if (strTargetType.isEmpty()) {
            strTargetType = type.getSimpleName();
        }
        return modelFieldBuilderJavaClassForValue.targetType(strTargetType).isReadOnly(modelField.isReadOnly()).isRequired(modelField.isRequired()).isArray(Collection.class.isAssignableFrom(field.getType())).isEnum(Enum.class.isAssignableFrom(field.getType())).isModel(Model.class.isAssignableFrom(field.getType())).authRules(arrayList).build();
    }

    private static ModelAssociation createModelAssociation(Field field) {
        if (field.isAnnotationPresent(BelongsTo.class)) {
            Annotation annotation = field.getAnnotation(BelongsTo.class);
            Objects.requireNonNull(annotation);
            BelongsTo belongsTo = (BelongsTo) annotation;
            return ModelAssociation.builder().name(BelongsTo.class.getSimpleName()).targetName(belongsTo.targetName()).targetNames(belongsTo.targetNames()).associatedType(belongsTo.type().getSimpleName()).associatedName(field.getName()).build();
        }
        if (field.isAnnotationPresent(HasOne.class)) {
            Annotation annotation2 = field.getAnnotation(HasOne.class);
            Objects.requireNonNull(annotation2);
            HasOne hasOne = (HasOne) annotation2;
            return ModelAssociation.builder().name(HasOne.class.getSimpleName()).associatedName(hasOne.associatedWith()).associatedType(hasOne.type().getSimpleName()).build();
        }
        if (!field.isAnnotationPresent(HasMany.class)) {
            return null;
        }
        Annotation annotation3 = field.getAnnotation(HasMany.class);
        Objects.requireNonNull(annotation3);
        HasMany hasMany = (HasMany) annotation3;
        return ModelAssociation.builder().name(HasMany.class.getSimpleName()).associatedName(hasMany.associatedWith()).associatedType(hasMany.type().getSimpleName()).build();
    }

    private static ModelIndex createModelIndex(Index index) {
        return ModelIndex.builder().indexName(index.name()).indexFieldNames(Arrays.asList(index.fields())).build();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public String getPluralName() {
        return this.pluralName;
    }

    @Nullable
    public String getListPluralName() {
        return this.listPluralName;
    }

    @Nullable
    public int getVersion() {
        return this.modelSchemaVersion;
    }

    @Nullable
    public String getSyncPluralName() {
        return this.syncPluralName;
    }

    public List<AuthRule> getAuthRules() {
        return this.authRules;
    }

    @NonNull
    public Map<String, ModelField> getFields() {
        return this.fields;
    }

    @NonNull
    public Map<String, ModelAssociation> getAssociations() {
        return Immutable.of(this.associations);
    }

    @NonNull
    public Map<String, ModelIndex> getIndexes() {
        return this.indexes;
    }

    @NonNull
    public List<String> getPrimaryIndexFields() {
        ModelIndex modelIndex = this.indexes.get(SQLiteCommandFactory.UNDEFINED);
        return (modelIndex == null || modelIndex.getIndexFieldNames().size() < 1) ? Arrays.asList(PrimaryKey.fieldName()) : modelIndex.getIndexFieldNames();
    }

    @NonNull
    public String getPrimaryKeyName() {
        return getPrimaryIndexFields().get(0);
    }

    @NonNull
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelSchema.class != obj.getClass()) {
            return false;
        }
        ModelSchema modelSchema = (ModelSchema) obj;
        return ObjectsCompat.equals(getName(), modelSchema.getName()) && ObjectsCompat.equals(getPluralName(), modelSchema.getPluralName()) && ObjectsCompat.equals(getListPluralName(), modelSchema.getListPluralName()) && ObjectsCompat.equals(getSyncPluralName(), modelSchema.getSyncPluralName()) && ObjectsCompat.equals(getAuthRules(), modelSchema.getAuthRules()) && ObjectsCompat.equals(getFields(), modelSchema.getFields()) && ObjectsCompat.equals(getAssociations(), modelSchema.getAssociations()) && ObjectsCompat.equals(getIndexes(), modelSchema.getIndexes()) && ObjectsCompat.equals(getModelClass(), modelSchema.getModelClass());
    }

    public int hashCode() {
        return ObjectsCompat.hash(getName(), getPluralName(), getListPluralName(), getSyncPluralName(), getAuthRules(), getFields(), getAssociations(), getIndexes(), getModelClass(), Integer.valueOf(getVersion()));
    }

    public String toString() {
        return "ModelSchema{name='" + this.name + "', pluralName='" + this.pluralName + "', listPluralName='" + this.listPluralName + "', syncPluralName='" + this.syncPluralName + "', authRules=" + this.authRules + ", fields=" + this.fields + ", associations=" + this.associations + ", indexes=" + this.indexes + ", modelClass=" + this.modelClass + ", modelSchemaVersion=" + this.modelSchemaVersion + '}';
    }

    public Model.Type getModelType() {
        return this.modelType;
    }

    public static final class Builder {
        private String listPluralName;
        private Class<? extends Model> modelClass;
        private int modelSchemaVersion;
        private String name;
        private String pluralName;
        private String syncPluralName;
        private Model.Type type;
        private final List<AuthRule> authRules = new ArrayList();
        private final Map<String, ModelField> fields = new TreeMap();
        private final Map<String, ModelAssociation> associations = new TreeMap();
        private final Map<String, ModelIndex> indexes = new TreeMap();

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
        public Builder listPluralName(@Nullable String str) {
            this.listPluralName = str;
            return this;
        }

        @NonNull
        public Builder syncPluralName(@Nullable String str) {
            this.syncPluralName = str;
            return this;
        }

        @NonNull
        public Builder authRules(@NonNull List<AuthRule> list) {
            Objects.requireNonNull(list);
            this.authRules.clear();
            this.authRules.addAll(list);
            return this;
        }

        @NonNull
        public Builder fields(@NonNull Map<String, ModelField> map) {
            Objects.requireNonNull(map);
            this.fields.clear();
            this.fields.putAll(map);
            return this;
        }

        @NonNull
        public Builder associations(@NonNull Map<String, ModelAssociation> map) {
            Objects.requireNonNull(map);
            this.associations.clear();
            this.associations.putAll(map);
            return this;
        }

        @NonNull
        public Builder indexes(@NonNull Map<String, ModelIndex> map) {
            Objects.requireNonNull(map);
            this.indexes.clear();
            this.indexes.putAll(map);
            return this;
        }

        @NonNull
        public Builder modelClass(@NonNull Class<? extends Model> cls) {
            this.modelClass = cls;
            return this;
        }

        @NonNull
        public Builder modelType(@NonNull Model.Type type) {
            this.type = type;
            return this;
        }

        @NonNull
        public Builder version(@NonNull int i) {
            this.modelSchemaVersion = i;
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public ModelSchema build() {
            Objects.requireNonNull(this.name);
            return new ModelSchema(this);
        }
    }
}
