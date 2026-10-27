package com.amplifyframework.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonObjectConverter {
    private GsonObjectConverter() {
        throw new UnsupportedOperationException("No instances allowed.");
    }

    public static Map<String, Object> toMap(JsonObject jsonObject) {
        HashMap map = new HashMap();
        for (String str : jsonObject.keySet()) {
            map.put(str, toObject(jsonObject.get(str)));
        }
        return Immutable.of(map);
    }

    public static List<Object> toList(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(toObject(jsonArray.get(i)));
        }
        return Immutable.of(arrayList);
    }

    private static Object toObject(JsonElement jsonElement) {
        if (jsonElement == null) {
            return null;
        }
        if (jsonElement.isJsonArray()) {
            return toList(jsonElement.getAsJsonArray());
        }
        if (jsonElement.isJsonObject()) {
            return toMap(jsonElement.getAsJsonObject());
        }
        if (!jsonElement.isJsonPrimitive()) {
            return null;
        }
        JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
        if (asJsonPrimitive.isString()) {
            return asJsonPrimitive.getAsString();
        }
        if (asJsonPrimitive.isNumber()) {
            Number asNumber = asJsonPrimitive.getAsNumber();
            if (asNumber.floatValue() == asNumber.intValue()) {
                return Integer.valueOf(asNumber.intValue());
            }
            if (asNumber.floatValue() == asNumber.doubleValue()) {
                return Float.valueOf(asNumber.floatValue());
            }
            return Double.valueOf(asNumber.doubleValue());
        }
        if (asJsonPrimitive.isBoolean()) {
            return Boolean.valueOf(asJsonPrimitive.getAsBoolean());
        }
        return null;
    }
}
