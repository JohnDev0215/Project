package com.amplifyframework.core.model;

import com.amplifyframework.AmplifyException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public interface ModelProvider {
    Set<Class<? extends Model>> models();

    String version();

    default Map<String, ModelSchema> modelSchemas() {
        HashMap map = new HashMap();
        if (models() == null) {
            return map;
        }
        for (Class<? extends Model> cls : models()) {
            try {
                map.put(cls.getSimpleName(), ModelSchema.fromModelClass(cls));
            } catch (AmplifyException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    default Map<String, CustomTypeSchema> customTypeSchemas() {
        return new HashMap();
    }

    default Set<String> modelNames() {
        HashSet hashSet = new HashSet();
        if (models() == null) {
            return hashSet;
        }
        Iterator<Class<? extends Model>> it = models().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getSimpleName());
        }
        return hashSet;
    }

    default Set<String> customTypeNames() {
        return new HashSet();
    }
}
