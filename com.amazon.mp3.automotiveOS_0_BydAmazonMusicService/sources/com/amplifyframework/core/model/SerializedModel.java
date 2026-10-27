package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.ModelIdentifier;
import com.amplifyframework.util.Immutable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class SerializedModel implements Model {
    private final Serializable modelId;
    private final ModelSchema modelSchema;
    private final Map<String, Object> serializedData;

    public interface BuilderSteps {

        public interface BuildStep {
            @NonNull
            SerializedModel build();
        }

        public interface ModelSchemaStep {
            @NonNull
            SerializedDataStep modelSchema(@Nullable ModelSchema modelSchema);
        }

        public interface SerializedDataStep {
            @NonNull
            BuildStep serializedData(@NonNull Map<String, Object> map);
        }
    }

    private SerializedModel(Serializable serializable, Map<String, Object> map, ModelSchema modelSchema) {
        this.modelId = serializable;
        this.serializedData = map;
        this.modelSchema = modelSchema;
    }

    public static <T extends Model> SerializedModel create(T t, ModelSchema modelSchema) throws AmplifyException {
        return builder().modelSchema(modelSchema).serializedData(ModelConverter.toMap(t, modelSchema)).build();
    }

    public static <T extends Model> SerializedModel difference(T t, T t2, ModelSchema modelSchema) throws AmplifyException {
        if (t2 == null) {
            return create(t, modelSchema);
        }
        Map<String, Object> map = ModelConverter.toMap(t, modelSchema);
        Map<String, Object> map2 = ModelConverter.toMap(t2, modelSchema);
        HashMap map3 = new HashMap();
        for (String str : map.keySet()) {
            HashSet hashSet = new HashSet();
            hashSet.add(PrimaryKey.fieldName());
            hashSet.addAll(modelSchema.getPrimaryIndexFields());
            if (hashSet.contains(str) || !ObjectsCompat.equals(map2.get(str), map.get(str))) {
                map3.put(str, map.get(str));
            }
        }
        return builder().modelSchema(modelSchema).serializedData(map3).build();
    }

    public static SerializedModel merge(SerializedModel serializedModel, SerializedModel serializedModel2, ModelSchema modelSchema) {
        HashMap map = new HashMap(serializedModel.serializedData);
        for (String str : serializedModel2.getSerializedData().keySet()) {
            if (!map.containsKey(str)) {
                map.put(str, serializedModel2.getSerializedData().get(str));
            }
        }
        return builder().modelSchema(modelSchema).serializedData(map).build();
    }

    public static Map<String, Object> parseSerializedData(Map<String, Object> map, String str, SchemaRegistry schemaRegistry) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, ModelField> entry : schemaRegistry.getModelSchemaForModelClass(str).getFields().entrySet()) {
            String key = entry.getKey();
            ModelField value = entry.getValue();
            if (map.containsKey(key)) {
                Object obj = map.get(key);
                if (obj == null) {
                    map2.put(key, null);
                } else if (value.isModel()) {
                    ModelSchema modelSchemaForModelClass = schemaRegistry.getModelSchemaForModelClass(value.getTargetType());
                    Map<String, Object> map3 = (Map) map.get(key);
                    if (map3 != null) {
                        map2.put(key, builder().modelSchema(modelSchemaForModelClass).serializedData(map3).build());
                    }
                } else if (value.isCustomType()) {
                    if (value.isArray()) {
                        List list = (List) obj;
                        ArrayList arrayList = new ArrayList();
                        if (!list.isEmpty()) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                arrayList.add(SerializedCustomType.builder().serializedData(SerializedCustomType.parseSerializedData((Map) it.next(), value.getTargetType(), schemaRegistry)).customTypeSchema(schemaRegistry.getCustomTypeSchemaForCustomTypeClass(value.getTargetType())).build());
                            }
                        }
                        map2.put(key, arrayList);
                    } else {
                        map2.put(key, SerializedCustomType.builder().serializedData(SerializedCustomType.parseSerializedData((Map) obj, value.getTargetType(), schemaRegistry)).customTypeSchema(schemaRegistry.getCustomTypeSchemaForCustomTypeClass(value.getTargetType())).build());
                    }
                } else {
                    map2.put(key, obj);
                }
            }
        }
        return map2;
    }

    @NonNull
    public static BuilderSteps.ModelSchemaStep builder() {
        return new Builder();
    }

    @Override // com.amplifyframework.core.model.Model
    @NonNull
    public Serializable resolveIdentifier() {
        return this.modelId;
    }

    @NonNull
    public Map<String, Object> getSerializedData() {
        return this.serializedData;
    }

    @Nullable
    public ModelSchema getModelSchema() {
        return this.modelSchema;
    }

    @Nullable
    public Object getValue(ModelField modelField) {
        return this.serializedData.get(modelField.getName());
    }

    @Override // com.amplifyframework.core.model.Model
    @Nullable
    public String getModelName() {
        ModelSchema modelSchema = this.modelSchema;
        if (modelSchema == null) {
            return null;
        }
        return modelSchema.getName();
    }

    @NonNull
    public String toString() {
        return "SerializedModel{id='" + this.modelId + "', serializedData=" + this.serializedData + ", modelName=" + getModelName() + '}';
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SerializedModel.class != obj.getClass()) {
            return false;
        }
        SerializedModel serializedModel = (SerializedModel) obj;
        return ObjectsCompat.equals(this.modelId, serializedModel.modelId) && ObjectsCompat.equals(this.serializedData, serializedModel.serializedData) && ObjectsCompat.equals(this.modelSchema, serializedModel.modelSchema);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.modelId, this.serializedData, this.modelSchema);
    }

    private static final class Builder implements BuilderSteps.SerializedDataStep, BuilderSteps.ModelSchemaStep, BuilderSteps.BuildStep {
        private String id;
        private ModelSchema modelSchema;
        private final Map<String, Object> serializedData;

        private Builder() {
            this.serializedData = new HashMap();
        }

        @Override // com.amplifyframework.core.model.SerializedModel.BuilderSteps.SerializedDataStep
        @NonNull
        public BuilderSteps.BuildStep serializedData(@NonNull Map<String, Object> map) {
            Map<String, Object> map2 = this.serializedData;
            Objects.requireNonNull(map);
            map2.putAll(map);
            this.id = ModelIdentifier.Helper.getUniqueKey(this.modelSchema, map);
            return this;
        }

        @Override // com.amplifyframework.core.model.SerializedModel.BuilderSteps.ModelSchemaStep
        @NonNull
        public BuilderSteps.SerializedDataStep modelSchema(@Nullable ModelSchema modelSchema) {
            this.modelSchema = modelSchema;
            return this;
        }

        @Override // com.amplifyframework.core.model.SerializedModel.BuilderSteps.BuildStep
        @NonNull
        public SerializedModel build() {
            return new SerializedModel(this.id, Immutable.of(this.serializedData), this.modelSchema);
        }
    }
}
