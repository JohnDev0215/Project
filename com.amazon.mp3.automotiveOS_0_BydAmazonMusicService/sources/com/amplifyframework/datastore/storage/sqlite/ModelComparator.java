package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.Nullable;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.QuerySortBy;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.logging.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelComparator<T extends Model> implements Comparator<T> {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private static final int NUM_LETTERS_FOR_GET = 3;
    private final Class<T> itemClass;
    private final Consumer<DataStoreException> onObservationError;
    private final QuerySortBy sortBy;

    public ModelComparator(QuerySortBy querySortBy, Class<T> cls, Consumer<DataStoreException> consumer) {
        this.sortBy = querySortBy;
        this.itemClass = cls;
        this.onObservationError = consumer;
    }

    private Object getValue(Method method, Model model) {
        try {
            return method.invoke(model, new Object[0]);
        } catch (IllegalAccessException e) {
            String str = "Could not invoke method during sorting because of access level" + method.getName();
            LOG.warn(str);
            Consumer<DataStoreException> consumer = this.onObservationError;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String message = e.getMessage();
            Objects.requireNonNull(message);
            sb.append(message);
            consumer.accept(new DataStoreException("ObserveQuery", sb.toString()));
            return null;
        } catch (InvocationTargetException e2) {
            String str2 = "Could not invoke method during sorting " + method.getName();
            LOG.warn(str2);
            Consumer<DataStoreException> consumer2 = this.onObservationError;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            String message2 = e2.getMessage();
            Objects.requireNonNull(message2);
            sb2.append(message2);
            consumer2.accept(new DataStoreException("ObserveQuery", sb2.toString()));
            return null;
        }
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        Method method;
        QuerySortBy querySortBy = this.sortBy;
        if (querySortBy != null && querySortBy.getModelName() != null && (method = getMethod()) != null) {
            Object value = getValue(method, t);
            Object value2 = getValue(method, t2);
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            if (method.getReturnType() == String.class) {
                return ((String) value).compareTo((String) value2);
            }
            if (method.getReturnType() == Integer.TYPE) {
                return Integer.compare(((Integer) value).intValue(), ((Integer) value2).intValue());
            }
            if (method.getReturnType() == Integer.class) {
                return Integer.compare(((Integer) value).intValue(), ((Integer) value2).intValue());
            }
            if (method.getReturnType() == Boolean.TYPE) {
                return Boolean.compare(((Boolean) value).booleanValue(), ((Boolean) value2).booleanValue());
            }
            if (method.getReturnType() == Boolean.class) {
                return Boolean.compare(((Boolean) value).booleanValue(), ((Boolean) value2).booleanValue());
            }
            if (method.getReturnType() == Long.TYPE) {
                return Long.compare(((Long) value).longValue(), ((Long) value2).longValue());
            }
            if (method.getReturnType() == Double.TYPE) {
                return Double.compare(((Double) value).doubleValue(), ((Double) value2).doubleValue());
            }
            if (method.getReturnType() == Double.class) {
                return Double.compare(((Double) value).doubleValue(), ((Double) value2).doubleValue());
            }
            if (method.getReturnType() == Date.class) {
                return ((Date) value).compareTo((Date) value2);
            }
            if (method.getReturnType() == Float.TYPE) {
                return Float.compare(((Float) value).floatValue(), ((Float) value2).floatValue());
            }
            if (method.getReturnType() == Float.class) {
                return Float.compare(((Float) value).floatValue(), ((Float) value2).floatValue());
            }
            if (method.getReturnType() == Character.TYPE) {
                return Character.compare(((Character) value).charValue(), ((Character) value2).charValue());
            }
            if (method.getReturnType() == Character.class) {
                return Character.compare(((Character) value).charValue(), ((Character) value2).charValue());
            }
            if (method.getReturnType() == Byte.TYPE) {
                return Byte.compare(((Byte) value).byteValue(), ((Byte) value2).byteValue());
            }
            if (method.getReturnType() == Byte.class) {
                return Byte.compare(((Byte) value).byteValue(), ((Byte) value2).byteValue());
            }
            if (method.getReturnType() == Temporal.DateTime.class) {
                return ((Temporal.DateTime) value).compareTo((Temporal.DateTime) value2);
            }
            if (method.getReturnType() == Temporal.Date.class) {
                return ((Temporal.DateTime) value).compareTo((Temporal.DateTime) value2);
            }
        }
        return -1;
    }

    private Method getMethod() {
        try {
            Field declaredField = this.itemClass.getDeclaredField(this.sortBy.getField());
            if (declaredField.getName().equals(this.sortBy.getField())) {
                return getMethod(this.itemClass, declaredField);
            }
            return null;
        } catch (NoSuchFieldException unused) {
            LOG.warn("Could not find the method " + this.sortBy.getField());
            return null;
        }
    }

    @Nullable
    private Method getMethod(Class<?> cls, Field field) {
        for (Method method : cls.getMethods()) {
            if (method.getName().startsWith("get") && method.getName().length() == field.getName().length() + 3) {
                String name = method.getName();
                Locale locale = Locale.ROOT;
                if (name.toLowerCase(locale).endsWith(field.getName().toLowerCase(locale))) {
                    return method;
                }
            }
        }
        return null;
    }
}
