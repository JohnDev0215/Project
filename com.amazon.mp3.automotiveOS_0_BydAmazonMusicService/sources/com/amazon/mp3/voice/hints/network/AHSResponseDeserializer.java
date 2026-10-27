package com.amazon.mp3.voice.hints.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: AHSResponseDeserializer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/voice/hints/network/AHSResponseDeserializer;", "Lcom/google/gson/JsonDeserializer;", "Lcom/amazon/mp3/voice/hints/network/AHSHints;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AHSResponseDeserializer implements JsonDeserializer<AHSHints> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public AHSHints deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonElement jsonElement;
        JsonElement jsonElement2;
        JsonObject asJsonObject;
        JsonElement jsonElement3;
        Set<String> setKeySet;
        JsonElement jsonElement4;
        JsonObject asJsonObject2;
        JsonElement jsonElement5;
        ArrayList arrayList = new ArrayList();
        JsonObject asJsonObject3 = json == null ? null : json.getAsJsonObject();
        JsonObject asJsonObject4 = (asJsonObject3 == null || (jsonElement = asJsonObject3.get("_embedded")) == null) ? null : jsonElement.getAsJsonObject();
        JsonArray asJsonArray = (asJsonObject4 == null || (jsonElement2 = asJsonObject4.get("hints")) == null) ? null : jsonElement2.getAsJsonArray();
        if (asJsonArray != null) {
            int i = 0;
            int size = asJsonArray.size();
            while (i < size) {
                int i2 = i + 1;
                JsonElement jsonElement6 = asJsonArray.get(i);
                JsonObject asJsonObject5 = (jsonElement6 == null || (asJsonObject = jsonElement6.getAsJsonObject()) == null || (jsonElement3 = asJsonObject.get("localizedContent")) == null) ? null : jsonElement3.getAsJsonObject();
                String asString = (asJsonObject5 == null || (jsonElement4 = asJsonObject5.get((asJsonObject5 != null && (setKeySet = asJsonObject5.keySet()) != null) ? (String) CollectionsKt___CollectionsKt.first(setKeySet) : null)) == null || (asJsonObject2 = jsonElement4.getAsJsonObject()) == null || (jsonElement5 = asJsonObject2.get("utteranceText")) == null) ? null : jsonElement5.getAsString();
                if (asString != null) {
                    arrayList.add(asString);
                }
                i = i2;
            }
        }
        return new AHSHints(arrayList);
    }
}
