package com.amplifyframework.datastore.model;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.logging.Logger;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelHelper {
    private static final Logger LOGGER = Amplify.Logging.forNamespace("amplify:aws-datastore");

    private ModelHelper() {
    }

    public static <M extends Model> Object getValue(M m, ModelField modelField) throws DataStoreException {
        Class<?> cls = m.getClass();
        String name = modelField.getName();
        String str = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        try {
            return cls.getMethod(str, new Class[0]).invoke(m, new Object[0]);
        } catch (Exception unused) {
            LOGGER.verbose(String.format("Could not find %s() on %s. Fallback to direct field access.", str, cls.getName()));
            try {
                Field declaredField = cls.getDeclaredField(name);
                declaredField.setAccessible(true);
                return declaredField.get(m);
            } catch (Exception e) {
                throw new DataStoreException("Error when reading the property " + name + " from class " + cls.getName(), e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
            }
        }
    }
}
