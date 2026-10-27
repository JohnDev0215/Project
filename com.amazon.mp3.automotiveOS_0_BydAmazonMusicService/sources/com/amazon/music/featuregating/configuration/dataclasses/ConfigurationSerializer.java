package com.amazon.music.featuregating.configuration.dataclasses;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonTransformingSerializer;

/* JADX INFO: compiled from: FinalConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/featuregating/configuration/dataclasses/ConfigurationSerializer;", "Lkotlinx/serialization/json/JsonTransformingSerializer;", "Lcom/amazon/music/featuregating/configuration/dataclasses/FinalConfiguration;", "()V", "transformDeserialize", "Lkotlinx/serialization/json/JsonElement;", "element", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConfigurationSerializer extends JsonTransformingSerializer<FinalConfiguration> {
    public ConfigurationSerializer() {
        super(FinalConfiguration.INSTANCE.serializer());
    }

    @Override // kotlinx.serialization.json.JsonTransformingSerializer
    protected JsonElement transformDeserialize(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (!(element instanceof JsonObject)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        JsonObject jsonObject = JsonElementKt.getJsonObject(element);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (!Intrinsics.areEqual(entry.getKey(), "configuration")) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        jsonObjectBuilder.put("entries", new JsonObject(linkedHashMap));
        JsonElement jsonElement = (JsonElement) JsonElementKt.getJsonObject(element).get((Object) "configuration");
        if (jsonElement != null) {
            jsonObjectBuilder.put("configuration", jsonElement);
        }
        return jsonObjectBuilder.build();
    }
}
