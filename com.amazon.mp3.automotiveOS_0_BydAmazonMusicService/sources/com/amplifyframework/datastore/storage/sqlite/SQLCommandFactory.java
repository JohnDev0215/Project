package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreException;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
interface SQLCommandFactory {
    @NonNull
    Set<SqlCommand> createIndexesFor(@NonNull ModelSchema modelSchema);

    @NonNull
    Set<SqlCommand> createIndexesForForeignKeys(@NonNull ModelSchema modelSchema);

    @NonNull
    SqlCommand createTableFor(@NonNull ModelSchema modelSchema);

    @NonNull
    SqlCommand deleteFor(@NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) throws DataStoreException;

    @NonNull
    SqlCommand existsFor(@NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) throws DataStoreException;

    @NonNull
    <T extends Model> SqlCommand insertFor(@NonNull ModelSchema modelSchema, @NonNull T t) throws DataStoreException;

    @NonNull
    SqlCommand queryFor(@NonNull ModelSchema modelSchema, @NonNull QueryOptions queryOptions) throws DataStoreException;

    @NonNull
    <T extends Model> SqlCommand updateFor(@NonNull ModelSchema modelSchema, @NonNull T t) throws DataStoreException;
}
