package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.datastore.DataStoreConfigurationProvider;
import com.amplifyframework.datastore.DataStoreConflictHandler;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.appsync.AppSyncConflictUnhandledError;
import com.amplifyframework.datastore.appsync.ModelMetadata;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda2;
import com.amplifyframework.util.GsonFactory;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class ConflictResolver {
    private final AppSync appSync;
    private final DataStoreConfigurationProvider configurationProvider;

    ConflictResolver(DataStoreConfigurationProvider dataStoreConfigurationProvider, AppSync appSync) {
        this.configurationProvider = dataStoreConfigurationProvider;
        this.appSync = appSync;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    <T extends Model> Single<ModelWithMetadata<T>> resolve(@NonNull PendingMutation<T> pendingMutation, @NonNull AppSyncConflictUnhandledError<T> appSyncConflictUnhandledError) {
        try {
            final DataStoreConflictHandler conflictHandler = this.configurationProvider.getConfiguration().getConflictHandler();
            ModelWithMetadata<T> serverVersion = appSyncConflictUnhandledError.getServerVersion();
            final ModelMetadata syncMetadata = serverVersion.getSyncMetadata();
            final DataStoreConflictHandler.ConflictData conflictDataCreate = DataStoreConflictHandler.ConflictData.create(getMutatedModelFromSerializedModel(pendingMutation), getServerModel(serverVersion, pendingMutation.getMutatedItem()));
            return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.ConflictResolver$$ExternalSyntheticLambda4
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                    ConflictResolver.lambda$resolve$0(conflictHandler, conflictDataCreate, singleEmitter);
                }
            }).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.ConflictResolver$$ExternalSyntheticLambda5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$resolve$1(conflictDataCreate, syncMetadata, (DataStoreConflictHandler.ConflictResolutionDecision) obj);
                }
            });
        } catch (DataStoreException e) {
            return Single.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$resolve$0(DataStoreConflictHandler dataStoreConflictHandler, DataStoreConflictHandler.ConflictData conflictData, final SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.getClass();
        dataStoreConflictHandler.onConflictDetected(conflictData, new Consumer() { // from class: com.amplifyframework.datastore.syncengine.ConflictResolver$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                singleEmitter.onSuccess((DataStoreConflictHandler.ConflictResolutionDecision) obj);
            }
        });
    }

    private <T extends Model> T getMutatedModelFromSerializedModel(@NonNull PendingMutation<T> pendingMutation) {
        SerializedModel serializedModel = (T) pendingMutation.getMutatedItem();
        if (!(serializedModel instanceof SerializedModel)) {
            return serializedModel;
        }
        SerializedModel serializedModel2 = serializedModel;
        ModelSchema modelSchema = ((SerializedModel) pendingMutation.getMutatedItem()).getModelSchema();
        Objects.requireNonNull(modelSchema);
        Class<? extends Model> modelClass = modelSchema.getModelClass();
        if (modelClass == SerializedModel.class) {
            return serializedModel;
        }
        Gson gsonInstance = GsonFactory.instance();
        return (T) gsonInstance.fromJson(gsonInstance.toJson(serializedModel2.getSerializedData()), (Type) modelClass);
    }

    private <T extends Model> T getServerModel(@NonNull ModelWithMetadata<T> modelWithMetadata, T t) {
        SerializedModel serializedModel = (T) modelWithMetadata.getModel();
        return serializedModel instanceof SerializedModel ? SerializedModel.builder().modelSchema(((SerializedModel) t).getModelSchema()).serializedData(serializedModel.getSerializedData()).build() : serializedModel;
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.ConflictResolver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$DataStoreConflictHandler$ResolutionStrategy;

        static {
            int[] iArr = new int[DataStoreConflictHandler.ResolutionStrategy.values().length];
            $SwitchMap$com$amplifyframework$datastore$DataStoreConflictHandler$ResolutionStrategy = iArr;
            try {
                iArr[DataStoreConflictHandler.ResolutionStrategy.RETRY_LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$DataStoreConflictHandler$ResolutionStrategy[DataStoreConflictHandler.ResolutionStrategy.APPLY_REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$DataStoreConflictHandler$ResolutionStrategy[DataStoreConflictHandler.ResolutionStrategy.RETRY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    /* JADX INFO: renamed from: resolveModelAndMetadata, reason: merged with bridge method [inline-methods] */
    public <T extends Model> Single<ModelWithMetadata<T>> lambda$resolve$1(@NonNull DataStoreConflictHandler.ConflictData<T> conflictData, @NonNull ModelMetadata modelMetadata, @NonNull DataStoreConflictHandler.ConflictResolutionDecision<T> conflictResolutionDecision) {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$DataStoreConflictHandler$ResolutionStrategy[conflictResolutionDecision.getResolutionStrategy().ordinal()];
        if (i == 1) {
            return publish(conflictData.getLocal(), modelMetadata.getVersion().intValue());
        }
        if (i == 2) {
            return Single.just(new ModelWithMetadata(conflictData.getRemote(), modelMetadata));
        }
        if (i == 3) {
            return publish(conflictResolutionDecision.getCustomModel(), modelMetadata.getVersion().intValue());
        }
        throw new IllegalStateException("Unknown resolution strategy = " + conflictResolutionDecision.getResolutionStrategy());
    }

    @NonNull
    private <T extends Model> Single<ModelWithMetadata<T>> publish(@NonNull final T t, final int i) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.ConflictResolver$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$publish$2(t, i, singleEmitter);
            }
        }).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.ConflictResolver$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return ConflictResolver.lambda$publish$3((GraphQLResponse) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$publish$2(Model model, int i, SingleEmitter singleEmitter) throws Throwable {
        ModelSchema modelSchemaForModelClass = SchemaRegistry.instance().getModelSchemaForModelClass(model.getModelName());
        AppSync appSync = this.appSync;
        Integer numValueOf = Integer.valueOf(i);
        singleEmitter.getClass();
        appSync.update(model, modelSchemaForModelClass, numValueOf, new ConflictResolver$$ExternalSyntheticLambda1(singleEmitter), new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SingleSource lambda$publish$3(GraphQLResponse graphQLResponse) throws Throwable {
        if (graphQLResponse.hasErrors() || !graphQLResponse.hasData()) {
            return Single.error(new DataStoreException("Error encountered while processing conflict data.", "Please check your conflict handler logic."));
        }
        return Single.just(graphQLResponse.getData());
    }
}
