package com.amplifyframework.api.aws;

import androidx.annotation.VisibleForTesting;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.util.GsonFactory;
import com.amplifyframework.util.TypeMaker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
final class GsonGraphQLResponseFactory implements GraphQLResponse.Factory {
    private final Gson gson;

    GsonGraphQLResponseFactory() {
        this(GsonFactory.instance());
    }

    @VisibleForTesting
    GsonGraphQLResponseFactory(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLResponse.Factory
    public <T> GraphQLResponse<T> buildResponse(GraphQLRequest<T> graphQLRequest, String str) throws ApiException {
        try {
            return (GraphQLResponse) this.gson.newBuilder().registerTypeHierarchyAdapter(Iterable.class, new IterableDeserializer(graphQLRequest)).create().fromJson(str, TypeMaker.getParameterizedType(GraphQLResponse.class, graphQLRequest.getResponseType()));
        } catch (JsonSyntaxException e) {
            throw new ApiException("Amplify encountered an error while deserializing an object.", e, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
    }

    static final class IterableDeserializer<R> implements JsonDeserializer<Iterable<Object>> {
        private static final String ITEMS_KEY = "items";
        private static final String NEXT_TOKEN_KEY = "nextToken";
        private final GraphQLRequest<R> request;

        IterableDeserializer(GraphQLRequest<R> graphQLRequest) {
            this.request = graphQLRequest;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Iterable<Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type type2 = parameterizedType.getActualTypeArguments()[0];
                if (jsonElement.isJsonObject()) {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    if (asJsonObject.has("items") && asJsonObject.get("items").isJsonArray()) {
                        Iterable<Object> list = toList(asJsonObject.get("items").getAsJsonArray(), type2, jsonDeserializationContext);
                        return PaginatedResult.class.equals(parameterizedType.getRawType()) ? buildPaginatedResult(list, asJsonObject.get(NEXT_TOKEN_KEY)) : list;
                    }
                    throw new JsonParseException("Got JSON from an API call which was supposed to go with a List but is in the form of an object rather than an array. It also is not in the standard format of having an items property with the actual array of data so we do not know how to deserialize it.");
                }
                if (jsonElement.isJsonArray()) {
                    return toList(jsonElement.getAsJsonArray(), type2, jsonDeserializationContext);
                }
                throw new JsonParseException("Got a JSON value that was not an object or a list. Refusing to deserialize into a Java Iterable.");
            }
            throw new JsonParseException("Expected a parameterized type during list deserialization.");
        }

        private Iterable<Object> toList(JsonArray jsonArray, Type type, JsonDeserializationContext jsonDeserializationContext) {
            ArrayList arrayList = new ArrayList();
            Iterator<JsonElement> it = jsonArray.iterator();
            while (it.hasNext()) {
                arrayList.add(jsonDeserializationContext.deserialize(it.next(), type));
            }
            return arrayList;
        }

        private PaginatedResult<Object> buildPaginatedResult(Iterable<Object> iterable, JsonElement jsonElement) throws AmplifyException {
            AppSyncGraphQLRequest<R> appSyncGraphQLRequestBuild = null;
            if (jsonElement.isJsonPrimitive()) {
                String asString = jsonElement.getAsJsonPrimitive().getAsString();
                try {
                    GraphQLRequest<R> graphQLRequest = this.request;
                    if (graphQLRequest instanceof AppSyncGraphQLRequest) {
                        appSyncGraphQLRequestBuild = ((AppSyncGraphQLRequest) graphQLRequest).newBuilder().variable(NEXT_TOKEN_KEY, "String", asString).build();
                    }
                } catch (AmplifyException e) {
                    throw new JsonParseException("Failed to create requestForNextPage with nextToken variable", e);
                }
            }
            return new PaginatedResult<>(iterable, appSyncGraphQLRequestBuild);
        }
    }
}
