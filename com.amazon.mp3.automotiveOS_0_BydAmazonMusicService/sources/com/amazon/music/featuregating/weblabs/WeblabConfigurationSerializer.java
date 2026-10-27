package com.amazon.music.featuregating.weblabs;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonTransformingSerializer;

/* JADX INFO: compiled from: WeblabConfigurationReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabConfigurationSerializer;", "Lkotlinx/serialization/json/JsonTransformingSerializer;", "Lcom/amazon/music/featuregating/weblabs/Weblabs;", "()V", "transformDeserialize", "Lkotlinx/serialization/json/JsonElement;", "element", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class WeblabConfigurationSerializer extends JsonTransformingSerializer<Weblabs> {
    public WeblabConfigurationSerializer() {
        super(Weblabs.INSTANCE.serializer());
    }

    @Override // kotlinx.serialization.json.JsonTransformingSerializer
    protected JsonElement transformDeserialize(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (element instanceof JsonArray) {
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            jsonObjectBuilder.put("weblabs", element);
            return jsonObjectBuilder.build();
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
