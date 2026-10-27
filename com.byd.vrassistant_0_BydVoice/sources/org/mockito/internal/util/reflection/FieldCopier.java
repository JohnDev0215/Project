package org.mockito.internal.util.reflection;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public class FieldCopier {
    public <T> void copyValue(T t, T t2, Field field) throws IllegalAccessException {
        field.set(t2, field.get(t));
    }
}
