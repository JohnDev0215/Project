package com.amplifyframework.datastore.storage.sqlite.adapter;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.PrimaryKey;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.sqlite.SQLiteDataType;
import com.amplifyframework.datastore.storage.sqlite.TypeConverter;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteColumn;
import com.amplifyframework.util.Immutable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes5.dex */
public final class SQLiteTable {
    public static final String PRIMARY_KEY_FIELD_NAME = "@@primaryKey";
    private final Map<String, SQLiteColumn> columns;
    private final String name;
    private final List<String> primaryKeyColumns;
    private final List<SQLiteColumn> sortedColumns;
    private final Model.Type type;

    private SQLiteTable(String str, Map<String, SQLiteColumn> map, List<String> list, Model.Type type) {
        this.name = str;
        this.columns = map;
        this.type = type;
        this.sortedColumns = sortedColumns();
        this.primaryKeyColumns = list;
    }

    @NonNull
    public static SQLiteTable fromSchema(@NonNull ModelSchema modelSchema) {
        String name;
        Objects.requireNonNull(modelSchema);
        Map<String, ModelAssociation> associations = modelSchema.getAssociations();
        TreeMap treeMap = new TreeMap();
        if (modelSchema.getModelType() == Model.Type.USER && modelSchema.getPrimaryIndexFields().size() > 1) {
            treeMap.put(PRIMARY_KEY_FIELD_NAME, SQLiteColumn.builder().name(PRIMARY_KEY_FIELD_NAME).fieldName(PRIMARY_KEY_FIELD_NAME).tableName(modelSchema.getName()).ownerOf(null).isNonNull(true).dataType(SQLiteDataType.TEXT).build());
        }
        for (ModelField modelField : modelSchema.getFields().values()) {
            ModelAssociation modelAssociation = associations.get(modelField.getName());
            boolean z = modelAssociation != null;
            if (!z || modelAssociation.isOwner()) {
                SQLiteColumn.Builder builder = SQLiteColumn.builder();
                if (z) {
                    name = getForeignKeyColumnName(modelSchema.getVersion(), modelField.getName(), modelAssociation);
                } else {
                    name = modelField.getName();
                }
                treeMap.put(modelField.getName(), builder.name(name).fieldName(modelField.getName()).tableName(modelSchema.getName()).ownerOf(z ? modelAssociation.getAssociatedType() : null).ownerField(z ? modelField.getName() : null).isNonNull(modelField.isRequired()).dataType(sqlTypeFromModelField(modelField)).build());
            }
        }
        return builder().name(modelSchema.getName()).type(modelSchema.getModelType()).columns(treeMap).primaryKeyColumns(modelSchema.getPrimaryIndexFields()).build();
    }

    private static SQLiteDataType sqlTypeFromModelField(ModelField modelField) {
        return TypeConverter.getSQLiteDataType(modelField);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public SQLiteColumn getPrimaryKey() {
        for (SQLiteColumn sQLiteColumn : this.sortedColumns) {
            if (this.type == Model.Type.USER) {
                if (this.primaryKeyColumns.size() > 1) {
                    if (sQLiteColumn.getName().equals(PRIMARY_KEY_FIELD_NAME)) {
                        return sQLiteColumn;
                    }
                } else if (this.primaryKeyColumns.size() == 1 && this.primaryKeyColumns.contains(sQLiteColumn)) {
                    return sQLiteColumn;
                }
            }
            if (this.primaryKeyColumns.size() <= 1 && sQLiteColumn.isPrimaryKey()) {
                return sQLiteColumn;
            }
        }
        return null;
    }

    @NonNull
    public Model.Type getType() {
        return this.type;
    }

    @NonNull
    public List<String> getPrimaryKeyColumns() {
        return this.primaryKeyColumns;
    }

    @NonNull
    public String getPrimaryKeyColumnName() {
        if (getPrimaryKey() == null) {
            return PrimaryKey.fieldName();
        }
        return getPrimaryKey().getQuotedColumnName();
    }

    @NonNull
    public static String getForeignKeyColumnName(int i, String str, ModelAssociation modelAssociation) {
        if (i == 0) {
            return modelAssociation.getTargetName();
        }
        if (modelAssociation.getTargetNames().length > 1) {
            return "@@" + str + "ForeignKey";
        }
        return modelAssociation.getTargetNames()[0];
    }

    @NonNull
    public List<SQLiteColumn> getForeignKeys() {
        LinkedList linkedList = new LinkedList();
        for (SQLiteColumn sQLiteColumn : this.sortedColumns) {
            if (sQLiteColumn.isForeignKey()) {
                linkedList.add(sQLiteColumn);
            }
        }
        return Immutable.of(linkedList);
    }

    @NonNull
    public SQLiteColumn getColumn(@NonNull String str) throws DataStoreException {
        Map<String, SQLiteColumn> map = this.columns;
        Objects.requireNonNull(str);
        SQLiteColumn sQLiteColumn = map.get(str);
        if (sQLiteColumn != null) {
            return sQLiteColumn;
        }
        throw new DataStoreException("Column with columnName " + str + " not found in " + this.name, AmplifyException.TODO_RECOVERY_SUGGESTION);
    }

    @NonNull
    public Map<String, SQLiteColumn> getColumns() {
        return Immutable.of(this.columns);
    }

    @NonNull
    public List<SQLiteColumn> getSortedColumns() {
        return Immutable.of(this.sortedColumns);
    }

    private List<SQLiteColumn> sortedColumns() {
        if (this.columns == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList(this.columns.values());
        Collections.sort(linkedList, new Comparator() { // from class: com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteTable$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SQLiteTable.lambda$sortedColumns$0((SQLiteColumn) obj, (SQLiteColumn) obj2);
            }
        });
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortedColumns$0(SQLiteColumn sQLiteColumn, SQLiteColumn sQLiteColumn2) {
        if (sQLiteColumn.isPrimaryKey()) {
            return -1;
        }
        if (sQLiteColumn2.isPrimaryKey()) {
            return 1;
        }
        if (sQLiteColumn.isForeignKey() && !sQLiteColumn2.isForeignKey()) {
            return 1;
        }
        if (sQLiteColumn.isForeignKey() || !sQLiteColumn2.isForeignKey()) {
            return sQLiteColumn.getName().compareTo(sQLiteColumn2.getName());
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SQLiteTable.class != obj.getClass()) {
            return false;
        }
        SQLiteTable sQLiteTable = (SQLiteTable) obj;
        return ObjectsCompat.equals(this.name, sQLiteTable.name) && ObjectsCompat.equals(this.columns, sQLiteTable.columns) && ObjectsCompat.equals(this.sortedColumns, sQLiteTable.sortedColumns);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.name, this.columns, this.sortedColumns);
    }

    public String toString() {
        return "SQLiteTable{name='" + this.name + "', columns=" + this.columns + ", sortedColumns=" + this.sortedColumns + '}';
    }

    public static final class Builder {
        private final Map<String, SQLiteColumn> columns = new HashMap();
        private String name;
        private List<String> primaryKeyColumns;
        private Model.Type type;

        Builder() {
        }

        @NonNull
        public Builder name(@NonNull String str) {
            Objects.requireNonNull(str);
            this.name = str;
            return this;
        }

        @NonNull
        public Builder type(@NonNull Model.Type type) {
            Objects.requireNonNull(type);
            this.type = type;
            return this;
        }

        @NonNull
        public Builder primaryKeyColumns(@NonNull List<String> list) {
            Objects.requireNonNull(list);
            this.primaryKeyColumns = list;
            return this;
        }

        @NonNull
        public Builder columns(@NonNull Map<String, SQLiteColumn> map) {
            Objects.requireNonNull(map);
            this.columns.clear();
            this.columns.putAll(map);
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public SQLiteTable build() {
            return new SQLiteTable(this.name, Immutable.of(this.columns), this.primaryKeyColumns, this.type);
        }
    }
}
