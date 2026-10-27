package com.amplifyframework.datastore.model;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.CustomTypeSchema;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public final class CompoundModelProvider implements ModelProvider {
    private final SimpleModelProvider delegateProvider;

    private CompoundModelProvider(SimpleModelProvider simpleModelProvider) {
        this.delegateProvider = simpleModelProvider;
    }

    @NonNull
    public static CompoundModelProvider of(@NonNull ModelProvider... modelProviderArr) {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        StringBuilder sb = new StringBuilder();
        for (ModelProvider modelProvider : modelProviderArr) {
            sb.append(modelProvider.version());
            map.putAll(modelProvider.modelSchemas());
            map2.putAll(modelProvider.customTypeSchemas());
        }
        return new CompoundModelProvider(SimpleModelProvider.instance(UUID.nameUUIDFromBytes(sb.toString().getBytes()).toString(), map, map2));
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    @NonNull
    public Set<Class<? extends Model>> models() {
        return this.delegateProvider.models();
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    public Map<String, ModelSchema> modelSchemas() {
        return this.delegateProvider.modelSchemas();
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    public Set<String> modelNames() {
        return this.delegateProvider.modelNames();
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    public Map<String, CustomTypeSchema> customTypeSchemas() {
        return this.delegateProvider.customTypeSchemas();
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    public Set<String> customTypeNames() {
        return this.delegateProvider.customTypeNames();
    }

    @Override // com.amplifyframework.core.model.ModelProvider
    @NonNull
    public String version() {
        return this.delegateProvider.version();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ModelProvider) {
            return version().equals(((ModelProvider) obj).version());
        }
        return false;
    }

    public int hashCode() {
        return version().hashCode();
    }

    @NonNull
    public String toString() {
        return "CompoundModelsProvider{delegateProvider=" + this.delegateProvider + '}';
    }
}
