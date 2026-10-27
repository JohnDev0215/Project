package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.find.utils.PageUriUtils;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AppSyncGraphQLRequest;
import com.amplifyframework.api.aws.AuthModeStrategyType;
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.api.graphql.QueryType;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.AuthRule;
import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelIdentifier;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SerializedCustomType;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.predicate.BeginsWithQueryOperator;
import com.amplifyframework.core.model.query.predicate.BetweenQueryOperator;
import com.amplifyframework.core.model.query.predicate.ContainsQueryOperator;
import com.amplifyframework.core.model.query.predicate.EqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.GreaterOrEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.GreaterThanQueryOperator;
import com.amplifyframework.core.model.query.predicate.LessOrEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.LessThanQueryOperator;
import com.amplifyframework.core.model.query.predicate.NotContainsQueryOperator;
import com.amplifyframework.core.model.query.predicate.NotEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.util.Casing;
import com.amplifyframework.util.TypeMaker;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
final class AppSyncRequestFactory {
    private AppSyncRequestFactory() {
    }

    @NonNull
    static <T> AppSyncGraphQLRequest<T> buildSyncRequest(@NonNull ModelSchema modelSchema, @Nullable Long l, @Nullable Integer num, @NonNull QueryPredicate queryPredicate) throws DataStoreException {
        return buildSyncRequest(modelSchema, l, num, queryPredicate, AuthModeStrategyType.DEFAULT);
    }

    @NonNull
    static <T> AppSyncGraphQLRequest<T> buildSyncRequest(@NonNull ModelSchema modelSchema, @Nullable Long l, @Nullable Integer num, @NonNull QueryPredicate queryPredicate, @NonNull AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            AppSyncGraphQLRequest.Builder builderResponseType = AppSyncGraphQLRequest.builder().modelClass(modelSchema.getModelClass()).modelSchema(modelSchema).operation(QueryType.SYNC).requestAuthorizationStrategyType(authModeStrategyType).requestOptions(new DataStoreGraphQLRequestOptions()).responseType(TypeMaker.getParameterizedType(PaginatedResult.class, ModelWithMetadata.class, modelSchema.getModelClass()));
            if (l != null) {
                builderResponseType.variable("lastSync", "AWSTimestamp", l);
            }
            if (num != null) {
                builderResponseType.variable("limit", "Int", num);
            }
            if (!QueryPredicates.all().equals(queryPredicate)) {
                String str = "Model" + Casing.capitalizeFirst(modelSchema.getName()) + "FilterInput";
                if (!(queryPredicate instanceof QueryPredicateGroup)) {
                    queryPredicate = QueryPredicateGroup.andOf(queryPredicate);
                }
                builderResponseType.variable(PageUriUtils.FILTER_QUERY_PARAM, str, parsePredicate(queryPredicate));
            }
            return builderResponseType.build();
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    static <T> AppSyncGraphQLRequest<T> buildSubscriptionRequest(ModelSchema modelSchema, SubscriptionType subscriptionType, AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            return AppSyncGraphQLRequest.builder().modelClass(modelSchema.getModelClass()).modelSchema(modelSchema).operation(subscriptionType).requestOptions(new DataStoreGraphQLRequestOptions()).requestAuthorizationStrategyType(authModeStrategyType).responseType(TypeMaker.getParameterizedType(ModelWithMetadata.class, modelSchema.getModelClass())).build();
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    static <M extends Model> AppSyncGraphQLRequest<ModelWithMetadata<M>> buildDeletionRequest(ModelSchema modelSchema, M m, Integer num, QueryPredicate queryPredicate, AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            HashMap map = new HashMap();
            map.put(ModelWithMetadataAdapter.VERSION_KEY, num);
            map.putAll(getDeleteMutationInputMap(modelSchema, m));
            return buildMutation(modelSchema, map, queryPredicate, MutationType.DELETE, authModeStrategyType);
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    static <M extends Model> AppSyncGraphQLRequest<ModelWithMetadata<M>> buildUpdateRequest(ModelSchema modelSchema, M m, Integer num, QueryPredicate queryPredicate, AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            HashMap map = new HashMap();
            map.put(ModelWithMetadataAdapter.VERSION_KEY, num);
            map.putAll(getMapOfFieldNameAndValues(modelSchema, m));
            return buildMutation(modelSchema, map, queryPredicate, MutationType.UPDATE, authModeStrategyType);
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    static <M extends Model> AppSyncGraphQLRequest<ModelWithMetadata<M>> buildCreationRequest(ModelSchema modelSchema, M m, AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            return buildMutation(modelSchema, getMapOfFieldNameAndValues(modelSchema, m), QueryPredicates.all(), MutationType.CREATE, authModeStrategyType);
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    static Map<String, Object> parsePredicate(QueryPredicate queryPredicate) throws DataStoreException {
        if (queryPredicate instanceof QueryPredicateOperation) {
            QueryPredicateOperation queryPredicateOperation = (QueryPredicateOperation) queryPredicate;
            QueryOperator queryOperatorOperator = queryPredicateOperation.operator();
            return Collections.singletonMap(queryPredicateOperation.field(), Collections.singletonMap(appSyncOpType(queryOperatorOperator.type()), appSyncOpValue(queryOperatorOperator)));
        }
        if (queryPredicate instanceof QueryPredicateGroup) {
            QueryPredicateGroup queryPredicateGroup = (QueryPredicateGroup) queryPredicate;
            if (QueryPredicateGroup.Type.NOT.equals(queryPredicateGroup.type())) {
                try {
                    return Collections.singletonMap("not", parsePredicate(queryPredicateGroup.predicates().get(0)));
                } catch (IndexOutOfBoundsException e) {
                    throw new DataStoreException("Predicate group of type NOT must include a value to negate.", e, "Check if you created a NOT condition in your Predicate with no included value.");
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator<QueryPredicate> it = queryPredicateGroup.predicates().iterator();
            while (it.hasNext()) {
                arrayList.add(parsePredicate(it.next()));
            }
            return Collections.singletonMap(queryPredicateGroup.type().toString().toLowerCase(Locale.getDefault()), arrayList);
        }
        throw new DataStoreException("Tried to parse an unsupported QueryPredicate", "Try changing to one of the supported values: QueryPredicateOperation, QueryPredicateGroup.");
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.appsync.AppSyncRequestFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type;

        static {
            int[] iArr = new int[QueryOperator.Type.values().length];
            $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type = iArr;
            try {
                iArr[QueryOperator.Type.NOT_EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_OR_EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_THAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.BETWEEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.BEGINS_WITH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.NOT_CONTAINS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private static String appSyncOpType(QueryOperator.Type type) throws DataStoreException {
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[type.ordinal()]) {
            case 1:
                return "ne";
            case 2:
                return "eq";
            case 3:
                return "le";
            case 4:
                return "lt";
            case 5:
                return "ge";
            case 6:
                return "gt";
            case 7:
                return "contains";
            case 8:
                return "between";
            case 9:
                return "beginsWith";
            default:
                throw new DataStoreException("Tried to parse an unsupported QueryOperator type", "Check if a new QueryOperator.Type enum has been created which is not supported in the AppSyncGraphQLRequestFactory.");
        }
    }

    private static Object appSyncOpValue(QueryOperator<?> queryOperator) throws DataStoreException {
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[queryOperator.type().ordinal()]) {
            case 1:
                return ((NotEqualQueryOperator) queryOperator).value();
            case 2:
                return ((EqualQueryOperator) queryOperator).value();
            case 3:
                return ((LessOrEqualQueryOperator) queryOperator).value();
            case 4:
                return ((LessThanQueryOperator) queryOperator).value();
            case 5:
                return ((GreaterOrEqualQueryOperator) queryOperator).value();
            case 6:
                return ((GreaterThanQueryOperator) queryOperator).value();
            case 7:
                return ((ContainsQueryOperator) queryOperator).value();
            case 8:
                BetweenQueryOperator betweenQueryOperator = (BetweenQueryOperator) queryOperator;
                return Arrays.asList(betweenQueryOperator.start(), betweenQueryOperator.end());
            case 9:
                return ((BeginsWithQueryOperator) queryOperator).value();
            case 10:
                return ((NotContainsQueryOperator) queryOperator).value();
            default:
                throw new DataStoreException("Tried to parse an unsupported QueryOperator type", "Check if a new QueryOperator.Type enum has been created which is not supported in the AppSyncGraphQLRequestFactory.");
        }
    }

    private static <M extends Model> AppSyncGraphQLRequest<ModelWithMetadata<M>> buildMutation(ModelSchema modelSchema, Map<String, Object> map, QueryPredicate queryPredicate, MutationType mutationType, AuthModeStrategyType authModeStrategyType) throws DataStoreException {
        try {
            String name = modelSchema.getName();
            AppSyncGraphQLRequest.Builder builderResponseType = AppSyncGraphQLRequest.builder().modelClass(modelSchema.getModelClass()).modelSchema(modelSchema).operation(mutationType).requestAuthorizationStrategyType(authModeStrategyType).requestOptions(new DataStoreGraphQLRequestOptions()).responseType(TypeMaker.getParameterizedType(ModelWithMetadata.class, modelSchema.getModelClass()));
            builderResponseType.variable("input", Casing.capitalize(mutationType.toString()) + Casing.capitalizeFirst(name) + "Input!", map);
            if (!QueryPredicates.all().equals(queryPredicate)) {
                builderResponseType.variable("condition", "Model" + Casing.capitalizeFirst(name) + "ConditionInput", parsePredicate(queryPredicate));
            }
            return builderResponseType.build();
        } catch (AmplifyException e) {
            throw new DataStoreException("Failed to get fields for model.", e, "Validate your model file.");
        }
    }

    private static Map<String, Object> getDeleteMutationInputMap(@NonNull ModelSchema modelSchema, @NonNull Model model) throws AmplifyException {
        HashMap map = new HashMap();
        for (String str : modelSchema.getPrimaryIndexFields()) {
            map.put(str, extractFieldValue(str, model, modelSchema));
        }
        return map;
    }

    private static Map<String, Object> getMapOfFieldNameAndValues(@NonNull ModelSchema modelSchema, @NonNull Model model) throws AmplifyException {
        boolean z = model instanceof SerializedModel;
        if (!model.getClass().getSimpleName().equals(modelSchema.getName()) && !z) {
            throw new AmplifyException("The object provided is not an instance of " + modelSchema.getName() + ".", "Please provide an instance of " + modelSchema.getName() + " that matches the schema type.");
        }
        HashMap map = new HashMap(extractFieldLevelData(modelSchema, model));
        for (AuthRule authRule : modelSchema.getAuthRules()) {
            if (AuthStrategy.OWNER.equals(authRule.getAuthStrategy())) {
                String ownerFieldOrDefault = authRule.getOwnerFieldOrDefault();
                if (map.containsKey(ownerFieldOrDefault) && map.get(ownerFieldOrDefault) == null) {
                    map.remove(ownerFieldOrDefault);
                }
            }
        }
        return map;
    }

    private static Map<String, Object> extractFieldLevelData(ModelSchema modelSchema, Model model) throws AmplifyException {
        HashMap map = new HashMap();
        for (ModelField modelField : modelSchema.getFields().values()) {
            if (!modelField.isReadOnly()) {
                String name = modelField.getName();
                ModelAssociation modelAssociation = modelSchema.getAssociations().get(name);
                if (!(model instanceof SerializedModel) || ((SerializedModel) model).getSerializedData().containsKey(name)) {
                    if (modelAssociation == null) {
                        map.put(name, extractFieldValue(modelField.getName(), model, modelSchema));
                    } else if (modelAssociation.isOwner()) {
                        if (modelSchema.getVersion() >= 1 && modelAssociation.getTargetNames() != null && modelAssociation.getTargetNames().length > 0) {
                            insertForeignKeyValues(map, modelField, model, modelSchema, modelAssociation);
                        } else {
                            map.put(modelAssociation.getTargetName(), extractAssociateId(modelField, model, modelSchema));
                        }
                    }
                }
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void insertForeignKeyValues(Map<String, Object> map, ModelField modelField, Model model, ModelSchema modelSchema, ModelAssociation modelAssociation) throws AmplifyException {
        Object objExtractFieldValue = extractFieldValue(modelField.getName(), model, modelSchema);
        if (modelField.isModel() && (objExtractFieldValue instanceof Model)) {
            Model model2 = (Model) objExtractFieldValue;
            if (model2.resolveIdentifier() instanceof ModelIdentifier) {
                ModelIdentifier modelIdentifier = (ModelIdentifier) model2.resolveIdentifier();
                ListIterator listIterator = Arrays.asList(modelAssociation.getTargetNames()).listIterator();
                ListIterator<? extends Serializable> listIterator2 = modelIdentifier.sortedKeys().listIterator();
                map.put(listIterator.next(), modelIdentifier.key().toString());
                while (listIterator.hasNext()) {
                    map.put(listIterator.next(), listIterator2.next().toString());
                }
                return;
            }
            if (objExtractFieldValue instanceof SerializedModel) {
                SerializedModel serializedModel = (SerializedModel) objExtractFieldValue;
                ModelSchema modelSchema2 = serializedModel.getModelSchema();
                if (modelSchema2 != null && modelSchema2.getPrimaryIndexFields().size() > 1) {
                    ListIterator<String> listIterator3 = modelSchema2.getPrimaryIndexFields().listIterator();
                    for (String str : modelAssociation.getTargetNames()) {
                        map.put(str, serializedModel.getSerializedData().get(listIterator3.next()));
                    }
                    return;
                }
                map.put(modelAssociation.getTargetNames()[0], model2.resolveIdentifier().toString());
                return;
            }
            map.put(modelAssociation.getTargetNames()[0], model2.resolveIdentifier().toString());
        }
    }

    private static Object extractAssociateId(ModelField modelField, Model model, ModelSchema modelSchema) throws AmplifyException {
        Object objExtractFieldValue = extractFieldValue(modelField.getName(), model, modelSchema);
        if (modelField.isModel() && (objExtractFieldValue instanceof Model)) {
            return ((Model) objExtractFieldValue).resolveIdentifier();
        }
        if (modelField.isModel() && (objExtractFieldValue instanceof Map)) {
            return ((Map) objExtractFieldValue).get("id");
        }
        throw new IllegalStateException("Associated data is not Model or Map.");
    }

    private static Object extractFieldValue(String str, Model model, ModelSchema modelSchema) throws AmplifyException {
        if (model instanceof SerializedModel) {
            Map<String, Object> serializedData = ((SerializedModel) model).getSerializedData();
            ModelField modelField = modelSchema.getFields().get(str);
            Object obj = serializedData.get(str);
            return (obj == null || modelField == null || !modelField.isCustomType()) ? obj : extractCustomTypeFieldValue(str, serializedData.get(str));
        }
        try {
            Field declaredField = model.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(model);
        } catch (Exception e) {
            throw new AmplifyException("An invalid field was provided. " + str + " is not present in " + modelSchema.getName(), e, "Check if this model schema is a correct representation of the fields in the provided Object");
        }
    }

    private static Object extractCustomTypeFieldValue(String str, Object obj) throws AmplifyException {
        if (obj instanceof SerializedCustomType) {
            HashMap map = new HashMap();
            for (Map.Entry<String, Object> entry : ((SerializedCustomType) obj).getSerializedData().entrySet()) {
                if (entry.getValue() instanceof SerializedCustomType) {
                    map.put(entry.getKey(), extractCustomTypeFieldValue(entry.getKey(), entry.getValue()));
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
            return map;
        }
        if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (List) obj) {
                if (obj2 instanceof SerializedCustomType) {
                    arrayList.add(extractCustomTypeFieldValue(str, obj2));
                } else {
                    arrayList.add(obj2);
                }
            }
            return arrayList;
        }
        throw new AmplifyException("An invalid CustomType field was provided. " + str + " must be an instance of SerializedCustomType or a List of instances of SerializedCustomType", "Check if this model schema is a correct representation of the fields in the provided Object");
    }
}
