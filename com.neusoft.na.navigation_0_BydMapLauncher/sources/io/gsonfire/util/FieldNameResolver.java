package io.gsonfire.util;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public final class FieldNameResolver {
    private final ConcurrentMap<Field, String> fieldNameCache = new ConcurrentHashMap();
    private final FieldNamingStrategy fieldNamingStrategy;

    public FieldNameResolver(Gson gson) {
        this.fieldNamingStrategy = getFieldNamingStrategy(gson);
    }

    public String getFieldName(Field field) {
        String strValue = this.fieldNameCache.get(field);
        if (strValue == null) {
            SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
            if (serializedName == null) {
                strValue = this.fieldNamingStrategy.translateName(field);
            } else {
                strValue = serializedName.value();
            }
            if (!this.fieldNameCache.containsKey(field)) {
                this.fieldNameCache.put(field, strValue);
            }
        }
        return strValue;
    }

    private FieldNamingStrategy getFieldNamingStrategy(Gson gson) {
        return gson.fieldNamingStrategy();
    }
}
