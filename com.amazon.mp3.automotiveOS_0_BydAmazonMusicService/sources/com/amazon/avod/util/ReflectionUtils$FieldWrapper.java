package com.amazon.avod.util;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectionUtils$FieldWrapper<T> {
    private Optional<Field> field;
    private final Object instance;
    private final boolean mReflectionMissExpected;

    public ReflectionUtils$FieldWrapper(Class<?> cls, Object obj, String str, boolean z) {
        this.mReflectionMissExpected = z;
        this.instance = obj;
        try {
            Optional<Field> optionalOf = Optional.of(cls.getDeclaredField(str));
            this.field = optionalOf;
            optionalOf.get().setAccessible(true);
        } catch (NoSuchFieldException e) {
            Preconditions.checkState(this.mReflectionMissExpected, "Required field could not be found via reflection: " + str);
            DLog.exceptionf(e, "Field access won't be available for %s on %s", str, cls.getSimpleName());
            this.field = Optional.absent();
        }
    }

    public T get() {
        if (!this.field.isPresent()) {
            Preconditions.checkState(this.mReflectionMissExpected, "Required field could not be accessed for getter");
            return null;
        }
        try {
            return (T) this.field.get().get(this.instance);
        } catch (IllegalAccessException e) {
            if (this.mReflectionMissExpected) {
                return null;
            }
            throw new RuntimeException("Required field could not be accessed: " + this.field.get().getName(), e);
        }
    }
}
