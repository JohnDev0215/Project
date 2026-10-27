package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class SerializedCustomType {
    private final CustomTypeSchema customTypeSchema;
    private final Map<String, Object> serializedData;

    public interface BuilderSteps {

        public interface BuildStep {
            @NonNull
            SerializedCustomType build();
        }

        public interface CustomTypeSchemaStep {
            @NonNull
            BuildStep customTypeSchema(@Nullable CustomTypeSchema customTypeSchema);
        }

        public interface SerializedDataStep {
            @NonNull
            CustomTypeSchemaStep serializedData(@NonNull Map<String, Object> map);
        }
    }

    private SerializedCustomType(Map<String, Object> map, CustomTypeSchema customTypeSchema) {
        this.serializedData = map;
        this.customTypeSchema = customTypeSchema;
    }

    @NonNull
    public static BuilderSteps.SerializedDataStep builder() {
        return new Builder();
    }

    public static Map<String, Object> parseSerializedData(Map<String, Object> map, String str, SchemaRegistry schemaRegistry) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, CustomTypeField> entry : schemaRegistry.getCustomTypeSchemaForCustomTypeClass(str).getFields().entrySet()) {
            String key = entry.getKey();
            CustomTypeField value = entry.getValue();
            if (map.containsKey(key)) {
                Object obj = map.get(key);
                if (obj == null) {
                    map2.put(key, null);
                } else if (value.isCustomType()) {
                    schemaRegistry.getCustomTypeSchemaForCustomTypeClass(value.getTargetType());
                    if (value.isArray()) {
                        List list = (List) obj;
                        ArrayList arrayList = new ArrayList();
                        if (!list.isEmpty()) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                arrayList.add(builder().serializedData(parseSerializedData((Map) it.next(), value.getTargetType(), schemaRegistry)).customTypeSchema(schemaRegistry.getCustomTypeSchemaForCustomTypeClass(value.getTargetType())).build());
                            }
                        }
                        map2.put(key, arrayList);
                    } else {
                        map2.put(key, builder().serializedData(parseSerializedData((Map) obj, value.getTargetType(), schemaRegistry)).customTypeSchema(schemaRegistry.getCustomTypeSchemaForCustomTypeClass(value.getTargetType())).build());
                    }
                } else {
                    map2.put(key, obj);
                }
            }
        }
        return map2;
    }

    @NonNull
    public Map<String, Object> getSerializedData() {
        return this.serializedData;
    }

    @Nullable
    public CustomTypeSchema getCustomTypeSchema() {
        return this.customTypeSchema;
    }

    @Nullable
    public Object getValue(ModelField modelField) {
        return this.serializedData.get(modelField.getName());
    }

    @Nullable
    public String getCustomTypeName() {
        CustomTypeSchema customTypeSchema = this.customTypeSchema;
        if (customTypeSchema == null) {
            return null;
        }
        return customTypeSchema.getName();
    }

    @NonNull
    public String toString() {
        return "SerializedCustomType{serializedData=" + this.serializedData + ", customTypeName=" + getCustomTypeSchema() + '}';
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SerializedCustomType.class != obj.getClass()) {
            return false;
        }
        SerializedCustomType serializedCustomType = (SerializedCustomType) obj;
        return ObjectsCompat.equals(this.serializedData, serializedCustomType.serializedData) && ObjectsCompat.equals(this.customTypeSchema, serializedCustomType.customTypeSchema);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.serializedData, this.customTypeSchema);
    }

    private static final class Builder implements BuilderSteps.SerializedDataStep, BuilderSteps.CustomTypeSchemaStep, BuilderSteps.BuildStep {
        private CustomTypeSchema customTypeSchema;
        private final Map<String, Object> serializedData;

        private Builder() {
            this.serializedData = new HashMap();
        }

        @Override // com.amplifyframework.core.model.SerializedCustomType.BuilderSteps.SerializedDataStep
        @NonNull
        public BuilderSteps.CustomTypeSchemaStep serializedData(@NonNull Map<String, Object> map) {
            Map<String, Object> map2 = this.serializedData;
            Objects.requireNonNull(map);
            map2.putAll(map);
            return this;
        }

        @Override // com.amplifyframework.core.model.SerializedCustomType.BuilderSteps.CustomTypeSchemaStep
        @NonNull
        public BuilderSteps.BuildStep customTypeSchema(@Nullable CustomTypeSchema customTypeSchema) {
            this.customTypeSchema = customTypeSchema;
            return this;
        }

        @Override // com.amplifyframework.core.model.SerializedCustomType.BuilderSteps.BuildStep
        @NonNull
        public SerializedCustomType build() {
            return new SerializedCustomType(Immutable.of(this.serializedData), this.customTypeSchema);
        }
    }
}
