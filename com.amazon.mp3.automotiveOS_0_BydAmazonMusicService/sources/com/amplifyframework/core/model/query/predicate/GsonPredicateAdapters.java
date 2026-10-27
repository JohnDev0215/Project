package com.amplifyframework.core.model.query.predicate;

import com.amplifyframework.core.model.query.predicate.QueryOperator;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonPredicateAdapters {
    private GsonPredicateAdapters() {
    }

    public static void register(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(QueryOperator.class, new QueryOperatorAdapter()).registerTypeAdapter(QueryPredicate.class, new QueryPredicateAdapter());
    }

    public static final class QueryOperatorAdapter implements JsonDeserializer<QueryOperator<?>>, JsonSerializer<QueryOperator<?>> {
        private static final String TYPE = "type";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public QueryOperator<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                return null;
            }
            switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.valueOf(jsonElement.getAsJsonObject().get("type").getAsString()).ordinal()]) {
                case 1:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, ContainsQueryOperator.class);
                case 2:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, NotContainsQueryOperator.class);
                case 3:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, GreaterOrEqualQueryOperator.class);
                case 4:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, LessOrEqualQueryOperator.class);
                case 5:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, GreaterThanQueryOperator.class);
                case 6:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, LessThanQueryOperator.class);
                case 7:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, BetweenQueryOperator.class);
                case 8:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, EqualQueryOperator.class);
                case 9:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, NotEqualQueryOperator.class);
                case 10:
                    return (QueryOperator) jsonDeserializationContext.deserialize(jsonElement, BeginsWithQueryOperator.class);
                default:
                    throw new JsonParseException("Unable to deserialize " + jsonElement.toString() + " to QueryOperator instance.");
            }
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(QueryOperator<?> queryOperator, Type type, JsonSerializationContext jsonSerializationContext) {
            if (queryOperator instanceof ContainsQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, ContainsQueryOperator.class);
            }
            if (queryOperator instanceof NotContainsQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, NotContainsQueryOperator.class);
            }
            if (queryOperator instanceof GreaterOrEqualQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, GreaterOrEqualQueryOperator.class);
            }
            if (queryOperator instanceof LessOrEqualQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, LessOrEqualQueryOperator.class);
            }
            if (queryOperator instanceof GreaterThanQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, GreaterThanQueryOperator.class);
            }
            if (queryOperator instanceof LessThanQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, LessThanQueryOperator.class);
            }
            if (queryOperator instanceof BetweenQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, BetweenQueryOperator.class);
            }
            if (queryOperator instanceof EqualQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, EqualQueryOperator.class);
            }
            if (queryOperator instanceof NotEqualQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, NotEqualQueryOperator.class);
            }
            if (queryOperator instanceof BeginsWithQueryOperator) {
                return jsonSerializationContext.serialize(queryOperator, BeginsWithQueryOperator.class);
            }
            throw new JsonParseException("Unable to serialize a QueryOperator of type " + queryOperator.type().name() + ".");
        }
    }

    public static final class QueryPredicateAdapter implements JsonDeserializer<QueryPredicate>, JsonSerializer<QueryPredicate> {
        private static final String TYPE = "_type";

        private enum PredicateType {
            OPERATION,
            GROUP,
            ALL
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public QueryPredicate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                return null;
            }
            int i = AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$GsonPredicateAdapters$QueryPredicateAdapter$PredicateType[PredicateType.valueOf(jsonElement.getAsJsonObject().get(TYPE).getAsString()).ordinal()];
            if (i == 1) {
                return (QueryPredicate) jsonDeserializationContext.deserialize(jsonElement, QueryPredicateOperation.class);
            }
            if (i == 2) {
                return (QueryPredicate) jsonDeserializationContext.deserialize(jsonElement, QueryPredicateGroup.class);
            }
            if (i == 3) {
                return (QueryPredicate) jsonDeserializationContext.deserialize(jsonElement, MatchAllQueryPredicate.class);
            }
            throw new JsonParseException("Unable to deserialize " + jsonElement.toString() + " to QueryPredicate instance.");
        }

        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(QueryPredicate queryPredicate, Type type, JsonSerializationContext jsonSerializationContext) throws JsonParseException {
            JsonElement jsonElementSerialize;
            PredicateType predicateType;
            if (queryPredicate instanceof MatchAllQueryPredicate) {
                predicateType = PredicateType.ALL;
                jsonElementSerialize = jsonSerializationContext.serialize(queryPredicate, MatchAllQueryPredicate.class);
            } else if (queryPredicate instanceof QueryPredicateOperation) {
                jsonElementSerialize = jsonSerializationContext.serialize(queryPredicate, QueryPredicateOperation.class);
                predicateType = PredicateType.OPERATION;
            } else if (queryPredicate instanceof QueryPredicateGroup) {
                jsonElementSerialize = jsonSerializationContext.serialize(queryPredicate, QueryPredicateGroup.class);
                predicateType = PredicateType.GROUP;
            } else {
                throw new JsonParseException("Unable to identify the predicate type.");
            }
            JsonObject asJsonObject = jsonElementSerialize.getAsJsonObject();
            asJsonObject.addProperty(TYPE, predicateType.name());
            return asJsonObject;
        }
    }

    /* JADX INFO: renamed from: com.amplifyframework.core.model.query.predicate.GsonPredicateAdapters$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$query$predicate$GsonPredicateAdapters$QueryPredicateAdapter$PredicateType;
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type;

        static {
            int[] iArr = new int[QueryPredicateAdapter.PredicateType.values().length];
            $SwitchMap$com$amplifyframework$core$model$query$predicate$GsonPredicateAdapters$QueryPredicateAdapter$PredicateType = iArr;
            try {
                iArr[QueryPredicateAdapter.PredicateType.OPERATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$GsonPredicateAdapters$QueryPredicateAdapter$PredicateType[QueryPredicateAdapter.PredicateType.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$GsonPredicateAdapters$QueryPredicateAdapter$PredicateType[QueryPredicateAdapter.PredicateType.ALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[QueryOperator.Type.values().length];
            $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type = iArr2;
            try {
                iArr2[QueryOperator.Type.CONTAINS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.NOT_CONTAINS.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_OR_EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_OR_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.BETWEEN.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.EQUAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.NOT_EQUAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.BEGINS_WITH.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }
}
