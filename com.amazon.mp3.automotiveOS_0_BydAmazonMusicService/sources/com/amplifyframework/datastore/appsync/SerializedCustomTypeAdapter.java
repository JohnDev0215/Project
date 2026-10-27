package com.amplifyframework.datastore.appsync;

import com.amplifyframework.core.model.SerializedCustomType;
import com.amplifyframework.util.GsonObjectConverter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class SerializedCustomTypeAdapter implements JsonDeserializer<SerializedCustomType>, JsonSerializer<SerializedCustomType> {
    private SerializedCustomTypeAdapter() {
    }

    public static void register(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(SerializedCustomType.class, new SerializedCustomTypeAdapter());
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(SerializedCustomType serializedCustomType, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(serializedCustomType.getSerializedData());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public SerializedCustomType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        HashMap map = new HashMap(GsonObjectConverter.toMap(asJsonObject));
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            if (entry.getValue().isJsonObject()) {
                map.put(entry.getKey(), SerializedCustomType.builder().serializedData(deserialize(entry.getValue(), type, jsonDeserializationContext).getSerializedData()).customTypeSchema(null).build());
            } else if (entry.getValue().isJsonArray()) {
                JsonArray asJsonArray = entry.getValue().getAsJsonArray();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < asJsonArray.size(); i++) {
                    JsonElement jsonElement2 = asJsonArray.get(i);
                    if (jsonElement2.isJsonObject()) {
                        arrayList.add(SerializedCustomType.builder().serializedData(deserialize(jsonElement2, type, jsonDeserializationContext).getSerializedData()).customTypeSchema(null).build());
                    } else {
                        List list = (List) map.get(entry.getKey());
                        if (list != null) {
                            arrayList.add(list.get(i));
                        }
                    }
                }
                map.put(entry.getKey(), arrayList);
            } else {
                map.put(entry.getKey(), map.get(entry.getKey()));
            }
        }
        return SerializedCustomType.builder().serializedData(map).customTypeSchema(null).build();
    }
}
