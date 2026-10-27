package com.amazon.mp3.auto.carmode;

import android.net.Uri;
import com.amazon.mp3.util.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UriGsonSerializerDeserializer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/auto/carmode/UriGsonSerializerDeserializer;", "Lcom/google/gson/JsonSerializer;", "Landroid/net/Uri;", "Lcom/google/gson/JsonDeserializer;", "()V", "deserialize", "src", "Lcom/google/gson/JsonElement;", "srcType", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "serialize", "typeOfSrc", "Lcom/google/gson/JsonSerializationContext;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UriGsonSerializerDeserializer implements JsonSerializer<Uri>, JsonDeserializer<Uri> {
    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(String.valueOf(src));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public Uri deserialize(JsonElement src, Type srcType, JsonDeserializationContext context) throws JsonParseException {
        Intrinsics.checkNotNullParameter(src, "src");
        if (src.isJsonNull() || src.isJsonObject() || src.isJsonArray()) {
            Uri EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            return EMPTY;
        }
        try {
            Uri uri = Uri.parse(src.getAsString());
            Intrinsics.checkNotNullExpressionValue(uri, "{\n            Uri.parse(src.asString)\n        }");
            return uri;
        } catch (Exception e) {
            Log.error(UriGsonSerializerDeserializer.class.getSimpleName(), "Error deserializing Uri", e);
            Uri EMPTY2 = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY2, "EMPTY");
            return EMPTY2;
        }
    }
}
