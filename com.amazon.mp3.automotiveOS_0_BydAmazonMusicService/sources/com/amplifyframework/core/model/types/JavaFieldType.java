package com.amplifyframework.core.model.types;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.temporal.Temporal;

/* JADX INFO: loaded from: classes5.dex */
public enum JavaFieldType {
    BOOLEAN(Boolean.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    STRING(String.class),
    DATE(Temporal.Date.class),
    DATE_TIME(Temporal.DateTime.class),
    TIME(Temporal.Time.class),
    TIMESTAMP(Temporal.Timestamp.class),
    ENUM(Enum.class),
    MODEL(Model.class),
    CUSTOM_TYPE(Object.class);

    private final Class<?> javaFieldType;

    JavaFieldType(@NonNull Class cls) {
        this.javaFieldType = cls;
    }

    public static JavaFieldType from(@Nullable Class<?> cls) {
        for (JavaFieldType javaFieldType : values()) {
            if (javaFieldType.javaFieldType.equals(cls)) {
                return javaFieldType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + cls + " value.");
    }
}
