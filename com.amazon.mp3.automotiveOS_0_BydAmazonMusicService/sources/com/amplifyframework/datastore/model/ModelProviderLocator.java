package com.amplifyframework.datastore.model;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.datastore.DataStoreException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelProviderLocator {
    private static final String DEFAULT_MODEL_PROVIDER_CLASS_NAME = "com.amplifyframework.datastore.generated.model.AmplifyModelProvider";
    private static final String GET_INSTANCE_ACCESSOR_METHOD_NAME = "getInstance";

    private ModelProviderLocator() {
    }

    public static ModelProvider locate() throws DataStoreException {
        return locate(DEFAULT_MODEL_PROVIDER_CLASS_NAME);
    }

    static ModelProvider locate(@NonNull String str) throws DataStoreException {
        Objects.requireNonNull(str);
        try {
            Class<?> cls = Class.forName(str);
            if (!ModelProvider.class.isAssignableFrom(cls)) {
                throw new DataStoreException("Located class as " + cls.getName() + ", but it does not implement " + ModelProvider.class.getName() + ".", "Validate that " + cls.getName() + " has not been modified since the time it was code-generated.");
            }
            try {
                try {
                    return (ModelProvider) cls.getDeclaredMethod(GET_INSTANCE_ACCESSOR_METHOD_NAME, new Class[0]).invoke(null, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new DataStoreException("Tried to call " + cls.getName() + GET_INSTANCE_ACCESSOR_METHOD_NAME + ", but this method did not have public access.", e, "Validate that " + cls.getName() + " has not been modified since the time it was code-generated.");
                } catch (InvocationTargetException e2) {
                    throw new DataStoreException("An exception was thrown from " + cls.getName() + GET_INSTANCE_ACCESSOR_METHOD_NAME + " while invoking via reflection.", e2, "This is not expected to occur. Contact AWS.");
                }
            } catch (NoSuchMethodException e3) {
                throw new DataStoreException("Found a code-generated model provider = " + cls.getName() + ", however it had no static method named getInstance()!", e3, "Validate that " + cls.getName() + " has not been modified since the time it was code-generated.");
            }
        } catch (ClassNotFoundException e4) {
            throw new DataStoreException("Failed to find code-generated model provider.", e4, "Validate that " + str + " is built into your project.");
        }
    }
}
