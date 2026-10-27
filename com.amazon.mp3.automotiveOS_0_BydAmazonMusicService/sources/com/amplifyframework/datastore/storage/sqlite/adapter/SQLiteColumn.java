package com.amplifyframework.datastore.storage.sqlite.adapter;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.PrimaryKey;
import com.amplifyframework.datastore.storage.sqlite.SQLiteDataType;
import com.amplifyframework.util.Wrap;

/* JADX INFO: loaded from: classes5.dex */
public final class SQLiteColumn {
    private static final String CUSTOM_ALIAS_DELIMITER = "_";
    private static final String SQLITE_NAME_DELIMITER = ".";
    private final SQLiteDataType dataType;
    private final String fieldName;
    private final boolean isNonNull;
    private final String name;
    private final String ownedField;
    private final String ownedType;
    private final String tableName;

    private SQLiteColumn(Builder builder) {
        this.name = builder.name;
        this.fieldName = builder.fieldName;
        this.tableName = builder.tableName;
        this.ownedType = builder.ownedType;
        this.isNonNull = builder.isNonNull;
        this.dataType = builder.dataType;
        this.ownedField = builder.ownedField;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return this.name;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getAliasedName() {
        return this.tableName + "_" + this.name;
    }

    public String getQuotedColumnName() {
        return Wrap.inBackticks(this.tableName) + SQLITE_NAME_DELIMITER + Wrap.inBackticks(this.name);
    }

    public String getOwnedField() {
        return this.ownedField;
    }

    public String getOwnedType() {
        return this.ownedType;
    }

    public boolean isPrimaryKey() {
        return PrimaryKey.matches(this.name);
    }

    public boolean isForeignKey() {
        return this.ownedType != null;
    }

    public boolean isNonNull() {
        return this.isNonNull;
    }

    public String getColumnType() {
        return this.dataType.getSqliteDataType();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SQLiteColumn.class != obj.getClass()) {
            return false;
        }
        SQLiteColumn sQLiteColumn = (SQLiteColumn) obj;
        return this.isNonNull == sQLiteColumn.isNonNull && ObjectsCompat.equals(this.name, sQLiteColumn.name) && ObjectsCompat.equals(this.fieldName, sQLiteColumn.fieldName) && ObjectsCompat.equals(this.tableName, sQLiteColumn.tableName) && ObjectsCompat.equals(this.ownedType, sQLiteColumn.ownedType) && this.dataType == sQLiteColumn.dataType;
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.name, this.fieldName, this.tableName, this.ownedType, Boolean.valueOf(this.isNonNull), this.dataType);
    }

    public String toString() {
        return "SQLiteColumn{name='" + this.name + "', fieldName='" + this.fieldName + "', tableName='" + this.tableName + "', ownedType='" + this.ownedType + "', isNonNull=" + this.isNonNull + ", dataType=" + this.dataType + '}';
    }

    public static final class Builder {
        private SQLiteDataType dataType;
        private String fieldName;
        private boolean isNonNull = false;
        private String name;
        private String ownedField;
        private String ownedType;
        private String tableName;

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder fieldName(String str) {
            this.fieldName = str;
            return this;
        }

        public Builder tableName(String str) {
            this.tableName = str;
            return this;
        }

        public Builder ownerOf(String str) {
            this.ownedType = str;
            return this;
        }

        public Builder ownerField(String str) {
            this.ownedField = str;
            return this;
        }

        public Builder isNonNull(boolean z) {
            this.isNonNull = z;
            return this;
        }

        public Builder dataType(SQLiteDataType sQLiteDataType) {
            this.dataType = sQLiteDataType;
            return this;
        }

        public SQLiteColumn build() {
            return new SQLiteColumn(this);
        }
    }
}
