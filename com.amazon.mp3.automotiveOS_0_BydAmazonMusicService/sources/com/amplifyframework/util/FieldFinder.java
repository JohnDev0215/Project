package com.amplifyframework.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.annotations.ModelField;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class FieldFinder {
    private FieldFinder() {
    }

    @NonNull
    public static List<Field> findModelFieldsIn(@NonNull Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(ModelField.class)) {
                    arrayList.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.amplifyframework.util.FieldFinder$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FieldFinder.lambda$findModelFieldsIn$0((Field) obj, (Field) obj2);
            }
        });
        return Immutable.of(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$findModelFieldsIn$0(Field field, Field field2) {
        return field.getName().compareTo(field2.getName());
    }

    @NonNull
    public static List<Field> findNonTransientFieldsIn(@NonNull Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isTransient(field.getModifiers())) {
                    arrayList.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        Collections.sort(arrayList, Comparator.comparing(new FieldFinder$$ExternalSyntheticLambda0()));
        return Immutable.of(arrayList);
    }

    @Nullable
    public static Object extractFieldValue(@NonNull Object obj, @NonNull String str) throws NoSuchFieldException {
        if (obj instanceof SerializedModel) {
            return ((SerializedModel) obj).getSerializedData().get(str);
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            throw e;
        } catch (Exception unused) {
            return null;
        }
    }
}
