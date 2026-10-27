package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.types.JavaFieldType;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class TypeConverter {
    private static final Map<JavaFieldType, SQLiteDataType> JAVA_TO_SQL;

    static {
        HashMap map = new HashMap();
        JAVA_TO_SQL = map;
        JavaFieldType javaFieldType = JavaFieldType.BOOLEAN;
        SQLiteDataType sQLiteDataType = SQLiteDataType.INTEGER;
        map.put(javaFieldType, sQLiteDataType);
        map.put(JavaFieldType.LONG, sQLiteDataType);
        map.put(JavaFieldType.INTEGER, sQLiteDataType);
        JavaFieldType javaFieldType2 = JavaFieldType.FLOAT;
        SQLiteDataType sQLiteDataType2 = SQLiteDataType.REAL;
        map.put(javaFieldType2, sQLiteDataType2);
        map.put(JavaFieldType.DOUBLE, sQLiteDataType2);
        JavaFieldType javaFieldType3 = JavaFieldType.STRING;
        SQLiteDataType sQLiteDataType3 = SQLiteDataType.TEXT;
        map.put(javaFieldType3, sQLiteDataType3);
        map.put(JavaFieldType.ENUM, sQLiteDataType3);
        map.put(JavaFieldType.DATE, sQLiteDataType3);
        map.put(JavaFieldType.DATE_TIME, sQLiteDataType3);
        map.put(JavaFieldType.TIME, sQLiteDataType3);
        map.put(JavaFieldType.TIMESTAMP, sQLiteDataType);
        map.put(JavaFieldType.MODEL, sQLiteDataType3);
        map.put(JavaFieldType.CUSTOM_TYPE, sQLiteDataType3);
    }

    private TypeConverter() {
    }

    static JavaFieldType getJavaFieldType(@NonNull ModelField modelField) {
        if (modelField.isModel()) {
            return JavaFieldType.MODEL;
        }
        if (modelField.isEnum()) {
            return JavaFieldType.ENUM;
        }
        try {
            return JavaFieldType.from(modelField.getJavaClassForValue());
        } catch (IllegalArgumentException unused) {
            return JavaFieldType.CUSTOM_TYPE;
        }
    }

    public static JavaFieldType getJavaFieldTypeFromValue(@NonNull Object obj) {
        if (obj instanceof Model) {
            return JavaFieldType.MODEL;
        }
        if (obj instanceof Enum) {
            return JavaFieldType.ENUM;
        }
        try {
            return JavaFieldType.from(obj.getClass());
        } catch (IllegalArgumentException unused) {
            return JavaFieldType.CUSTOM_TYPE;
        }
    }

    public static SQLiteDataType getSQLiteDataType(@NonNull ModelField modelField) {
        return JAVA_TO_SQL.get(getJavaFieldType(modelField));
    }
}
