package io.gsonfire.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.gsonfire.util.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class JsonArrayBuilder implements JsonElementBuilder<JsonArray> {
    private final JsonArray array = new JsonArray();

    public JsonArrayBuilder add(JsonElement jsonElement) {
        this.array.add(jsonElement);
        return this;
    }

    public JsonArrayBuilder add(JsonElementBuilder jsonElementBuilder) {
        this.array.add(jsonElementBuilder.build());
        return this;
    }

    public JsonArrayBuilder add(Boolean bool) {
        this.array.add(bool);
        return this;
    }

    public JsonArrayBuilder add(Character ch) {
        this.array.add(ch);
        return this;
    }

    public JsonArrayBuilder add(Number number) {
        this.array.add(number);
        return this;
    }

    public JsonArrayBuilder add(String str) {
        this.array.add(str);
        return this;
    }

    public JsonArrayBuilder addAll(JsonArray jsonArray) {
        this.array.addAll(jsonArray);
        return this;
    }

    @Override // io.gsonfire.builders.JsonElementBuilder
    public JsonArray build() {
        return JsonUtils.deepCopy(this.array).getAsJsonArray();
    }

    public static JsonArrayBuilder start() {
        return new JsonArrayBuilder();
    }
}
