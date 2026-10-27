package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.appsync.AppSyncExtensions;
import com.amplifyframework.util.Empty;
import com.amplifyframework.util.GsonFactory;
import com.amplifyframework.util.TypeMaker;
import com.google.gson.Gson;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AppSyncConflictUnhandledError<T extends Model> {
    private final ModelWithMetadata<T> serverVersion;

    private AppSyncConflictUnhandledError(ModelWithMetadata<T> modelWithMetadata) {
        this.serverVersion = modelWithMetadata;
    }

    @Nullable
    public static <T extends Model> AppSyncConflictUnhandledError<T> findFirst(@NonNull Class<T> cls, @Nullable List<GraphQLResponse.Error> list) {
        if (Empty.check(list)) {
            return null;
        }
        Gson gsonInstance = GsonFactory.instance();
        for (GraphQLResponse.Error error : list) {
            if (!Empty.check(error.getExtensions())) {
                AppSyncExtensions appSyncExtensions = new AppSyncExtensions(error.getExtensions());
                if (AppSyncExtensions.AppSyncErrorType.CONFLICT_UNHANDLED.equals(appSyncExtensions.getErrorType())) {
                    ModelWithMetadata modelWithMetadata = (ModelWithMetadata) gsonInstance.fromJson(gsonInstance.toJson(appSyncExtensions.getData()), TypeMaker.getParameterizedType(ModelWithMetadata.class, cls));
                    Objects.requireNonNull(modelWithMetadata);
                    return new AppSyncConflictUnhandledError<>(modelWithMetadata);
                }
            }
        }
        return null;
    }

    public ModelWithMetadata<T> getServerVersion() {
        return this.serverVersion;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AppSyncConflictUnhandledError.class != obj.getClass()) {
            return false;
        }
        return getServerVersion().equals(((AppSyncConflictUnhandledError) obj).getServerVersion());
    }

    public int hashCode() {
        return getServerVersion().hashCode();
    }

    public String toString() {
        return "ConflictUnhandledError{serverVersion=" + this.serverVersion + '}';
    }
}
