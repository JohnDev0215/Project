package com.neusoft.de.nts.OneCoreTest.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class OneCoreJsonMapTypeAdapter<K, V> implements JsonSerializer<Map<K, V>>, JsonDeserializer<Map<K, V>> {
    OneCoreJsonMapTypeAdapter() {
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(Map<K, V> map, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            JsonArray jsonArray2 = new JsonArray();
            jsonArray2.add(jsonSerializationContext.serialize(entry.getKey()));
            jsonArray2.add(jsonSerializationContext.serialize(entry.getValue()));
            jsonArray.add(jsonArray2);
        }
        return jsonArray;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.gson.JsonDeserializer
    public Map<K, V> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        Type type2 = actualTypeArguments[0];
        Type type3 = actualTypeArguments[1];
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
        while (it.hasNext()) {
            JsonArray asJsonArray = it.next().getAsJsonArray();
            linkedHashMap.put(jsonDeserializationContext.deserialize(asJsonArray.get(0), type2), jsonDeserializationContext.deserialize(asJsonArray.get(1), type3));
        }
        return linkedHashMap;
    }
}
