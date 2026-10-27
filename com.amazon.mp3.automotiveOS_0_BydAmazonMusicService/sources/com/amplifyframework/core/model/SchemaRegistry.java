package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.util.Immutable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class SchemaRegistry {
    private static SchemaRegistry instance;
    private final Map<String, ModelSchema> modelSchemaMap = new HashMap();
    private final Map<String, CustomTypeSchema> customTypeSchemaMap = new HashMap();

    private SchemaRegistry() {
    }

    public synchronized void register(@NonNull Set<Class<? extends Model>> set) throws AmplifyException {
        for (Class<? extends Model> cls : set) {
            this.modelSchemaMap.put(cls.getSimpleName(), ModelSchema.fromModelClass(cls));
        }
    }

    public synchronized void register(@NonNull Map<String, ModelSchema> map) {
        this.modelSchemaMap.putAll(map);
    }

    public synchronized void register(@NonNull Map<String, ModelSchema> map, @NonNull Map<String, CustomTypeSchema> map2) {
        this.modelSchemaMap.putAll(map);
        this.customTypeSchemaMap.putAll(map2);
    }

    public synchronized void register(@NonNull String str, @NonNull ModelSchema modelSchema) {
        this.modelSchemaMap.put(str, modelSchema);
    }

    public synchronized void register(@NonNull String str, @NonNull CustomTypeSchema customTypeSchema) {
        this.customTypeSchemaMap.put(str, customTypeSchema);
    }

    public synchronized ModelSchema getModelSchemaForModelClass(@NonNull String str) {
        return this.modelSchemaMap.get(str);
    }

    public synchronized CustomTypeSchema getCustomTypeSchemaForCustomTypeClass(@NonNull String str) {
        return this.customTypeSchemaMap.get(str);
    }

    public synchronized <T extends Model> ModelSchema getModelSchemaForModelClass(@NonNull Class<T> cls) {
        return this.modelSchemaMap.get(cls.getSimpleName());
    }

    public synchronized <T extends Model> ModelSchema getModelSchemaForModelInstance(@NonNull T t) {
        return this.modelSchemaMap.get(t.getClass().getSimpleName());
    }

    @NonNull
    public Map<String, ModelSchema> getModelSchemaMap() {
        return Immutable.of(this.modelSchemaMap);
    }

    @NonNull
    public Map<String, CustomTypeSchema> getCustomTypeSchemaMap() {
        return Immutable.of(this.customTypeSchemaMap);
    }

    @NonNull
    public static synchronized SchemaRegistry instance() {
        if (instance == null) {
            instance = new SchemaRegistry();
        }
        return instance;
    }

    public void clear() {
        this.modelSchemaMap.clear();
        this.customTypeSchemaMap.clear();
    }
}
