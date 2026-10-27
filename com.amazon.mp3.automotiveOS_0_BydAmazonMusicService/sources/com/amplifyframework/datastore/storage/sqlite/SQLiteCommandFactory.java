package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelIndex;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.PrimaryKey;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.QueryPaginationInput;
import com.amplifyframework.core.model.query.QuerySortBy;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLPredicate;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteColumn;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteTable;
import com.amplifyframework.util.Empty;
import com.amplifyframework.util.Immutable;
import com.amplifyframework.util.UserAgent;
import com.amplifyframework.util.Wrap;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
final class SQLiteCommandFactory implements SQLCommandFactory {
    public static final String UNDEFINED = "undefined";
    private final Gson gson;
    private final SchemaRegistry schemaRegistry;

    SQLiteCommandFactory(@NonNull SchemaRegistry schemaRegistry, @NonNull Gson gson) {
        Objects.requireNonNull(schemaRegistry);
        this.schemaRegistry = schemaRegistry;
        Objects.requireNonNull(gson);
        this.gson = gson;
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public SqlCommand createTableFor(@NonNull ModelSchema modelSchema) {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS");
        SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(sQLiteTableFromSchema.getName()));
        sb.append(sqlKeyword);
        if (Empty.check(sQLiteTableFromSchema.getColumns())) {
            return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString());
        }
        sb.append("(");
        sb.append((CharSequence) parseColumns(sQLiteTableFromSchema));
        sb.append(",");
        sb.append(sqlKeyword);
        sb.append(createPrimaryKey(modelSchema).toString());
        if (!sQLiteTableFromSchema.getForeignKeys().isEmpty()) {
            sb.append(",");
            sb.append(sqlKeyword);
            sb.append((CharSequence) parseForeignKeys(sQLiteTableFromSchema));
        }
        sb.append(");");
        return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString());
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public Set<SqlCommand> createIndexesFor(@NonNull ModelSchema modelSchema) {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        HashSet hashSet = new HashSet();
        for (ModelIndex modelIndex : modelSchema.getIndexes().values()) {
            if (shouldCreateIndex(modelIndex, modelSchema.getAssociations())) {
                hashSet.add(createIndexCommand(sQLiteTableFromSchema.getName(), modelIndex.getIndexName(), modelIndex.getIndexFieldNames()));
            }
        }
        return Immutable.of(hashSet);
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public Set<SqlCommand> createIndexesForForeignKeys(@NonNull ModelSchema modelSchema) {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        HashSet hashSet = new HashSet();
        Iterator<SQLiteColumn> it = sQLiteTableFromSchema.getForeignKeys().iterator();
        while (it.hasNext()) {
            String name = it.next().getName();
            hashSet.add(createIndexCommand(sQLiteTableFromSchema.getName(), sQLiteTableFromSchema.getName() + name, Collections.singletonList(name)));
        }
        return Immutable.of(hashSet);
    }

    @NonNull
    private SqlCommand createIndexCommand(String str, String str2, List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE INDEX IF NOT EXISTS");
        SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(getIndexName(str2, list)));
        sb.append(sqlKeyword);
        sb.append(SqlKeyword.ON);
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(str));
        sb.append(sqlKeyword);
        sb.append("(");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(Wrap.inBackticks(it.next()));
            if (it.hasNext()) {
                sb.append(",");
                sb.append(SqlKeyword.DELIMITER);
            }
        }
        sb.append(");");
        return new SqlCommand(str, sb.toString());
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public SqlCommand queryFor(@NonNull ModelSchema modelSchema, @NonNull QueryOptions queryOptions) throws DataStoreException {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        String name = sQLiteTableFromSchema.getName();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Map<String, List<SQLiteColumn>> map = new HashMap<>();
        map.put(sQLiteTableFromSchema.getName(), sQLiteTableFromSchema.getSortedColumns());
        Map<String, Integer> map2 = new HashMap<>();
        boolean z = true;
        map2.put(name, 1);
        recursivelyBuildJoins(sQLiteTableFromSchema, map, sb3, map2, name);
        for (String str : map.keySet()) {
            if (z) {
                z = false;
            } else {
                sb2.append(",");
                sb2.append(SqlKeyword.DELIMITER);
            }
            List<SQLiteColumn> list = map.get(str);
            Objects.requireNonNull(list);
            Iterator<SQLiteColumn> it = list.iterator();
            while (it.hasNext()) {
                SQLiteColumn next = it.next();
                sb2.append(next.getQuotedColumnName().replace(next.getTableName(), str));
                String str2 = next.getAliasedName() + str.substring(next.getTableName().length());
                SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
                sb2.append(sqlKeyword);
                sb2.append(SqlKeyword.AS);
                sb2.append(sqlKeyword);
                sb2.append(Wrap.inBackticks(str2));
                if (it.hasNext()) {
                    sb2.append(",");
                    sb2.append(sqlKeyword);
                }
            }
        }
        sb.append(SqlKeyword.SELECT);
        SqlKeyword sqlKeyword2 = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword2);
        sb.append(sb2.toString());
        sb.append(sqlKeyword2);
        sb.append(SqlKeyword.FROM);
        sb.append(sqlKeyword2);
        sb.append(Wrap.inBackticks(name));
        if (!sb3.toString().isEmpty()) {
            sb.append(sqlKeyword2);
            sb.append(sb3.toString());
        }
        QueryPredicate queryPredicate = queryOptions.getQueryPredicate();
        if (!QueryPredicates.all().equals(queryPredicate)) {
            SQLPredicate sQLPredicate = new SQLPredicate(queryPredicate);
            arrayList.addAll(sQLPredicate.getBindings());
            String string = sQLPredicate.toString();
            if (queryPredicate instanceof QueryPredicateOperation) {
                QueryPredicateOperation<?> queryPredicateOperation = (QueryPredicateOperation) queryPredicate;
                String strField = queryPredicateOperation.field();
                string = getFlutterString(string, queryPredicateOperation);
                if (strField.equals(PrimaryKey.fieldName()) && queryPredicateOperation.modelName() == null && queryPredicateOperation.operator().type() == QueryOperator.Type.EQUAL) {
                    string = string.replace(strField, name + "." + strField);
                }
            }
            sb.append(sqlKeyword2);
            sb.append(SqlKeyword.WHERE);
            sb.append(sqlKeyword2);
            sb.append(string);
        }
        List<QuerySortBy> sortBy = queryOptions.getSortBy();
        if (sortBy != null) {
            sb.append(sqlKeyword2);
            sb.append(SqlKeyword.ORDER_BY);
            sb.append(sqlKeyword2);
            Iterator<QuerySortBy> it2 = sortBy.iterator();
            while (it2.hasNext()) {
                QuerySortBy next2 = it2.next();
                String strInBackticks = Wrap.inBackticks(next2.getModelName());
                String strInBackticks2 = Wrap.inBackticks(next2.getField());
                if (strInBackticks == null) {
                    strInBackticks = Wrap.inBackticks(name);
                }
                sb.append(strInBackticks + "." + strInBackticks2);
                SqlKeyword sqlKeyword3 = SqlKeyword.DELIMITER;
                sb.append(sqlKeyword3);
                sb.append(SqlKeyword.fromQuerySortOrder(next2.getSortOrder()));
                if (it2.hasNext()) {
                    sb.append(",");
                    sb.append(sqlKeyword3);
                }
            }
        }
        QueryPaginationInput paginationInput = queryOptions.getPaginationInput();
        if (paginationInput != null) {
            SqlKeyword sqlKeyword4 = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword4);
            sb.append(SqlKeyword.LIMIT);
            sb.append(sqlKeyword4);
            sb.append("?");
            sb.append(sqlKeyword4);
            sb.append(SqlKeyword.OFFSET);
            sb.append(sqlKeyword4);
            sb.append("?");
            arrayList.add(Integer.valueOf(paginationInput.getLimit()));
            arrayList.add(Integer.valueOf(paginationInput.getPage() * paginationInput.getLimit()));
        }
        sb.append(";");
        return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString(), arrayList);
    }

    private String getFlutterString(String str, QueryPredicateOperation<?> queryPredicateOperation) {
        String strField = queryPredicateOperation.field();
        return (UserAgent.isFlutter() && !Empty.check(queryPredicateOperation.field()) && strField.startsWith("@@") && queryPredicateOperation.modelName() == null) ? str.replace(strField, Wrap.inBackticks(strField)) : str;
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public SqlCommand existsFor(@NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) throws DataStoreException {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        String name = sQLiteTableFromSchema.getName();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        SqlKeyword sqlKeyword = SqlKeyword.SELECT;
        sb.append(sqlKeyword);
        SqlKeyword sqlKeyword2 = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword2);
        sb.append(SqlKeyword.EXISTS);
        sb.append("(");
        sb.append(sqlKeyword);
        sb.append(sqlKeyword2);
        sb.append("1");
        sb.append(sqlKeyword2);
        sb.append(SqlKeyword.FROM);
        sb.append(sqlKeyword2);
        sb.append(Wrap.inBackticks(name));
        if (!QueryPredicates.all().equals(queryPredicate)) {
            SQLPredicate sQLPredicate = new SQLPredicate(queryPredicate);
            arrayList.addAll(sQLPredicate.getBindings());
            sb.append(sqlKeyword2);
            sb.append(SqlKeyword.WHERE);
            sb.append(sqlKeyword2);
            sb.append(sQLPredicate);
        }
        sb.append(");");
        return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString(), arrayList);
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public <T extends Model> SqlCommand insertFor(@NonNull ModelSchema modelSchema, @NonNull T t) throws DataStoreException {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO");
        SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(sQLiteTableFromSchema.getName()));
        sb.append(sqlKeyword);
        sb.append("(");
        List<SQLiteColumn> sortedColumns = sQLiteTableFromSchema.getSortedColumns();
        Iterator<SQLiteColumn> it = sortedColumns.iterator();
        while (it.hasNext()) {
            sb.append(Wrap.inBackticks(it.next().getName()));
            if (it.hasNext()) {
                sb.append(",");
                sb.append(SqlKeyword.DELIMITER);
            }
        }
        sb.append(")");
        SqlKeyword sqlKeyword2 = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword2);
        sb.append("VALUES");
        sb.append(sqlKeyword2);
        sb.append("(");
        for (int i = 0; i < sortedColumns.size(); i++) {
            if (i == sortedColumns.size() - 1) {
                sb.append("?");
            } else {
                sb.append("?, ");
            }
        }
        sb.append(")");
        return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString(), extractFieldValues(t));
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public <T extends Model> SqlCommand updateFor(@NonNull ModelSchema modelSchema, @NonNull T t) throws DataStoreException {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE");
        SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(sQLiteTableFromSchema.getName()));
        sb.append(sqlKeyword);
        sb.append("SET");
        sb.append(sqlKeyword);
        Iterator<SQLiteColumn> it = sQLiteTableFromSchema.getSortedColumns().iterator();
        while (it.hasNext()) {
            sb.append(Wrap.inBackticks(it.next().getName()));
            SqlKeyword sqlKeyword2 = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword2);
            sb.append(SqlKeyword.EQUAL);
            sb.append(sqlKeyword2);
            sb.append("?");
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        SQLPredicate sQLPredicate = new SQLPredicate(QueryField.field(SQLiteTable.fromSchema(modelSchema).getPrimaryKeyColumnName()).eq(t.getPrimaryKeyString()));
        SqlKeyword sqlKeyword3 = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword3);
        sb.append(SqlKeyword.WHERE);
        sb.append(sqlKeyword3);
        sb.append(sQLPredicate);
        sb.append(";");
        String string = sb.toString();
        List<Object> listExtractFieldValues = extractFieldValues(t);
        listExtractFieldValues.addAll(sQLPredicate.getBindings());
        return new SqlCommand(sQLiteTableFromSchema.getName(), string, listExtractFieldValues);
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.SQLCommandFactory
    @NonNull
    public SqlCommand deleteFor(@NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) throws DataStoreException {
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchema);
        SQLPredicate sQLPredicate = new SQLPredicate(queryPredicate);
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM");
        SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword);
        sb.append(Wrap.inBackticks(sQLiteTableFromSchema.getName()));
        sb.append(sqlKeyword);
        sb.append(SqlKeyword.WHERE);
        sb.append(sqlKeyword);
        sb.append(sQLPredicate);
        sb.append(";");
        return new SqlCommand(sQLiteTableFromSchema.getName(), sb.toString(), sQLPredicate.getBindings());
    }

    private String getIndexName(String str, List<String> list) {
        if (!str.equals(UNDEFINED)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("undefined_");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            sb.append(listIterator.next());
            if (listIterator.hasNext()) {
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
        }
        return sb.toString();
    }

    private List<Object> extractFieldValues(@NonNull Model model) throws DataStoreException {
        Object objConvertValueFromTarget;
        ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(model.getModelName());
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchemaForModelClass);
        SQLiteModelFieldTypeConverter sQLiteModelFieldTypeConverter = new SQLiteModelFieldTypeConverter(modelSchemaForModelClass, this.schemaRegistry, this.gson);
        Map<String, ModelField> fields = modelSchemaForModelClass.getFields();
        ArrayList arrayList = new ArrayList();
        for (SQLiteColumn sQLiteColumn : sQLiteTableFromSchema.getSortedColumns()) {
            if (sQLiteColumn.getName().equals(SQLiteTable.PRIMARY_KEY_FIELD_NAME)) {
                objConvertValueFromTarget = model.getPrimaryKeyString();
            } else if (sQLiteColumn.isForeignKey()) {
                ModelField modelField = fields.get(sQLiteColumn.getOwnedField());
                Objects.requireNonNull(modelField);
                objConvertValueFromTarget = sQLiteModelFieldTypeConverter.convertValueFromTarget(model, modelField);
            } else {
                ModelField modelField2 = fields.get(sQLiteColumn.getFieldName());
                Objects.requireNonNull(modelField2);
                objConvertValueFromTarget = sQLiteModelFieldTypeConverter.convertValueFromTarget(model, modelField2);
            }
            arrayList.add(objConvertValueFromTarget);
        }
        return arrayList;
    }

    private void recursivelyBuildJoins(SQLiteTable sQLiteTable, Map<String, List<SQLiteColumn>> map, StringBuilder sb, Map<String, Integer> map2, String str) {
        String str2;
        Iterator<SQLiteColumn> it = sQLiteTable.getForeignKeys().iterator();
        while (it.hasNext()) {
            SQLiteColumn next = it.next();
            String ownedType = next.getOwnedType();
            SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(this.schemaRegistry.getModelSchemaForModelClass(ownedType));
            int iIntValue = 1;
            if (map2.containsKey(ownedType)) {
                Integer num = map2.get(ownedType);
                iIntValue = 1 + (num == null ? 0 : num.intValue());
                str2 = ownedType + iIntValue;
            } else {
                str2 = ownedType;
            }
            map2.put(ownedType, Integer.valueOf(iIntValue));
            map.put(str2, sQLiteTableFromSchema.getSortedColumns());
            sb.append(next.isNonNull() ? SqlKeyword.INNER_JOIN : SqlKeyword.LEFT_JOIN);
            SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword);
            sb.append(Wrap.inBackticks(ownedType));
            sb.append(sqlKeyword);
            if (!ownedType.equals(str2)) {
                sb.append(SqlKeyword.AS);
                sb.append(sqlKeyword);
                sb.append(Wrap.inBackticks(str2));
                sb.append(sqlKeyword);
            }
            String strReplace = next.getQuotedColumnName().replace(sQLiteTable.getName(), str);
            String strReplace2 = sQLiteTableFromSchema.getPrimaryKeyColumnName().replace(ownedType, str2);
            sb.append(SqlKeyword.ON);
            sb.append(sqlKeyword);
            sb.append(strReplace);
            sb.append(SqlKeyword.EQUAL);
            sb.append(strReplace2);
            if (it.hasNext()) {
                sb.append(sqlKeyword);
            }
            recursivelyBuildJoins(sQLiteTableFromSchema, map, sb, map2, str2);
        }
    }

    private StringBuilder parseColumns(SQLiteTable sQLiteTable) {
        StringBuilder sb = new StringBuilder();
        Iterator<SQLiteColumn> it = sQLiteTable.getSortedColumns().iterator();
        while (it.hasNext()) {
            SQLiteColumn next = it.next();
            sb.append(Wrap.inBackticks(next.getName()));
            SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword);
            sb.append(next.getColumnType());
            if (next.isNonNull()) {
                sb.append(sqlKeyword);
                sb.append("NOT NULL");
            }
            if (it.hasNext()) {
                sb.append(",");
                sb.append(sqlKeyword);
            }
        }
        return sb;
    }

    private StringBuilder parseForeignKeys(SQLiteTable sQLiteTable) {
        StringBuilder sb = new StringBuilder();
        Iterator<SQLiteColumn> it = sQLiteTable.getForeignKeys().iterator();
        while (it.hasNext()) {
            SQLiteColumn next = it.next();
            String name = next.getName();
            String ownedType = next.getOwnedType();
            ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(ownedType);
            String idField = getIdField(modelSchemaForModelClass.getPrimaryIndexFields(), modelSchemaForModelClass.getModelType());
            sb.append("FOREIGN KEY");
            SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword);
            sb.append("(" + Wrap.inBackticks(name) + ")");
            sb.append(sqlKeyword);
            sb.append("REFERENCES");
            sb.append(sqlKeyword);
            sb.append(Wrap.inBackticks(ownedType));
            sb.append("(" + Wrap.inBackticks(idField) + ")");
            sb.append(sqlKeyword);
            sb.append("ON DELETE CASCADE");
            if (it.hasNext()) {
                sb.append(",");
                sb.append(sqlKeyword);
            }
        }
        return sb;
    }

    private String getIdField(List<String> list, Model.Type type) {
        return (type != Model.Type.USER || list.size() <= 1) ? list.get(0) : SQLiteTable.PRIMARY_KEY_FIELD_NAME;
    }

    private StringBuilder createPrimaryKey(@NonNull ModelSchema modelSchema) {
        StringBuilder sb = new StringBuilder();
        List<String> primaryIndexFields = modelSchema.getPrimaryIndexFields();
        if (primaryIndexFields.size() > 0) {
            sb.append("PRIMARY KEY");
            SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
            sb.append(sqlKeyword);
            sb.append("(");
            sb.append(sqlKeyword);
            sb.append("'");
            sb.append(getIdField(primaryIndexFields, modelSchema.getModelType()));
            sb.append("'");
        } else {
            sb.append(SqlKeyword.DELIMITER);
            sb.append("'");
            sb.append(primaryIndexFields.get(0));
            sb.append("'");
        }
        sb.append(")");
        return sb;
    }

    private boolean shouldCreateIndex(ModelIndex modelIndex, Map<String, ModelAssociation> map) {
        if (modelIndex.getIndexName().equals(UNDEFINED) && modelIndex.getIndexFieldNames().size() == 1) {
            return false;
        }
        for (Map.Entry<String, ModelAssociation> entry : map.entrySet()) {
            if (entry.getValue().isOwner()) {
                for (String str : entry.getValue().getTargetNames()) {
                    if (modelIndex.getIndexFieldNames().contains(str)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
