package com.amplifyframework.datastore.appsync;

import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.util.GsonObjectConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class SerializedModelAdapter implements JsonDeserializer<SerializedModel>, JsonSerializer<SerializedModel> {
    private SerializedModelAdapter() {
    }

    public static void register(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(SerializedModel.class, new SerializedModelAdapter());
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(SerializedModel serializedModel, Type type, JsonSerializationContext jsonSerializationContext) {
        ModelSchema modelSchema = serializedModel.getModelSchema();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("id", jsonSerializationContext.serialize(serializedModel.getPrimaryKeyString()));
        jsonObject.add("modelSchema", jsonSerializationContext.serialize(modelSchema));
        JsonObject jsonObject2 = new JsonObject();
        for (Map.Entry<String, Object> entry : serializedModel.getSerializedData().entrySet()) {
            if (entry.getValue() instanceof SerializedModel) {
                jsonObject2.add(entry.getKey(), jsonSerializationContext.serialize(((SerializedModel) entry.getValue()).getSerializedData()));
            } else {
                jsonObject2.add(entry.getKey(), jsonSerializationContext.serialize(entry.getValue()));
            }
        }
        jsonObject.add("serializedData", jsonObject2);
        return jsonObject;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public SerializedModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        ModelSchema modelSchema = (ModelSchema) jsonDeserializationContext.deserialize(asJsonObject.get("modelSchema"), ModelSchema.class);
        JsonObject asJsonObject2 = asJsonObject.get("serializedData").getAsJsonObject();
        HashMap map = new HashMap(GsonObjectConverter.toMap(asJsonObject2));
        for (Map.Entry<String, JsonElement> entry : asJsonObject2.entrySet()) {
            ModelField modelField = modelSchema.getFields().get(entry.getKey());
            if (modelField != null && modelField.isModel()) {
                map.put(modelField.getName(), SerializedModel.builder().modelSchema(SchemaRegistry.instance().getModelSchemaForModelClass(modelField.getTargetType())).serializedData((Map) new Gson().fromJson(entry.getValue(), new TypeToken<Map<String, Object>>() { // from class: com.amplifyframework.datastore.appsync.SerializedModelAdapter.1
                }.getType())).build());
            }
        }
        return SerializedModel.builder().modelSchema(modelSchema).serializedData(map).build();
    }
}
