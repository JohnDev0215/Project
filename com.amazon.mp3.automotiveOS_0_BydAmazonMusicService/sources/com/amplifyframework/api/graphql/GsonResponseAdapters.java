package com.amplifyframework.api.graphql;

import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.util.GsonObjectConverter;
import com.amplifyframework.util.TypeMaker;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonResponseAdapters {
    private GsonResponseAdapters() {
    }

    public static void register(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(GraphQLResponse.class, new ResponseDeserializer()).registerTypeAdapter(GraphQLResponse.Error.class, new ErrorDeserializer());
    }

    public static final class ResponseDeserializer implements JsonDeserializer<GraphQLResponse<Object>> {
        private static final String DATA_KEY = "data";
        private static final String ERRORS_KEY = "errors";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public GraphQLResponse<Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (!jsonElement.isJsonObject()) {
                throw new JsonParseException("Expected a JsonObject while deserializing GraphQLResponse but found " + jsonElement);
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            JsonElement jsonElementSkipQueryLevel = asJsonObject.has(DATA_KEY) ? asJsonObject.get(DATA_KEY) : null;
            List<GraphQLResponse.Error> errors = parseErrors(asJsonObject.has("errors") ? asJsonObject.get("errors") : null, jsonDeserializationContext);
            if (!(type instanceof ParameterizedType)) {
                throw new JsonParseException("Expected a parameterized type during GraphQLResponse deserialization.");
            }
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (shouldSkipQueryLevel(type2)) {
                jsonElementSkipQueryLevel = skipQueryLevel(jsonElementSkipQueryLevel);
            }
            if (jsonElementSkipQueryLevel == null || jsonElementSkipQueryLevel.isJsonNull()) {
                return new GraphQLResponse<>(null, errors);
            }
            return new GraphQLResponse<>(jsonDeserializationContext.deserialize(jsonElementSkipQueryLevel, type2), errors);
        }

        private boolean shouldSkipQueryLevel(Type type) {
            if (!(type instanceof ParameterizedType)) {
                return Model.class.isAssignableFrom((Class) type);
            }
            Type rawType = ((ParameterizedType) type).getRawType();
            return ModelWithMetadata.class.equals(rawType) || Iterable.class.isAssignableFrom((Class) rawType);
        }

        private JsonElement skipQueryLevel(JsonElement jsonElement) throws JsonParseException {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                return null;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.size() == 0) {
                throw new JsonParseException("Amplify encountered an error while serializing/deserializing an object.  Please add a single top level field in your query.");
            }
            if (asJsonObject.size() > 1) {
                throw new JsonParseException("Amplify encountered an error while serializing/deserializing an object.  Please reduce your query to a single top level field.");
            }
            return asJsonObject.get(asJsonObject.keySet().iterator().next());
        }

        private List<GraphQLResponse.Error> parseErrors(JsonElement jsonElement, JsonDeserializationContext jsonDeserializationContext) {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                return Collections.emptyList();
            }
            return (List) jsonDeserializationContext.deserialize(jsonElement, TypeMaker.getParameterizedType(ArrayList.class, GraphQLResponse.Error.class));
        }
    }

    public static final class ErrorDeserializer implements JsonDeserializer<GraphQLResponse.Error> {
        private static final String EXTENSIONS_KEY = "extensions";
        private static final String LOCATIONS_KEY = "locations";
        private static final String MESSAGE_KEY = "message";
        private static final String PATH_KEY = "path";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public GraphQLResponse.Error deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (!jsonElement.isJsonObject()) {
                throw new JsonParseException("Expected a JSONObject but found a " + jsonElement.getClass().getName() + " while deserializing error");
            }
            JsonObject jsonObject = new JsonObject();
            JsonObject jsonObject2 = new JsonObject();
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            String str = null;
            List list = null;
            List<GraphQLPathSegment> path = null;
            for (String str2 : asJsonObject.keySet()) {
                JsonElement jsonElement2 = asJsonObject.get(str2);
                if (jsonElement2 != null) {
                    str2.hashCode();
                    switch (str2) {
                        case "extensions":
                            jsonObject = jsonElement2.getAsJsonObject();
                            continue;
                            break;
                        case "locations":
                            list = (List) jsonDeserializationContext.deserialize(jsonElement2, TypeMaker.getParameterizedType(List.class, GraphQLLocation.class));
                            continue;
                            break;
                        case "path":
                            path = getPath(jsonElement2);
                            continue;
                            break;
                        case "message":
                            str = (String) jsonDeserializationContext.deserialize(jsonElement2, String.class);
                            break;
                        default:
                            jsonObject2.add(str2, jsonElement2);
                            break;
                    }
                }
            }
            if (str == null) {
                str = "Message was null or missing while deserializing error";
            }
            for (String str3 : jsonObject2.keySet()) {
                if (!jsonObject.has(str3)) {
                    jsonObject.add(str3, jsonObject2.get(str3));
                }
            }
            return new GraphQLResponse.Error(str, list, path, jsonObject.size() > 0 ? GsonObjectConverter.toMap(jsonObject) : null);
        }

        private List<GraphQLPathSegment> getPath(JsonElement jsonElement) {
            ArrayList arrayList = new ArrayList();
            if (jsonElement.isJsonNull()) {
                return null;
            }
            if (!jsonElement.isJsonArray()) {
                throw new JsonParseException("Expected a JsonArray but found a " + jsonElement.getClass().getName() + " while deserializing path");
            }
            for (JsonPrimitive jsonPrimitive : jsonElement.getAsJsonArray()) {
                if (jsonPrimitive.isNumber()) {
                    arrayList.add(new GraphQLPathSegment(jsonPrimitive.getAsInt()));
                } else if (jsonPrimitive.isString()) {
                    arrayList.add(new GraphQLPathSegment(jsonPrimitive.getAsString()));
                } else {
                    throw new JsonParseException("Expected a String or int, but found a " + JsonPrimitive.class.getSimpleName() + " while deserializing path segment");
                }
            }
            return arrayList;
        }
    }
}
