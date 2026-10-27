package com.amplifyframework.datastore.storage.sqlite;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.types.JavaFieldType;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.model.ModelFieldTypeConverter;
import com.amplifyframework.datastore.model.ModelHelper;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteColumn;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteTable;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.UserAgent;
import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class SQLiteModelFieldTypeConverter implements ModelFieldTypeConverter<Cursor, Model> {
    private static final Logger LOGGER = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final Map<String, SQLiteColumn> columns;
    private final Map<String, Integer> cursorInnerModelCounts;
    private final Gson gson;
    private final boolean isInnerModel;
    private final ModelSchema parentSchema;
    private final SchemaRegistry schemaRegistry;

    SQLiteModelFieldTypeConverter(@NonNull ModelSchema modelSchema, @NonNull SchemaRegistry schemaRegistry, @NonNull Gson gson) {
        this(modelSchema, schemaRegistry, gson, new HashMap());
    }

    private SQLiteModelFieldTypeConverter(@NonNull ModelSchema modelSchema, @NonNull SchemaRegistry schemaRegistry, @NonNull Gson gson, @NonNull Map<String, Integer> map) {
        Objects.requireNonNull(modelSchema);
        this.parentSchema = modelSchema;
        Objects.requireNonNull(schemaRegistry);
        this.schemaRegistry = schemaRegistry;
        Objects.requireNonNull(gson);
        this.gson = gson;
        this.columns = SQLiteTable.fromSchema(modelSchema).getColumns();
        this.cursorInnerModelCounts = map;
        boolean z = !map.isEmpty();
        this.isInnerModel = z;
        if (z) {
            return;
        }
        map.put(modelSchema.getName(), 1);
    }

    public static Object convertRawValueToTarget(@Nullable Object obj, @NonNull JavaFieldType javaFieldType, @NonNull Gson gson) {
        OffsetDateTime offsetDateTime;
        String str;
        LocalTime localTimeFrom;
        ZoneOffset zoneOffsetFrom;
        if (obj == null) {
            return null;
        }
        Objects.requireNonNull(javaFieldType);
        Objects.requireNonNull(gson);
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[javaFieldType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            case 6:
                break;
            case 7:
                if (!(obj instanceof Map)) {
                }
                break;
            case 8:
                if (!(obj instanceof String)) {
                    break;
                }
                break;
            case 9:
                break;
            case 10:
                if (!(obj instanceof String)) {
                    break;
                }
                break;
            case 11:
                if (!UserAgent.isFlutter() || !(obj instanceof String)) {
                    if (obj instanceof String) {
                        offsetDateTime = OffsetDateTime.parse((String) obj);
                    } else {
                        offsetDateTime = OffsetDateTime.parse(((Temporal.DateTime) obj).format());
                    }
                }
                break;
            case 12:
                if (!UserAgent.isFlutter() || !(obj instanceof String)) {
                    if (obj instanceof String) {
                        str = (String) obj;
                    } else {
                        str = ((Temporal.Time) obj).format();
                    }
                    try {
                        OffsetTime offsetTime = OffsetTime.parse(str, DateTimeFormatter.ISO_OFFSET_TIME);
                        localTimeFrom = LocalTime.from(offsetTime);
                        zoneOffsetFrom = ZoneOffset.from((TemporalAccessor) offsetTime);
                    } catch (Exception unused) {
                        localTimeFrom = LocalTime.parse(str, DateTimeFormatter.ISO_LOCAL_TIME);
                        zoneOffsetFrom = ZoneOffset.UTC;
                    }
                }
                break;
            case 13:
                if (obj instanceof Integer) {
                    break;
                } else if (!(obj instanceof Long)) {
                    break;
                }
                break;
            default:
                LOGGER.warn(String.format("Field of type %s is not supported. Fallback to null.", javaFieldType));
                break;
        }
        return null;
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.storage.sqlite.SQLiteModelFieldTypeConverter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType;

        static {
            int[] iArr = new int[JavaFieldType.values().length];
            $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType = iArr;
            try {
                iArr[JavaFieldType.INTEGER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.STRING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.BOOLEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.MODEL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.CUSTOM_TYPE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.DATE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.DATE_TIME.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.TIME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[JavaFieldType.TIMESTAMP.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    Map<String, Object> buildMapForModel(@NonNull Cursor cursor) throws DataStoreException {
        HashMap map = new HashMap();
        for (Map.Entry<String, ModelField> entry : this.parentSchema.getFields().entrySet()) {
            map.put(entry.getKey(), convertValueFromSource(cursor, entry.getValue()));
        }
        if (!this.isInnerModel) {
            this.cursorInnerModelCounts.clear();
            this.cursorInnerModelCounts.put(this.parentSchema.getName(), 1);
        }
        return map;
    }

    @Override // com.amplifyframework.datastore.model.ModelFieldTypeConverter
    public Object convertValueFromSource(@NonNull Cursor cursor, @NonNull ModelField modelField) throws DataStoreException {
        int iIntValue;
        JavaFieldType javaFieldType = TypeConverter.getJavaFieldType(modelField);
        try {
            SQLiteColumn sQLiteColumn = this.columns.get(modelField.getName());
            if (sQLiteColumn == null) {
                LOGGER.verbose(String.format("Column with name %s does not exist", modelField.getName()));
                return null;
            }
            String aliasedName = sQLiteColumn.getAliasedName();
            if (javaFieldType == JavaFieldType.MODEL) {
                String targetType = modelField.getTargetType();
                if (this.cursorInnerModelCounts.containsKey(targetType)) {
                    Integer num = this.cursorInnerModelCounts.get(targetType);
                    iIntValue = (num == null ? 0 : num.intValue()) + 1;
                } else {
                    iIntValue = 1;
                }
                this.cursorInnerModelCounts.put(targetType, Integer.valueOf(iIntValue));
            }
            if (this.isInnerModel && this.cursorInnerModelCounts.containsKey(this.parentSchema.getName())) {
                Integer num2 = this.cursorInnerModelCounts.get(this.parentSchema.getName());
                if (!Objects.equals(num2, 1)) {
                    aliasedName = aliasedName + num2;
                }
            }
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(aliasedName);
            if (cursor.isNull(columnIndexOrThrow)) {
                return null;
            }
            String string = cursor.getString(columnIndexOrThrow);
            Logger logger = LOGGER;
            logger.verbose(String.format("Attempt to convert value \"%s\" from field %s of type %s in model %s", string, modelField.getName(), modelField.getTargetType(), this.parentSchema.getName()));
            switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$types$JavaFieldType[javaFieldType.ordinal()]) {
                case 1:
                    return Integer.valueOf(cursor.getInt(columnIndexOrThrow));
                case 2:
                    return Long.valueOf(cursor.getLong(columnIndexOrThrow));
                case 3:
                    return Float.valueOf(cursor.getFloat(columnIndexOrThrow));
                case 4:
                    return Double.valueOf(cursor.getDouble(columnIndexOrThrow));
                case 5:
                    return cursor.getString(columnIndexOrThrow);
                case 6:
                    return Boolean.valueOf(cursor.getInt(columnIndexOrThrow) != 0);
                case 7:
                    return convertModelAssociationToTarget(cursor, modelField);
                case 8:
                    return convertEnumValueToTarget(string, modelField);
                case 9:
                    return convertCustomTypeToTarget(cursor, modelField, columnIndexOrThrow);
                case 10:
                    return new Temporal.Date(string);
                case 11:
                    return new Temporal.DateTime(string);
                case 12:
                    return new Temporal.Time(string);
                case 13:
                    return new Temporal.Timestamp(cursor.getLong(columnIndexOrThrow), TimeUnit.SECONDS);
                default:
                    logger.warn(String.format("Field of type %s is not supported. Fallback to null.", javaFieldType));
                    return null;
            }
        } catch (Exception e) {
            throw new DataStoreException(String.format("Error converting field \"%s\" from model \"%s\"", modelField.getName(), this.parentSchema.getName()), e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private Object convertModelAssociationToTarget(@NonNull Cursor cursor, @NonNull ModelField modelField) throws DataStoreException {
        return new SQLiteModelFieldTypeConverter(this.schemaRegistry.getModelSchemaForModelClass(modelField.getTargetType()), this.schemaRegistry, this.gson, this.cursorInnerModelCounts).buildMapForModel(cursor);
    }

    private Object convertCustomTypeToTarget(Cursor cursor, ModelField modelField, int i) throws IOException {
        String string = cursor.getString(i);
        Gson gson = this.gson;
        Class<?> javaClassForValue = modelField.getJavaClassForValue();
        Objects.requireNonNull(javaClassForValue);
        return gson.getAdapter(javaClassForValue).fromJson(string);
    }

    private <E extends Enum<E>> E convertEnumValueToTarget(@NonNull String str, @NonNull ModelField modelField) {
        Class<?> javaClassForValue = modelField.getJavaClassForValue();
        Objects.requireNonNull(javaClassForValue);
        return (E) Enum.valueOf(javaClassForValue.asSubclass(Enum.class), str);
    }

    @Override // com.amplifyframework.datastore.model.ModelFieldTypeConverter
    public Object convertValueFromTarget(Model model, ModelField modelField) throws DataStoreException {
        Object value;
        if (model.getClass() == SerializedModel.class) {
            value = ((SerializedModel) model).getValue(modelField);
        } else {
            value = ModelHelper.getValue(model, modelField);
        }
        if (value == null) {
            return null;
        }
        return convertRawValueToTarget(value, TypeConverter.getJavaFieldType(modelField), this.gson);
    }
}
