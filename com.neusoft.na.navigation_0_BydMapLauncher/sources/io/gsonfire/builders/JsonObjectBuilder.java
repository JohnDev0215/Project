package io.gsonfire.builders;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import io.gsonfire.util.JsonUtils;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class JsonObjectBuilder implements JsonElementBuilder<JsonObject> {
    private final JsonObject object = new JsonObject();

    public JsonObjectBuilder set(String str, String str2) {
        this.object.addProperty(str, str2);
        return this;
    }

    public JsonObjectBuilder set(String str, Number number) {
        this.object.addProperty(str, number);
        return this;
    }

    public JsonObjectBuilder set(String str, Boolean bool) {
        this.object.addProperty(str, bool);
        return this;
    }

    public JsonObjectBuilder set(String str, JsonElement jsonElement) {
        this.object.add(str, jsonElement);
        return this;
    }

    public JsonObjectBuilder set(String str, JsonElementBuilder jsonElementBuilder) {
        this.object.add(str, jsonElementBuilder.build());
        return this;
    }

    public JsonObjectBuilder setNull(String str) {
        this.object.add(str, JsonNull.INSTANCE);
        return this;
    }

    public JsonObjectBuilder merge(JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> entry : JsonUtils.deepCopy(jsonObject).getAsJsonObject().entrySet()) {
            this.object.add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.gsonfire.builders.JsonElementBuilder
    public JsonObject build() {
        return JsonUtils.deepCopy(this.object).getAsJsonObject();
    }
}
