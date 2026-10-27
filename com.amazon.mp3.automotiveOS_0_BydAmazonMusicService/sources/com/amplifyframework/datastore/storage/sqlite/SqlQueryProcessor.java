package com.amplifyframework.datastore.storage.sqlite;

import android.database.Cursor;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteTable;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.GsonFactory;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class SqlQueryProcessor {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final Gson gson = GsonFactory.instance();
    private final SchemaRegistry modelSchemaRegistry;
    private final SQLCommandFactory sqlCommandFactory;
    private final SQLCommandProcessor sqlCommandProcessor;

    SqlQueryProcessor(SQLCommandProcessor sQLCommandProcessor, SQLCommandFactory sQLCommandFactory, SchemaRegistry schemaRegistry) {
        this.sqlCommandProcessor = sQLCommandProcessor;
        this.sqlCommandFactory = sQLCommandFactory;
        this.modelSchemaRegistry = schemaRegistry;
    }

    <T extends Model> List<T> queryOfflineData(@NonNull Class<T> cls, @NonNull QueryOptions queryOptions, @NonNull Consumer<DataStoreException> consumer) {
        ModelSchema modelSchemaForModelClass = this.modelSchemaRegistry.getModelSchemaForModelClass(cls.getSimpleName());
        ArrayList arrayList = new ArrayList();
        try {
            Cursor cursorRawQuery = this.sqlCommandProcessor.rawQuery(this.sqlCommandFactory.queryFor(modelSchemaForModelClass, queryOptions));
            try {
                LOG.debug("Querying item for: " + cls.getSimpleName());
                SQLiteModelFieldTypeConverter sQLiteModelFieldTypeConverter = new SQLiteModelFieldTypeConverter(modelSchemaForModelClass, this.modelSchemaRegistry, this.gson);
                if (cursorRawQuery == null) {
                    consumer.accept(new DataStoreException("Error in getting a cursor to the table for class: " + cls.getSimpleName(), AmplifyException.TODO_RECOVERY_SUGGESTION));
                } else if (cursorRawQuery.moveToFirst()) {
                    do {
                        arrayList.add(this.gson.fromJson(this.gson.toJson(sQLiteModelFieldTypeConverter.buildMapForModel(cursorRawQuery)), (Class) cls));
                    } while (cursorRawQuery.moveToNext());
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } finally {
            }
        } catch (Exception e) {
            consumer.accept(new DataStoreException("Error in querying the model.", e, "See attached exception for details."));
        }
        return arrayList;
    }

    boolean modelExists(Model model, QueryPredicate queryPredicate) throws DataStoreException {
        ModelSchema modelSchemaForModelClass = this.modelSchemaRegistry.getModelSchemaForModelClass(model.getModelName());
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchemaForModelClass);
        return this.sqlCommandProcessor.executeExists(this.sqlCommandFactory.existsFor(modelSchemaForModelClass, queryPredicate.and(QueryField.field(sQLiteTableFromSchema.getName(), sQLiteTableFromSchema.getPrimaryKey().getName()).eq(model.getPrimaryKeyString()))));
    }
}
