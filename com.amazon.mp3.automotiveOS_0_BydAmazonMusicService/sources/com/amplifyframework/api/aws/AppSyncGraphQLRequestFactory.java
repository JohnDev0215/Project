package com.amplifyframework.api.aws;

import androidx.annotation.NonNull;
import com.amazon.music.find.utils.PageUriUtils;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AppSyncGraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.api.graphql.QueryType;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.model.AuthRule;
import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelSchema;
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
import com.amplifyframework.util.Casing;
import com.amplifyframework.util.TypeMaker;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AppSyncGraphQLRequestFactory {
    private static final int DEFAULT_QUERY_LIMIT = 1000;

    private AppSyncGraphQLRequestFactory() {
    }

    public static <R, T extends Model> GraphQLRequest<R> buildQuery(Class<T> cls, String str) {
        try {
            return AppSyncGraphQLRequest.builder().modelClass(cls).operation(QueryType.GET).requestOptions(new ApiGraphQLRequestOptions()).responseType(cls).variable("id", "ID!", str).build();
        } catch (AmplifyException e) {
            throw new IllegalStateException("Could not generate a schema for the specified class", e);
        }
    }

    public static <R, T extends Model> GraphQLRequest<R> buildQuery(Class<T> cls, QueryPredicate queryPredicate) {
        return buildQuery(cls, queryPredicate, 1000, TypeMaker.getParameterizedType(PaginatedResult.class, cls));
    }

    public static <R, T extends Model> GraphQLRequest<R> buildPaginatedResultQuery(Class<T> cls, QueryPredicate queryPredicate, int i) {
        return buildQuery(cls, queryPredicate, i, TypeMaker.getParameterizedType(PaginatedResult.class, cls));
    }

    static <R, T extends Model> GraphQLRequest<R> buildQuery(Class<T> cls, QueryPredicate queryPredicate, int i, Type type) {
        try {
            String name = ModelSchema.fromModelClass(cls).getName();
            AppSyncGraphQLRequest.Builder builderResponseType = AppSyncGraphQLRequest.builder().modelClass(cls).operation(QueryType.LIST).requestOptions(new ApiGraphQLRequestOptions()).responseType(type);
            if (!QueryPredicates.all().equals(queryPredicate)) {
                builderResponseType.variable(PageUriUtils.FILTER_QUERY_PARAM, "Model" + Casing.capitalizeFirst(name) + "FilterInput", parsePredicate(queryPredicate));
            }
            builderResponseType.variable("limit", "Int", Integer.valueOf(i));
            return builderResponseType.build();
        } catch (AmplifyException e) {
            throw new IllegalStateException("Could not generate a schema for the specified class", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <R, T extends Model> GraphQLRequest<R> buildMutation(T t, QueryPredicate queryPredicate, MutationType mutationType) {
        try {
            Class<?> cls = t.getClass();
            ModelSchema modelSchemaFromModelClass = ModelSchema.fromModelClass(cls);
            String name = modelSchemaFromModelClass.getName();
            AppSyncGraphQLRequest.Builder builderResponseType = AppSyncGraphQLRequest.builder().operation(mutationType).modelClass(cls).requestOptions(new ApiGraphQLRequestOptions()).responseType(cls);
            String str = Casing.capitalize(mutationType.toString()) + Casing.capitalizeFirst(name) + "Input!";
            if (MutationType.DELETE.equals(mutationType)) {
                builderResponseType.variable("input", str, getDeleteMutationInputMap(modelSchemaFromModelClass, t));
            } else {
                builderResponseType.variable("input", str, getMapOfFieldNameAndValues(modelSchemaFromModelClass, t));
            }
            if (!QueryPredicates.all().equals(queryPredicate)) {
                builderResponseType.variable("condition", "Model" + Casing.capitalizeFirst(name) + "ConditionInput", parsePredicate(queryPredicate));
            }
            return builderResponseType.build();
        } catch (AmplifyException e) {
            throw new IllegalStateException("Could not generate a schema for the specified class", e);
        }
    }

    public static <R, T extends Model> GraphQLRequest<R> buildSubscription(Class<T> cls, SubscriptionType subscriptionType) {
        try {
            return AppSyncGraphQLRequest.builder().modelClass(cls).operation(subscriptionType).requestOptions(new ApiGraphQLRequestOptions()).responseType(cls).build();
        } catch (AmplifyException e) {
            throw new IllegalStateException("Failed to build GraphQLRequest", e);
        }
    }

    private static Map<String, Object> parsePredicate(QueryPredicate queryPredicate) {
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
                    throw new IllegalStateException("Predicate group of type NOT must include a value to negate.", e);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator<QueryPredicate> it = queryPredicateGroup.predicates().iterator();
            while (it.hasNext()) {
                arrayList.add(parsePredicate(it.next()));
            }
            return Collections.singletonMap(queryPredicateGroup.type().toString().toLowerCase(Locale.getDefault()), arrayList);
        }
        throw new IllegalStateException("Invalid predicate type, supported values: QueryPredicateOperation, QueryPredicateGroup.");
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.AppSyncGraphQLRequestFactory$1, reason: invalid class name */
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

    private static String appSyncOpType(QueryOperator.Type type) {
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
                throw new IllegalStateException("Tried to parse an unsupported QueryOperator type. Check if a new QueryOperator.Type enum has been created which is not supported in the AppSyncGraphQLRequestFactory.");
        }
    }

    private static Object appSyncOpValue(QueryOperator<?> queryOperator) {
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
                throw new IllegalStateException("Tried to parse an unsupported QueryOperator type. Check if a new QueryOperator.Type enum has been created which is not implemented yet.");
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
        if (!model.getClass().getSimpleName().equals(modelSchema.getName())) {
            throw new AmplifyException("The object provided is not an instance of " + modelSchema.getName() + ".", "Please provide an instance of " + modelSchema.getName() + " that matches the schema type.");
        }
        HashMap map = new HashMap();
        for (ModelField modelField : modelSchema.getFields().values()) {
            if (!modelField.isReadOnly()) {
                String name = modelField.getName();
                Object objExtractFieldValue = extractFieldValue(name, model, modelSchema);
                ModelAssociation modelAssociation = modelSchema.getAssociations().get(name);
                if (modelAssociation == null) {
                    map.put(name, objExtractFieldValue);
                } else if (modelAssociation.isOwner()) {
                    Objects.requireNonNull(objExtractFieldValue);
                    map.put(modelAssociation.getTargetName(), ((Model) objExtractFieldValue).getPrimaryKeyString());
                }
            }
        }
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

    private static Object extractFieldValue(String str, Model model, ModelSchema modelSchema) throws AmplifyException {
        try {
            Field declaredField = model.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(model);
        } catch (Exception e) {
            throw new AmplifyException("An invalid field was provided. " + str + " is not present in " + modelSchema.getName(), e, "Check if this model schema is a correct representation of the fields in the provided Object");
        }
    }
}
