package com.amplifyframework.core.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public interface Model {

    public enum Type {
        USER,
        SYSTEM
    }

    @NonNull
    default Serializable resolveIdentifier() {
        try {
            Object objInvoke = getClass().getMethod("getId", new Class[0]).invoke(this, new Object[0]);
            Objects.requireNonNull(objInvoke);
            return (Serializable) objInvoke;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Primary key field Id not found.", e);
        } catch (NoSuchMethodException e2) {
            throw new IllegalStateException("Primary key field Id not found.", e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException("Primary key field Id not found.", e3);
        }
    }

    @NonNull
    default String getModelName() {
        return getClass().getSimpleName();
    }

    @NonNull
    default String getPrimaryKeyString() {
        try {
            if (resolveIdentifier() instanceof ModelIdentifier) {
                return ((ModelIdentifier) resolveIdentifier()).getIdentifier();
            }
            return (String) resolveIdentifier();
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Primary Key, It should either be of type String or composite Primary Key." + e);
        }
    }
}
