package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes6.dex */
public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field) {
        C$Gson$Preconditions.checkNotNull(field);
        this.field = field;
    }

    public Class<?> getDeclaringClass() {
        return this.field.getDeclaringClass();
    }
}
